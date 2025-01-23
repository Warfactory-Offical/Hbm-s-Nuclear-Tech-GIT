package com.hbm.physics;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticlePhysicsBlocks extends Particle {

	public RigidBody body;
	public AxisAlignedBB[] boxes;
	public BlockPos[] blocks;
	public BlockPos createPos;
	public boolean didInit = false;
	int callListId;

	public ParticlePhysicsBlocks(final World world, final double posXIn, final double posYIn, final double posZIn, final BlockPos createPos, final BlockPos[] blocks) {
		super(world, posXIn, posYIn, posZIn);
		this.blocks = blocks;
		this.createPos = createPos;
		init();
	}
	
	public void init(){
		final int offsetY = 0;
		body = new RigidBody(world, posX, posY, posZ);
		final ArrayList<AABBCollider> boxs = new ArrayList<>();
		for(final BlockPos pos : blocks){
			final ArrayList<AxisAlignedBB> boxes2 = new ArrayList<>();
			final IBlockState state = world.getBlockState(pos);
			state.addCollisionBoxToList(world, pos, TileEntity.INFINITE_EXTENT_AABB, boxes2, null, false);
			for(final AxisAlignedBB box : boxes2){
				final boolean light = state.getBlock() == Blocks.LEAVES || state.getBlock() == Blocks.LEAVES2;
				final float mass = light ? 0.25F : 1F;
				boxs.add(new AABBCollider(box.offset(-posX, -posY + offsetY, -posZ), mass));
			}
		}
		boxes = new AxisAlignedBB[boxs.size()];
		for(int i = 0; i < boxes.length; i ++){
			boxes[i] = boxs.get(i).box;
		}
		body.addColliders(boxs.toArray(new AABBCollider[0]));
		final Vec3d impulse = Minecraft.getMinecraft().player.getLookVec().scale(0.6*body.mass);
		body.impulseVelocity(new Vec3(impulse.x, 0, impulse.z), new Vec3(posX + 0.5, posY, posZ + 0.5));
		body.friction = 0.8F;
		particleMaxAge = 1000;
		
		
		callListId = GL11.glGenLists(1);
		GL11.glNewList(callListId, GL11.GL_COMPILE);
		final Tessellator tes = Tessellator.getInstance();
		final BufferBuilder buf = tes.getBuffer();
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		for(final BlockPos pos : blocks){
			final IBlockState state = world.getBlockState(pos);
			final IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModelSmooth(world, model, state, pos.add(0, offsetY, 0), buf, true, MathHelper.getPositionRandom(pos));
		}
		tes.draw();
		GL11.glEndList();
	}
	
	@Override
	public void onUpdate() {
		body.minecraftTimestep();
		this.particleAge ++;
		if(particleAge >= particleMaxAge){
			setExpired();
			GL11.glDeleteLists(callListId, 1);
		}
	}
	
	@Override
	public int getFXLayer() {
		return 3;
	}
	
	@Override
	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks, final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY, final float rotationXZ) {
		GL11.glPushMatrix();
		final double entPosX = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX)*partialTicks;
        final double entPosY = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY)*partialTicks;
        final double entPosZ = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ)*partialTicks;
        
        interpPosX = entPosX;
        interpPosY = entPosY;
        interpPosZ = entPosZ;
        GL11.glPushMatrix();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        
        GlStateManager.disableTexture2D();
		GlStateManager.glLineWidth(4);
		//RenderGlobal.drawSelectionBoundingBox(body.boundingBox.offset(-interpPosX, -interpPosY, -interpPosZ), 0, 1, 0, 1);
		GlStateManager.enableTexture2D();
        GL11.glPopMatrix();
        for(final Contact c : body.contacts.contacts){
			if(false){
				final Tessellator tes = Tessellator.getInstance();
				final BufferBuilder buf = tes.getBuffer();
				buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
				final Vec3 normal = c.normal.mult(0.5F);
				final Vec3 globalA = c.globalA.subtract(interpPosX, interpPosY, interpPosZ);
				final Vec3 globalB = c.globalB.subtract(interpPosX, interpPosY, interpPosZ);
				buf.pos(globalA.xCoord, globalA.yCoord, globalA.zCoord).color(0F, 0F, 1F, 1F).endVertex();
				buf.pos(globalA.xCoord-normal.xCoord, globalA.yCoord-normal.yCoord, globalA.zCoord-normal.xCoord).color(0F, 0F, 1F, 1F).endVertex();
				
				//buf.pos(globalB.xCoord, globalB.yCoord, globalB.zCoord).color(0F, 0F, 1F, 1F).endVertex();
				//buf.pos(globalB.xCoord+normal.xCoord, globalB.yCoord+normal.yCoord, globalB.zCoord+normal.xCoord).color(0F, 0F, 1F, 1F).endVertex();
				tes.draw();
				
				GL11.glPointSize(16);
				buf.begin(GL11.GL_POINTS, DefaultVertexFormats.POSITION_COLOR);
				buf.pos(globalA.xCoord, globalA.yCoord, globalA.zCoord).color(0F, 0F, 1F, 1F).endVertex();
				//buf.pos(globalB.xCoord, globalB.yCoord, globalB.zCoord).color(0F, 0F, 1F, 1F).endVertex();
				tes.draw();
			}
		}
        
		body.doGlTransform(new Vec3(interpPosX, interpPosY, interpPosZ), partialTicks);
		GL11.glTranslated(-createPos.getX(), -createPos.getY(), -createPos.getZ());
		
		GlStateManager.disableTexture2D();
		GlStateManager.glLineWidth(4);
		for(final AxisAlignedBB box : boxes){
			//RenderGlobal.drawSelectionBoundingBox(box, 1, collided ? 0 : 1, collided ? 0 : 1, 1);
		}
		GlStateManager.enableTexture2D();
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.enableCull();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GL11.glCallList(callListId);
		GlStateManager.shadeModel(GL11.GL_FLAT);
		
		GL11.glPopMatrix();
	}
}
