package com.hbm.lib;

import com.hbm.entity.projectile.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class ModDamageSource extends DamageSource {
    public static DamageSource nuclearBlast = (new DamageSource("nuclearBlast")).setExplosion();
    public static DamageSource blast = (new DamageSource("blast")).setExplosion();
    public static DamageSource mudPoisoning = (new DamageSource("mudPoisoning")).setDamageBypassesArmor();
    public static DamageSource acid = (new DamageSource("acid")).setDamageBypassesArmor();
    public static DamageSource euthanizedSelf = (new DamageSource("euthanizedSelf")).setDamageBypassesArmor();
    public static DamageSource euthanizedSelf2 = (new DamageSource("euthanizedSelf2")).setDamageBypassesArmor();
    public static DamageSource tauBlast = (new DamageSource("tauBlast")).setDamageBypassesArmor().setDamageIsAbsolute();
    public static DamageSource digamma = (new DamageSource("digamma")).setDamageIsAbsolute().setDamageBypassesArmor().setDamageAllowedInCreativeMode();
    public static DamageSource radiation = (new DamageSource("radiation")).setDamageBypassesArmor();
    public static DamageSource suicide = (new DamageSource("suicide")).setProjectile();
    public static DamageSource teleporter = (new DamageSource("teleporter")).setDamageIsAbsolute();
    public static DamageSource cheater = (new DamageSource("cheater")).setDamageIsAbsolute().setDamageBypassesArmor().setDamageAllowedInCreativeMode();
    public static DamageSource rubble = (new DamageSource("rubble")).setProjectile();
    public static DamageSource shrapnel = (new DamageSource("shrapnel")).setProjectile();
    public static DamageSource blackhole = (new DamageSource("blackhole")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource turbofan = (new DamageSource("blender")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource meteorite = (new DamageSource("meteorite")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource boxcar = (new DamageSource("boxcar")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource boat = (new DamageSource("boat")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource building = (new DamageSource("building")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource taint = (new DamageSource("taint")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource ams = (new DamageSource("ams")).setDamageIsAbsolute().setDamageBypassesArmor().setDamageAllowedInCreativeMode();
    public static DamageSource amsCore = (new DamageSource("amsCore")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource broadcast = (new DamageSource("broadcast")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource bang = (new DamageSource("bang")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource pc = (new DamageSource("pc")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource cloud = (new DamageSource("cloud")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource lead = (new DamageSource("lead")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource enervation = (new DamageSource("enervation")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource electricity = (new DamageSource("electricity")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource exhaust = (new DamageSource("exhaust")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource spikes = (new DamageSource("spikes")).setDamageBypassesArmor();
    public static DamageSource lunar = (new DamageSource("lunar")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource gluon = new DamageSource("gluon").setDamageIsAbsolute().setDamageBypassesArmor().setDamageAllowedInCreativeMode();
    public static DamageSource slicer = new DamageSource("slicer").setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource crucible = new DamageSource("crucible").setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource monoxide = (new DamageSource("monoxide")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource asbestos = (new DamageSource("asbestos")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource blacklung = (new DamageSource("blacklung")).setDamageIsAbsolute().setDamageBypassesArmor();
    public static DamageSource mku = (new DamageSource("mku")).setDamageIsAbsolute().setDamageBypassesArmor();
	
	public ModDamageSource(final String p_i1566_1_) {
		super(p_i1566_1_);
	}
    public static DamageSource causeBulletDamage(final EntityBullet p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("revolverBullet", p_76353_0_, p_76353_1_)).setProjectile();
    }
    public static DamageSource causeBulletDamage(final EntityBulletBase base, final Entity ent)
    {
        return (new EntityDamageSourceIndirect("revolverBullet", base, ent)).setProjectile();
    }
    public static DamageSource causeBulletGibDamage(final EntityBulletBase base, final Entity ent)
    {
        return (new EntityDamageSourceIndirect("gunGib", base, ent)).setProjectile();
    }
    public static DamageSource causeDisplacementDamage(final EntityBullet p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("chopperBullet", p_76353_0_, p_76353_1_)).setProjectile();
    }
    public static DamageSource causeTauDamage(final Entity p_76353_0_, final Entity p_76353_1_)
    {
        return new EntityDamageSourceIndirect("tau", p_76353_0_, p_76353_1_).setProjectile().setDamageBypassesArmor().setDamageIsAbsolute();
    }
    public static DamageSource causeCombineDamage(final Entity p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("cmb", p_76353_0_, p_76353_1_)).setProjectile().setDamageBypassesArmor();
    }
    public static DamageSource causeSubatomicDamage(final EntityRainbow p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("subAtomic", p_76353_0_, p_76353_1_)).setProjectile().setDamageBypassesArmor();
    }
    public static DamageSource causeSubatomicDamage2(final EntityRainbow p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("subAtomic2", p_76353_0_, p_76353_1_)).setProjectile().setDamageBypassesArmor();
    }
    public static DamageSource causeSubatomicDamage3(final EntityRainbow p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("subAtomic3", p_76353_0_, p_76353_1_)).setProjectile().setDamageBypassesArmor();
    }
    public static DamageSource causeSubatomicDamage4(final EntityRainbow p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("subAtomic4", p_76353_0_, p_76353_1_)).setProjectile().setDamageBypassesArmor();
    }
    public static DamageSource causeSubatomicDamage5(final EntityRainbow p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("subAtomic5", p_76353_0_, p_76353_1_)).setProjectile().setDamageBypassesArmor();
    }
    public static DamageSource euthanized(final Entity p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("euthanized", p_76353_0_, p_76353_1_)).setDamageBypassesArmor();
    }
    public static DamageSource causeDischargeDamage(final EntityDischarge p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("electrified", p_76353_0_, p_76353_1_)).setDamageBypassesArmor();
    }
    public static DamageSource causeFireDamage(final EntityFire p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("flamethrower", p_76353_0_, p_76353_1_)).setFireDamage().setDamageBypassesArmor();
    }
    public static DamageSource causePlasmaDamage(final EntityPlasmaBeam p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("plasma", p_76353_0_, p_76353_1_)).setDamageBypassesArmor();
    }
    public static DamageSource causeIceDamage(final EntityLN2 p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("ice", p_76353_0_, p_76353_1_)).setDamageBypassesArmor();
    }
    public static DamageSource causeLaserDamage(final EntityLaserBeam p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("laser", p_76353_0_, p_76353_1_)).setDamageBypassesArmor();
    }
    public static DamageSource causeLaserDamage(final EntityMinerBeam p_76353_0_, final Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("laser", p_76353_0_, p_76353_1_)).setDamageBypassesArmor();
    }
    
    public static boolean getIsBullet(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("revolverBullet");
    	}
    	return false;
    }
    
    public static boolean getIsEmplacer(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("chopperBullet");
    	}
    	return false;
    }
    
    public static boolean getIsTau(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("tau");
    	}
    	return false;
    }
    
    public static boolean getIsPoison(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("euthanized");
    	}
    	return false;
    }
    
    public static boolean getIsCmb(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("cmb");
    	}
    	return false;
    }
    
    public static boolean getIsSubatomic(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		final String s = source.damageType;
    		return s.equals("subAtomic") || s.equals("subAtomic2") || s.equals("subAtomic3") || s.equals("subAtomic4") || s.equals("subAtomic5");
    	}
    	return false;
    }
    
    public static boolean getIsDischarge(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("electrified");
    	}
    	return false;
    }
    
    public static boolean getIsFire(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("flamethrower");
    	}
    	return false;
    }
    
    public static boolean getIsPlasma(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("plasma");
    	}
    	return false;
    }
    
    public static boolean getIsLiquidNitrogen(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("ice");
    	}
    	return false;
    }
    
    public static boolean getIsLaser(final DamageSource source) {
    	if(source instanceof EntityDamageSourceIndirect)
    	{
    		return source.damageType.equals("laser");
    	}
    	return false;
    }
}
