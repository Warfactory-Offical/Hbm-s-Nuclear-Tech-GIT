package com.hbm.inventory.control_panel.nodes;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.hbm.inventory.control_panel.DataValue;
import com.hbm.inventory.control_panel.NodeConnection;
import com.hbm.inventory.control_panel.NodeSystem;
import com.hbm.inventory.control_panel.NodeType;

import net.minecraft.nbt.NBTTagCompound;

public class NodeInput extends Node {

	public String name;
	
	public NodeInput(final float x, final float y, final String name){
		super(x, y);
		this.name = name;
	}

	@Override
	public DataValue evaluate(final int idx){
		return outputs.get(idx).defaultValue;
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound tag, final NodeSystem sys){
		tag.setString("nodeType", "input");
		tag.setString("name", name);
		return super.writeToNBT(tag, sys);
	}

	@Override
	public void readFromNBT(final NBTTagCompound tag, final NodeSystem sys){
		this.name = tag.getString(name);
		super.readFromNBT(tag, sys);
	}
	
	@Override
	public NodeType getType(){
		return NodeType.INPUT;
	}
	
	@Override
	public String getDisplayName(){
		return name;
	}
	
	public NodeInput setVars(final Map<String, DataValue> vars){
		outputs.clear();
		for(final Entry<String, DataValue> e : vars.entrySet()){
			final NodeConnection c = new NodeConnection(e.getKey(), this, outputs.size(), false, e.getValue().getType(), e.getValue());
			outputs.add(c);
		}
		this.recalcSize();
		return this;
	}
	
	public NodeInput setOutputFromVars(final Map<String, DataValue> vars){
		for(final NodeConnection c : outputs){
			if (Objects.equals(c.name, "from index")) {
				c.enumSelector = null;
				continue;
			}
			c.setDefault(vars.get(c.name));
		}
		return this;
	}
}
