package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.lib.Library;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityMachineBattery;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class MachineBattery extends BlockContainer implements ILookOverlay {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private final long maxPower;

	public MachineBattery(final Material materialIn, final long power, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.machineTab);
		this.maxPower = power;

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityMachineBattery();
	}

	public long getMaxPower() {
		return maxPower;
	}

	@Override
	public void onBlockAdded(final World worldIn, final BlockPos pos, final IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(final World worldIn, final BlockPos pos, final IBlockState state) {
		if (!worldIn.isRemote) {
			final IBlockState iblockstate = worldIn.getBlockState(pos.north());
			final IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			final IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			final IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return null;
	}

	@Override
	public boolean removedByPlayer(final IBlockState state, final World world, final BlockPos pos, final EntityPlayer player, final boolean willHarvest){
		if(!player.capabilities.isCreativeMode && !world.isRemote && willHarvest) {
			
			final ItemStack drop = ItemStackUtil.itemStackFrom(this);
			final TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityMachineBattery battery) {

                final NBTTagCompound nbt = new NBTTagCompound();
				battery.writeNBT(nbt);

				if(!nbt.isEmpty()) {
					drop.setTagCompound(nbt);
				}
			}

			InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop);
		}
		return world.setBlockToAir(pos);
	}

	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		final TileEntity te = worldIn.getTileEntity(pos);
		if(stack.hasTagCompound()){
			if (te instanceof TileEntityMachineBattery battery) {
                if(stack.hasDisplayName()) {
					battery.setCustomName(stack.getDisplayName());
				}
				try {
					final NBTTagCompound stackNBT = stack.getTagCompound();
					if(stackNBT.hasKey("NBT_PERSISTENT_KEY")){
						battery.readNBT(stackNBT);
					}
				} catch(final Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final TileEntityMachineBattery entity = (TileEntityMachineBattery) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_machine_battery, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		final TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TileEntityMachineBattery) {
			InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean hasComparatorInputOverride(final IBlockState state){
		return true;
	}

	@Override
	public int getComparatorInputOverride(final IBlockState blockState, final World worldIn, final BlockPos pos){
		
		final TileEntity te = worldIn.getTileEntity(pos);
		
		if(!(te instanceof TileEntityMachineBattery battery))
			return 0;

        return (int)battery.getPowerRemainingScaled(15L);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, list, flagIn);
		long charge = 0L;
		if(stack.hasTagCompound()){
			final NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey("NBT_PERSISTENT_KEY")){
				charge = nbt.getCompoundTag("NBT_PERSISTENT_KEY").getLong("power");
			}
		}

		if(charge == 0L){
			list.add("§c0§4/" + Library.getShortNumber(this.maxPower) + "HE §c(0.0%)§r");
		}else {
			final double percent = Math.round(charge*1000L/this.maxPower)*0.1D;
			String color = "§e";
			String color2 = "§6"; 
			if(percent < 25){
				color = "§c";
				color2 = "§4";
			}else if(percent >= 75){
				color = "§a";
				color2 = "§2";
			}
			list.add(color+Library.getShortNumber(charge)+color2+"/"+Library.getShortNumber(this.maxPower)+"HE "+color+"("+percent+"%)§r");
		}
	}

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
			
		final TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(te instanceof TileEntityMachineBattery battery))
			return;

        final List<String> text = new ArrayList();
		text.add(Library.getShortNumber(battery.power) + "/" + Library.getShortNumber(getMaxPower()) + " HE");
		if(battery.powerDelta == 0){
			text.add("§e-- §r0HE/s");
		}
		else if(battery.powerDelta > 0){
			text.add("§a-> §r" + Library.getShortNumber(battery.powerDelta) + "HE/s");
		}
		else{
			text.add("§c<- §r" + Library.getShortNumber(-battery.powerDelta) + "HE/s");
		}
		text.add("&["+Library.getColorProgress((double)battery.power/(double)getMaxPower())+"&]    "+Library.getPercentage((double)battery.power/(double)getMaxPower())+"%");
		ILookOverlay.printGeneric(event, getLocalizedName(), 0xffff00, 0x404000, text);
	}
}
