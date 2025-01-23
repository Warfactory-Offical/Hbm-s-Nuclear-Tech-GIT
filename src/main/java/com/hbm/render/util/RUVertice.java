package com.hbm.render.util;

public class RUVertice {
	
	public float x;
	public float y;
	public float z;

	public RUVertice(final float X, final float Y, final float Z) {
		x = X;
		y = Y;
		z = Z;
	}

	public RUVertice normalize() {
		final float l = (float) Math.sqrt(x * x + y * y + z * z);
		x /= l;
		y /= l;
		z /= l;
		return this;
	}
}
