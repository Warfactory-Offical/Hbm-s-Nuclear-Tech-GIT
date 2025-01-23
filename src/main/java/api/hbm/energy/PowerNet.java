package api.hbm.energy;

import api.hbm.energy.IEnergyConnector.ConnectionPriority;
import com.hbm.config.GeneralConfig;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Basic IPowerNet implementation. The behavior of this demo might change inbetween releases, but the API remains the same.
 * For more consistency please implement your own IPowerNet.
 * @author hbm
 */
public class PowerNet implements IPowerNet {
	
	private boolean valid = true;
	private final HashMap<Integer, IEnergyConductor> links = new HashMap();
	private final HashMap<Integer, Integer> proxies = new HashMap();
	private final List<IEnergyConnector> subscribers = new ArrayList();

	public static List<PowerNet> trackingInstances = null;
	protected long totalTransfer = 0;

	@Override
	public void joinNetworks(final IPowerNet network) {
		
		if(network == this)
			return; //wtf?!

		for(final IEnergyConductor conductor : network.getLinks()) {
			joinLink(conductor);
		}
		network.getLinks().clear();
		
		for(final IEnergyConnector connector : network.getSubscribers()) {
			this.subscribe(connector);
		}
		
		network.destroy();
	}

	@Override
	public IPowerNet joinLink(final IEnergyConductor conductor) {
		
		if(conductor.getPowerNet() != null)
			conductor.getPowerNet().leaveLink(conductor);
		
		conductor.setPowerNet(this);
		final int identity = conductor.getIdentity();
		this.links.put(identity, conductor);
		
		if(conductor.hasProxies()) {
			for(final Integer i : conductor.getProxies()) {
				this.proxies.put(i, identity);
			}
		}
		
		return this;
	}

	@Override
	public void leaveLink(final IEnergyConductor conductor) {
		conductor.setPowerNet(null);
		final int identity = conductor.getIdentity();
		this.links.remove(identity);
		
		if(conductor.hasProxies()) {
			for(final Integer i : conductor.getProxies()) {
				this.proxies.remove(i);
			}
		}
	}

	@Override
	public void subscribe(final IEnergyConnector connector) {
		this.subscribers.add(connector);
	}

	@Override
	public void unsubscribe(final IEnergyConnector connector) {
		this.subscribers.remove(connector);
	}

	@Override
	public boolean isSubscribed(final IEnergyConnector connector) {
		return this.subscribers.contains(connector);
	}

	@Override
	public List<IEnergyConductor> getLinks() {
		final List<IEnergyConductor> linkList = new ArrayList();
		linkList.addAll(this.links.values());
		return linkList;
	}

	public HashMap<Integer, Integer> getProxies() {
		final HashMap<Integer, Integer> proxyCopy = new HashMap(proxies);
		return proxyCopy;
	}

	@Override
	public List<IEnergyConnector> getSubscribers() {
		return this.subscribers;
	}
	
	@Override
	public void destroy() {
		this.valid = false;
		this.subscribers.clear();
		
		for(final IEnergyConductor link : this.links.values()) {
			link.setPowerNet(null);
		}
		
		this.links.clear();
	}
	
	@Override
	public boolean isValid() {
		return this.valid;
	}

	@Override
	public long getTotalTransfer() {
		return this.totalTransfer;
	}
	
	public long lastCleanup = System.currentTimeMillis();
	
	@Override
	public long transferPower(final long power) {
		
		final List<PowerNet> cache = new ArrayList();
		if(trackingInstances != null && !trackingInstances.isEmpty()) {
			cache.addAll(trackingInstances);
		}

		trackingInstances = new ArrayList();
		trackingInstances.add(this);
		final long result = fairTransfer(this.subscribers, power);
		trackingInstances.addAll(cache);
		return result;
	}
	
	public static void cleanup(final List<IEnergyConnector> subscribers) {

		subscribers.removeIf(x -> 
			x == null || !(x instanceof TileEntity) || ((TileEntity)x).isInvalid() || !x.isLoaded()
		);
	}

	public static boolean shouldSend(final ConnectionPriority senderPrio, final ConnectionPriority p, final IEnergyConnector x){
		return (x.getPriority() == p) && (!x.isStorage() || (senderPrio.compareTo(p) <= 0));
	}

