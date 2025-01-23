package com.hbm.items.tool;

import com.hbm.blocks.BlockDummyable;
import com.hbm.items.ModItems;
import com.hbm.tileentity.network.energy.TileEntityPylonBase;
import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

public class ItemWiring extends Item {

	public ItemWiring(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World world, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
	
		final Block b = world.getBlockState(pos).getBlock();
		BlockPos core = pos;
		if(b instanceof BlockDummyable) {
			final int[] corePos = ((BlockDummyable)b).findCore(world, pos.getX(), pos.getY(), pos.getZ());
		
			if(corePos != null) {
				core = new BlockPos(corePos[0], corePos[1], corePos[2]);
			}
		}

		final TileEntity te = world.getTileEntity(core);
		final ItemStack stack = player.getHeldItem(hand);
		if (te != null && te instanceof TileEntityPylonBase) {
			if(player.isSneaking()) { //if sneak then set a other wise connect to b
				if (!stack.hasTagCompound())
					stack.setTagCompound(new NBTTagCompound());

				stack.getTagCompound().setInteger("x", pos.getX());
				stack.getTagCompound().setInteger("y", pos.getY());
				stack.getTagCompound().setInteger("z", pos.getZ());

				if (world.isRemote)
					player.sendMessage(new TextComponentTranslation("chat.wiring.start", pos.getX(), pos.getY(), pos.getZ()));
			} else {
				if (stack.hasTagCompound()) {
					final int x1 = stack.getTagCompound().getInteger("x");
					final int y1 = stack.getTagCompound().getInteger("y");
					final int z1 = stack.getTagCompound().getInteger("z");
					
					final TileEntityPylonBase thisPylon = (TileEntityPylonBase)te;
					final BlockPos newPos = new BlockPos(x1, y1, z1);
					if(!this.isLengthValid(pos.getX(), pos.getY(), pos.getZ(), x1, y1, z1, thisPylon.getMaxWireLength())){
						if (world.isRemote){
							final BlockPos vector = newPos.subtract(pos);
							final int distance = (int)MathHelper.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
							player.sendMessage(new TextComponentTranslation("chat.wiring.tofar", distance, thisPylon.getMaxWireLength()));
						}
					} else if(pos == newPos){
						if (world.isRemote)
							player.sendMessage(new TextComponentTranslation("chat.wiring.noself"));
					} else{
						final Block a = world.getBlockState(newPos).getBlock();
						BlockPos coreB = newPos;
						if(a instanceof BlockDummyable) {
							final int[] corePosB = ((BlockDummyable)a).findCore(world, newPos.getX(), newPos.getY(), newPos.getZ());
						
							if(corePosB != null) {
								coreB = new BlockPos(corePosB[0], corePosB[1], corePosB[2]);
							}
						}
						final TileEntity target = world.getTileEntity(coreB);
						if(target instanceof TileEntityPylonBase targetPylon) {

                            if(TileEntityPylonBase.canConnect(thisPylon, targetPylon)){
								thisPylon.addConnection(targetPylon.getPos());
								targetPylon.addConnection(thisPylon.getPos());

								if (world.isRemote)
									player.sendMessage(new TextComponentTranslation("chat.wiring.connected"));
							}else{
								if(thisPylon.getConnectionType() != targetPylon.getConnectionType()){
									if (world.isRemote)
										player.sendMessage(new TextComponentTranslation("chat.wiring.notcompatible"));
								}
							}
						}
					}
				}
			}
		} else { // Say distance if not on pylon
			if(player.isSneaking()){
				if(stack.hasTagCompound()) {
					stack.setTagCompound(null);
					if (world.isRemote)
						player.sendMessage(new TextComponentTranslation("chat.wiring.cleared"));
				}
			} else {
				if(stack.hasTagCompound() && world.isRemote) {
					final int x1 = stack.getTagCompound().getInteger("x");
					final int y1 = stack.getTagCompound().getInteger("y");
					final int z1 = stack.getTagCompound().getInteger("z");

					final BlockPos vector = new BlockPos(x1, y1, z1).subtract(pos);
					final int distance = (int)MathHelper.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
					
					player.sendMessage(new TextComponentTranslation("chat.wiring.measure", distance));
				}
			}
		}
		player.swingArm(hand);
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> tooltip, final ITooltipFlag flagIn) {
		if (stack.getTagCompound() != null) {
			final int x1 = stack.getTagCompound().getInteger("x");
			final int y1 = stack.getTagCompound().getInteger("y");
			final int z1 = stack.getTagCompound().getInteger("z");
			tooltip.add(I18nUtil.resolveKey("desc.wiring.start", x1, y1, z1));
		} else {
			tooltip.add(I18nUtil.resolveKey("desc.wiring.1"));
			tooltip.add(I18nUtil.resolveKey("desc.wiring.2"));
			tooltip.add(I18nUtil.resolveKey("desc.wiring.3"));
			tooltip.add(I18nUtil.resolveKey("desc.wiring.4"));
			
		}
	}
	
	public boolean isLengthValid(final int x1, final int y1, final int z1, final int x2, final int y2, final int z2, final int length) {
		final double l = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
		
		return l <= length;
	}
}
