package api.hbm.fluid;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.tank.FluidTank;

public interface IFluidStandardTransceiver extends IFluidUser {

    public FluidTank[] getSendingTanks();
    public FluidTank[] getReceivingTanks();

    @Override
    public default long getTotalFluidForSend(FluidType type, int pressure) {

        for(FluidTank tank : getSendingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                return tank.getFill();
            }
        }

        return 0;
    }

    @Override
    public default void removeFluidForTransfer(FluidType type, int pressure, long amount) {

        for(FluidTank tank : getSendingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                tank.setFill(tank.getFill() - (int) amount);
                return;
            }
        }
    }

    @Override
    public default long getDemand(FluidType type, int pressure) {

        for(FluidTank tank : getReceivingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                return tank.getMaxFill() - tank.getFill();
            }
        }

        return 0;
    }

    @Override
    public default long transferFluid(FluidType type, int pressure, long amount) {

        for(FluidTank tank : getReceivingTanks()) {
            if(tank.getTankType() == type && tank.getPressure() == pressure) {
                tank.setFill(tank.getFill() + (int) amount);

                if(tank.getFill() > tank.getMaxFill()) {
                    long overshoot = tank.getFill() - tank.getMaxFill();
                    tank.setFill(tank.getMaxFill());
                    return overshoot;
                }

                return 0;
            }
        }

        return amount;
    }
}
