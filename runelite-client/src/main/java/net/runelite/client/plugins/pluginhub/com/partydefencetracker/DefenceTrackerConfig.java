package net.runelite.client.plugins.pluginhub.com.partydefencetracker;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("defencetracker")
public interface DefenceTrackerConfig extends Config
{
	@ConfigItem(
			name = "Low Defence Threshold",
			keyName = "lowDef",
			description = "Sets when you want the defence to appear as low defence",
			position = 1
	)
	default int lowDef()
	{
		return 10;
	}

	@ConfigItem(
		name = "High Defence Color",
		keyName = "highDefColor",
		description = "Color of the infobox text when the defence is above the low defence threshold",
		position = 2
	)
	default Color highDefColor()
	{
		return Color.WHITE;
	}

	@ConfigItem(
		name = "Low Defence Color",
		keyName = "lowDefColor",
		description = "Color of the infobox text when the defence is beneath the low defence threshold",
		position = 3
	)
	default Color lowDefColor()
	{
		return Color.YELLOW;
	}

	@ConfigItem(
		name = "Capped Defence Color",
		keyName = "cappedDefColor",
		description = "Color of the infobox text when the defence is at the lowest possible amount for that NPC",
		position = 4
	)
	default Color cappedDefColor()
	{
		return Color.GREEN;
	}

	@ConfigItem(
		keyName = "disableIBColor",
		name = "Disable Infobox Text Color",
		description = "Defence infobox text will always be set to White",
		position = 5
	)
	default boolean disableIBColor()
	{
		return false;
	}

	@ConfigItem(
			keyName = "vulnerability",
			name = "Show Vulnerability",
			description = "Displays an infobox when you successfully land vulnerability",
			position = 6
	)
	default boolean vulnerability()
	{
		return true;
	}

	@ConfigItem(
			keyName = "shadowBarrage",
			name = "Show Shadow Barrage",
			description = "Displays an infobox when you successfully land a shadow barrage whilst wearing the shadow ancient sceptre",
			position = 7
	)
	default boolean shadowBarrage() { return true; }

	@ConfigItem(
		keyName = "redKeris",
		name = "Show Red Keris",
		description = "Displays an infobox when you successfully land a Red Keris (Corruption) special attack",
		position = 8
	)
	default boolean redKeris()
	{
		return true;
	}
}
