package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.tool.ItemToolAbility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockCoalOil extends BlockOre {

	public BlockCoalOil(final String s) {
		super();
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		for(final EnumFacing dir : EnumFacing.VALUES) {

        	final IBlockState nS = world.getBlockState(pos.offset(dir));
        	final Block n = nS.getBlock();

        	if(n == ModBlocks.ore_coal_oil_burning || n == ModBlocks.balefire || n == Blocks.FIRE || nS.getMaterial() == Material.LAVA) {
        		world.scheduleUpdate(pos, this, world.rand.nextInt(20) + 2);
        	}
        }
	}
	
	@Override
	public void onBlockHarvested(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player) {
		if(player.getHeldItemMainhand() == null)
    		return;

    	if(!(player.getHeldItemMainhand().getItem() instanceof ItemTool || player.getHeldItemMainhand().getItem() instanceof ItemToolAbility))
    		return;

    	final ItemTool tool = (ItemTool)player.getHeldItemMainhand().getItem();

    	if(tool.getToolMaterialName() != ToolMaterial.WOOD.toString()) {

    		if(world.rand.nextInt(10) == 0)
    			world.setBlockState(pos, Blocks.FIRE.getDefaultState());
    	}
	}
	
	@Override
	public void onExplosionDestroy(final World worldIn, final BlockPos pos, final Explosion explosionIn) {
		worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState());
	}
	
	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand) {
		world.setBlockState(pos, ModBlocks.ore_coal_oil_burning.getDefaultState());
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.COAL;
	}
	
	@Override
	public int quantityDropped(final IBlockState state, final int fortune, final Random random) {
		return 2 + random.nextInt(2);
	}
}
