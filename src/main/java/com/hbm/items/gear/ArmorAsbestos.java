package com.hbm.items.gear;

import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.render.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class ArmorAsbestos extends ItemArmor implements ISpecialArmor {

	private final ResourceLocation asbestosBlur = new ResourceLocation(RefStrings.MODID + ":textures/misc/overlay_asbestos.png");
	
	public ArmorAsbestos(final ArmorMaterial materialIn, final int renderIndexIn, final EntityEquipmentSlot equipmentSlotIn, final String s) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(CreativeTabs.COMBAT);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public String getArmorTexture(final ItemStack stack, final Entity entity, final EntityEquipmentSlot slot, final String type) {
		if(stack.getItem().equals(ModItems.asbestos_helmet) || stack.getItem().equals(ModItems.asbestos_plate) || stack.getItem().equals(ModItems.asbestos_boots)) {
			return (RefStrings.MODID + ":textures/armor/asbestos_1.png");
		}
		if(stack.getItem().equals(ModItems.asbestos_legs)) {
			return (RefStrings.MODID + ":textures/armor/asbestos_2.png");
		}
		return null;
	}
	
	@Override
	public ArmorProperties getProperties(final EntityLivingBase player, final ItemStack armor, final DamageSource source, final double damage, final int slot) {
		if(source.isFireDamage())
		{
			return new ArmorProperties(1, 1, MathHelper.floor(999999999));
		}
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(final EntityPlayer player, final ItemStack armor, final int slot) {
		if(slot == 0)
		{
			return 3;
		}
		if(slot == 1)
		{
			return 8;
		}
		if(slot == 2)
		{
			return 6;
		}
		if(slot == 3)
		{
			return 3;
		}
		return 0;
	}

	@Override
	public void damageArmor(final EntityLivingBase entity, final ItemStack stack, final DamageSource source, final int damage, final int slot) {
		stack.damageItem(damage, entity);
	}
	
	@Override
	public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
		player.extinguish();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(final ItemStack stack, final EntityPlayer player, final ScaledResolution resolution, final float partialTicks) {
		if(this != ModItems.asbestos_helmet)
    		return;
    	

        GlStateManager.disableDepth();
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(asbestosBlur);
        RenderHelper.startDrawingTexturedQuads();
        RenderHelper.addVertexWithUV(0.0D, resolution.getScaledHeight(), -90.0D, 0.0D, 1.0D);
        RenderHelper.addVertexWithUV(resolution.getScaledWidth(), resolution.getScaledHeight(), -90.0D, 1.0D, 1.0D);
        RenderHelper.addVertexWithUV(resolution.getScaledWidth(), 0.0D, -90.0D, 1.0D, 0.0D);
        RenderHelper.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        RenderHelper.draw();
        GL11.glDepthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
