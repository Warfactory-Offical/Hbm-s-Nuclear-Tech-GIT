package com.hbm.entity.missile;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.bomb.BlockTaint;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityBlackHole;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.effect.EntityEMPBlast;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.explosion.ExplosionNukeGeneric;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityMissileTier0 extends EntityMissileBaseNT {

	public EntityMissileTier0(World world) { super(world); }
	public EntityMissileTier0(World world, float x, float y, float z, int a, int b) { super(world, x, y, z, a, b); }

	@Override
	public List<ItemStack> getDebris() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		// gottverdammt Alcater, where are you when we need you..
		//list.add(new ItemStack(ModItems.wire_fine, 4, Mats.MAT_ALUMINIUM.id));
		list.add(new ItemStack(ModItems.plate_titanium, 4));
		//list.add(new ItemStack(ModItems.shell, 2, Mats.MAT_ALUMINIUM.id));
		list.add(new ItemStack(ModItems.ducttape, 1));
		return list;
	}

	@Override
	protected float getContrailScale() {
		return 0.5F;
	}
	
	public static class EntityMissileMicro extends EntityMissileTier0 {
		public EntityMissileMicro(World world) { super(world); }
		public EntityMissileMicro(World world, float x, float y, float z, int a, int b) { super(world, x, y, z, a, b); }
		@Override public void onImpact() {
			if (!this.world.isRemote)
			{

				this.world.spawnEntity(EntityNukeExplosionMK5.statFac(world, BombConfig.fatmanRadius, posX, posY, posZ));

				if(MainRegistry.polaroidID == 11 || rand.nextInt(100) == 0){
					EntityNukeTorex.statFacBale(world, this.posX, this.posY, this.posZ, BombConfig.fatmanRadius);
				} else {
					EntityNukeTorex.statFac(world, this.posX, this.posY, this.posZ, BombConfig.fatmanRadius);
				}
			}
		}
		@Override public ItemStack getDebrisRareDrop() { return ItemStack.EMPTY; }//return DictFrame.fromOne(ModItems.ammo_standard, EnumAmmo.NUKE_HIGH); }
		@Override public ItemStack getMissileItemForInfo() { return new ItemStack(ModItems.missile_micro); }
	}
	
	public static class EntityMissileSchrabidium extends EntityMissileTier0 {
		public EntityMissileSchrabidium(World world) { super(world); }
		public EntityMissileSchrabidium(World world, float x, float y, float z, int a, int b) { super(world, x, y, z, a, b); }
		@Override public void onImpact() {
			if (!this.world.isRemote)
			{
				EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(this.world);
				entity.posX = this.posX;
				entity.posY = this.posY;
				entity.posZ = this.posZ;
				if(!EntityNukeExplosionMK3.isJammed(this.world, entity)){
					entity.destructionRange = BombConfig.aSchrabRadius;
					entity.speed = 25;
					entity.coefficient = 1.0F;
					entity.waste = false;

					this.world.spawnEntity(entity);

					EntityCloudFleija cloud = new EntityCloudFleija(this.world, BombConfig.aSchrabRadius);
					cloud.posX = this.posX;
					cloud.posY = this.posY;
					cloud.posZ = this.posZ;
					this.world.spawnEntity(cloud);
				}
			}
		}
		@Override public ItemStack getDebrisRareDrop() { return null; }
		@Override public ItemStack getMissileItemForInfo() { return new ItemStack(ModItems.missile_schrabidium); }
	}
	
	public static class EntityMissileBHole extends EntityMissileTier0 {
		public EntityMissileBHole(World world) { super(world); }
		public EntityMissileBHole(World world, float x, float y, float z, int a, int b) { super(world, x, y, z, a, b); }
		@Override public void onImpact() {
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1.5F, true);
			EntityBlackHole bl = new EntityBlackHole(this.world, 1.5F);
			bl.posX = this.posX;
			bl.posY = this.posY;
			bl.posZ = this.posZ;
			this.world.spawnEntity(bl);
		}
		@Override public ItemStack getDebrisRareDrop() { return new ItemStack(ModItems.grenade_black_hole, 1); }
		@Override public ItemStack getMissileItemForInfo() { return new ItemStack(ModItems.missile_bhole); }
	}
	
	public static class EntityMissileTaint extends EntityMissileTier0 {
		public EntityMissileTaint(World world) { super(world); }
		public EntityMissileTaint(World world, float x, float y, float z, int a, int b) { super(world, x, y, z, a, b); }
		@Override public void onImpact() {
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, 10.0F, true);
			for(int i = 0; i < 100; i++) {
				int a = rand.nextInt(11) + (int) this.posX - 5;
				int b = rand.nextInt(11) + (int) this.posY - 5;
				int c = rand.nextInt(11) + (int) this.posZ - 5;
				BlockPos pos = new BlockPos(a, b, c);
				if(world.getBlockState(pos).getBlock().isReplaceable(world, pos) && BlockTaint.hasPosNeightbour(world, pos)) world.setBlockState(pos, ModBlocks.taint.getDefaultState());
			}
		}
		@Override public ItemStack getDebrisRareDrop() { return new ItemStack(ModItems.powder_spark_mix, 1); }
		@Override public ItemStack getMissileItemForInfo() { return new ItemStack(ModItems.missile_taint); }
	}
	
	public static class EntityMissileEMP extends EntityMissileTier0 {
		public EntityMissileEMP(World world) { super(world); }
		public EntityMissileEMP(World world, float x, float y, float z, int a, int b) { super(world, x, y, z, a, b); }
		@Override public void onImpact() {
			ExplosionNukeGeneric.empBlast(world, (int)posX, (int)posY, (int)posZ, 50);
			EntityEMPBlast wave = new EntityEMPBlast(world, 50);
			wave.posX = posX;
			wave.posY = posY;
			wave.posZ = posZ;
			world.spawnEntity(wave);
		}
		@Override public ItemStack getDebrisRareDrop() { return new ItemStack(ModBlocks.emp_bomb, 1); }
		@Override public ItemStack getMissileItemForInfo() { return new ItemStack(ModItems.missile_emp); }
	}
}
