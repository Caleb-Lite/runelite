package net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;


@ConfigGroup(GroundMarkerProfilesConfig.GROUP)
public interface GroundMarkerProfilesConfig extends Config {
	String GROUP = "groundMarkerProfile";

	// ------------------------------------------------------------
	@ConfigSection(
			name = "General Settings",
			position = 0,
			description = "Display settings for all tiles",
			closedByDefault = false
	)
	String groundMarkerGeneralSettings = "groundMarkerGeneralSettings";

	@ConfigItem(
			keyName = "borderWidth",
			name = "Border width",
			description = "Width of the marked tile border",
			section = groundMarkerGeneralSettings,
			position = 0
	)
	default double borderWidth() {
		return 2;
	}

	@ConfigItem(
			keyName = "drawOnMinimap",
			name = "Draw tiles on minimap",
			description = "Configures whether marked tiles should be drawn on minimap",
			section = groundMarkerGeneralSettings,
			position = 0
	)
	default boolean drawTilesOnMinimmap() {
		return false;
	}

	@ConfigItem(
			keyName = "fillOpacity",
			name = "Fill opacity",
			description = "Opacity of the tile fill color",
			section = groundMarkerGeneralSettings,
			position = 0
	)
	default int fillOpacity() {
		return 50;
	}

	// Group 0 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 1",
			position = 0,
			description = "Settings for the first group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile0 = "groundMarkerProfile0";

	@ConfigItem(
			keyName = "nameProfile0",
			name = "Marker Name 1",
			description = "Name for the first set of ground markers",
			section = groundMarkerProfile0,
			position = 0
	)
	default String nameProfile0() {
		return "CM 5 tg";
	}

	@ConfigItem(
			keyName = "tilesProfile0",
			name = "Marker Profile 1",
			description = "Ground markers associated with first group",
			section = groundMarkerProfile0,
			position = 0
	)
	default String tilesProfile0() {
		return "[{\"regionId\":13140,\"regionX\":46,\"regionY\":3,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":46,\"regionY\":7,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":48,\"regionY\":6,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":47,\"regionY\":8,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":49,\"regionY\":9,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"5\"},{\"regionId\":13140,\"regionX\":49,\"regionY\":13,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"6\"},{\"regionId\":13140,\"regionX\":53,\"regionY\":15,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"7\"},{\"regionId\":13140,\"regionX\":43,\"regionY\":3,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":41,\"regionY\":3,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":40,\"regionY\":5,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":41,\"regionY\":7,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":43,\"regionY\":7,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"5\"},{\"regionId\":13140,\"regionX\":45,\"regionY\":13,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"6\"},{\"regionId\":13140,\"regionX\":51,\"regionY\":7,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"7\"},{\"regionId\":13140,\"regionX\":52,\"regionY\":10,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"8\"},{\"regionId\":13140,\"regionX\":54,\"regionY\":10,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"drop\"},{\"regionId\":13140,\"regionX\":44,\"regionY\":2,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":43,\"regionY\":6,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":42,\"regionY\":9,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":44,\"regionY\":11,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":47,\"regionY\":13,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"5\"},{\"regionId\":13140,\"regionX\":48,\"regionY\":14,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"6\"},{\"regionId\":13140,\"regionX\":50,\"regionY\":16,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"7\"},{\"regionId\":13140,\"regionX\":52,\"regionY\":17,\"z\":0,\"color\":\"#FF0087FF\",\"label\":\"8\"},{\"regionId\":13140,\"regionX\":57,\"regionY\":12,\"z\":0,\"color\":\"#FF000000\",\"label\":\"drop\"},{\"regionId\":13140,\"regionX\":46,\"regionY\":5,\"z\":0,\"color\":\"#FF000000\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":45,\"regionY\":8,\"z\":0,\"color\":\"#FF000000\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":46,\"regionY\":9,\"z\":0,\"color\":\"#FF000000\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":48,\"regionY\":12,\"z\":0,\"color\":\"#FF000000\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":50,\"regionY\":11,\"z\":0,\"color\":\"#FF000000\",\"label\":\"5\"},{\"regionId\":13140,\"regionX\":52,\"regionY\":13,\"z\":0,\"color\":\"#FF000000\",\"label\":\"6\"},{\"regionId\":13140,\"regionX\":56,\"regionY\":13,\"z\":0,\"color\":\"#FF000000\",\"label\":\"7\"}]";
	}

	// Group 1 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 2",
			position = 1,
			description = "Settings for the second group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile1 = "groundMarkerProfile1";

	@ConfigItem(
			keyName = "nameProfile1",
			name = "Marker Name 2",
			description = "Name for the second set of ground markers",
			section = groundMarkerProfile1,
			position = 0
	)
	default String nameProfile1() {
		return "Profile 2";
	}

	@ConfigItem(
			keyName = "tilesProfile1",
			name = "Marker Profile 2",
			description = "Ground markers associated with second group",
			section = groundMarkerProfile1,
			position = 0
	)
	default String tilesProfile1() {
		return "";
	}

	// Group 2 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 3",
			position = 2,
			description = "Settings for the third group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile2 = "groundMarkerProfile2";

	@ConfigItem(
			keyName = "nameProfile2",
			name = "Marker Name 3",
			description = "Name for the third set of ground markers",
			section = groundMarkerProfile2,
			position = 0
	)
	default String nameProfile2() {
		return "Profile 3";
	}

