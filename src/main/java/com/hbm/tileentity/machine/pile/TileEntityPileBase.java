package com.hbm.tileentity.machine.pile;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.pile.BlockGraphiteRod;
import com.hbm.render.amlfrom1710.Vec3;

import api.hbm.block.IPileNeutronReceiver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class TileEntityPileBase extends TileEntity implements ITickable {

	@Override
	public abstract void update();
	
	protected void castRay(final int flux, final int range) {
		
		final Random rand = world.rand;
		final Vec3 vec = Vec3.createVectorHelper(1, 0, 0);
		vec.rotateAroundZ((float)(rand.nextDouble() * Math.PI * 2D));
		vec.rotateAroundY((float)(rand.nextDouble() * Math.PI * 2D));
		
		int prevX = pos.getX();
		int prevY = pos.getY();
		int prevZ = pos.getZ();
		
		for(float i = 1; i <= range; i += 0.5F) {

			final int x = (int)Math.floor(pos.getX() + 0.5 + vec.xCoord * i);
			final int y = (int)Math.floor(pos.getY() + 0.5 + vec.yCoord * i);
			final int z = (int)Math.floor(pos.getZ() + 0.5 + vec.zCoord * i);
			
			if(x == prevX && y == prevY && z == prevZ)
				continue;

			prevX = x;
			prevY = y;
			prevZ = z;
			
			final IBlockState b = world.getBlockState(new BlockPos(x, y, z));
			
			if(b.getBlock() == ModBlocks.block_boron)
				return;
			
			if(b == ModBlocks.block_graphite_rod && !b.getValue(BlockGraphiteRod.OUT))
				return;
			
			final TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
			
			if(te instanceof IPileNeutronReceiver rec) {
				
				//this part throttles neutron efficiency for reactions that are way too close, efficiency reaches 100% after 2.5 meters
				final float mult = Math.min(i / 2.5F, 1F);
				final int n = (int)(flux * mult);

                rec.receiveNeutrons(n);
				return;
			}
		}
	}
}
