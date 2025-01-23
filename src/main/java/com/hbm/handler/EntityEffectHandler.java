package com.hbm.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.capability.HbmLivingCapability.EntityHbmProps;
import com.hbm.capability.HbmLivingCapability.IEntityHbmProps;
import com.hbm.capability.HbmLivingProps;
import com.hbm.capability.HbmLivingProps.ContaminationEffect;
import com.hbm.config.CompatibilityConfig;
import com.hbm.config.GeneralConfig;
import com.hbm.config.RadiationConfig;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.ExtPropPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.saveddata.AuxSavedData;
import com.hbm.saveddata.RadiationSavedData;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityEffectHandler {
	public static void onUpdate(final EntityLivingBase entity) {
		
		if(!entity.world.isRemote) {
			
			if(entity.ticksExisted % 20 == 0) {
				HbmLivingProps.setRadBuf(entity, HbmLivingProps.getRadEnv(entity));
				HbmLivingProps.setRadEnv(entity, 0);
			}
			
			if(entity instanceof EntityPlayerMP) {
				final NBTTagCompound data = new NBTTagCompound();
				final IEntityHbmProps props = HbmLivingProps.getData(entity);
				props.saveNBTData(data);
				PacketDispatcher.wrapper.sendTo(new ExtPropPacket(data), (EntityPlayerMP) entity);
			}
		}
		
		handleContamination(entity);
		handleContagion(entity);
		handleRadiation(entity);
		handleDigamma(entity);
		handleLungDisease(entity);
	}
	
	private static void handleContamination(final EntityLivingBase entity) {
		
		if(entity.world.isRemote)
			return;
		
		final List<ContaminationEffect> contamination = HbmLivingProps.getCont(entity);
		final List<ContaminationEffect> rem = new ArrayList<>();
		
		for(final ContaminationEffect con : contamination) {
			ContaminationUtil.contaminate(entity, HazardType.RADIATION, con.ignoreArmor ? ContaminationType.RAD_BYPASS : ContaminationType.CREATIVE, con.getRad());
			
			con.time--;
			
			if(con.time <= 0)
				rem.add(con);
		}
		
		contamination.removeAll(rem);
	}
	
	private static void handleRadiation(final EntityLivingBase entity) {
		
		if(ContaminationUtil.isRadImmune(entity))
			return;
		
		final World world = entity.world;
		
		final RadiationSavedData data = RadiationSavedData.getData(world);
		
		if(!world.isRemote) {
			final int ix = MathHelper.floor(entity.posX);
			final int iy = MathHelper.floor(entity.posY);
			final int iz = MathHelper.floor(entity.posZ);

			float rad = data.getRadNumFromCoord(new BlockPos(ix, iy, iz));
			
			final Object dimRad = CompatibilityConfig.dimensionRad.get(world.provider.getDimension());
			if(dimRad != null) {
				if(rad < (float)dimRad) {
					// TODO: Can we use sealed rad pockets to protect against dim rads?
					rad = (float)dimRad;
				}
			}

			if(rad > 0) {
				ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, rad / 20F);
			}
	
			if(entity.world.isRaining() && RadiationConfig.cont > 0 && AuxSavedData.getThunder(entity.world) > 0 && entity.world.canBlockSeeSky(new BlockPos(ix, iy, iz))) {
				
				ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, RadiationConfig.cont * 0.0005F);
			}
			
			if(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode)
				return;
			
			final Random rand = new Random(entity.getEntityId());
			final int r600 = rand.nextInt(600);
			final int r1200 = rand.nextInt(1200);
			
			if(HbmLivingProps.getRadiation(entity) > 600 && (world.getTotalWorldTime() + r600) % 600 < 20 && canVomit(entity)) {
				final NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("type", "vomit");
				nbt.setString("mode", "blood");
				nbt.setInteger("count", 25);
				nbt.setInteger("entity", entity.getEntityId());
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
				
				if((world.getTotalWorldTime() + r600) % 600 == 1) {
					world.playSound(null, ix, iy, iz, HBMSoundHandler.vomit, SoundCategory.NEUTRAL, 1.0F, 1.0F);
					entity.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 60, 19));
				}

			} else if(HbmLivingProps.getRadiation(entity) > 200 && (world.getTotalWorldTime() + r1200) % 1200 < 20 && canVomit(entity)) {
				
				final NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("type", "vomit");
				nbt.setString("mode", "normal");
				nbt.setInteger("count", 15);
				nbt.setInteger("entity", entity.getEntityId());
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
				
				if((world.getTotalWorldTime() + r1200) % 1200 == 1) {
					world.playSound(null, ix, iy, iz, HBMSoundHandler.vomit, SoundCategory.NEUTRAL, 1.0F, 1.0F);
					entity.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 60, 19));
				}
			}
			
			if(HbmLivingProps.getRadiation(entity) > 900 && (world.getTotalWorldTime() + rand.nextInt(10)) % 10 == 0) {
				
				final NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("type", "sweat");
				nbt.setInteger("count", 1);
				nbt.setInteger("block", Block.getIdFromBlock(Blocks.REDSTONE_BLOCK));
				nbt.setInteger("entity", entity.getEntityId());
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
			
			}
		} else {
			final float radiation = HbmLivingProps.getRadiation(entity);
			
			if(entity instanceof EntityPlayer && radiation > 600) {
				
				final NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("type", "radiation");
				nbt.setInteger("count", radiation > 900 ? 4 : radiation > 800 ? 2 : 1);
				MainRegistry.proxy.effectNT(nbt);
			}
		}
	}
	
	private static void handleDigamma(final EntityLivingBase entity) {
		
		if(!entity.world.isRemote) {
			
			final float digamma = HbmLivingProps.getDigamma(entity);
			
			if(digamma < 0.01F)
				return;
			
			final int chance = Math.max(10 - (int)(digamma), 1);
			
			if(chance == 1 || entity.getRNG().nextInt(chance) == 0) {
				
				final NBTTagCompound data = new NBTTagCompound();
				data.setString("type", "sweat");
				data.setInteger("count", 1);
				data.setInteger("block", Block.getIdFromBlock(Blocks.SOUL_SAND));
				data.setInteger("entity", entity.getEntityId());
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
			}
		}
	}
	
	private static void handleContagion(final EntityLivingBase entity) {
		
		final World world = entity.world;
		
		if(!entity.world.isRemote) {
			
			final Random rand = entity.getRNG();
			final int minute = 60 * 20;
			final int hour = 60 * minute;
			
			final int contagion = HbmLivingProps.getContagion(entity);
			
			if(entity instanceof EntityPlayer player) {

                final int randSlot = rand.nextInt(player.inventory.mainInventory.size());
				ItemStack stack = player.inventory.getStackInSlot(randSlot);
				
				if(rand.nextInt(100) == 0) {
					stack = player.inventory.armorInventory.get(rand.nextInt(4));
				}
				
				if(stack != null && !ArmorUtil.checkForHazmatOnly(player) && !ArmorRegistry.hasProtection(player, EntityEquipmentSlot.HEAD, ArmorRegistry.HazardClass.BACTERIA)) {
					
					if(contagion > 0) {
						
						if(!stack.hasTagCompound())
							stack.setTagCompound(new NBTTagCompound());
						if(!stack.getTagCompound().getBoolean("ntmContagion"))
							stack.getTagCompound().setBoolean("ntmContagion", true);
						
					} else {
						
						if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("ntmContagion")) {
							HbmLivingProps.setContagion(player, 3 * hour);
						}
					}
				}
			}
			
			if(contagion > 0) {
				HbmLivingProps.setContagion(entity, contagion - 1);
				
				//aerial transmission only happens once a second 5 minutes into the contagion
				if(contagion < (2 * hour + 55 * minute) && contagion % 20 == 0) {
					
					final double range = entity.isWet() ? 16D : 2D; //avoid rain, just avoid it
					
					final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().grow(range, range, range));
					
					for(final Entity ent : list) {
						
						if(ent instanceof EntityLivingBase living) {
                            if(HbmLivingProps.getContagion(living) <= 0 && !ArmorUtil.checkForHazmatOnly(living) && !ArmorRegistry.hasProtection(living, EntityEquipmentSlot.HEAD, ArmorRegistry.HazardClass.BACTERIA)) {
								HbmLivingProps.setContagion(living, 3 * hour);
							}
						}
						
						if(ent instanceof EntityItem) {
							final ItemStack stack = ((EntityItem)ent).getItem();
							
							if(!stack.hasTagCompound())
								stack.setTagCompound(new NBTTagCompound());
							
							stack.getTagCompound().setBoolean("ntmContagion", true);
						}
					}
				}
				
				//one hour in, add rare and subtle screen fuckery
				if(contagion < 2 * hour && rand.nextInt(1000) == 0) {
					entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 0));
				}
				
				//two hours in, give 'em the full blast
				if(contagion < hour && rand.nextInt(100) == 0) {
					entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 0));
					entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 4));
				}
				
				//T-30 minutes, take damage every 20 seconds
				if(contagion < 30 * minute && rand.nextInt(400) == 0) {
					entity.attackEntityFrom(ModDamageSource.mku, 1F);
				}

				//T-30 minutes, start vomiting
				if(contagion < 30 * minute && (contagion + entity.getEntityId()) % 200 < 20 && canVomit(entity)) {
					final NBTTagCompound nbt = new NBTTagCompound();
					nbt.setString("type", "vomit");
					nbt.setString("mode", "blood");
					nbt.setInteger("count", 25);
					nbt.setInteger("entity", entity.getEntityId());
					PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
					
					if((contagion + entity.getEntityId()) % 200 == 19)
						world.playSound(null, entity.posX, entity.posY, entity.posZ, HBMSoundHandler.vomit, SoundCategory.PLAYERS, 1.0F, 1.0F);
				}
				
				//T-5 minutes, take damage every 5 seconds
				if(contagion < 5 * minute && rand.nextInt(100) == 0) {
					entity.attackEntityFrom(ModDamageSource.mku, 2F);
				}

				//end of contagion, drop dead
				if(contagion == 0) {
					entity.attackEntityFrom(ModDamageSource.mku, 100000F);
				}
			}
		}
	}
	
	private static void handleLungDisease(final EntityLivingBase entity) {
		
		if(entity.world.isRemote)
			return;
		
		if(entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode) {
			HbmLivingProps.setBlackLung(entity, 0);
			HbmLivingProps.setAsbestos(entity, 0);
			
			return;
		} else {
			
			final int bl = HbmLivingProps.getBlackLung(entity);
			
			if(bl > 0 && bl < EntityHbmProps.maxBlacklung * 0.25)
				HbmLivingProps.setBlackLung(entity, HbmLivingProps.getBlackLung(entity) - 1);
		}

		final double blacklung = Math.min(HbmLivingProps.getBlackLung(entity), EntityHbmProps.maxBlacklung);
		final double asbestos = Math.min(HbmLivingProps.getAsbestos(entity), EntityHbmProps.maxAsbestos);
		
		final boolean coughs = blacklung / EntityHbmProps.maxBlacklung > 0.25D || asbestos / EntityHbmProps.maxAsbestos > 0.25D;
		
		if(!coughs)
			return;

		final boolean coughsCoal = blacklung / EntityHbmProps.maxBlacklung > 0.5D;
		final boolean coughsALotOfCoal = blacklung / EntityHbmProps.maxBlacklung > 0.8D;
		final boolean coughsBlood = asbestos / EntityHbmProps.maxAsbestos > 0.75D || blacklung / EntityHbmProps.maxBlacklung > 0.75D;

		final double blacklungDelta = 1D - (blacklung / (double)EntityHbmProps.maxBlacklung);
		final double asbestosDelta = 1D - (asbestos / (double)EntityHbmProps.maxAsbestos);
		
		final double total = 1 - (blacklungDelta * asbestosDelta);
		
		final int freq = Math.max((int) (1000 - 950 * total), 20);
		
		final World world = entity.world;
		final Random rand = new Random(entity.getEntityId());

		if(total > 0.8D) {
			entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 6));
			entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 0));
			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 3));
			if(rand.nextInt(250) == 0)
				entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 2));
		}
		else if(total > 0.65D) {
			entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 2));
			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2));
			if(rand.nextInt(500) == 0)
				entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0));
		} 
		else if(total > 0.45D) {
			entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 1));
			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 1));
		}
		else if(total > 0.25D) {
			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 0));
		}
		
		if(world.getTotalWorldTime() % freq == entity.getEntityId() % freq) {
			world.playSound(null, entity.posX, entity.posY, entity.posZ, HBMSoundHandler.cough, SoundCategory.PLAYERS, 1.0F, 1.0F);
			
			if(coughsBlood) {
				final NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("type", "vomit");
				nbt.setString("mode", "blood");
				nbt.setInteger("count", 5);
				nbt.setInteger("entity", entity.getEntityId());
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
			}
			
			if(coughsCoal) {
				final NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("type", "vomit");
				nbt.setString("mode", "smoke");
				nbt.setInteger("count", coughsALotOfCoal ? 50 : 10);
				nbt.setInteger("entity", entity.getEntityId());
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
			}
		}
	}

	private static boolean canVomit(final Entity e) {
        return !e.isCreatureType(EnumCreatureType.WATER_CREATURE, false);
    }
}
