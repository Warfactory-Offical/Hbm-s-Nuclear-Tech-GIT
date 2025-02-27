package com.hbm.explosion;

import java.util.List;

import com.hbm.config.CompatibilityConfig;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.WasteLog;
import com.hbm.handler.ArmorUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ExplosionThermo {

	public static void freeze(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = bombStartStrength * 2;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if (ZZ < r22 + world.rand.nextInt(r22 / 2))
						pos.setPos(X, Y, Z);
					freezeDest(world, pos);
				}
			}
		}
	}

	public static void scorch(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = bombStartStrength * 2;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for (int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for (int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy;
				for (int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if (ZZ < r22 + world.rand.nextInt(r22 / 2))
						pos.setPos(X, Y, Z);
					scorchDest(world, pos);
				}
			}
		}
	}

	public static void scorchDest(final World world, final BlockPos pos) {
		final Block block = world.getBlockState(pos).getBlock();

		if (block == Blocks.GRASS) {
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		
		} else if (block == ModBlocks.frozen_grass) {
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		
		} else if (block == Blocks.DIRT) {
			world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
		
		} else if (block == ModBlocks.frozen_dirt) {
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		
		} else if (block == Blocks.NETHERRACK) {
			world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState());
		
		} else if (block == ModBlocks.frozen_log) {
			world.setBlockState(pos, ((WasteLog)ModBlocks.waste_log).withSameRotationState(world.getBlockState(pos)));
		
		} else if(block instanceof BlockLog) {
			world.setBlockState(pos, ((WasteLog)ModBlocks.waste_log).getSameRotationState(world.getBlockState(pos)));
		
		} else if (block == ModBlocks.frozen_planks) {
			world.setBlockState(pos, ModBlocks.waste_planks.getDefaultState());
		
		} else if (block == Blocks.PLANKS) {
			world.setBlockState(pos, ModBlocks.waste_planks.getDefaultState());
		
		} else if (block == Blocks.STONE) {
			world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState());
		
		} else if (block == Blocks.COBBLESTONE) {
			world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState());
		
		} else if (block == Blocks.STONEBRICK) {
			world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState());
		
		} else if (block == Blocks.OBSIDIAN) {
			world.setBlockState(pos, Blocks.FLOWING_LAVA.getDefaultState());
		
		} else if (block instanceof BlockLeaves) {
			world.setBlockToAir(pos);
		
		} else if (block == Blocks.WATER) {
			world.setBlockToAir(pos);
		
		} else if (block == Blocks.FLOWING_WATER) {
			world.setBlockToAir(pos);
		
		} else if (block == Blocks.PACKED_ICE) {
			world.setBlockState(pos, Blocks.FLOWING_WATER.getDefaultState());
		
		} else if (block == Blocks.ICE) {
			world.setBlockToAir(pos);
		
		} else if (block == Blocks.SNOW) {
			world.setBlockToAir(pos);
		
		} else if (block == Blocks.SNOW_LAYER) {
			world.setBlockToAir(pos);
		}
	}

	public static void freezeDest(final World world, final BlockPos pos) {
		final Block block = world.getBlockState(pos).getBlock();

		if (block == Blocks.GRASS) {
			world.setBlockState(pos, ModBlocks.frozen_grass.getDefaultState());
		
		} else if (block == Blocks.DIRT) {
			world.setBlockState(pos, ModBlocks.frozen_dirt.getDefaultState());
		
		} else if (block == Blocks.PLANKS) {
			world.setBlockState(pos, ModBlocks.frozen_planks.getDefaultState());
		
		} else if (block == ModBlocks.waste_log) {
			world.setBlockState(pos, ((WasteLog)ModBlocks.frozen_log).withSameRotationState(world.getBlockState(pos)));
		
		} else if(block instanceof BlockLog) {
			world.setBlockState(pos, ((WasteLog)ModBlocks.frozen_log).getSameRotationState(world.getBlockState(pos)));
		
		} else if (block == ModBlocks.waste_planks) {
			world.setBlockState(pos, ModBlocks.frozen_planks.getDefaultState());
		
		} else if (block == Blocks.STONE) {
			world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
		
		} else if (block == Blocks.COBBLESTONE) {
			world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
		
		} else if (block == Blocks.STONEBRICK) {
			world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
		
		} else if (block instanceof BlockLeaves) {
			world.setBlockState(pos, Blocks.SNOW.getDefaultState());
		
		} else if (block == Blocks.LAVA) {
			world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
		
		} else if (block == Blocks.FLOWING_LAVA) {
			world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
		
		} else if (block == Blocks.WATER) {
			world.setBlockState(pos, Blocks.ICE.getDefaultState());
		
		} else if (block == Blocks.FLOWING_WATER) {
			world.setBlockState(pos, Blocks.ICE.getDefaultState());
		}
	}

	public static void freezer(final World world, final int x, final int y, final int z, int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final float f = bombStartStrength;
		final int i;
		final int j;
		final int k;
		double d5;
		double d6;
		double d7;
		final double wat = bombStartStrength;

		bombStartStrength *= 2.0F;
		i = MathHelper.floor(x - wat - 1.0D);
		j = MathHelper.floor(x + wat + 1.0D);
		k = MathHelper.floor(y - wat - 1.0D);
		final int i2 = MathHelper.floor(y + wat + 1.0D);
		final int l = MathHelper.floor(z - wat - 1.0D);
		final int j2 = MathHelper.floor(z + wat + 1.0D);
		final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(i, k, l, j, i2, j2));

		final MutableBlockPos pos = new BlockPos.MutableBlockPos();

		for (int i1 = 0; i1 < list.size(); ++i1) {
			final Entity entity = list.get(i1);
			final double d4 = entity.getDistance(x, y, z) / bombStartStrength;

			if (d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				final double d9 = MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
				if (d9 < wat && !(entity instanceof EntityOcelot) && entity instanceof EntityLivingBase) {
					for (int a = (int) entity.posX - 2; a < (int) entity.posX + 1; a++) {
						for (int b = (int) entity.posY; b < (int) entity.posY + 3; b++) {
							for (int c = (int) entity.posZ - 1; c < (int) entity.posZ + 2; c++) {
								pos.setPos(a, b, c);
								world.setBlockState(pos, Blocks.ICE.getDefaultState());
							}
						}
					}

					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2 * 60 * 20, 4));
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 90 * 20, 2));
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 3 * 60 * 20, 2));
				}
			}
		}

		bombStartStrength = (int) f;
	}

	public static void setEntitiesOnFire(final World world, final double x, final double y, final double z, final int radius) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));

		for(final Entity e : list) {
			
			if(e.getDistance(x, y, z) <= radius) {

				if(!(e instanceof EntityPlayer && ArmorUtil.checkForAsbestos((EntityPlayer) e))) {
					
					if(e instanceof EntityLivingBase)
						((EntityLivingBase) e).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 15 * 20, 4));
					
					e.setFire(10);
				}
			}
		}
	}
	
	public static void scorchLight(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = bombStartStrength * 2;
		final int r2 = r*r;
		final int r22 = r2/2;
		for (int xx = -r; xx < r; xx++)
		{
			final int X = xx+x;
			final int XX = xx*xx;
			for (int yy = -r; yy < r; yy++)
			{
				final int Y = yy+y;
				final int YY = XX+yy*yy;
				for (int zz = -r; zz < r; zz++)
				{
					final int Z = zz+z;
					final int ZZ = YY+zz*zz;
					if (ZZ<r22 + world.rand.nextInt(r22/2))
						scorchDestLight(world, pos.setPos(X, Y, Z));
				}
			}
		}
	}
	
	public static void scorchDestLight(final World world, final BlockPos pos) {
		final IBlockState blockstate = world.getBlockState(pos);
		final Block block = blockstate.getBlock();
		
		if(block == Blocks.GRASS){
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		
		} else if(block == ModBlocks.frozen_grass){
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		
		} else if(block == Blocks.DIRT) {
			world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
		
		} else if(block == ModBlocks.frozen_dirt){
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		
		} else if(block == ModBlocks.waste_earth){
			world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
		
		} else if(block == ModBlocks.frozen_log){
			world.setBlockState(pos, ((WasteLog)ModBlocks.waste_log).withSameRotationState(world.getBlockState(pos)));
		
		} else if(block instanceof BlockLog){
			world.setBlockState(pos, ((WasteLog)ModBlocks.waste_log).getSameRotationState(world.getBlockState(pos)));
		
		} else if(block == ModBlocks.frozen_planks){
			world.setBlockState(pos, ModBlocks.waste_planks.getDefaultState());
		
		} else if(block == Blocks.PLANKS){
			world.setBlockState(pos, ModBlocks.waste_planks.getDefaultState());
		
		} else if(block == Blocks.OBSIDIAN){
			world.setBlockState(pos, ModBlocks.gravel_obsidian.getDefaultState());
		
		} else if(block instanceof BlockLeaves){
			world.setBlockToAir(pos);
		
		} else if(block == Blocks.WATER){
			world.setBlockToAir(pos);
		
		} else if(block == Blocks.FLOWING_WATER){
			world.setBlockToAir(pos);
		
		} else if(block == Blocks.PACKED_ICE){
			world.setBlockState(pos, Blocks.FLOWING_WATER.getDefaultState());
		
		} else if(block == Blocks.ICE){
			world.setBlockToAir(pos);
		
		} else if(block == Blocks.SAND){
			world.setBlockState(pos, Blocks.GLASS.getDefaultState());
		
		} else if(block == Blocks.CLAY){
			world.setBlockState(pos, Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.values()[world.rand.nextInt(16)]));
		}
	}
	public static void snow(final World world, final int x, final int y, final int z, final int bound) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
    	final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		
    	final int r = bound;
    	final int r2 = r*r;
    	final int r22 = r2/2;
    	for (int xx = -r; xx < r; xx++)
    	{
    		final int X = xx+x;
    		final int XX = xx*xx;
    		for (int yy = -r; yy < r; yy++)
    		{
    			final int Y = yy+y;
    			final int YY = XX+yy*yy;
    			for (int zz = -r; zz < r; zz++)
    			{
    				final int Z = zz+z;
    				final int ZZ = YY+zz*zz;
    				if (ZZ<r22)
    				{
    					pos.setPos(X, Y + 1, Z);
    					if(Blocks.SNOW_LAYER.canPlaceBlockAt(world, pos) && (world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == Blocks.FIRE)) {
    						world.setBlockState(pos, Blocks.SNOW_LAYER.getDefaultState());
    					}
    				}
    			}
    		}
    	}
    }
}
