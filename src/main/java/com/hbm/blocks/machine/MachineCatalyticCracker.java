package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.inventory.CrackRecipes;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.lib.ForgeDirection;
import com.hbm.items.machine.ItemForgeFluidIdentifier;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.oil.TileEntityMachineCatalyticCracker;
import com.hbm.util.I18nUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class MachineCatalyticCracker extends BlockDummyable implements ILookOverlay {

	public MachineCatalyticCracker(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		
		if(meta >= 12) return new TileEntityMachineCatalyticCracker();
		if(meta >= 6) return new TileEntityProxyCombo(false, false, true);
		
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {0, 0, 3, 3, 2, 3};
	}

	@Override
	public int getOffset() {
		return 3;
	}

	@Override
    public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
        return Item.getItemFromBlock(ModBlocks.machine_catalytic_cracker);
    }

    @Override
    public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
        return ItemStackUtil.itemStackFrom(ModBlocks.machine_catalytic_cracker);
    }
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos1, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		
		if(!world.isRemote && !player.isSneaking()) {

			if(!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() instanceof ItemForgeFluidIdentifier) {
				final int[] pos = this.findCore(world, pos1.getX(), pos1.getY(), pos1.getZ());
				if(pos == null)
					return false;
				
				final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

				if(!(te instanceof TileEntityMachineCatalyticCracker cracker))
					return false;

                final Fluid type = ItemForgeFluidIdentifier.getType(player.getHeldItem(hand));
				if(!CrackRecipes.hasRecipe(type)){
					player.sendMessage(new TextComponentString("§cNo recipe found for §e"+type.getLocalizedName(new FluidStack(type, 1))));
					return false;
				}
				cracker.setTankType(0, type);
				cracker.markDirty();
				player.sendMessage(new TextComponentString("§eRecipe changed to §a"+type.getLocalizedName(new FluidStack(type, 1))));
				
				return true;
			}
			return false;
			
		} else {
			return true;
		}
	}

	@Override
	protected boolean checkRequirement(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		return super.checkRequirement(world, x, y, z, dir, o) &&
				MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{4, -1, 3, -1, 1, 1}, x, y, z, dir) &&
				MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{13, 0, 0, 3, 2, 1}, x, y, z, dir) &&
				MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{14, -13, -1, 2, 1, 0}, x, y, z, dir) &&
				MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{3, -1, 2, 3, -1, 3}, x, y, z, dir);
	}

	@Override
	public void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);

		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{4, -1, 3, -1, 1, 1}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{13, 0, 0, 3, 2, 1}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{14, -13, -1, 2, 1, 0}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{3, -1, 2, 3, -1, 3}, this, dir);
		
		final ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

		this.makeExtra(world, x + dir.offsetX * o + dir.offsetX * 3 + rot.offsetX, y + dir.offsetY * o, z + dir.offsetZ * o + dir.offsetZ * 3 + rot.offsetZ);
		this.makeExtra(world, x + dir.offsetX * o + dir.offsetX * 3 - rot.offsetX * 2, y + dir.offsetY * o, z + dir.offsetZ * o + dir.offsetZ * 3 - rot.offsetZ * 2);
		this.makeExtra(world, x + dir.offsetX * o - dir.offsetX * 3 + rot.offsetX, y + dir.offsetY * o, z + dir.offsetZ * o - dir.offsetZ * 3 + rot.offsetZ);
		this.makeExtra(world, x + dir.offsetX * o - dir.offsetX * 3 - rot.offsetX * 2, y + dir.offsetY * o, z + dir.offsetZ * o - dir.offsetZ * 3 - rot.offsetZ * 2);

		this.makeExtra(world, x + dir.offsetX * o + dir.offsetX * 2 + rot.offsetX * 2, y + dir.offsetY * o, z + dir.offsetZ * o + dir.offsetZ * 2 + rot.offsetZ * 2);
		this.makeExtra(world, x + dir.offsetX * o + dir.offsetX * 2 - rot.offsetX * 3, y + dir.offsetY * o, z + dir.offsetZ * o + dir.offsetZ * 2 - rot.offsetZ * 3);
		this.makeExtra(world, x + dir.offsetX * o - dir.offsetX * 2 + rot.offsetX * 2, y + dir.offsetY * o, z + dir.offsetZ * o - dir.offsetZ * 2 + rot.offsetZ * 2);
		this.makeExtra(world, x + dir.offsetX * o - dir.offsetX * 2 - rot.offsetX * 3, y + dir.offsetY * o, z + dir.offsetZ * o - dir.offsetZ * 2 - rot.offsetZ * 3);
	}

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		final int[] pos = this.findCore(world, x, y, z);
		
		if(pos == null)
			return;
		
		final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		
		if(!(te instanceof TileEntityMachineCatalyticCracker cracker))
			return;

        final List<String> text = new ArrayList();

		for(int i = 0; i < cracker.types.length; i++)
			if(cracker.types[i] != null)
				text.add((i < 2 ? "§a-> " : "§c<- ") + "§r" + cracker.types[i].getLocalizedName(new FluidStack(cracker.types[i], 1)) + ": " + cracker.tanks[i].getFluidAmount() + "/" + cracker.tanks[i].getCapacity() + "mB");

		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}
}
