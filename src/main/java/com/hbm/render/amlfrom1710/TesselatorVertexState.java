package com.hbm.render.amlfrom1710;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TesselatorVertexState
{
    private final int[] rawBuffer;
    private final int rawBufferIndex;
    private final int vertexCount;
    private final boolean hasTexture;
    private final boolean hasBrightness;
    private final boolean hasNormals;
    private final boolean hasColor;
    private static final String __OBFID = "CL_00000961";

    public TesselatorVertexState(final int[] p_i45079_1_, final int p_i45079_2_, final int p_i45079_3_, final boolean p_i45079_4_, final boolean p_i45079_5_, final boolean p_i45079_6_, final boolean p_i45079_7_)
    {
        this.rawBuffer = p_i45079_1_;
        this.rawBufferIndex = p_i45079_2_;
        this.vertexCount = p_i45079_3_;
        this.hasTexture = p_i45079_4_;
        this.hasBrightness = p_i45079_5_;
        this.hasNormals = p_i45079_6_;
        this.hasColor = p_i45079_7_;
    }

    public int[] getRawBuffer()
    {
        return this.rawBuffer;
    }

    public int getRawBufferIndex()
    {
        return this.rawBufferIndex;
    }

    public int getVertexCount()
    {
        return this.vertexCount;
    }

    public boolean getHasTexture()
    {
        return this.hasTexture;
    }

    public boolean getHasBrightness()
    {
        return this.hasBrightness;
    }

    public boolean getHasNormals()
    {
        return this.hasNormals;
    }

    public boolean getHasColor()
    {
        return this.hasColor;
    }
}