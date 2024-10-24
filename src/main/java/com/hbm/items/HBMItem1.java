package com.hbm.items;

import api.hbm.item.base.HBMItemBase;
import api.hbm.item.base.StandardHBMItem;

import java.util.List;

public class HBMItem1 extends StandardHBMItem {
    private HBMItem1() {}

    public static final List<HBMItemBase<?>> ITEMS =    HBMItemBase.getItems();

    public static void init() {
        HBMItem1 ingredients = new HBMItem1();
        ingredients.setRegistryName("hbm_item_ingredients");

    }
}
