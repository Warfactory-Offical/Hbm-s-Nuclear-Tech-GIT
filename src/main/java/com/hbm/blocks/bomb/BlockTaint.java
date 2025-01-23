package com.hbm.blocks.bomb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.mob.EntityTaintedCreeper;
import com.hbm.main.MainRegistry;
import com.hbm.potion.HbmPotion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTaint extends Block {

	public static final PropertyInteger TEXTURE = PropertyInteger.create("taintage", 0, 15);
	
	public BlockTaint(final Material m, final String s) {
		super(m);
		this.setTickRandomly(true);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TEXTURE, 0));
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public void updateTick(final World world, final BlockPos pos1, final IBlockState state, final Random rand) {

		final int meta = state.getValue(TEXTURE);
    	if(!world.isRemote && meta < 15) {
    		final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
	    	for(int i = 0; i < 15; i++) {
	    		final int a = rand.nextInt(11) + pos1.getX() - 5;
	    		final int b = rand.nextInt(11) + pos1.getY() - 5;
	    		final int c = rand.nextInt(11) + pos1.getZ() - 5;
	    		pos.setPos(a, b, c);
	            if(world.getBlockState(pos).getBlock().isReplaceable(world, pos) && !world.getBlockState(pos).getMaterial().isLiquid() && BlockTaint.hasPosNeightbour(world, pos))
	            	world.setBlockState(pos, ModBlocks.taint.getBlockState().getBaseState().withProperty(TEXTURE, meta + 1), 2);
	    	}
	            
		    for(int i = 0; i < 85; i++) {
		    	final int a = rand.nextInt(7) + pos1.getX() - 3;
		    	final int b = rand.nextInt(7) + pos1.getY() - 3;
		    	final int c = rand.nextInt(7) + pos1.getZ() - 3;
		    	pos.setPos(a, b, c);
	           	if(world.getBlockState(pos).getBlock().isReplaceable(world, pos) && !world.getBlockState(pos).getMaterial().isLiquid() && BlockTaint.hasPosNeightbour(world, pos))
		           	world.setBlockState(pos, ModBlocks.taint.getBlockState().getBaseState().withProperty(TEXTURE, meta + 1), 2);
		    }
    	}
	}

	private static boolean checkAttachment(final World world, final BlockPos pos){
		if(!world.isAirBlock(pos)){
            return world.getBlockState(pos).getBlock() != ModBlocks.taint;
    	}
    	return false;
    }


	//Drillgon200: Ah yes, spelling.
	 public static boolean hasPosNeightbour(final World world, final BlockPos pos) {
	    	return checkAttachment(world, pos.add(1, 0, 0)) ||
					checkAttachment(world, pos.add(0, 1, 0)) ||
					checkAttachment(world, pos.add(0, 0, 1)) ||
					checkAttachment(world, pos.add(-1, 0, 0)) ||
					checkAttachment(world, pos.add(0, -1, 0)) ||
					checkAttachment(world, pos.add(0, 0, -1));
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(final IBlockState state, final World worldIn, final BlockPos pos) {
		return new AxisAlignedBB(pos, pos);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isCollidable(){
		return true;
	}

	@Override
	public boolean isReplaceable(final IBlockAccess worldIn, final BlockPos pos){
		return false;
	}


	@Override
	public void onEntityCollision(final World world, final BlockPos pos, final IBlockState state, final Entity entity) {
		final int meta = world.getBlockState(pos).getBlock().getMetaFromState(state);
		final int level = 15 - meta;
		
    	final List<ItemStack> list = new ArrayList<ItemStack>();
    	final PotionEffect effect = new PotionEffect(HbmPotion.taint, 15 * 20, level);
    	effect.setCurativeItems(list);
    	
    	if(entity instanceof EntityLivingBase) {
    		if(world.rand.nextInt(50) == 0) {
    			((EntityLivingBase)entity).addPotionEffect(effect);
    		}
    	}
    	
    	if(entity instanceof EntityCreeper) {
    		final EntityTaintedCreeper creep = new EntityTaintedCreeper(world);
    		creep.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);

    		if(!world.isRemote) {
    			entity.setDead();
    			world.spawnEntity(creep);
    		}
    	}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		tooltip.add("DO NOT TOUCH, BREATHE OR STARE AT. RUN!");
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TEXTURE);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(TEXTURE);
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState().withProperty(TEXTURE, meta);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if(!BlockTaint.hasPosNeightbour(world, pos) && !world.isRemote)
			world.setBlockToAir(pos);
	}
	
	@Override
	public boolean causesSuffocation(final IBlockState state) {
		return true;
	}
}
