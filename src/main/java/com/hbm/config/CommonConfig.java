package com.hbm.config;

import java.util.HashMap;
import java.util.HashSet;

import com.hbm.main.MainRegistry;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class CommonConfig {

	public static boolean createConfigBool(final Configuration config, final String category, final String name, final String comment, final boolean def) {
	
	    final Property prop = config.get(category, name, def);
	    prop.setComment(comment);
	    return prop.getBoolean();
	}
	
	public static String createConfigString(final Configuration config, final String category, final String name, final String comment, final String def) {

		final Property prop = config.get(category, name, def);
		prop.setComment(comment);
		return prop.getString();
	}

	public static String[] createConfigStringList(final Configuration config, final String category, final String name, final String comment, final String[] defaultValues) {

		final Property prop = config.get(category, name, defaultValues);
		prop.setComment(comment);
		return prop.getStringList();
	}

	public static HashMap createConfigHashMap(final Configuration config, final String category, final String name, final String comment, final String keyType, final String valueType, final String[] defaultValues, final String splitReg) {
		final HashMap<Object, Object> configDictionary = new HashMap<>();
		final Property prop = config.get(category, name, defaultValues);
		prop.setComment(comment);
		for(final String entry: prop.getStringList()){
			final String[] pairs = entry.split(splitReg, 0);
			configDictionary.put(parseType(pairs[0], keyType), parseType(pairs[1], valueType));
		}
		return configDictionary;
	}

	public static HashSet createConfigHashSet(final Configuration config, final String category, final String name, final String comment, final String valueType, final String[] defaultValues) {
		final HashSet<Object> configSet = new HashSet<>();
		final Property prop = config.get(category, name, defaultValues);
		prop.setComment(comment);
		for(final String entry: prop.getStringList()){
			configSet.add(parseType(entry, valueType));
		}
		return configSet;
	}

	private static Object parseType(final String value, final String type){
		if(type == "Float"){
			return Float.parseFloat(value);
		}
		if(type == "Int"){
			return Integer.parseInt(value);
		}
		if(type == "Long"){
			return Float.parseFloat(value);
		}
		if(type == "Double"){
			return Double.parseDouble(value);
		}
		return value;
	}

	public static int createConfigInt(final Configuration config, final String category, final String name, final String comment, final int def) {
	
	    final Property prop = config.get(category, name, def);
	    prop.setComment(comment);
	    return prop.getInt();
	}

	public static double createConfigDouble(final Configuration config, final String category, final String name, final String comment, final double def) {
	
	    final Property prop = config.get(category, name, def);
	    prop.setComment(comment);
	    return prop.getDouble();
	}

	public static int setDefZero(final int value, final int def) {

		if(value < 0) {
			MainRegistry.logger.error("Fatal error config: Randomizer value has been below zero, despite bound having to be positive integer!");
			MainRegistry.logger.error(String.format("Errored value will default back to %d, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", def));
			return def;
		}

		return value;
	}
	
	public static int setDef(final int value, final int def) {
	
		if(value <= 0) {
			MainRegistry.logger.error("Fatal error config: Randomizer value has been set to zero, despite bound having to be positive integer!");
			MainRegistry.logger.error(String.format("Errored value will default back to %d, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", def));
			return def;
		}
	
		return value;
	}

}
