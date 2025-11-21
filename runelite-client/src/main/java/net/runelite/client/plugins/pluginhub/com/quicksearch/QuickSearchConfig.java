package net.runelite.client.plugins.pluginhub.com.quicksearch;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("quicksearch")
public interface QuickSearchConfig extends Config {
	@ConfigSection(
			name = "General",
			description = "General settings",
			position = 0
	)
	String generalSection = "generalSection";

	@ConfigSection(
			name = "Platforms",
			description = "Platform-specific settings",
			position = 1
	)
	String platformsSection = "platformsSection";

	@ConfigItem(
			keyName = "suppressDisabled",
			name = "Suppress disabled message",
			description = "Hides the message shown when trying to use a disabled platform",
			position = 0,
			section = generalSection
	)
	default boolean suppressDisabled() {
		return false;
	}

	@ConfigItem(
			keyName = "enableWiki",
			name = "Enable Wiki Search",
			description = "Enables OSRS Wiki search (::w or ::osrs)",
			position = 0,
			section = platformsSection
	)
	default boolean enableWiki() {
		return true;
	}

	@ConfigItem(
			keyName = "enableGoogle",
			name = "Enable Google Search",
			description = "Enables Google search (::g or ::google)",
			position = 1,
			section = platformsSection
	)
	default boolean enableGoogle() {
		return true;
	}

	@ConfigItem(
			keyName = "enableYoutube",
			name = "Enable YouTube Search",
			description = "Enables YouTube search (::yt or ::youtube)",
			position = 2,
			section = platformsSection
	)
	default boolean enableYoutube() {
		return true;
	}

	@ConfigItem(
			keyName = "enableKick",
			name = "Enable Kick Search",
			description = "Enables Kick search (::kick)",
			position = 3,
			section = platformsSection
	)
	default boolean enableKick() {
		return true;
	}

	@ConfigItem(
			keyName = "enableTwitch",
			name = "Enable Twitch Search",
			description = "Enables Twitch search (::ttv or ::twitch)",
			position = 4,
			section = platformsSection
	)
	default boolean enableTwitch() {
		return true;
	}
}
