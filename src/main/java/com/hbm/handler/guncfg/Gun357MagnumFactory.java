package com.hbm.handler.guncfg;

import java.util.ArrayList;

import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.potion.HbmPotion;
import com.hbm.render.misc.RenderScreenOverlay.Crosshair;

import net.minecraft.potion.PotionEffect;

public class Gun357MagnumFactory {

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
		config.firingSound = HBMSoundHandler.revolverShoot;
		config.reloadSoundEnd = false;
		
		return config;
	}
	
	public static GunConfiguration getRevolverIronConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 2000;
		
		config.name = "FFI Viper";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.IRON_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 3500;
		
		config.name = "FFI Viper Inox";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.STEEL_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverSaturniteConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 3500;
		
		config.name = "FFI Viper D-25A";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SATURNITE_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverLeadConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 2000;
		
		config.name = "FFI Viper Lead";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.LEAD_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverGoldConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 2500;
		
		config.name = "FFI Viper Bling";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.GOLD_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverCursedConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.rateOfFire = 7;
		config.ammoCap = 17;
		config.durability = 5000;
		config.firingSound = HBMSoundHandler.heavyShoot;
		
		config.name = "Britannia Standard Issue Motorized Handgun";
		config.manufacturer = "BAE Systems plc";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.CURSED_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverSchrabidiumConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 7500;
		config.firingSound = HBMSoundHandler.schrabidiumShoot;
		
		config.name = "FFI Viper Ultra";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.SCHRABIDIUM_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverNightmareConfig() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 4000;
		config.firingSound = HBMSoundHandler.schrabidiumShoot;
		
		config.name = "FFI Viper N1";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.NIGHT_REVOLVER);
		config.config.add(BulletConfigSyncingUtil.DESH_REVOLVER);
		
		return config;
	}
	
	public static GunConfiguration getRevolverNightmare2Config() {
		
		final GunConfiguration config = getBaseConfig();
		
		config.durability = 4000;
		config.firingSound = HBMSoundHandler.schrabidiumShoot;
		config.crosshair = Crosshair.NONE;
		
		config.name = "FFI Viper N2";
		config.manufacturer = "FlimFlam Industries";
		
		config.config = new ArrayList<Integer>();
		config.config.add(BulletConfigSyncingUtil.NIGHT2_REVOLVER);
		
		return config;
	}
	
	    ////    //  //  //      //      //////  //////  //////
	   //  //  //  //  //      //      //        //    //
	  ////    //  //  //      //      ////      //    //////
	 //  //  //  //  //      //      //        //        //
	////    //////  //////  //////  //////    //    //////
	
	public static BulletConfiguration getRevIronConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_iron_ammo;
		bullet.dmgMin = 2;
		bullet.dmgMax = 4;
		
		return bullet;
	}
	
	public static BulletConfiguration getRevSteelConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_ammo;
		bullet.dmgMin = 3;
		bullet.dmgMax = 5;
		
		return bullet;
	}
	
	public static BulletConfiguration getRevLeadConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_lead_ammo;
		bullet.dmgMin = 2;
		bullet.dmgMax = 3;
		
		bullet.effects = new ArrayList<PotionEffect>();
		bullet.effects.add(new PotionEffect(HbmPotion.radiation, 10 * 20, 4));
		
		return bullet;
	}
	
	public static BulletConfiguration getRevGoldConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_gold_ammo;
		bullet.dmgMin = 10;
		bullet.dmgMax = 15;
		
		return bullet;
	}
	
	public static BulletConfiguration getRevDeshConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.ammo_357_desh;
		bullet.dmgMin = 15;
		bullet.dmgMax = 17;
		
		return bullet;
	}
	
	public static BulletConfiguration getRevSchrabidiumConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_schrabidium_ammo;
		bullet.dmgMin = 1000000;
		bullet.dmgMax = 10000000;
		// bullet.instakill = true;
		
		return bullet;
	}
	
	public static BulletConfiguration getRevCursedConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_cursed_ammo;
		bullet.dmgMin = 12;
		bullet.dmgMax = 15;
		
		return bullet;
	}
	
	public static BulletConfiguration getRevNightmareConfig() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_nightmare_ammo;
		bullet.dmgMin = 1;
		bullet.dmgMax = 50;
		
		return bullet;
	}
	
	public static BulletConfiguration getRevNightmare2Config() {
		
		final BulletConfiguration bullet = BulletConfigFactory.standardBulletConfig();
		
		bullet.ammo = ModItems.gun_revolver_nightmare2_ammo;
		bullet.spread *= 10;
		bullet.bulletsMin = 4;
		bullet.bulletsMax = 6;
		bullet.dmgMin = 50;
		bullet.dmgMax = 150;
		bullet.destroysBlocks = true;
		bullet.style = BulletConfiguration.STYLE_BOLT;
		bullet.trail = BulletConfiguration.BOLT_NIGHTMARE;
		
		return bullet;
	}
}
