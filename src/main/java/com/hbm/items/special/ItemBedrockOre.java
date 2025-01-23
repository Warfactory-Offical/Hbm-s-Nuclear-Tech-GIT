package com.hbm.items.special;
import com.hbm.util.ItemStackUtil;

import java.util.Map;
import java.util.List;

import com.hbm.inventory.BedrockOreRegistry;
import com.hbm.main.MainRegistry;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.util.I18nUtil;
import com.hbm.blocks.generic.BlockBedrockOreTE.TileEntityBedrockOre;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBedrockOre extends Item {

	public ItemBedrockOre(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHasSubtypes(true);
		this.setCreativeTab(MainRegistry.controlTab);
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> items){
		if(tab == CreativeTabs.SEARCH || tab == this.getCreativeTab()){
			for(final Integer oreMeta : BedrockOreRegistry.oreIndexes.keySet()) {
				items.add(ItemStackUtil.itemStackFrom(this, 1, oreMeta));
			}
		}
	}

	public static ItemStack getOut(final int oreMeta, final int amount){
		final ItemStack out = BedrockOreRegistry.getResource(BedrockOreRegistry.oreIndexes.get(oreMeta)).copy();
		out.setCount(amount);
		return out;
	}

	public static String getOreTag(final ItemStack stack){
		return BedrockOreRegistry.oreIndexes.get(stack.getMetadata());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(final ItemStack stack) {
		return I18n.format(this.getTranslationKey() + ".name", BedrockOreRegistry.getOreName(getOreTag(stack)));
	}

	public static int getColor(final ItemStack stack){
		return BedrockOreRegistry.getOreColor(getOreTag(stack));
	}

	public static int getOutType(final int oreMeta){
		final String oreResult = BedrockOreRegistry.oreResults.get(BedrockOreRegistry.oreIndexes.get(oreMeta));
		if(oreResult.startsWith("gem")) return 0;
		if(oreResult.startsWith("dust")) return 1;
		if(oreResult.startsWith("ingot")) return 2;
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final World world, final List<String> list, final ITooltipFlag flagIn) {
		if(stack.getItem() == ModItems.ore_bedrock){
			final String oreName = BedrockOreRegistry.oreIndexes.get(stack.getMetadata());
			final int tier = BedrockOreRegistry.getOreTier(oreName);
			list.add("§6"+I18nUtil.resolveKey("desc.tier", tier));
			final FluidStack req = BedrockOreRegistry.getFluidRequirement(tier);
			list.add("§e"+I18nUtil.resolveKey("desc.requires", req.amount, req.getFluid().getLocalizedName(req)));
		}
		super.addInformation(stack, world, list, flagIn);
	}


	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World worldIn, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(player.capabilities.isCreativeMode){
			final TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TileEntityBedrockOre){
				final ItemStack bedrockOreItem = player.getHeldItem(hand);
				if(bedrockOreItem != null && bedrockOreItem.getItem() == ModItems.ore_bedrock){
					((TileEntityBedrockOre)te).setOre(getOreTag(bedrockOreItem));
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}