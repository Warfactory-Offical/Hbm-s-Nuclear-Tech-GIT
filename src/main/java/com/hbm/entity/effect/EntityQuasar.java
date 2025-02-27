package com.hbm.entity.effect;

import net.minecraft.world.World;

public class EntityQuasar extends EntityBlackHole {

	public EntityQuasar(final World world) {
		super(world);
		this.ignoreFrustumCheck = true;
		this.isImmuneToFire = true;
	}

	public EntityQuasar(final World world, final float size) {
		super(world);
		this.getDataManager().set(SIZE, size);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
}
