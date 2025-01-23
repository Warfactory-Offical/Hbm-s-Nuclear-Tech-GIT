package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntitySoyuzLauncher;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoyuzLauncher extends BlockDummyable {

	public SoyuzLauncher(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta == 2 || meta == 3)
			return new TileEntityProxyCombo(false, true, true);
		if(meta >= ForgeDirection.UNKNOWN.ordinal())
			return new TileEntitySoyuzLauncher();

		return null;
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			final int[] pos1 = this.findCore(world, pos.getX(), pos.getY(), pos.getZ());

			if(pos1 == null)
				return false;

			final TileEntitySoyuzLauncher entity = (TileEntitySoyuzLauncher) world.getTileEntity(new BlockPos(pos1[0], pos1[1], pos1[2]));
			if(entity != null) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_soyuz_launcher, world, pos1[0], pos1[1], pos1[2]);
			}
			return true;
		} else {
			return false;
		}
	}

	public static final int height = 4;

	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase player, final ItemStack itemStack) {
		if(!(player instanceof EntityPlayer pl))
			return;

        final EnumHand hand = player.getHeldItemMainhand() == itemStack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;

		final int o = -getOffset();

		final ForgeDirection dir = ForgeDirection.EAST;

		if(!checkRequirement(world, pos.getX(), pos.getY(), pos.getZ(), dir, o)) {
			world.setBlockToAir(pos);

			if(!pl.capabilities.isCreativeMode) {
				final ItemStack stack = pl.inventory.mainInventory.get(pl.inventory.currentItem);
				final Item item = Item.getItemFromBlock(this);

				if(stack == null) {
					pl.inventory.mainInventory.set(pl.inventory.currentItem, ItemStackUtil.itemStackFrom(this));
				} else {
					if(stack.getItem() != item || stack.getCount() == stack.getMaxStackSize()) {
						pl.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(this));
					} else {
						pl.getHeldItem(hand).grow(1);
					}
				}
			}

			return;
		}

		final int x = pos.getX();
		final int y = pos.getY();
		final int z = pos.getZ();

		world.setBlockState(new BlockPos(x + dir.offsetX * o, y + dir.offsetY * o + height, z + dir.offsetZ * o), this.getDefaultState().withProperty(META, dir.ordinal() + offset), 3);
		fillSpace(world, x, y, z, dir, o);
		world.scheduleUpdate(pos, this, 1);
		world.scheduleUpdate(pos, this, 2);

	}

	@Override
	public boolean checkRequirement(final World world, int x, int y, int z, final ForgeDirection dir, final int o) {
		x = x + dir.offsetX * o;
		y = y + dir.offsetY * o + height;
		z = z + dir.offsetZ * o;

		if(!MultiblockHandlerXR.checkSpace(world, x, y, z, new int[] { 0, 1, 6, 6, 6, 6 }, x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x, y, z, new int[] { -2, 4, -3, 6, -3, 6 }, x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x, y, z, new int[] { -2, 4, 6, -3, -3, 6 }, x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x, y, z, new int[] { -2, 4, 6, -3, 6, -3 }, x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x, y, z, new int[] { -2, 4, -3, 6, 6, -3 }, x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x, y, z, new int[] { 0, 4, 1, 1, -6, 8 }, x, y, z, dir))
			return false;
        return MultiblockHandlerXR.checkSpace(world, x, y, z, new int[]{0, 4, 2, 2, 9, -5}, x, y, z, dir);
    }

	@Override
	public void fillSpace(final World world, int x, int y, int z, final ForgeDirection dir, final int o) {
		x = x + dir.offsetX * o;
		y = y + dir.offsetY * o + height;
		z = z + dir.offsetZ * o;

		MultiblockHandlerXR.fillSpace(world, x, y, z, new int[] { 0, 1, 6, 6, 6, 6 }, this, dir);
		MultiblockHandlerXR.fillSpace(world, x, y, z, new int[] { -2, 4, -3, 6, -3, 6 }, this, dir);
		MultiblockHandlerXR.fillSpace(world, x, y, z, new int[] { -2, 4, 6, -3, -3, 6 }, this, dir);
		MultiblockHandlerXR.fillSpace(world, x, y, z, new int[] { -2, 4, 6, -3, 6, -3 }, this, dir);
		MultiblockHandlerXR.fillSpace(world, x, y, z, new int[] { -2, 4, -3, 6, 6, -3 }, this, dir);
		MultiblockHandlerXR.fillSpace(world, x, y, z, new int[] { 0, 4, 1, 1, -6, 8 }, this, dir);
		MultiblockHandlerXR.fillSpace(world, x, y, z, new int[] { 0, 4, 2, 2, 9, -5 }, this, dir);

	}

	@Override
	public int[] getDimensions() {
		// because we'll implement our own gnarly behavior here
		return new int[] { 0, 0, 0, 0, 0, 0 };
	}

	@Override
	public int getOffset() {
		return 0;
	}

	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		if(world.getTileEntity(pos) != null) {
			InventoryHelper.dropInventoryItems(world, pos, world.getTileEntity(pos));
			final int x = pos.getX();
			final int y = pos.getY();
			final int z = pos.getZ();
			for(int l = 0; l < 10; l++)
				world.spawnEntity(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, ItemStackUtil.itemStackFrom(ModBlocks.struct_launcher, 38)));
			for(int l = 0; l < 8; l++)
				world.spawnEntity(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, ItemStackUtil.itemStackFrom(ModBlocks.concrete_smooth, 41)));
			for(int l = 0; l < 6; l++)
				world.spawnEntity(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, ItemStackUtil.itemStackFrom(ModBlocks.struct_scaffold, 64)));
			world.spawnEntity(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, ItemStackUtil.itemStackFrom(ModBlocks.struct_scaffold, 53)));
			world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

}
