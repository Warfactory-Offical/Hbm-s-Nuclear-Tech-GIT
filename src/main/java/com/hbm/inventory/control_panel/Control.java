package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hbm.inventory.control_panel.controls.ControlType;
import com.hbm.render.amlfrom1710.IModelCustom;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Control {

	public String name;
	public ControlPanel panel;
	//Set of block positions this control is connected to. When an event is sent, it gets sent to each one
	public List<BlockPos> connectedSet = new ArrayList<>();
	//A map of event names to node system for events this control is sending out to connected blocks
	public Map<String, NodeSystem> sendNodeMap = new HashMap<>();
	//A map of event names to node systems for events this control is receiving
	public Map<String, NodeSystem> receiveNodeMap = new HashMap<>();
	//A map of all variables, either used internally by the control or in the node systems
	public Map<String, DataValue> vars = new HashMap<>();
	public Map<String, DataValue> varsPrev = new HashMap<>();
	//A set of the custom variables the user is allowed to remove
	public Set<String> customVarNames = new HashSet<>();
	// map of (static) initial configurations for a control e.g. color, size
	public Map<String, DataValue> configMap = new HashMap<>();
	public float posX;
	public float posY;


	public Control(final String name, final ControlPanel panel){
		this.name = name;
		this.panel = panel;
	}

	public abstract ControlType getControlType();

	public abstract float[] getSize();

	public Map<String, DataValue> getConfigs() {
		return configMap;
	}
	public void applyConfigs(final Map<String, DataValue> configs) {
		configMap = configs;
	}

	public void renderBatched(){}

    public void render(){}

    public List<String> getOutEvents(){return Collections.emptyList();}

    public List<String> getInEvents(){return Arrays.asList("tick", "initialize");}

    @SideOnly(Side.CLIENT)
	public abstract IModelCustom getModel();
	@SideOnly(Side.CLIENT)
	public abstract ResourceLocation getGuiTexture();

	public AxisAlignedBB getBoundingBox() {
		final float width = getSize()[0];
		final float length = getSize()[1];
		final float height = getSize()[2];
		// offset to fix placement position error for controls not 1x1.
		return new AxisAlignedBB(-width/2, 0, -length/2, width/2, height, length/2).offset(posX+((width>1?Math.abs(1-width)/2:(width-1)/2)), 0, posY+((length>1)? Math.abs(1-length)/2 : (length-1)/2));
//				.offset(posX+((width>1)?Math.abs(1-width/2):0), 0, posY+Math.abs(1-length)/2);
//		GlStateManager.translate((width>1)? Math.abs(1-width)/2 : (width-1)/2, 0, (length>1)? Math.abs(1-length)/2 : 0);
	}

	public float[] getBox() {
		final float width = getSize()[0];
		final float length = getSize()[1];
		return new float[] {posX, posY, posX + width, posY + length};
	}

	public abstract Control newControl(ControlPanel panel);

	public abstract void populateDefaultNodes(List<ControlEvent> receiveEvents);

	public void receiveEvent(final ControlEvent evt){
		final NodeSystem sys = receiveNodeMap.get(evt.name);
		if(sys != null){
			sys.resetCachedValues();
			sys.receiveEvent(panel, this, evt);
		}
	}
	
	public DataValue getVar(final String name){
		return vars.getOrDefault(name, new DataValueFloat(0));
	}
	
	public DataValue getGlobalVar(final String name){
		return panel.getVar(name);
	}

	public NBTTagCompound writeToNBT(final NBTTagCompound tag){
		tag.setString("name", ControlRegistry.getName(this.getClass()));
		tag.setString("myName", name);
		final NBTTagCompound vars = new NBTTagCompound();
		for(final Entry<String, DataValue> e : this.vars.entrySet()) {
			vars.setTag(e.getKey(), e.getValue().writeToNBT());
		}
		tag.setTag("vars", vars);
		
		final NBTTagCompound sendNodes = new NBTTagCompound();
		for(final Entry<String, NodeSystem> e : sendNodeMap.entrySet()){
			final NBTTagCompound eventNodeMap = e.getValue().writeToNBT(new NBTTagCompound());
			sendNodes.setTag(e.getKey(), eventNodeMap);
		}
		tag.setTag("SN", sendNodes);
		
		final NBTTagCompound receiveNodes = new NBTTagCompound();
		for(final Entry<String, NodeSystem> e : receiveNodeMap.entrySet()){
			receiveNodes.setTag(e.getKey(), e.getValue().writeToNBT(new NBTTagCompound()));
		}
		tag.setTag("RN", receiveNodes);
		
		final NBTTagCompound customVarNames = new NBTTagCompound();
		int i = 0;
		for(final String s : this.customVarNames){
			customVarNames.setString("var" + i, s);
			i++;
		}
		tag.setTag("customvars", customVarNames);
		
		final NBTTagCompound connectedSet = new NBTTagCompound();
		for(i = 0; i < this.connectedSet.size(); i ++){
			connectedSet.setInteger("px"+i, this.connectedSet.get(i).getX());
			connectedSet.setInteger("py"+i, this.connectedSet.get(i).getY());
			connectedSet.setInteger("pz"+i, this.connectedSet.get(i).getZ());
		}
		tag.setTag("conset", connectedSet);
		
		tag.setFloat("X", posX);
		tag.setFloat("Y", posY);

		final NBTTagCompound configs = new NBTTagCompound();
		for (final Entry<String, DataValue> e : configMap.entrySet()) {
			configs.setTag(e.getKey(), e.getValue().writeToNBT());
		}
		tag.setTag("configs", configs);

		return tag;
	}
	
	public void readFromNBT(final NBTTagCompound tag){
		final NBTTagCompound vars = tag.getCompoundTag("vars");
		for(final String k : vars.getKeySet()) {
			final NBTBase base = vars.getTag(k);
			final DataValue val = DataValue.newFromNBT(base);
			if(val != null) {
				this.vars.put(k, val);
			}
		}
		
		sendNodeMap.clear();
		receiveNodeMap.clear();
		
		final NBTTagCompound sendNodes = tag.getCompoundTag("SN");
		for(final String s : sendNodes.getKeySet()){
			final NodeSystem sys = new NodeSystem(this);
			sendNodeMap.put(s, sys);
			sys.readFromNBT(sendNodes.getCompoundTag(s));
		}
		final NBTTagCompound receiveNodes = tag.getCompoundTag("RN");
		for(final String s : receiveNodes.getKeySet()){
			final NodeSystem sys = new NodeSystem(this);
			receiveNodeMap.put(s, sys);
			sys.readFromNBT(receiveNodes.getCompoundTag(s));
		}
		
		final NBTTagCompound customVarNames = tag.getCompoundTag("customvars");
		for(int i = 0; i < customVarNames.getKeySet().size(); i ++){
			this.customVarNames.add(customVarNames.getString("var"+i));
		}
		
		final NBTTagCompound connectedSet = tag.getCompoundTag("conset");
		for(int i = 0; i < connectedSet.getKeySet().size()/3; i ++){
			final int x = connectedSet.getInteger("px"+i);
			final int y = connectedSet.getInteger("py"+i);
			final int z = connectedSet.getInteger("pz"+i);
			this.connectedSet.add(new BlockPos(x, y, z));
		}
		
		this.posX = tag.getFloat("X");
		this.posY = tag.getFloat("Y");

		final NBTTagCompound configs = tag.getCompoundTag("configs");
		for (final String e : configs.getKeySet()) {
			configMap.put(e, DataValue.newFromNBT(configs.getTag(e)));
		}
	}


}
