package api.hbm.energy;

import com.hbm.lib.ForgeDirection;
import com.hbm.render.amlfrom1710.Vec3;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * For anything that connects to power and can be transferred power to, the bottom-level interface.
 * This is mean for TILE ENTITIES
 * @author hbm
 */
public interface IEnergyConnector extends ILoadedTile {
	
	/**
	 * Returns the amount of power that remains in the source after transfer
	 * @param power
	 * @return
	 */
	public long transferPower(long power);
	
	/**
	 * Whether the given side can be connected to
	 * dir refers to the side of this block, not the connecting block doing the check
	 * @param dir
	 * @return
	 */
	public default boolean canConnect(final ForgeDirection dir) {
		return dir != ForgeDirection.UNKNOWN;
	}
	
	/**
	 * The current power of either the machine or an entire network
	 * @return
	 */
	public long getPower();
	
	/**
	 * The capacity of either the machine or an entire network
	 * @return
	 */
	public long getMaxPower();
	
	public default long getTransferWeight() {
		return Math.max(getMaxPower() - getPower(), 0);
	}
	
	/**
	 * Basic implementation of subscribing to a nearby power grid
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	public default void trySubscribe(final World world, final BlockPos pos, final ForgeDirection dir) {

		final TileEntity te = world.getTileEntity(pos);
		boolean red = false;
		
		if(te instanceof IEnergyConductor con) {

            if(!con.canConnect(dir.getOpposite()))
				return;
			
			if(con.getPowerNet() != null && !con.getPowerNet().isSubscribed(this))
				con.getPowerNet().subscribe(this);
			
			if(con.getPowerNet() != null)
				red = true;
		}
		
		// if(particleDebug) {//
		// 	NBTTagCompound data = new NBTTagCompound();
		// 	data.setString("type", "network");
		// 	data.setString("mode", "power");
		// 	double posX = pos.getX() + 0.5 + dir.offsetX * 0.5 + world.rand.nextDouble() * 0.5 - 0.25;
		// 	double posY = pos.getY() + 0.5 + dir.offsetY * 0.5 + world.rand.nextDouble() * 0.5 - 0.25;
		// 	double posZ = pos.getZ() + 0.5 + dir.offsetZ * 0.5 + world.rand.nextDouble() * 0.5 - 0.25;
		// 	data.setDouble("mX", -dir.offsetX * (red ? 0.025 : 0.1));
		// 	data.setDouble("mY", -dir.offsetY * (red ? 0.025 : 0.1));
		// 	data.setDouble("mZ", -dir.offsetZ * (red ? 0.025 : 0.1));
		// 	PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, posX, posY, posZ), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 25));
		// }
	}
	
	public default void tryUnsubscribe(final World world, final BlockPos pos) {

		final TileEntity te = world.getTileEntity(pos);
		
		if(te instanceof IEnergyConductor con) {

            if(con.getPowerNet() != null && con.getPowerNet().isSubscribed(this))
				con.getPowerNet().unsubscribe(this);
		}
	}
	
	public static final boolean particleDebug = true;
	
	public default Vec3 getDebugParticlePos() {
		final BlockPos pos = ((TileEntity) this).getPos();
		final Vec3 vec = Vec3.createVectorHelper(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		return vec;
	}
	
	public default ConnectionPriority getPriority() {
		return ConnectionPriority.NORMAL;
	}
	
	public enum ConnectionPriority {
		LOW,
		NORMAL,
		HIGH
	}

	public default boolean isStorage() { //used for batteries
		return false;
	}

	public default void updateStandardConnections(final World world, final TileEntity te) {
		updateStandardConnections(world, te.getPos());
	}
		
	public default void updateStandardConnections(final World world, final BlockPos pos) {
		
		for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			this.trySubscribe(world, pos.add(dir.offsetX, dir.offsetY, dir.offsetZ), dir);
		}
	}

	public default void updateConnectionsExcept(final World world, final BlockPos pos, final ForgeDirection nogo) {
		
		for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if(dir != nogo)
				this.trySubscribe(world, pos.add(dir.offsetX, dir.offsetY, dir.offsetZ), dir);
		}
	}
}
