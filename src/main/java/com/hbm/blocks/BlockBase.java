package com.hbm.blocks;

import java.util.List;

import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockBase extends Block {
	
	public BlockBase(final Material m, final String s){
		super(m);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(MainRegistry.controlTab);
		ModBlocks.ALL_BLOCKS.add(this);
	}

	public BlockBase(final Material m, final SoundType sound, final String s){
		super(m);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setSoundType(sound);
		this.setHarvestLevel("pickaxe", 0);
		this.setCreativeTab(MainRegistry.controlTab);
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> list, final ITooltipFlag advanced) {
		if(stack.getItem() == Item.getItemFromBlock(ModBlocks.meteor_battery)){
			list.add(I18nUtil.resolveKey("desc.teslacoils"));
		}
		
		final float hardness = this.getExplosionResistance(null);
		if(hardness > 50){
			list.add(TextFormatting.GOLD + I18nUtil.resolveKey("trait.blastres", hardness));
		}
	}

	public Block setSoundType(final SoundType sound){
		return super.setSoundType(sound);
	}
}
