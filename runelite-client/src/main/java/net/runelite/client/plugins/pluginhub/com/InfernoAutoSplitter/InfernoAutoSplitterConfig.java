package net.runelite.client.plugins.pluginhub.com.InfernoAutoSplitter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("autosplitter")
public interface InfernoAutoSplitterConfig extends Config {

    @ConfigItem(position = 2, keyName = "port", name = "Port", description = "Port for the LiveSplit server. (Restart required)")
    default int port() {
        return 16834;
    }

}

/*
InfernoAutoSplitterPlugin
Connects to LiveSplit Server and automatically does the splits for the Inferno
Created by Molgoatkirby and Naabe
Credit to SkyBouncer's CM AutoSplitter, the code for the panel and config comes largely from that
Initial date: 10/28/2021
 */
