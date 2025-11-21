package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.comborunes;

import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;

@Singleton
public class UpdateNotifier
{

	private static final int TARGET_VERSION = 1;

	@Inject
	private Client client;

	@Inject
	private ComboRunesOnlyConfig config;

	@Inject
	private ConfigManager configManager;

	public void checkAndNotify()
	{
		switch (config.updateVersion())
		{
			case TARGET_VERSION:
				return;

			case 0:
				sendMessage("<col=ff0000>Missing Craft-rune?</col> " +
					"ComboRunesOnly now removes Craft-rune options from more altars " +
					"and can de-prioritize options instead of fully removing them. " +
					"Check the plugin configuration to customize.");
		}

		configManager.setConfiguration(
			ComboRunesOnlyConfig.CONFIG_GROUP,
			ComboRunesOnlyConfig.UPDATE_VERSION_KEY_NAME,
			TARGET_VERSION
		);
	}

	private void sendMessage(String message)
	{
		client.addChatMessage(
			ChatMessageType.GAMEMESSAGE,
			"",
			message,
			null
		);
	}

}