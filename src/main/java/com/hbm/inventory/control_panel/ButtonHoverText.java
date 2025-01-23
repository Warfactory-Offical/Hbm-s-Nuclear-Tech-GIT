package com.hbm.inventory.control_panel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ButtonHoverText extends GuiButton {

	public String hoverText;
	
	public ButtonHoverText(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText, final String hoverText){
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.hoverText = hoverText;
	}
	
	@Override
	public void drawButton(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks){
		final String prev = this.displayString;
		if(this.isMouseOver())
			this.displayString = this.hoverText;
		super.drawButton(mc, mouseX, mouseY, partialTicks);
		this.displayString = prev;
	}

}
