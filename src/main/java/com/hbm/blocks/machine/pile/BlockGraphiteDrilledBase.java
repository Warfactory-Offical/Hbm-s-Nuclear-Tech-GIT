package com.hbm.blocks.machine.pile;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockHazardFuel;
import com.hbm.items.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGraphiteDrilledBase extends BlockHazardFuel {

	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);

	public BlockGraphiteDrilledBase(final String s) {
		super(ModBlocks.block_graphite.getDefaultState().getMaterial(), s, ((BlockHazardFuel) ModBlocks.block_graphite).encouragement, ((BlockHazardFuel) ModBlocks.block_graphite).flammability, 16000);
		this.setCreativeTab(null);
		this.setSoundType(SoundType.METAL);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
	}
	
	protected static void ejectItem(final World world, final int x, final int y, final int z, final EnumFacing dir, final ItemStack stack) {
		
		final EntityItem dust = new EntityItem(world, x + 0.5D + dir.getXOffset() * 0.75D, y + 0.5D + dir.getYOffset() * 0.75D, z + 0.5D + dir.getZOffset() * 0.75D, stack);
		dust.motionX = dir.getXOffset() * 0.25;
		dust.motionY = dir.getYOffset() * 0.25;
		dust.motionZ = dir.getZOffset() * 0.25;
		world.spawnEntity(dust);
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		return Items.AIR;
	}
	
	@Override
	public void getDrops(final NonNullList<ItemStack> drops, final IBlockAccess world, final BlockPos pos, final IBlockState state, final int fortune){
		drops.add(ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.GRAPHITE), 8));
	}
	
	@Override
	public int getMetaFromState(final IBlockState state){
		return state.getValue(AXIS).ordinal();
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta){
		return this.getDefaultState().withProperty(AXIS, EnumFacing.Axis.values()[meta&3]);
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, AXIS);
	}
}
