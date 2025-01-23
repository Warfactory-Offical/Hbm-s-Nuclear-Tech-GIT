package api.hbm.energy;

import com.hbm.lib.ForgeDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IEnergyGenerator extends IEnergyUser {

	/**
	 * Standard implementation for machines that can only send energy but never receive it.
	 * @param power
	 */
	@Override
	public default long transferPower(final long power) {
		return power;
	}

	/* should stop making non-receivers from interfering by applying their weight which doesn't even matter */
	@Override
	public default long getTransferWeight() {
		return 0;
	}

	public default void sendPower(final World world, final BlockPos pos){
		for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			this.sendPower(world, pos.add(dir.offsetX, dir.offsetY, dir.offsetZ), dir);
	}
}
