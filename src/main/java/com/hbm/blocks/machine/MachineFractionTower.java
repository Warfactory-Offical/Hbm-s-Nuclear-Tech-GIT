package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.inventory.RefineryRecipes;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemForgeFluidIdentifier;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.oil.TileEntityMachineFractionTower;
import com.hbm.util.I18nUtil;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class MachineFractionTower extends BlockDummyable implements ILookOverlay {

	public MachineFractionTower(final Material mat, final String s) {
		super(mat, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		
		if(meta >= 12)
			return new TileEntityMachineFractionTower();
		if(meta >= extra)
			return new TileEntityProxyCombo(false, false, true);
		
		return null;
	}

	@Override
	public int[] getDimensions() {
		return new int[] {2, 0, 1, 1, 1, 1};
	}

	@Override
	public int getOffset() {
		return 1;
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos1, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(!world.isRemote && !player.isSneaking()) {
			
			if(player.getHeldItem(hand).isEmpty() || player.getHeldItem(hand).getItem() == ModItems.forge_fluid_identifier) {
				final int[] pos = this.findCore(world, pos1.getX(), pos1.getY(), pos1.getZ());
					
				if(pos == null)
					return false;
				
				final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
				
				if(!(te instanceof TileEntityMachineFractionTower frac))
					return false;

                if(player.getHeldItem(hand).isEmpty()) {
					if(world.isRemote){
						player.sendMessage(new TextComponentTranslation("chat.fractioning.y", pos[1]));

						for(int i = 0; i < frac.tanks.length; i++)
							player.sendMessage(new TextComponentTranslation(frac.types[i].getUnlocalizedName()).appendSibling(new TextComponentString(": " + frac.tanks[i].getFluidAmount() + "/" + frac.tanks[i].getCapacity() + "mB")));
					}
				} else {
					
					if(world.getTileEntity(new BlockPos(pos[0], pos[1] - 3, pos[2])) instanceof TileEntityMachineFractionTower) {
						if(world.isRemote){
							player.sendMessage(new TextComponentTranslation("chat.fractioning.onlybottom"));
						}
					} else {
						final Fluid type = ItemForgeFluidIdentifier.getType(player.getHeldItem(hand));
						if(RefineryRecipes.getFractions(type) == null){
							if(world.isRemote){
								player.sendMessage(new TextComponentTranslation("chat.fractioning.norecipe", type.getLocalizedName(new FluidStack(type, 1))));
							}
							return false;
						}
						
						frac.setTankType(0, type);
						frac.markDirty();
						if(world.isRemote){
							player.sendMessage(new TextComponentTranslation("chat.fractioning.changedto", I18n.format(type.getUnlocalizedName())));
						}
					}
				}
				
				return true;
			}
			return false;
			
		} else {
			return true;
		}
	}
	
	@Override
	public void fillSpace(final World world, int x, final int y, int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);
		
		x = x + dir.offsetX * o;
		z = z + dir.offsetZ * o;

		this.makeExtra(world, x + 1, y, z);
		this.makeExtra(world, x - 1, y, z);
		this.makeExtra(world, x, y, z + 1);
		this.makeExtra(world, x, y, z - 1);
	}

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		final int[] pos = this.findCore(world, x, y, z);
			
		if(pos == null)
			return;
		
		final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		
		if(!(te instanceof TileEntityMachineFractionTower frac))
			return;

        final List<String> text = new ArrayList();

		for(int i = 0; i < frac.types.length; i++){
			if(frac.types[i] != null){
				text.add((i < 1 ? ("§a" + "-> ") : ("§c" + "<- ")) + "§r" + frac.types[i].getLocalizedName(new FluidStack(frac.types[i], 1)) + ": " + frac.tanks[i].getFluidAmount() + "/" + frac.tanks[i].getCapacity() + "mB");
			}
		}

		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}
}