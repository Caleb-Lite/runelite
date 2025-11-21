package net.runelite.client.plugins.pluginhub.com.BetterGodwarsOverlay;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup(BetterGodwarsOverlayPlugin.CONFIG_GROUP)
public interface BetterGodwarsOverlayConfig extends Config {

	@ConfigSection(
			name = "General",
			description = "General plugin features can be modified here",
			position = 0
	)
	String generalSection = "general";

	@ConfigSection(
			name = "Customization",
			description = "Overlay customizations",
			position = 1
	)
	String customizationSection = "customization";

	@ConfigSection(
			name = "Hide Gods",
			description = "Toggle options to prevent specific god killcounts appearing on the overlay",
			position = 2
	)
	String hideSection = "hide";

	@ConfigItem(
			keyName = "showOverlay",
			name = "Show Overlay",
			description = "Show kill count overlay",
			section = generalSection
	)
	default boolean showOverlay() {
		return true;
	}

	@ConfigItem(
			keyName = "showInfoBoxes",
			name = "Show Info Boxes",
			description = "Show KC in info boxes",
			section = generalSection
	)
	default boolean showInfoBox() {
		return false;
	}

	@ConfigItem(
			keyName = "ShortGodNames",
			name = "Shorten God Names",
			description = "Shorten god names on the killcount overlay",
			section = customizationSection
	)
	default boolean shortGodNames() {
		return false;
	}

	@ConfigItem(
			keyName = "godNameColour",
			name = "God Name Colour",
			description = "Change the colour of the god names displayed on the overlay",
			section = customizationSection
	)
	default Color godNameColor() {
		return Color.ORANGE;
	}

	@ConfigItem(keyName = "highlightOnKCColour",
			name = "Kill Count Highlight Colour",
			description = "Change the colour of the kill count highlight displayed on the overlay",
			section = customizationSection)
	default Color highlightOnKCColor() {
		return Color.GREEN;
	}

	@ConfigItem(
			keyName = "showZeroKc",
			name = "Show Zero Kill Count",
			description = "Show info boxes with zero kc",
			section = generalSection
	)
	default boolean showZeroKc() {
		return false;
	}

	@ConfigItem(
			keyName = "hideArmadyl",
			name = "Hide Armadyl",
			description = "Hide Armadyl killcount from the overlay",
			section = hideSection,
			position = 1
	)
	default boolean hideArmadyl() {
		return false;
	}

	@ConfigItem(
			keyName = "hideBandos",
			name = "Hide Bandos",
			description = "Hide Bandos killcount from the overlay",
			section = hideSection,
			position = 2
	)

	default boolean hideBandos() {
		return false;
	}

	@ConfigItem(
			keyName = "hideSaradomin",
			name = "Hide Saradomin",
			description = "Hide Saradomin killcount from the overlay",
			section = hideSection,
			position = 3
	)

	default boolean hideSaradomin() {
		return false;
	}

	@ConfigItem(
			keyName = "hideZamorak",
			name = "Hide Zamorak",
			description = "Hide Zamorak killcount from the overlay",
			section = hideSection,
			position = 4
	)

	default boolean hideZamorak() {
		return false;
	}

	@ConfigItem(
			keyName = "hideAncient",
			name = "Hide Ancient",
			description = "Hide Ancient killcount from the overlay",
			section = hideSection,
			position = 5
	)
	default boolean hideAncient() {
		return false;
	}
}
