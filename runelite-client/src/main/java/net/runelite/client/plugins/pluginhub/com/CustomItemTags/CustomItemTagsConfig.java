package net.runelite.client.plugins.pluginhub.com.CustomItemTags;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("Custom Item Tags")
public interface CustomItemTagsConfig extends Config
{
	@ConfigSection(name = "Instructions", description = "instructions", position = 0, closedByDefault = true)
	String customTagsInstructions = "Custom tags instructions";

	@ConfigItem(
			keyName = "customSwapperInstructions",
			name = "Click to reset instructions",
			description = "Options to swap to the top.",
			section = customTagsInstructions,
			position = 2
	)
	default String customTagsInstructions() {
		return "### Basic use:\nAdd tags, one per line. Format is \"text,item ID\". Item IDs can be found on https://www.osrsbox.com/tools/item-search/. \n";
	}

	@ConfigSection(name = "Custom Tags", description = "List custom item tags here", position = 1)
	String customTags = "Custom Tags";
	@ConfigItem(
			keyName = "customTags",
			name = "Custom tags",
			description = "Options for item text.",
			section = customTags,
			position = 0
	)
	default String customTags() {
		return "";
	}
}

