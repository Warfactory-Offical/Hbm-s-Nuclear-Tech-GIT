package com.hbm.tileentity.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.UpgradeManager;
import com.hbm.inventory.container.ContainerChemfac;
import com.hbm.inventory.gui.GUIChemfac;
import com.hbm.items.machine.ItemMachineUpgrade;
import com.hbm.items.machine.ItemMachineUpgrade.UpgradeType;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.packet.LoopedSoundPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.IGUIProvider;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileEntityMachineChemfac extends TileEntityMachineChemplantBase implements IGUIProvider {
	float rotSpeed;
	public float rot;
	public float prevRot;

	public TypedFluidTank water;
	public TypedFluidTank steam;

	private final UpgradeManager upgradeManager;

	public TileEntityMachineChemfac() {
		super(77);

		water = new TypedFluidTank(ModForgeFluids.coolant, new FluidTank(6400));
		steam = new TypedFluidTank(ModForgeFluids.hotcoolant, new FluidTank(6400));

		inventory = new ItemStackHandler(77) {
			@Override
			protected void onContentsChanged(final int slot) {
				super.onContentsChanged(slot);
				markDirty();
			}

			@Override
			public void setStackInSlot(final int slot, @Nonnull final ItemStack stack) {
				super.setStackInSlot(slot, stack);
				if(!stack.isEmpty() && slot >= 1 && slot <= 4 && stack.getItem() instanceof ItemMachineUpgrade) {
					world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, HBMSoundHandler.upgradePlug, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}
			}
		};
		upgradeManager = new UpgradeManager();
	}

	@Override
	public void update() {
		super.update();

		if(!world.isRemote) {
			if(world.getTotalWorldTime() % 60 == 0) {
				this.updateConnections();
			}

			this.sendFluids();

			this.speed = 100;
			this.consumption = 100;

			upgradeManager.eval(inventory, 1, 4);
			final int speedLevel = Math.min(upgradeManager.getLevel(UpgradeType.SPEED), 6);
			final int powerLevel = Math.min(upgradeManager.getLevel(UpgradeType.POWER), 3);
			final int overLevel = upgradeManager.getLevel(UpgradeType.OVERDRIVE);

			this.speed -= speedLevel * 15;
			this.consumption += speedLevel * 300;
			this.speed += powerLevel * 5;
			this.consumption -= powerLevel * 30;
			this.speed /= (overLevel + 1);
			this.consumption *= (overLevel + 1);

			if(this.speed <= 0) {
				this.speed = 1;
			}

			final NBTTagCompound data = new NBTTagCompound();
			data.setLong("power", this.power);
			data.setIntArray("progress", this.progress);
			data.setIntArray("maxProgress", this.maxProgress);
			data.setBoolean("isProgressing", isProgressing);
			data.setTag("tanks", serializeTanks());

			final NBTTagCompound tankWater = new NBTTagCompound();
			water.writeToNBT(tankWater);
			data.setTag("water", tankWater);

			final NBTTagCompound tankSteam = new NBTTagCompound();
			steam.writeToNBT(tankSteam);
			data.setTag("steam", tankSteam);

			PacketDispatcher.wrapper.sendToAll(new LoopedSoundPacket(pos.getX(), pos.getY(), pos.getZ()));
			this.networkPack(data, 150);
		} else {
			final float maxSpeed = 30F;

			if(isProgressing) {
				rotSpeed += 0.1;

				if(rotSpeed > maxSpeed) {
					rotSpeed = maxSpeed;
				}

				if(rotSpeed == maxSpeed && world.getTotalWorldTime() % 5 == 0) {
					final ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset).getOpposite();
					final ForgeDirection rot = dir.getRotation(ForgeDirection.UP);
					final Random rand = world.rand;

					final double x = pos.getX() + 0.5 - rot.offsetX * 0.5;
					final double y = pos.getY() + 3;
					final double z = pos.getZ() + 0.5 - rot.offsetZ * 0.5;

					world.spawnParticle(EnumParticleTypes.CLOUD, x + dir.offsetX * 1.5 + rand.nextGaussian() * 0.2, y, z + dir.offsetZ * 1.5 + rand.nextGaussian() * 0.2, 0.0D, 0.15D, 0.0D);
					world.spawnParticle(EnumParticleTypes.CLOUD, x + dir.offsetX * -0.5 + rand.nextGaussian() * 0.2, y, z + dir.offsetZ * -0.5 + rand.nextGaussian() * 0.2, 0.0D, 0.15D, 0.0D);
				}
			} else {
				rotSpeed -= 0.1;

				if(rotSpeed < 0) {
					rotSpeed = 0;
				}
			}

			prevRot = rot;
			rot += rotSpeed;

			if(rot >= 360) {
				rot -= 360;
				prevRot -= 360;
			}
		}
	}

	@Override
	public void networkUnpack(final NBTTagCompound nbt) {
		this.power = nbt.getLong("power");
		this.progress = nbt.getIntArray("progress");
		this.maxProgress = nbt.getIntArray("maxProgress");
		this.isProgressing = nbt.getBoolean("isProgressing");
		this.deserializeTanks(nbt.getTagList("tanks", 10));

		water.readFromNBT(nbt.getCompoundTag("water"));
		steam.readFromNBT(nbt.getCompoundTag("steam"));
	}

	private int getWaterRequired() {
		return 100 / this.speed;
	}


	@Override
	protected boolean canProcess(final int index) {
		return super.canProcess(index) && this.water.tank.getFluidAmount() >= getWaterRequired() && this.steam.tank.getFluidAmount() + getWaterRequired() <= this.steam.tank.getCapacity();
	}

	@Override
	protected void process(final int index) {
		super.process(index);
		this.water.tank.drain(getWaterRequired(), true);
		this.steam.tank.fill(new FluidStack(ModForgeFluids.hotcoolant, getWaterRequired()), true);
	}


	private void updateConnections() {
		for (final Pair<BlockPos, ForgeDirection> pos : getConPos()) {
			this.trySubscribe(world, pos.getLeft(), pos.getRight());
		}
	}

	private void sendFluids() {
		for (final Pair<BlockPos, ForgeDirection> pos : getConPos()) {
			for (final TypedFluidTank tank : outTanks()) {
				if(tank.type != null && tank.tank.getFluidAmount() > 0) {
					FFUtils.fillFluid(this, tank.tank, world, pos.getLeft(), tank.tank.getFluidAmount());
				}
			}
		}
	}

	List<Pair<BlockPos, ForgeDirection>> conPos;

	protected List<Pair<BlockPos, ForgeDirection>> getConPos() {
		if(conPos != null && !conPos.isEmpty())
			return conPos;

		conPos = new ArrayList<>();

		final ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset).getOpposite();
		final ForgeDirection rot = dir.getRotation(ForgeDirection.DOWN);

		for (int i = 0; i < 6; i++) {
			conPos.add(Pair.of(pos.add(dir.offsetX * (3 - i) + rot.offsetX * 3, 4, dir.offsetZ * (3 - i) + rot.offsetZ * 3), Library.POS_Y));
			conPos.add(Pair.of(pos.add(dir.offsetX * (3 - i) - rot.offsetX * 2, 4, dir.offsetZ * (3 - i) - rot.offsetZ * 2), Library.POS_Y));

			for (int j = 0; j < 2; j++) {
				conPos.add(Pair.of(pos.add(dir.offsetX * (3 - i) + rot.offsetX * 5, 1 + j, dir.offsetZ * (3 - i) + rot.offsetZ * 5), rot));
				conPos.add(Pair.of(pos.add(dir.offsetX * (3 - i) - rot.offsetX * 4, 1 + j, dir.offsetZ * (3 - i) - rot.offsetZ * 4), rot.getOpposite()));
			}
		}

		return conPos;
	}

	@Override
	public long getMaxPower() {
		return 10_000_000;
	}

	@Override
	public String getName() {
		return "container.machineChemFac";
	}

	@Override
	public int getRecipeCount() {
		return 8;
	}

	@Override
	public int getTankCapacity() {
		return 32_000;
	}

	@Override
	public int getTemplateIndex(final int index) {
		return 13 + index * 9;
	}

	@Override
	public int[] getSlotIndicesFromIndex(final int index) {
		return new int[]{5 + index * 9, 8 + index * 9, 9 + index * 9, 12 + index * 9};
	}

	BlockPos[] inPos;
	BlockPos[] outPos;

	@Override
	public BlockPos[] getInputPositions() {
		if(inPos != null) {
			return inPos;
		}

		final ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset);
		final ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

		inPos = new BlockPos[]{
				pos.add(dir.offsetX * 4 - rot.offsetX, 0, dir.offsetZ * 4 - rot.offsetZ),
				pos.add(-dir.offsetX * 5 + rot.offsetX * 2, 0, -dir.offsetZ * 5 + rot.offsetZ * 2),
				pos.add(-dir.offsetX * 2 - rot.offsetX * 4, 0, -dir.offsetZ * 2 - rot.offsetZ * 4),
				pos.add(dir.offsetX + rot.offsetX * 5, 0, dir.offsetZ + rot.offsetZ * 5)
		};

		return inPos;
	}

	@Override
	public BlockPos[] getOutputPositions() {
		if(outPos != null)
			return outPos;

		final ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset);
		final ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

		outPos = new BlockPos[]{
				pos.add(dir.offsetX * 4 + rot.offsetX * 2, 0, dir.offsetZ * 4 + rot.offsetZ * 2),
				pos.add(-dir.offsetX * 5 - rot.offsetX, 0, -dir.offsetZ * 5 - rot.offsetZ),
				pos.add(dir.offsetX - rot.offsetX * 4, 0, dir.offsetZ - rot.offsetZ * 4),
				pos.add(-dir.offsetX * 2 + rot.offsetX * 5, 0, -dir.offsetZ * 2 + rot.offsetZ * 5)
		};

		return outPos;
	}

	IFluidTankProperties[] properties;

	@Override
	public IFluidTankProperties[] getTankProperties() {
		if(properties == null) {
			properties = new IFluidTankProperties[tanks.length + 2];
			for (int i = 0; i < tanks.length; i++) {
				properties[i] = tanks[i].tank.getTankProperties()[0];
			}

			properties[tanks.length] = water.tank.getTankProperties()[0];
			properties[tanks.length + 1] = steam.tank.getTankProperties()[0];
		}

		return properties;
	}

	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
		final NBTTagCompound tankWater = new NBTTagCompound();
		water.tank.writeToNBT(tankWater);
		nbt.setTag("water", tankWater);

		final NBTTagCompound tankSteam = new NBTTagCompound();
		steam.tank.writeToNBT(tankSteam);
		nbt.setTag("steam", tankSteam);

		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(final NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		water.tank.readFromNBT(nbt.getCompoundTag("water"));
		steam.tank.readFromNBT(nbt.getCompoundTag("steam"));
	}

	@Override
	protected List<TypedFluidTank> inTanks() {
		final List<TypedFluidTank> inTanks = super.inTanks();
		inTanks.add(water);

		return inTanks;
	}

	@Override
	public List<TypedFluidTank> outTanks() {
		final List<TypedFluidTank> outTanks = super.outTanks();
		outTanks.add(steam);

		return outTanks;
	}

	AxisAlignedBB bb = null;

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		if(bb == null) {
			bb = new AxisAlignedBB(
					pos.getX() - 5,
					pos.getY(),
					pos.getZ() - 5,
					pos.getX() + 5,
					pos.getY() + 4,
					pos.getZ() + 5
			);
		}

		return bb;
	}

	@Override
	public Container provideContainer(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		return new ContainerChemfac(player.inventory, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiScreen provideGUI(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
		return new GUIChemfac(player.inventory, this);
	}

	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}

		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}

		return super.hasCapability(capability, facing);
	}
}
