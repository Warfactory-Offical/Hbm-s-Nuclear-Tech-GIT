package com.hbm.blocks.machine;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.lib.ForgeDirection;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityMachineIGenerator;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public class MachineIGenerator extends BlockDummyable {

	public MachineIGenerator(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		if(meta >= 12)
			return new TileEntityMachineIGenerator();
		
		if(meta >= extra)
			return new TileEntityProxyCombo(false, true, true);
		
		return null;
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos1, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			
			final int[] pos = this.findCore(world, pos1.getX(), pos1.getY(), pos1.getZ());
			
			if(pos == null)
				return false;
			
			final TileEntityMachineIGenerator gen = (TileEntityMachineIGenerator)world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
			
			if(gen != null)
				FMLNetworkHandler.openGui(player, MainRegistry.instance, ModBlocks.guiID_machine_industrial_generator, world, pos[0], pos[1], pos[2]);
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int[] getDimensions() {
		return new int [] {1,0,2,2,2,4};
	}

	@Override
	public int getOffset() {
		return 2;
	}
	
	@Override
	protected boolean checkRequirement(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int [] {5,0,2,2,8,-2}, x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int [] {4,0,2,2,-4,8}, x, y, z, dir))
			return false;
		if(!MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int [] {3,-2,1,1,-1,3}, x, y, z, dir))
			return false;
        return MultiblockHandlerXR.checkSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, new int[]{4, -2, 1, 1, 1, 0}, x, y, z, dir);
    }
	
	@Override
	protected void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o) {
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(), this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int [] {5,0,2,2,8,-2}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int [] {4,0,2,2,-4,8}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int [] {3,-2,1,1,-1,3}, this, dir);
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o , y + dir.offsetY * o, z + dir.offsetZ * o, new int [] {4,-2,1,1,1,0}, this, dir);
		
		final int[] rot = MultiblockHandlerXR.rotate(new int [] {1,0,2,2,8,8}, dir.toEnumFacing());
		
		for(int iy = 0; iy <= 1; iy++) {
			for(int ix = -rot[4]; ix <= rot[5]; ix++) {
				for(int iz = -rot[2]; iz <= rot[3]; iz++) {
					
					if(ix == -rot[4] || ix == rot[5] || iz == -rot[2] || iz == rot[3]) {
						this.makeExtra(world, x + dir.offsetX * o + ix, y + iy, z + dir.offsetZ * o + iz);
					}
				}
			}
		}
	}

}
