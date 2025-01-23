package com.hbm.render.util;

public class HmfController {
	
	public static double modoloMod = 100000D;
	public static double quotientMod = 5000D;
	
	public static void setMod(final double modolo, final double quotient) {
		modoloMod = modolo;
		quotientMod = quotient;
	}
	
	public static void resetMod() {
		modoloMod = 100000D;
		quotientMod = 5000D;
	}

}
