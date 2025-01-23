package com.hbm.render.util;

import java.util.ArrayList;

import com.hbm.render.amlfrom1710.Tessellator;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class HbmGroupObject {
	
    public String name;
    public ArrayList<HbmFace> faces = new ArrayList<HbmFace>();
    public int glDrawingMode;

    public HbmGroupObject()
    {
        this("");
    }

    public HbmGroupObject(final String name)
    {
        this(name, -1);
    }

    public HbmGroupObject(final String name, final int glDrawingMode)
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
            for (final HbmFace face : faces)
            {
                face.addFaceForRender(tessellator);
            }
        }
    }
}
