package net.runelite.client.plugins.pluginhub.com.telegramnotifier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("telegramnotifier")
public interface TelegramNotifierConfig extends Config
{
	@ConfigItem(
			keyName = "keywords",
			name = "Keywords",
			description = "Comma-separated list of keywords to match in messages (e.g., 'superior, granite maul, abyssal whip')"
	)
	default String keywords() {
		return "superior, granite maul, abyssal whip, abyssal dagger, low prayer, goading potion";
	}

	@ConfigItem(
			keyName = "telegramBotToken",
			name = "Telegram Bot Token",
			description = "Your bot token from BotFather"
	)
	default String telegramBotToken()
	{
		return "";
	}

	@ConfigItem(
			keyName = "telegramChatId",
			name = "Telegram Chat ID",
			description = "Your Telegram chat ID"
	)
	default String telegramChatId()
	{
		return "";
	}

}
