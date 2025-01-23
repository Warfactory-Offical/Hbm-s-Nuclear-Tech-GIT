package com.hbm.inventory.control_panel;

import net.minecraft.client.gui.GuiButton;

public class SubElement {
	
	public GuiControlEdit gui;
	
	public boolean lock = false;
	
	public SubElement(final GuiControlEdit gui) {
		this.gui = gui;
	}
	protected void actionPerformed(final GuiButton button){
	}
	protected void initGui(){
		enableButtons(false);
	}
	protected void drawScreen(){
	}
	protected void renderBackground(){
	}
	protected void enableButtons(final boolean enable){
	}
	protected void keyTyped(final char typedChar, final int code){
	}
	protected void mouseClicked(final int mouseX, final int mouseY, final int button){
	}
	protected void mouseReleased(final int mouseX, final int mouseY, final int state){
	}
	protected void update(){
	}
	public void onClose(){
	}
}