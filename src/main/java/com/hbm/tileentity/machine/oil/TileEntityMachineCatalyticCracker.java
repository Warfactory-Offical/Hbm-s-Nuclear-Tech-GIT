package com.hbm.tileentity.machine.oil;

import api.hbm.fluid.IFluidStandardTransceiver;
import com.hbm.blocks.BlockDummyable;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.CrackRecipes;
import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTank;
import com.hbm.lib.DirPos;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.INBTPacketReceiver;

import com.hbm.tileentity.TileEntityLoadedBase;
import com.hbm.util.Tuple;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMachineCatalyticCracker extends TileEntityLoadedBase implements INBTPacketReceiver, ITickable, IFluidStandardTransceiver {
	
	public FluidTank[] tanks;
	
	public TileEntityMachineCatalyticCracker() {
		super();
		tanks = new FluidTank[5];

		tanks[0] = new FluidTank(Fluids.BITUMEN, 4000);

		tanks[1] = new FluidTank(Fluids.STEAM, 8000);

		tanks[2] = new FluidTank(Fluids.OIL, 4000);

		tanks[3] = new FluidTank(Fluids.PETROLEUM, 4000);

		tanks[4] = new FluidTank(Fluids.SPENTSTEAM, 4000);
	}

	@Override
	public void update() {

		if(!world.isRemote) {

			this.world.profiler.startSection("catalyticCracker_setup_tanks");
			setupTanks();
			this.world.profiler.endStartSection("catalyticCracker_update_connections");
			updateConnections();

			this.world.profiler.endStartSection("catalyticCracker_do_recipe");
			if(world.getTotalWorldTime() % 5 == 0)
				crack();

			this.world.profiler.endStartSection("catalyticCracker_send_fluid");
			if(world.getTotalWorldTime() % 10 == 0) {

				for(DirPos pos : getConPos()) {
					for(int i = 2; i <= 4; i++) {
						if(tanks[i].getFill() > 0) this.sendFluid(tanks[i], world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
					}
				}

				NBTTagCompound data = new NBTTagCompound();

				for(int i = 0; i < 5; i++)
					tanks[i].writeToNBT(data, "tank" + i);

				INBTPacketReceiver.networkPack(this, data, 50);
			}
			this.world.profiler.endSection();
		}
	}

	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		for(int i = 0; i < 5; i++)
			tanks[i].readFromNBT(nbt, "tank" + i);
	}

	private void updateConnections() {

		for(DirPos pos : getConPos()) {
			this.trySubscribe(tanks[0].getTankType(), world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
			this.trySubscribe(tanks[1].getTankType(), world, pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), pos.getDir());
		}
	}

	protected DirPos[] getConPos() {

		ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset);
		ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

		return new DirPos[] {
				new DirPos(pos.getX() + dir.offsetX * 4 + rot.offsetX * 1, pos.getY(), pos.getZ() + dir.offsetZ * 4 + rot.offsetZ * 1, dir),
				new DirPos(pos.getX() + dir.offsetX * 4 - rot.offsetX * 2, pos.getY(), pos.getZ() + dir.offsetZ * 4 - rot.offsetZ * 2, dir),
				new DirPos(pos.getX() - dir.offsetX * 4 + rot.offsetX * 1, pos.getY(), pos.getZ() - dir.offsetZ * 4 + rot.offsetZ * 1, dir.getOpposite()),
				new DirPos(pos.getX() - dir.offsetX * 4 - rot.offsetX * 2, pos.getY(), pos.getZ() - dir.offsetZ * 4 - rot.offsetZ * 2, dir.getOpposite()),
				new DirPos(pos.getX() + dir.offsetX * 2 + rot.offsetX * 3, pos.getY(), pos.getZ() + dir.offsetZ * 2 + rot.offsetZ * 3, rot),
				new DirPos(pos.getX() + dir.offsetX * 2 - rot.offsetX * 4, pos.getY(), pos.getZ() + dir.offsetZ * 2 - rot.offsetZ * 4, rot),
				new DirPos(pos.getX() - dir.offsetX * 2 + rot.offsetX * 3, pos.getY(), pos.getZ() - dir.offsetZ * 2 + rot.offsetZ * 3, rot.getOpposite()),
				new DirPos(pos.getX() - dir.offsetX * 2 - rot.offsetX * 4, pos.getY(), pos.getZ() - dir.offsetZ * 2 - rot.offsetZ * 4, rot.getOpposite())
		};
	}

	private void setupTanks() {

		Tuple.Pair<FluidStack, FluidStack> quart = CrackRecipes.getCracking(tanks[0].getTankType());

		if(quart != null) {
			tanks[1].setTankType(Fluids.STEAM);
			tanks[2].setTankType(quart.getKey().type);
			tanks[3].setTankType(quart.getValue().type);
			tanks[4].setTankType(Fluids.SPENTSTEAM);
		} else {
			tanks[0].setTankType(Fluids.NONE);
			tanks[1].setTankType(Fluids.NONE);
			tanks[2].setTankType(Fluids.NONE);
			tanks[3].setTankType(Fluids.NONE);
			tanks[4].setTankType(Fluids.NONE);
		}
	}

	private void crack() {

		Tuple.Pair<FluidStack, FluidStack> quart = CrackRecipes.getCracking(tanks[0].getTankType());

		if(quart != null) {

			int left = quart.getKey().fill;
			int right = quart.getValue().fill;

			for(int i = 0; i < 2; i++) {
				if(tanks[0].getFill() >= 100 && tanks[1].getFill() >= 200 && hasSpace(left, right)) {
					tanks[0].setFill(tanks[0].getFill() - 100);
					tanks[1].setFill(tanks[1].getFill() - 200);
					tanks[2].setFill(tanks[2].getFill() + left);
					tanks[3].setFill(tanks[3].getFill() + right);
					tanks[4].setFill(tanks[4].getFill() + 2); //LPS has the density of WATER not STEAM (1%!)
				}
			}
		}
	}

	private boolean hasSpace(int left, int right) {
		return tanks[2].getFill() + left <= tanks[2].getMaxFill() && tanks[3].getFill() + right <= tanks[3].getMaxFill() && tanks[4].getFill() + 2 <= tanks[4].getMaxFill();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		for(int i = 0; i < tanks.length; i++)
			tanks[i].readFromNBT(nbt, "tank" + i);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		for(int i = 0; i < tanks.length; i++)
			tanks[i].writeToNBT(nbt, "tank" + i);
		return nbt;
	}

	AxisAlignedBB bb = null;
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		
		if(bb == null) {
			bb = new AxisAlignedBB(
				pos.getX() - 3,
				pos.getY(),
				pos.getZ() - 3,
				pos.getX() + 4,
				pos.getY() + 16,
				pos.getZ() + 4
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
	public FluidTank[] getSendingTanks() {
		return new FluidTank[] {tanks[2], tanks[3], tanks[4]};
	}

	@Override
	public FluidTank[] getReceivingTanks() {
		return new FluidTank[] {tanks[0], tanks[1]};
	}

	@Override
	public FluidTank[] getAllTanks() {
		return tanks;
	}
}
