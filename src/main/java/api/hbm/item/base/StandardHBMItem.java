package api.hbm.item.base;

import api.hbm.item.base.HBMItemBase.HBMItem;
public class StandardHBMItem extends HBMItemBase<HBMItemBase<?>.HBMItem> {
    public StandardHBMItem() {
        super((short) 0);
    }

    public StandardHBMItem(short offset) {
        super(offset);
    }

    @Override
    protected HBMItem createItem(short id, String unlocalizedName) {
        return new HBMItem(id, unlocalizedName);
    }
}
