package com.hbm.render.amlfrom1710;

public class TextureCoordinate
{
    public float u, v, w;

    public TextureCoordinate(final float u, final float v)
    {
        this(u, v, 0F);
    }

    public TextureCoordinate(final float u, final float v, final float w)
    {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
