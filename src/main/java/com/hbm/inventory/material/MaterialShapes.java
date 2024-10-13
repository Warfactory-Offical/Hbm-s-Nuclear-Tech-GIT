package com.hbm.inventory.material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.hbm.lib.RefStrings.MODID;

public class MaterialShapes extends Item {

	public static List<MaterialShapes> ALL_MATERIALS = new ArrayList<>();


	public static final MaterialShapes NUGGET = new MaterialShapes("nugget", null, 8, "nugget");
	public static final MaterialShapes INGOT = new MaterialShapes("ingot", null, NUGGET.quantity * 9, "ingot");
	public static final MaterialShapes QUANTUM = new MaterialShapes("quantum", null, 1); // 1/72 of an ingot, allows the ingot to be divisible through 2, 4, 6, 8, 9, 12, 24 and 36
	public static final MaterialShapes FRAGMENT = new MaterialShapes("fragment", null, 8, "bedrockOreFragment");
	public static final MaterialShapes DUSTTINY = new MaterialShapes("powder_tiny", "powder_%s_tiny", NUGGET.quantity, "dustTiny", "dust%sTiny");
	public static final MaterialShapes WIRE = new MaterialShapes("wire", null, 9);
	public static final MaterialShapes DENSEWIRE = new MaterialShapes("dense_wire", null, INGOT.quantity, "wireDense");
	public static final MaterialShapes BILLET = new MaterialShapes("billet", null, NUGGET.quantity * 6, "billet");
	public static final MaterialShapes GEM = new MaterialShapes("gem", null, INGOT.quantity, "gem");
	public static final MaterialShapes CRYSTAL = new MaterialShapes("crystal", null, INGOT.quantity, "crystal");
	public static final MaterialShapes POWDER = new MaterialShapes("powder", null, INGOT.quantity, "dust");
	public static final MaterialShapes PLATE = new MaterialShapes("plate", null, INGOT.quantity, "plate");
	public static final MaterialShapes CASTPLATE = new MaterialShapes("cast_plate", null, INGOT.quantity * 3, "plateTriple");
	public static final MaterialShapes WELDEDPLATE = new MaterialShapes("welded_plate", null, INGOT.quantity * 6, "plateSextuple");
	public static final MaterialShapes QUART = new MaterialShapes("quart", null, 162, "quart");
	public static final MaterialShapes SHELL = new MaterialShapes("shell", null, INGOT.quantity * 4, "shell");
	public static final MaterialShapes PIPE = new MaterialShapes("pipe", null, INGOT.quantity * 3, "ntmpipe");
	public static final MaterialShapes BLOCK = new MaterialShapes("block", null, INGOT.quantity * 9, "block");
	public static final MaterialShapes HEAVY_COMPONENT = new MaterialShapes( "heavy_component", null, CASTPLATE.quantity * 256, "componentHeavy");

	public static final MaterialShapes BOLT = new MaterialShapes("bolt", null, 9);


	public static final MaterialShapes LIGHTBARREL = new MaterialShapes("light_barrel", null, INGOT.quantity * 3, "barrelLight");
	public static final MaterialShapes HEAVYBARREL = new MaterialShapes("heavy_barrel", null, INGOT.quantity * 6, "barrelHeavy");
	public static final MaterialShapes LIGHTRECEIVER = new MaterialShapes("LIGHT_RECEIVER", null, INGOT.quantity * 4, "receiverLight");
	public static final MaterialShapes HEAVYRECEIVER = new MaterialShapes("HEAVYRECEIVER", null, INGOT.quantity * 9, "receiverHeavy");
	public static final MaterialShapes MECHANISM = new MaterialShapes("MECHANISM", null, INGOT.quantity * 4, "gunMechanism");
	public static final MaterialShapes STOCK = new MaterialShapes("STOCK", null, INGOT.quantity * 4, "stock");
	public static final MaterialShapes GRIP = new MaterialShapes("GRIP", null, INGOT.quantity * 2, "grip");

	private final String itemName;
	private final String translationKeyFormat;
	private final int quantity;
	private final List<String> oreDictFormats;
	private final List<NTMMaterial> materials = new ArrayList<>();

	public MaterialShapes(@NotNull String itemName, @Nullable String translationKeyFormat, int quantity, String... prefixes) {
		this.itemName = itemName;
		this.translationKeyFormat = translationKeyFormat == null ? (itemName + "_%s") : translationKeyFormat;
		this.quantity = quantity;
		this.oreDictFormats = Arrays.stream(prefixes).map(prefix -> prefix.contains("%s") ? prefix : (prefix + "%s")).collect(Collectors.toList());

		setTranslationKey(itemName);
		setRegistryName(itemName);
		setHasSubtypes(true);

//        for(String prefix : prefixes) {
//            Mats.prefixByName.put(prefix, this);
//        }

        setCreativeTab(MainRegistry.partsTab);

		ModItems.ALL_ITEMS.add(this);
		ALL_MATERIALS.add(this);
	}

	public static void init() {
		// DON'T REMOVE
	}

	@Override
	public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab)) {
			for (int i = 0; i < materials.size(); ++i) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	@NotNull
	public String getTranslationKey(ItemStack stack) {
		int meta = stack.getMetadata();
		if (meta < materials.size()) return "item." + String.format(
				this.translationKeyFormat,
				materials.get(meta).getNameForItem()
		);
		return "item." + String.format(
				this.translationKeyFormat,
				"null"
		);
	}

	@SideOnly(Side.CLIENT)
	public void registerModels() {
		if (getRegistryName() == null) {
			MainRegistry.logger.error("Registry name is null for item: {}", this);
			return;
		}

		for (int i = 0; i < materials.size(); i++) {
			String materialName = materials.get(i).getNameForItem();
			ModelLoader.setCustomModelResourceLocation(
					this,
					i,
					new ModelResourceLocation(getRegistryName() + "_" + materialName, "inventory")
			);
			MainRegistry.logger.info("Registered model for: {}:{}@{}", MODID, getRegistryName(), materialName);
		}
	}

	public List<String> getOreDictNames(NTMMaterial material) {
		return oreDictFormats.stream().map(format -> String.format(format, material.getNameForOreDict())).collect(Collectors.toList());
	}
	
	public int q(int amount) {
		return this.quantity * amount;
	}
	
	public int q(int unitsUsed, int itemsProduced) { // e.g. rails: INGOT.q(6, 16) since the recipe uses 6 ton ingots producing 16 individual rail blocks
		return this.quantity * unitsUsed / itemsProduced;
	}

	public void addMaterial(NTMMaterial material) {
		MainRegistry.logger.info("MaterialShapes::addMaterial adding {} to values of {}", material, this.itemName);
		this.materials.add(material);
	}

	public ItemStack getItemStack(NTMMaterial material, int amount) {
		if (!materials.contains(material)) throw new RuntimeException("Add " + material.getClass().getPackage().getName() + " { " + material.name + " } " + " to " + this.getClass().getPackage().getName() + " { " + this.itemName + " }");
		return new ItemStack(this, amount, getMetadataFor(material));
	}

	private int getMetadataFor(NTMMaterial material) {
		return this.materials.indexOf(material);
	}

	public ItemStack getItemStack(NTMMaterial material) {
		return this.getItemStack(material, 1);
	}

	public List<NTMMaterial> getMaterials() {
		return this.materials;
	}

}
