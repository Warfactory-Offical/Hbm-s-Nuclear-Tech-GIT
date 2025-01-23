package com.hbm.items.special;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.TileEntityMachineTeleporter;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemTeleLink extends Item {

	public ItemTeleLink(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		final ItemStack stack = player.getHeldItem(hand);

		if (player.isSneaking()) {
			final TileEntity te = world.getTileEntity(pos);
			
			if (te != null && te instanceof TileEntityMachineTeleporter && world.getBlockState(pos).getBlock() == ModBlocks.machine_teleporter && stack.getTagCompound() != null) {
				final int x1 = stack.getTagCompound().getInteger("x");
				final int y1 = stack.getTagCompound().getInteger("y");
				final int z1 = stack.getTagCompound().getInteger("z");
				final BlockPos pos1 = new BlockPos(x1, y1, z1);

				((TileEntityMachineTeleporter) te).target = pos1;
				((TileEntityMachineTeleporter) te).linked = true;
				te.markDirty();

				if (world.isRemote)
					player.sendMessage(new TextComponentTranslation("chat.telelink.linked", x1, y1, z1));

				stack.setTagCompound(null);
			}
			player.swingArm(hand);
			return EnumActionResult.SUCCESS;
		} else{ 
			if(stack.getTagCompound() == null) {
				stack.setTagCompound(new NBTTagCompound());
			}
			final int x = pos.getX();
			final int y = pos.getY();
			final int z = pos.getZ();
			stack.getTagCompound().setInteger("x", x);
			stack.getTagCompound().setInteger("y", y);
			stack.getTagCompound().setInteger("z", z);

			if (world.isRemote){
				player.sendMessage(new TextComponentTranslation("chat.telelink.set", x, y, z));
			}
		}

		return EnumActionResult.PASS;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if (stack.getTagCompound() != null) {
			tooltip.add("§a"+I18nUtil.resolveKey("chat.possetxyz", stack.getTagCompound().getInteger("x"), stack.getTagCompound().getInteger("y"), stack.getTagCompound().getInteger("z")));
		} else {
			tooltip.add(I18nUtil.resolveKey("item.linker.desc1"));
			tooltip.add(I18nUtil.resolveKey("item.linker.desc2"));
			tooltip.add("§e"+I18nUtil.resolveKey("chat.posnoset"));
		}
	}
}
