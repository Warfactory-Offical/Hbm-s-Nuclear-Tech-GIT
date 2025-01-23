package com.hbm.blocks.generic;

import java.util.List;
import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;

import api.hbm.item.IDepthRockTool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockDepth extends Block {

	public BlockDepth(final String s){
		super(Material.ROCK);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockUnbreakable();
		this.setResistance(10.0F);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	@SuppressWarnings("deprecation")
	public float getPlayerRelativeBlockHardness(final IBlockState state, final EntityPlayer player, final World worldIn, final BlockPos pos){
		if(player.getHeldItemMainhand().getItem() instanceof IDepthRockTool) {
			if(!player.getHeldItemMainhand().isEmpty() && ((IDepthRockTool)player.getHeldItemMainhand().getItem()).canBreakRock(worldIn, player, player.getHeldItemMainhand(), state, pos))
				return (float)(1D / 100D);
		}
		return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		final float hardness = this.getExplosionResistance(null);
		tooltip.add("§d[" + I18nUtil.resolveKey("trait.unmineable") + "]");
		tooltip.add("§e" + I18nUtil.resolveKey("trait.destroybyexplosion"));
		if(hardness > 50){
			tooltip.add("§6" + I18nUtil.resolveKey("trait.blastres", hardness));
		}
	}
}