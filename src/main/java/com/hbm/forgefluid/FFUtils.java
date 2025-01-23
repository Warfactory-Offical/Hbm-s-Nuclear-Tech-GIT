package com.hbm.forgefluid;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.hbm.interfaces.IFluidPipe;
import com.hbm.interfaces.IFluidPipeMk2;
import com.hbm.interfaces.IFluidVisualConnectable;
import com.hbm.interfaces.IItemFluidHandler;
import com.hbm.inventory.FluidCombustionRecipes;
import com.hbm.inventory.HeatRecipes;
import com.hbm.inventory.EngineRecipes;
import com.hbm.inventory.gui.GuiInfoContainer;
import com.hbm.items.ModItems;
import com.hbm.items.armor.JetpackBase;
import com.hbm.items.machine.ItemFluidTank;
import com.hbm.items.special.ItemCell;
import com.hbm.handler.ArmorModHandler;
import com.hbm.items.tool.ItemFluidCanister;
import com.hbm.items.tool.ItemGasCanister;
import com.hbm.lib.Library;
import com.hbm.render.RenderHelper;
import com.hbm.tileentity.machine.TileEntityDummy;

import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;

//Drillgon200: This is Library.java except for fluids
//Drillgon200: Let's hope this works without bugs in 1.12.2...
//Drillgon200: Still mad they removed the fluid container registry.
public class FFUtils {

	// Drillgon200: Wow that took a while to fix. Now the code is ugly and I'll
	// probably never fix it because it works. Dang it.
	/**
	 * Tessellates a liquid texture across a rectangle without looking weird and
	 * stretched.
	 * 
	 * @param tank
	 *            - the tank with the fluid to render
	 * @param guiLeft
	 *            - the left side of the gui
	 * @param guiTop
	 *            - the top of the gui
	 * @param zLevel
	 *            - the z level of the gui
	 * @param sizeX
	 *            - how big the rectangle should be
	 * @param sizeY
	 *            - how tall the rectangle should be
	 * @param offsetX
	 *            - where the starting x of the rectangle should be on screen
	 * @param offsetY
	 *            - where the starting y of the rectangle should be on screen
	 */
	public static void drawLiquid(final FluidTank tank, final int guiLeft, final int guiTop, final float zLevel, final int sizeX, final int sizeY, final int offsetX, final int offsetY){
		drawLiquid(tank, guiLeft, guiTop, zLevel, sizeX, sizeY, offsetX, offsetY, false);
	}

	public static void drawLogLiquid(final FluidTank tank, final int guiLeft, final int guiTop, final float zLevel, final int sizeX, final int sizeY, final int offsetX, final int offsetY){
		drawLiquid(tank, guiLeft, guiTop, zLevel, sizeX, sizeY, offsetX, offsetY, true);
	}

	public static void drawLiquid(final FluidTank tank, final int guiLeft, final int guiTop, final float zLevel, final int sizeX, final int sizeY, final int offsetX, int offsetY, final boolean log){
		// This is retarded, but it would be too much of a pain to fix it
		offsetY -= 44;
		RenderHelper.bindBlockTexture();

		if(tank.getFluid() != null) {
			final TextureAtlasSprite liquidIcon = getTextureFromFluid(tank.getFluid().getFluid());

			if(liquidIcon != null) {
				int level = 0;
				if(log){
					if(tank.getFluidAmount() > 0){
						level = (int)(sizeY * (Math.log(tank.getFluidAmount()) / Math.log(tank.getCapacity())));
					}
				} else{
					level = (int)(((double)tank.getFluidAmount() / (double)tank.getCapacity()) * sizeY);
				}

				drawFull(tank.getFluid().getFluid(), guiLeft, guiTop, zLevel, liquidIcon, level, sizeX, offsetX, offsetY, sizeY);
			}
		}
	}

	public static void drawLiquid(final FluidStack fluid, final int guiLeft, final int guiTop, final float zLevel, final int sizeX, final int sizeY, final int offsetX, final int offsetY){
		if(fluid == null || fluid.getFluid() == null)
			return;
		drawLiquid(fluid.getFluid(), guiLeft, guiTop, zLevel, sizeX, sizeY, offsetX, offsetY);
	}

	public static void drawLiquid(final Fluid fluid, final int guiLeft, final int guiTop, final float zLevel, final int sizeX, final int sizeY, final int offsetX, final int offsetY){
		RenderHelper.bindBlockTexture();
		if(fluid != null) {
			final TextureAtlasSprite liquidIcon = getTextureFromFluid(fluid);
			if(liquidIcon != null) {
				drawFull(fluid, guiLeft, guiTop, zLevel, liquidIcon, sizeY, sizeX, offsetX, offsetY, sizeY);
			}
		}
	}

