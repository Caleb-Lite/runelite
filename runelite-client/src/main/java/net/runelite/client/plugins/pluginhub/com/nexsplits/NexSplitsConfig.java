package net.runelite.client.plugins.pluginhub.com.nexsplits;

import net.runelite.client.plugins.pluginhub.com.nexsplits.config.BackgroundMode;
import net.runelite.client.plugins.pluginhub.com.nexsplits.config.CoughMode;
import net.runelite.client.plugins.pluginhub.com.nexsplits.config.CustomOverlayInfo;
import net.runelite.client.plugins.pluginhub.com.nexsplits.config.FontType;
import net.runelite.client.plugins.pluginhub.com.nexsplits.config.FontWeight;
import net.runelite.client.plugins.pluginhub.com.nexsplits.config.KillTimerMode;
import net.runelite.client.plugins.pluginhub.com.nexsplits.config.PhaseNameTypeMode;
import net.runelite.client.plugins.pluginhub.com.nexsplits.config.TimeStyle;
import java.util.Collections;
import java.util.Set;

import java.awt.*;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup(NexSplitsConfig.GROUP)
public interface NexSplitsConfig extends Config
{
	String GROUP = "nexSplits";

	@ConfigSection(
		name = "Timer",
		description = "Configuration for Kill/Phase Timers",
		position = 0,
		closedByDefault = true
	)
	String timerSection = "Timer";

	@ConfigSection(
		name = "Font",
		description = "Configuration for Kill/Phase Timer Font",
		position = 1,
		closedByDefault = true
	)
	String fontSection = "Font";

	//Timer Section
	@ConfigItem(
		keyName = "timerStyle",
		name = "Timer Style",
		description = "Changes how the time is displayed",
		position = 0,
		section = timerSection
	)
	default TimeStyle timerStyle()
	{
		return TimeStyle.VARBIT;
	}

	@ConfigItem(
		keyName = "killTimer",
		name = "Kill Timer",
		description = "Display either an infobox or panel with kill/phase times",
		position = 1,
		section = timerSection
	)
	default KillTimerMode killTimer()
	{
		return KillTimerMode.OFF;
	}

	@ConfigItem(
		keyName = "overlayInfo",
		name = "Display Options",
		description = "Options that can be displayed in the custom overlay",
		position = 2,
		section = timerSection
	)
	default Set<CustomOverlayInfo> overlayInfo()
	{
		return Collections.emptySet();
	}

	@ConfigItem(
		keyName = "phaseNameType",
		name = "Phase Name Type",
		description = "Display phases in timers and messages as either numbers(P1, P2, P3) or name(Smoke, shadow, blood)",
		position = 3,
		section = timerSection
	)
	default PhaseNameTypeMode phaseNameType()
	{
		return PhaseNameTypeMode.NUMBER;
	}

	@ConfigItem(
		keyName = "phaseChatMessages",
		name = "Phase Chat Message",
		description = "Puts message in chatbox for each phase",
		position = 4,
		section = timerSection
	)
	default boolean phaseChatMessages()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showMinionSplit",
		name = "Show Minion Split",
		description = "Shows boss and minion times for each phase",
		position = 5,
		section = timerSection
	)
	default boolean showMinionSplit()
	{
		return false;
	}

	@ConfigItem(
		name = "Time Exporter",
		keyName = "timeExporter",
		description = "Exports Nex times to .txt files in .runelite/nex-splits",
		position = 6,
		section = timerSection
	)
	default boolean timeExporter()
	{
		return false;
	}

	//Font Section
	@ConfigItem(
		name = "Font Type",
		keyName = "fontType",
		description = "",
		position = 0,
		section = fontSection
	)
	default FontType fontType()
	{
		return FontType.REGULAR;
	}

	@ConfigItem(
		name = "Custom Font Name",
		keyName = "fontName",
		description = "Custom font override",
		position = 1,
		section = fontSection
	)
	default String fontName()
	{
		return "";
	}

	@ConfigItem(
		name = "Custom Font Size",
		keyName = "fontsSize",
		description = "",
		position = 2,
		section = fontSection
	)
	default int fontSize()
	{
		return 11;
	}

	@ConfigItem(
		name = "Custom Weight",
		keyName = "fontWeight",
		description = "Sets the custom font weight",
		position = 3,
		section = fontSection
	)
	default FontWeight fontWeight()
	{
		return FontWeight.PLAIN;
	}

	@ConfigItem(
		name = "Background Style",
		keyName = "backgroundStyle",
		description = "Sets the background to the style you select",
		position = 4,
		section = fontSection
	)
	default BackgroundMode backgroundStyle()
	{
		return BackgroundMode.STANDARD;
	}

	@Alpha
	@ConfigItem(
		name = "Background Color",
		keyName = "backgroundColor",
		description = "Sets the overlay color on the custom setting",
		position = 5,
		section = fontSection
	)
	default Color backgroundColor()
	{
		return new Color(23, 23, 23, 156);
	}

	//Misc Section
	@ConfigItem(
		keyName = "replaceCough",
		name = "Replace Cough",
		description = "Replaces *Cough* during smoke phase",
		position = 96
	)
	default CoughMode replaceCough()
	{
		return CoughMode.OFF;
	}

	@Range(min = 0, max = 255)
	@ConfigItem(
		keyName = "nexDimmer",
		name = "Nex Region Dimmer",
		description = "Saves your eyes while hard grinding. Does nothing if 0",
		position = 97
	)
	default int nexDimmer()
	{
		return 0;
	}
}
