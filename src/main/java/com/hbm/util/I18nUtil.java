package com.hbm.util;

import net.minecraft.client.resources.I18n;

public class I18nUtil {

	public static String resolveKey(final String s, final Object... args) {
		return I18n.format(s, args);
	}

	public static String[] resolveKeyArray(final String s, final Object... args) {
		return resolveKey(s, args).split("\\$");
	}
}
