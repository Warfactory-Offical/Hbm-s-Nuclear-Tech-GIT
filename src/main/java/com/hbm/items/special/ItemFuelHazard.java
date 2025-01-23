package com.hbm.items.special;

import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFuelHazard extends ItemHazard {

	private final int burntime;
	
	public ItemFuelHazard(final String s, final int burntime, final boolean pythonic){
		super(0F, pythonic, s);
		this.burntime = burntime;
	}

	
	@Override
	public int getItemBurnTime(final ItemStack itemStack) {
		return burntime;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
	//	super.addInformation(stack, worldIn, list, flagIn);
		if(this == ModItems.dust)
		{
			if(MainRegistry.polaroidID == 11)
				list.add("Another one bites the dust!");
			else
				list.add("I hate dust!");
		}
		if(this == ModItems.powder_fire)
		{
			list.add("Used in multi purpose bombs:");
			list.add("Incendiary bombs are fun!");
		}
	}
}
