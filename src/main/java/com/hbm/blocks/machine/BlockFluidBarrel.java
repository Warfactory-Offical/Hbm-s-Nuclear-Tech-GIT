package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.YellowBarrel;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityBarrel;

import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFluidBarrel extends BlockContainer {

	private final int capacity;
	public static boolean keepInventory;
	
	public BlockFluidBarrel(final Material materialIn, final int cap, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		capacity = cap;
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityBarrel(capacity);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> list, final ITooltipFlag advanced) {
		if(this == ModBlocks.barrel_plastic) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "12,000"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannothot"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
		
		if(this == ModBlocks.barrel_corroded) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "6,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhighcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
			list.add(TextFormatting.RED + I18nUtil.resolveKey("desc.leaky"));
		}
		
		if(this == ModBlocks.barrel_iron) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "8,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotcor1"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
		
		if(this == ModBlocks.barrel_steel) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "16,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.cancor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannothighcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
		
		if(this == ModBlocks.barrel_antimatter) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "16,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhighcor"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canam"));
		}
		
		if(this == ModBlocks.barrel_tcalloy) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "24,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhighcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
	}
	
	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote) {
			return true;
			
		} else if(!player.isSneaking()) {
			player.openGui(MainRegistry.instance, ModBlocks.guiID_barrel, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
			
		} else {
			return false;
		}
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		if(!keepInventory)
			InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return YellowBarrel.BARREL_BB;
	}
}
