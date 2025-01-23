package com.hbm.blocks.turret;

import com.hbm.entity.projectile.EntityFire;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.tileentity.turret.TileEntityTurretFlamer;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TurretFlamer extends TurretBase {

	public TurretFlamer(final Material materialIn, final String s) {
		super(materialIn, s);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityTurretFlamer();
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
		
		if(true) {
			final Vec3d vector = new Vec3d(
					-Math.sin(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI),
					-Math.sin(pitch / 180.0F * (float) Math.PI),
					Math.cos(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI));
			
			vector.normalize();
			
			if(!world.isRemote) {
				final EntityFire bullet = new EntityFire(world);
				bullet.posX = x + vector.x * 2 + 0.5;
				bullet.posY = y + vector.y * 2 + 1;
				bullet.posZ = z + vector.z * 2 + 0.5;
				
				bullet.motionX = vector.x * 3;
				bullet.motionY = vector.y * 3;
				bullet.motionZ = vector.z * 3;

				bullet.setDamage(2 + rand.nextInt(3));
				
				world.spawnEntity(bullet);
			}
			
			if(i == 0)
				world.playSound(null, x + 0.5, y + 0.5, z + 0.5, HBMSoundHandler.flamethrowerIgnite, SoundCategory.BLOCKS, 1.0F, 1.0F);
			else
				world.playSound(null, x + 0.5, y + 0.5, z + 0.5, HBMSoundHandler.flamethrowerShoot, SoundCategory.BLOCKS, 1.0F, 1.0F);
			
			flag = true;
		}
		
		return flag;
	}

	@Override
	public void executeReleaseAction(final World world, final int i, final double yaw, final double pitch, final BlockPos pos) {}

}
