package com.qdu.pokerun.actor.tool;

import com.qdu.pokerun.actor.tool.element.CleanToolElement;
import com.qdu.pokerun.actor.tool.element.DragToolElement;
import com.qdu.pokerun.actor.tool.element.FeedToolElement;

public class ToolElementList {
    static public DragToolElement[] CleanToolList = new DragToolElement[] {
            new CleanToolElement("blower", "exp", 500),
            new CleanToolElement("brush", "friendship", 5),
            new CleanToolElement("comb", "exp", 500),
            new CleanToolElement("drug", "friendship", 5),
            new CleanToolElement("towel", "exp", 500),
    };
    static public DragToolElement[] FeedToolList = new DragToolElement[] {
            new FeedToolElement("apriblender_bigger", "friendship", 5),
            new FeedToolElement("lumiose_galette", "exp", 500),
            new FeedToolElement("poffin", "friendship", 5),
            new FeedToolElement("poke_bean", "exp", 500),
            new FeedToolElement("poke_puff", "friendship", 5),
            new FeedToolElement("porygon_candy", "exp", 500),
    };
}
