package net.runelite.client.plugins.pluginhub.autoretaliatewarning;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import java.awt.Color;

@ConfigGroup("autoretaliatewarning")
public interface AutoRetaliateWarningConfig extends Config
{
	@ConfigSection(
		name = "General Settings",
		description = "General plugin settings",
		position = 1
	)
	String generalSection = "generalSection";

	@ConfigSection(
		name = "NPC Attack Options",
		description = "Settings for NPC attack options warnings",
		position = 2
	)
	String npcAttackSection = "npcAttackSection";

	@ConfigSection(
		name = "Auto Retaliate On",
		description = "Settings for auto retaliate enabled warnings",
		position = 3
	)
	String autoRetaliateOnSection = "autoRetaliateOnSection";

	@ConfigSection(
		name = "Auto Retaliate Off",
		description = "Settings for auto retaliate disabled warnings",
		position = 4
	)
	String autoRetaliateOffSection = "autoRetaliateOffSection";

	// NPC Attack Options Section
	@ConfigItem(
		position = 1,
		keyName = "npcAttackOptionsWarn",
		name = "Enable Warning",
		description = "Warns you when NPC attack options aren't hidden",
		section = npcAttackSection
	)
	default boolean npcAttackOptionsWarn()
	{
		return true;
	}

	@ConfigItem(
		position = 2,
		keyName = "npcAttackOptionsText",
		name = "Warning Text",
		description = "Custom text to display when NPC attack options aren't hidden",
		section = npcAttackSection
	)
	default String npcAttackOptionsText()
	{
		return "NPC ATTACK OPTIONS NOT HIDDEN";
	}

	@ConfigItem(
		position = 3,
		keyName = "npcAttackOptionsColor",
		name = "Warning Color",
		description = "Color for the NPC attack options warning text",
		section = npcAttackSection
	)
	default Color npcAttackOptionsColor()
	{
		return Color.RED;
	}

	// Auto Retaliate On Section
	@ConfigItem(
		position = 1,
		keyName = "autoRetaliateWarn",
		name = "Enable Warning",
		description = "Warns you when you have auto retaliate enabled",
		section = autoRetaliateOnSection
	)
	default boolean autoRetaliateWarn()
	{
		return true;
	}

	@ConfigItem(
		position = 2,
		keyName = "autoRetaliateText",
		name = "Warning Text",
		description = "Custom text to display when auto retaliate is enabled",
		section = autoRetaliateOnSection
	)
	default String autoRetaliateText()
	{
		return "AUTO RETALIATE IS ON";
	}

	@ConfigItem(
		position = 3,
		keyName = "autoRetaliateColor",
		name = "Warning Color",
		description = "Color for the auto retaliate on warning text",
		section = autoRetaliateOnSection
	)
	default Color autoRetaliateColor()
	{
		return Color.RED;
	}

	// Auto Retaliate Off Section
	@ConfigItem(
		position = 1,
		keyName = "autoRetaliateOffWarn",
		name = "Enable Warning",
		description = "Warns you when you have auto retaliate disabled",
		section = autoRetaliateOffSection
	)
	default boolean autoRetaliateOffWarn()
	{
		return false;
	}

	@ConfigItem(
		position = 2,
		keyName = "autoRetaliateOffText",
		name = "Warning Text",
		description = "Custom text to display when auto retaliate is disabled",
		section = autoRetaliateOffSection
	)
	default String autoRetaliateOffText()
	{
		return "AUTO RETALIATE IS OFF";
	}

	@ConfigItem(
		position = 3,
		keyName = "autoRetaliateOffColor",
		name = "Warning Color",
		description = "Color for the auto retaliate off warning text",
		section = autoRetaliateOffSection
	)
	default Color autoRetaliateOffColor()
	{
		return Color.RED;
	}

	// General Settings Section
	@ConfigItem(
		position = 1,
		keyName = "hideRetaliateConfig",
		name = "Hide Auto Retaliate Button",
		description = "Hides the auto retaliate button",
		section = generalSection
	)
	default boolean hideRetaliateConfig()
	{
		return false;
	}

	@ConfigItem(
		position = 2,
		keyName = "fontSize",
		name = "Font Size",
		description = "Changes the warnings font size",
		section = generalSection
	)
	default int fontSize()
	{
		return 20;
	}
}