package com.hbm.items.weapon;

import com.hbm.entity.effect.EntityCloudFleijaRainbow;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.projectile.EntityModBeam;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
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

import java.util.List;
import java.util.Random;

public class GunB93 extends Item {

	Random rand = new Random();

	public int dmgMin = 16;
	public int dmgMax = 28;

	public GunB93(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.weaponTab);
		this.maxStackSize = 1;
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void onPlayerStoppedUsing(final ItemStack stack, final World worldIn, final EntityLivingBase entityLiving, final int timeLeft) {
		if (entityLiving.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) == stack && !entityLiving.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).isEmpty() && entityLiving.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() == ModItems.gun_b93) {
			entityLiving.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).onPlayerStoppedUsing(worldIn, entityLiving, timeLeft);
		}
		if (!entityLiving.isSneaking()) {
			int j = this.getMaxItemUseDuration(stack) - timeLeft;
			if (entityLiving instanceof EntityPlayer) {
				final ArrowLooseEvent evt = new ArrowLooseEvent((EntityPlayer) entityLiving, stack, worldIn, j, false);
				MinecraftForge.EVENT_BUS.post(evt);
				j = evt.getCharge();
			}

			final boolean flag = true;
			
			if (flag) {
				float f = j / 20.0F;
				f = (f * f + f * 2.0F) / 3.0F;

				if (j < 10.0D) {
					return;
				}

				if (j > 10.0F) {
					f = 10.0F;
				}

				if (!worldIn.isRemote) {
					
					final EntityModBeam entityarrow1;
					entityarrow1 = new EntityModBeam(worldIn, entityLiving, 3.0F, stack == entityLiving.getHeldItem(EnumHand.MAIN_HAND) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
					entityarrow1.mode = getPower(stack) - 1;
					stack.damageItem(1, entityLiving);

					worldIn.spawnEntity(entityarrow1);

					worldIn.playSound(null, entityLiving.posX, entityLiving.posY, entityLiving.posZ, HBMSoundHandler.sparkShoot, SoundCategory.PLAYERS, 5.0F, 1.0F);
				}

				setAnim(stack, 1);
				setPower(stack, 0);
			}
		} else {
		}
	}

	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int i, final boolean b) {
		final int j = getAnim(stack);

		if (j > 0) {
			if (j < 30)
				setAnim(stack, j + 1);
			else
				setAnim(stack, 0);

			if (j == 15) {
				world.playSound(null, entity.posX, entity.posY, entity.posZ, HBMSoundHandler.b92Reload, SoundCategory.PLAYERS, 2F, 0.9F);
				setPower(stack, getPower(stack) + 1);
				
				if(getPower(stack) > 10) {
					
					setPower(stack, 0);
					
			    	if(!world.isRemote) {
			    		world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 100.0f, world.rand.nextFloat() * 0.1F + 0.9F);

						final EntityNukeExplosionMK3 exp = new EntityNukeExplosionMK3(world);
						exp.posX = entity.posX;
						exp.posY = entity.posY;
						exp.posZ = entity.posZ;
						if(!EntityNukeExplosionMK3.isJammed(world, exp)){
							
							exp.destructionRange = 50;
							exp.speed = 25;
							exp.coefficient = 1.0F;
							exp.waste = false;

							world.spawnEntity(exp);
				    		
				    		final EntityCloudFleijaRainbow cloud = new EntityCloudFleijaRainbow(world, 50);
				    		cloud.posX = entity.posX;
				    		cloud.posY = entity.posY;
				    		cloud.posZ = entity.posZ;
				    		world.spawnEntity(cloud);
				    	}
			    	}
				}
			}
		}

	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(final ItemStack p_77626_1_) {
		return 72000;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(final ItemStack p_77661_1_) {
		return EnumAction.BOW;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand handIn) {
		final ItemStack stack = playerIn.getHeldItem(handIn);
		if (!playerIn.isSneaking() && getPower(stack) > 0) {

			if (getAnim(stack) == 0)
				playerIn.setActiveHand(handIn);
		} else {
			if (getAnim(stack) == 0) {
				setAnim(stack, 1);
			}
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based
	 * on material.
	 */
	@Override
	public int getItemEnchantability() {
		return 1;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		tooltip.add(I18nUtil.resolveKey("trait.legendaryweap"));
	}

	private static int getAnim(final ItemStack stack) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}

		return stack.getTagCompound().getInteger("animation");

	}

	private static void setAnim(final ItemStack stack, final int i) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.getTagCompound().setInteger("animation", i);

	}

	private static int getPower(final ItemStack stack) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}

		return stack.getTagCompound().getInteger("energy");

	}

	private static void setPower(final ItemStack stack, final int i) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.getTagCompound().setInteger("energy", i);

	}

	public static float getRotationFromAnim(final ItemStack stack, final float partialTicks) {
		float rad = 0.0174533F;
		rad *= 7.5F;
		int i = getAnim(stack);

		if (i < 10)
			return 0;
		i -= 10;

		if (i < 6)
			return rad * (i - 1 + partialTicks);
		if (i > 14)
			return rad * (5 - (i + partialTicks - 15));
		return rad * 5;
	}

	/*public static float getOffsetFromAnim(ItemStack stack) {
		float i = getAnim(stack);

		if (i < 10)
			return 0;
		i -= 10;

		if (i < 10)
			return i / 10;
		else
			return 2 - (i / 10);
	}*/

	public static float getTransFromAnim(final ItemStack stack, final float partialTicks) {
		float i = getAnim(stack);

		if (i < 10)
			return 0;
		i -= 10;

		if (i > 4 && i < 10)
			return (i+partialTicks - 5) * 0.05F;

		if (i > 9 && i < 15)
			return (10 * 0.05F) - ((i+partialTicks - 5) * 0.05F);

		return 0;
	}

	@Override
	public EnumRarity getRarity(final ItemStack p_77613_1_) {

		return EnumRarity.UNCOMMON;
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(final ItemStack oldStack, final ItemStack newStack, final boolean slotChanged) {
		return false;
	}
}
