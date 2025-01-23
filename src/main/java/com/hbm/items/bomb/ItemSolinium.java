package com.hbm.items.bomb;

import com.hbm.items.special.ItemHazard;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemSolinium extends ItemHazard {

	public ItemSolinium(final float radiation, final boolean blinding, final String s) {
		super(radiation, false, blinding, s);
		this.setCreativeTab(MainRegistry.nukeTab);
	}

	@Override
	public void addInformation(final ItemStack stack, final World world, final List<String> list, final ITooltipFlag flagIn) {
		list.add(I18nUtil.resolveKey("desc.usedin"));
		list.add(" " + I18nUtil.resolveKey("trait.soliniumbomb"));
		//super.addInformation(stack, world, list, flagIn);
	}
}
