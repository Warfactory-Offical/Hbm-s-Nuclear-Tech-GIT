package com.hbm.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineDrain;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderDrain extends TileEntitySpecialRenderer<TileEntityMachineDrain> {

    @Override
    public void render(TileEntityMachineDrain te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_CULL_FACE);

        switch(te.getBlockMetadata() - BlockDummyable.offset) {
            case 4: GL11.glRotatef(180, 0F, 1F, 0F); break;
            case 3: GL11.glRotatef(270, 0F, 1F, 0F); break;
            case 5: GL11.glRotatef(0, 0F, 1F, 0F); break;
            case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
        }

        bindTexture(ResourceManager.drain_tex);
        ResourceManager.drain.renderAll();

        GL11.glPopMatrix();

    }
}
