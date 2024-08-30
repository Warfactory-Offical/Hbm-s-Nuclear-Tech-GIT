package com.hbm.items.special;

import java.util.List;
import java.util.Random;

import com.hbm.capability.HbmLivingProps;
import com.hbm.config.VersatileConfig;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.handler.ConsumableHandler;
import com.hbm.items.ModItems;
import com.hbm.items.armor.JetpackBase;
import com.hbm.items.weapon.ItemGunBase;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.MainRegistry;
import com.hbm.potion.HbmPotion;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemConsumable extends Item {

	Random rand = new Random();
	
	public ItemConsumable(String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		return 	ConsumableHandler.handleItemUse(world, player, hand, this );
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!(attacker instanceof EntityPlayer))
			return false; // To Oliwer509: Nice try cunt
		return ConsumableHandler.handleHit(stack, target, (EntityPlayer)attacker);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this == ModItems.syringe_antidote) {
			tooltip.add("Removes all potion effects");
		}
		if (this == ModItems.syringe_awesome) {
			tooltip.add("§2Every good effect for 50 seconds");
		}
		if (this == ModItems.syringe_metal_stimpak) {
			tooltip.add("§aHeals 2.5 hearts");
		}
		if(this == ModItems.syringe_metal_medx) {
			tooltip.add("§dResistance III for 4 minutes");
		}
		if(this == ModItems.syringe_metal_psycho) {
			tooltip.add("§dResistance I for 2 minutes");
			tooltip.add("§6Strength I for 2 minutes");
		}
		if(this == ModItems.syringe_metal_super) {
			tooltip.add("§aHeals 25 hearts");
			tooltip.add("§bSlowness I for 10 seconds");
		}
		if(this == ModItems.syringe_poison) {
			tooltip.add("Deadly");
		}
		if(this == ModItems.syringe_taint) {
			tooltip.add("§5Tainted I for 60 seconds");
			tooltip.add("§eNausea I for 5 seconds");
			tooltip.add("Cloud damage + taint = ghoulified effect");
		}
		if(this == ModItems.med_bag) {
			tooltip.add("§aFull heal, regardless of max health");
			tooltip.add("Removes negative effects");
		}
		if(this == ModItems.gas_mask_filter_mono) {
			tooltip.add("Repairs worn monoxide mask");
		}
		if(this == ModItems.syringe_mkunicorn) {
			tooltip.add(TextFormatting.RED + "?");
		}
	}
}
