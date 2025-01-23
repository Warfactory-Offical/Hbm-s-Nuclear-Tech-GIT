package com.hbm.render.amlfrom1710;
import java.util.ArrayList;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GroupObject
{
    public String name;
    public ArrayList<Face> faces = new ArrayList<Face>();
    public int glDrawingMode;

    public GroupObject()
    {
        this("");
    }

    public GroupObject(final String name)
    {
        this(name, -1);
    }

    public GroupObject(final String name, final int glDrawingMode)
    {
        this.name = name;
        this.glDrawingMode = glDrawingMode;
    }

    @SideOnly(Side.CLIENT)
    public void render()
    {
        if (faces.size() > 0)
        {
        	
            final Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawing(glDrawingMode);
            render(tessellator);
            tessellator.draw();
        }
    }

    @SideOnly(Side.CLIENT)
    public void render(final Tessellator tessellator)
    {
        if (faces.size() > 0)
        {
            for (final Face face : faces)
            {
                face.addFaceForRender(tessellator);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderSplit(final float splitHeight, final float scale)
    {
        if (faces.size() > 0)
        {
            
            final Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawing(glDrawingMode);
            renderSplit(tessellator, splitHeight, scale);
            tessellator.draw();
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderSplit(final Tessellator tessellator, final float splitHeight, final float scale)
    {
        if (faces.size() > 0)
        {
            for (final Face face : faces)
            {
                face.addFaceForRenderSplit(tessellator, 0F, splitHeight, scale);
            }
        }
    }
}