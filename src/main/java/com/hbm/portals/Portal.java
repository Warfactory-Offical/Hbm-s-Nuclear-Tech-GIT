package com.hbm.portals;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Portal {

	Vec3d bottomLeftCorner;
	Vec3d bottomRightCorner;
	Vec3d topLeftCorner;
	Vec3d topRightCorner;
	double posX;
	double posY;
	double posZ;
	EnumFacing rotation;
	Portal linkedPortal;
	World world;
	Entity dummyRenderEntity;
	
	public Portal(final World world, final Vec3d blc, final Vec3d brc, final Vec3d tlc, final Vec3d trc, final EnumFacing dir) {
		this.bottomLeftCorner = blc;
		this.bottomRightCorner = brc;
		this.topLeftCorner = tlc;
		this.topRightCorner = trc;
		this.rotation = dir;
		this.world = world;
		if(!world.isRemote)
			dummyRenderEntity = new DummyRenderEntity(world);
		if(!world.isRemote)
			PortalManager.ALL_RENDER_PORTALS.add(this);
	}
	
	public Portal link(final Portal portal){
		this.linkedPortal = portal;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	public void render(){
	}
}
