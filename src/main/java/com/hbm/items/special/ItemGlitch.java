package com.hbm.items.special;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.entity.effect.EntityVortex;
import com.hbm.entity.missile.EntityMIRV;
import com.hbm.entity.projectile.EntityBoxcar;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.MainRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemGlitch extends Item {

	public ItemGlitch(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.maxStackSize = 1;
        this.setMaxDamage(1);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		stack.damageItem(5, player);
		
		if(!world.isRemote)
			switch(itemRand.nextInt(31)) {
			case 0:
				player.sendMessage(new TextComponentTranslation("chat.glitch.0"));
				break;
			case 1:
				player.sendMessage(new TextComponentTranslation("chat.glitch.1"));
				break;
			case 2:
				player.attackEntityFrom(ModDamageSource.radiation, 1000);
				break;
			case 3:
				player.attackEntityFrom(ModDamageSource.boxcar, 1000);
				break;
			case 4:
				player.attackEntityFrom(ModDamageSource.blackhole, 1000);
				break;
			case 5:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModBlocks.block_meteor_treasure.getItemDropped(ModBlocks.block_meteor_treasure.getDefaultState(), itemRand, 0)));
				break;
			case 6:
				for(int i = 0; i < 3; i++)
					player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModBlocks.block_meteor_treasure.getItemDropped(ModBlocks.block_meteor_treasure.getDefaultState(), itemRand, 0)));
				break;
			case 7:
				for(int i = 0; i < 10; i++)
					player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModBlocks.block_meteor_treasure.getItemDropped(ModBlocks.block_meteor_treasure.getDefaultState(), itemRand, 0)));
				break;
			case 8:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.ammo_container, 10));
				player.sendMessage(new TextComponentTranslation("chat.glitch.8"));
				break;
			case 9:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.nuke_advanced_kit, 1));
				break;
			case 10:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.nuke_starter_kit, 1));
				break;
			case 11:
				final EntityBoxcar pip = new EntityBoxcar(world);
				pip.posX = player.posX;
				pip.posY = player.posY + 50;
				pip.posZ = player.posZ;
				world.spawnEntity(pip);
				break;
			case 12:
				for(int i = 0; i < 10; i++) {
					final EntityBoxcar pippo = new EntityBoxcar(world);
					pippo.posX = player.posX + itemRand.nextGaussian() * 25;
					pippo.posY = player.posY + 50;
					pippo.posZ = player.posZ + itemRand.nextGaussian() * 25;
					world.spawnEntity(pippo);
				}
				break;
			case 13:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.gun_revolver_pip));
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.bottle_rad));
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.geiger_counter));
				player.sendMessage(new TextComponentTranslation("chat.glitch.13a"));
				player.sendMessage(new TextComponentTranslation("chat.glitch.13b"));
				break;
			case 14:
				player.inventory.dropAllItems();
				ExplosionChaos.burn(world, new BlockPos((int)player.posX, (int)player.posY, (int)player.posZ), 5);
				break;
			case 15:
				for(int i = 0; i < 36; i++)
					player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(Blocks.DIRT, 64));
				break;
			case 16:
				player.sendMessage(new TextComponentTranslation("chat.glitch.16"));
				break;
			case 17:
				player.sendMessage(new TextComponentTranslation("chat.glitch.17"));
				break;
			case 18:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.gun_lever_action));
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.ammo_20gauge, 12));
				player.sendMessage(new TextComponentTranslation("chat.glitch.18"));
				break;
			case 19:
				player.sendMessage(new TextComponentTranslation("chat.glitch.19"));
				break;
			case 20:
				player.sendMessage(new TextComponentTranslation("chat.glitch.20"));
				break;
			case 21:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.missile_nuclear));
				player.sendMessage(new TextComponentTranslation("chat.glitch.21"));
				break;
			case 22:
				player.sendMessage(new TextComponentTranslation("chat.glitch.22"));
				break;
			case 23:
				player.sendMessage(new TextComponentTranslation("chat.glitch.23"));
				break;
			case 24:
				player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60 * 20, 9));
				player.sendMessage(new TextComponentTranslation("chat.glitch.24"));
				break;
			case 25:
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60 * 20, 9));
				player.sendMessage(new TextComponentTranslation("chat.glitch.25"));
				break;
			case 26:
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60 * 20, 9));
				player.sendMessage(new TextComponentTranslation("chat.glitch.26"));
				break;
			case 27:
				final EntityVortex vortex = new EntityVortex(world, 2.5F);
				vortex.posX = player.posX;
				vortex.posY = player.posY - 15;
				vortex.posZ = player.posZ;
				world.spawnEntity(vortex);
				break;
			case 28:
				final EntityMIRV mirv = new EntityMIRV(world);
				mirv.posX = player.posX;
				mirv.posY = player.posY + 100;
				mirv.posZ = player.posZ;
				world.spawnEntity(mirv);
				player.sendMessage(new TextComponentTranslation("chat.glitch.28"));
				break;
			case 29:
				ExplosionLarge.spawnBurst(world, player.posX, player.posY, player.posZ, 27, 3);
				player.sendMessage(new TextComponentTranslation("chat.glitch.29"));
				break;
			case 30:
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.plate_saturnite));
				player.sendMessage(new TextComponentTranslation("chat.glitch.30"));
				break;
			}
		
		player.inventoryContainer.detectAndSendChanges();
		
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add(I18nUtil.resolveKey("desc.glitch"));
		list.add("");
		if(MainRegistry.polaroidID > 0 && MainRegistry.polaroidID < 19)
			list.add(I18nUtil.resolveKey("desc.glitch."+MainRegistry.polaroidID));
	}
}
