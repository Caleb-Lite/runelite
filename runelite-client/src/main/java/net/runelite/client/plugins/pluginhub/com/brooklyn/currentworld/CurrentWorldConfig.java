package net.runelite.client.plugins.pluginhub.com.brooklyn.currentworld;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.Color;

@ConfigGroup("currentworld")
public interface CurrentWorldConfig extends Config
{
	@ConfigSection(
		name = "Overlay Colors",
		description = "Overlay colors",
		position = 4
	)
	String colorSection = "colorSection";

	@ConfigItem(
		keyName = "showOverlay",
		name = "Overlay",
		description = "Enables the current world overlay",
		position = 1
	)
	default boolean showOverlay()
	{
		return true;
	}

	@ConfigItem(
			keyName = "overlayActivity",
			name = "Overlay Activity",
			description = "Adds world activity to the overlay, instead of<br>only warning for PvP and High Risk worlds.",
			position = 2
	)
	default boolean overlayActivity()
	{
		return false;
	}

	@ConfigItem(
		keyName = "worldSwitcherActivity",
		name = "World Switcher Activity",
		description = "Adds world activity to the Jagex world switcher<br>e.g., 'Sulliuscep cutting' or '2200 Skill total'",
		position = 3
	)
	default boolean worldSwitcherActivity()
	{
		return true;
	}

	@ConfigItem(
		keyName = "safeWorldColor",
		name = "Safe Worlds",
		description = "The color of the overlay for safe worlds",
		position = 1,
		section = colorSection
	)
	default Color safeWorldColor()
	{
		return Color.GREEN;
	}

	@ConfigItem(
		keyName = "highRiskWorldColor",
		name = "High Risk Worlds",
		description = "The color of the overlay for High Risk worlds",
		position = 2,
		section = colorSection
	)
	default Color highRiskWorldColor()
	{
		return Color.ORANGE;
	}

	@ConfigItem(
		keyName = "pvpWorldColor",
		name = "PVP Worlds",
		description = "The color of the overlay for PVP worlds",
		position = 3,
		section = colorSection
	)
	default Color pvpWorldColor()
	{
		return Color.RED;
	}
}

