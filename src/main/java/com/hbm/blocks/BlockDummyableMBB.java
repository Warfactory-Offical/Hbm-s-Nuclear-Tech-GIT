package com.hbm.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hbm.handler.MultiblockBBHandler;
import com.hbm.handler.MultiblockBBHandler.MultiblockBounds;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class BlockDummyableMBB extends BlockDummyable {

	public BlockDummyableMBB(final Material materialIn, final String s) {
		super(materialIn, s);
	}
	
	@Override
	protected boolean checkRequirement(final World world, final int xx, final int yy, final int zz, final ForgeDirection dir, final int o) {
		final MultiblockBounds b = MultiblockBBHandler.REGISTRY.get(this);
		final Map<BlockPos, List<AxisAlignedBB>> blocks = new HashMap<>();
		for(final AxisAlignedBB unrotatedBox : b.boxes){
			final AxisAlignedBB box = rotate(unrotatedBox, dir.toEnumFacing());
			final int x1 = MathHelper.floor(box.minX);
			final int x2 = MathHelper.ceil(box.maxX);
			final int y1 = MathHelper.floor(box.minY);
			final int y2 = MathHelper.ceil(box.maxY);
			final int z1 = MathHelper.floor(box.minZ);
			final int z2 = MathHelper.ceil(box.maxZ);

			for(int x = x1; x <= x2; x++) {
				for(int y = y1; y <= y2; y++) {
					for(int z = z1; z <= z2; z++) {
						final BlockPos pos = new BlockPos(x, y, z);
						List<AxisAlignedBB> blockBBs = blocks.get(pos);
						if(blockBBs == null){
							blockBBs = new ArrayList<>();
							blocks.put(pos, blockBBs);
						}
						final AxisAlignedBB blockBB = clampToPos(box, pos).offset(-pos.getX(), -pos.getY(), -pos.getZ());
						if(volume(blockBB) == 0){
							if(blockBBs.size() == 0)
								blocks.remove(pos);
							continue;
						} else if(FULL_BLOCK_AABB.equals(blockBB)){
							blockBBs.add(FULL_BLOCK_AABB);
						} else {
							blockBBs.add(blockBB);
						}
					}
				}
			}
		}
		for(final BlockPos pos : blocks.keySet()){
			final BlockPos pos1 = pos.add(xx, yy, zz);
			if(pos1.getX() == xx && pos1.getY() == yy && pos1.getZ() == zz)
				continue;
			if(!world.getBlockState(pos1).getBlock().canPlaceBlockAt(world, pos1)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected void fillSpace(final World world, final int xxx, final int yyy, final int zzz, final ForgeDirection dir, final int o) {
		final int xx = xxx + dir.offsetX * o;
		final int yy = yyy + dir.offsetY * o;
		final int zz = zzz + dir.offsetZ * o;
		BlockDummyable.safeRem = true;
		final MultiblockBounds bbounds = MultiblockBBHandler.REGISTRY.get(this);
		final Map<BlockPos, List<AxisAlignedBB>> blocks = new HashMap<>();
		for(final AxisAlignedBB unrotatedBox : bbounds.boxes){
			final AxisAlignedBB box = rotate(unrotatedBox, dir.toEnumFacing());
			final int x1 = MathHelper.floor(box.minX);
			final int x2 = MathHelper.ceil(box.maxX);
			final int y1 = MathHelper.floor(box.minY);
			final int y2 = MathHelper.ceil(box.maxY);
			final int z1 = MathHelper.floor(box.minZ);
			final int z2 = MathHelper.ceil(box.maxZ);

			for(int x = x1; x <= x2; x++) {
				for(int y = y1; y <= y2; y++) {
					for(int z = z1; z <= z2; z++) {
						final BlockPos pos = new BlockPos(x, y, z);
						List<AxisAlignedBB> blockBBs = blocks.get(pos);
						if(blockBBs == null){
							blockBBs = new ArrayList<>();
							blocks.put(pos, blockBBs);
						}
						final AxisAlignedBB blockBB = clampToPos(box, pos).offset(-pos.getX(), -pos.getY(), -pos.getZ());
						if(volume(blockBB) == 0){
							if(blockBBs.size() == 0)
								blocks.remove(pos);
							continue;
						} else if(FULL_BLOCK_AABB.equals(blockBB)){
							blockBBs.add(FULL_BLOCK_AABB);
						} else {
							blockBBs.add(blockBB);
						}
					}
				}
			}
		}
		for(final BlockPos pos : blocks.keySet()){
			final BlockPos pos1 = pos.add(xx, yy, zz);
			int meta = 0;
			final int a = pos1.getX();
			final int b = pos1.getY();
			final int c = pos1.getZ();
			
			if(b < yy) {
				meta = ForgeDirection.DOWN.ordinal();
			} else if(b > yy) {
				meta = ForgeDirection.UP.ordinal();
			} else if(a < xx) {
				meta = ForgeDirection.WEST.ordinal();
			} else if(a > xx) {
				meta = ForgeDirection.EAST.ordinal();
			} else if(c < zz) {
				meta = ForgeDirection.NORTH.ordinal();
			} else if(c > zz) {
				meta = ForgeDirection.SOUTH.ordinal();
			} else {
				continue;
			}
			world.setBlockState(pos1, this.getStateFromMeta(meta), 3);
		}
		BlockDummyable.safeRem = false;
	}
	
	public AxisAlignedBB clampToPos(final AxisAlignedBB box, final BlockPos pos) {
		final AxisAlignedBB b = new AxisAlignedBB(MathHelper.clamp(box.minX, pos.getX(), pos.getX() + 1),
				MathHelper.clamp(box.minY, pos.getY(), pos.getY() + 1),
				MathHelper.clamp(box.minZ, pos.getZ(), pos.getZ() + 1),
				MathHelper.clamp(box.maxX, pos.getX(), pos.getX() + 1),
				MathHelper.clamp(box.maxY, pos.getY(), pos.getY() + 1),
				MathHelper.clamp(box.maxZ, pos.getZ(), pos.getZ() + 1));
		return b;
	}
	
	public double volume(final AxisAlignedBB box){
		return (box.maxX-box.minX)*(box.maxY-box.minY)*(box.maxZ-box.minZ);
	}
	
	public static AxisAlignedBB rotate(AxisAlignedBB box, final EnumFacing dir) {
		box = box.offset(-0.5, 0, -0.5);
		if(box == null)
			return null;
		
		if(dir == EnumFacing.SOUTH)
			return new AxisAlignedBB(box.minZ, box.minY, -box.minX, box.maxZ, box.maxY, -box.maxX).offset(0.5, 0, 0.5);
		
		if(dir == EnumFacing.NORTH) {
			return new AxisAlignedBB(-box.minZ, box.minY, box.minX, -box.maxZ, box.maxY, box.maxX).offset(0.5, 0, 0.5);
		}
		
		if(dir == EnumFacing.EAST) {
			return new AxisAlignedBB(-box.minX, box.minY, -box.minZ, -box.maxX, box.maxY, -box.maxZ).offset(0.5, 0, 0.5);
		}
		
		if(dir == EnumFacing.WEST) {
			return box.offset(0.5, 0, 0.5);
		}
		
		return box.offset(0.5, 0, 0.5);
	}
}
