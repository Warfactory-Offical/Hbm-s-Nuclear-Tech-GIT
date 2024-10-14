package com.hbm.tileentity.machine;

import api.hbm.fluid.IFluidStandardTransceiver;
import com.hbm.capability.HbmCapability;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.IFluidAcceptor;
import com.hbm.interfaces.IFluidContainer;
import com.hbm.interfaces.IFluidSource;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.inventory.control_panel.*;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTank;
import com.hbm.inventory.fluid.trait.FT_Corrosive;
import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.IBufPacketReceiver;
import com.hbm.tileentity.TileEntityMachineBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import scala.actors.threadpool.Arrays;

import java.util.*;

public class TileEntityMachineFluidTank extends TileEntityMachineBase implements ITickable, IFluidContainer, IFluidSource, IFluidAcceptor, IFluidStandardTransceiver, IBufPacketReceiver, IControllable {

	public FluidTank tank;

	public short mode = 0;
	public static final short modes = 4;
	protected boolean sendingBrake = false;
	public int age = 0;
	public List<IFluidAcceptor> list = new ArrayList();
	public static int[] slots = { 2 };
	
	public TileEntityMachineFluidTank() {
		super(6);
		tank = new FluidTank(Fluids.NONE, 256000);
	}
	
	public String getName() {
		return "container.fluidtank";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		tank.readFromNBT(compound, "tank");
		mode = compound.getShort("mode");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		tank.writeToNBT(compound, "tank");
		compound.setShort("mode", mode);
		return super.writeToNBT(compound);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e){
		return slots;
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			age++;
			if (age >= 20) {
				age = 0;
				markDirty();
			}

			this.sendingBrake = true;
			tank.setFill(TileEntityBarrel.transmitFluidFairly(world, tank, this, tank.getFill(), this.mode == 0 || this.mode == 1, this.mode == 1 || this.mode == 2, getConPos()));
			this.sendingBrake = false;

			if ((mode == 1 || mode == 2) && (age == 9 || age == 19))
				fillFluidInit(tank.getTankType());
			
			if(tank.getTankType() != Fluids.NONE && (tank.getTankType().isAntimatter() || tank.getTankType().hasTrait(FT_Corrosive.class) && tank.getTankType().getTrait(FT_Corrosive.class).isHighlyCorrosive())) {
				world.destroyBlock(pos, false);
				world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, true, true);
			}

			tank.unloadTank(4, 5, inventory);

