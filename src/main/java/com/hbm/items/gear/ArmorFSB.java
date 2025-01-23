package com.hbm.items.gear;

import com.hbm.config.PotionConfig;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemGeigerCounter;
import com.hbm.main.ClientProxy;
import com.hbm.packet.KeybindPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.render.RenderHelper;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.I18nUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ArmorFSB extends ItemArmor {

	public static Field nextStepDistance = null;
	public static Field distanceWalkedOnStepModified = null;
	
	@SideOnly(Side.CLIENT)
	public static boolean flashlightPress;
	
	private String texture = "";
	private ResourceLocation overlay = null;
	public List<PotionEffect> effects = new ArrayList<PotionEffect>();
	public HashMap<String, Float> resistance = new HashMap<String, Float>();
	public float blastProtection = -1;
	public float projectileProtection = -1;
	public float damageCap = -1;
	public float damageMod = -1;
	public float damageThreshold = 0;
	public float protectionYield = 50F;
	public boolean fireproof = false;
	public boolean noHelmet = false;
	public boolean vats = false;
	public boolean thermal = false;
	public boolean geigerSound = false;
	public boolean customGeiger = false;
	public boolean hardLanding = false;
	public Vec3d flashlightPosition = null;
	public double gravity = 0;
	public SoundEvent step;
	public SoundEvent jump;
	public SoundEvent fall;
	
	public ArmorFSB(final ArmorMaterial materialIn, final int renderIndexIn, final EntityEquipmentSlot equipmentSlotIn, final String texture, final String name) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.texture = texture;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	public static boolean hasFSBArmor(final EntityLivingBase entity) {
		if(entity == null)
			return false;
		
		final ItemStack plate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		
		if(plate != null && plate.getItem() instanceof ArmorFSB chestplate) {

            final boolean noHelmet = chestplate.noHelmet;

			for(final EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
				if(slot == EntityEquipmentSlot.MAINHAND || slot == EntityEquipmentSlot.OFFHAND)
					continue;
				if(noHelmet && slot == EntityEquipmentSlot.HEAD)
					continue;
				final ItemStack armor = entity.getItemStackFromSlot(slot);

				if(armor == null || !(armor.getItem() instanceof ArmorFSB))
					return false;

				if(((ArmorFSB)armor.getItem()).getArmorMaterial() != chestplate.getArmorMaterial())
					return false;

				if(!((ArmorFSB)armor.getItem()).isArmorEnabled(armor))
					return false;
			}
			return true;
		}

		return false;
    }
	
	public static boolean hasFSBArmorHelmet(final EntityLivingBase entity){
		final ItemStack plate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if(plate != null && plate.getItem() instanceof ArmorFSB) {
			return !((ArmorFSB)plate.getItem()).noHelmet && hasFSBArmor(entity);
		}
		return false;
	}

	public static boolean hasFSBArmorIgnoreCharge(final EntityLivingBase entity) {
		if(entity == null)
			return false;

		final ItemStack plate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		
		if(plate != null && plate.getItem() instanceof ArmorFSB chestplate) {

            final boolean noHelmet = chestplate.noHelmet;
			
			for(final EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
				if(slot == EntityEquipmentSlot.MAINHAND || slot == EntityEquipmentSlot.OFFHAND)
					continue;
				if(noHelmet && slot == EntityEquipmentSlot.HEAD)
					continue;
				final ItemStack armor = entity.getItemStackFromSlot(slot);

				if(armor == null || !(armor.getItem() instanceof ArmorFSB))
					return false;

				if(((ArmorFSB)armor.getItem()).getArmorMaterial() != chestplate.getArmorMaterial())
					return false;
			}
			return true;
		}

		return false;
    }

	
    public static void handleAttack(final LivingAttackEvent event) {

		final EntityLivingBase e = event.getEntityLiving();

		if(ArmorFSB.hasFSBArmor(e)) {

			final ItemStack plate = e.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

			final ArmorFSB chestplate = (ArmorFSB)plate.getItem();
			
			chestplate.handleAttack(event, chestplate);
		}
    }

    public void handleAttack(final LivingAttackEvent event, final ArmorFSB chestplate){
    	if(chestplate.damageThreshold >= event.getAmount() && !event.getSource().isUnblockable()) {
			event.setCanceled(true);
		}

		if(chestplate.fireproof && event.getSource().isFireDamage()) {
			event.getEntityLiving().extinguish();
			event.setCanceled(true);
		}
		if(chestplate.resistance.get(event.getSource().getDamageType()) != null &&
				chestplate.resistance.get(event.getSource().getDamageType()) <= 0) {
			event.setCanceled(true);
		}
    }
	
    public static void handleHurt(final LivingHurtEvent event) {

		final EntityLivingBase e = event.getEntityLiving();

		if(ArmorFSB.hasFSBArmor(e)) {

			final ArmorFSB chestplate = (ArmorFSB)e.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();

			chestplate.handleHurt(event, chestplate);
		}
    }
    
    public void handleHurt(final LivingHurtEvent event, final ArmorFSB chestplate){

    	//store any damage above the yield
		final float overFlow = Math.max(0, event.getAmount() - chestplate.protectionYield);
		//reduce the damage to the yield cap if it exceeds the yield
		event.setAmount(Math.min(event.getAmount(), chestplate.protectionYield));


    	if(!event.getSource().isUnblockable())
			event.setAmount(event.getAmount()-chestplate.damageThreshold);
		
		if(chestplate.damageMod != -1) {
			event.setAmount(event.getAmount()*chestplate.damageMod);
		}

		if(chestplate.resistance.get(event.getSource().getDamageType()) != null) {
			event.setAmount(event.getAmount()*chestplate.resistance.get(event.getSource().getDamageType()));
		}

		if(chestplate.blastProtection != -1 && event.getSource().isExplosion()) {
			event.setAmount(event.getAmount()*chestplate.blastProtection);
		}

		if(chestplate.projectileProtection != -1 && event.getSource().isProjectile()) {
			event.setAmount(event.getAmount()*chestplate.projectileProtection);
		}

		if(chestplate.damageCap != -1) {
			event.setAmount(Math.min(event.getAmount(), chestplate.damageCap));
		}

		//add back anything that was above the protection yield before
		event.setAmount(event.getAmount()+overFlow);
    }
	
	public boolean isArmorEnabled(final ItemStack stack) {
		return true;
	}

	public static void handleTick(final TickEvent.PlayerTickEvent event) {
		handleTick(event.player, event.phase == Phase.START);
	}

	public static void handleTick(final EntityLivingBase entity) {
		handleTick(entity, true);
	}
	
    public static void handleTick(final EntityLivingBase entity, final boolean isStart) {
		if(ArmorFSB.hasFSBArmor(entity)) {

			final ItemStack plate = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

			final ArmorFSB chestplate = (ArmorFSB) plate.getItem();

			if(!chestplate.effects.isEmpty()) {

				for(final PotionEffect i : chestplate.effects) {
					entity.addPotionEffect(new PotionEffect(i.getPotion(), i.getDuration(), i.getAmplifier(), i.getIsAmbient(), i.doesShowParticles()));
				}
			}

			if(!entity.isInWater()){
				if(!(entity instanceof EntityPlayer) || (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isFlying))
					entity.motionY -= chestplate.gravity;
			}
			
			if(chestplate.step != null && entity.world.isRemote && entity.onGround && isStart && !entity.isSneaking()) {

				try {
					if(nextStepDistance == null)
						nextStepDistance = ReflectionHelper.findField(Entity.class, "nextStepDistance", "field_70150_b");
					if(distanceWalkedOnStepModified == null)
						distanceWalkedOnStepModified = ReflectionHelper.findField(Entity.class, "distanceWalkedOnStepModified", "field_82151_R");

					if(entity.getEntityData().getFloat("hfr_nextStepDistance") == 0) {
						entity.getEntityData().setFloat("hfr_nextStepDistance", nextStepDistance.getFloat(entity));
					}

	                final int px = MathHelper.floor(entity.posX);
	                final int py = MathHelper.floor(entity.posY - 0.2D);
	                final int pz = MathHelper.floor(entity.posZ);
	                final IBlockState block = entity.world.getBlockState(new BlockPos(px, py, pz));
					if(block.getMaterial() != Material.AIR && entity.getEntityData().getFloat("hfr_nextStepDistance") <= distanceWalkedOnStepModified.getFloat(entity)){
						entity.playSound(chestplate.step, 1.0F, 1.0F);
					}

					entity.getEntityData().setFloat("hfr_nextStepDistance", nextStepDistance.getFloat(entity));

				} catch (final Exception x) {
					x.printStackTrace();
				}
			}
		}
    }
	
	
	public static void handleJump(final EntityLivingBase entity) {

		if(ArmorFSB.hasFSBArmor(entity)) {

			final ArmorFSB chestplate = (ArmorFSB) entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();

			if(chestplate.jump != null)
				entity.playSound(chestplate.jump, 1.0F, 1.0F);
		}
	}

	public static void handleFall(final EntityLivingBase entity) {

		if(ArmorFSB.hasFSBArmor(entity)) {

			final ArmorFSB chestplate = (ArmorFSB) entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();

			if(chestplate.hardLanding && entity.fallDistance > 10) {

				final List<Entity> entities = entity.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().grow(3, 0, 3));

				for(final Entity e : entities) {

					final Vec3 vec = Vec3.createVectorHelper(entity.posX - e.posX, 0, entity.posZ - e.posZ);

					if(vec.length() < 3) {

						final double intensity = 3 - vec.length();
						e.motionX += vec.xCoord * intensity * -2;
						e.motionY += 0.1D * intensity;
						e.motionZ += vec.zCoord * intensity * -2;

						e.attackEntityFrom(DamageSource.causeIndirectDamage(e, entity).setDamageBypassesArmor(), (float) (intensity * 10));
					}
				}
				// return;
			}
			
			if(chestplate.fall != null && entity.fallDistance > 0.25 && !entity.isSneaking()){
				entity.playSound(chestplate.fall, 1.0F, 1.0F);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateClient(final ItemStack stack, final ArmorFSB fsbarmor, final World world, final Entity entity, final int slot, final boolean selected){
		if(fsbarmor.flashlightPosition != null){
			if(!flashlightPress && ClientProxy.fsbFlashlight.isKeyDown()){
				PacketDispatcher.wrapper.sendToServer(new KeybindPacket(1));
			}
			flashlightPress = ClientProxy.fsbFlashlight.isKeyDown();
		}
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity e, final int itemSlot, final boolean isSelected) {

		if(this.armorType != EntityEquipmentSlot.CHEST || !(e instanceof EntityLivingBase entity))
			return;
        if(!hasFSBArmor(entity))
			return;
		final ArmorFSB fsbarmor = (ArmorFSB) entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
		
		if(world.isRemote){
			updateClient(stack, fsbarmor, world, e, itemSlot, isSelected);
		}
		
		if(!fsbarmor.geigerSound || !(entity instanceof EntityPlayer))
			return;
		
		ItemGeigerCounter.playGeiger(world, (EntityPlayer)entity);
	}
	
	//Drillgon200: This method is literally never called in 1.12 for some unknown reason even though it absolutely looks like it should be.
	//@Override
	//public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
	//	
	//}
	
	//For crazier stuff not possible without hooking the event
    @SideOnly(Side.CLIENT)
	public void handleOverlay(final RenderGameOverlayEvent.Pre event, final EntityPlayer player) { }
	
	public ArmorFSB enableThermalSight(final boolean thermal) {
		this.thermal = thermal;
		return this;
	}
	
	public ArmorFSB setHasGeigerSound(final boolean geiger) {
		this.geigerSound = geiger;
		return this;
	}

	public ArmorFSB setHasCustomGeiger(final boolean geiger) {
		this.customGeiger = geiger;
		return this;
	}
	
	public ArmorFSB setHasHardLanding(final boolean hardLanding) {
		this.hardLanding = hardLanding;
		return this;
	}

	public ArmorFSB setGravity(final double gravity) {
		this.gravity = gravity;
		return this;
	}

	public ArmorFSB setProtectionLevel(final float damageYield) {
		this.protectionYield = damageYield;
		return this;
	}
	
	public ArmorFSB setBlastProtection(final float blastProtection) {
		this.blastProtection = blastProtection;
		return this;
	}

	public ArmorFSB setProjectileProtection(final float projectileProtection) {
		this.projectileProtection = projectileProtection;
		return this;
	}

	public ArmorFSB setStep(final SoundEvent step) {
		this.step = step;
		return this;
	}
	
	public ArmorFSB setJump(final SoundEvent jump) {
		this.jump = jump;
		return this;
	}

	public ArmorFSB setFall(final SoundEvent fall) {
		this.fall = fall;
		return this;
	}
	
	public ArmorFSB addEffect(final PotionEffect effect) {
		if(!PotionConfig.doJumpBoost && effect.getPotion() == MobEffects.JUMP_BOOST)
			return this;
		effects.add(effect);
		return this;
	}
	
	public ArmorFSB addResistance(final String damage, final float mod) {
		resistance.put(damage, mod);
		return this;
	}
	
	public ArmorFSB setCap(final float cap) {
		this.damageCap = cap;
		return this;
	}
	
	public ArmorFSB setMod(final float mod) {
		this.damageMod = mod;
		return this;
	}
	
	public ArmorFSB setThreshold(final float threshold) {
		this.damageThreshold = threshold;
		return this;
	}
	
	public ArmorFSB setFireproof(final boolean fire) {
		this.fireproof = fire;
		return this;
	}
	
	public ArmorFSB setNoHelmet(final boolean noHelmet) {
		this.noHelmet = noHelmet;
		return this;
	}
	
	public ArmorFSB enableVATS(final boolean vats) {
		this.vats = vats;
		return this;
	}
	
	public ArmorFSB enableFlashlight(final Vec3d pos){
		this.flashlightPosition = pos;
		return this;
	}
	
	public ArmorFSB setOverlay(final String path) {
		this.overlay = new ResourceLocation(path);
		return this;
	}
	
	public ArmorFSB cloneStats(final ArmorFSB original) {
		//lists aren't being modified after instantiation, so there's no need to dereference
		this.effects = original.effects;
		this.resistance = original.resistance;
		this.damageCap = original.damageCap;
		this.damageMod = original.damageMod;
		this.damageThreshold = original.damageThreshold;
		this.protectionYield = original.protectionYield;
		this.blastProtection = original.blastProtection;
		this.projectileProtection = original.projectileProtection;
		this.fireproof = original.fireproof;
		this.noHelmet = original.noHelmet;
		this.vats = original.vats;
		this.thermal = original.thermal;
		this.geigerSound = original.geigerSound;
		this.customGeiger = original.customGeiger;
		this.hardLanding = original.hardLanding;
		this.gravity = original.gravity;
		this.step = original.step;
		this.jump = original.jump;
		this.fall = original.fall;
		this.flashlightPosition = original.flashlightPosition;
		//overlay doesn't need to be copied because it's helmet exclusive
		return this;
	}
	
	@Override
	public String getArmorTexture(final ItemStack stack, final Entity entity, final EntityEquipmentSlot slot, final String type) {
		return texture;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add(TextFormatting.GOLD + I18nUtil.resolveKey("armor.fullSetBonus"));
    	
    	if(!effects.isEmpty()) {
    		
    		for(final PotionEffect effect : effects) {
	    		list.add(TextFormatting.AQUA + "  " + I18n.format(effect.getPotion().getName()));
    		}
    	}
    	
    	if(!resistance.isEmpty()) {

        	for(final Entry<String, Float> struct : resistance.entrySet()) {
        		if(struct.getValue() != 0)
        			list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.damageModifier", struct.getValue(), I18n.format(struct.getKey())));
        		else
        			list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.nullDamage", I18n.format(struct.getKey())));
        	}
    	}
    	
    	if(blastProtection != -1) {
    		list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.blastProtection", blastProtection));
    	}

    	if(projectileProtection != -1) {
			list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.projectileProtection", projectileProtection));
		}
    	
    	if(damageCap != -1) {
    		list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.cap", damageCap));
    	}
    	
    	if(damageMod != -1) {
    		list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.modifier", damageMod));
    	}
    	
    	if(damageThreshold > 0) {
    		list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.threshold", damageThreshold));
    	}
    	
    	if(fireproof) {
    		list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.fireproof"));
    	}
    	
    	if(geigerSound) {
    		list.add(TextFormatting.GOLD + "  " + I18nUtil.resolveKey("armor.geigerSound"));
    	}

    	if(customGeiger) {
    		list.add(TextFormatting.GOLD + "  " + I18nUtil.resolveKey("armor.geigerHUD"));
    	}
    	
    	if(vats) {
    		list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.vats"));
    	}
    	
    	if(thermal) {
    		list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.thermal"));
    	}
    	
    	if(hardLanding) {
			list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.hardLanding"));
		}
    	
    	if(gravity != 0) {
    		list.add(TextFormatting.DARK_AQUA + "  " + I18nUtil.resolveKey("armor.gravity", gravity));
    	}

    	if(protectionYield > 0F) {
			list.add(TextFormatting.GREEN + "  " + I18nUtil.resolveKey("armor.yield", protectionYield));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(final ItemStack stack, final EntityPlayer player, final ScaledResolution resolution, final float partialTicks) {
		if(overlay == null)
    		return;
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(overlay);
        RenderHelper.startDrawingTexturedQuads();
        RenderHelper.addVertexWithUV(0.0D, resolution.getScaledHeight(), -90.0D, 0.0D, 1.0D);
        RenderHelper.addVertexWithUV(resolution.getScaledWidth(), resolution.getScaledHeight(), -90.0D, 1.0D, 1.0D);
        RenderHelper.addVertexWithUV(resolution.getScaledWidth(), 0.0D, -90.0D, 1.0D, 0.0D);
        RenderHelper.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        RenderHelper.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
