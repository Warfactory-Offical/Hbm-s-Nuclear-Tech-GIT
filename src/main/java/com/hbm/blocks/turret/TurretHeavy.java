package com.hbm.blocks.turret;

import com.hbm.entity.particle.EntityGasFlameFX;
import com.hbm.entity.projectile.EntityBullet;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.tileentity.turret.TileEntityTurretHeavy;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TurretHeavy extends TurretBase {

	public TurretHeavy(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityTurretHeavy();
	}

	@Override
	public boolean executeHoldAction(final World world, final int i, final double yaw, double pitch, final BlockPos pos) {
		boolean flag = false;
		
		if(pitch < -60)
			pitch = -60;
		if(pitch > 30)
			pitch = 30;
		
		final int x = pos.getX();
		final int y = pos.getY();
		final int z = pos.getZ();
		
		if(i != 0 && i % 20 == 0) {
			final Vec3d vector = new Vec3d(
					-Math.sin(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI),
					-Math.sin(pitch / 180.0F * (float) Math.PI),
					Math.cos(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI));
			
			vector.normalize();
			
			final TileEntityTurretHeavy te = (TileEntityTurretHeavy)world.getTileEntity(pos);
			te.recoil = 0.5D;

			if(!world.isRemote) {
				final EntityBullet bullet = new EntityBullet(world);
				bullet.posX = x + vector.x * 1 + 0.5;
				bullet.posY = y + vector.y * 1 + 1;
				bullet.posZ = z + vector.z * 1 + 0.5;
				
				bullet.motionX = vector.x * 3;
				bullet.motionY = vector.y * 3;
				bullet.motionZ = vector.z * 3;
				
				bullet.damage = rand.nextInt(6) + 15;
				
				world.spawnEntity(bullet);
				
				final EntityGasFlameFX fx = new EntityGasFlameFX(world);
				fx.posX = x + vector.x * 2.2 + 0.5;
				fx.posY = y + vector.y * 2.2 + 1;
				fx.posZ = z + vector.z * 2.2 + 0.5;
				world.spawnEntity(fx);
			}

			world.playSound(null, x + 0.5, y + 0.5, z + 0.5, HBMSoundHandler.defabShoot, SoundCategory.BLOCKS, 1.0F, 0.75F);
			
			flag = true;
		}
		
		return flag;
	}

	@Override
	public void executeReleaseAction(final World world, final int i, final double yaw, final double pitch, final BlockPos pos) {}

}