	/**
	 * Internal method to actually render the fluid
	 * 
	 * @param tank
	 * @param guiLeft
	 * @param guiTop
	 * @param zLevel
	 * @param liquidIcon
	 * @param level
	 * @param sizeX
	 * @param offsetX
	 * @param offsetY
	 */
	private static void drawFull(final Fluid f, final int guiLeft, final int guiTop, final float zLevel, final TextureAtlasSprite liquidIcon, final int level, final int sizeX, final int offsetX, final int offsetY, final int sizeY){
		final int color = f.getColor();
		RenderHelper.setColor(color);
		RenderHelper.startDrawingTexturedQuads();
		for(int i = 0; i < level; i += 16) {
			for(int j = 0; j < sizeX; j += 16) {
				final int drawX = Math.min(16, sizeX - j);
				final int drawY = Math.min(16, level - i);
				RenderHelper.drawScaledTexture(liquidIcon, guiLeft + offsetX + j, guiTop + offsetY - i + (16 - drawY), drawX, drawY, zLevel);
			}
		}
		RenderHelper.draw();
	}

	/**
	 * Renders tank info, like fluid type and millibucket amount. Same as the
	 * hbm one, just centralized to a utility file.
	 * 
	 * @param gui
	 *            - the gui to render the fluid info on
	 * @param mouseX
	 *            - the cursor's x position
	 * @param mouseY
	 *            - the cursor's y position
	 * @param x
	 *            - the x left corner of where to render the info
	 * @param y
	 *            - the y top corner of where to render the info
	 * @param width
	 *            - how wide the area to render info inside is
	 * @param height
	 *            - how tall the area to render info inside is
	 * @param fluidTank
	 *            - the tank to render info of
	 */
	public static void renderTankInfo(final GuiInfoContainer gui, final int mouseX, final int mouseY, final int x, final int y, final int width, final int height, final FluidTank fluidTank){
		renderTankInfo(gui, mouseX, mouseY, x, y, width, height, fluidTank, null);
	}

	public static void renderTankInfo(final GuiInfoContainer gui, final int mouseX, final int mouseY, final int x, final int y, final int width, final int height, final FluidTank fluidTank, final Fluid fluid){
		if(fluidTank.getFluid() != null) {
			renderFluidInfo(gui, mouseX, mouseY, x, y, width, height, fluidTank.getFluid().getFluid(), fluidTank.getFluidAmount(), fluidTank.getCapacity());
		} else {
			renderFluidInfo(gui, mouseX, mouseY, x, y, width, height, fluid, 0, fluidTank.getCapacity());
		}
	}

	public static void addFluidInfo(final Fluid fluid, final List<String> texts){
		final int temp = fluid.getTemperature()-273;
		if(temp != 27){
			String tempColor = "";
			if(temp < -130) {
				tempColor = "§3";
			} else if(temp < 0) {
				tempColor = "§b";
			} else if(temp < 100) {
				tempColor = "§e";
			} else if(temp < 300) {
				tempColor = "§6";
			} else if(temp < 1000) {
				tempColor = "§c";
			} else if(temp < 3000) {
				tempColor = "§4";
			} else if(temp < 20000) {
				tempColor = "§5";
			} else {
				tempColor = "§d";
			}
			texts.add(String.format("%s%d°C", tempColor, temp));
		}
		boolean hasInfo = false;
		final boolean isKeyPressed = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);

		if (FluidTypeHandler.isAntimatter(fluid)) {
			if(isKeyPressed){
				texts.add("§4["+I18n.format("trait.antimatter")+"]");
			}
			hasInfo = true;
		}

		if (FluidTypeHandler.isCorrosivePlastic(fluid)) {
			if (FluidTypeHandler.isCorrosiveIron(fluid)) {
				if(isKeyPressed){
					texts.add("§2["+I18n.format("trait.corrosiveIron")+"]");
				}
			} else if(isKeyPressed){
				texts.add("§a["+I18n.format("trait.corrosivePlastic")+"]");
			}
			hasInfo = true;
		}

		if (FluidCombustionRecipes.hasFuelRecipe(fluid)) {
			if(isKeyPressed){
				texts.add("§6["+I18n.format("trait.flammable")+"]");
				texts.add(" "+I18n.format("trait.flammable.desc", Library.getShortNumber(FluidCombustionRecipes.getFlameEnergy(fluid) * 1000L)));
			}
			hasInfo = true;
		}
		if (EngineRecipes.hasFuelRecipe(fluid)) {
			if(isKeyPressed){
				texts.add("§c["+I18n.format("trait.combustable")+"]");
				
				texts.add(" "+I18n.format("trait.combustable.desc", Library.getShortNumber(EngineRecipes.getEnergy(fluid))));
				texts.add(" "+I18n.format("trait.combustable.desc2", I18n.format(EngineRecipes.getFuelGrade(fluid).getGrade())));
			}
			hasInfo = true;
		}

