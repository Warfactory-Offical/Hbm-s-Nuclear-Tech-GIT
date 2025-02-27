package com.hbm.items.machine;
import com.hbm.util.ItemStackUtil;

import java.util.List;

import javax.annotation.Nonnull;

import com.hbm.interfaces.IHasCustomModel;
import com.hbm.inventory.AssemblerRecipes;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.util.I18nUtil;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemAssemblyTemplate extends Item implements IHasCustomModel {

	public static final ModelResourceLocation location = new ModelResourceLocation(
			RefStrings.MODID + ":assembly_template", "inventory");
	
	//private static IForgeRegistry<Item> itemRegistry;
	//private static IForgeRegistry<Block> blockRegistry;


	//public static List<AssemblerRecipe> recipes = new ArrayList<AssemblerRecipe>();
	//public static List<AssemblerRecipe> recipesBackup = null;
	

	public ItemAssemblyTemplate(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(MainRegistry.templateTab);

		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(final ItemStack stack) {
		String s = (I18n.format(this.getTranslationKey() + ".name")).trim();
		final int damage = getTagWithRecipeNumber(stack).getInteger("type");
		final ItemStack out = damage < AssemblerRecipes.recipeList.size() ? AssemblerRecipes.recipeList.get(damage).toStack() : ItemStack.EMPTY;
		final String s1 = (I18n.format((out != ItemStack.EMPTY ? out.getTranslationKey() : "") + ".name")).trim();

		if (s1 != null) {
			s = s + " " + s1;
		}

		return s;
	}

	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> list) {
		if (tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
			final int count = AssemblerRecipes.recipeList.size();

	    	for(int i = 0; i < count; i++) {
				final NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("type", i);
				final ItemStack stack = ItemStackUtil.itemStackFrom(this, 1, 0);
				stack.setTagCompound(tag);
				list.add(stack);
			}
		}
	}
	
	public static ItemStack getTemplate(final int id){
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("type", id);
		final ItemStack stack = ItemStackUtil.itemStackFrom(ModItems.assembly_template, 1, 0);
		stack.setTagCompound(tag);
		return stack;
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		if (!(stack.getItem() instanceof ItemAssemblyTemplate))
			return;
		
		list.add("§6" + I18nUtil.resolveKey("info.templatefolder"));
		list.add("");

		final int i = getTagWithRecipeNumber(stack).getInteger("type");
		
		if(i < 0 || i >= AssemblerRecipes.recipeList.size()) {
    		list.add("I AM ERROR");
    		return;
    	}

    	final ComparableStack out = AssemblerRecipes.recipeList.get(i);

    	if(out == null) {
    		list.add("I AM ERROR");
    		return;
    	}

    	final Object[] in = AssemblerRecipes.recipes.get(out);

    	if(in == null) {
    		list.add("I AM ERROR");
    		return;
    	}

    	final ItemStack output = out.toStack();

    	list.add("§l" + I18nUtil.resolveKey("info.template_out"));
		list.add(" §a"+ output.getCount() + "x " + output.getDisplayName());
		list.add("§l" + I18nUtil.resolveKey("info.template_in_p"));

		for(final Object o : in) {

			if(o instanceof ComparableStack)  {
				final ItemStack input = ((ComparableStack)o).toStack();
	    		list.add(" §c"+ input.getCount() + "x " + input.getDisplayName());

			} else if(o instanceof OreDictStack input)  {
                final NonNullList<ItemStack> ores = OreDictionary.getOres(input.name);

				if(ores.size() > 0) {
					final ItemStack inStack = ores.get((int) (Math.abs(System.currentTimeMillis() / 1000) % ores.size()));
		    		list.add(" §c"+ input.count() + "x " + inStack.getDisplayName());
				} else {
		    		list.add("I AM ERROR - No OrdDict match found for "+ o);
				}
			}
		}

		list.add("§l" + I18nUtil.resolveKey("info.template_time"));
    	list.add(" §3" + Math.floor((float)(getProcessTime(stack)) / 20 * 100) / 100 + " " + I18nUtil.resolveKey("info.template_seconds"));
	}

	public static int getProcessTime(final ItemStack stack) {
		if (!(stack.getItem() instanceof ItemAssemblyTemplate))
			return 100;
		
		final int i = getTagWithRecipeNumber(stack).getInteger("type");

    	if(i < 0 || i >= AssemblerRecipes.recipeList.size())
    		return 100;

    	final ComparableStack out = AssemblerRecipes.recipeList.get(i);
    	final Integer time = AssemblerRecipes.time.get(out);

    	if(time != null)
    		return time;
    	else
    		return 100;

	}

	@Override
	public ModelResourceLocation getResourceLocation() {
		return location;
	}
	
	public static int getRecipeIndex(final ItemStack stack){
		return getTagWithRecipeNumber(stack).getInteger("type");
	}

	public static NBTTagCompound getTagWithRecipeNumber(@Nonnull final ItemStack stack){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("type", 0);
		}
		return stack.getTagCompound();
	}
}
