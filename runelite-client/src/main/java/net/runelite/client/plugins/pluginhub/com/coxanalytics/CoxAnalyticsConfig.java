package net.runelite.client.plugins.pluginhub.com.coxanalytics;

import net.runelite.client.plugins.pluginhub.com.coxanalytics.config.BackgroundMode;
import net.runelite.client.plugins.pluginhub.com.coxanalytics.config.FontType;
import net.runelite.client.plugins.pluginhub.com.coxanalytics.config.FontWeight;
import net.runelite.client.plugins.pluginhub.com.coxanalytics.config.CustomOverlayInfo;
import net.runelite.client.plugins.pluginhub.com.coxanalytics.config.TimeStyle;
import java.awt.Color;
import java.util.Collections;
import java.util.Set;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("CoxAnalytics")
public interface CoxAnalyticsConfig extends Config
{
	@ConfigSection(
		name = "Timer",
		description = "Timer Options",
		position = 0
	)
	String timerSection = "timer";

	@ConfigSection(
		name = "Overlay",
		description = "Overlay Options",
		position = 1
	)
	String overlaySection = "overlay";

	@ConfigSection(
		name = "Points Panel",
		description = "Panel Options",
		position = 2
	)
	String panelSection = "panel";

	//Timer Section
	@ConfigItem(
		keyName = "replaceWidget",
		name = "Replace Widget",
		description = "Replaces the CoX point widget with a custom overlay",
		position = 0,
		section = timerSection
	)
	default boolean replaceWidget()
	{
		return true;
	}

	@ConfigItem(
		keyName = "timerStyle",
		name = "Timer Style",
		description = "Changes how the time is displayed",
		position = 1,
		section = timerSection
	)
	default TimeStyle timerStyle()
	{
		return TimeStyle.VARBIT;
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
		keyName = "splitsTooltip",
		name = "Splits Tooltip",
		description = "Displays the floor splits when hovering the overlay or widget",
		position = 3,
		section = timerSection
	)
	default boolean splitsTooltip()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showOlmMageHand",
		name = "Olm Mage Hand Message",
		description = "Prints a chat message displaying how long the mage hand took to kill <br> De0's CoX Timers must be installed and enabled",
		position = 4,
		section = timerSection
	)
	default boolean showOlmMageHand()
	{
		return true;
	}

	@ConfigItem(
		keyName = "ptsHr",
		name = "Points Per Hour Message",
		description = "Displays a pts/hr chat message after the raid has ended",
		position = 5,
		section = timerSection
	)
	default boolean ptsHr()
	{
		return true;
	}

	@ConfigItem(
		keyName = "exportTimes",
		name = "Export Times",
		description = "Exports times to a file in the 'cox-analytics' folder in .runelite",
		position = 6,
		section = timerSection
	)
	default boolean exportTimes()
	{
		return true;
	}

	//Font Section
	@ConfigItem(
		name = "Font Type",
		keyName = "fontType",
		description = "",
		position = 0,
		section = overlaySection
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
		section = overlaySection
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
		section = overlaySection
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
		section = overlaySection
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
		section = overlaySection
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
		section = overlaySection
	)
	default Color backgroundColor()
	{
		return new Color(23, 23, 23, 156);
	}

	//Points Panel Section
	@ConfigItem(
		name = "Enable Points Panel",
		keyName = "ptsPanel",
		description = "Enable the panel",
		position = 1,
		section = panelSection
	)
	default boolean ptsPanel()
	{
		return true;
	}

	@ConfigItem(
		name = "Panel Priority",
		keyName = "panelPriority",
		description = "Determines where the points panel is on the side",
		position = 2,
		section = panelSection
	)
	default int panelPriority()
	{
		return 9;
	}
}
