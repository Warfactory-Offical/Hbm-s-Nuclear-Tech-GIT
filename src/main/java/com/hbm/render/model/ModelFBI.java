package com.hbm.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;

public class ModelFBI extends ModelBiped {

    public void render(final Entity p_78088_1_, final float p_78088_2_, final float p_78088_3_, final float p_78088_4_, final float p_78088_5_, final float p_78088_6_, final float p_78088_7_) {
    	this.rightArmPose = ArmPose.BOW_AND_ARROW;
    	super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
    }
}
