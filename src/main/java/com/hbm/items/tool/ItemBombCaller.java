package com.hbm.items.tool;

import com.hbm.entity.logic.EntityBomber;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import com.hbm.util.ItemStackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemBombCaller extends Item {

	public ItemBombCaller(final String s) {
		this.setRegistryName(s);
		this.setTranslationKey(s);
		this.setCreativeTab(MainRegistry.consumableTab);
		this.setHasSubtypes(true);

		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.airstrike"));

		switch (getTypeFromStack(stack)) {
		case CARPET:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.YELLOW + I18nUtil.resolveKey("type.carpet"));
			break;
		case NAPALM:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.GOLD + I18nUtil.resolveKey("type.napalm"));
			break;
		case POISON:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.GREEN + I18nUtil.resolveKey("type.poison"));
			break;
		case ORANGE:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.GOLD + I18nUtil.resolveKey("type.orange"));
			break;
		case ATOMIC:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.DARK_RED + TextFormatting.BOLD + I18nUtil.resolveKey("type.atomic"));
			break;
		case STINGER:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.AQUA + I18nUtil.resolveKey("type.stinger"));
			break;
		case PIP:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.AQUA + I18nUtil.resolveKey("type.pip"));
			break;
		case CLOUD:
			list.add(TextFormatting.GRAY + I18nUtil.resolveKey("desc.type") + " " + TextFormatting.AQUA + I18nUtil.resolveKey("type.cloud"));
			break;
		default:
			break;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer playerIn, final EnumHand handIn) {
		final RayTraceResult trace = Library.rayTrace(playerIn, 500, 1);
		final ItemStack stack = playerIn.getHeldItem(handIn);
		boolean b = false;
		if (trace.typeOfHit != Type.MISS && !world.isRemote) {

			final int x = trace.getBlockPos().getX();
			final int y = trace.getBlockPos().getY();
			final int z = trace.getBlockPos().getZ();

			switch (getTypeFromStack(stack)) {
			case CARPET:
				if (world.spawnEntity(EntityBomber.statFacCarpet(world, x, y, z)))
					b = true;
				break;
			case NAPALM:
				if (world.spawnEntity(EntityBomber.statFacNapalm(world, x, y, z)))
					b = true;
				break;
			case POISON:
				if (world.spawnEntity(EntityBomber.statFacChlorine(world, x, y, z)))
					b = true;
				break;
			case ORANGE:
				if (world.spawnEntity(EntityBomber.statFacOrange(world, x, y, z)))
					b = true;
				break;
			case ATOMIC:
				if (world.spawnEntity(EntityBomber.statFacABomb(world, x, y, z)))
					b = true;
				break;
			case STINGER:
				if (world.spawnEntity(EntityBomber.statFacStinger(world, x, y, z)))
					b = true;
				break;
			case PIP:
				if (world.spawnEntity(EntityBomber.statFacBoxcar(world, x, y, z)))
					b = true;
				break;
			case CLOUD:
				if (world.spawnEntity(EntityBomber.statFacPC(world, x, y, z)))
					b = true;
				break;
			default:
				break;
			}
			if (b) {
				playerIn.sendMessage(new TextComponentTranslation("chat.callas"));
				if (!playerIn.capabilities.isCreativeMode)
					stack.shrink(1);
			}
			world.playSound(playerIn.posX, playerIn.posY, playerIn.posZ, HBMSoundHandler.techBoop, SoundCategory.PLAYERS, 1.0F, 1.0F, true);

		}
		return new ActionResult<ItemStack>(b ? EnumActionResult.SUCCESS : EnumActionResult.FAIL, stack.copy());
	}

	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> items) {
		if (tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH)
			for (int i = 0; i < EnumCallerType.values().length - 4; i++) {
				final ItemStack stack = ItemStackUtil.itemStackFrom(this, 1, 0);
				setCallerType(stack, EnumCallerType.values()[i]);
				items.add(stack);
			}
	}

	@Override
	public boolean hasEffect(final ItemStack stack) {
		return getTypeFromStack(stack).ordinal() >= 4;
	}

	public static enum EnumCallerType {
		CARPET, NAPALM, POISON, ORANGE, ATOMIC, STINGER, PIP, CLOUD, NONE
	}

	public static EnumCallerType getTypeFromStack(final ItemStack stack) {
		if (stack == null || stack.getItem() != ModItems.bomb_caller) {
			return EnumCallerType.NONE;
		}
		if (!stack.hasTagCompound()) {
			final NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("callerType", EnumCallerType.CARPET.ordinal());
			stack.setTagCompound(tag);
		}
		final int i = stack.getTagCompound().getInteger("callerType");
		if (i < 0 || i > EnumCallerType.values().length - 2) {
			return EnumCallerType.NONE;
		}
		return EnumCallerType.values()[i];
	}

	public static void setCallerType(final ItemStack stack, final EnumCallerType type) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setInteger("callerType", type.ordinal());
	}
	
	public static ItemStack getStack(final EnumCallerType type){
		final ItemStack stack = ItemStackUtil.itemStackFrom(ModItems.bomb_caller, 1, 0);
		setCallerType(stack, type);
		return stack;
	}
}
