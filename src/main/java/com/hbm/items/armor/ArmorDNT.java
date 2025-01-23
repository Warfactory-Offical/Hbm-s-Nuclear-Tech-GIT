package com.hbm.items.armor;

import com.google.common.collect.Multimap;
import com.hbm.capability.HbmCapability;
import com.hbm.capability.HbmCapability.IHBMData;
import com.hbm.handler.ArmorUtil;
import com.hbm.items.ModItems;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.render.model.ModelArmorDNT;
import com.hbm.util.I18nUtil;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

public class ArmorDNT extends ArmorFSBPowered {

	public ArmorDNT(final ArmorMaterial material, final int layer, final EntityEquipmentSlot slot, final String texture, final long maxPower, final long chargeRate, final long consumption, final long drain, final String s) {
		super(material, layer, slot, texture, maxPower, chargeRate, consumption, drain, s);
	}

	@SideOnly(Side.CLIENT)
	ModelArmorDNT[] models;

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemStack, final EntityEquipmentSlot armorSlot, final ModelBiped _default){
		if(models == null) {
			models = new ModelArmorDNT[4];

			for(int i = 0; i < 4; i++)
				models[i] = new ModelArmorDNT(i);
		}

		return models[armorSlot.getIndex()];
	}
	
	private static final UUID speed = UUID.fromString("6ab858ba-d712-485c-bae9-e5e765fc555a");

	@Override
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {

		super.onArmorTick(world, player, stack);
		
		if(this != ModItems.dns_plate)
			return;

		final IHBMData props = HbmCapability.getData(player);
		
		/// SPEED ///
		final Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(EntityEquipmentSlot.CHEST, stack);
		multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(speed, "DNT SPEED", 0.25, 0));
		player.getAttributeMap().removeAttributeModifiers(multimap);
		
		if(player.isSprinting()) {
			player.getAttributeMap().applyAttributeModifiers(multimap);
		}

		if(hasFSBArmor(player)) {
			
			ArmorUtil.resetFlightTime(player);

			if(props.isJetpackActive()) {

				if(player.motionY < 0.6D)
					player.motionY += 0.2D;

				player.fallDistance = 0;

				if(world.getTotalWorldTime() % 4 == 0)
					world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.immolatorShoot, SoundCategory.PLAYERS, 0.125F, 1.5F);

			} else if(!player.isSneaking() && !player.onGround && props.getEnableBackpack()) {
				player.fallDistance = 0;
				
				if(player.motionY < -1)
					player.motionY += 0.4D;
				else if(player.motionY < -0.1)
					player.motionY += 0.2D;
				else if(player.motionY < 0)
					player.motionY = 0;

				player.motionX *= 1.05D;
				player.motionZ *= 1.05D;
				
				if(player.moveForward != 0) {
					player.motionX += player.getLookVec().x * 0.25 * player.moveForward;
					player.motionZ += player.getLookVec().z * 0.25 * player.moveForward;
				}
				if(world.getTotalWorldTime() % 4 == 0)
					world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.immolatorShoot, SoundCategory.PLAYERS, 0.125F, 1.5F);
			}
			
			if(player.isSneaking() && !player.onGround) {
				player.motionY -= 0.1D;
			}
		}
	}
	
	@Override
	public void handleAttack(final LivingAttackEvent event, final ArmorFSB chestplate) {

		final EntityLivingBase e = event.getEntityLiving();

		if(ArmorFSB.hasFSBArmor(e)) {
				
			if(event.getSource().isExplosion()) {
				return;
			}

			e.world.playSound(null, e.posX, e.posY, e.posZ, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.PLAYERS, 5F, 1.0F + e.getRNG().nextFloat() * 0.5F);
			event.setCanceled(true);
		}
	}
	
	@Override
	public void handleHurt(final LivingHurtEvent event, final ArmorFSB chestplate) {

		final EntityLivingBase e = event.getEntityLiving();

		if(ArmorFSB.hasFSBArmor(e)) {
				
			if(event.getSource().isExplosion()) {
				event.setAmount(event.getAmount()*0.001F);
				return;
			}
			
			event.setAmount(0);
		}
	}

	public static String getColor(final long a, final long b){
		final float fraction = 100F * a/b;
		if(fraction > 75)
			return "§a";
		if(fraction > 25)
			return "§e";
		return "§c";
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		final long power = getCharge(stack);
    	list.add("Charge: " + getColor(power, maxPower) + Library.getShortNumber(power) + " §2/ " + Library.getShortNumber(maxPower));

		list.add(TextFormatting.GOLD + I18nUtil.resolveKey("armor.fullSetBonus"));

		if(!effects.isEmpty()) {

			for(final PotionEffect effect : effects) {
				list.add(TextFormatting.AQUA + "  " + I18n.format(effect.getEffectName()));
			}
		}
		
		list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.explosionImmune"));
		list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.cap", 5));
		list.add(TextFormatting.YELLOW + "  " + I18nUtil.resolveKey("armor.modifier", 0.001F));
		list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.vats"));
		list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.thermal"));
		list.add(TextFormatting.RED + "  " + I18nUtil.resolveKey("armor.hardLanding"));
		list.add(TextFormatting.DARK_RED + "  " + I18nUtil.resolveKey("armor.ignoreLimit"));
		list.add(TextFormatting.AQUA + "  " + I18nUtil.resolveKey("armor.rocketBoots"));
		list.add(TextFormatting.AQUA + "  " + I18nUtil.resolveKey("armor.fastFall"));
		list.add(TextFormatting.AQUA + "  " + I18nUtil.resolveKey("armor.sprintBoost"));
	}
}