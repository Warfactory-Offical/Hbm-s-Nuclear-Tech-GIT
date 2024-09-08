package com.hbm.render.item;

import com.hbm.main.ResourceManager;
import com.hbm.render.anim.HbmAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class ItemRenderBoltgun extends TEISRBase {

    @Override
    public void renderByItem(@NotNull ItemStack _stack) {

        GlStateManager.enableCull();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.boltgun_tex);

        switch (type) {
            case FIRST_PERSON_LEFT_HAND:
            case FIRST_PERSON_RIGHT_HAND:
                if (type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                    GL11.glTranslated(0.3, 0.9, -0.3);
                    GL11.glRotated(205, 0, 0, 1);
                    GL11.glTranslated(-0.2, 1.1, 0.8);
                    GL11.glRotated(-25, 0, 0, 1);
                    GL11.glRotated(180, 1, 0, 0);
                } else {
                    GL11.glTranslated(0, 0, 0.9);
                    GL11.glRotated(0, 0, 0, 1);
                }

                double s0 = 0.25D;
                GL11.glRotated(25, 0, 0, 1);
                GL11.glTranslated(1.25, -0.25, -0.25);
                GL11.glRotated(180 - 95, 0, 1, 0);
                GL11.glScaled(s0, s0, s0);

                if (type == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                    GL11.glRotated(18, 0, 1, 0);
                    GL11.glRotated(-2, 0, 0, 1);
                }

                EnumHand hand = type == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;

                double[] recoil = HbmAnimations.getRelevantTransformation("RECOIL", hand);
                GL11.glRotated(recoil[0] * 5, 1, 0, 0);
                GL11.glTranslated(0, 0, recoil[0]);

                break;
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
            case GROUND:
            case FIXED:
            case HEAD:
                GL11.glScalef(0.3F, 0.3F, 0.3F);
                GL11.glTranslated(1.75, -0.5, 0.4);
                GL11.glRotated(180, 0, 1, 0);
                break;
            case GUI:
                GL11.glTranslated(0.4, 0.35, 0);
                GL11.glRotated(180 + 90, 0, 1, 0);
                GL11.glRotated(45, 1, 0, 0);
                GL11.glScaled(0.1, 0.1, 0.1);
                break;
            default:
                break;
        }

        ResourceManager.boltgun.renderAll();

    }

}
