package com.hbm.capability;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.hbm.capability.HbmLivingProps.ContaminationEffect;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class HbmLivingCapability {
	
	public interface IEntityHbmProps {
		public float getRads();
		public void setRads(float rads);
		public void increaseRads(float rads);
		public void decreaseRads(float rads);

		public float getNeutrons();
		public void setNeutrons(float rads);
		
		public float getRadsEnv();
		public void setRadsEnv(float rads);
		
		public float getRadBuf();
		public void setRadBuf(float buf);
		
		public float getDigamma();
		public void setDigamma(float dig);
		public void increaseDigamma(float dig);
		public void decreaseDigamma(float dig);
		
		public int getAsbestos();
		public void setAsbestos(int asbestos);
		
		public int getBlacklung();
		public void setBlacklung(int blacklung);
		
		public int getBombTimer();
		public void setBombTimer(int bombTimer);
		
		public int getContagion();
		public void setContagion(int cont);
		
		public List<ContaminationEffect> getContaminationEffectList();
		
		public void saveNBTData(NBTTagCompound tag);
		public void loadNBTData(NBTTagCompound tag);
	}
	
	public static class EntityHbmProps implements IEntityHbmProps {

		public static final Callable<IEntityHbmProps> FACTORY = () -> {return new EntityHbmProps();};
		
		private float rads = 0;
		private float neutrons = 0;
		private float envRads = 0;
		private float radBuf = 0;
		private float digamma = 0;
		private int asbestos = 0;
		public static final int maxAsbestos = 60 * 60 * 20;
		private int blacklung;
		public static final int maxBlacklung = 60 * 60 * 20;
		private int bombTimer;
		private int contagion;
		private final List<ContaminationEffect> contamination = new ArrayList<>();
		
		@Override
		public float getRads() {
			return rads;
		}

		@Override
		public void setRads(final float rads) {
			this.rads = MathHelper.clamp(rads, 0, 2500);
		}

		@Override
		public float getNeutrons() {
			return neutrons;
		}

		@Override
		public void setNeutrons(final float neutrons) {
			this.neutrons = Math.max(neutrons, 0);
		}
		
		@Override
		public void increaseRads(final float rads){
			this.rads = MathHelper.clamp(this.rads + rads, 0, 2500);
		}
		
		@Override
		public void decreaseRads(final float rads){
			this.rads = MathHelper.clamp(this.rads - rads, 0, 2500);
		}

		@Override
		public float getRadsEnv(){
			return envRads;
		}

		@Override
		public void setRadsEnv(final float rads){
			envRads = rads;
		}

		@Override
		public float getRadBuf(){
			return radBuf;
		}

		@Override
		public void setRadBuf(final float buf){
			radBuf = buf;
		}

		@Override
		public float getDigamma(){
			return digamma;
		}

		@Override
		public void setDigamma(final float dig){
			digamma = dig;
		}

		@Override
		public void increaseDigamma(final float dig){
			this.digamma = MathHelper.clamp(this.digamma + dig, 0, 1000);
		}
		
		@Override
		public void decreaseDigamma(final float dig){
			this.digamma = MathHelper.clamp(this.digamma - dig, 0, 1000);
		}

		@Override
		public int getAsbestos(){
			return asbestos;
		}

		@Override
		public void setAsbestos(final int asbestos){
			this.asbestos = asbestos;
		}

		@Override
		public int getBlacklung(){
			return blacklung;
		}

		@Override
		public void setBlacklung(final int blacklung){
			this.blacklung = blacklung;
		}

		@Override
		public int getBombTimer(){
			return bombTimer;
		}

		@Override
		public void setBombTimer(final int bombTimer){
			this.bombTimer = bombTimer;
		}

		@Override
		public int getContagion(){
			return contagion;
		}

		@Override
		public void setContagion(final int cont){
			contagion = cont;
		}
		
		@Override
		public List<ContaminationEffect> getContaminationEffectList(){
			return contamination;
		}
		
		@Override
		public void saveNBTData(final NBTTagCompound tag){
			tag.setFloat("rads", getRads());
			tag.setFloat("neutrons", getNeutrons());
			tag.setFloat("envRads", getRadsEnv());
			tag.setFloat("radBuf", getRadBuf());
			tag.setFloat("digamma", getDigamma());
			tag.setInteger("asbestos", getAsbestos());
			tag.setInteger("blacklung", blacklung);
			tag.setInteger("bombtimer", bombTimer);
			tag.setInteger("contagion", contagion);
			tag.setInteger("conteffectsize", contamination.size());
			for(int i = 0; i < contamination.size(); i ++){
				contamination.get(i).save(tag, i);
			}
		}

		@Override
		public void loadNBTData(final NBTTagCompound tag){
			setRads(tag.getFloat("rads"));
			setNeutrons(tag.getFloat("neutrons"));
			setRadsEnv(tag.getFloat("envRads"));
			setRadBuf(tag.getFloat("radBuf"));
			setDigamma(tag.getFloat("digamma"));
			setAsbestos(tag.getInteger("asbestos"));
			setBlacklung(tag.getInteger("blacklung"));
			setBombTimer(tag.getInteger("bombtimer"));
			setContagion(tag.getInteger("contagion"));
			contamination.clear();
			for(int i = 0; i < tag.getInteger("conteffectsize"); i ++){
				contamination.add(ContaminationEffect.load(tag, i));
			}
		}
	}
	
	public static class EntityHbmPropsStorage implements IStorage<IEntityHbmProps>{

		@Override
		public NBTBase writeNBT(final Capability<IEntityHbmProps> capability, final IEntityHbmProps instance, final EnumFacing side) {
			final NBTTagCompound tag = new NBTTagCompound();
			instance.saveNBTData(tag);
			return tag;
		}

		@Override
		public void readNBT(final Capability<IEntityHbmProps> capability, final IEntityHbmProps instance, final EnumFacing side, final NBTBase nbt) {
			if(nbt instanceof NBTTagCompound tag){
                instance.loadNBTData(tag);
			}
		}
		
	}
	
	public static class EntityHbmPropsProvider implements ICapabilitySerializable<NBTBase> {

		public static final IEntityHbmProps DUMMY = new IEntityHbmProps(){
			@Override
			public float getRads() {
				return 0;
			}
			@Override
			public void setRads(final float rads) {
			}
			@Override
			public float getNeutrons() {
				return 0;
			}
			@Override
			public void setNeutrons(final float neutrons) {
			}
			@Override
			public void increaseRads(final float rads) {
			}
			@Override
			public void decreaseRads(final float rads) {
			}
			@Override
			public float getRadsEnv(){
				return 0;
			}
			@Override
			public void setRadsEnv(final float rads){
			}
			@Override
			public float getRadBuf(){
				return 0;
			}
			@Override
			public void setRadBuf(final float buf){
			}
			@Override
			public float getDigamma(){
				return 0;
			}
			@Override
			public void setDigamma(final float dig){
			}
			@Override
			public void increaseDigamma(final float dig){
			}
			@Override
			public void decreaseDigamma(final float dig){
			}
			@Override
			public int getAsbestos(){
				return 0;
			}
			@Override
			public void setAsbestos(final int asbestos){
			}
			@Override
			public void saveNBTData(final NBTTagCompound tag){
			}
			@Override
			public void loadNBTData(final NBTTagCompound tag){
			}
			@Override
			public List<ContaminationEffect> getContaminationEffectList(){
				return new ArrayList<>(0);
			}
			@Override
			public int getBlacklung(){
				return 0;
			}
			@Override
			public void setBlacklung(final int blacklung){
			}
			@Override
			public int getBombTimer(){
				return 0;
			}
			@Override
			public void setBombTimer(final int bombTimer){
			}
			@Override
			public int getContagion(){
				return 0;
			}
			@Override
			public void setContagion(final int cont){
			}
		};
		
		@CapabilityInject(IEntityHbmProps.class)
		public static final Capability<IEntityHbmProps> ENT_HBM_PROPS_CAP = null;
		
		private final IEntityHbmProps instance = ENT_HBM_PROPS_CAP.getDefaultInstance();
		
		@Override
		public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
			return capability == ENT_HBM_PROPS_CAP;
		}

		@Override
		public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
			return capability == ENT_HBM_PROPS_CAP ? ENT_HBM_PROPS_CAP.cast(this.instance) : null;
		}

		@Override
		public NBTBase serializeNBT() {
			return ENT_HBM_PROPS_CAP.getStorage().writeNBT(ENT_HBM_PROPS_CAP, instance, null);
		}

		@Override
		public void deserializeNBT(final NBTBase nbt) {
			ENT_HBM_PROPS_CAP.getStorage().readNBT(ENT_HBM_PROPS_CAP, instance, null, nbt);
		}
		
	}
}