			this.networkPackNT(150);
		}

		ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - 10);
		ForgeDirection rot = dir.getRotation(ForgeDirection.UP);
		List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos, pos.add(1, 2.875, 1)).offset(dir.offsetX * 0.5 - rot.offsetX * 2.25, 0, dir.offsetZ * 0.5 - rot.offsetZ * 2.25));

		for(EntityPlayer player : players) {
			HbmCapability.IHBMData props = HbmCapability.getData(player);
			if(player == MainRegistry.proxy.me() && !props.getOnLadder()) {
				props.setOnLadder(true);
			}
		}
	}

	@Override
	public void serialize(ByteBuf buf) {
		super.serialize(buf);
		buf.writeShort(mode);
		tank.serialize(buf);
	}

	@Override
	public void deserialize(ByteBuf buf) {
		super.deserialize(buf);
		mode = buf.readShort();
		tank.deserialize(buf);
	}
	
	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		mode = nbt.getShort("mode");
	}

	protected DirPos[] getConPos() {
		return new DirPos[] {
				new DirPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1, Library.POS_X),
				new DirPos(pos.getX() + 2, pos.getY(), pos.getZ() + 1, Library.POS_X),
				new DirPos(pos.getX() - 2, pos.getY(), pos.getZ() - 1, Library.NEG_X),
				new DirPos(pos.getX() - 2, pos.getY(), pos.getZ() + 1, Library.NEG_X),
				new DirPos(pos.getX() - 1, pos.getY(), pos.getZ() + 2, Library.POS_Z),
				new DirPos(pos.getX() + 1, pos.getY(), pos.getZ() + 2, Library.POS_Z),
				new DirPos(pos.getX() - 1, pos.getY(), pos.getZ() - 2, Library.NEG_Z),
				new DirPos(pos.getX() + 1, pos.getY(), pos.getZ() - 2, Library.NEG_Z)
		};
	}
	
	@Override
	public void handleButtonPacket(int value, int meta) {
		mode = (short) ((mode + 1) % modes);
		if (!world.isRemote) {
			broadcastControlEvt();
		}
		markDirty();
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	@Override
	public void setFillForSync(int fill, int index) {
		tank.setFill(fill);
	}

	@Override
	public void setTypeForSync(FluidType type, int index) {
		tank.setTankType(type);
	}

	@Override
	public int getMaxFluidFill(FluidType type) {

		if(mode == 2 || mode == 3 || this.sendingBrake)
			return 0;

		return type.getName().equals(this.tank.getTankType().getName()) ? tank.getMaxFill() : 0;
	}

	@Override
	public void fillFluidInit(FluidType type) {
		fillFluid(this.pos.getX() + 2, this.pos.getY(), this.pos.getZ() - 1, getTact(), type);
		fillFluid(this.pos.getX() + 2, this.pos.getY(), this.pos.getZ() + 1, getTact(), type);
		fillFluid(this.pos.getX() - 2, this.pos.getY(), this.pos.getZ() - 1, getTact(), type);
		fillFluid(this.pos.getX() - 2, this.pos.getY(), this.pos.getZ() + 1, getTact(), type);
		fillFluid(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ() + 2, getTact(), type);
		fillFluid(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ() + 2, getTact(), type);
		fillFluid(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ() - 2, getTact(), type);
		fillFluid(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ() - 2, getTact(), type);
	}

	@Override
	public void fillFluid(int x, int y, int z, boolean newTact, FluidType type) {
		Library.transmitFluid(x, y, z, newTact, this, world, type);
	}

	@Override
	public boolean getTact() {
		if (age >= 0 && age < 10) {
			return true;
		}

		return false;
	}

	@Override
	public int getFluidFill(FluidType type) {
		return type.getName().equals(this.tank.getTankType().getName()) ? tank.getFill() : 0;
	}

	@Override
	public void setFluidFill(int i, FluidType type) {
		if(type.getName().equals(tank.getTankType().getName()))
			tank.setFill(i);
	}

	@Override
	public List<IFluidAcceptor> getFluidList(FluidType type) {
		return this.list;
	}

	@Override
	public void clearFluidList(FluidType type) {
		this.list.clear();
	}

	@Override
	public long transferFluid(FluidType type, int pressure, long fluid) {
		long toTransfer = Math.min(getDemand(type, pressure), fluid);
		tank.setFill(tank.getFill() + (int) toTransfer);
		return fluid - toTransfer;
	}

	@Override
	public long getDemand(FluidType type, int pressure) {

		if(this.mode == 2 || this.mode == 3 || this.sendingBrake)
			return 0;

		if(tank.getPressure() != pressure) return 0;

		return type == tank.getTankType() ? tank.getMaxFill() - tank.getFill() : 0;
	}

	@Override
	public FluidTank[] getAllTanks() {
		return new FluidTank[] { tank };
	}

	@Override
	public FluidTank[] getSendingTanks() {
		return (mode == 1 || mode == 2) ? new FluidTank[] {tank} : new FluidTank[0];
	}

	@Override
	public FluidTank[] getReceivingTanks() {
		if(this.sendingBrake) return new FluidTank[0];
		return (mode == 0 || mode == 1) ? new FluidTank[] {tank} : new FluidTank[0];
	}

	// control panel

	@Override
	public Map<String, DataValue> getQueryData() {
		Map<String, DataValue> data = new HashMap<>();

		if (tank.getTankType() != Fluids.NONE) {
			data.put("t0_fluidType", new DataValueString(tank.getTankType().getLocalizedName()));
		}
		data.put("t0_fluidAmount", new DataValueFloat(tank.getFill()));
		data.put("mode", new DataValueFloat(mode));

		return data;
	}

	@Override
	public void receiveEvent(BlockPos from, ControlEvent e) {
		if (e.name.equals("tank_set_mode")) {
			mode = (short) (e.vars.get("mode").getNumber() % modes);
			broadcastControlEvt();
		}
	}

	public void broadcastControlEvt() {
		ControlEventSystem.get(world).broadcastToSubscribed(this, ControlEvent.newEvent("tank_set_mode").setVar("mode", new DataValueFloat(mode)));
	}

	@Override
	public List<String> getInEvents() {
		return Collections.singletonList("tank_set_mode");
	}

	@Override
	public List<String> getOutEvents() {
		return Collections.singletonList("tank_set_mode");
	}

	@Override
	public void validate() {
		super.validate();
		ControlEventSystem.get(world).addControllable(this);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		ControlEventSystem.get(world).removeControllable(this);
	}

	@Override
	public BlockPos getControlPos() {
		return getPos();
	}

	@Override
	public World getControlWorld() {
		return getWorld();
	}

}
