package api.hbm.energy;

import com.hbm.util.ItemStackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IBatteryItem {

	public void chargeBattery(ItemStack stack, long i);
	public void setCharge(ItemStack stack, long i);
	public void dischargeBattery(ItemStack stack, long i);
	public long getCharge(ItemStack stack);
	public long getMaxCharge();
	public long getChargeRate();
	public long getDischargeRate();
	
	/** Returns a string for the NBT tag name of the long storing power */
	public default String getChargeTagName() {
		return "charge";
	}

	/** Returns a string for the NBT tag name of the long storing power */
	public static String getChargeTagName(final ItemStack stack) {
		return ((IBatteryItem) stack.getItem()).getChargeTagName();
	}

	/** Returns an empty battery stack from the passed ItemStack, the original won't be modified */
	public static ItemStack emptyBattery(final ItemStack stack) {
		if(stack != null && stack.getItem() instanceof IBatteryItem) {
			final String keyName = getChargeTagName(stack);
			final ItemStack stackOut = stack.copy();
			final NBTTagCompound tag;
			if(stack.hasTagCompound())
				tag = stack.getTagCompound();
			else
				tag = new NBTTagCompound();
			tag.setLong(keyName, 0);
			stackOut.setTagCompound(tag);
			return stackOut.copy();
		}
		return null;
	}

	/** Returns an empty battery stack from the passed Item */
	public static ItemStack emptyBattery(final Item item) {
		return item instanceof IBatteryItem ? emptyBattery(ItemStackUtil.itemStackFrom(item)) : null;
	}
}
