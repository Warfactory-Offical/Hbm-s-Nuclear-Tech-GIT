package com.hbm.items.weapon;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Multimap;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.lib.ModDamageSource;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class GunSuicide extends Item {

	private Item ammo;
    Random rand = new Random();
    public GunSuicide(final String s) {
    	this.setTranslationKey(s);
    	this.setRegistryName(s);
    	this.maxStackSize = 1;
    	this.setMaxDamage(500);
    	this.ammo = ModItems.gun_revolver_ammo;
    	
    	ModItems.ALL_ITEMS.add(this);
	}
    
    @Override
    public void onPlayerStoppedUsing(final ItemStack stack, final World worldIn, final EntityLivingBase entityLiving, final int timeLeft) {
    	if(!(entityLiving instanceof EntityPlayer player))
    		return;
    	if(this.ammo == null)
    		this.ammo = ModItems.gun_revolver_ammo;
        int j = this.getMaxItemUseDuration(stack) - timeLeft;

        final ArrowLooseEvent event = new ArrowLooseEvent(player, stack, worldIn, j, Library.hasInventoryItem(player.inventory, ammo));
        MinecraftForge.EVENT_BUS.post(event);
        j = event.getCharge();

        final boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
        

        if (flag || Library.hasInventoryItem(player.inventory, ammo))
        {
            float f = j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if (j < 10.0D)
            {
               	return;
            }

            if (j > 10.0F)
            {
               	f = 10.0F;
            }
            
            stack.damageItem(1, player);
            worldIn.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.revolverShoot, SoundCategory.PLAYERS, 1.0F, 1.0F);
            
            if (flag)
            { }
            else
            {
               	Library.consumeInventoryItem(player.inventory, ammo);
            }

            if (!worldIn.isRemote)
            {
            	player.attackEntityFrom(ModDamageSource.suicide, 10000);
            	if(!player.capabilities.isCreativeMode)
            		player.setHealth(0.0F);
            }
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
    	final ArrowNockEvent event = new ArrowNockEvent(playerIn, playerIn.getHeldItem(handIn), handIn, worldIn, Library.hasInventoryItem(playerIn.inventory, ammo));
        MinecraftForge.EVENT_BUS.post(event);
        playerIn.setActiveHand(handIn);
    	return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    
    @Override
    public int getItemEnchantability() {
    	return 1;
    }
    
    @Override
    public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
    	list.add("I've seen things...");
		list.add("...I shouldn't have seen.");
		list.add("");
		list.add("Ammo: §eLead Bullets");
		list.add("Damage: Infinite");
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(final EntityEquipmentSlot slot, final ItemStack stack) {
    	final Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);
		if(slot == EntityEquipmentSlot.MAINHAND || slot == EntityEquipmentSlot.OFFHAND){
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 2.5, 0));
		}
		return map;
    }
}
