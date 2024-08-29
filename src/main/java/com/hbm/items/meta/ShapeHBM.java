package com.hbm.items.meta;

public enum ShapeHBM implements IMetaItemShape {

    INGOT("ingot", "ingot", "");

    private final String a, b, c;

    ShapeHBM(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getResultingItemName() {
        return a;
    }

    @Override
    public String getOreDictionaryPrefix() {
        return b;
    }

    @Override
    public String getOreDictionarySuffix() {
        return c;
    }

}