	public static long fairTransferWithPrio(final ConnectionPriority senderPrio, final List<IEnergyConnector> subscribers, long power) {
		
		if(power <= 0) return 0;
		
		if(subscribers.isEmpty())
			return power;
		
		cleanup(subscribers);
		
		final ConnectionPriority[] priorities = new ConnectionPriority[] {ConnectionPriority.HIGH, ConnectionPriority.NORMAL, ConnectionPriority.LOW};
		
		long totalTransfer = 0;
		
		for(final ConnectionPriority p : priorities) {
			
			final List<IEnergyConnector> subList = new ArrayList();
			subscribers.forEach(x -> {
				if(shouldSend(senderPrio, p, x)) {
					subList.add(x);
				}
			});
			
			if(subList.isEmpty())
				continue;
			
			final List<Long> weight = new ArrayList();
			long totalReq = 0;
			
			for(final IEnergyConnector con : subList) {
				final long req = con.getTransferWeight();
				weight.add(req);
				totalReq += req;
			}
			
			if(totalReq == 0)
				continue;
			
			long totalGiven = 0;
			
			for(int i = 0; i < subList.size(); i++) {
				final IEnergyConnector con = subList.get(i);
				final long req = weight.get(i);
				final double fraction = (double)req / (double)totalReq;
				
				final long given = (long) Math.floor(fraction * power);
				
				totalGiven += (given - con.transferPower(given));

				if(con instanceof TileEntity tile) {
                    tile.getWorld().markChunkDirty(tile.getPos(), tile);
				}
			}
			
			power -= totalGiven;
			totalTransfer += totalGiven;
		}

		if(trackingInstances != null) {
			
			for(int i = 0; i < trackingInstances.size(); i++) {
				final PowerNet net = trackingInstances.get(i);
				net.totalTransfer += totalTransfer;
			}
			
			trackingInstances.clear();
		}
		
		return power;
	}

	public static long fairTransfer(final List<IEnergyConnector> subscribers, long power) {
		
		if(power <= 0) return 0;
		
		if(subscribers.isEmpty())
			return power;
		
		cleanup(subscribers);
		
		final ConnectionPriority[] priorities = new ConnectionPriority[] {ConnectionPriority.HIGH, ConnectionPriority.NORMAL, ConnectionPriority.LOW};
		
		long totalTransfer = 0;
		
		for(final ConnectionPriority p : priorities) {
			
			final List<IEnergyConnector> subList = new ArrayList();
			subscribers.forEach(x -> {
				if(x.getPriority() == p) {
					subList.add(x);
				}
			});
			
			if(subList.isEmpty())
				continue;
			
			final List<Long> weight = new ArrayList();
			long totalReq = 0;
			
			for(final IEnergyConnector con : subList) {
				final long req = con.getTransferWeight();
				weight.add(req);
				totalReq += req;
			}
			
			if(totalReq == 0)
				continue;
			
			long totalGiven = 0;
			
			for(int i = 0; i < subList.size(); i++) {
				final IEnergyConnector con = subList.get(i);
				final long req = weight.get(i);
				final double fraction = (double)req / (double)totalReq;
				
				final long given = (long) Math.floor(fraction * power);
				
				totalGiven += (given - con.transferPower(given));

				if(con instanceof TileEntity tile) {
                    tile.getWorld().markChunkDirty(tile.getPos(), tile);
				}
			}
			
			power -= totalGiven;
			totalTransfer += totalGiven;
		}

		if(trackingInstances != null) {
			
			for(int i = 0; i < trackingInstances.size(); i++) {
				final PowerNet net = trackingInstances.get(i);
				net.totalTransfer += totalTransfer;
			}
			
			trackingInstances.clear();
		}
		
		return power;
	}

	@Override
	public void reevaluate() {
		
		if(!GeneralConfig.enableReEval) {
			this.destroy();
			return;
		}

		final HashMap<Integer, IEnergyConductor> copy = new HashMap(links);
		final HashMap<Integer, Integer> proxyCopy = new HashMap(proxies);
		
		for(final IEnergyConductor link : copy.values()) {
			this.leaveLink(link);
		}
		
		for(final IEnergyConductor link : copy.values()) {
			
			link.setPowerNet(null);
			link.reevaluate(copy, proxyCopy);
			
			if(link.getPowerNet() == null) {
				link.setPowerNet(new PowerNet().joinLink(link));
			}
		}
	}
}
