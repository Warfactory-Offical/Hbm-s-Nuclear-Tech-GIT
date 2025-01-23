package com.hbm.tileentity;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

public interface IConfigurableMachine {

	/* the name for the JOSN object for this machine */
	public String getConfigName();
	/* reads the JSON object and sets the machine's parameters, use defaults and ignore if a value is not yet present */
	public void readIfPresent(JsonObject obj);
	/* writes the entire config for this machine using the relevant values */
	public void writeConfig(JsonWriter writer) throws IOException;
	
	public static boolean grab(final JsonObject obj, final String name, final boolean def) {
		return obj.has(name) ? obj.get(name).getAsBoolean() : def;
	}
	
	public static int grab(final JsonObject obj, final String name, final int def) {
		return obj.has(name) ? obj.get(name).getAsInt() : def;
	}
	
	public static long grab(final JsonObject obj, final String name, final long def) {
		return obj.has(name) ? obj.get(name).getAsLong() : def;
	}
	
	public static double grab(final JsonObject obj, final String name, final double def) {
		return obj.has(name) ? obj.get(name).getAsDouble() : def;
	}
}