	@ConfigItem(
			keyName = "tilesProfile2",
			name = "Marker Profile 3",
			description = "Ground markers associated with third group",
			section = groundMarkerProfile2,
			position = 0
	)
	default String tilesProfile2() {
		return "";
	}

	// Group 3 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 4",
			position = 3,
			description = "Settings for the fourth group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile3 = "groundMarkerProfile3";

	@ConfigItem(
			keyName = "nameProfile3",
			name = "Marker Name 4",
			description = "Name for the fourth set of ground markers",
			section = groundMarkerProfile3,
			position = 0
	)
	default String nameProfile3() {
		return "Profile 4";
	}

	@ConfigItem(
			keyName = "tilesProfile3",
			name = "Marker Profile 4",
			description = "Ground markers associated with fourth group",
			section = groundMarkerProfile3,
			position = 0
	)
	default String tilesProfile3() {
		return "";
	}

	// Group 4 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 5",
			position = 4,
			description = "Settings for the fifth group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile4 = "groundMarkerProfile4";

	@ConfigItem(
			keyName = "nameProfile4",
			name = "Marker Name 5",
			description = "Name for the fifth set of ground markers",
			section = groundMarkerProfile4,
			position = 0
	)
	default String nameProfile4() {
		return "Profile 5";
	}

	@ConfigItem(
			keyName = "tilesProfile4",
			name = "Marker Profile 5",
			description = "Ground markers associated with fifth group",
			section = groundMarkerProfile4,
			position = 0
	)
	default String tilesProfile4() {
		return "";
	}

	// Group 5 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 6",
			position = 5,
			description = "Settings for the sixth group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile5 = "groundMarkerProfile5";

	@ConfigItem(
			keyName = "nameProfile5",
			name = "Marker Name 6",
			description = "Name for the sixth set of ground markers",
			section = groundMarkerProfile5,
			position = 0
	)
	default String nameProfile5() {
		return "Profile 6";
	}

	@ConfigItem(
			keyName = "tilesProfile5",
			name = "Marker Profile 6",
			description = "Ground markers associated with sixth group",
			section = groundMarkerProfile5,
			position = 0
	)
	default String tilesProfile5() {
		return "";
	}

	// Group 6 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 7",
			position = 6,
			description = "Settings for the seventh group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile6 = "groundMarkerProfile6";

	@ConfigItem(
			keyName = "nameProfile6",
			name = "Marker Name 7",
			description = "Name for the seventh set of ground markers",
			section = groundMarkerProfile6,
			position = 0
	)
	default String nameProfile6() {
		return "Profile 7";
	}

	@ConfigItem(
			keyName = "tilesProfile6",
			name = "Marker Profile 7",
			description = "Ground markers associated with seventh group",
			section = groundMarkerProfile6,
			position = 0
	)
	default String tilesProfile6() {
		return "";
	}

	// Group 7 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 8",
			position = 7,
			description = "Settings for the eighth group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile7 = "groundMarkerProfile7";

	@ConfigItem(
			keyName = "nameProfile7",
			name = "Marker Name 8",
			description = "Name for the eighth set of ground markers",
			section = groundMarkerProfile7,
			position = 0
	)
	default String nameProfile7() {
		return "Profile 8";
	}

	@ConfigItem(
			keyName = "tilesProfile7",
			name = "Marker Profile 8",
			description = "Ground markers associated with eighth group",
			section = groundMarkerProfile7,
			position = 0
	)
	default String tilesProfile7() {
		return "";
	}

	// Group 8 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 9",
			position = 8,
			description = "Settings for the ninth group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile8 = "groundMarkerProfile8";

	@ConfigItem(
			keyName = "nameProfile8",
			name = "Marker Name 9",
			description = "Name for the ninth set of ground markers",
			section = groundMarkerProfile8,
			position = 0
	)
	default String nameProfile8() {
		return "Profile 9";
	}

	@ConfigItem(
			keyName = "tilesProfile8",
			name = "Marker Profile 9",
			description = "Ground markers associated with ninth group",
			section = groundMarkerProfile8,
			position = 0
	)
	default String tilesProfile8() {
		return "";
	}

	// Group 9 (+1 for visible strings)
	@ConfigSection(
			name = "Ground Marker Group 10",
			position = 9,
			description = "Settings for the tength group of ground markers",
			closedByDefault = true
	)
	String groundMarkerProfile9 = "groundMarkerProfile9";

	@ConfigItem(
			keyName = "nameProfile9",
			name = "Marker Name 10",
			description = "Name for the tenth set of ground markers",
			section = groundMarkerProfile9,
			position = 0
	)
	default String nameProfile9() {
		return "Profile 10";
	}

	@ConfigItem(
			keyName = "tilesProfile9",
			name = "Marker Profile 10",
			description = "Ground markers associated with tenth group",
			section = groundMarkerProfile9,
			position = 0
	)
	default String tilesProfile9() {
		return "";
	}

	@ConfigItem(
			keyName = "previousMarkerProfile",
			name = "",
			description = "",
			hidden = true
	)
	default Integer previousMarkerProfile() {
		return 0;
	}

	@ConfigItem(
			keyName = "previousMarkerProfile",
			name = "",
			description = "",
			hidden = true
	)
	void setPreviousMarkerProfile(Integer index);
}