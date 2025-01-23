package com.hbm.items.armor;

import com.hbm.items.ModItems;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.lib.ModDamageSource;
import com.hbm.render.model.ModelArmorBJ;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorBJ extends ArmorFSBPowered {

	public ArmorBJ(final ArmorMaterial material, final int layer, final EntityEquipmentSlot slot, final String texture, final long maxPower, final long chargeRate, final long consumption, final long drain, final String s) {
		super(material, layer, slot, texture, maxPower, chargeRate, consumption, drain, s);
	}

	@SideOnly(Side.CLIENT)
	ModelArmorBJ[] models;
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemStack, final EntityEquipmentSlot armorSlot, final ModelBiped _default) {
		if(models == null) {
			models = new ModelArmorBJ[4];

			for(int i = 0; i < 4; i++)
				models[i] = new ModelArmorBJ(i);
		}
		return models[3-armorSlot.getIndex()];
	}
	
	@Override
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);

    	if(this == ModItems.bj_helmet && ArmorFSB.hasFSBArmorIgnoreCharge(player) && !ArmorFSB.hasFSBArmor(player)) {

    		final ItemStack helmet = player.inventory.armorInventory.get(3);

    		if(!player.inventory.addItemStackToInventory(helmet))
    			player.dropItem(helmet, false);

    		player.inventory.armorInventory.set(3, ItemStack.EMPTY);

    		player.attackEntityFrom(ModDamageSource.lunar, 1000);
    	}
	}
}