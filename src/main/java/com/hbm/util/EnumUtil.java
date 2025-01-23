package com.hbm.util;

public class EnumUtil {
    public static <T extends Enum> T grabEnumSafely(final Class<? extends Enum> theEnum, int index) {
        final Enum[] values = theEnum.getEnumConstants();
        index = Math.abs(index % values.length);
        return (T)values[index];
    }
}
