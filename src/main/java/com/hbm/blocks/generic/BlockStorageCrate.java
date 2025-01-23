package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.lib.InventoryHelper;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.config.MachineConfig;
import com.hbm.tileentity.machine.TileEntityLockableBase;
import com.hbm.tileentity.machine.TileEntityCrateIron;
import com.hbm.tileentity.machine.TileEntityCrateSteel;
import com.hbm.tileentity.machine.TileEntityCrateTungsten;
import com.hbm.tileentity.machine.TileEntityCrateDesh;
import com.hbm.tileentity.machine.TileEntitySafe;

import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStorageCrate extends BlockContainer {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private static boolean dropInv = true;

	public BlockStorageCrate(final Material materialIn, final String s){
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setSoundType(SoundType.METAL);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta){
		if(this == ModBlocks.crate_iron)
			return new TileEntityCrateIron();
		if(this == ModBlocks.crate_steel)
			return new TileEntityCrateSteel();
		if(this == ModBlocks.crate_tungsten)
			return new TileEntityCrateTungsten();
		if(this == ModBlocks.crate_desh)
			return new TileEntityCrateDesh();
		if(this == ModBlocks.safe)
			return new TileEntitySafe();
		return null;
	}

	public int getSlots(){
		if(this == ModBlocks.crate_iron)
			return 36;
		if(this == ModBlocks.crate_steel)
			return 54;
		if(this == ModBlocks.crate_tungsten)
			return 27;
		if(this == ModBlocks.crate_desh)
			return 104;
		if(this == ModBlocks.safe)
			return 15;
		return 0;
	}


	@Override
	public boolean canHarvestBlock(final IBlockAccess world, final BlockPos pos, final EntityPlayer player){
		return true;
	}

	@Override
	public boolean removedByPlayer(final IBlockState state, final World world, final BlockPos pos, final EntityPlayer player, final boolean willHarvest){

		if(!player.capabilities.isCreativeMode && !world.isRemote && willHarvest) {
			
			final ItemStack drop = ItemStackUtil.itemStackFrom(this);
			final TileEntity te = world.getTileEntity(pos);
			
			final NBTTagCompound nbt = new NBTTagCompound();
			
			if(te != null) {
				final IItemHandler inventory;
				if(te instanceof TileEntitySafe){

					inventory = ((TileEntitySafe)te).getPackingCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
				}
				else{
					inventory = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
				}

				for(int i = 0; i < inventory.getSlots(); i++) {
					
					final ItemStack stack = inventory.getStackInSlot(i);
					if(stack.isEmpty())
						continue;
					
					final NBTTagCompound slot = new NBTTagCompound();
					stack.writeToNBT(slot);
					nbt.setTag("slot" + i, slot);
				}
			}
			
			if(te instanceof TileEntityLockableBase lockable) {

                if(lockable.isLocked()) {
					nbt.setInteger("lock", lockable.getPins());
					nbt.setDouble("lockMod", lockable.getMod());
				}
			}
			
			
			if(!nbt.isEmpty()) {
				drop.setTagCompound(nbt);
								
				if(nbt.toString().length() > MachineConfig.crateByteSize * 1000) {
					player.sendMessage(new TextComponentString("§cWarning: Container NBT exceeds "+MachineConfig.crateByteSize+"kB, contents will be ejected!"));
					InventoryHelper.dropInventoryItems(world, pos, world.getTileEntity(pos));
					InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ItemStackUtil.itemStackFrom(Item.getItemFromBlock(this)));
					return world.setBlockToAir(pos);
				}
			}
			
			InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop);
		}

		dropInv = false;
		final boolean flag = world.setBlockToAir(pos);
		dropInv = true;
		
		return flag;
	}

	@Override
	public Block setSoundType(final SoundType sound){
		return super.setSoundType(sound);
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state){
		if(dropInv){
			InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(world.isRemote) {
			return true;
		} else if(player.getHeldItemMainhand() != null && (player.getHeldItemMainhand().getItem() instanceof ItemLock || player.getHeldItemMainhand().getItem() == ModItems.key_kit)) {
			return false;

		} else if(!player.isSneaking()) {
			final TileEntity entity = world.getTileEntity(pos);
			final int x = pos.getX();
			final int y = pos.getY();
			final int z = pos.getZ();
			if(entity instanceof TileEntityCrateIron && ((TileEntityCrateIron)entity).canAccess(player)) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_crate_iron, world, x, y, z);
			}
			if(entity instanceof TileEntityCrateSteel && ((TileEntityCrateSteel)entity).canAccess(player)) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_crate_steel, world, x, y, z);
			}
			if(entity instanceof TileEntityCrateTungsten && ((TileEntityCrateTungsten)entity).canAccess(player)) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_crate_tungsten, world, x, y, z);
			}
			if(entity instanceof TileEntityCrateDesh && ((TileEntityCrateDesh)entity).canAccess(player)) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_crate_desh, world, x, y, z);
			}
			if(entity instanceof TileEntitySafe && ((TileEntitySafe)entity).canAccess(player)) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_safe, world, x, y, z);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack){

		final TileEntity te = world.getTileEntity(pos);

		if(te != null && stack.hasTagCompound()) {
			final IItemHandler inventory = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			final NBTTagCompound nbt = stack.getTagCompound();
			for(int i = 0; i < inventory.getSlots(); i++) {
				inventory.insertItem(i, ItemStackUtil.itemStackFrom(nbt.getCompoundTag("slot" + i)), false);
			}
			
			if(te instanceof TileEntityLockableBase lockable) {

                if(nbt.hasKey("lock")) {
					lockable.setPins(nbt.getInteger("lock"));
					lockable.setMod(nbt.getDouble("lockMod"));
					lockable.lock();
				}
			}
		}

		if(this != ModBlocks.safe)
			super.onBlockPlacedBy(world, pos, state, placer, stack);
		else
			world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand){
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return null;
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int getMetaFromState(final IBlockState state){
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(final int meta){
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

		if(enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot){
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn){
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state){
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, list, flagIn);
		final int totalSlots = getSlots();
		if(stack.hasTagCompound()){
			final NBTTagCompound nbt = stack.getTagCompound();
			int slotCount = 0;
			for(int i=0; i<totalSlots; i++){
				if(nbt.hasKey("slot"+i)){
					slotCount++;
				}
			}
			final float percent = Library.roundFloat(slotCount * 100F/totalSlots, 1);
			String color = "§e";
			String color2 = "§6"; 
			if(percent >= 75){
				color = "§c";
				color2 = "§4";
			}else if(percent < 25){
				color = "§a";
				color2 = "§2";
			}
			list.add(color+slotCount+color2+"/"+totalSlots+" Slots used "+color+"("+percent+"%)§r");

		}else{
			list.add("§a0§2/" + totalSlots + " Slots used §a(0.0%)§r");
		}
	}
}
