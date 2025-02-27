package com.hbm.render.factories;

import com.hbm.entity.particle.EntityModFX;
import com.hbm.render.entity.MultiCloudRenderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class MultiCloudRendererFactory implements IRenderFactory<EntityModFX> {

	private final Item[] textureItems;
	private final int meta;
	
	public MultiCloudRendererFactory(final Item[] items) {
		textureItems = items;
		this.meta = 0;
	}
	
	public MultiCloudRendererFactory(final Item[] items, final int meta) {
		textureItems = items;
		this.meta = meta;
	}
	
	@Override
	public Render<EntityModFX> createRenderFor(final RenderManager manager) {
		return new MultiCloudRenderer(textureItems, meta, manager);
	}

}
