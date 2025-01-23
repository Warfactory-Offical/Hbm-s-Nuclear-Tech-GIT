package com.hbm.explosion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.BitSet;
import java.util.Map.Entry;
import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.config.CompatibilityConfig;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagLongArray;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ExplosionNukeRayBatched {

	public HashMap<ChunkPos, BitSet> perChunk = new HashMap<ChunkPos, BitSet>();
	public List<ChunkPos> orderedChunks = new ArrayList();
	private final CoordComparator comparator = new CoordComparator();
	public boolean isContained = true;
	int posX;
	int posY;
	int posZ;
	World world;

	int strength;
	int radius;

	int gspNumMax;
	int gspNum;
	double gspX;
	double gspY;

	private static final int maxY = 255;
	private static final int minY = 0;

	public boolean isAusf3Complete = false;
	public int rayCheckInterval = 100;

	public ExplosionNukeRayBatched(final World world, final int x, final int y, final int z, final int strength, final int radius) {
		this.world = world;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.strength = strength;
		this.radius = radius;

		// Total number of points
		this.gspNumMax = (int)(2.5 * Math.PI * Math.pow(this.strength, 2));
		this.gspNum = 1;

		// The beginning of the generalized spiral points
		this.gspX = Math.PI;
		this.gspY = 0.0;
		this.rayCheckInterval = 10000/radius;
	}

	private void generateGspUp(){
		if (this.gspNum < this.gspNumMax) {
			final int k = this.gspNum + 1;
			final double hk = -1.0 + 2.0 * (k - 1.0) / (this.gspNumMax - 1.0);
			this.gspX = Math.acos(hk);

			final double prev_lon = this.gspY;
			final double lon = prev_lon + 3.6 / Math.sqrt(this.gspNumMax) / Math.sqrt(1.0 - hk * hk);
			this.gspY = lon % (Math.PI * 2);
		} else {
			this.gspX = 0.0;
			this.gspY = 0.0;
		}
		this.gspNum++;
	}

	// Get Cartesian coordinates for spherical coordinates
	// 90 X-Axis rotation for more efficient chunk scanning
	private Vec3 getSpherical2cartesian(){
		final double dx = Math.sin(this.gspX) * Math.cos(this.gspY);
		final double dy = Math.sin(this.gspX) * Math.sin(this.gspY);
		final double dz = Math.cos(this.gspX);
		return Vec3.createVectorHelper(dx, dy, dz);
	}

	public void addPos(final int x, final int y, final int z){
		chunk = new ChunkPos(x >> 4, z >> 4);
		BitSet hitPositions = perChunk.get(chunk);
				
		if(hitPositions == null) {
			hitPositions = new BitSet(65536);
			perChunk.put(chunk, hitPositions); //we re-use the same pos instead of using individualized per-chunk ones to save on RAM
		}
		hitPositions.set(((255-y) << 8) + ((x - chunk.getXStart()) << 4) + (z - chunk.getZStart()));
	}

	int age = 0;
	public void collectTip(final int time) {
		if(!CompatibilityConfig.isWarDim(world)){
			isAusf3Complete = true;
			return;
		}
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		long raysProcessed = 0;
		final long start = System.currentTimeMillis();

		IBlockState blockState;
		Block b;
		int iX, iY, iZ, radius;
		float rayStrength;
		Vec3 vec;
		age++;
		if(age == 120){
			System.out.println("NTM C "+raysProcessed+" "+Math.round(10000D * 100D*gspNum/(double)gspNumMax)/10000D+"% "+gspNum+"/"+gspNumMax);
			age = 0;
		}
		while(this.gspNumMax >= this.gspNum){
			// Get Cartesian coordinates for spherical coordinates
			vec = this.getSpherical2cartesian();

			radius = (int)Math.ceil(this.radius);
			rayStrength = strength * 0.3F;

			//Finding the end of the ray
			for(int r = 0; r < radius+1; r ++) {

				iY = (int) Math.floor(posY + (vec.yCoord * r));
				
				if(iY < minY || iY > maxY){
					isContained = false;
					break;
				}

				iX = (int) Math.floor(posX + (vec.xCoord * r));
				iZ = (int) Math.floor(posZ + (vec.zCoord * r));


				pos.setPos(iX, iY, iZ);
				blockState = world.getBlockState(pos);
				b = blockState.getBlock();
				if(b.getExplosionResistance(null) >= 2_000_000)
					break;

				rayStrength -= Math.pow(getNukeResistance(blockState, b)+1, 3 * ((double) (r = (r > 0) ? r : 1)) / ((double) radius))-1;

				//save block positions in to-destroy-boolean[] until rayStrength is 0 
				if(rayStrength > 0){
					if(b != Blocks.AIR) {
						//all-air chunks don't need to be buffered at all
						addPos(iX, iY, iZ);
					}
					if(r >= radius) {
						isContained = false;
					}
				} else {
					break;
				}
			}
			
			// Raise one generalized spiral points
			this.generateGspUp();
			raysProcessed++;
			if(raysProcessed % rayCheckInterval == 0 && System.currentTimeMillis()+1 > start + time) {
				return;
			}
		} 
		orderedChunks.addAll(perChunk.keySet());
		orderedChunks.sort(comparator);
		
		isAusf3Complete = true;
	}
	
	public static float getNukeResistance(final IBlockState blockState, final Block b) {
		if(blockState.getMaterial().isLiquid()){
			return 0.1F;
		} else {
			if(b == Blocks.SANDSTONE) return 4F;
			if(b == Blocks.OBSIDIAN) return 18F;
			return b.getExplosionResistance(null);
		}
	}
	
	/** little comparator for roughly sorting chunks by distance to the center */
	public class CoordComparator implements Comparator<ChunkPos> {

		@Override
		public int compare(final ChunkPos o1, final ChunkPos o2) {

			final int chunkX = ExplosionNukeRayBatched.this.posX >> 4;
			final int chunkZ = ExplosionNukeRayBatched.this.posZ >> 4;

			final int diff1 = Math.abs((chunkX - (o1.getXStart() >> 4))) + Math.abs((chunkZ - (o1.getZStart() >> 4)));
			final int diff2 = Math.abs((chunkX - (o2.getXStart() >> 4))) + Math.abs((chunkZ - (o2.getZStart() >> 4)));
			
			return diff1 > diff2 ? 1 : diff1 < diff2 ? -1 : 0;
		}
	}

	public void processChunk(final int time){
		final long start = System.currentTimeMillis();
		while(System.currentTimeMillis() < start + time){
			processChunkBlocks(start, time);
		}
	}

	BitSet hitArray;
	ChunkPos chunk;
	boolean needsNewHitArray = true;
	int index = 0;

	public void processChunkBlocks(final long start, final int time){
		if(!CompatibilityConfig.isWarDim(world)){
			this.perChunk.clear();
		}
		if(this.perChunk.isEmpty()) return;
		if(needsNewHitArray){
			chunk = orderedChunks.get(0);
			hitArray = perChunk.get(chunk);
			index = hitArray.nextSetBit(0);
			needsNewHitArray = false;
		}
		
		final int chunkX = chunk.getXStart();
		final int chunkZ = chunk.getZStart();
		
		final MutableBlockPos pos = new BlockPos.MutableBlockPos();
		int blocksRemoved = 0;
		while(index > -1) {
			pos.setPos(((index >> 4) % 16) + chunkX, 255 - (index >> 8), (index % 16) + chunkZ);
			world.setBlockToAir(pos);
			index = hitArray.nextSetBit(index+1);
			blocksRemoved++;
			if(blocksRemoved % 256 == 0 && System.currentTimeMillis()+1 > start + time){
				break;
			}
		}

		if(index < 0){
			perChunk.remove(chunk);
			orderedChunks.remove(0);
			needsNewHitArray = true;
		}
	}
	
	public void readEntityFromNBT(final NBTTagCompound nbt) {
		radius = nbt.getInteger("radius");
		strength = nbt.getInteger("strength");
		posX = nbt.getInteger("posX");
		posY = nbt.getInteger("posY");
		posZ = nbt.getInteger("posZ");
		gspNumMax = (int)(2.5 * Math.PI * Math.pow(strength, 2));
		rayCheckInterval = 10000/radius;

		if(nbt.hasKey("gspNum")){
			gspNum = nbt.getInteger("gspNum");
			isAusf3Complete = nbt.getBoolean("f3");
			isContained = nbt.getBoolean("isContained");

			int i = 0;
			while(nbt.hasKey("chunks"+i)){
				final NBTTagCompound c = (NBTTagCompound)nbt.getTag("chunks"+i);

				perChunk.put(new ChunkPos(c.getInteger("cX"), c.getInteger("cZ")), BitSet.valueOf(getLongArray((NBTTagLongArray)c.getTag("cB"))));
				i++;
			}
			if(isAusf3Complete){
				orderedChunks.addAll(perChunk.keySet());
				orderedChunks.sort(comparator);
			}
		}
	}

	public void writeEntityToNBT(final NBTTagCompound nbt) {
		nbt.setInteger("radius", radius);
		nbt.setInteger("strength", strength);
		nbt.setInteger("posX", posX);
		nbt.setInteger("posY", posY);
		nbt.setInteger("posZ", posZ);
		
		if(BombConfig.enableNukeNBTSaving){
			nbt.setInteger("gspNum", gspNum);
			nbt.setBoolean("f3", isAusf3Complete);
			nbt.setBoolean("isContained", isContained);
		
			int i = 0;
			for(final Entry<ChunkPos, BitSet> e : perChunk.entrySet()){
				final NBTTagCompound c = new NBTTagCompound();
				c.setInteger("cX", e.getKey().x);
				c.setInteger("cZ", e.getKey().z);
				c.setTag("cB", new NBTTagLongArray(e.getValue().toLongArray()));
				nbt.setTag("chunks"+i, c.copy());
				i++;
			}
		}
	}

	// Who tf forgot to add a way to retrieve the long array from NBTTagLongArray??
	public static long[] getLongArray(final NBTTagLongArray nbt) {
		return ObfuscationReflectionHelper.getPrivateValue(NBTTagLongArray.class, nbt, 0);
	}
}
