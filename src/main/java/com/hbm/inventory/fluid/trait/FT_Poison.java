package com.hbm.inventory.fluid.trait;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.util.I18nUtil;
import com.mojang.realmsclient.gui.ChatFormatting;

import java.io.IOException;
import java.util.List;

@Deprecated //use FT_Toxin instead
public class FT_Poison extends FluidTrait {

	protected boolean withering = false;
	protected int level = 0;
	
	public FT_Poison() { }
	
	public FT_Poison(boolean withering, int level) {
		this.withering = withering;
		this.level = level;
	}
	
	public boolean isWithering() {
		return this.withering;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	@Override
	public void addInfoHidden(List<String> info) {
		info.add(ChatFormatting.GREEN + "[" + I18nUtil.resolveKey("trait.toxicfumes") + "]");
	}
	
	@Override public void serializeJSON(JsonWriter writer) throws IOException {
		writer.name("level").value(this.level);
		writer.name("withering").value(this.withering);
	}
	
	@Override public void deserializeJSON(JsonObject obj) {
		this.level = obj.get("level").getAsInt();
		this.withering = obj.get("withering").getAsBoolean();
	}
}
