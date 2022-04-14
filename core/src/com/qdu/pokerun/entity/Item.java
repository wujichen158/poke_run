package com.qdu.pokerun.entity;

/**
 * 可捡到的携带物品
 */
public class Item {
    //物品ID
    public String itemID;
    //物品元数据（损害值）
    public Integer meta;

    protected Item() {
    }

    public Item(String itemID) {
        this.itemID = itemID;
        this.meta = 0;
    }

    public Item(String itemID, Integer meta) {
        this.itemID = itemID;
        this.meta = meta;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }
}
