package net.runelite.client.plugins.pluginhub.com.betterdiscordlootlogger;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Keybind;

@ConfigGroup("betterdiscordlootlogger")
public interface BetterDiscordLootLoggerConfig extends Config
{
    @ConfigSection(
            name = "Choose what to send",
            description = "Choose from the options below which events you would like to send",
            position = 99
    )
    String whatToSendSection = "what to send";

    @ConfigItem(
            keyName = "sendScreenshot",
            name = "Send Screenshot?",
            description = "Include a screenshot in the discord message?",
            position = 3
    )
    default boolean sendScreenshot()
    {
        return true;
    }

    @ConfigItem(
            keyName = "keybind",
            name = "Screenshot Keybind",
            description = "Add keybind to manually take a screenshot and send a message of your rare drop",
            position = 4
    )
    default Keybind keybind()
    {
        return Keybind.NOT_SET;
    }

    @ConfigItem(
            keyName = "pets",
            name = "Include Pets",
            description = "Configures whether new pets will be automatically sent to discord",
            position = 5,
            section = whatToSendSection
    )
    default boolean includePets()
    {
        return true;
    }

    @ConfigItem(
            keyName = "valuableDrop",
            name = "Include Valuable drops",
            description = "Configures whether valuable drops will be automatically sent to discord.",
            position = 6,
            section = whatToSendSection
    )
    default boolean includeValuableDrops()
    {
        return false;
    }

    @ConfigItem(
            keyName = "valuableDropThreshold",
            name = "Valuable Drop Threshold",
            description = "The minimum value of drop for it to send a discord message.",
            position = 7,
            section = whatToSendSection
    )
    default int valuableDropThreshold()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "collectionLogItem",
            name = "Include collection log items",
            description = "Configures whether a message will be automatically sent to discord when you obtain a new collection log item.",
            position = 8,
            section = whatToSendSection
    )
    default boolean includeCollectionLogItems()
    {
        return true;
    }

	@ConfigItem(
		keyName = "raidLoot",
		name = "Include raid loot (Experimental)",
		description = "Configures whether a message will be automatically sent to discord when you obtain a raid unique.",
		position = 8,
		section = whatToSendSection
	)
	default boolean includeRaidLoot()
	{
		return true;
	}

    @ConfigItem(
            keyName = "webhook",
            name = "Discord Webhook",
            description = "The webhook used to send messages to Discord."
    )
    String webhook();
}

