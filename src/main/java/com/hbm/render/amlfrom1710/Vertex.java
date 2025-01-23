package com.hbm.render.amlfrom1710;

public class Vertex
{
    public float x, y, z;

    public Vertex(final float x, final float y)
    {
        this(x, y, 0F);
    }

    public Vertex(final float x, final float y, final float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
