package com.hbm.inventory.control_panel;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;

public class DataValueString extends DataValue {

	public String str;
	private float floatVal;
	
	public DataValueString(final String s){
		this.str = s;
		float num = 0;
		try {
			num = Float.parseFloat(str);
		} catch(final Exception x){
		}
		floatVal = num;
	}

	@Override
	public float getNumber(){
		return floatVal;
	}

	@Override
	public boolean getBoolean(){
        return str.equals("true");
    }

	@Override
	public String toString(){
		return str;
	}

	@Override
	public <E extends Enum<E>> E getEnum(final Class<E> clazz){
		final E[] enms = clazz.getEnumConstants();
		for(final E enm : enms){
			if(enm.name().equalsIgnoreCase(str))
				return enm;
		}
		return enms[0];
	}
	
	@Override
	public DataType getType(){
		return DataType.STRING;
	}

	@Override
	public NBTBase writeToNBT(){
		return new NBTTagString(str);
	}

	@Override
	public void readFromNBT(final NBTBase nbt){
		str = ((NBTTagString)nbt).getString();
		float num = 0;
		try {
			num = Float.parseFloat(str);
		} catch(final Exception x){
		}
		floatVal = num;
	}

}
