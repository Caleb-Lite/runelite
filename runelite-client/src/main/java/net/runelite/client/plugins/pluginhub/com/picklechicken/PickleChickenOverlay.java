package net.runelite.client.plugins.pluginhub.com.picklechicken;

import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;

import javax.inject.Inject;
import java.awt.*;

public class PickleChickenOverlay extends Overlay {
    @Inject
    Client client;

    @Inject
    TooltipManager tooltipManager;

    @Inject
    PickleChickenOverlay(Client client, TooltipManager tooltipManager)
    {
        setPosition(OverlayPosition.DYNAMIC);
        // additionally allow tooltips above the full screen world map and welcome screen
        drawAfterInterface(InterfaceID.FULLSCREEN_CONTAINER_TLI);
        this.client = client;
        this.tooltipManager = tooltipManager;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        MenuEntry[] menuEntries = client.getMenuEntries();
        int last = menuEntries.length - 1;

        if (last < 0)
        {
            return null;
        }

        MenuEntry menuEntry = menuEntries[last];
        String target = menuEntry.getTarget();
        if(target.contains(("Chicken"))) {
            menuEntry.setTarget(target.replace("Chicken", "Pickle"));
        }
        else if(target.contains(("chicken"))) {
            menuEntry.setTarget(target.replace("chicken", "pickle"));
        }
        return null;
    }
}
