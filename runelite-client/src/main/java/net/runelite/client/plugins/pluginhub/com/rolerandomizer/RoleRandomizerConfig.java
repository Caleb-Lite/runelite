package net.runelite.client.plugins.pluginhub.com.rolerandomizer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("BA Role Randomizer")
public interface RoleRandomizerConfig extends Config
{
    @ConfigItem(
            keyName = "randomizeResultChatbox",
            name = "Print result to in-game chatbox",
            description = "Sends the result of the random from the UI to the in-game chatbox"
    )
    default boolean sendToChatBox()
    {
        return false;
    }

    @ConfigItem(
            keyName = "addToRando",
            name = "Add to rando",
            description = "Add option on chat messages to add to rando",
            position = 2
    )
    default boolean addToRando()
    {
        return false;
    }



}
