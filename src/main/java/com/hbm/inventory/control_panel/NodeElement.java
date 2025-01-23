package com.hbm.inventory.control_panel;

import com.hbm.inventory.control_panel.nodes.Node;

import net.minecraft.nbt.NBTTagCompound;

public class NodeElement {

	public String name = "default";
	public Node parent;
	public int index;
	public float offsetX;
	public float offsetY;
	
	public NodeElement(final Node parent, final int idx){
		this.parent = parent;
		this.index = idx;
		resetOffset();
	}

	public NodeElement(final String name, final Node parent, final int idx){
		this.name = name;
		this.parent = parent;
		this.index = idx;
		resetOffset();
	}

	public void render(final float mX, final float mY){
	}
	
	public void resetOffset(){
		offsetX = parent.posX;
		offsetY = parent.posY+index*8;
	}
	
	public boolean onClick(final float x, final float y){
		return false;
	}
	
	public NBTTagCompound writeToNBT(final NBTTagCompound tag, final NodeSystem sys){
		tag.setFloat("oX", offsetX);
		tag.setFloat("oY", offsetY);
		tag.setInteger("idx", index);
		tag.setInteger("pIdx", sys.nodes.indexOf(parent));
		return tag;
	}
	
	public void readFromNBT(final NBTTagCompound tag, final NodeSystem sys){
		offsetX = tag.getFloat("oX");
		offsetY = tag.getFloat("oY");
		index = tag.getInteger("idx");
		parent = sys.nodes.get(tag.getInteger("pIdx"));
	}
}
