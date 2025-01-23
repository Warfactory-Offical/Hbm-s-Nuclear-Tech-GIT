package com.hbm.items.weapon;

import com.hbm.handler.GunConfiguration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ItemGunDart extends ItemGunBase {

	public ItemGunDart(final GunConfiguration config, final String s) {
		super(config, s);
	}
	
	public static void writePlayer(final ItemStack stack, final EntityPlayer player) {

		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setString("player", player.getDisplayName().getUnformattedText());
	}

	public static EntityPlayer readPlayer(final ItemStack stack) {

		if(!stack.hasTagCompound())
			return null;

		return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(stack.getTagCompound().getString("player"));
	}
	
	@Override
	public void startAction(final ItemStack stack, final World world, final EntityPlayer player, final boolean main, final EnumHand hand) {
		if(main) {
			super.startAction(stack, world, player, main, hand);
		} else {

			final EntityPlayer target = readPlayer(stack);

			if(target != null) {

				final int dim = target.world.provider.getDimension();
				final int x = (int)target.posX;
				final int y = (int)target.posY;
				final int z = (int)target.posZ;
				final int dist = (int) target.getDistance(player);

				player.sendMessage(new TextComponentString(target.getDisplayName().getUnformattedText()).setStyle(new Style().setColor(TextFormatting.YELLOW)));
				player.sendMessage(new TextComponentString("Dim: " + dim + " X:" + x + " Y:" + y + " Z:" + z + " (" + dist + " blocks away)").setStyle(new Style().setColor(TextFormatting.YELLOW)));
			} else {

				player.sendMessage(new TextComponentString("No Target").setStyle(new Style().setColor(TextFormatting.RED)));
			}
		}
	}

}
