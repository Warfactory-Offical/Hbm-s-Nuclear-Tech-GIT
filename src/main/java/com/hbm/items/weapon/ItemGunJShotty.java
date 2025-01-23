package com.hbm.items.weapon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;

import com.hbm.animloader.AnimationWrapper.EndResult;
import com.hbm.animloader.AnimationWrapper.EndType;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.handler.HbmShaderManager2;
import com.hbm.main.MainRegistry;
import com.hbm.main.ResourceManager;
import com.hbm.particle.ParticleBatchRenderer;
import com.hbm.particle.bullet_hit.ParticleSmokeAnim;
import com.hbm.render.LightRenderer;
import com.hbm.render.RenderHelper;
import com.hbm.render.anim.HbmAnimations;
import com.hbm.render.anim.HbmAnimations.AnimType;
import com.hbm.render.anim.HbmAnimations.BlenderAnimation;
import com.hbm.render.item.weapon.ItemRenderJShotgun;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGunJShotty extends ItemGunBase {

	public ItemGunJShotty(final GunConfiguration config, final String s) {
		super(config, s);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void startAnim(final EntityPlayer player, final ItemStack stack, final int slot, final AnimType type) {
		switch(type){
		case RELOAD:
			final int bullets = getMag(stack);
			if(bullets == 1){
				HbmAnimations.hotbar[slot] = new BlenderAnimation(stack.getItem().getTranslationKey(), System.currentTimeMillis(), 1, ResourceManager.jshotgun_anim0, new EndResult(EndType.END));
			} else if(bullets == 0){
				HbmAnimations.hotbar[slot] = new BlenderAnimation(stack.getItem().getTranslationKey(), System.currentTimeMillis(), 1, ResourceManager.jshotgun_anim1, new EndResult(EndType.END));
			}
		break;
		case CYCLE:
			if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
				MainRegistry.proxy.setRecoil(10F);
			final float partialTicks = MainRegistry.proxy.partialTicks();
			final Vec3d start = new Vec3d(-0.28, -0.1, 2).rotatePitch(-(float) Math.toRadians(player.rotationPitch)).rotateYaw(-(float) Math.toRadians(player.rotationYaw)).add(player.getPositionEyes(partialTicks));
			Vec3d look = player.getLook(partialTicks);
			look = BobMathUtil.randVecInCone(look, 20);
			look = look.scale(0.1F);
			ParticleBatchRenderer.addParticle(new ParticleSmokeAnim(player.world, start.x, start.y, start.z, 0.05F, 4, 0.15F, 5).gravity(-0.01F).motion((float)look.x, (float)look.y, (float)look.z).color(1, 1, 1, 0.5F));
		default:
			super.startAnim(player, stack, slot, type);
		}
	}
	
	@Override
	protected EntityBulletBase getBulletEntity(final World world, final EntityPlayer player, final ItemStack stack, final int config, final EnumHand hand) {
		final EntityBulletBase b = super.getBulletEntity(world, player, stack, config, hand);
		b.overrideDamage = 4 + world.rand.nextInt(7);
		b.overrideMaxAge = 4+world.rand.nextInt(3);
		b.overrideStyle(BulletConfiguration.STYLE_TRACER);
		return b;
	}
	
	@Override
	protected int getBullets(final World world, final EntityPlayer player, final EnumHand hand, final BulletConfiguration config) {
		return 8 + world.rand.nextInt(5);
		//return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void playerWorldRender(final EntityPlayer player, final RenderWorldLastEvent e, final EnumHand hand) {
		if(hand == EnumHand.MAIN_HAND){
			final float partialTicks = e.getPartialTicks();
			Vec3d start = new Vec3d(-0.28, -0.1, 2).rotatePitch(-(float) Math.toRadians(player.rotationPitch)).rotateYaw(-(float) Math.toRadians(player.rotationYaw)).add(player.getPositionEyes(partialTicks));
			Vec3d direction = player.getLook(partialTicks);
			if(getFlashlightActive(player.getHeldItemMainhand())){
				if(ItemRenderJShotgun.firstPersonFlashlightPos != null && player == Minecraft.getMinecraft().player && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0){
					final Vec3d world = RenderHelper.unproject_world(HbmShaderManager2.inv_ViewProjectionMatrix, ItemRenderJShotgun.firstPersonFlashlightPos[0], ItemRenderJShotgun.firstPersonFlashlightPos[1], 1);
					final Vec3d eyePos = player.getPositionEyes(partialTicks);
					final Entity ent = Minecraft.getMinecraft().getRenderViewEntity();
					final double rPosX = ent.prevPosX + (ent.posX-ent.prevPosX)*partialTicks;
					final double rPosY = ent.prevPosY + (ent.posY-ent.prevPosY)*partialTicks;
					final double rPosZ = ent.prevPosZ + (ent.posZ-ent.prevPosZ)*partialTicks;
					final Vec3d camPos = ActiveRenderInfo.getCameraPosition().add(rPosX, rPosY, rPosZ);
					start = camPos.add(world.subtract(eyePos).normalize().scale(1));
					
					final Vec3d dir = BobMathUtil.viewToLocal(new Vector4f((float)ItemRenderJShotgun.flashlightDirection.x, (float)ItemRenderJShotgun.flashlightDirection.y, (float)ItemRenderJShotgun.flashlightDirection.z, 0))[0].normalize();
					direction = new Vec3d(dir.x, dir.y, dir.z);
				}
				LightRenderer.addFlashlight(start, start.add(direction.scale(30)), 20, 500, ResourceManager.fl_cookie, true, true);
			}
			if(getDelay(player.getHeldItemMainhand()) == mainConfig.rateOfFire && partialTicks < 0.9F){
				LightRenderer.addPointLight(start, new Vec3d(1, 0.72, 0.46), 15);
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand handIn) {
		final ItemStack stack = playerIn.getHeldItem(handIn);
		setFlashlightActive(stack, !getFlashlightActive(stack));
		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 1, 1);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderHUD(final Pre event, final ElementType type, final EntityPlayer player, final ItemStack stack, final EnumHand hand) {
		super.renderHUD(event, type, player, stack, hand);
		if(type == ElementType.CROSSHAIRS){
			event.setCanceled(true);
			final ScaledResolution res = event.getResolution();
			final float x = res.getScaledWidth()/2;
			final float y = res.getScaledHeight()/2;

			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.shotgun_crosshair);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		        GlStateManager.enableBlend();
		        GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
		        GlStateManager.color(0.7F, 1, 0.5F, 1);
		        RenderHelper.drawGuiRect(x - 20F, y - 20F, 0, 0, 40, 40, 1, 1);
		        GlStateManager.color(1, 1, 1, 1);
		        GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
		        GlStateManager.disableBlend();
	        GL11.glPopMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(Gui.ICONS);
		}
	}
	
	public static void setFlashlightActive(final ItemStack stack, final boolean b) {
		writeNBT(stack, "flashlightActive", b ? 1 : 0);
	}

	public static boolean getFlashlightActive(final ItemStack stack) {
		return readNBT(stack, "flashlightActive") == 1;
	}
}
