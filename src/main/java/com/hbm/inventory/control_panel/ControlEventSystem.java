package com.hbm.inventory.control_panel;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hbm.lib.RefStrings;

import com.hbm.main.MainRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

@Mod.EventBusSubscriber(modid = RefStrings.MODID)
public class ControlEventSystem {

	private static final Map<World, ControlEventSystem> systems = new HashMap<>();
	
	private final Set<IControllable> allControllables = new HashSet<>();
	private final Set<IControllable> tickables = new HashSet<>();
	private final Map<String, Map<BlockPos, IControllable>> controllablesByEventName = new HashMap<>();
	private final Map<BlockPos, Set<IControllable>> positionSubscriptions = new HashMap<>();
	
	public void addControllable(final IControllable c){
		if(allControllables.contains(c))
			return;
		for(final String s : c.getInEvents()){
			if(s.equals("tick")){
				tickables.add(c);
				continue;
			}
			if(!controllablesByEventName.containsKey(s)){
				controllablesByEventName.put(s, new HashMap<>());
			}
			controllablesByEventName.get(s).put(c.getControlPos(), c);
		}
		allControllables.add(c);
	}
	
	public void removeControllable(final IControllable c){
		for(final String s : c.getInEvents()){
			if(s.equals("tick")){
				tickables.remove(c);
				continue;
			}
			controllablesByEventName.get(s).remove(c);
		}
		allControllables.remove(c);
	}
	
	public boolean isValid(final IControllable c){
		return allControllables.contains(c);
	}
	
	public void subscribeTo(final IControllable subscriber, final IControllable target){
		if(!positionSubscriptions.containsKey(target.getControlPos())){
			positionSubscriptions.put(target.getControlPos(), new HashSet<>());
		}
		if(!positionSubscriptions.get(target).contains(subscriber))
			positionSubscriptions.get(target.getControlPos()).add(subscriber);
	}
	
	public void subscribeTo(final IControllable subscriber, final BlockPos target){
		if(!positionSubscriptions.containsKey(target)){
			positionSubscriptions.put(target, new HashSet<>());
		}
        positionSubscriptions.get(target).add(subscriber);
	}
	
	public void unsubscribeFrom(final IControllable subscriber, final IControllable target){
		if(positionSubscriptions.containsKey(target.getControlPos())){
			positionSubscriptions.get(target.getControlPos()).remove(subscriber);
			if(positionSubscriptions.get(target.getControlPos()).isEmpty())
				positionSubscriptions.remove(target.getControlPos());
		}
	}
	
	public void unsubscribeFrom(final IControllable subscriber, final BlockPos target){
		if(positionSubscriptions.containsKey(target)){
			positionSubscriptions.get(target).remove(subscriber);
			if(positionSubscriptions.get(target).isEmpty())
				positionSubscriptions.remove(target);
		}
	}
	
	public void broadcastEvent(final BlockPos from, final ControlEvent evt, final BlockPos pos){
		final Map<BlockPos, IControllable> map = controllablesByEventName.get(evt.name);
		if(map == null)
			return;
		final IControllable c = map.get(pos);
		if(c != null) {
			c.receiveEvent(from, evt);
		}
	}
	
	public void broadcastEvent(final BlockPos from, final ControlEvent evt, final Collection<BlockPos> positions){
		final Map<BlockPos, IControllable> map = controllablesByEventName.get(evt.name);
		if(map == null)
			return;
		if(positions == null){
			for(final IControllable c : map.values()){
				c.receiveEvent(from, evt);
			}
		} else {
			for(final BlockPos pos : positions){
				final IControllable c = map.get(pos);
				if(c != null){
					c.receiveEvent(from, evt);
				}
			}
		}
	}
	
	public void broadcastEvent(final BlockPos from, final ControlEvent c){
		broadcastEvent(from, c, (Collection<BlockPos>)null);
	}
	
	public void broadcastToSubscribed(final IControllable ctrl, final ControlEvent evt){
		final Set<IControllable> subscribed = positionSubscriptions.get(ctrl.getControlPos());
		if(subscribed == null)
			return;
		for(final IControllable sub : subscribed){
			sub.receiveEvent(ctrl.getControlPos(), evt);
		}
	}
	
	public static ControlEventSystem get(final World w){
		if(!systems.containsKey(w))
			systems.put(w, new ControlEventSystem());
		return systems.get(w);
	}
	
	@SubscribeEvent
	public static void tick(final WorldTickEvent evt){
		if(systems.containsKey(evt.world)){
			final ControlEventSystem s = systems.get(evt.world);
			for(final IControllable c : s.tickables){
				c.receiveEvent(c.getControlPos(), ControlEvent.newEvent("tick").setVar("time", evt.world.getTotalWorldTime()));
			}
		}
	}
	
	@SubscribeEvent
	public static void worldUnload(final WorldEvent.Unload evt){
		systems.remove(evt.getWorld());
	}
}
