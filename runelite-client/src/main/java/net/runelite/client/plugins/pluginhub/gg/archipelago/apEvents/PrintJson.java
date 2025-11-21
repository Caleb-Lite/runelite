package net.runelite.client.plugins.pluginhub.gg.archipelago.apEvents;

import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.Print.APPrintPart;
import net.runelite.client.plugins.pluginhub.dev.koifysh.archipelago.events.PrintJSONEvent;
import net.runelite.client.plugins.pluginhub.gg.archipelago.ArchipelagoPlugin;
import net.runelite.client.eventbus.Subscribe;

public class PrintJson {

    @Subscribe
    public void onPrintJSONEvent(PrintJSONEvent event) {
        if ("Join".equals(event.apPrint.type) || "Tutorial".equals(event.apPrint.type)) return;
        StringBuilder msgBuilder = new StringBuilder();
        for (APPrintPart part : event.apPrint.parts){
            msgBuilder.append(part.text);
        }
        ArchipelagoPlugin.plugin.DisplayChatMessage(msgBuilder.toString());
    }
}
