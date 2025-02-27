package api.hbm.energy;

import com.hbm.lib.ForgeDirection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * For machines and things that have an energy buffer and are affected by EMPs
 * @author hbm
 */
public interface IEnergyUser extends IEnergyConnector {
	
	/**
	 * Not to be used for actual energy transfer, rather special external things like EMPs and sync packets
	 */
	public void setPower(long power);
	
	/**
	 * Standard implementation for power transfer.
	 * Turns out you can override interfaces to provide a default implementation. Neat.
	 * @param long power
	 */
	@Override
	public default long transferPower(final long power) {
		final long ownMaxPower = this.getMaxPower();
		final long ownPower = this.getPower();
		if(power > ownMaxPower - ownPower) {
			
			final long overshoot = power-(ownMaxPower-ownPower);
			this.setPower(ownMaxPower);
			return overshoot;
		}

		this.setPower(ownPower + power);
		
		return 0;
	}
	
	/**
	 * Standard implementation of sending power
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param dir
	 */
	public default void sendPower(final World world, final BlockPos pos, final ForgeDirection dir) {
		
		final TileEntity te = world.getTileEntity(pos);
		boolean wasSubscribed = false;
		boolean red = false;
		
		// first we make sure we're not subscribed to the network that we'll be supplying
		if(te instanceof IEnergyConductor con) {

            if(con.canConnect(dir.getOpposite()) && con.getPowerNet() != null && con.getPowerNet().isSubscribed(this)) {
				con.getPowerNet().unsubscribe(this);
				wasSubscribed = true;
			}
		}
		
		//then we add energy
		if(te instanceof IEnergyConnector con) {

            if(con.canConnect(dir.getOpposite())) {
				final long oldPower = this.getPower();
				final long transfer = oldPower - con.transferPower(oldPower);
				this.setPower(oldPower - transfer);
				red = true;
			}
		}
		
		//then we subscribe if possible
		if(wasSubscribed && te instanceof IEnergyConductor con) {

            if(con.getPowerNet() != null && !con.getPowerNet().isSubscribed(this)) {
				con.getPowerNet().subscribe(this);
			}
		}
		
		// if(particleDebug) {
		// 	NBTTagCompound data = new NBTTagCompound();
		// 	data.setString("type", "network");
		// 	data.setString("mode", "power");
		// 	double posX = pos.getX() + 0.5 + dir.offsetX * 0.5 + world.rand.nextDouble() * 0.5 - 0.25;
		// 	double posY = pos.getY() + 0.5 + dir.offsetY * 0.5 + world.rand.nextDouble() * 0.5 - 0.25;
		// 	double posZ = pos.getZ() + 0.5 + dir.offsetZ * 0.5 + world.rand.nextDouble() * 0.5 - 0.25;
		// 	data.setDouble("mX", dir.offsetX * (red ? 0.025 : 0.1));
		// 	data.setDouble("mY", dir.offsetY * (red ? 0.025 : 0.1));
		// 	data.setDouble("mZ", dir.offsetZ * (red ? 0.025 : 0.1));
		// 	PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, posX, posY, posZ), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 25));
		// }
	}
}
