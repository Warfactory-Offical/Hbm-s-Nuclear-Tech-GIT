package com.hbm.items.machine;

import java.util.List;

import com.hbm.lib.Library;
import com.hbm.items.special.ItemDrop;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFWatzCore extends ItemDrop {

	public int type;

	public int coolantDrain;
	public int amatDrain;
	public int aschrabDrain;

	public int coolantRefill;

	public long powerOutput;


	public ItemFWatzCore(final String s, final long powerOutputL, final int amatDrainL, final int aschrabDrainL, final int coolantDrainL, final int coolantRefillL, final int typeL) {
		super(s);
		this.type = typeL;
		this.powerOutput = powerOutputL;
		this.amatDrain = amatDrainL;
		this.aschrabDrain = aschrabDrainL;
		this.coolantDrain = coolantDrainL;
		this.coolantRefill = coolantRefillL;
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("§3[Singularity-Anti-Fusion-Experiment Core]");
		tooltip.add(" §aPower: "+Library.getShortNumber(this.powerOutput * 20L)+"HE/s");
		tooltip.add(" §fAntimatter: "+this.amatDrain*0.02F+"b/s §cAntischrabidium: "+this.aschrabDrain*0.02F+"b/s");
		final float uptime = ((int)((128000F/this.coolantDrain)*5F)/100F);
		final float downtime = ((int)((128000F/this.coolantRefill)*5F)/100F);
		tooltip.add(" §bUptime: "+uptime+"s Downtime: "+downtime+"s");
	}
}
