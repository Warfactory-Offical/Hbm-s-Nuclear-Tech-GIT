package com.hbm.inventory.control_panel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;

public class ControlEvent {

	private static final HashMap<String, ControlEvent> REGISTRY = new HashMap<>();
	
	public String name;
	public Map<String, DataValue> vars = new HashMap<>();
	
	public ControlEvent(final String name){
		this.name = name;
	}
	
	public ControlEvent setVar(final String key, final DataValue val){
		vars.put(key, val);
		return this;
	}
	
	public ControlEvent setVar(final String key, final float f){
		vars.put(key, new DataValueFloat(f));
		return this;
	}
	
	public ControlEvent setVar(final String key, final boolean b){
		vars.put(key, new DataValueFloat(b ? 1 : 0));
		return this;
	}
	
	public ControlEvent setVar(final String key, final String str){
		vars.put(key, new DataValueString(str));
		return this;
	}
	
	public <E extends Enum<E>> ControlEvent setVar(final String key, final E enm){
		vars.put(key, new DataValueEnum<E>(enm));
		return this;
	}
	
	public ControlEvent copy(){
		final ControlEvent evt = new ControlEvent(name);
		//Set default values
		for(final Entry<String, DataValue> def : vars.entrySet()){
			evt.vars.put(def.getKey(), def.getValue());
		}
		return evt;
	}
	
	public NBTTagCompound writeToNBT(final NBTTagCompound tag){
		tag.setString("name", this.name);
		final NBTTagCompound vars = new NBTTagCompound();
		for(final Entry<String, DataValue> e : this.vars.entrySet()){
			vars.setTag(e.getKey(), e.getValue().writeToNBT());
		}
		tag.setTag("vars", vars);
		return tag;
	}
	
	public static ControlEvent readFromNBT(final NBTTagCompound tag){
		final ControlEvent evt = ControlEvent.newEvent(tag.getString("name"));
		final NBTTagCompound vars = tag.getCompoundTag("vars");
		for(final String k : vars.getKeySet()){
			final DataValue val = DataValue.newFromNBT(vars.getTag(k));
			if(val != null)
				evt.vars.put(k, val);
		}
		return evt;
	}
	
	public static ControlEvent newEvent(final String name){
		return getRegisteredEvent(name).copy();
	}
	
	public static ControlEvent getRegisteredEvent(final String name){
		final ControlEvent e = REGISTRY.get(name);
		if(e == null)
			throw new RuntimeException("Unregistered control event: " + name);
		return e;
	}
	
	public static void register(final ControlEvent c){
		REGISTRY.put(c.name, c);
	}
	
	public static void init(){
		register(new ControlEvent("tick").setVar("time", 0));
		register(new ControlEvent("door_open_state").setVar("state", 0));
		register(new ControlEvent("door_toggle").setVar("passcode", 0));
		register(new ControlEvent("turret_set_target").setVar("players", false).setVar("passive", false).setVar("hostile", true).setVar("machines", true));
		register(new ControlEvent("turret_switch").setVar("isOn", true));
		register(new ControlEvent("lever_toggle").setVar("isOn", false));
		register(new ControlEvent("spinny_light_power").setVar("isOn", false));
		register(new ControlEvent("siren_set_state").setVar("isOn", false));
		register(new ControlEvent("tank_set_mode").setVar("mode", 0));
		register(new ControlEvent("rbmk_ctrl_set_color").setVar("color", 0));
		register(new ControlEvent("rbmk_ctrl_set_level").setVar("level", 0));
		register(new ControlEvent("rbmk_crane_move").setVar("up", 0).setVar("down", 0).setVar("left", 0).setVar("right", 0));
		register(new ControlEvent("rbmk_crane_load"));
		register(new ControlEvent("ctrl_press").setVar("isSneaking", false));
		register(new ControlEvent("initialize"));
	}
}
