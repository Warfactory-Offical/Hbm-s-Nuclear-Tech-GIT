package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import com.hbm.lib.Library;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.BlockDummyable;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityChungus;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class MachineChungus extends BlockDummyable implements ILookOverlay {

	public MachineChungus(final Material mat, final String s) {
		super(mat, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		
		if(meta >= 12)
			return new TileEntityChungus();
		
		if(meta >= 6)
			return new TileEntityProxyCombo(false, true, true);
		
		return null;
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos1, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(!player.isSneaking()) {
			final int x = pos1.getX();
			final int y = pos1.getY();
			final int z = pos1.getZ();
			final int[] pos = this.findCore(world, x, y, z);

			if(pos == null)
				return true;

			final TileEntityChungus entity = (TileEntityChungus) world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
			if(entity != null) {
				
				final ForgeDirection dir = ForgeDirection.getOrientation(entity.getBlockMetadata() - offset);
				final ForgeDirection turn = dir.getRotation(ForgeDirection.DOWN);

				final int iX = entity.getPos().getX() + dir.offsetX + turn.offsetX * 2;
				final int iX2 = entity.getPos().getX() + dir.offsetX * 2 + turn.offsetX * 2;
				final int iZ = entity.getPos().getZ() + dir.offsetZ + turn.offsetZ * 2;
				final int iZ2 = entity.getPos().getZ() + dir.offsetZ * 2 + turn.offsetZ * 2;
				
				if((x == iX || x == iX2) && (z == iZ || z == iZ2) && y < entity.getPos().getY() + 2) {
					world.playSound(null, x + 0.5, y + 0.5, z + 0.5, HBMSoundHandler.chungus_lever, SoundCategory.BLOCKS, 1.5F, 1.0F);
					
					if(!world.isRemote) {
						int newFill = 0;
						if(entity.types[0] == ModForgeFluids.steam){
							entity.types[0] = ModForgeFluids.hotsteam;
							entity.types[1] = ModForgeFluids.steam;
							newFill = entity.tanks[0].getFluidAmount() / 10;
						} else if(entity.types[0] == ModForgeFluids.hotsteam){
							entity.types[0] = ModForgeFluids.superhotsteam;
							entity.types[1] = ModForgeFluids.hotsteam;
							newFill = entity.tanks[0].getFluidAmount() / 10;
						} else if(entity.types[0] == ModForgeFluids.superhotsteam){
							entity.types[0] = ModForgeFluids.ultrahotsteam;
							entity.types[1] = ModForgeFluids.superhotsteam;
							newFill = entity.tanks[0].getFluidAmount() / 10;
						} else if(entity.types[0] == ModForgeFluids.ultrahotsteam){
							entity.types[0] = ModForgeFluids.steam;
							entity.types[1] = ModForgeFluids.spentsteam;
							newFill = Math.min(entity.tanks[0].getFluidAmount() * 1000, entity.tanks[0].getCapacity());
						}
						entity.tanks[0].setFluid(new FluidStack(entity.types[0], newFill));
						entity.tanks[1].setFluid(null);
						
						entity.markDirty();
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public int[] getDimensions() {
		return new int[] { 3, 0, 0, 3, 2, 2 };
	}

	@Override
	public int getOffset() {
		return 3;
	}

	@Override
	public void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		super.fillSpace(world, x, y, z, dir, o);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {4, -4, 0, 3, 1, 1}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {3, 0, 6, -1, 1, 1}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {2, 0, 10, -7, 1, 1}, this, dir);
		world.setBlockState(new BlockPos(x + dir.offsetX, y + 2, z + dir.offsetZ), this.getDefaultState().withProperty(META, dir.ordinal()), 3);

		this.makeExtra(world, x + dir.offsetX, y + 2, z + dir.offsetZ);
		this.makeExtra(world, x + dir.offsetX * (o - 10), y, z + dir.offsetZ * (o - 10));
		final ForgeDirection side = dir.getRotation(ForgeDirection.UP);
		this.makeExtra(world, x + dir.offsetX * o + side.offsetX * 2 , y, z + dir.offsetZ * o + side.offsetZ * 2);
		this.makeExtra(world, x + dir.offsetX * o - side.offsetX * 2 , y, z + dir.offsetZ * o - side.offsetZ * 2);
	}

	@Override
	protected boolean checkRequirement(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {

		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), x, y, z, dir)) return false;
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {3, 0, 6, -1, 1, 1}, x, y, z, dir)) return false;
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int[] {2, 0, 10, -7, 1, 1}, x, y, z, dir)) return false;
        return world.getBlockState(new BlockPos(x + dir.offsetX, y + 2, z + dir.offsetZ)).getBlock().canPlaceBlockAt(world, new BlockPos(x + dir.offsetX, y + 2, z + dir.offsetZ));
    }

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		final int[] pos = this.findCore(world, x, y, z);
		
		if(pos == null)
			return;
		
		final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		
		if(!(te instanceof TileEntityChungus chungus))
			return;

        final List<String> text = new ArrayList();
		text.add(Library.getShortNumber(chungus.power) + "/" + Library.getShortNumber(TileEntityChungus.maxPower) + " HE");
		text.add("§a-> §r" + Library.getShortNumber(20 * chungus.powerProduction) + "HE/s");
		if(chungus.types[0] != null)
			text.add("§a-> §r" + chungus.types[0].getLocalizedName(new FluidStack(chungus.types[0], 1)) + ": " + chungus.tanks[0].getFluidAmount() + "/" + chungus.tanks[0].getCapacity() + "mB");
		if(chungus.types[1] != null)
			text.add("§c<- §r" + chungus.types[1].getLocalizedName(new FluidStack(chungus.types[1], 1)) + ": " + chungus.tanks[1].getFluidAmount() + "/" + chungus.tanks[1].getCapacity() + "mB");
		ILookOverlay.printGeneric(event, getLocalizedName(), 0xffff00, 0x404000, text);
	}
}