package com.hbm.inventory.control_panel;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;

public class DataValueFloat extends DataValue {

	public float num;
	
	public DataValueFloat(final float f) {
		num = f;
	}

	public DataValueFloat(final boolean b) {
		num = (b)? 1.0F : 0.0F;
	}

	@Override
	public float getNumber() {
		return num;
	}

	@Override
	public boolean getBoolean() {
		return num != 0;
	}

	@Override
	public String toString() {
		return Float.toString(num);
	}

	@Override
	public DataType getType(){
		return DataType.NUMBER;
	}
	
	
	@Override
	public <E extends Enum<E>> E getEnum(final Class<E> clazz){
		final int i = (int)num;
		final E[] enums = clazz.getEnumConstants();
		if(i >= 0 && i < enums.length){
			return enums[i];
		}
		return enums[0];
	}

	@Override
	public NBTBase writeToNBT(){
		return new NBTTagFloat(num);
	}

	@Override
	public void readFromNBT(final NBTBase nbt){
		final NBTTagFloat f = (NBTTagFloat)nbt;
		num = f.getFloat();
	}

}
