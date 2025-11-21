package net.runelite.client.plugins.pluginhub.com.picklechicken;

import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuOpened;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
        name = "Pickle",
        description = "The one with the lady on it?",
        tags = {"pickle", "chicken", "joke"},
        enabledByDefault = true
)
public class PickleChickenPlugin extends Plugin  {
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private Client client;

    @Inject
    private PickleChickenOverlay overlay;

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(overlay);
    }

    @Subscribe
    public void onMenuOpened(MenuOpened event)
    {
        MenuEntry[] menuItems = client.getMenuEntries();
        for(MenuEntry menuItem : menuItems) {
            String target = menuItem.getTarget();
            if(target.contains(("Chicken"))) {
                menuItem.setTarget(target.replace("Chicken", "Pickle"));
            }
            else if(target.contains(("chicken"))) {
                menuItem.setTarget(target.replace("chicken", "pickle"));
            }
        }
    }
}

