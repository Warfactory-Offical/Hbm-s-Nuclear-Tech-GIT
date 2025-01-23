package com.hbm.items.special;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import com.hbm.main.MainRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWasteLong extends ItemContaminating {

	public ItemWasteLong(final float radiation, final String s) {
		super(radiation, s);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(MainRegistry.controlTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> items){
		if(tab == CreativeTabs.SEARCH || tab == this.getCreativeTab())
			for(int i = 0; i < WasteClass.values().length; ++i) {
				items.add(ItemStackUtil.itemStackFrom(this, 1, i));
			}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World world, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.ITALIC + WasteClass.values()[rectify(stack.getItemDamage())].name);
		super.addInformation(stack, world, list, flagIn);
	}
	
	public static int rectify(final int meta) {
		return Math.abs(meta) % WasteClass.values().length;
	}
	
	public enum WasteClass {

		//all decayed versions include lead-types and classic nuclear waste
		THORIUM("Thorium-232", 0, 0),		//uranium 233 and uranium 235 / -
		URANIUM233("Uranium-233", 0, 50),	//uranium 235, plutonium 239, neptunium 237 / -
		URANIUM235("Uranium-235", 0, 0),	//plutonium 239 and 240, neptunium 237 / -
		NEPTUNIUM("Neptunium-237", 0, 100),	//plutonium 239 and uranium 238 / -
		SCHRABIDIUM("Schrabidium-326", 0, 250); //tantalum, neodymium, solinium, euphemium, ghiorsium-336 / -
		
		public String name;
		public int liquid;
		public int gas;
		
		private WasteClass(final String name, final int liquid, final int gas) {
			this.name = name;
			this.liquid = liquid;
			this.gas = gas;
		}
	}
}