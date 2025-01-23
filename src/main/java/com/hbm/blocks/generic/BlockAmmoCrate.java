package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAmmoCrate extends Block {

	public BlockAmmoCrate(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public Block setSoundType(final SoundType sound) {
		return super.setSoundType(sound);
	}
	
	Random rand = new Random();
	
	@Override
	public void getDrops(final NonNullList<ItemStack> ret, final IBlockAccess world, final BlockPos pos, final IBlockState state, final int fortune) {
		ret.add(ItemStackUtil.itemStackFrom(ModItems.cap_nuka, 12 + rand.nextInt(21)));
        ret.add(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_stimpak, 1 + rand.nextInt(3)));
        
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_22lr, 16 + rand.nextInt(17)));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_9mm, 6 + rand.nextInt(13)));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge, 6 + rand.nextInt(4)));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge, 3 + rand.nextInt(4)));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_ammo, 10 + rand.nextInt(11)));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_iron_ammo, 12 + rand.nextInt(15)));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_50bmg, 2 + rand.nextInt(7)));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket, 1));
        if(rand.nextBoolean()) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade, 1 + rand.nextInt(2)));

        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_12gauge_incendiary, 3));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_incendiary, 3));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_caustic, 3));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge_flechette, 3));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_9mm_ap, 7));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_incendiary, 1));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_rocket_sleek, 1));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_he, 1));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_incendiary, 1));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.ammo_grenade_sleek, 1));
        if(rand.nextInt(10) == 0) ret.add(ItemStackUtil.itemStackFrom(ModItems.syringe_metal_super, 2));
	}

}
