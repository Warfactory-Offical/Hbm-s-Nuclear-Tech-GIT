package com.hbm.inventory.control_panel.nodes;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hbm.inventory.control_panel.ControlEvent;
import com.hbm.inventory.control_panel.ControlEventSystem;
import com.hbm.inventory.control_panel.DataValue;
import com.hbm.inventory.control_panel.DataValue.DataType;
import com.hbm.inventory.control_panel.DataValueFloat;
import com.hbm.inventory.control_panel.IControllable;
import com.hbm.inventory.control_panel.NodeConnection;
import com.hbm.inventory.control_panel.NodeDropdown;
import com.hbm.inventory.control_panel.NodeSystem;
import com.hbm.inventory.control_panel.NodeType;

import com.hbm.main.MainRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NodeEventBroadcast extends NodeOutput {

	public String eventName;
	
	public NodeEventBroadcast(final float x, final float y, final List<ControlEvent> ableToBroadcast){
		super(x, y);
		final NodeDropdown opSelector = new NodeDropdown(this, otherElements.size(), s -> {
			setEvent(s);
			return null;
		}, () -> eventName);
		for(final ControlEvent c : ableToBroadcast){
			opSelector.list.addItems(c.name);
		}
		this.otherElements.add(opSelector);
		setEvent(ableToBroadcast.get(0).name);
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound tag, final NodeSystem sys){
		tag.setString("nodeType", "eventBroadcast");
		tag.setString("eventName", eventName);
		final NBTTagCompound list = new NBTTagCompound();
		int i = 0;
		for(final String s : ((NodeDropdown)this.otherElements.get(0)).list.itemNames){
			list.setString("item"+i, s);
			i++;
		}
		tag.setTag("itemList", list);
		return super.writeToNBT(tag, sys);
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound tag, final NodeSystem sys){
		eventName = tag.getString("eventName");
		super.readFromNBT(tag, sys);
	}

	@Override
	public boolean doOutput(final IControllable from, final Map<String, NodeSystem> sendNodeMap, final List<BlockPos> positions){
		final World world = from.getControlWorld();
		final ControlEvent e = ControlEvent.newEvent(eventName);
		for(final NodeConnection c : inputs){
			if(c.name.startsWith("Cancel")){
				if(c.evaluate().getBoolean())
					return true;
				continue;
			}
			e.vars.put(c.name, c.evaluate());
		}
		if(sendNodeMap != null){
			if(sendNodeMap.containsKey(e.name)){
				final NodeSystem sys = sendNodeMap.get(e.name);
				cont:
				for(int i = 0; i < positions.size(); i ++){
					sys.resetCachedValues();
					sys.setVar("receiver_id", new DataValueFloat(i));
					for(final NodeOutput o : sys.outputNodes){
						//If canceled, don't continue;
						if(!o.doOutput(from, sendNodeMap, positions))
							continue cont;
					}
					ControlEventSystem.get(world).broadcastEvent(from.getControlPos(), e, positions.get(i));
				}
			} else {
				ControlEventSystem.get(world).broadcastEvent(from.getControlPos(), e, positions);
			}
		} else {
			ControlEventSystem.get(world).broadcastEvent(from.getControlPos(), e, positions);
		}
		return true;
	}
	
	public void setEvent(final String name){
		eventName = name;
		for(final NodeConnection c : inputs){
			c.removeConnection();
		}
		this.inputs.clear();
		final ControlEvent evt = ControlEvent.getRegisteredEvent(name);
		for(final Entry<String, DataValue> e : evt.vars.entrySet()){
			inputs.add(new NodeConnection(e.getKey(), this, inputs.size(), true, e.getValue().getType(), e.getValue()));
		}
		inputs.add(new NodeConnection("Cancel", this, inputs.size(), true, DataType.GENERIC, new DataValueFloat(0)));
		recalcSize();
	}
	
	@Override
	public NodeType getType(){
		return NodeType.OUTPUT;
	}

	@Override
	public String getDisplayName(){
		return "Broadcast";
	}

}
