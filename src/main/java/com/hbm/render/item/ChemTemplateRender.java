package com.hbm.render.item;
import com.hbm.util.ItemStackUtil;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemChemistryTemplate;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class ChemTemplateRender extends TileEntityItemStackRenderer {

	public static final ChemTemplateRender INSTANCE = new ChemTemplateRender();
	public IBakedModel itemModel;
	public TransformType type;
	@Override
	public void renderByItem(final ItemStack stack) {
		if (stack.getItem() instanceof ItemChemistryTemplate && type == TransformType.GUI) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				GL11.glTranslated(0.5, 0.5, 0);
				final ItemStack renderStack = ItemStackUtil.itemStackFrom(ModItems.chemistry_icon, 1, stack.getItemDamage());
				Minecraft.getMinecraft().getRenderItem().renderItem(renderStack, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(renderStack, Minecraft.getMinecraft().world, Minecraft.getMinecraft().player));
			} else {
				GL11.glTranslated(0.5, 0.5, 0);
				Minecraft.getMinecraft().getRenderItem().renderItem(stack, itemModel);
			}
		} else {
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, itemModel);
		}
		super.renderByItem(stack);
	}
}
