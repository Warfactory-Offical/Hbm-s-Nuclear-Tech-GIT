package com.hbm.items.machine;

import java.util.List;

import javax.annotation.CheckForNull;

import com.hbm.items.ModItems;
import com.hbm.util.BobMathUtil;
import com.hbm.util.I18nUtil;
import com.hbm.items.special.ItemHazard;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemRTGPellet extends ItemHazard {
	
	private short heat = 0;
	private boolean doesDecay = false;
	private Item decayItem = null;
	private long halflife = 0;
	private long lifespan = 0;
	
	public ItemRTGPellet(int heatIn, float radiation, String s) {
		super(radiation, s);
		this.heat = (short) heatIn;
		this.setMaxStackSize(1);
		this.setTranslationKey(s);
		//ModItems.ALL_ITEMS.add(this);
	}
	
	private static final String[] facts = new String[] {
			"One gram of Pu-238 costs $8,000.",
			"One gram of Pu-238 produces just under half a Watt of decay heat.",
			"The typical plutonium RTG contains close to eight kilograms of Pu-238.",
			"Pu-238's half life is 87.7 years.",
			"A plutonium RTG was used to power the New Horizons probe that visited Pluto in 2015.",
			"Po-210 can also be used for RTGs as it generates 140 W/g of decay heat due to its 138 day half life.",
			"Pa-231 is an isotope of protactinium that easily fissions, but it isn't quite fissile.",
			"Muons generated by matter-antimatter reactions can trigger nuclear fusion reactions at room temperature.",
			"Roughly 20% of U-235 nuclei will fail to fission when hit by a neutron. They become U-236 nuclei instead.",
			"Thorium reactors are really uranium reactors that convert thorium into U-233.",
			"Natural uranium consists of 99.284% U-238, 0.711% U-235, and 0.0055% U-234.",
			"Most nuclear reactors use uranium that has been enriched to 3-5% U-235.",
			"Uranium-based nuclear weapons require uranium enriched to at least 85-90% U-235.",
			"Depleted uranium is uranium that has had most of its U-235 removed. It is effectively pure U-238.",
			"In the 1920s, uranium was considered a useless byproduct of the production of radium.",
			"The Manhattan Project referred to refined natural uranium as tuballoy, enriched uranium as oralloy, and depleted uranium as depletalloy."
	};
	
	public ItemRTGPellet setDecays(Item depleted, long halflife, int halflifes) {
		this.doesDecay = true;
		this.decayItem = depleted;
		this.halflife = halflife;
		this.lifespan = halflife * halflifes;
		return this;
	}
	
	public long getMaxLifespan() {
		return this.lifespan;
	}
	public long getHalfLife() {
		return this.halflife;
	}

	public short getHeat() {
		return this.heat;
	}

	@CheckForNull
	public Item getDecayItem() {
		return this.decayItem == null ? null : this.decayItem;
	}

	public boolean getDoesDecay() {
		return this.doesDecay;
	}
	
	public static ItemStack handleDecay(ItemStack stack, ItemRTGPellet instance) {
		if (instance.getDoesDecay()) {
			if (instance.getLifespan(stack) <= 0)
				return new ItemStack(instance.getDecayItem());
			else
				instance.decay(stack);
		}
		
		return stack;
	}
	
	public void decay(ItemStack stack) {
		if (stack != null && stack.getItem() instanceof ItemRTGPellet) {
			if (!((ItemRTGPellet) stack.getItem()).getDoesDecay())
				return;
			if (stack.hasTagCompound())
				stack.getTagCompound().setLong("PELLET_DEPLETION", getLifespan(stack) - 1);
			else {
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setLong("PELLET_DEPLETION", getMaxLifespan());
			}
		}
	}
	
	public long getLifespan(ItemStack stack)
	{	
		if (stack != null && stack.getItem() instanceof ItemRTGPellet)
		{
			if (stack.hasTagCompound())
				return stack.getTagCompound().getLong("PELLET_DEPLETION");
			else
			{
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setLong("PELLET_DEPLETION", getMaxLifespan());
				return getMaxLifespan();
			}
		}
		return 0;
	}

	public static double getDecay(ItemRTGPellet fuel, ItemStack stack) {
		return (double) Math.pow(0.5, ((double)(fuel.getMaxLifespan()-fuel.getLifespan(stack)) / (double)fuel.getHalfLife()));
	}
	
	public static short getScaledPower(ItemRTGPellet fuel, ItemStack stack) {
		return (short) Math.ceil(fuel.getHeat() * getDecay(fuel, stack));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if(!world.isRemote) {
			player.sendMessage(new TextComponentString(facts[world.rand.nextInt(facts.length)]).setStyle(new Style().setColor(TextFormatting.YELLOW)));
		}
		return super.onItemRightClick(world, player, handIn);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return getDoesDecay() && getLifespan(stack) != getMaxLifespan();
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		final ItemRTGPellet instance = (ItemRTGPellet) stack.getItem();
		return 1D-(double)getDecay(instance, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn) {
		super.addInformation(stack, world, list, flagIn);
		final ItemRTGPellet instance = (ItemRTGPellet) stack.getItem();
		list.add("§c" + I18nUtil.resolveKey("desc.item.rtgHeat", getScaledPower(instance, stack)) + "§r");
		if (instance.getDoesDecay()) {
			list.add("§aFuel left: "+((int)(instance.getDecay(instance, stack) * 100000000D))/1000000D + "%§r");
			list.add(I18nUtil.resolveKey("desc.item.rtgDecay", new ItemStack(instance.getDecayItem()).getDisplayName()));
			list.add("");
			list.add(String.format("%s / %s ticks", instance.getLifespan(stack), instance.getMaxLifespan()));
			final String[] halfLife = BobMathUtil.ticksToDate(instance.getHalfLife());
			final String[] timeLeft = BobMathUtil.ticksToDate(instance.getLifespan(stack));
			final String[] maxLife = BobMathUtil.ticksToDate(instance.getMaxLifespan());
			list.add(String.format("§aHalf life:      %sy %sd %sh %sm %ss§r", (Object[]) halfLife));
			list.add(String.format("Time remaining: %sy %sd %sh %sm %ss", (Object[]) timeLeft));
			list.add(String.format("Decay Time:     %sy %sd %sh %sm %ss", (Object[]) maxLife));
		}
	}

	public String getData() {
		return String.format("%s (%s HE/t) %s", I18nUtil.resolveKey(getTranslationKey().concat(".name")), getHeat()*5, (getDoesDecay() ? " (decays)" : ""));
	}
}