		if (HeatRecipes.hasCoolRecipe(fluid)) {
			if(isKeyPressed){
				final String heat = Library.getShortNumber(HeatRecipes.getResultingHeat(fluid) * 1000L / HeatRecipes.getInputAmountCold(fluid));
				texts.add("§4["+I18n.format("trait.coolable")+"]");
				texts.add(" "+I18n.format("trait.coolable.desc", heat));
			}
			hasInfo = true;
		}

		if (HeatRecipes.hasBoilRecipe(fluid)) {
			if(isKeyPressed){
				final String heat = Library.getShortNumber(HeatRecipes.getRequiredHeat(fluid) * 1000L / HeatRecipes.getInputAmountHot(fluid));
				texts.add("§3["+I18n.format("trait.boilable")+"]");
				texts.add(" "+I18n.format("trait.boilable.desc", heat));
			}
			hasInfo = true;
		}

		float dfcEff = FluidTypeHandler.getDFCEfficiency(fluid);

		if(dfcEff >= 1){
			if(isKeyPressed){
				texts.add("§5["+I18n.format("trait.dfcFuel")+"]");
				dfcEff = (dfcEff-1F);
				texts.add(" "+I18n.format("trait.dfcFuel.desc", dfcEff >= 0 ? "+"+Library.getPercentage(dfcEff) : Library.getPercentage(dfcEff)));
			}
			hasInfo = true;
		}

