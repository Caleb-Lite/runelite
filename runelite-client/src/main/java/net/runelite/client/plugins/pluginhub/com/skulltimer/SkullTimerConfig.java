package net.runelite.client.plugins.pluginhub.com.skulltimer;

import java.awt.Color;
import java.time.Duration;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("Skull Timer")
public interface SkullTimerConfig extends Config
{
	@ConfigSection(
		position = 0,
		name = "General Settings",
		description = "Standard timer configuration settings."
	)
	String settings = "settings";

	@ConfigItem(
		keyName = "textColour",
		name="Text Colour",
		description = "The colour of the countdown text displayed on the timer.",
		section = settings
	)
	default Color textColour() {return Color.WHITE;}

	@ConfigItem(
		keyName = "warningTextColour",
		name="Warning Text Colour",
		description = "The colour of the countdown text displayed on the timer when 30 seconds or less is left on the timer.",
		section = settings
	)
	default Color warningTextColour() {return Color.RED;}

	@ConfigItem(
		keyName = "skullDuration",
		name = "",
		description = "",
		hidden = true
	)
	Duration skullDuration();

	@ConfigItem(
		keyName = "skullDuration",
		name = "",
		description = ""
	)
	void skullDuration(Duration skullDuration);

	@ConfigSection(
		position = 1,
		name = "PVP Settings",
		description = "Settings that affect PVP timers only."
	)
	String experimental = "experimental";

	@ConfigItem(
		keyName = "pvpToggle",
		name="Enable PVP timer",
		description = "Toggles the plugin to track PVP skulls. Please note, this timer may not always be 100% accurate.",
		section = experimental
	)
	default boolean pvpToggle() {return true;}
}