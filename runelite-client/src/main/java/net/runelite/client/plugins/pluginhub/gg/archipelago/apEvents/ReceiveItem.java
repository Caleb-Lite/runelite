package net.runelite.client.plugins.pluginhub.gg.archipelago.apEvents;

import net.runelite.client.plugins.pluginhub.gg.archipelago.ArchipelagoPlugin;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.events.ReceiveItemEvent;
import net.runelite.client.eventbus.Subscribe;

public class ReceiveItem {

    @Subscribe
    public void onReceiveItemEvent(ReceiveItemEvent event) {
        ArchipelagoPlugin.plugin.ReceiveItem(event);
    }
}