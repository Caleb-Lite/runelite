package net.runelite.client.plugins.pluginhub.com.coxdrink;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("CoX Drink")
public interface CoXDrinkConfig extends Config
{
	@ConfigItem(
		keyName = "message",
		name = "Drinking Message",
		description = "The message to show above a player's head when they drink from an energy pool"
	)
	default String message()
	{
		return "Shluuuurp!";
	}
}
