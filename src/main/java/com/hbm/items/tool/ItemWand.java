package com.hbm.items.tool;

import com.hbm.items.ModItems;
import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

public class ItemWand extends Item {

	public ItemWand(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(final ItemStack itemstack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		list.add(I18nUtil.resolveKey("desc.creative"));
		list.add(I18nUtil.resolveKey("desc.contructionwand.1"));
		list.add(I18nUtil.resolveKey("desc.contructionwand.2"));
		list.add(I18nUtil.resolveKey("desc.contructionwand.3"));
		
		if(itemstack.getTagCompound() != null &&
				!(itemstack.getTagCompound().getInteger("x") == 0 &&
						itemstack.getTagCompound().getInteger("y") == 0 &&
								itemstack.getTagCompound().getInteger("z") == 0))
		{
			list.add(I18nUtil.resolveKey("desc.contructionwand.pos", itemstack.getTagCompound().getInteger("x"), itemstack.getTagCompound().getInteger("y"), itemstack.getTagCompound().getInteger("z")));
		} else {
			list.add(I18nUtil.resolveKey("desc.contructionwand.posnoset"));
		}
		if(itemstack.getTagCompound() != null)
			list.add(I18nUtil.resolveKey("desc.contructionwand.blocksaved", I18nUtil.resolveKey(Block.getBlockById(itemstack.getTagCompound().getInteger("block")).getTranslationKey())));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		final ItemStack stack = player.getHeldItem(hand);
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		if(player.isSneaking())
		{
			final IBlockState state = world.getBlockState(pos);
			stack.getTagCompound().setInteger("block", Block.getIdFromBlock(state.getBlock()));
			stack.getTagCompound().setInteger("meta", state.getBlock().getMetaFromState(state));
			if(world.isRemote)
				player.sendMessage(new TextComponentTranslation("chat.constructionwand.setblock").appendSibling(new TextComponentTranslation(Block.getBlockById(stack.getTagCompound().getInteger("block")).getTranslationKey())));
		} else {
			if(stack.getTagCompound().getInteger("x") == 0 &&
					stack.getTagCompound().getInteger("y") == 0 &&
					stack.getTagCompound().getInteger("z") == 0)
			{
				stack.getTagCompound().setInteger("x", pos.getX());
				stack.getTagCompound().setInteger("y", pos.getY());
				stack.getTagCompound().setInteger("z", pos.getZ());
				if(world.isRemote)
					player.sendMessage(new TextComponentTranslation("chat.posset"));
			} else {
				
				final int x = stack.getTagCompound().getInteger("x");
				final int y = stack.getTagCompound().getInteger("y");
				final int z = stack.getTagCompound().getInteger("z");
				
				stack.getTagCompound().setInteger("x", 0);
				stack.getTagCompound().setInteger("y", 0);
				stack.getTagCompound().setInteger("z", 0);
				
				if(!world.isRemote)
				{
					final MutableBlockPos mPos = new BlockPos.MutableBlockPos();
					for(int i = Math.min(x, pos.getX()); i <= Math.max(x, pos.getX()); i++)
					{
						for(int j = Math.min(y, pos.getY()); j <= Math.max(y, pos.getY()); j++)
						{
							for(int k = Math.min(z, pos.getZ()); k <= Math.max(z, pos.getZ()); k++)
							{
								world.setBlockState(mPos.setPos(i, j, k), Block.getBlockById(stack.getTagCompound().getInteger("block")).getStateFromMeta(stack.getTagCompound().getInteger("meta")), 3);
							}
						}
					}
				}
				if(world.isRemote)
					player.sendMessage(new TextComponentTranslation("chat.constructionwand.filled"));
			}
		}
    	
        return EnumActionResult.SUCCESS;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		if(player.isSneaking())
		{
			stack.getTagCompound().setInteger("block", 0);
			stack.getTagCompound().setInteger("meta", 0);
			if(world.isRemote)
				player.sendMessage(new TextComponentTranslation("chat.constructionwand.setblock").appendSibling(new TextComponentTranslation(Block.getBlockById(stack.getTagCompound().getInteger("block")).getTranslationKey())));
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
				
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}
}
