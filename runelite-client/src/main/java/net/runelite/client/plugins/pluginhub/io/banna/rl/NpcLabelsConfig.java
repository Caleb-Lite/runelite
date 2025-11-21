package net.runelite.client.plugins.pluginhub.io.banna.rl;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import static io.banna.rl.NpcLabelsConfig.CONFIG_GROUP;

@ConfigGroup(CONFIG_GROUP)
public interface NpcLabelsConfig extends Config
{

	String CONFIG_GROUP = "npc-labels";

	@ConfigItem(
		position = 1,
		keyName = "showOverlay",
		name = "Show Overlay",
		description = "Draw labels on NPCs"
	)
	default boolean showOverlay()
	{
		return true;
	}

	@ConfigItem(
		position = 2,
		keyName = "confirmClear",
		name = "Confirm Clear",
		description = "Confirm before clearing labels off an NPC"
	)
	default boolean confirmClear()
	{
		return true;
	}

	@ConfigItem(
		position = 3,
		keyName = "dropShadow",
		name = "Drop Shadow",
		description = "Draw a black drop shadow under each label"
	)
	default boolean dropShadow()
	{
		return true;
	}

	@ConfigItem(
			position = 4,
			keyName = "labelHeight",
			name = "Height of the text label",
			description = "Change the vertical offset of the textual label"
	)
	default int labelHeight() {
		return 30;
	}

	@ConfigItem(
			position = 4,
			keyName = "iconHeight",
			name = "Height of the item icon",
			description = "Change the vertical offset of the item icon"
	)
	default int iconHeight() {
		return 70;
	}
}
