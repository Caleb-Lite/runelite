package net.runelite.client.plugins.pluginhub.com.SelfHealingNotifications;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("SelfHealingNotifications")
public interface SelfHealingNotificationsConfig extends Config
{
	enum SendToChar
	{
		Default,
		Simple,
		Off
	}
	@ConfigItem(
			keyName = "ignoreRegen",
			name = "Threshold",
			description = "Ignores all healing that is under submitted value"
	)
	default int ignoreRegen()
	{
		return 1;
	}

	@ConfigItem(
			keyName = "sendToChar",
			name = "Display",
			description = "Display healing above character"
	)
	default SendToChar sendToChar()
	{
		return SendToChar.Default;
	}

}

