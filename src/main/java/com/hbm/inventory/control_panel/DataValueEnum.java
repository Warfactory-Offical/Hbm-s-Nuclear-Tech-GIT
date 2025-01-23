package com.hbm.inventory.control_panel;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class DataValueEnum<T extends Enum<T>> extends DataValue {

	public Enum<T> value;
	public Class<T> enumClass;

	@SuppressWarnings("unchecked")
	public DataValueEnum(final Enum<T> e){
		value = e;
		if(e != null)
			enumClass = (Class<T>)e.getClass();
	}

	@Override
	public float getNumber(){
		return value.ordinal();
	}

	@Override
	public boolean getBoolean(){
		return value.toString().equalsIgnoreCase("true");
	}

	@Override
	public String toString(){
		return value.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Enum<E>> E getEnum(final Class<E> clazz){
		if(clazz == enumClass) {
			return (E)value;
		} else {
			return clazz.getEnumConstants()[0];
		}
	}

	@Override
	public DataType getType(){
		return DataType.ENUM;
	}

	public T[] getPossibleValues(){
		return enumClass.getEnumConstants();
	}

	@Override
	public NBTBase writeToNBT(){
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setString("clazz", enumClass.getName());
		tag.setInteger("ordinal", value.ordinal());
		return tag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readFromNBT(final NBTBase nbt){
		final NBTTagCompound tag = (NBTTagCompound)nbt;
		try {
			this.enumClass = (Class<T>)Class.forName(tag.getString("clazz"));
		} catch(final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		this.value = this.enumClass.getEnumConstants()[tag.getInteger("ordinal")];
	}

}
