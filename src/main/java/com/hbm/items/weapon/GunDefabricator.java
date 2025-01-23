package com.hbm.items.weapon;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Multimap;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;

import com.hbm.util.I18nUtil;
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

public class GunDefabricator extends Item {

	Random rand = new Random();
	
	public GunDefabricator(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.maxStackSize = 1;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumAction getItemUseAction(final ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public int getMaxItemUseDuration(final ItemStack stack) {
		return 72000;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void onUsingTick(final ItemStack stack, final EntityLivingBase player1, final int count) {
		if(!(player1 instanceof EntityPlayer player))
			return;
        if(player.getHeldItemMainhand() == stack && player.getHeldItemOffhand().getItem() == ModItems.gun_defabricator){
			player.getHeldItemOffhand().getItem().onUsingTick(player.getHeldItemOffhand(), player, count);
		}
		final World world = player.world;

		final boolean flag = player.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
		if ((player.capabilities.isCreativeMode || Library.hasInventoryItem(player.inventory, ModItems.gun_defabricator_ammo))
				&& count % 2 == 0) {
			final EntityBullet entitybullet = new EntityBullet(world, player, 3.0F, 40, 120, false, "tauDay", player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
			entitybullet.setDamage(40 + rand.nextInt(120 - 40));

			//world.playSoundAtEntity(player, "random.explode", 1.0F, 1.5F + (rand.nextFloat() / 4));
			world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.defabShoot, SoundCategory.PLAYERS, 1.0F, 0.9F + (rand.nextFloat() * 0.2F));
			if(count == this.getMaxItemUseDuration(stack))
				world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.defabSpinup, SoundCategory.PLAYERS, 1.0F, 1.0F);

			if(count % 20 == 0 && !flag)
				Library.consumeInventoryItem(player.inventory, ModItems.gun_defabricator_ammo);

			if (!world.isRemote) {
				world.spawnEntity(entitybullet);
			}
		}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		if(MainRegistry.polaroidID == 11)
			list.add("Did you set your alarm for volcano day?");
		else
			list.add("§4§lBAD WOLF");
		list.add("");
		list.add("Ammo: §cDefabricator Energy Cell");
		list.add("Damage: 40 - 120");
		list.add("");
		list.add(I18nUtil.resolveKey("trait.legendaryweap"));
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(final EntityEquipmentSlot slot, final ItemStack stack) {
		final Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);
		if(slot == EntityEquipmentSlot.MAINHAND || slot == EntityEquipmentSlot.OFFHAND){
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 6.5, 0));
		}
		return map;
	}
}