		if (hasInfo && !isKeyPressed) {
			texts.add(I18nUtil.resolveKey("desc.tooltip.hold", "LSHIFT"));
		}
	}

	private static void renderFluidInfo(final GuiInfoContainer gui, final int mouseX, final int mouseY, final int x, final int y, final int width, final int height, final Fluid fluid, final int amount, final int capacity) {
		if (x <= mouseX && x + width > mouseX && y < mouseY && y + height >= mouseY) {
			final List<String> texts = new ArrayList<>();
			if (fluid != null) {
				texts.add(fluid.getLocalizedName(new FluidStack(fluid, 1)));
				texts.add(amount + "/" + capacity + "mB");
				addFluidInfo(fluid, texts);
			} else {
				texts.add(I18nUtil.resolveKey("desc.none"));
				texts.add(amount + "/" + capacity + "mB");
			}

			gui.drawFluidInfo(texts, mouseX, mouseY);
		}
	}

	public static boolean hasEnoughFluid(final FluidTank t, final FluidStack f){
		if(f == null || f.amount == 0) return true;
		if(t == null || t.getFluid() == null) return false;
        return t.getFluid().isFluidEqual(f) && t.getFluidAmount() >= f.amount;
    }

	/**
	 * Replacement method for the old method of transferring fluids out of a
	 * machine
	 * 
	 * @param tileEntity
	 *            - the tile entity it is filling from
	 * @param tank
	 *            - the fluid tank to fill from
	 * @param world
	 *            - the world the filling is taking place in
	 * @param i
	 *            - x coord of place to fill
	 * @param j
	 *            - y coord of place to fill
	 * @param k
	 *            - z coord of place to fill
	 * @param maxDrain
	 *            - the maximum amount that can be drained from the tank at a
	 *            time
	 * @return Whether something was actually filled or not, or whether it needs
	 *         an update
	 */

	public static boolean fillFluid(final TileEntity tileEntity, final FluidTank tank, final World world, final BlockPos toFill, final int maxDrain){
		if(tank.getFluidAmount() <= 0 || tank.getFluid() == null || tank.getFluid().getFluid() == null) {
			return false;
		}
		final TileEntity te = world.getTileEntity(toFill);

		if(te != null && te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
			if(te instanceof TileEntityDummy ted) {
                if(world.getTileEntity(ted.target) == tileEntity) {
					return false;
				}
			}
			try{
				final IFluidHandler tef = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
				if(tef != null && tef.fill(new FluidStack(tank.getFluid(), Math.min(maxDrain, tank.getFluidAmount())), false) > 0) {
					tank.drain(tef.fill(new FluidStack(tank.getFluid(), Math.min(maxDrain, tank.getFluidAmount())), true), true);
					return true;
				}
			} catch(final Throwable t){
				return false;
			}
		}
		return false;
	}

	/**
	 * Fills a fluid handling item from a tank
	 * 
	 * @param slots
	 *            - the slot inventory
	 * @param tank
	 *            - the tank to fill from
	 * @param slot1
	 *            - the slot with an empty container
	 * @param slot2
	 *            - the output slot.
	 * @return true if something was actually filled
	 */
	public static boolean fillFromFluidContainer(final IItemHandlerModifiable slots, final FluidTank tank, final int slot1, final int slot2){ // fills fluid from item into tank
		if(slots == null || tank == null || slots.getSlots() < slot1 || slots.getSlots() < slot2 || slots.getStackInSlot(slot1) == null || slots.getStackInSlot(slot1).isEmpty()) {
			return false;
		}

		if(trySpecialFillFromFluidContainer(slots, tank, slot1, slot2))
			return true;

		if(slots.getStackInSlot(slot1).getItem() == ModItems.fluid_barrel_infinite && tank.getFluid() != null) {

			return tank.fill(new FluidStack(tank.getFluid(), Integer.MAX_VALUE), true) > 0;
		}
		if(FluidUtil.getFluidContained(slots.getStackInSlot(slot1)) == null) {

			moveItems(slots, slot1, slot2, false);
			return false;
		}
		if(slots.getStackInSlot(slot1).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			boolean returnValue = false;

			final IFluidHandlerItem ifhi = slots.getStackInSlot(slot1).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			if(ifhi != null && (tank.getFluid() == null || FluidUtil.getFluidContained(slots.getStackInSlot(slot1)).getFluid() == tank.getFluid().getFluid())) {
				tank.fill(ifhi.drain(Math.min(6000, tank.getCapacity() - tank.getFluidAmount()), true), true);
				returnValue = true;
			}
			if(ifhi.drain(Integer.MAX_VALUE, false) == null) {
				moveItems(slots, slot1, slot2, true);
			}
			return returnValue;
		}
		final ItemStack stack = slots.getStackInSlot(slot1);
		if(stack.getItem() instanceof IItemFluidHandler handler) {
			boolean returnValue = false;
            final FluidStack contained = handler.drain(stack, Integer.MAX_VALUE, false);
			if(contained != null)
				if(tank.getFluid() == null || contained.getFluid() == tank.getFluid().getFluid()) {
					tank.fill(handler.drain(stack, Math.min(6000, tank.getCapacity() - tank.getFluidAmount()), true), true);
					returnValue = true;
				}
			if(handler.drain(stack, Integer.MAX_VALUE, false) == null) {
				moveItems(slots, slot1, slot2, true);
			}
			return returnValue;
		}
		return false;
	}

	// Ah yes, hacky special methods to make stacks drain.
	private static boolean trySpecialFillFromFluidContainer(final IItemHandlerModifiable slots, final FluidTank tank, final int slot1, final int slot2){
		final ItemStack in = slots.getStackInSlot(slot1);
		final ItemStack out = slots.getStackInSlot(slot2);

		// Fluid Tank override
		if(in.getItem() == ModItems.fluid_tank_full && tank.fill(FluidUtil.getFluidContained(in), false) == 1000 && ((ItemFluidTank.isEmptyTank(out) && out.getCount() < 64) || out.isEmpty())) {
			tank.fill(FluidUtil.getFluidContained(in), true);
			in.shrink(1);
			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.fluid_tank_full));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Fluid barrel override
		if(in.getItem() == ModItems.fluid_barrel_full && tank.fill(FluidUtil.getFluidContained(in), false) == 16000 && ((ItemFluidTank.isEmptyBarrel(out) && out.getCount() < 64) || out.isEmpty())) {
			tank.fill(FluidUtil.getFluidContained(in), true);
			in.shrink(1);
			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.fluid_barrel_full));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Canister override
		if(in.getItem() == ModItems.canister_generic && tank.fill(FluidUtil.getFluidContained(in), false) == 1000 && ((ItemFluidCanister.isEmptyCanister(out) && out.getCount() < 64) || out.isEmpty())) {
			tank.fill(FluidUtil.getFluidContained(in), true);
			in.shrink(1);
			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.canister_generic));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Gas canister override
		if(in.getItem() == ModItems.gas_canister && tank.fill(FluidUtil.getFluidContained(in), false) == 4000 && ((ItemGasCanister.isEmptyCanister(out) && out.getCount() < 64) || out.isEmpty())) {
			tank.fill(FluidUtil.getFluidContained(in), true);
			in.shrink(1);
			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.gas_canister));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Cell override
		if(in.getItem() == ModItems.cell && tank.fill(FluidUtil.getFluidContained(in), false) == 1000 && ((ItemCell.isEmptyCell(out) && out.getCount() < 64) || out.isEmpty())) {
			tank.fill(FluidUtil.getFluidContained(in), true);
			in.shrink(1);
			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.cell));
			} else {
				out.grow(1);
			}
			return true;
		}

		//Mercury override
		//Oh god, these overrides are getting worse and worse, but it would take a large amount of effort to make the code good
		if(ItemStackUtil.isSameMetaItem(in, ModItems.nugget.getItemStack(MaterialMineral.MERCURY)) && tank.fill(new FluidStack(ModForgeFluids.mercury, 125), false) == 125){
			tank.fill(new FluidStack(ModForgeFluids.mercury, 125), true);
			in.shrink(1);
			return true;
		}

		//That's it. I'm making a fluid container registry just so I don't have to make this method any worse.
		if(FluidContainerRegistry.hasFluid(in)) {
			final FluidStack fluid = FluidContainerRegistry.getFluidFromItem(in);
			final ItemStack container = FluidContainerRegistry.getContainerItem(in);
			if(tank.fill(fluid, false) == fluid.amount && (out.isEmpty() || (out == container && out.getCount() < out.getMaxStackSize()))) {
				tank.fill(fluid, true);
				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(container));
				} else {
					out.grow(1);
				}
			}
		}

		return false;
	}

	//Jesus H. Christ I hate this class
	public static boolean checkRestrictions(final ItemStack stack, final Predicate<FluidStack> fluidRestrictor){
		if(stack.getItem() == ModItems.fluid_barrel_infinite)
			return true;
		FluidStack fluid = FluidUtil.getFluidContained(stack);
		if(fluid != null && fluidRestrictor.apply(fluid))
			return true;
		if(FluidContainerRegistry.hasFluid(stack)) {
			fluid = FluidContainerRegistry.getFluidFromItem(stack);
            return fluid != null && fluidRestrictor.apply(fluid);
		}
		return false;
	}

	public static boolean isEmtpyFluidTank(final ItemStack stack){
		return stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null) && FluidUtil.getFluidContained(stack) == null;
	}

	/**
	 * Fills a tank from a fluid handler item.
	 * 
	 * @param slots
	 *            - the slot inventory
	 * @param tank
	 *            - the tank to be filled
	 * @param slot1
	 *            - the slot with the full container
	 * @param slot2
	 *            - the output slot
	 */
	public static boolean fillFluidContainer(final IItemHandlerModifiable slots, final FluidTank tank, final int slot1, final int slot2){ //fills fluid from tank into item
		if(slots == null || tank == null || tank.getFluid() == null || slots.getSlots() < slot1 || slots.getSlots() < slot2 || slots.getStackInSlot(slot1) == null || slots.getStackInSlot(slot1).isEmpty()) {
			return false;
		}

		if(trySpecialFillFluidContainer(slots, tank, slot1, slot2))
			return true;

		final ItemStack stack = slots.getStackInSlot(slot1);
		if(stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			final IFluidHandlerItem ifhi = FluidUtil.getFluidHandler(stack);
			final FluidStack fStack = FluidUtil.getFluidContained(stack);
			return fillItemAndMove(slots, slot1, slot2, tank, ifhi, fStack, stack, true);
		}

		if(stack.getItem() instanceof IItemFluidHandler handler) {
            final FluidStack contained = handler.drain(stack, Integer.MAX_VALUE, false);
			return fillItemAndMove(slots, slot1, slot2, tank, handler, contained, stack, true);
		}

		if(ArmorModHandler.hasMods(stack)){

			final ItemStack mod = ArmorModHandler.pryMod(stack, ArmorModHandler.plate_only);
			boolean didFill = false;
			if(!mod.isEmpty()){
				if(mod.getItem() instanceof JetpackBase && ((JetpackBase)mod.getItem()).fuel == tank.getFluid().getFluid()) {

					if(tank.getFluidAmount() > 0 && JetpackBase.getFuel(mod) < ((JetpackBase)mod.getItem()).maxFuel) {
						final FluidStack st = tank.drain(25, false);
						int fill = st == null ? 0 : st.amount;
						fill = Math.min(((JetpackBase)mod.getItem()).maxFuel-JetpackBase.getFuel(mod), fill);
						if(fill > 0){
							JetpackBase.setFuel(mod, JetpackBase.getFuel(mod) + fill);
							tank.drain(fill, true);
							if(JetpackBase.getFuel(mod) < ((JetpackBase)mod.getItem()).maxFuel) {
								didFill = true;
							}
							ArmorModHandler.applyMod(stack, mod);
						}
					}
				}
			}
			if(!didFill)
				moveItems(slots, slot1, slot2, false);
			else
				return true;
		}

		return false;
	}

	private static boolean fillItemAndMove(final IItemHandlerModifiable slots, final int slot1, final int slot2, final FluidTank tank, final IFluidHandlerItem fHandler, FluidStack fStack, ItemStack stack, final boolean move){
		if(fStack != null && fHandler.fill(tank.getFluid(), false) <= 0) {
			if(move) moveItems(slots, slot1, slot2, false);
			return false;
		}
		boolean returnValue = false;
		if(fStack == null || fStack.getFluid() == tank.getFluid().getFluid()) {
			tank.drain(fHandler.fill(new FluidStack(tank.getFluid(), Math.min(16000, tank.getFluidAmount())), true), true);
			returnValue = true;
		}
		stack = fHandler.getContainer();
		fStack = FluidUtil.getFluidContained(stack);
		if(fStack != null && fHandler.fill(new FluidStack(fStack.getFluid(), Integer.MAX_VALUE), false) <= 0) {
			if(move) moveItems(slots, slot1, slot2, false);
		}
		return returnValue;
	}

	private static boolean fillItemAndMove(final IItemHandlerModifiable slots, final int slot1, final int slot2, final FluidTank tank, final IItemFluidHandler fHandler, FluidStack fStack, final ItemStack stack, final boolean move){
		if(fStack != null && fHandler.fill(stack, tank.getFluid(), false) <= 0) {
			if(move) moveItems(slots, slot1, slot2, false);
			return false;
		}
		boolean returnValue = false;
		if(fStack == null || fStack.getFluid() == tank.getFluid().getFluid()) {
			tank.drain(fHandler.fill(stack, new FluidStack(tank.getFluid(), Math.min(16000, tank.getFluidAmount())), true), true);
			returnValue = true;
		}
		fStack = fHandler.drain(stack, Integer.MAX_VALUE, false);
		if(fStack != null && fHandler.fill(stack, new FluidStack(fStack.getFluid(), Integer.MAX_VALUE), false) <= 0) {
			if(move) moveItems(slots, slot1, slot2, false);
		}
		return returnValue;
	}

	// Ah yes, hacky special methods to make stacks drain.
	private static boolean trySpecialFillFluidContainer(final IItemHandlerModifiable slots, final FluidTank tank, final int slot1, final int slot2){ //drains tank into item
		final ItemStack in = slots.getStackInSlot(slot1);
		final ItemStack out = slots.getStackInSlot(slot2);

		final ItemStack in1 = in.copy();
		in1.setCount(1);

		// Fluid Tank override
		if(tank.getFluid() != null && in.getItem() == ModItems.fluid_tank_full && tank.drain(1000, false) != null && tank.drain(1000, false).amount == 1000 && ItemFluidTank.isEmptyTank(in1) && ((ItemFluidTank.isFullTank(out, tank.getFluid().getFluid()) && out.getCount() < 64) || out.isEmpty())) {
			final FluidStack f = tank.drain(1000, true);
			if(f == null)
				return false;
			in.shrink(1);

			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemFluidTank.getFullTank(f.getFluid()));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Fluid barrel override
		if(tank.getFluid() != null && in.getItem() == ModItems.fluid_barrel_full && tank.drain(16000, false) != null && tank.drain(16000, false).amount == 16000 && ItemFluidTank.isEmptyBarrel(in1) && ((ItemFluidTank.isFullBarrel(out, tank.getFluid().getFluid()) && out.getCount() < 64) || out.isEmpty())) {
			final FluidStack f = tank.drain(16000, true);
			if(f == null)
				return false;
			in.shrink(1);

			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemFluidTank.getFullBarrel(f.getFluid()));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Canister override
		if(tank.getFluid() != null && in.getItem() == ModItems.canister_generic && SpecialContainerFillLists.EnumCanister.contains(tank.getFluid().getFluid()) && tank.drain(1000, false) != null && tank.drain(1000, false).amount == 1000 && ItemFluidCanister.isEmptyCanister(in1) && ((ItemFluidCanister.isFullCanister(out, tank.getFluid().getFluid()) && out.getCount() < 64) || out.isEmpty())) {
			final FluidStack f = tank.drain(1000, true);
			if(f == null)
				return false;
			in.shrink(1);

			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemFluidCanister.getFullCanister(f.getFluid()));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Gas canister override
		if(tank.getFluid() != null && in.getItem() == ModItems.gas_canister && SpecialContainerFillLists.EnumGasCanister.contains(tank.getFluid().getFluid()) && tank.drain(4000, false) != null && tank.drain(4000, false).amount == 4000 && ItemGasCanister.isEmptyCanister(in1) && ((ItemGasCanister.isFullCanister(out, tank.getFluid().getFluid()) && out.getCount() < 64) || out.isEmpty())) {
			final FluidStack f = tank.drain(4000, true);
			if(f == null)
				return false;
			in.shrink(1);

			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemGasCanister.getFullCanister(f.getFluid()));
			} else {
				out.grow(1);
			}
			return true;
		}

		
		// Cell override
		if(tank.getFluid() != null && in.getItem() == ModItems.cell && SpecialContainerFillLists.EnumCell.contains(tank.getFluid().getFluid()) && tank.drain(1000, false) != null && tank.drain(1000, false).amount == 1000 && ItemCell.isEmptyCell(in1) && ((ItemCell.isFullCell(out, tank.getFluid().getFluid()) && out.getCount() < 64) || out.isEmpty())) {
			final FluidStack f = tank.drain(1000, true);
			if(f == null)
				return false;
			in.shrink(1);

			if(out.isEmpty()) {
				slots.setStackInSlot(slot2, ItemCell.getFullCell(f.getFluid()));
			} else {
				out.grow(1);
			}
			return true;
		}

		// Rod override (extra messy because I don't feel like restarting
		// minecraft to make a helper method)
		if(in.getItem() == ModItems.rod_empty) {
			if(tank.getFluid() != null && tank.getFluid().getFluid() == ModForgeFluids.coolant && tank.getFluid().amount >= 1000 && out.isEmpty()) {
				tank.drain(1000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_coolant));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_coolant));
				}
				return true;
			}
			if(tank.getFluid() != null && tank.getFluid().getFluid() == ModForgeFluids.tritium && tank.getFluid().amount >= 1000 && out.isEmpty()) {
				tank.drain(1000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_tritium));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_tritium));
				}
				return true;
			}
			if(tank.getFluid() != null && tank.getFluid().getFluid() == FluidRegistry.WATER && tank.getFluid().amount >= 1000 && out.isEmpty()) {
				tank.drain(1000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_water));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_water));
				}
				return true;
			}
		}
		if(in.getItem() == ModItems.rod_dual_empty) {
			if(tank.getFluid() != null && tank.getFluid().getFluid() == ModForgeFluids.coolant && tank.getFluid().amount >= 2000 && out.isEmpty()) {
				tank.drain(2000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_dual_coolant));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_dual_coolant));
				}
				return true;
			}
			if(tank.getFluid() != null && tank.getFluid().getFluid() == ModForgeFluids.tritium && tank.getFluid().amount >= 2000 && out.isEmpty()) {
				tank.drain(2000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_dual_tritium));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_dual_tritium));
				}
				return true;
			}
			if(tank.getFluid() != null && tank.getFluid().getFluid() == FluidRegistry.WATER && tank.getFluid().amount >= 2000 && out.isEmpty()) {
				tank.drain(2000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_dual_water));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_dual_water));
				}
				return true;
			}
		}
		if(in.getItem() == ModItems.rod_quad_empty) {
			if(tank.getFluid() != null && tank.getFluid().getFluid() == ModForgeFluids.coolant && tank.getFluid().amount >= 4000 && out.isEmpty()) {
				tank.drain(4000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_quad_coolant));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_quad_coolant));
				}
				return true;
			}
			if(tank.getFluid() != null && tank.getFluid().getFluid() == ModForgeFluids.tritium && tank.getFluid().amount >= 4000 && out.isEmpty()) {
				tank.drain(4000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_quad_tritium));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_quad_tritium));
				}
				return true;
			}
			if(tank.getFluid() != null && tank.getFluid().getFluid() == FluidRegistry.WATER && tank.getFluid().amount >= 4000 && out.isEmpty()) {
				tank.drain(4000, true);

				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(ModItems.rod_quad_water));
				} else {
					slots.setStackInSlot(slot1, ItemStackUtil.itemStackFrom(ModItems.rod_quad_water));
				}
				return true;
			}
		}

		if(in.getItem() instanceof JetpackBase && ((JetpackBase)in.getItem()).fuel == tank.getFluid().getFluid()) {

			if(tank.getFluidAmount() > 0 && JetpackBase.getFuel(in) < ((JetpackBase)in.getItem()).maxFuel) {
				final FluidStack st = tank.drain(25, false);
				final int fill = st == null ? 0 : st.amount;
				JetpackBase.setFuel(in, Math.min(JetpackBase.getFuel(in) + fill, ((JetpackBase)in.getItem()).maxFuel));
				tank.drain(fill, true);
				if(JetpackBase.getFuel(in) >= ((JetpackBase)in.getItem()).maxFuel && out.isEmpty()) {
					slots.setStackInSlot(slot2, in);
					slots.setStackInSlot(slot1, ItemStack.EMPTY);
				}
				return true;
			}
		}

		final ItemStack container = FluidContainerRegistry.getFullContainer(in, tank.getFluid().getFluid());
		if(container != null && container != Items.AIR.getDefaultInstance()) {
			final FluidStack stack = FluidContainerRegistry.getFluidFromItem(container);
			if(tank.drain(stack, false).amount == stack.amount && (out.isEmpty() || (ItemStackUtil.isSameMetaItem(out, container) && out.getCount() < out.getMaxStackSize()))) {
				tank.drain(stack, true);
				in.shrink(1);
				if(out.isEmpty()) {
					slots.setStackInSlot(slot2, ItemStackUtil.itemStackFrom(container));
				} else {
					out.grow(1);
				}
				return true;
			}
		}

		return false;
	}

	public static boolean moveItems(final IItemHandlerModifiable slots, final int in, final int out, final boolean shouldUseContainerItem){
		if(slots.getStackInSlot(in) != null && !slots.getStackInSlot(in).isEmpty()) {
			if(shouldUseContainerItem && slots.getStackInSlot(in).getItem().hasContainerItem(slots.getStackInSlot(in))) {
				slots.setStackInSlot(in, slots.getStackInSlot(in).getItem().getContainerItem(slots.getStackInSlot(in)));
			}
			if(slots.getStackInSlot(out) == null || slots.getStackInSlot(out).isEmpty()) {

				slots.setStackInSlot(out, slots.getStackInSlot(in));
				slots.setStackInSlot(in, ItemStack.EMPTY);
				return true;
			} else if(Library.areItemStacksEqualIgnoreCount(slots.getStackInSlot(in), slots.getStackInSlot(out))) {
				final int amountToTransfer = Math.min(slots.getStackInSlot(out).getMaxStackSize() - slots.getStackInSlot(out).getCount(), slots.getStackInSlot(in).getCount());
				slots.getStackInSlot(in).shrink(amountToTransfer);
				if(slots.getStackInSlot(in).getCount() <= 0)
					slots.setStackInSlot(in, ItemStack.EMPTY);

				slots.getStackInSlot(out).grow(amountToTransfer);
				return true;
			}
		}
		return false;
	}

	public static FluidTank changeTankSize(final FluidTank fluidTank, final int i){
		final FluidTank newTank = new FluidTank(i);
		if(fluidTank.getFluid() == null) {
			return newTank;
		} else {
			newTank.fill(fluidTank.getFluid(), true);
			return newTank;
		}
	}

	public static TextureAtlasSprite getTextureFromFluid(final Fluid f){
		if(f == null) {
			return null;
		}
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(f.getStill().toString());
	}

	public static int getColorFromFluid(final Fluid f){
		return Library.getColorFromResourceLocation(new ResourceLocation(f.getStill().getNamespace(), "textures/"+f.getStill().getPath()+".png"));
	}

	public static void setColorFromFluid(final Fluid f){
		if(f == null)
			return;

		setRGBAFromHex(f.getColor());
	}

	public static void setRGBAFromHex(final int color){
		final float r = (color >> 16 & 0xFF) / 255F;
		final float g = (color >> 8 & 0xFF) / 255F;
		final float b = (color & 0xFF) / 255F;
		final float a = (color >> 24 & 0xFF) / 255F;

		GlStateManager.color(r, g, b, a);
	}

	public static void setRGBFromHex(final int color){
		final float r = (color >> 16 & 0xFF) / 255F;
		final float g = (color >> 8 & 0xFF) / 255F;
		final float b = (color & 0xFF) / 255F;

		GlStateManager.color(r, g, b, 1);
	}

	public static boolean containsFluid(final ItemStack stack, final Fluid fluid){
		if(stack.getItem() == ModItems.fluid_barrel_infinite)
			return true;
		FluidStack contained = FluidUtil.getFluidContained(stack);
		if(contained != null && contained.getFluid() == fluid)
			return true;
		if(FluidContainerRegistry.hasFluid(stack)) {
			contained = FluidContainerRegistry.getFluidFromItem(stack);
            return contained != null && contained.getFluid() == fluid;
		}
		return false;
	}

	public static NBTTagList serializeTankArray(final FluidTank[] tanks){
		final NBTTagList list = new NBTTagList();
		for(int i = 0; i < tanks.length; i++) {
			if(tanks[i] != null) {
				final NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("tank", (byte)i);
				tanks[i].writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		return list;
	}

	public static void deserializeTankArray(final NBTTagList tankList, final FluidTank[] tanks){
		for(int i = 0; i < tankList.tagCount(); i++) {
			final NBTTagCompound tag = tankList.getCompoundTagAt(i);
			final byte b0 = tag.getByte("tank");
			if(b0 >= 0 && b0 < tanks.length) {
				tanks[b0].readFromNBT(tag);
			}
		}
	}

	public static boolean areTanksEqual(final FluidTank tank1, final FluidTank tank2){
		if(tank1 == null && tank2 == null) {
			return true;
		}
		if(tank1 == null ^ tank2 == null) {
			return false;
		}
		if(tank1.getFluid() == null && tank2.getFluid() == null) {
			return true;
		}
		if(tank1.getFluid() == null ^ tank2.getFluid() == null) {
			return false;
		}
        return tank1.getFluid().amount == tank2.getFluid().amount && tank1.getFluid().getFluid() == tank2.getFluid().getFluid() && tank1.getCapacity() == tank2.getCapacity();
    }

	public static FluidTank copyTank(final FluidTank tank){
		if(tank == null)
			return null;
		return new FluidTank(tank.getFluid() != null ? tank.getFluid().copy() : null, tank.getCapacity());
	}

	public static boolean checkFluidConnectables(final World world, final BlockPos pos, final FFPipeNetwork net, @Nullable final EnumFacing facing){
		final TileEntity tileentity = world.getTileEntity(pos);
		if(tileentity != null && tileentity instanceof IFluidPipe && ((IFluidPipe)tileentity).getNetworkTrue() == net)
			return true;
        return tileentity != null && !(tileentity instanceof IFluidPipe) && tileentity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing);
    }

	public static boolean checkFluidConnectablesMk2(final World world, final BlockPos pos, final Fluid type, @Nullable final EnumFacing facing){
		final TileEntity tileentity = world.getTileEntity(pos);
		if(tileentity instanceof IFluidPipeMk2 && ((IFluidPipeMk2)tileentity).getType() == type)
			return true;
		if(tileentity != null && !(tileentity instanceof IFluidPipeMk2) && tileentity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
			return true;
		}
		final Block block = world.getBlockState(pos).getBlock();
		if(block instanceof IFluidVisualConnectable)
			return ((IFluidVisualConnectable)block).shouldConnect(type);
		return false;
	}
}
