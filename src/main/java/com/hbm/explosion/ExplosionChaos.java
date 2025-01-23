package com.hbm.explosion;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.CompatibilityConfig;
import com.hbm.blocks.generic.EntityGrenadeTau;
import com.hbm.entity.grenade.EntityGrenadeZOMG;
import com.hbm.entity.particle.EntityChlorineFX;
import com.hbm.entity.particle.EntityCloudFX;
import com.hbm.entity.particle.EntityModFX;
import com.hbm.entity.particle.EntityOrangeFX;
import com.hbm.entity.particle.EntityPinkCloudFX;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.entity.projectile.EntityMiniNuke;
import com.hbm.entity.projectile.EntityRainbow;
import com.hbm.entity.projectile.EntityRocket;
import com.hbm.entity.projectile.EntityRubble;
import com.hbm.entity.projectile.EntitySchrab;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.handler.ArmorUtil;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.ModDamageSource;
import com.hbm.potion.HbmPotion;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ExplosionChaos {

	private final static Random random = new Random();
	private static final Random rand = new Random();

	public static void explode(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = bombStartStrength;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for(int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for(int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy;
				for(int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if(ZZ < r22) {
						destruction(world, pos.setPos(X, Y, Z));
					}
				}
			}
		}
	}

	public static void destruction(final World world, final BlockPos pos) {

		final Block b = world.getBlockState(pos).getBlock();
		if(b == Blocks.BEDROCK || b == ModBlocks.reinforced_brick || b == ModBlocks.reinforced_sand || b == ModBlocks.reinforced_glass || b == ModBlocks.reinforced_lamp_on || b == ModBlocks.reinforced_lamp_off) {

		} else {
			world.setBlockToAir(pos);
		}
	}

	public static void spawnExplosion(final World world, final int x, final int y, final int z, final int bound) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		int randX;
		int randY;
		int randZ;

		for(int i = 0; i < 25; i++) {

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y + randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y + randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y - randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y + randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y + randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y - randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y + randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y + randY, z - randZ,
			// 5);
			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y - randY, z + randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y - randY, z + randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y + randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y + randY, z - randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x + randX, y - randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x + randX, y - randY, z - randZ,
			// 5);

			randX = random.nextInt(bound);
			randY = random.nextInt(bound);
			randZ = random.nextInt(bound);

			world.createExplosion(null, x - randX, y - randY, z - randZ, 10.0F, true);
			// ExplosionChaos.explode(world, x - randX, y - randY, z - randZ,
			// 5);
		}
	}

	// Drillgon200: Descriptive method names anyone?
	// Alcater: Ill write this down - maybe ill need it later. c stands for cloudPoisoning
	public static void c(final World world, final int x, final int y, final int z, int bombStartStrength) {
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
		final double wat = bombStartStrength * 2;

		bombStartStrength *= 2.0F;
		i = MathHelper.floor(x - wat - 1.0D);
		j = MathHelper.floor(x + wat + 1.0D);
		k = MathHelper.floor(y - wat - 1.0D);
		final int i2 = MathHelper.floor(y + wat + 1.0D);
		final int l = MathHelper.floor(z - wat - 1.0D);
		final int j2 = MathHelper.floor(z + wat + 1.0D);
		final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(i, k, l, j, i2, j2));

		for(int i1 = 0; i1 < list.size(); ++i1) {
			final Entity entity = list.get(i1);
			final double d4 = entity.getDistance(x, y, z) / bombStartStrength;

			if(d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				final double d9 = MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
				if(d9 < wat) {

					if(entity instanceof EntityPlayer) {
						if(!ArmorRegistry.hasProtection((EntityPlayer) entity, EntityEquipmentSlot.HEAD, HazardClass.GAS_CORROSIVE)){
							ArmorUtil.damageSuit((EntityPlayer) entity, 0, 5);
							ArmorUtil.damageSuit((EntityPlayer) entity, 1, 5);
							ArmorUtil.damageSuit((EntityPlayer) entity, 2, 5);
							ArmorUtil.damageSuit((EntityPlayer) entity, 3, 5);
						}
					}

					if(!(entity instanceof EntityPlayer && ArmorUtil.checkForHazmat((EntityPlayer) entity))) {

						if(entity instanceof EntityLivingBase livi){
                            if(livi.isPotionActive(HbmPotion.taint)) {
								livi.removePotionEffect(HbmPotion.taint);
								livi.addPotionEffect(new PotionEffect(HbmPotion.mutation, 60 * 60 * 20, 0, false, true));
							} else {
								if(ArmorRegistry.hasProtection(livi, EntityEquipmentSlot.HEAD, HazardClass.BACTERIA)){
									ArmorUtil.damageGasMaskFilter(livi, 1);
								}else{
									entity.attackEntityFrom(ModDamageSource.cloud, 3);
								}
							}
						}
					}
				}
			}
		}

		bombStartStrength = (int) f;
	}

	/**
	 * Sets all flammable blocks on fire
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param bound
	 */
	public static void flameDeath(final World world, final BlockPos pos, final int bound) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos mPos = new BlockPos.MutableBlockPos(pos);
		final MutableBlockPos mPosUp = new BlockPos.MutableBlockPos(pos.up());

		final int r = bound;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for(int xx = -r; xx < r; xx++) {
			final int X = xx + pos.getX();
			final int XX = xx * xx;
			for(int yy = -r; yy < r; yy++) {
				final int Y = yy + pos.getY();
				final int YY = XX + yy * yy;
				for(int zz = -r; zz < r; zz++) {
					final int Z = zz + pos.getZ();
					final int ZZ = YY + zz * zz;
					if(ZZ < r22) {
						mPos.setPos(X, Y, Z);
						mPosUp.setPos(X, Y + 1, Z);
						if(world.getBlockState(mPos).getBlock().isFlammable(world, mPos, EnumFacing.UP) && world.getBlockState(mPosUp).getBlock() == Blocks.AIR) {
							world.setBlockState(mPosUp, Blocks.FIRE.getDefaultState());
						}
					}
				}
			}
		}

	}

	/**
	 * Sets all blocks on fire
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param bound
	 */
	public static void burn(final World world, final BlockPos pos, final int bound) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos mPos = new BlockPos.MutableBlockPos(pos);
		final MutableBlockPos mPosUp = new BlockPos.MutableBlockPos(pos.up());

		final int r = bound;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for(int xx = -r; xx < r; xx++) {
			final int X = xx + pos.getX();
			final int XX = xx * xx;
			for(int yy = -r; yy < r; yy++) {
				final int Y = yy + pos.getY();
				final int YY = XX + yy * yy;
				for(int zz = -r; zz < r; zz++) {
					final int Z = zz + pos.getZ();
					final int ZZ = YY + zz * zz;
					if(ZZ < r22) {
						mPos.setPos(X, Y, Z);
						mPosUp.setPos(X, Y + 1, Z);
						if((world.getBlockState(mPosUp).getBlock() == Blocks.AIR || world.getBlockState(mPosUp).getBlock() == Blocks.SNOW_LAYER) && world.getBlockState(mPos) != Blocks.AIR) {
							world.setBlockState(mPosUp, Blocks.FIRE.getDefaultState());
						}
					}
				}
			}
		}

	}

	public static void spawnChlorine(final World world, final double x, final double y, final double z, final int count, final double speed, final int type) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		for(int i = 0; i < count; i++) {

			EntityModFX fx = null;

			if(type == 0) {
				fx = new EntityChlorineFX(world, x, y, z, 0.0, 0.0, 0.0);
			} else if(type == 1) {
				fx = new EntityCloudFX(world, x, y, z, 0.0, 0.0, 0.0);
			} else if(type == 2) {
				fx = new EntityPinkCloudFX(world, x, y, z, 0.0, 0.0, 0.0);
			} else {
				fx = new EntityOrangeFX(world, x, y, z, 0.0, 0.0, 0.0);
			}

			fx.motionY = rand.nextGaussian() * speed;
			fx.motionX = rand.nextGaussian() * speed;
			fx.motionZ = rand.nextGaussian() * speed;
			world.spawnEntity(fx);
		}
	}
	// Alcater: pc for pinkCouldPoisoning
	public static void pc(final World world, final int x, final int y, final int z, int bombStartStrength) {
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
		final double wat = bombStartStrength * 2;

		bombStartStrength *= 2.0F;
		i = MathHelper.floor(x - wat - 1.0D);
		j = MathHelper.floor(x + wat + 1.0D);
		k = MathHelper.floor(y - wat - 1.0D);
		final int i2 = MathHelper.floor(y + wat + 1.0D);
		final int l = MathHelper.floor(z - wat - 1.0D);
		final int j2 = MathHelper.floor(z + wat + 1.0D);
		final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(i, k, l, j, i2, j2));

		for(int i1 = 0; i1 < list.size(); ++i1) {
			final Entity entity = list.get(i1);
			final double d4 = entity.getDistance(x, y, z) / bombStartStrength;

			if(d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				final double d9 = MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
				if(d9 < wat) {

					if(entity instanceof EntityPlayer) {
						if(!ArmorRegistry.hasProtection((EntityPlayer) entity, EntityEquipmentSlot.HEAD, HazardClass.GAS_CORROSIVE)){
							ArmorUtil.damageSuit((EntityPlayer) entity, 0, 25);
							ArmorUtil.damageSuit((EntityPlayer) entity, 1, 25);
							ArmorUtil.damageSuit((EntityPlayer) entity, 2, 25);
							ArmorUtil.damageSuit((EntityPlayer) entity, 3, 25);
						}
					}
					if(entity instanceof EntityLivingBase){
						if(ArmorRegistry.hasAllProtection((EntityLivingBase) entity, EntityEquipmentSlot.HEAD, HazardClass.BACTERIA, HazardClass.SAND)){
							ArmorUtil.damageGasMaskFilter((EntityLivingBase) entity, 2);
						}else{
							entity.attackEntityFrom(ModDamageSource.pc, 5);
						}
					}
				}
			}
		}

		bombStartStrength = (int) f;
	}

	//Alcater: used by grenades and Chlorine seal gas blocks
	public static void poison(final World world, final int x, final int y, final int z, int bombStartStrength) {
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
		final double wat = bombStartStrength * 2;

		bombStartStrength *= 2.0F;
		i = MathHelper.floor(x - wat - 1.0D);
		j = MathHelper.floor(x + wat + 1.0D);
		k = MathHelper.floor(y - wat - 1.0D);
		final int i2 = MathHelper.floor(y + wat + 1.0D);
		final int l = MathHelper.floor(z - wat - 1.0D);
		final int j2 = MathHelper.floor(z + wat + 1.0D);
		final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(i, k, l, j, i2, j2));

		for(int i1 = 0; i1 < list.size(); ++i1) {
			final Entity entity = list.get(i1);
			final double d4 = entity.getDistance(x, y, z) / bombStartStrength;

			if(d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				final double d9 = MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
				if(d9 < wat) {
					if(!(entity instanceof EntityLivingBase entityLiving))
						continue;

                    if(ArmorRegistry.hasAllProtection(entityLiving, EntityEquipmentSlot.HEAD, HazardClass.NERVE_AGENT)) {
						ArmorUtil.damageGasMaskFilter(entityLiving, 1);
					} else {
						entityLiving.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 5 * 20, 0));
						entityLiving.addPotionEffect(new PotionEffect(MobEffects.POISON, 20 * 20, 2));
						entityLiving.addPotionEffect(new PotionEffect(MobEffects.WITHER, 20, 1));
						entityLiving.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30 * 20, 1));
						entityLiving.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 30 * 20, 2));
					}
				}
			}
		}

		bombStartStrength = (int) f;
	}

	public static void cluster(final World world, final int x, final int y, final int z, final int count, final double gravity) {
		 cluster(world, x, y, z, count, gravity, 5);
	}

	public static void cluster(final World world, final int x, final int y, final int z, final int count, final double gravity, final int size) {

		double mx = 0;
		double my = 0;
		double mz = 0;
		EntityRocket fragment;

		for(int i = 0; i < count; i++) {
			mx = rand.nextGaussian() * 0.1 * size;
			my = rand.nextGaussian();
			mz = rand.nextGaussian() * 0.1 * size;

			fragment = new EntityRocket(world, x, y, z, mx, my, mz, gravity);
			fragment.explosionSize = size;
			world.spawnEntity(fragment);
		}
	}

	public static void miniMirv(final World world, final double x, final double y, final double z) {
		final double modifier = 1.25;
		final double zeta = Math.sqrt(2) / 2;
		final EntityMiniNuke mirv1 = new EntityMiniNuke(world);
		final EntityMiniNuke mirv2 = new EntityMiniNuke(world);
		final EntityMiniNuke mirv3 = new EntityMiniNuke(world);
		final EntityMiniNuke mirv4 = new EntityMiniNuke(world);
		final double vx1 = 1;
		final double vy1 = rand.nextDouble() * -1;
		final double vz1 = 0;

		mirv1.posX = x;
		mirv1.posY = y;
		mirv1.posZ = z;
		mirv1.motionY = vy1;
		mirv2.posX = x;
		mirv2.posY = y;
		mirv2.posZ = z;
		mirv2.motionY = vy1;
		mirv3.posX = x;
		mirv3.posY = y;
		mirv3.posZ = z;
		mirv3.motionY = vy1;
		mirv4.posX = x;
		mirv4.posY = y;
		mirv4.posZ = z;
		mirv4.motionY = vy1;

		mirv1.motionX = vx1 * modifier;
		mirv1.motionZ = vz1 * modifier;
		world.spawnEntity(mirv1);

		mirv2.motionX = -vz1 * modifier;
		mirv2.motionZ = vx1 * modifier;
		world.spawnEntity(mirv2);

		mirv3.motionX = -vx1 * modifier;
		mirv3.motionZ = -vz1 * modifier;
		world.spawnEntity(mirv3);

		mirv4.motionX = vz1 * modifier;
		mirv4.motionZ = -vx1 * modifier;
		world.spawnEntity(mirv4);

		final EntityMiniNuke mirv5 = new EntityMiniNuke(world);
		final EntityMiniNuke mirv6 = new EntityMiniNuke(world);
		final EntityMiniNuke mirv7 = new EntityMiniNuke(world);
		final EntityMiniNuke mirv8 = new EntityMiniNuke(world);
		// double vx2 = vx1 < theta ? vx1 + theta : vx1 - theta;
		// double vy2 = vy1;
		// double vz2 = Math.sqrt(Math.pow(1, 2) - Math.pow(vx2, 2));
		final double vx2 = zeta;
		final double vy2 = vy1;
		final double vz2 = zeta;

		mirv5.posX = x;
		mirv5.posY = y;
		mirv5.posZ = z;
		mirv5.motionY = vy2;
		mirv6.posX = x;
		mirv6.posY = y;
		mirv6.posZ = z;
		mirv6.motionY = vy2;
		mirv7.posX = x;
		mirv7.posY = y;
		mirv7.posZ = z;
		mirv7.motionY = vy2;
		mirv8.posX = x;
		mirv8.posY = y;
		mirv8.posZ = z;
		mirv8.motionY = vy2;

		mirv5.motionX = vx2 * modifier;
		mirv5.motionZ = vz2 * modifier;
		world.spawnEntity(mirv5);

		mirv6.motionX = -vz2 * modifier;
		mirv6.motionZ = vx2 * modifier;
		world.spawnEntity(mirv6);

		mirv7.motionX = -vx2 * modifier;
		mirv7.motionZ = -vz2 * modifier;
		world.spawnEntity(mirv7);

		mirv8.motionX = vz2 * modifier;
		mirv8.motionZ = -vx2 * modifier;
		world.spawnEntity(mirv8);
	}

	public static void explodeZOMG(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = bombStartStrength;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for(int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for(int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy;
				for(int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if(ZZ < r22) {
						pos.setPos(X, Y, Z);
						if(!(world.getBlockState(pos).getBlock() == Blocks.BEDROCK && Y <= 0))
							world.setBlockToAir(pos);
					}
				}
			}
		}
	}

	public static void frag(final World world, final int x, final int y, final int z, final int count, final boolean flame, final Entity shooter) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityArrow fragment;

		for(int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if(rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if(rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			fragment = new EntityTippedArrow(world, x, y, z);

			fragment.motionX = d1;
			fragment.motionY = d2;
			fragment.motionZ = d3;
			fragment.shootingEntity = shooter;

			fragment.setIsCritical(true);
			if(flame) {
				fragment.setFire(1000);
			}

			fragment.setDamage(2.5);

			world.spawnEntity(fragment);
		}
	}

	public static void schrab(final World world, final int x, final int y, final int z, final int count, final int gravity) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntitySchrab fragment;

		for(int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if(rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if(rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			fragment = new EntitySchrab(world, x, y, z, d1, d2, d3, 0.0125D);

			world.spawnEntity(fragment);
		}
	}

	@SuppressWarnings("deprecation")
	public static void pulse(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final int r = bombStartStrength;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for(int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for(int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy;
				for(int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if(ZZ < r22) {
						if(world.getBlockState(new BlockPos(X, Y, Z)).getBlock().getExplosionResistance(null) <= 70)
							pDestruction(world, X, Y, Z);
					}
				}
			}
		}
	}

	public static void pDestruction(final World world, final int x, final int y, final int z) {
		final BlockPos pos = new BlockPos(x, y, z);
		final IBlockState state = world.getBlockState(pos);
		final EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, (float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, state);
		world.spawnEntity(entityfallingblock);
	}

	public static void plasma(final World world, final int x, final int y, final int z, final int radius) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = radius;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for(int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for(int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy;
				for(int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if(ZZ < r22 + world.rand.nextInt(r22 / 2)) {
						pos.setPos(X, Y, Z);						
						final Block block =world.getBlockState(pos).getBlock();
						if(block.getExplosionResistance(null) > 0.1F) continue;
						if(block != Blocks.BEDROCK && world.getBlockState(pos).getBlock() != ModBlocks.statue_elb
													&& world.getBlockState(pos).getBlock() != ModBlocks.statue_elb_g
													&& world.getBlockState(pos).getBlock() != ModBlocks.statue_elb_w
													&& world.getBlockState(pos).getBlock() != ModBlocks.statue_elb_f)
							world.setBlockState(pos, ModBlocks.plasma.getDefaultState());
					}
				}
			}
		}
	}

	// Drillgon200: This method name irks me.
	public static void tauMeSinPi(final World world, final double x, final double y, final double z, final int count, final Entity shooter, final EntityGrenadeTau tau) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;
		EntityBullet fragment;

		if(shooter != null && shooter instanceof EntityPlayer)
			for(int i = 0; i < count; i++) {
				d1 = rand.nextDouble();
				d2 = rand.nextDouble();
				d3 = rand.nextDouble();

				if(rand.nextInt(2) == 0) {
					d1 *= -1;
				}

				if(rand.nextInt(2) == 0) {
					d2 *= -1;
				}

				if(rand.nextInt(2) == 0) {
					d3 *= -1;
				}

				if(rand.nextInt(5) == 0) {
					fragment = new EntityBullet(world, (EntityPlayer) shooter, 3.0F, 35, 45, false, "tauDay", tau);
					fragment.setDamage(rand.nextInt(301) + 100);
				} else {
					fragment = new EntityBullet(world, (EntityPlayer) shooter, 3.0F, 35, 45, false, "eyyOk", tau);
					fragment.setDamage(rand.nextInt(11) + 35);
				}

				fragment.motionX = d1 * 5;
				fragment.motionY = d2 * 5;
				fragment.motionZ = d3 * 5;
				fragment.shootingEntity = shooter;

				fragment.setIsCritical(true);

				world.spawnEntity(fragment);
			}
	}

	// Drillgon200: You know what? I'm changing this one.
	public static void zomg(final World world, final double x, final double y, final double z, final int count, final Entity shooter, final EntityGrenadeZOMG zomg) {

		double d1 = 0;
		double d2 = 0;
		double d3 = 0;

		// if (shooter != null && shooter instanceof EntityPlayer)
		for(int i = 0; i < count; i++) {
			d1 = rand.nextDouble();
			d2 = rand.nextDouble();
			d3 = rand.nextDouble();

			if(rand.nextInt(2) == 0) {
				d1 *= -1;
			}

			if(rand.nextInt(2) == 0) {
				d2 *= -1;
			}

			if(rand.nextInt(2) == 0) {
				d3 *= -1;
			}

			final EntityRainbow entityZomg = new EntityRainbow(world, (EntityPlayer) shooter, 1F, 10000, 100000, zomg);

			entityZomg.motionX = d1;// * 5;
			entityZomg.motionY = d2;// * 5;
			entityZomg.motionZ = d3;// * 5;
			entityZomg.shootingEntity = shooter;

			world.spawnEntity(entityZomg);
			world.playSound(null, zomg.posX, zomg.posY, zomg.posZ, HBMSoundHandler.zomgShoot, SoundCategory.AMBIENT, 10.0F, 0.8F + (rand.nextFloat() * 0.4F));
		}
	}

	public static void spawnVolley(final World world, final double x, final double y, final double z, final int count, final double speed) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		for(int i = 0; i < count; i++) {

			final EntityModFX fx = new EntityOrangeFX(world, x, y, z, 0.0, 0.0, 0.0);

			fx.motionX = rand.nextGaussian() * speed;
			fx.motionZ = rand.nextGaussian() * speed;

			fx.motionY = rand.nextDouble() * speed * 7.5D;

			world.spawnEntity(fx);
		}
	}

	public static void floater(final World world, final BlockPos pos, final int radi, final int height) {
		floater(world, pos.getX(), pos.getY(), pos.getZ(), radi, height);
	}

	public static void floater(final World world, final int x, final int y, final int z, final int radi, final int height) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		IBlockState save;

		final int r = radi;
		final int r2 = r * r;
		final int r22 = r2 / 2;
		for(int xx = -r; xx < r; xx++) {
			final int X = xx + x;
			final int XX = xx * xx;
			for(int yy = -r; yy < r; yy++) {
				final int Y = yy + y;
				final int YY = XX + yy * yy;
				for(int zz = -r; zz < r; zz++) {
					final int Z = zz + z;
					final int ZZ = YY + zz * zz;
					if(ZZ < r22) {
						pos.setPos(X, Y, Z);
						save = world.getBlockState(pos);
						world.setBlockState(pos, Blocks.AIR.getDefaultState());
						if(save.getBlock() != Blocks.AIR) {
							world.setBlockState(pos.setPos(X, Y + height, Z), save);
						}
					}
				}
			}
		}

	}

	public static void move(final World world, final BlockPos pos, final int radius, final int a, final int b, final int c) {
		move(world, pos.getX(), pos.getY(), pos.getZ(), radius, a, b, c);
	}

	public static void move(final World world, final int x, final int y, final int z, int radius, final int a, final int b, final int c) {
		final float f = radius;
		final int i;
		final int j;
		final int k;
		double d5;
		double d6;
		double d7;
		final double wat = radius;
		int rand = 0;

		radius *= 2.0F;
		i = MathHelper.floor(x - wat - 1.0D);
		j = MathHelper.floor(x + wat + 1.0D);
		k = MathHelper.floor(y - wat - 1.0D);
		final int i2 = MathHelper.floor(y + wat + 1.0D);
		final int l = MathHelper.floor(z - wat - 1.0D);
		final int j2 = MathHelper.floor(z + wat + 1.0D);
		final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(i, k, l, j, i2, j2));

		for(int i1 = 0; i1 < list.size(); ++i1) {
			final Entity entity = list.get(i1);
			final double d4 = entity.getDistance(x, y, z) / radius;

			if(d4 <= 1.0D) {
				d5 = entity.posX - x;
				d6 = entity.posY + entity.getEyeHeight() - y;
				d7 = entity.posZ - z;
				if(entity instanceof EntityLiving && !(entity instanceof EntitySheep)) {
					rand = random.nextInt(2);
					if(rand == 0) {
						entity.setCustomNameTag("Dinnerbone");
					} else {
						entity.setCustomNameTag("Grumm");
					}
				}

				if(entity instanceof EntitySheep) {
					entity.setCustomNameTag("jeb_");
				}

				final double d9 = MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
				if(d9 < wat) {
					entity.setPosition(entity.posX += a, entity.posY += b, entity.posZ += c);
				}
			}
		}

		radius = (int) f;
	}

	public static void levelDown(final World world, final int x, final int y, final int z, final int radius) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		if(!world.isRemote)
			for(int i = x - radius; i <= x + radius; i++)
				for(int j = z - radius; j <= z + radius; j++) {

					final IBlockState b = world.getBlockState(pos.setPos(i, y, j));
					final float k = b.getBlockHardness(world, pos.setPos(i, y, j));
					if(k < 6000 && k > 0 && b.getBlock() != Blocks.AIR) {

						final EntityRubble rubble = new EntityRubble(world);
						rubble.posX = i + 0.5F;
						rubble.posY = y;
						rubble.posZ = j + 0.5F;

						rubble.motionY = 0.025F * 10 + 0.15F;
						rubble.setMetaBasedOnBlock(b.getBlock(), b.getBlock().getMetaFromState(b));

						world.spawnEntity(rubble);

						world.setBlockState(pos.setPos(i, y, j), Blocks.AIR.getDefaultState());
					}
				}
	}

	public static void decontaminate(final World world, final BlockPos pos) {
		// Bridged
		// if (!world.isRemote) {

		final Random random = new Random();
		final IBlockState b = world.getBlockState(pos);
		final Block bblock = b.getBlock();

		if(bblock == ModBlocks.waste_earth && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.GRASS.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_dirt && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_sand && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.SAND.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_sand_red && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.SAND.getStateFromMeta(1));
		}
		
		else if(bblock == Blocks.SANDSTONE && random.nextInt(3) != 0) {
				world.setBlockState(pos, ModBlocks.waste_sandstone.getDefaultState());
		} 
		
		else if((bblock == Blocks.HARDENED_CLAY || bblock == Blocks.STAINED_HARDENED_CLAY) && random.nextInt(3) != 0) {
			world.setBlockState(pos, ModBlocks.waste_sandstone_red.getDefaultState());
		}

		else if(bblock == Blocks.RED_SANDSTONE && random.nextInt(3) != 0) {
			world.setBlockState(pos, ModBlocks.waste_sandstone_red.getDefaultState());
		}
		
		else if(bblock == ModBlocks.waste_grass_tall && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.TALLGRASS.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_gravel && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.GRAVEL.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_mycelium && random.nextInt(5) == 0) {
			world.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_leaves && random.nextInt(5) != 0) {
			world.setBlockState(pos, Blocks.LEAVES.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_trinitite && random.nextInt(3) == 0) {
			world.setBlockState(pos, Blocks.SAND.getDefaultState());
		}

		else if(bblock == ModBlocks.waste_trinitite_red && random.nextInt(3) == 0) {
			world.setBlockState(pos, Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND));
		}

		else if(bblock == ModBlocks.waste_log && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(world.getBlockState(pos).getValue(BlockLog.AXIS))));
		}

		else if(bblock == ModBlocks.waste_planks && random.nextInt(3) != 0) {
			world.setBlockState(pos, Blocks.PLANKS.getDefaultState());
		}

		else if(bblock == ModBlocks.block_trinitite && random.nextInt(10) == 0) {
			world.setBlockState(pos, ModBlocks.block_lead.getDefaultState());
		}

		else if(bblock == ModBlocks.block_waste && random.nextInt(10) == 0) {
			world.setBlockState(pos, ModBlocks.block_lead.getDefaultState());
		}

		else if(bblock == ModBlocks.sellafield_core && random.nextInt(10) == 0) {
			world.setBlockState(pos, ModBlocks.sellafield_4.getStateFromMeta(world.rand.nextInt(4)));
		}

		else if(bblock == ModBlocks.sellafield_4 && random.nextInt(5) == 0) {
			world.setBlockState(pos, ModBlocks.sellafield_3.getStateFromMeta(world.rand.nextInt(4)));
		}

		else if(bblock == ModBlocks.sellafield_3 && random.nextInt(5) == 0) {
			world.setBlockState(pos, ModBlocks.sellafield_2.getStateFromMeta(world.rand.nextInt(4)));
		}

		else if(bblock == ModBlocks.sellafield_2 && random.nextInt(5) == 0) {
			world.setBlockState(pos, ModBlocks.sellafield_1.getStateFromMeta(world.rand.nextInt(4)));
		}

		else if(bblock == ModBlocks.sellafield_1 && random.nextInt(5) == 0) {
			world.setBlockState(pos, ModBlocks.sellafield_0.getStateFromMeta(world.rand.nextInt(4)));
		}

		else if(bblock == ModBlocks.sellafield_0 && random.nextInt(5) == 0) {
			world.setBlockState(pos, ModBlocks.sellafield_slaked.getStateFromMeta(world.rand.nextInt(4)));
		}

		else if(bblock == ModBlocks.sellafield_slaked && random.nextInt(5) == 0) {
			world.setBlockState(pos, Blocks.STONE.getDefaultState());
		}

	}
	
	public static void hardenVirus(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = bombStartStrength;
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
					if (ZZ < r22) {
						if (world.getBlockState(pos.setPos(X, Y, Z)).getBlock() == ModBlocks.crystal_virus)
							world.setBlockState(pos.setPos(X, Y, Z), ModBlocks.crystal_hardened.getDefaultState());
					}
				}
			}
		}
	}

	public static void spreadVirus(final World world, final int x, final int y, final int z, final int bombStartStrength) {
		if(!CompatibilityConfig.isWarDim(world)){
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		final int r = bombStartStrength;
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
					if (ZZ < r22) {
						if (rand.nextInt(15) == 0 && world.getBlockState(pos.setPos(X, Y, Z)).getBlock() != Blocks.AIR)
							world.setBlockState(pos.setPos(X, Y, Z), ModBlocks.cheater_virus_seed.getDefaultState());
					}
				}
			}
		}
	}
}
