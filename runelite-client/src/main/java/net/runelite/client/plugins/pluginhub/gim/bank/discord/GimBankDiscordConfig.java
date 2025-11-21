package net.runelite.client.plugins.pluginhub.gim.bank.discord;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("gimbankdiscord")
public interface GimBankDiscordConfig extends Config
{
	@ConfigItem(
			keyName = "webhook",
			name = "Discord Webhook",
			description = "The webhook used to send messages to Discord."
	)
	default String webhook()
	{
		return "";
	}
}
