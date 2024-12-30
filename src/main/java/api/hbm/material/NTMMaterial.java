package api.hbm.material;

import api.hbm.item.icon.HBMMaterialIconSet;
import api.hbm.util.HBMUtil;
import api.hbm.util.lang.LocaleUtils;
import com.hbm.inventory.OreDictManager.DictFrame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

/**
 * Encapsulates most materials that are currently listed as DictFrames, even vanilla ones.
 * @author hbm
 *
 */
public class NTMMaterial {

	public final String name;
	public MaterialShapes[] shapes = new MaterialShapes[0];
	public Set<MatTraits> traits = new HashSet();
	public boolean omitItemGen = false;
	public SmeltingBehavior smeltable = SmeltingBehavior.NOT_SMELTABLE;
	public int solidColorLight = 0xFF4A00;
	public int solidColorDark = 0x802000;
	public int moltenColor = 0xFF4A00;
	public final ResourceLocation resourceLocation;

	public NTMMaterial smeltsInto;
	public int convIn;
	public int convOut;

	public HBMMaterialIconSet iconSet;
	
	public NTMMaterial(String name, ResourceLocation location, HBMMaterialIconSet iconSet) {
		
		this.name = name;
		this.resourceLocation = location;

		this.smeltsInto = this;
		this.convIn = 1;
		this.convOut = 1;

		this.iconSet = iconSet;

//		for(String name : dict.mats) {
//			Mats.matByName.put(name, this);
//		}
		
		Mats.orderedList.add(this);
	}

	public ResourceLocation getResourceLocation() {
		return resourceLocation;
	}

	public String getUnlocalizedName() {
		ResourceLocation location = getResourceLocation();
		return location.getNamespace() + ".hbmmat." + location.getPath();
	}

	public String getLocalizedName() {
		return LocaleUtils.format(getUnlocalizedName());
	}

	public NTMMaterial setConversion(NTMMaterial mat, int in, int out) {
		this.smeltsInto = mat;
		this.convIn = in;
		this.convOut = out;
		return this;
	}
	
	/** Shapes for autogen */
	public NTMMaterial setShapes(MaterialShapes... shapes) {
		// TODO: perhaps remove, because of adding of materials in make()
		this.shapes = shapes;
		for (MaterialShapes shape : shapes) {
			shape.addMaterial(this);
		}
		return this;
	}

	public NTMMaterial setSolidColor(int colorLight, int colorDark) {
		this.solidColorLight = colorLight;
		this.solidColorDark = colorDark;
		return this;
	}
	
	/** Turn off autogen for this material, use this for vanilla stuff */
	public NTMMaterial omitAutoGen() {
		this.omitItemGen = true;
		return this;
	}
	
	/** Defines smelting behavior */
	public NTMMaterial smeltable(SmeltingBehavior behavior) {
		this.smeltable = behavior;
		return this;
	}
	
	public NTMMaterial setMoltenColor(int color) {
		this.moltenColor = color;
		return this;
	}

	public String getNameForItem() {
		return this.name;
	}

	public String getNameForOreDict() {
		String[] parts = this.name.toLowerCase().split("_");
		StringBuilder pascalCaseName = new StringBuilder();
		for (String part : parts) {
			pascalCaseName.append(Character.toUpperCase(part.charAt(0)))
					.append(part.substring(1));
		}
		return pascalCaseName.toString();
	}

	public String toCamelCaseStrng() {
		return HBMUtil.lowerUnderscoreToUpperCamel(toString());
	}

	public static enum SmeltingBehavior {
		NOT_SMELTABLE,	//anything that can't be smelted or otherwise doesn't belong in a smelter, like diamond. may also include things that are smeltable but turn into a different type
		VAPORIZES,		//can't be smelted because the material would skadoodle
		BREAKS,			//can't be smelted because the material doesn't survive the temperatures
		SMELTABLE,		//mostly metal
		ADDITIVE		//stuff like coal which isn't smeltable but can be put in a crucible anyway
	}

	public enum MatTraits {
		METAL,		   //metal(like), smeltable by arc furnaces
		NONMETAL,	   //non-metal(like), for gems, non-alloy compounds and similar
		NO_UNIFICATION // no autogen
	}
	public NTMMaterial m() { this.traits.add(MatTraits.METAL); return this; }
	public NTMMaterial n() { this.traits.add(MatTraits.NONMETAL); return this; }

	public ItemStack make(Item template, int amount) {
		assert template instanceof MaterialShapes;
		return ((MaterialShapes) template).getItemStack(this, amount);
	}

	public ItemStack make(Item template) {
		return this.make(template, 1);
	}

	@Override
	public String toString() {
		return name;
	}



}
