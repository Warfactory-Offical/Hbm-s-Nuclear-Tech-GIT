package com.hbm.inventory.material;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.util.ItemStackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static com.hbm.lib.RefStrings.MODID;

public class MaterialShapes extends Item {

	public static final MaterialShapes QUANTUM = new MaterialShapes("quantum", 1); // 1/72 of an ingot, allows the ingot to be divisible through 2, 4, 6, 8, 9, 12, 24 and 36
	public static final MaterialShapes NUGGET = new MaterialShapes("nugget", 8, "nugget");
	public static final MaterialShapes POWDER_TINY = new MaterialShapes("powder_tiny", NUGGET.quantity, "dustTiny");
	public static final MaterialShapes WIRE = new MaterialShapes("wire", 9);
	public static final MaterialShapes BILLET = new MaterialShapes("billet", NUGGET.quantity * 6, "billet");
	public static final MaterialShapes INGOT = new MaterialShapes("ingot", NUGGET.quantity * 9, "ingot");
	public static final MaterialShapes GEM = new MaterialShapes("gem", INGOT.quantity, "gem");
	public static final MaterialShapes CRYSTAL = new MaterialShapes("crystal", INGOT.quantity, "crystal");
	public static final MaterialShapes POWDER = new MaterialShapes("powder", INGOT.quantity, "dust");
	public static final MaterialShapes PLATE = new MaterialShapes("plate", INGOT.quantity, "plate");
	public static final MaterialShapes QUART = new MaterialShapes("quart", 162);
	public static final MaterialShapes BLOCK = new MaterialShapes("block", INGOT.quantity * 9, "block");
	
	private final int quantity;
	private final String[] prefixes;
	private final List<NTMMaterial> materials = new ArrayList<>();

	private MaterialShapes(String itemName, int quantity, String... prefixes) {
		this.quantity = quantity;
		this.prefixes = prefixes;

		setTranslationKey(itemName);
		setRegistryName(itemName);
		setHasSubtypes(true);

//        for(String prefix : prefixes) {
//            Mats.prefixByName.put(prefix, this);
//        }

		setCreativeTab(MainRegistry.partsTab);

		ModItems.ALL_ITEMS.add(this);
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
		if (meta < materials.size()) return super.getTranslationKey() + "_" + materials.get(meta).getNameForItem();
		return super.getTranslationKey() + "_error";
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
	
	public int q(int amount) {
		return this.quantity * amount;
	}
	
	public int q(int unitsUsed, int itemsProduced) { //eg rails: INOGT.q(6, 16) since the recipe uses 6 ton ingots producing 16 individual rail blocks
		return this.quantity * unitsUsed / itemsProduced;
	}

	public String name() {
		return (prefixes != null && prefixes.length > 0) ? prefixes[0] : "unknown";
	}

	public void addMaterial(NTMMaterial material) {
		this.materials.add(material);
	}

}
