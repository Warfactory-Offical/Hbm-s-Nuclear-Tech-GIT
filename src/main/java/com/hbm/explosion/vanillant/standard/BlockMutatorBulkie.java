package com.hbm.explosion.vanillant.standard;

import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.interfaces.IBlockMutator;
import com.hbm.inventory.RecipesCommon.MetaBlock;
import com.hbm.render.amlfrom1710.Vec3;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class BlockMutatorBulkie implements IBlockMutator {
	
	protected MetaBlock metaBlock;
	
	public BlockMutatorBulkie(Block block) {
		this(block, 0);
	}
	
	public BlockMutatorBulkie(Block block, int meta) {
		this.metaBlock = new MetaBlock(block, meta);
	}

	@Override
	public void mutatePre(ExplosionVNT explosion, Block block, int meta, int x, int y, int z) {
		if(!block.isNormalCube(block.getDefaultState(), explosion.world, new BlockPos(x, y, z))) return;
		Vec3 vec = Vec3.createVectorHelper(x + 0.5 - explosion.posX, y + 0.5 - explosion.posY, z + 0.5 - explosion.posZ);
		if(vec.lengthVector() >= explosion.size - 0.5) {
			explosion.world.setBlockState(new BlockPos(x, y, z), metaBlock.block.getDefaultState(), metaBlock.meta);
		}
	}

	@Override public void mutatePost(ExplosionVNT explosion, int x, int y, int z) { }
}
