package com.hbm.handler.guncfg;

import java.util.ArrayList;

import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.render.anim.BusAnimation;
import com.hbm.render.anim.BusAnimationKeyframe;
import com.hbm.render.anim.BusAnimationSequence;
import com.hbm.render.anim.HbmAnimations.AnimType;
import com.hbm.render.misc.RenderScreenOverlay.Crosshair;

public class GunGaussFactory {
	
	public static GunConfiguration getXVLConfig() {
		
		final GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 6;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.firingMode = GunConfiguration.FIRE_AUTO;
		config.reloadDuration = 20;
		config.firingDuration = 0;
		config.ammoCap = 0;
		config.reloadType = GunConfiguration.RELOAD_NONE;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_RAD;
		config.durability = 6000;
		config.firingSound = HBMSoundHandler.tauShoot;
		
		config.animations.put(AnimType.CYCLE, new BusAnimation().addBus("RECOIL", new BusAnimationSequence()
				.addKeyframe(new BusAnimationKeyframe(0, 90, -4, 50))
				.addKeyframe(new BusAnimationKeyframe(0, -10, 1, 200))
				.addKeyframe(new BusAnimationKeyframe(0, 0, 0, 100))));
		config.animations.put(AnimType.ALT_CYCLE, new BusAnimation().addBus("RECOIL", new BusAnimationSequence()
				.addKeyframe(new BusAnimationKeyframe(0, 90, -4, 50))
				.addKeyframe(new BusAnimationKeyframe(0, 45, -3, 275))
				.addKeyframe(new BusAnimationKeyframe(0, 0, -0.25, 250))
				.addKeyframe(new BusAnimationKeyframe(0, 0, 0, 100))));
		config.animations.put(AnimType.SPINUP, new BusAnimation().addBus("SPIN", new BusAnimationSequence()
				.addKeyframe(new BusAnimationKeyframe(1000000, -1, 0, 20000))));
		
		config.name = "XVL1456 Tau Cannon";
		config.manufacturer = "Black Mesa Research Facility";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SPECIAL_GAUSS);
		
		return config;
	}
	
	public static GunConfiguration getChargedConfig() {
		
		final GunConfiguration config = new GunConfiguration();
		
		config.rateOfFire = 10;
		config.roundsPerCycle = 1;
		config.gunMode = GunConfiguration.MODE_NORMAL;
		config.reloadDuration = 1;
		config.firingDuration = 0;
		config.ammoCap = 0;
		config.reloadType = GunConfiguration.RELOAD_NONE;
		config.allowsInfinity = true;
		config.crosshair = Crosshair.L_ARROWS;
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SPECIAL_GAUSS_CHARGED);
		
		return config;
	}

	public static BulletConfiguration getGaussConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_xvl1456_ammo;
		bullet.dmgMin = 6;
		bullet.dmgMax = 9;
		bullet.trail = 1;
		bullet.vPFX = "fireworksSpark";
		bullet.LBRC = 80;
		bullet.HBRC = 5;
		
		return bullet;
	}
	
	public static BulletConfiguration getAltConfig() {
		
		final BulletConfiguration bullet = getGaussConfig();
		
		bullet.vPFX = "reddust";
		
		return bullet;
	}
}
