package com.hbm.items.weapon;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Multimap;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class GunBoltAction extends Item {

	Random rand = new Random();
	
	public int dmgMin = 16;
	public int dmgMax = 28;
	
	public GunBoltAction(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.maxStackSize = 1;

		this.setMaxDamage(2500);
		dmgMin = 24;
		dmgMax = 36;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void onPlayerStoppedUsing(final ItemStack stack, final World world, final EntityLivingBase entityLiving, final int timeLeft) {
		if(!(entityLiving instanceof EntityPlayer player))
			return;
        if(player.getHeldItemMainhand() == stack && player.getHeldItemOffhand().getItem() == ModItems.gun_bolt_action_saturnite){
			player.getHeldItemOffhand().onPlayerStoppedUsing(world, entityLiving, timeLeft);
		}
		int j = this.getMaxItemUseDuration(stack) - timeLeft;

		final ArrowLooseEvent event = new ArrowLooseEvent(player, stack, world, j, Library.hasInventoryItem(player.inventory, ModItems.ammo_20gauge_slug));
		MinecraftForge.EVENT_BUS.post(event);
		j = event.getCharge();

		final boolean flag = player.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

		if (flag || Library.hasInventoryItem(player.inventory, ModItems.ammo_20gauge_slug)) {
			float f = j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if (j < 10.0D) {
				return;
			}

			if (j > 10.0F) {
				f = 10.0F;
			}
			final EntityBullet entityarrow1;
			entityarrow1 = new EntityBullet(world, player, 3.0F, dmgMin, dmgMax, false, false, player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
			entityarrow1.setDamage(dmgMin + rand.nextInt(dmgMax - dmgMin));
			
			entityarrow1.fire = true;

			stack.damageItem(1, player);

			world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.revolverShoot, SoundCategory.PLAYERS, 5.0F, 0.75F);

			if (flag) { } else {
				Library.consumeInventoryItem(player.inventory, ModItems.ammo_20gauge_slug);
			}

			if (!world.isRemote) {
				world.spawnEntity(entityarrow1);
			}
			
			setAnim(stack, 1);
		}
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entityIn, final int itemSlot, final boolean isSelected) {
		final int j = getAnim(stack);
    	
    	if(j > 0) {
    		if(j < 30)
    			setAnim(stack, j + 1);
    		else
    			setAnim(stack, 0);
    		
        	if(j == 15)
        		worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, HBMSoundHandler.leverActionReload, SoundCategory.PLAYERS, 2F, 0.85F);
    	}
	}
	
	@Override
	public int getMaxItemUseDuration(final ItemStack stack) {
		return 72000;
	}
	
	@Override
	public EnumAction getItemUseAction(final ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand handIn) {
		final ItemStack stack = playerIn.getHeldItem(handIn);
		final ArrowNockEvent event = new ArrowNockEvent(playerIn, stack, handIn, worldIn, Library.hasInventoryItem(playerIn.inventory, ModItems.ammo_20gauge_slug));
		MinecraftForge.EVENT_BUS.post(event);

		if(getAnim(stack) == 0)
			playerIn.setActiveHand(handIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public int getItemEnchantability() {
		return 1;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add("Shiny shooter made from D-25A alloy.");
		list.add("");
		list.add("Ammo: §e20 Gauge Brenneke Slug");
		list.add("Damage: 24 - 36");
		list.add("Sets enemy on fire.");
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(final EntityEquipmentSlot slot, final ItemStack stack) {
		final Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);
		if(slot == EntityEquipmentSlot.MAINHAND || slot == EntityEquipmentSlot.OFFHAND){
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 3.5, 0));
		}
		return map;
	}
	
	private static int getAnim(final ItemStack stack) {
		if(stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		
		return stack.getTagCompound().getInteger("animation");
		
	}
	
	private static void setAnim(final ItemStack stack, final int i) {
		if(stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setInteger("animation", i);
		
	}
	
	public static float getRotationFromAnim(final ItemStack stack) {
		float rad = 0.0174533F;
		rad *= 7.5F;
		int i = getAnim(stack);
		
		if(i < 10)
			return 0;
		i -= 10;
		
		if(i < 10)
			return rad * i;
		else
			return (rad * 10) - (rad * (i - 10));
	}
	
	public static float getLevRotationFromAnim(final ItemStack stack) {
		float rad = 0.0174533F;
		rad *= 10F;
		int i = getAnim(stack);
		
		if(i < 10)
			return 0;
		i -= 10;
		
		if(i < 6)
			return rad * i;
		if(i > 14)
			return rad * (5 - (i - 15));
		return rad * 5;
	}
	
	public static float getOffsetFromAnim(final ItemStack stack) {
		float i = getAnim(stack);
		
		if(i < 10)
			return 0;
		i -= 10;
		
		if(i < 10)
			return i / 10;
		else
			return 2 - (i / 10);
	}
	
	public static float getTransFromAnim(final ItemStack stack) {
		float i = getAnim(stack);
		
		if(i < 10)
			return 0;
		i -= 10;

		if(i > 4 && i < 10)
			return (i - 5) * 0.1F;
		
		if(i > 9 && i < 15)
			return (10 * 0.1F) - ((i - 5) * 0.1F);
					
		return 0;
	}

	@Override
	public EnumRarity getRarity(final ItemStack p_77613_1_) {

		return EnumRarity.RARE;
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(final ItemStack oldStack, final ItemStack newStack, final boolean slotChanged) {
		return false;
	}
	
}
