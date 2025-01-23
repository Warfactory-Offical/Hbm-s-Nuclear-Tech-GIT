package com.hbm.capability;

import java.util.List;
import java.util.UUID;

import com.hbm.interfaces.IItemHazard;
import com.hbm.capability.HbmLivingCapability.EntityHbmProps;
import com.hbm.capability.HbmLivingCapability.IEntityHbmProps;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.AdvancementManager;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class HbmLivingProps {

	public static final UUID digamma_UUID = UUID.fromString("2a3d8aec-5ab9-4218-9b8b-ca812bdf378b");

	public static IEntityHbmProps getData(final EntityLivingBase entity){
		return entity.hasCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null) ? entity.getCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null) : HbmLivingCapability.EntityHbmPropsProvider.DUMMY;
	}

	/// RADIATION ///
	public static float getRadiation(final EntityLivingBase entity){
		return getData(entity).getRads();
	}

	public static void setRadiation(final EntityLivingBase entity, final float rad){
		getData(entity).setRads(rad);
	}

	public static void incrementRadiation(final EntityLivingBase entity, final float rad){
		float radiation = getRadiation(entity) + rad;

		if(radiation > 25000000)
			radiation = 25000000;
		if(radiation < 0)
			radiation = 0;

		setRadiation(entity, radiation);
	}

	// Neutron Radiation

	public static float getNeutron(final EntityLivingBase entity){
		return getData(entity).getNeutrons();
	}

	public static void setNeutron(final EntityLivingBase entity, final float rad){
		getData(entity).setNeutrons(rad);
	}


	/// RAD ENV ///
	public static float getRadEnv(final EntityLivingBase entity){
		return getData(entity).getRadsEnv();
	}

	public static void setRadEnv(final EntityLivingBase entity, final float rad){
		getData(entity).setRadsEnv(rad);
	}

	/// RAD BUF ///
	public static float getRadBuf(final EntityLivingBase entity){
		return getData(entity).getRadBuf();
	}

	public static void setRadBuf(final EntityLivingBase entity, final float rad){
		getData(entity).setRadBuf(rad);
	}

	/// DIGAMA ///
	public static float getDigamma(final EntityLivingBase entity){
		return getData(entity).getDigamma();
	}

	public static void setDigamma(final EntityLivingBase entity, final float digamma){

		getData(entity).setDigamma(digamma);

		final float healthMod = (float)Math.pow(0.5, digamma) - 1F;

		final IAttributeInstance attributeinstance = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

		try {
			attributeinstance.removeModifier(attributeinstance.getModifier(digamma_UUID));
		} catch(final Exception ex) {
		}

		attributeinstance.applyModifier(new AttributeModifier(digamma_UUID, "digamma", healthMod, 2));

		if(entity.getHealth() > entity.getMaxHealth()) {
			entity.setHealth(entity.getMaxHealth());
		}

		if((entity.getMaxHealth() <= 0 || digamma >= 10.0F) && entity.isEntityAlive()) {
			entity.setAbsorptionAmount(0);
			entity.attackEntityFrom(ModDamageSource.digamma, 5000000F);
			entity.setHealth(0);
			entity.onDeath(ModDamageSource.digamma);

			final NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "sweat");
			data.setInteger("count", 50);
			data.setInteger("block", Block.getIdFromBlock(Blocks.SOUL_SAND));
			data.setInteger("entity", entity.getEntityId());
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, 0, 0, 0), new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 50));
		}

		if(entity instanceof EntityPlayer) {

			final float di = getData(entity).getDigamma();

			if(di > 0F)
				AdvancementManager.grantAchievement(((EntityPlayer)entity), AdvancementManager.digammaSee);
			if(di >= 2F)
				AdvancementManager.grantAchievement(((EntityPlayer)entity), AdvancementManager.digammaFeel);
			if(di >= 10F)
				AdvancementManager.grantAchievement(((EntityPlayer)entity), AdvancementManager.digammaKnow);
		}
	}

	public static void incrementDigamma(final EntityLivingBase entity, final float digamma){
		float dRad = getDigamma(entity) + digamma;

		if(dRad > 10)
			dRad = 10;
		if(dRad < 0)
			dRad = 0;

		setDigamma(entity, dRad);
	}

	/// ASBESTOS ///
	public static int getAsbestos(final EntityLivingBase entity){
		return getData(entity).getAsbestos();
	}

	public static void setAsbestos(final EntityLivingBase entity, final int asbestos){
		getData(entity).setAsbestos(asbestos);

		if(asbestos >= EntityHbmProps.maxAsbestos) {
			getData(entity).setAsbestos(0);
			entity.attackEntityFrom(ModDamageSource.asbestos, 1000);
		}
	}

	public static void incrementAsbestos(final EntityLivingBase entity, final int asbestos){
		setAsbestos(entity, getAsbestos(entity) + asbestos);
	}

	public static void addCont(final EntityLivingBase entity, final ContaminationEffect cont){
		getData(entity).getContaminationEffectList().add(cont);
	}

	/// BLACK LUNG DISEASE ///
	public static int getBlackLung(final EntityLivingBase entity){
		return getData(entity).getBlacklung();
	}

	public static void setBlackLung(final EntityLivingBase entity, final int blacklung){
		getData(entity).setBlacklung(blacklung);

		if(blacklung >= EntityHbmProps.maxBlacklung) {
			getData(entity).setBlacklung(0);
			entity.attackEntityFrom(ModDamageSource.blacklung, 1000);
		}
	}

	public static void incrementBlackLung(final EntityLivingBase entity, final int blacklung){
		setBlackLung(entity, getBlackLung(entity) + blacklung);
	}

	/// TIME BOMB ///
	public static int getTimer(final EntityLivingBase entity){
		return getData(entity).getBombTimer();
	}

	public static void setTimer(final EntityLivingBase entity, final int bombTimer){
		getData(entity).setBombTimer(bombTimer);
	}

	/// CONTAGION ///
	public static int getContagion(final EntityLivingBase entity){
		return getData(entity).getContagion();
	}

	public static void setContagion(final EntityLivingBase entity, final int contageon){
		getData(entity).setContagion(contageon);
	}

	public static List<ContaminationEffect> getCont(final EntityLivingBase e){
		return getData(e).getContaminationEffectList();
	}

	public static class ContaminationEffect {

		public float maxRad;
		public int maxTime;
		public int time;
		public boolean ignoreArmor;

		public ContaminationEffect(final float rad, final int time, final boolean ignoreArmor){
			this.maxRad = rad;
			this.maxTime = this.time = time;
			this.ignoreArmor = ignoreArmor;
		}

		public float getRad(){
			return maxRad * ((float)time / (float)maxTime);
		}

		public void save(final NBTTagCompound nbt, final int index){
			final NBTTagCompound me = new NBTTagCompound();
			me.setFloat("maxRad", this.maxRad);
			me.setInteger("maxTime", this.maxTime);
			me.setInteger("time", this.time);
			me.setBoolean("ignoreArmor", ignoreArmor);
			nbt.setTag("cont_" + index, me);
		}

		public static ContaminationEffect load(final NBTTagCompound nbt, final int index){
			final NBTTagCompound me = (NBTTagCompound)nbt.getTag("cont_" + index);
			final float maxRad = me.getFloat("maxRad");
			final int maxTime = nbt.getInteger("maxTime");
			final int time = nbt.getInteger("time");
			final boolean ignoreArmor = nbt.getBoolean("ignoreArmor");

			final ContaminationEffect effect = new ContaminationEffect(maxRad, maxTime, ignoreArmor);
			effect.time = time;
			return effect;
		}
	}
}
