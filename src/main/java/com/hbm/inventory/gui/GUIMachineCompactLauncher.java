package com.hbm.inventory.gui;

import com.hbm.util.I18nUtil;
import org.lwjgl.opengl.GL11;

import com.hbm.forgefluid.FFUtils;
import com.hbm.inventory.container.ContainerCompactLauncher;
import com.hbm.items.weapon.ItemCustomMissile;
import com.hbm.lib.RefStrings;
import com.hbm.render.misc.MissileMultipart;
import com.hbm.render.misc.MissilePronter;
import com.hbm.tileentity.bomb.TileEntityCompactLauncher;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GUIMachineCompactLauncher extends GuiInfoContainer {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_launch_table_small.png");
	private final TileEntityCompactLauncher launcher;
	
	public GUIMachineCompactLauncher(final InventoryPlayer invPlayer, final TileEntityCompactLauncher tedf) {
		super(new ContainerCompactLauncher(invPlayer, tedf));
		launcher = tedf;
		
		this.xSize = 176;
		this.ySize = 222;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float f) {
		super.drawScreen(mouseX, mouseY, f);

		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 116, guiTop + 36, 16, 34, launcher.tanks[0], launcher.tankTypes[0]);
		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 134, guiTop + 36, 16, 34, launcher.tanks[1], launcher.tankTypes[1]);
		final String[] text2 = I18nUtil.resolveKeyArray("desc.solidfuellaunch", launcher.solid);
		
		this.drawCustomInfo(this, mouseX, mouseY, guiLeft + 152, guiTop + 88 - 52, 16, 52, text2);
		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 134, guiTop + 113, 34, 6, launcher.power, TileEntityCompactLauncher.maxPower);

		final String[] text = I18nUtil.resolveKeyArray("desc.guimachcomplauncher1");
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36, 16, 16, guiLeft - 8, guiTop + 36 + 16, text);
		
		final String[] text1 = I18nUtil.resolveKeyArray("desc.guimachcomplauncher2");
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36 + 16, 16, 16, guiLeft - 8, guiTop + 36 + 16, text1);
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int i, final int j) {
		final String name = this.launcher.hasCustomInventoryName() ? this.launcher.getInventoryName() : I18n.format(this.launcher.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		final int i = (int)launcher.getPowerScaled(34);
		drawTexturedModalRect(guiLeft + 134, guiTop + 113, 176, 96, i, 6);
		
		final int j = launcher.getSolidScaled(52);
		drawTexturedModalRect(guiLeft + 152, guiTop + 88 - j, 176, 96 - j, 16, j);
		
		if(launcher.isMissileValid())
			drawTexturedModalRect(guiLeft + 25, guiTop + 35, 176, 26, 18, 18);
		
		if(launcher.hasDesignator())
			drawTexturedModalRect(guiLeft + 25, guiTop + 71, 176, 26, 18, 18);
		
		if(launcher.liquidState() == 1)
			drawTexturedModalRect(guiLeft + 121, guiTop + 23, 176, 0, 6, 8);
		if(launcher.liquidState() == 0)
			drawTexturedModalRect(guiLeft + 121, guiTop + 23, 182, 0, 6, 8);
		
		if(launcher.oxidizerState() == 1)
			drawTexturedModalRect(guiLeft + 139, guiTop + 23, 176, 0, 6, 8);
		if(launcher.oxidizerState() == 0)
			drawTexturedModalRect(guiLeft + 139, guiTop + 23, 182, 0, 6, 8);
		
		if(launcher.solidState() == 1)
			drawTexturedModalRect(guiLeft + 157, guiTop + 23, 176, 0, 6, 8);
		if(launcher.solidState() == 0)
			drawTexturedModalRect(guiLeft + 157, guiTop + 23, 182, 0, 6, 8);
		
		this.drawInfoPanel(guiLeft - 16, guiTop + 36, 16, 16, 2);
		this.drawInfoPanel(guiLeft - 16, guiTop + 36 + 16, 16, 16, 11);
		
		FFUtils.drawLiquid(launcher.tanks[0], guiLeft, guiTop, zLevel, 16, 34, 116, 98);
		FFUtils.drawLiquid(launcher.tanks[1], guiLeft, guiTop, zLevel, 16, 34, 134, 98);
		
		/// DRAW MISSILE START
		GL11.glPushMatrix();

		MissileMultipart missile;
		
		if(launcher.isMissileValid()) {
			final ItemStack custom = launcher.inventory.getStackInSlot(0);
			
			missile = new MissileMultipart();
			
			missile = MissileMultipart.loadFromStruct(ItemCustomMissile.getStruct(custom));
		
			GL11.glTranslatef(guiLeft + 88, guiTop + 115, 100);
			
			final double size = 5 * 18;
			final double scale = size / Math.max(missile.getHeight(), 6);

			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslated(missile.getHeight() / 2D * scale, 0, 0);
			GL11.glScaled(scale, scale, scale);
			
			GL11.glScalef(-1, -1, -1);
			
			MissilePronter.prontMissile(missile, Minecraft.getMinecraft().getTextureManager());
		}
		
		GL11.glPopMatrix();
		/// DRAW MISSILE END
	}
}