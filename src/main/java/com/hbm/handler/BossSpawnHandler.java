package com.hbm.handler;

import com.hbm.main.MainRegistry;
import com.hbm.items.ModItems;
import com.hbm.entity.mob.EntityFBI;
import com.hbm.entity.mob.EntityMaskMan;
import com.hbm.entity.mob.EntityRADBeast;
import com.hbm.entity.projectile.EntityMeteor;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.config.MobConfig;
import com.hbm.config.GeneralConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.util.ContaminationUtil;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class BossSpawnHandler {

	public static void rollTheDice(final World world) {

		if(MobConfig.enableMaskman) {
			if(world.getTotalWorldTime() % MobConfig.maskmanDelay == 0) {

				if(world.rand.nextInt(MobConfig.maskmanChance) == 0 && !world.playerEntities.isEmpty() && world.provider.isSurfaceWorld()) {	//33% chance only if there is a player online

					final EntityPlayer player = world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));	//choose a random player
					
					
					if(ContaminationUtil.getRads(player) >= MobConfig.maskmanMinRad && (world.getHeight((int)player.posX, (int)player.posZ) > player.posY + 3 || !MobConfig.maskmanUnderground)) {	//if the player has more than 50 RAD and is underground
						player.sendMessage(new TextComponentString("The mask man is about to claim another victim.").setStyle(new Style().setColor(TextFormatting.RED)));
						
						final double spawnX = player.posX + world.rand.nextGaussian() * 20;
						final double spawnZ = player.posZ + world.rand.nextGaussian() * 20;
						final double spawnY = world.getHeight((int)spawnX, (int)spawnZ);

						trySpawn(world, (float)spawnX, (float)spawnY, (float)spawnZ, new EntityMaskMan(world));
					}
				}
			}
		}

		if(MobConfig.enableRaids) {

			if(world.getTotalWorldTime() % MobConfig.raidDelay == 0) {

				if(world.rand.nextInt(MobConfig.raidChance) == 0 && !world.playerEntities.isEmpty() && world.provider.isSurfaceWorld()) {

					final EntityPlayer player = world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
					player.sendMessage(new TextComponentString("FBI, OPEN UP!").setStyle(new Style().setColor(TextFormatting.RED)));
					
					final Vec3 vec = Vec3.createVectorHelper(MobConfig.raidAttackDistance, 0, 0);
					vec.rotateAroundY((float)(Math.PI * 2) * world.rand.nextFloat());

					for(int i = 0; i < MobConfig.raidAmount; i++) {

						final double spawnX = player.posX + vec.xCoord + world.rand.nextGaussian() * 5;
						final double spawnZ = player.posZ + vec.zCoord + world.rand.nextGaussian() * 5;
						final double spawnY = world.getHeight((int)spawnX, (int)spawnZ);

						trySpawn(world, (float)spawnX, (float)spawnY, (float)spawnZ, new EntityFBI(world));
					}
				}
			}
		}
		
		if(MobConfig.enableRaids) {
			
			if(world.getTotalWorldTime() % MobConfig.raidDelay == 0) {
				
				if(world.rand.nextInt(MobConfig.raidChance) == 0 && !world.playerEntities.isEmpty() && world.provider.isSurfaceWorld()) {
					
					final EntityPlayer player = world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
					
					if(player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getLong("fbiMark") < world.getTotalWorldTime()) {
						player.sendMessage(new TextComponentString("FBI, OPEN UP!").setStyle(new Style().setColor(TextFormatting.RED)));
						
						final Vec3 vec = Vec3.createVectorHelper(MobConfig.raidAttackDistance, 0, 0);
						vec.rotateAroundY((float)(Math.PI * 2) * world.rand.nextFloat());
						
						for(int i = 0; i < MobConfig.raidAmount; i++) {
	
							final double spawnX = player.posX + vec.xCoord + world.rand.nextGaussian() * 5;
							final double spawnZ = player.posZ + vec.zCoord + world.rand.nextGaussian() * 5;
							final double spawnY = world.getHeight((int)spawnX, (int)spawnZ);
							
							trySpawn(world, (float)spawnX, (float)spawnY, (float)spawnZ, new EntityFBI(world));
						}
					}
				}
			}
		}
		
		if(MobConfig.enableElementals) {

			if(world.getTotalWorldTime() % MobConfig.elementalDelay == 0) {

				if(world.rand.nextInt(MobConfig.elementalChance) == 0 && !world.playerEntities.isEmpty() && world.provider.isSurfaceWorld()) {

					final EntityPlayer player = world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));

					if(player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getBoolean("radMark")) {

						player.sendMessage(new TextComponentString("You hear a faint clicking...").setStyle(new Style().setColor(TextFormatting.YELLOW)));
						player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setBoolean("radMark", false);

						final Vec3 vec = Vec3.createVectorHelper(MobConfig.raidAttackDistance, 0, 0);

						for(int i = 0; i < MobConfig.elementalAmount; i++) {

							vec.rotateAroundY((float)(Math.PI * 2) * world.rand.nextFloat());

							final double spawnX = player.posX + vec.xCoord + world.rand.nextGaussian();
							final double spawnZ = player.posZ + vec.zCoord + world.rand.nextGaussian();
							final double spawnY = world.getHeight((int)spawnX, (int)spawnZ);

							final EntityRADBeast rad = new EntityRADBeast(world);

							if(i == 0)
								rad.makeLeader();

							trySpawn(world, (float)spawnX, (float)spawnY, (float)spawnZ, rad);
						}
					}
				}
			}
		}

		if(GeneralConfig.enableMeteorStrikes && !world.isRemote) {
			meteorUpdate(world);
		}
	}
	
	private static void trySpawn(final World world, final float x, final float y, final float z, final EntityLiving e) {

		e.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);
		final Result canSpawn = ForgeEventFactory.canEntitySpawn(e, world, x, y, z, null);

		if (canSpawn == Result.ALLOW || canSpawn == Result.DEFAULT) {
			world.spawnEntity(e);
			ForgeEventFactory.doSpecialSpawn(e, world, x, y, z, null);
			e.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(e)), null);
		}
	}
	
	public static void markFBI(final EntityPlayer player) {
		if(!player.world.isRemote)
			player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setLong("fbiMark", player.world.getTotalWorldTime() + 20 * 60 * 20);
	}
	
	public static int meteorShower = 0;
	private static void meteorUpdate(final World world) {
		final int dimID = world.provider.getDimension();
		final int dimMeteorShowerChance = parseOInt(CompatibilityConfig.meteorShowerChance.get(dimID));
		final int dimMeteorStrikeChance = parseOInt(CompatibilityConfig.meteorStrikeChance.get(dimID));
		if(dimMeteorShowerChance > 0 && dimMeteorStrikeChance > 0){
			if(world.rand.nextInt(meteorShower > 0 ? dimMeteorShowerChance : dimMeteorStrikeChance) == 0) {
				if(!world.playerEntities.isEmpty()) {
					final EntityPlayer p = world.playerEntities.get(world.rand.nextInt(world.playerEntities.size()));
					if(p != null && p.dimension == 0) {
						boolean repell = false;
						boolean strike = true;

						final ItemStack armor = p.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
						if(armor != null && ArmorModHandler.hasMods(armor)) {
							final ItemStack mod = ArmorModHandler.pryMods(armor)[ArmorModHandler.helmet_only];
							
							if(mod != null) {
								if(mod.getItem() == ModItems.protection_charm) {
									repell = true;
								}
								if(mod.getItem() == ModItems.meteor_charm) {
									strike = false;
								}
							}
						}

						if(strike)
							spawnMeteorAtPlayer(p, repell);
					}
				}
			}

			if(meteorShower > 0) {
				meteorShower--;
				if(meteorShower == 0 && GeneralConfig.enableDebugMode)
					MainRegistry.logger.info("Ended meteor shower.");
			}

			if(world.rand.nextInt(dimMeteorStrikeChance * 100) == 0 && GeneralConfig.enableMeteorShowers) {
				final int dimMeteorShowerDuration = parseOInt(CompatibilityConfig.meteorShowerDuration.get(dimID));
				meteorShower = (int) (dimMeteorShowerDuration * (0.75 + 0.25 * world.rand.nextFloat()));

				if(GeneralConfig.enableDebugMode)
					MainRegistry.logger.info("Started meteor shower! Duration: " + meteorShower);
			}
		}
	}

	public static void spawnMeteorAtPlayer(final EntityPlayer p, final boolean repell) {
		final EntityMeteor meteor = new EntityMeteor(p.world);
		meteor.posX = p.posX + p.world.rand.nextInt(201) - 100;
		meteor.posY = 384;
		meteor.posZ = p.posZ + p.world.rand.nextInt(201) - 100;
		if(!repell){
			meteor.motionX = p.world.rand.nextDouble() - 0.5;
			meteor.motionZ = p.world.rand.nextDouble() - 0.5;
		} else {
			meteor.motionX = (p.world.rand.nextDouble() - 0.5) * 3;
			meteor.motionZ = (p.world.rand.nextDouble() - 0.5) * 3;
		}
		meteor.motionY = -10;
		meteor.safe = repell;
		p.world.spawnEntity(meteor);
	}

	private static int parseOInt(final Object o){
		if(o == null)
			return 0;
		return (int)o;
	}
}
