package com.hbm.blocks.bomb;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.tileentity.bomb.TileEntityFireworks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockFireworks extends BlockContainer {

	public BlockFireworks(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityFireworks();
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
			return true;

		final TileEntityFireworks te = (TileEntityFireworks)world.getTileEntity(pos);

		if(player.getHeldItem(hand) != null && !player.getHeldItem(hand).isEmpty()) {

			if(player.getHeldItem(hand).getItem() == Items.GUNPOWDER) {
				te.charges += player.getHeldItem(hand).getCount() * 3;
				te.markDirty();
				player.getHeldItem(hand).setCount(0);
				return true;
			}

			if(player.getHeldItem(hand).getItem() == ModItems.sulfur) {
				te.charges += player.getHeldItem(hand).getCount();
				te.markDirty();
				player.getHeldItem(hand).setCount(0);
				return true;
			}

			if(player.getHeldItem(hand).getItem() instanceof ItemDye) {
				te.color = ItemDye.DYE_COLORS[player.getHeldItem(hand).getItemDamage()];
				te.markDirty();
				player.getHeldItem(hand).shrink(1);
				return true;
			}

			if(player.getHeldItem(hand).getItem() == Items.NAME_TAG) {
				te.message = player.getHeldItem(hand).getDisplayName();
				te.markDirty();
				player.getHeldItem(hand).shrink(1);
				return true;
			}
		}

		player.sendMessage(new TextComponentTranslation(this.getTranslationKey() + ".name").setStyle(new Style().setColor(TextFormatting.GOLD)));
		player.sendMessage(new TextComponentTranslation(this.getTranslationKey() + ".charges", te.charges).setStyle(new Style().setColor(TextFormatting.YELLOW)));
		player.sendMessage(new TextComponentTranslation(this.getTranslationKey() + ".color", Integer.toHexString(te.color)).setStyle(new Style().setColor(TextFormatting.YELLOW)));
		player.sendMessage(new TextComponentTranslation(this.getTranslationKey() + ".message", te.message).setStyle(new Style().setColor(TextFormatting.YELLOW)));

		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		tooltip.add(I18nUtil.resolveKey("desc.fireworks.1"));
		tooltip.add(I18nUtil.resolveKey("desc.fireworks.2"));
		tooltip.add(" "+I18nUtil.resolveKey("desc.fireworks.3"));
		tooltip.add(" "+I18nUtil.resolveKey("desc.fireworks.4"));
		tooltip.add(" "+I18nUtil.resolveKey("desc.fireworks.5"));

	}
}
