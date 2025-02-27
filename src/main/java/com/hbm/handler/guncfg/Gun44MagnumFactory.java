package com.hbm.handler.guncfg;

import java.util.ArrayList;

import com.hbm.entity.particle.EntityBSmokeFX;
import com.hbm.entity.projectile.EntityBoxcar;
import com.hbm.entity.projectile.EntityBuilding;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.entity.projectile.EntityDuchessGambit;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.interfaces.IBulletHitBehavior;
import com.hbm.interfaces.IBulletImpactBehavior;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.potion.HbmPotion;
import com.hbm.render.misc.RenderScreenOverlay.Crosshair;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class Gun44MagnumFactory {

	public static GunConfiguration getBaseConfig() {

		final GunConfiguration config = new GunConfiguration();

		config.rateOfFire = 10;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.firingMode = GunConfiguration.FIRE_MANUAL;
		config.reloadDuration = 10;
		config.firingDuration = 0;
		config.ammoCap = 6;
		config.reloadType = GunConfiguration.RELOAD_FULL;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_CLASSIC;
		config.reloadSound = GunConfiguration.RSOUND_REVOLVER;
		config.firingSound = HBMSoundHandler.revolverShootAlt;
		config.reloadSoundEnd = false;

		return config;
	}

	public static GunConfiguration getNovacConfig() {

		final GunConfiguration config = getBaseConfig();

		config.durability = 2500;

		config.name = "IF-18 Horseshoe";
		config.manufacturer = "Ironshod Firearms";
		config.comment.add("Fallout New Vegas wasn't THAT good.");

		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.M44_NORMAL);
		config.config.add(BulletConfigSyncingUtil.M44_AP);
		config.config.add(BulletConfigSyncingUtil.M44_DU);
		config.config.add(BulletConfigSyncingUtil.M44_PHOSPHORUS);
		config.config.add(BulletConfigSyncingUtil.M44_STAR);
		config.config.add(BulletConfigSyncingUtil.CHL_M44);
		config.config.add(BulletConfigSyncingUtil.M44_ROCKET);

		return config;
	}

	public static GunConfiguration getMacintoshConfig() {

		final GunConfiguration config = getBaseConfig();

		config.durability = 4000;

		config.name = "IF-18 Horseshoe Scoped";
		config.manufacturer = "Ironshod Firearms";
		config.comment.add("Poppin' mentats like tic tacs");

		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.M44_PIP);
		config.config.add(BulletConfigSyncingUtil.M44_NORMAL);
		config.config.add(BulletConfigSyncingUtil.M44_AP);
		config.config.add(BulletConfigSyncingUtil.M44_DU);
		config.config.add(BulletConfigSyncingUtil.M44_PHOSPHORUS);
		config.config.add(BulletConfigSyncingUtil.M44_STAR);
		config.config.add(BulletConfigSyncingUtil.CHL_M44);
		config.config.add(BulletConfigSyncingUtil.M44_ROCKET);

		return config;
	}

	public static GunConfiguration getBlackjackConfig() {

		final GunConfiguration config = getBaseConfig();

		config.durability = 4000;
		config.ammoCap = 5;

		config.name = "IF-18 Horseshoe Vanity";
		config.manufacturer = "Ironshod Firearms";
		config.comment.add("Alcoholism is cool!");

		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.M44_BJ);
		config.config.add(BulletConfigSyncingUtil.M44_NORMAL);
		config.config.add(BulletConfigSyncingUtil.M44_AP);
		config.config.add(BulletConfigSyncingUtil.M44_DU);
		config.config.add(BulletConfigSyncingUtil.M44_PHOSPHORUS);
		config.config.add(BulletConfigSyncingUtil.M44_STAR);
		config.config.add(BulletConfigSyncingUtil.CHL_M44);
		config.config.add(BulletConfigSyncingUtil.M44_ROCKET);

		return config;
	}

	public static GunConfiguration getSilverConfig() {

		final GunConfiguration config = getBaseConfig();

		config.durability = 4000;
		config.ammoCap = 6;

		config.name = "IF-18 Horseshoe Silver Storm";
		config.manufacturer = "Ironshod Firearms";
		config.comment.add("Our friendship is based on abusive behaviour");
		config.comment.add("and mutual hate. It's not that complicated.");

		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.M44_SILVER);
		config.config.add(BulletConfigSyncingUtil.M44_NORMAL);
		config.config.add(BulletConfigSyncingUtil.M44_AP);
		config.config.add(BulletConfigSyncingUtil.M44_DU);
		config.config.add(BulletConfigSyncingUtil.M44_PHOSPHORUS);
		config.config.add(BulletConfigSyncingUtil.M44_STAR);
		config.config.add(BulletConfigSyncingUtil.CHL_M44);
		config.config.add(BulletConfigSyncingUtil.M44_ROCKET);

		return config;
	}

	public static GunConfiguration getRedConfig() {

		final GunConfiguration config = getBaseConfig();

		config.durability = 4000;
		config.ammoCap = 64;

		config.name = "IF-18 Horseshoe Bottomless Pit";
		config.manufacturer = "Ironshod Firearms R&D";
		config.comment.add("Explore the other side");
		config.comment.add("...from afar!");

		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.M44_NORMAL);
		config.config.add(BulletConfigSyncingUtil.M44_AP);
		config.config.add(BulletConfigSyncingUtil.M44_DU);
		config.config.add(BulletConfigSyncingUtil.M44_STAR);
		config.config.add(BulletConfigSyncingUtil.CHL_M44);
		config.config.add(BulletConfigSyncingUtil.M44_PIP);
		config.config.add(BulletConfigSyncingUtil.M44_PHOSPHORUS);
		config.config.add(BulletConfigSyncingUtil.M44_BJ);
		config.config.add(BulletConfigSyncingUtil.M44_SILVER);
		config.config.add(BulletConfigSyncingUtil.M44_ROCKET);

		return config;
	}

	public static BulletConfiguration getNoPipConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.ammo_44;
		bullet.dmgMin = 5;
		bullet.dmgMax = 7;

		return bullet;
	}

	public static BulletConfiguration getNoPipAPConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.ammo_44_ap;
		bullet.dmgMin = 7;
		bullet.dmgMax = 10;
		bullet.wear = 15;
		bullet.leadChance = 10;

		return bullet;
	}

	public static BulletConfiguration getNoPipDUConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.ammo_44_du;
		bullet.dmgMin = 7;
		bullet.dmgMax = 10;
		bullet.wear = 25;
		bullet.leadChance = 50;

		return bullet;
	}

	public static BulletConfiguration getNoPipStarConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.ammo_44_star;
		bullet.dmgMin = 14;
		bullet.dmgMax = 20;
		bullet.wear = 25;
		bullet.leadChance = 100;

		return bullet;
	}

	public static BulletConfiguration getPipConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.ammo_44_pip;
		bullet.dmgMin = 4;
		bullet.dmgMax = 5;
		bullet.wear = 25;
		bullet.doesPenetrate = false;

		bullet.bHit = new IBulletHitBehavior() {

			@Override
			public void behaveEntityHit(final EntityBulletBase bullet, final Entity hit) {

				if(!bullet.world.isRemote) {
					final EntityBoxcar pippo = new EntityBoxcar(bullet.world);
					pippo.posX = hit.posX;
					pippo.posY = hit.posY + 50;
					pippo.posZ = hit.posZ;

					for(int j = 0; j < 50; j++) {
						final EntityBSmokeFX fx = new EntityBSmokeFX(bullet.world, pippo.posX + (bullet.world.rand.nextDouble() - 0.5) * 4, pippo.posY + (bullet.world.rand.nextDouble() - 0.5) * 12, pippo.posZ + (bullet.world.rand.nextDouble() - 0.5) * 4, 0, 0, 0);
						bullet.world.spawnEntity(fx);
					}
					bullet.world.spawnEntity(pippo);

					bullet.world.playSound(null, pippo.posX, pippo.posY + 50, pippo.posZ, HBMSoundHandler.trainHorn, SoundCategory.PLAYERS, 100F, 1F);
				}
			}
		};

		return bullet;
	}

	public static BulletConfiguration getBJConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.ammo_44_bj;
		bullet.dmgMin = 4;
		bullet.dmgMax = 5;
		bullet.wear = 25;
		bullet.doesPenetrate = false;

		bullet.bHit = new IBulletHitBehavior() {

			@Override
			public void behaveEntityHit(final EntityBulletBase bullet, final Entity hit) {

				if(!bullet.world.isRemote) {
					final EntityDuchessGambit pippo = new EntityDuchessGambit(bullet.world);
					pippo.posX = hit.posX;
					pippo.posY = hit.posY + 50;
					pippo.posZ = hit.posZ;

					for(int j = 0; j < 150; j++) {
						final EntityBSmokeFX fx = new EntityBSmokeFX(bullet.world, pippo.posX + (bullet.world.rand.nextDouble() - 0.5) * 7, pippo.posY + (bullet.world.rand.nextDouble() - 0.5) * 8, pippo.posZ + (bullet.world.rand.nextDouble() - 0.5) * 18, 0, 0, 0);
						bullet.world.spawnEntity(fx);
					}
					bullet.world.spawnEntity(pippo);

					bullet.world.playSound(null, pippo.posX, pippo.posY + 50, pippo.posZ, HBMSoundHandler.boatWeapon, SoundCategory.PLAYERS, 100F, 1F);
				}
			}

		};

		return bullet;
	}

	public static BulletConfiguration getSilverStormConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.ammo_44_silver;
		bullet.dmgMin = 4;
		bullet.dmgMax = 5;
		bullet.wear = 25;
		bullet.doesPenetrate = false;

		bullet.bHit = new IBulletHitBehavior() {

			@Override
			public void behaveEntityHit(final EntityBulletBase bullet, final Entity hit) {

				if(!bullet.world.isRemote) {
					final EntityBuilding pippo = new EntityBuilding(bullet.world);
					pippo.posX = hit.posX;
					pippo.posY = hit.posY + 50;
					pippo.posZ = hit.posZ;

					for(int j = 0; j < 150; j++) {
						final EntityBSmokeFX fx = new EntityBSmokeFX(bullet.world, pippo.posX + (bullet.world.rand.nextDouble() - 0.5) * 15, pippo.posY + (bullet.world.rand.nextDouble() - 0.5) * 15, pippo.posZ + (bullet.world.rand.nextDouble() - 0.5) * 15, 0, 0, 0);
						bullet.world.spawnEntity(fx);
					}
					bullet.world.spawnEntity(pippo);

					bullet.world.playSound(null, pippo.posX, pippo.posY + 50, pippo.posZ, HBMSoundHandler.blockDebris, SoundCategory.PLAYERS, 100F, 1F);
				}
			}

		};

		return bullet;
	}

	public static BulletConfiguration getRocketConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardRocketConfig();

		bullet.ammo = ModItems.ammo_44_rocket;
		bullet.velocity = 5;
		bullet.explosive = 15F;
		bullet.trail = 1;

		return bullet;
	}
	
	public static BulletConfiguration getPhosphorusConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.ammo_44_phosphorus;
		bullet.dmgMin = 5;
		bullet.dmgMax = 7;
		bullet.wear = 15;
		bullet.incendiary = 5;
		bullet.doesPenetrate = false;
		
		final PotionEffect eff = new PotionEffect(HbmPotion.phosphorus, 20 * 20, 0, true, false);
		eff.getCurativeItems().clear();
		bullet.effects = new ArrayList<PotionEffect>();
		bullet.effects.add(new PotionEffect(eff));
		
		bullet.bImpact = new IBulletImpactBehavior() {

			@Override
			public void behaveBlockHit(final EntityBulletBase bullet, final int x, final int y, final int z) {
				
				final NBTTagCompound data = new NBTTagCompound();
				data.setString("type", "vanillaburst");
				data.setString("mode", "flame");
				data.setInteger("count", 15);
				data.setDouble("motion", 0.05D);
				
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, bullet.posX, bullet.posY, bullet.posZ), new TargetPoint(bullet.dimension, bullet.posX, bullet.posY, bullet.posZ, 50));
			}
		};
		
		return bullet;
	}
}
