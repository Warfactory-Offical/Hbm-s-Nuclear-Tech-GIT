package com.hbm.items.meta.shapes;

import com.hbm.items.meta.IMetaItemShape;

public enum ShapePart implements IMetaItemShape {

    INGOT("ingot", "ingot", ""),
    BILLET("billet", "billet", ""),
    NUGGET("nugget", "nugget", ""),
    ;

    private final String a, b, c;

    ShapePart(final String a, final String b, final String c) {
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
