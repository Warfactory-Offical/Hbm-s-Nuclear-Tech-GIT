package com.hbm.lib;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.Random;

public class InventoryHelper {
	
	public static final Random RANDOM = new Random();

	public static void dropInventoryItems(final World world, final BlockPos pos, final ICapabilityProvider t) {
		if(t == null)
			return;
		if(!t.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
			return;
		final IItemHandler inventory = t.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for (int i = 0; i < inventory.getSlots(); ++i)
        {
            final ItemStack itemstack = inventory.getStackInSlot(i);

            if (!itemstack.isEmpty())
            {
                spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack);
            }
        }
	}
	
	public static void spawnItemStack(final World worldIn, final double x, final double y, final double z, final ItemStack stack)
    {
        final float f = RANDOM.nextFloat() * 0.8F + 0.1F;
        final float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
        final float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

        while (!stack.isEmpty())
        {
            final EntityItem entityitem = new EntityItem(worldIn, x + (double)f, y + (double)f1, z + (double)f2, stack.splitStack(RANDOM.nextInt(21) + 10));
            entityitem.motionX = RANDOM.nextGaussian() * 0.05000000074505806D;
            entityitem.motionY = RANDOM.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
            entityitem.motionZ = RANDOM.nextGaussian() * 0.05000000074505806D;
            worldIn.spawnEntity(entityitem);
        }
    }
}
