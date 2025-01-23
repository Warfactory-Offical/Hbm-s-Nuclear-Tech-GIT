package com.hbm.tileentity.machine;

import java.util.List;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityDemonLamp extends TileEntity implements ITickable {

	@Override
	public void update(){
		if(!world.isRemote) {
			radiate(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@SuppressWarnings("deprecation")
	private void radiate(final World world, final int x, final int y, final int z){

		final float rads = 100000F;
		final double range = 25D;

		final List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(x + 0.5, y + 0.5, z + 0.5, x + 0.5, y + 0.5, z + 0.5).grow(range, range, range));
		for(final EntityLivingBase e : entities) {

			Vec3 vec = Vec3.createVectorHelper(e.posX - (x + 0.5), (e.posY + e.getEyeHeight()) - (y + 0.5), e.posZ - (z + 0.5));
			final double len = vec.length();
			vec = vec.normalize();

			float res = 0;

			for(int i = 1; i < len; i++) {

				final int ix = (int)Math.floor(x + 0.5 + vec.xCoord * i);
				final int iy = (int)Math.floor(y + 0.5 + vec.yCoord * i);
				final int iz = (int)Math.floor(z + 0.5 + vec.zCoord * i);
				
				final BlockPos pos = new BlockPos(ix, iy, iz);
				final IBlockState state = world.getBlockState(pos);
				res += state.getBlock().getExplosionResistance(null);
			}

			if(res < 1)
				res = 1;

			float eRads = rads;
			eRads /= res;
			eRads /= (float)(len * len);

			ContaminationUtil.contaminate(e, HazardType.RADIATION, ContaminationType.CREATIVE, eRads);
			if(len < 2) {
				e.attackEntityFrom(DamageSource.IN_FIRE, 100);
			}
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox(){
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared(){
		return 65536.0D;
	}
}
