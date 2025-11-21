package net.runelite.client.plugins.pluginhub.com.bonkmessages;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("bonk messages")
public interface BonkMessagesConfig extends Config
{
	@ConfigItem(
		keyName = "messages",
		name = "Bonk Messages",
		description = "The message to display when NPC is hit. Can be a comma-deliminated list for random messages."
	)
	default String messages()
	{
		return "UwU";
	}

	@ConfigItem(
		keyName = "threshold",
		name = "Damage Threshold",
		description = "Minimum damage dealt to display message."
	)
	default int damageThreshold(){ return 0; }
}
