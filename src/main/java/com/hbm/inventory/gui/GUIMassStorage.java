package com.hbm.inventory.gui;

import com.hbm.inventory.container.ContainerMassStorage;
import com.hbm.lib.RefStrings;
import com.hbm.packet.NBTControlPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.machine.storage.TileEntityMassStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Locale;

public class GUIMassStorage extends GuiInfoContainer {

	private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/storage/gui_mass_storage.png");
	private TileEntityMassStorage storage;

	public GUIMassStorage(InventoryPlayer invPlayer, TileEntityMassStorage tile) {
		super(new ContainerMassStorage(invPlayer, tile));
		storage = tile;

		this.xSize = 176;
		this.ySize = 221;
	}

	public void initGui() {
		super.initGui();
		if (mc.player != null) {
			TileEntityMassStorage.openInventory(mc.player);
		}
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		if (mc.player != null) {
			TileEntityMassStorage.closeInventory(mc.player);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);

		String percent = (((int) (storage.getStockpile() * 1000D / (double) storage.getCapacity())) / 10D) + "%";
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 96, guiTop + 16, 18, 90, mouseX, mouseY, new String[]
				{ String.format(Locale.US, "%,d", storage.getStockpile()) + " / " + String.format(Locale.US, "%,d", storage.getCapacity()), percent });

		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 62, guiTop + 72, 14, 14, mouseX, mouseY, new String[] { "Click: Provide one", "Shift-click: Provide stack" });
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 80, guiTop + 72, 14, 14, mouseX, mouseY, new String[] { "Toggle output" });
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void mouseClicked(int x, int y, int i) throws IOException {
		super.mouseClicked(x, y, i);

		if(guiLeft + 62 <= x && guiLeft + 62 + 14 > x && guiTop + 72 < y && guiTop + 72 + 14 >= y) {

			mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			NBTTagCompound data = new NBTTagCompound();
			data.setBoolean("provide", Keyboard.isKeyDown(Keyboard.KEY_LSHIFT));
			PacketDispatcher.wrapper.sendToServer(new NBTControlPacket(data, storage.getPos().getX(), storage.getPos().getY(), storage.getPos().getZ()));
		}

		if(guiLeft + 80 <= x && guiLeft + 80 + 14 > x && guiTop + 72 < y && guiTop + 72 + 14 >= y) {

			mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			NBTTagCompound data = new NBTTagCompound();
			data.setBoolean("toggle", false);
			PacketDispatcher.wrapper.sendToServer(new NBTControlPacket(data, storage.getPos().getX(), storage.getPos().getY(), storage.getPos().getZ()));
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.storage.hasCustomInventoryName() ? this.storage.getInventoryName() : I18n.format(this.storage.getInventoryName());

		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int gauge = storage.getStockpile() * 88 / storage.getCapacity();
		drawTexturedModalRect(guiLeft + 97, guiTop + 105 - gauge, 176, 88 - gauge, 16, gauge);

		if(storage.output)
			drawTexturedModalRect(guiLeft + 80, guiTop + 72, 192, 0, 14, 14);
	}
}
