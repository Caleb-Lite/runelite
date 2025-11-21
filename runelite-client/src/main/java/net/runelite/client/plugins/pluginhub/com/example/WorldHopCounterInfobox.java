package net.runelite.client.plugins.pluginhub.com.example;

import net.runelite.api.SpriteID;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import java.awt.*;

public class WorldHopCounterInfobox extends InfoBox
{
    WorldhopCounterPlugin myPlugin;

    WorldHopCounterInfobox(WorldhopCounterPlugin aPlugin)
    {
        super(aPlugin.mySpriteManager.getSprite(SpriteID.TAB_LOGOUT, 0), aPlugin);
        myPlugin = aPlugin;

        setTooltip("World-hops done");
    }


    @Override
    public String getText() {
        return myPlugin.myAmountOfHops.toString();
    }

    @Override
    public Color getTextColor() {
        return Color.white;
    }
}
