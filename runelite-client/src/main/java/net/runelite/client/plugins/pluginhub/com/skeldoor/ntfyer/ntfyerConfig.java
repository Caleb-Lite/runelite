package net.runelite.client.plugins.pluginhub.com.skeldoor.ntfyer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("ntfyer")
public interface ntfyerConfig extends Config
{

	@ConfigItem(
			keyName = "ntfyTopic",
			name = "ntfy Topic",
			description = "The ntfy topic to send messages to.",
			position = 0
	)
	String ntfyTopic();

	@ConfigItem(
			keyName = "ntfyUrl",
			name = "ntfy URL",
			description = "The ntfy url to send messages to, only change if you're self hosting ntfy.",
			position = 1

	)
	default String ntfyUrl(){
		return "https://ntfy.sh/";
	};
}

