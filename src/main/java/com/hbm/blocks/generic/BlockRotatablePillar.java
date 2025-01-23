package com.hbm.blocks.generic;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockRotatablePillar extends BlockRotatedPillar {

	public BlockRotatablePillar(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.block_schrabidium_cluster)){
			tooltip.add(I18nUtil.resolveKey("tile.block_schrabidium_cluster.desc"));
		}
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.block_euphemium_cluster)){
			tooltip.add(I18nUtil.resolveKey("tile.block_euphemium_cluster.desc"));
		}
		final float hardness = this.getExplosionResistance(null);
		if(hardness > 50){
			tooltip.add("§6" + I18nUtil.resolveKey("trait.blastres", hardness));
		}
	}
}
