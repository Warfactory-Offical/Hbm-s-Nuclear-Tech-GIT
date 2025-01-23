package com.hbm.items.tool;

import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.util.ItemStackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public class ItemMultitoolTool extends ItemTool {

	public ItemMultitoolTool(final float f, final ToolMaterial materialIn, final Set<Block> effectiveBlocksIn, final String s) {
		super(f, 0, materialIn, effectiveBlocksIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public float getDestroySpeed(final ItemStack stack, final IBlockState state) {
		return this.efficiency;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if(player.isSneaking()) {
			
	        world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.techBoop, SoundCategory.PLAYERS, 2.0F, 1.0F);
	        
			if(this == ModItems.multitool_dig) {
				final ItemStack item = ItemStackUtil.itemStackFrom(ModItems.multitool_silk, 1, stack.getItemDamage());
				item.addEnchantment(Enchantments.SILK_TOUCH, 3);
				return ActionResult.newResult(EnumActionResult.SUCCESS, item);
			} else if (this == ModItems.multitool_silk) {
				final ItemStack item = ItemStackUtil.itemStackFrom(ModItems.multitool_ext, 1, stack.getItemDamage());
				item.addEnchantment(Enchantments.FIRE_ASPECT, 3);
				return ActionResult.newResult(EnumActionResult.SUCCESS, item);
			}
		}
		
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}
	
	@Override
	public boolean canHarvestBlock(final IBlockState blockIn) {
		return true;
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(this == ModItems.multitool_dig) {
			tooltip.add("Breaks blocks extremely fast");
			tooltip.add("Extra drops for ores");
		}
		if(this == ModItems.multitool_silk) {
			tooltip.add("Breaks blocks extremely fast");
			tooltip.add("Ores will drop themselves via silk touch");
		}
	}
	
}
