package com.qdu.pokerun.actor.item;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.actor.tool.element.CleanToolElement;
import com.qdu.pokerun.actor.tool.element.DragToolElement;
import com.qdu.pokerun.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class BuffItemList {

    public static Item[] getBuffItemList() {
        List<Item> list=new ArrayList<>();

        //学习装置
        Item item=new Item("exp_share");
        list.add(item);

        //幸运蛋
        item=new Item("lucky_egg");
        list.add(item);

        return list.toArray(new Item[list.size()]);
    }
}
