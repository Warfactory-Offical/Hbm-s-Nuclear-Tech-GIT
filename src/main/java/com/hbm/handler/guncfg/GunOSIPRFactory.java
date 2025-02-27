package com.hbm.handler.guncfg;

import java.util.ArrayList;

import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.render.misc.RenderScreenOverlay.Crosshair;

public class GunOSIPRFactory {
	
	public static GunConfiguration getOSIPRConfig() {
		
		final GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 2;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.firingMode = GunConfiguration.FIRE_AUTO;
		config.reloadDuration = 20;
		config.firingDuration = 0;
		config.ammoCap = 30;
		config.reloadType = GunConfiguration.RELOAD_FULL;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_ARROWS;
		config.durability = 10000;
		config.reloadSound = HBMSoundHandler.osiprReload;
		config.firingSound = HBMSoundHandler.osiprShoot;
		config.reloadSoundEnd = false;
		
		config.name = "Overwatch Standard Issue Pulse Rifle";
		config.manufacturer = "The Universal Union";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SPECIAL_OSIPR);
		
		return config;
	}
	
	public static GunConfiguration getAltConfig() {
		
		final GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 15;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.firingMode = GunConfiguration.FIRE_MANUAL;
		config.reloadDuration = 20;
		config.firingDuration = 0;
		config.ammoCap = 0;
		config.reloadType = GunConfiguration.RELOAD_NONE;
		config.allowsInfinity = true;
		config.firingSound = HBMSoundHandler.singFlyby;
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SPECIAL_OSIPR_CHARGED);
		
		return config;
	}

	static float inaccuracy = 5;
	public static BulletConfiguration getPulseConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_osipr_ammo;
		bullet.spread *= inaccuracy;
		bullet.dmgMin = 3;
		bullet.dmgMax = 5;
		bullet.trail = 2;
		
		return bullet;
	}
	
	public static BulletConfiguration getPulseChargedConfig() {

		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();

		bullet.ammo = ModItems.gun_osipr_ammo2;

		return bullet;
	}
}
