package com.hbm.render.misc;

public enum EnumSymbol {
	NONE(0, 0),
	RADIATION(195, 2),
	NOWATER(195, 63),
	ACID(195, 124),
	ASPHYXIANT(195, 185),
	CROYGENIC(134, 185),
	ANTIMATTER(73, 185),
	OXIDIZER(12, 185);
	
	public int x;
	public int y;
	
	private EnumSymbol(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
}