package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ItemList {

	public float posX;
	public float posY;
	public float width;
	public int textColor = 0xFFAFAFAF;
	public float r = 0.5F;
	public float g = 0.5F;
	public float b = 0.5F;
	public float alpha = 0.5F;
	
	public List<String> itemNames = new ArrayList<>();
	public Function<String, ItemList> action;
	
	public ItemList child;
	
	public boolean isClosed;
	
	public ItemList(final float posX, final float posY, final float width, final Function<String, ItemList> a){
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.action = a;
		isClosed = false;
	}
	
	public ItemList addItems(final String... items){
        Collections.addAll(itemNames, items);
		return this;
	}
	
	public void render(final float mouseX, final float mouseY){
		if(isClosed)
			return;
		if(child != null)
			child.render(mouseX, mouseY);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.white);
		final Tessellator tes = Tessellator.getInstance();
		final BufferBuilder buf = tes.getBuffer();
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
		RenderHelper.drawGuiRectBatchedColor(posX, posY, 0, 0, width, itemNames.size()*6+4, 1, 1, r, g, b, alpha);
		final int idx = getMouseoverIndex(mouseX, mouseY);
		if(idx != -1){
			RenderHelper.drawGuiRectBatchedColor(posX, posY+idx*6+2, 0, 0, width, 5, 1, 1, r+0.1F, g+0.1F, b+0.1F, alpha);
		}
		tes.draw();
		GlStateManager.disableBlend();
		
		GL11.glPushMatrix();
		GL11.glTranslated(posX, posY, 0);
		GL11.glScaled(0.4, 0.4, 1);
		GL11.glTranslated(-posX, -posY, 0);
		float y = posY + 7;
		final FontRenderer font = Minecraft.getMinecraft().fontRenderer;
		for(String s : itemNames){
			if(s.startsWith("{expandable}")){
				font.drawString(">", posX+width*2-6, y, textColor, false);
				s = s.substring(12);
			}
			font.drawString(s, posX+2, y, textColor, false);
			y += 15;
		}
		GL11.glPopMatrix();
	}
	
	public boolean mouseClicked(final float x, final float y){
		if(isClosed)
			return false;
		boolean didAction = false;
		if(child != null && RenderHelper.intersects2DBox(x, y, child.getBoundingBox())){
			return child.mouseClicked(x, y);
		} else {
			if(child != null){
				child.close();
				child = null;
			}
			float yPos = posY + 2;
			for(final String s : itemNames){
				if(x > posX && x < posX+width && y > yPos && y < yPos + 6){
					final ItemList newList = action.apply(s);
					if(newList != null){
						newList.posX = posX+width;
						newList.posY = yPos;
						child = newList;
					} else {
						didAction = true;
					}
				}
				yPos += 6;
			}
		}
		return didAction;
	}
	
	public int getMouseoverIndex(final float x, final float y){
		float yPos = posY + 2;
		for(int i = 0; i < itemNames.size(); i++){
			if(x > posX && x < posX+width && y > yPos && y < yPos + 5){
				return i;
			}
			yPos += 6;
		}
		return -1;
	}
	
	public float[] getBoundingBox(){
		if(child != null){
			final float[] box = child.getBoundingBox();
			return new float[]{Math.min(posX, box[0]), Math.min(posY, box[1]), Math.max(posX+width, box[2]), Math.max(posY+itemNames.size()*6+4, box[3])};
		}
		return new float[]{posX, posY, posX+width, posY + itemNames.size()*6+4};
	}

	public void close(){
		isClosed = true;
	}
	
	public void open(){
		isClosed = false;
	}
}
