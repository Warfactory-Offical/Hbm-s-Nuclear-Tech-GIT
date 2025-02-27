package com.hbm.tileentity.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.missile.EntityMinerRocket;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemSatChip;
import com.hbm.saveddata.satellites.Satellite;
import com.hbm.saveddata.satellites.SatelliteMiner;
import com.hbm.saveddata.satellites.SatelliteHorizons;
import com.hbm.saveddata.satellites.SatelliteSavedData;
import com.hbm.util.WeightedRandomObject;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityMachineSatDock extends TileEntityMachineBase implements ITickable {

	private static final int[] access = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

	public TileEntityMachineSatDock(){
		super(16);
	}

	@Override
	public String getName() {
		return "container.satDock";
	}

	public boolean isUseableByPlayer(final EntityPlayer player){
		if(world.getTileEntity(pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64;
		}
	}

	SatelliteSavedData data = null;

	@Override
	public void update(){
		if(!world.isRemote) {

			if(data == null)
				data = (SatelliteSavedData)world.getPerWorldStorage().getOrLoadData(SatelliteSavedData.class, "satellites");

			if(data == null) {
				world.getPerWorldStorage().setData("satellites", new SatelliteSavedData());
				data = (SatelliteSavedData)world.getPerWorldStorage().getOrLoadData(SatelliteSavedData.class, "satellites");
			}
			data.markDirty();

			if(data != null && !inventory.getStackInSlot(15).isEmpty()) {
				final int freq = ItemSatChip.getFreq(inventory.getStackInSlot(15));

				final Satellite sat = data.getSatFromFreq(freq);

				final int delay = 10 * 60 * 1000; //10min

				if(sat != null && sat instanceof SatelliteMiner miner) {

                    if(miner.lastOp + delay < System.currentTimeMillis()) {

						final EntityMinerRocket rocket = new EntityMinerRocket(world);
						rocket.posX = pos.getX() + 0.5;
						rocket.posY = 300;
						rocket.posZ = pos.getZ() + 0.5;
						world.spawnEntity(rocket);
						miner.lastOp = System.currentTimeMillis();
						data.markDirty();
					}
				}
				if(sat != null && sat instanceof SatelliteHorizons gerald) {

                    if(gerald.lastOp + delay < System.currentTimeMillis()) {

						final EntityMinerRocket rocket = new EntityMinerRocket(world, (byte)1);
						rocket.posX = pos.getX() + 0.5;
						rocket.posY = 300;
						rocket.posZ = pos.getZ() + 0.5;
						rocket.setRocketType((byte)1);
						world.spawnEntity(rocket);
						gerald.lastOp = System.currentTimeMillis();
						data.markDirty();
					}
				}
			}

			final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos.getX() - 0.25 + 0.5, pos.getY() + 0.75, pos.getZ() - 0.25 + 0.5, pos.getX() + 0.25 + 0.5, pos.getY() + 2, pos.getZ() + 0.25 + 0.5));

			for(final Entity e : list) {

				if(e instanceof EntityMinerRocket rocket) {

                    if(rocket.getDataManager().get(EntityMinerRocket.TIMER) == 1 && rocket.timer == 50) {
						final byte type = rocket.getRocketType();
						if(type == 0){
							unloadCargo();
						} else if(type == 1){
							unloadGeraldCargo();
						}
					}
				}
			}

			ejectInto(pos.getX() + 2, pos.getY(), pos.getZ());
			ejectInto(pos.getX() - 2, pos.getY(), pos.getZ());
			ejectInto(pos.getX(), pos.getY(), pos.getZ() + 2);
			ejectInto(pos.getX(), pos.getY(), pos.getZ() - 2);
		}
	}

	private static Random rand = new Random();

	private void unloadCargo(){
		unloadTheCargo(cargo);
	}

	private void unloadGeraldCargo(){
		unloadTheCargo(cargoGerald);
	}

	private void unloadTheCargo(final WeightedRandomObject[] cargo){

		final int items = rand.nextInt(6) + 10;

		rand = new Random();

		for(int i = 0; i < items; i++) {
			final ItemStack stack = WeightedRandom.getRandomItem(rand, Arrays.asList(cargo)).asStack();
			addToInv(stack);
		}
	}

	private final WeightedRandomObject[] cargo = new WeightedRandomObject[] {
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_aluminium, 3), 10), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_iron, 3), 10), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_titanium, 2), 8), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_coal, 4), 15), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_uranium, 2), 5), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 1), 5), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_thorium, 2), 7), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_desh_mix, 3), 5), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_diamond, 2), 7), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(Items.REDSTONE, 5), 15), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_nitan_mix, 2), 5), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_power, 2), 5),
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_copper, 5), 15), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_lead, 3), 10), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.fluorite, 4), 15), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_lapis, 4), 10), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_combine_steel, 1), 1), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.crystal_aluminium, 1), 5), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.crystal_gold, 1), 5), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.crystal_phosphorus, 1), 10), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModBlocks.gravel_diamond, 1), 3), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.crystal_uranium, 1), 3), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.crystal_plutonium, 1), 3), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.crystal_trixite, 1), 1), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.crystal_starmetal, 1), 1)
	};

	private final WeightedRandomObject[] cargoGerald = new WeightedRandomObject[] {
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_meteorite, 12), 128),
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_plutonium, 4), 64), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_combine_steel, 6), 64), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_tektite, 16), 32), 
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_tantalium, 1), 16),
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_schrabidium, 1), 8),
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_bismuth, 1), 4),
		new WeightedRandomObject(ItemStackUtil.itemStackFrom(ModItems.powder_radspice, 1), 1)
	};

	private void addToInv(final ItemStack stack){

		for(int i = 0; i < 15; i++) {

			if(!inventory.getStackInSlot(i).isEmpty() && inventory.getStackInSlot(i).getItem() == stack.getItem() && inventory.getStackInSlot(i).getItemDamage() == stack.getItemDamage() && inventory.getStackInSlot(i).getCount() < inventory.getStackInSlot(i).getMaxStackSize()) {

				inventory.getStackInSlot(i).grow(1);

				return;
			}
		}

		for(int i = 0; i < 15; i++) {

			if(inventory.getStackInSlot(i).isEmpty()) {
				inventory.setStackInSlot(i, ItemStackUtil.itemStackFrom(stack.getItem(), 1, stack.getItemDamage()));
				return;
			}
		}
	}

	private void ejectInto(final int x, final int y, final int z){
		final BlockPos eject = new BlockPos(x, y, z);
		final TileEntity te = world.getTileEntity(eject);

		if(te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
			final IItemHandler chest = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			for(int i = 0; i < 15; i++) {
				for(int j = 0; j < chest.getSlots(); j++) {
					final ItemStack stack = inventory.getStackInSlot(i);
					if(stack.isEmpty()) break;
					inventory.setStackInSlot(i, chest.insertItem(j, stack, false));
				}
			}
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(){
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared(){
		return 65536.0D;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(final EnumFacing e){
		return access;
	}
	
	@Override
	public boolean canExtractItem(final int slot, final ItemStack itemStack, final int amount){
		return slot != 15;
	}
}
