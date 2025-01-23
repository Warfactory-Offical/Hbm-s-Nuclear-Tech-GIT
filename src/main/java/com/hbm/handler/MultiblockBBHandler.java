package com.hbm.handler;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.hbm.blocks.ModBlocks;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class MultiblockBBHandler {

	public static final MultiblockBounds FENSU_BOUNDS = load(new ResourceLocation(RefStrings.MODID, "multiblock_bounds/bb_fensu0.mbb"));
	
	public static final Map<Block, MultiblockBounds> REGISTRY = new HashMap<>();
	
	public static MultiblockBounds load(final ResourceLocation loc){
		try {
			final InputStream s = MainRegistry.class.getResourceAsStream("/assets/"+loc.getNamespace()+"/"+loc.getPath());
			return parse(ByteBuffer.wrap(IOUtils.toByteArray(s)));
		} catch(final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static MultiblockBounds parse(final ByteBuffer buf){
		buf.order(ByteOrder.LITTLE_ENDIAN);
		final int version = buf.getInt();
		final int offsetX = buf.getInt();
		final int offsetY = buf.getInt();
		final int offsetZ = buf.getInt();
		final AxisAlignedBB[] boundingBoxes = new AxisAlignedBB[buf.getInt()];
		final int numBlocks = buf.getInt();
		
		final Map<BlockPos, AxisAlignedBB[]> blocks = new HashMap<>();
		
		for(int i = 0; i < boundingBoxes.length; i ++){
			boundingBoxes[i] = new AxisAlignedBB(buf.getFloat(), buf.getFloat(), buf.getFloat(), buf.getFloat(), buf.getFloat(), buf.getFloat());
		}
		for(int i = 0; i < numBlocks; i ++){
			final BlockPos pos = new BlockPos(buf.getFloat(), buf.getFloat(), buf.getFloat());
			final AxisAlignedBB[] blockBoxes = new AxisAlignedBB[buf.getInt()];
			for(int j = 0; j < blockBoxes.length; j ++){
				blockBoxes[j] = new AxisAlignedBB(buf.getFloat(), buf.getFloat(), buf.getFloat(), buf.getFloat(), buf.getFloat(), buf.getFloat());
			}
			blocks.put(pos, blockBoxes);
		}
		
		return new MultiblockBounds(boundingBoxes, blocks);
	}
	
	public static void init(){
		REGISTRY.put(ModBlocks.machine_fensu, FENSU_BOUNDS);
	}
	
	public static class MultiblockBounds {
		public AxisAlignedBB[] boxes;
		public Map<BlockPos, AxisAlignedBB[]> blocks;
		
		public MultiblockBounds(final AxisAlignedBB[] boxes, final Map<BlockPos, AxisAlignedBB[]> blocks) {
			this.boxes = boxes;
			this.blocks = blocks;
		}
	}
	
}
