package com.hbm.render.item;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BakedModelNoGui implements IBakedModel {

	private final TEISRBase renderer;
	
	public BakedModelNoGui(final TEISRBase renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public List<BakedQuad> getQuads(final IBlockState state, final EnumFacing side, final long rand) {
		return renderer.type != TransformType.GUI ? Collections.emptyList() : renderer.itemModel.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return renderer.type == TransformType.GUI && renderer.itemModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return renderer.type == TransformType.GUI && renderer.itemModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return renderer.type != TransformType.GUI || renderer.itemModel.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return renderer.itemModel.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return renderer.type != TransformType.GUI ? new ItemOverrideList(Collections.emptyList()){
			@Override
			public IBakedModel handleItemState(final IBakedModel originalModel, final ItemStack stack, final World world, final EntityLivingBase entity) {
				renderer.entity = entity;
				renderer.world = world;
				return super.handleItemState(originalModel, stack, world, entity);
			}
		} : renderer.itemModel.getOverrides();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(final TransformType cameraTransformType) {
		renderer.type = cameraTransformType;
		return Pair.of(this, renderer.itemModel.handlePerspective(cameraTransformType).getRight());
	}
}
