package com.hbm.tileentity.machine.oil;

import api.hbm.energymk2.IEnergyReceiverMK2;
import api.hbm.fluid.IFluidStandardReceiver;
import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.SolidificationRecipes;
import com.hbm.inventory.UpgradeManager;
import com.hbm.inventory.container.ContainerSolidifier;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTank;
import com.hbm.inventory.gui.GUISolidifier;
import com.hbm.items.machine.ItemMachineUpgrade;
import com.hbm.lib.DirPos;
import com.hbm.lib.Library;
import com.hbm.tileentity.IFluidCopiable;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.util.I18nUtil;
import com.hbm.util.Tuple;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileEntityMachineSolidifier extends TileEntityMachineBase implements ITickable, IEnergyReceiverMK2, IFluidStandardReceiver, IGUIProvider, IFluidCopiable {

    public long power;
    public static final long maxPower = 100000;
    public static final int usageBase = 500;
    public int usage;
    public int progress;
    public static final int processTimeBase = 100;
    public int processTime;

    public FluidTank tank;
    public UpgradeManager manager;

    public TileEntityMachineSolidifier() {
        super(5);
        tank = new FluidTank(Fluids.NONE, 24_000);
    }

    @Override
    public String getName() {
        return "container.machineSolidifier";
    }

    @Override
    public void update() {
        manager.eval(inventory, 2, 3);
        if(!world.isRemote) {
            this.power = Library.chargeTEFromItems(inventory, 1, power, maxPower);
            tank.setType(4, inventory);

            this.updateConnections();
            int speed = Math.min(manager.getLevel(ItemMachineUpgrade.UpgradeType.SPEED), 3);
            int power = Math.min(manager.getLevel(ItemMachineUpgrade.UpgradeType.POWER), 3);

            this.processTime = processTimeBase - (processTimeBase / 4) * speed;
            this.usage = (usageBase + (usageBase * speed))  / (power + 1);

            if(this.canProcess())
                this.process();
            else
                this.progress = 0;

            NBTTagCompound data = new NBTTagCompound();
            data.setLong("power", this.power);
            data.setInteger("progress", this.progress);
            data.setInteger("usage", this.usage);
            data.setInteger("processTime", this.processTime);
            tank.writeToNBT(data, "t");
            this.networkPack(data, 50);
        }
    }

    private void updateConnections() {
        for(DirPos pos : getConPos()) {
            this.trySubscribe(world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
            this.trySubscribe(tank.getTankType(), world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
        }
    }

    private DirPos[] getConPos() {
        return new DirPos[] {
                new DirPos(pos.getX(), pos.getY() + 4, pos.getZ(), Library.POS_Y),
                new DirPos(pos.getX(), pos.getY() - 1, pos.getZ(), Library.NEG_Y),
                new DirPos(pos.getX() + 2, pos.getY() + 1, pos.getZ(), Library.POS_X),
                new DirPos(pos.getX() - 2, pos.getY() + 1, pos.getZ(), Library.NEG_X),
                new DirPos(pos.getX(), pos.getY() + 1, pos.getZ() + 2, Library.POS_Z),
                new DirPos(pos.getX(), pos.getY() + 1, pos.getZ() - 2, Library.NEG_Z)
        };
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(EnumFacing e) {
        return new int[] { 0 };
    }

    public boolean canProcess() {

        if(this.power < usage)
            return false;

        Tuple.Pair<Integer, ItemStack> out = SolidificationRecipes.getOutput(tank.getTankType());

        if(out == null)
            return false;

        int req = out.getKey();
        ItemStack stack = out.getValue();

        if(req > tank.getFill())
            return false;

        if(!inventory.getStackInSlot(0).isEmpty()) {

            if(inventory.getStackInSlot(0).getItem() != stack.getItem())
                return false;

            if(inventory.getStackInSlot(0).getItemDamage() != stack.getItemDamage())
                return false;

            if(inventory.getStackInSlot(0).getCount() + stack.getCount() > inventory.getStackInSlot(0).getMaxStackSize())
                return false;
        }

        return true;
    }

    public void process() {

        this.power -= usage;

        progress++;

        if(progress >= processTime) {

            Tuple.Pair<Integer, ItemStack> out = SolidificationRecipes.getOutput(tank.getTankType());
            int req = out.getKey();
            ItemStack stack = out.getValue();
            tank.setFill(tank.getFill() - req);

            if(inventory.getStackInSlot(0).isEmpty()) {
                inventory.setStackInSlot(0, stack.copy());
            } else {
                inventory.getStackInSlot(0).grow(stack.getCount());
            }

            progress = 0;

            this.markDirty();
        }
    }

    @Override
    public void networkUnpack(NBTTagCompound nbt) {
        super.networkUnpack(nbt);

        this.power = nbt.getLong("power");
        this.progress = nbt.getInteger("progress");
        this.usage = nbt.getInteger("usage");
        this.processTime = nbt.getInteger("processTime");
        tank.readFromNBT(nbt, "t");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt, "tank");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        tank.writeToNBT(nbt, "tank");
        return super.writeToNBT(nbt);
    }

    @Override
    public void setPower(long power) {
        this.power = power;
    }

    @Override
    public long getPower() {
        return power;
    }

    @Override
    public long getMaxPower() {
        return maxPower;
    }

    AxisAlignedBB bb = null;

    @Override
    public AxisAlignedBB getRenderBoundingBox() {

        if(bb == null) {
            bb = new AxisAlignedBB(
                    pos.getX() - 1,
                    pos.getY(),
                    pos.getZ() - 1,
                    pos.getX() + 2,
                    pos.getY() + 4,
                    pos.getZ() + 2
            );
        }

        return bb;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }

    @Override
    public FluidTank[] getReceivingTanks() {
        return new FluidTank[] { tank };
    }

    @Override
    public FluidTank[] getAllTanks() {
        return new FluidTank[] { tank };
    }

    @Override
    public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerSolidifier(player.inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GUISolidifier(player.inventory, this);
    }

    @Override
    public FluidTank getTankToPaste() {
        return tank;
    }
}
