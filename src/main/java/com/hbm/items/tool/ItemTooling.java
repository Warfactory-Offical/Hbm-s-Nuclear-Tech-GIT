package com.hbm.items.tool;

import api.hbm.block.IToolable;
import api.hbm.block.IToolable.ToolType;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ItemTooling extends Item {

	protected ToolType type;
	
	public ItemTooling(final ToolType type, final int dura, final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setMaxStackSize(1);
		this.setFull3D();
		this.setCreativeTab(MainRegistry.consumableTab);
		this.setMaxDamage(dura);
		this.type = type;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		final Block b = world.getBlockState(pos).getBlock();
		
		if(b instanceof IToolable) {
			if(((IToolable)b).onScrew(world, player, pos.getX(), pos.getY(), pos.getZ(), facing, hitX, hitY, hitZ, hand, this.type)) {
				
				if(this.getMaxDamage(player.getHeldItem(hand)) > 0)
					player.getHeldItem(hand).damageItem(1, player);
				
				return EnumActionResult.SUCCESS;
			}
		}
		return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(type == ToolType.SCREWDRIVER){
			tooltip.add(I18nUtil.resolveKey("desc.screwdriver1"));
			tooltip.add(I18nUtil.resolveKey("desc.screwdriver2"));
		}
	}

	public ToolType getType(){
		return type;
	}
}
