package net.runelite.client.plugins.pluginhub.com.ericversteeg;

import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.Location;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.Sound;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.TextSize;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.model.config.TimeUnit;
import net.runelite.client.plugins.pluginhub.com.ericversteeg.view.RSAnchorType;
import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup(ProximityPromptConfig.GROUP)
public interface ProximityPromptConfig extends Config
{
	String GROUP = "proximity_prompt";

	@ConfigItem(
			position = 1,
			keyName = "anchorType",
			name = "Anchor",
			description = "Configure anchor of list panel."
	)
	default RSAnchorType anchorType() {
		return RSAnchorType.TOP_LEFT;
	}

	@ConfigItem(
			position = 2,
			keyName = "anchorX",
			name = "X Position",
			description = "Configure x position relative to anchor of list panel."
	)
	default int anchorX() {
		return 10;
	}

	@ConfigItem(
			position = 3,
			keyName = "anchorY",
			name = "Y Position",
			description = "Configures y position relative to anchor of list panel."
	)
	default int anchorY() {
		return 120;
	}

	@ConfigItem(
			position = 4,
			keyName = "width",
			name = "Width",
			description = "Configures the width of list panel."
	)
	default int width() {
		return 140;
	}

	@ConfigItem(
			position = 0,
			keyName = "idFinder",
			name = "ID Finder",
			description = "Configures whether or not to enable ID Finder in list panel."
	)
	default boolean idFinder() {
		return false;
	}

	@ConfigItem(
			position = 6,
			keyName = "textSize",
			name = "Text Size",
			description = "Configures the text size of list panel."
	)
	default TextSize textSize() {
		return TextSize.SMALL;
	}

	@ConfigSection(
			name = "Custom",
			description = "JSON Reminders",
			position = 50,
			closedByDefault = true
	)
	String customReminders = "customRemindersSection";

	@ConfigItem(
			position = 0,
			keyName = "jsonReminders",
			name = "JSON",
			description = "Json array of reminders.",
			section = customReminders

	)
	default String customReminders() {
		return "";
	}

	@ConfigSection(
			name = "1",
			description = "Reminder 1",
			position = 21,
			closedByDefault = true
	)
	String reminder1= "reminder1";

	@ConfigItem(
			keyName = "enableReminder1",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder1
	)
	default boolean reminder1Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder1Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder1
	)
	default String reminder1Text() { return ""; }

	@ConfigItem(
			keyName = "reminder1Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder1
	)
	default Color reminder1Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder1Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder1
	)
	default String reminder1Times() { return ""; }

	@ConfigItem(
			keyName = "reminder1DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder1
	)
	default String reminder1DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder1Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder1
	)
	default String reminder1Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder1Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder1
	)
	default String reminder1Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder1Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder1
	)
	default String reminder1Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder1Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder1
	)
	default int reminder1Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder1RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder1
	)
	default String reminder1RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder1NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder1
	)
	default String reminder1NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder1ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder1
	)
	default String reminder1ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder1Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder1
	)
	default int reminder1Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder1Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder1
	)
	default int reminder1Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder1TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder1
	)
	default TimeUnit reminder1TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder1ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder1
	)
	default String reminder1ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder1Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder1
	)
	default boolean reminder1Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder1SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder1
	)
	default Location reminder1Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder1panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder1
	)
	default RSAnchorType reminder1PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder1PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder1
	)
	default int reminder1PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder1PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder1
	)
	default int reminder1PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder1ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder1
	)
	default int reminder1ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder1Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder1
	)
	default Sound reminder1Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder1ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder1
	)
	default boolean reminder1ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder1PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder1
	)
	default int reminder1PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder1PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder1
	)
	default TextSize reminder1PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder1PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder1
	)
	default Color reminder1PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder1PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder1
	)
	default boolean reminder1PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder1ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder1
	)
	default boolean reminder1ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder1ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder1
	)
	default int reminder1ImageOffset() { return 0; }


	@ConfigSection(
			name = "2",
			description = "Reminder 2",
			position = 22,
			closedByDefault = true
	)
	String reminder2= "reminder2";

	@ConfigItem(
			keyName = "enableReminder2",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder2
	)
	default boolean reminder2Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder2Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder2
	)
	default String reminder2Text() { return ""; }

	@ConfigItem(
			keyName = "reminder2Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder2
	)
	default Color reminder2Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder2Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder2
	)
	default String reminder2Times() { return ""; }

	@ConfigItem(
			keyName = "reminder2DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder2
	)
	default String reminder2DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder2Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder2
	)
	default String reminder2Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder2Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder2
	)
	default String reminder2Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder2Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder2
	)
	default String reminder2Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder2Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder2
	)
	default int reminder2Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder2RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder2
	)
	default String reminder2RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder2NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder2
	)
	default String reminder2NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder2ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder2
	)
	default String reminder2ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder2Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder2
	)
	default int reminder2Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder2Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder2
	)
	default int reminder2Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder2TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder2
	)
	default TimeUnit reminder2TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder2ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder2
	)
	default String reminder2ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder2Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder2
	)
	default boolean reminder2Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder2SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder2
	)
	default Location reminder2Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder2panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder2
	)
	default RSAnchorType reminder2PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder2PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder2
	)
	default int reminder2PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder2PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder2
	)
	default int reminder2PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder2ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder2
	)
	default int reminder2ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder2Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder2
	)
	default Sound reminder2Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder2ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder2
	)
	default boolean reminder2ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder2PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder2
	)
	default int reminder2PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder2PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder2
	)
	default TextSize reminder2PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder2PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder2
	)
	default Color reminder2PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder2PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder2
	)
	default boolean reminder2PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder2ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder2
	)
	default boolean reminder2ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder2ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder2
	)
	default int reminder2ImageOffset() { return 0; }


	@ConfigSection(
			name = "3",
			description = "Reminder 3",
			position = 23,
			closedByDefault = true
	)
	String reminder3= "reminder3";

	@ConfigItem(
			keyName = "enableReminder3",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder3
	)
	default boolean reminder3Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder3Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder3
	)
	default String reminder3Text() { return ""; }

	@ConfigItem(
			keyName = "reminder3Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder3
	)
	default Color reminder3Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder3Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder3
	)
	default String reminder3Times() { return ""; }

	@ConfigItem(
			keyName = "reminder3DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder3
	)
	default String reminder3DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder3Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder3
	)
	default String reminder3Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder3Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder3
	)
	default String reminder3Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder3Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder3
	)
	default String reminder3Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder3Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder3
	)
	default int reminder3Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder3RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder3
	)
	default String reminder3RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder3NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder3
	)
	default String reminder3NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder3ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder3
	)
	default String reminder3ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder3Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder3
	)
	default int reminder3Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder3Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder3
	)
	default int reminder3Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder3TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder3
	)
	default TimeUnit reminder3TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder3ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder3
	)
	default String reminder3ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder3Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder3
	)
	default boolean reminder3Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder3SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder3
	)
	default Location reminder3Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder3panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder3
	)
	default RSAnchorType reminder3PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder3PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder3
	)
	default int reminder3PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder3PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder3
	)
	default int reminder3PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder3ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder3
	)
	default int reminder3ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder3Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder3
	)
	default Sound reminder3Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder3ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder3
	)
	default boolean reminder3ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder3PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder3
	)
	default int reminder3PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder3PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder3
	)
	default TextSize reminder3PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder3PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder3
	)
	default Color reminder3PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder3PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder3
	)
	default boolean reminder3PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder3ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder3
	)
	default boolean reminder3ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder3ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder3
	)
	default int reminder3ImageOffset() { return 0; }


	@ConfigSection(
			name = "4",
			description = "Reminder 4",
			position = 24,
			closedByDefault = true
	)
	String reminder4= "reminder4";

	@ConfigItem(
			keyName = "enableReminder4",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder4
	)
	default boolean reminder4Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder4Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder4
	)
	default String reminder4Text() { return ""; }

	@ConfigItem(
			keyName = "reminder4Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder4
	)
	default Color reminder4Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder4Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder4
	)
	default String reminder4Times() { return ""; }

	@ConfigItem(
			keyName = "reminder4DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder4
	)
	default String reminder4DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder4Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder4
	)
	default String reminder4Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder4Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder4
	)
	default String reminder4Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder4Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder4
	)
	default String reminder4Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder4Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder4
	)
	default int reminder4Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder4RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder4
	)
	default String reminder4RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder4NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder4
	)
	default String reminder4NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder4ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder4
	)
	default String reminder4ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder4Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder4
	)
	default int reminder4Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder4Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder4
	)
	default int reminder4Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder4TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder4
	)
	default TimeUnit reminder4TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder4ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder4
	)
	default String reminder4ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder4Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder4
	)
	default boolean reminder4Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder4SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder4
	)
	default Location reminder4Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder4panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder4
	)
	default RSAnchorType reminder4PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder4PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder4
	)
	default int reminder4PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder4PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder4
	)
	default int reminder4PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder4ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder4
	)
	default int reminder4ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder4Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder4
	)
	default Sound reminder4Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder4ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder4
	)
	default boolean reminder4ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder4PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder4
	)
	default int reminder4PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder4PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder4
	)
	default TextSize reminder4PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder4PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder4
	)
	default Color reminder4PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder4PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder4
	)
	default boolean reminder4PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder4ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder4
	)
	default boolean reminder4ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder4ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder4
	)
	default int reminder4ImageOffset() { return 0; }


	@ConfigSection(
			name = "5",
			description = "Reminder 5",
			position = 25,
			closedByDefault = true
	)
	String reminder5= "reminder5";

	@ConfigItem(
			keyName = "enableReminder5",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder5
	)
	default boolean reminder5Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder5Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder5
	)
	default String reminder5Text() { return ""; }

	@ConfigItem(
			keyName = "reminder5Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder5
	)
	default Color reminder5Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder5Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder5
	)
	default String reminder5Times() { return ""; }

	@ConfigItem(
			keyName = "reminder5DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder5
	)
	default String reminder5DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder5Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder5
	)
	default String reminder5Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder5Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder5
	)
	default String reminder5Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder5Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder5
	)
	default String reminder5Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder5Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder5
	)
	default int reminder5Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder5RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder5
	)
	default String reminder5RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder5NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder5
	)
	default String reminder5NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder5ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder5
	)
	default String reminder5ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder5Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder5
	)
	default int reminder5Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder5Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder5
	)
	default int reminder5Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder5TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder5
	)
	default TimeUnit reminder5TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder5ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder5
	)
	default String reminder5ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder5Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder5
	)
	default boolean reminder5Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder5SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder5
	)
	default Location reminder5Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder5panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder5
	)
	default RSAnchorType reminder5PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder5PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder5
	)
	default int reminder5PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder5PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder5
	)
	default int reminder5PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder5ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder5
	)
	default int reminder5ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder5Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder5
	)
	default Sound reminder5Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder5ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder5
	)
	default boolean reminder5ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder5PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder5
	)
	default int reminder5PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder5PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder5
	)
	default TextSize reminder5PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder5PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder5
	)
	default Color reminder5PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder5PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder5
	)
	default boolean reminder5PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder5ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder5
	)
	default boolean reminder5ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder5ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder5
	)
	default int reminder5ImageOffset() { return 0; }


	@ConfigSection(
			name = "6",
			description = "Reminder 6",
			position = 26,
			closedByDefault = true
	)
	String reminder6= "reminder6";

	@ConfigItem(
			keyName = "enableReminder6",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder6
	)
	default boolean reminder6Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder6Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder6
	)
	default String reminder6Text() { return ""; }

	@ConfigItem(
			keyName = "reminder6Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder6
	)
	default Color reminder6Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder6Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder6
	)
	default String reminder6Times() { return ""; }

	@ConfigItem(
			keyName = "reminder6DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder6
	)
	default String reminder6DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder6Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder6
	)
	default String reminder6Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder6Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder6
	)
	default String reminder6Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder6Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder6
	)
	default String reminder6Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder6Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder6
	)
	default int reminder6Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder6RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder6
	)
	default String reminder6RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder6NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder6
	)
	default String reminder6NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder6ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder6
	)
	default String reminder6ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder6Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder6
	)
	default int reminder6Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder6Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder6
	)
	default int reminder6Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder6TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder6
	)
	default TimeUnit reminder6TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder6ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder6
	)
	default String reminder6ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder6Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder6
	)
	default boolean reminder6Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder6SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder6
	)
	default Location reminder6Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder6panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder6
	)
	default RSAnchorType reminder6PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder6PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder6
	)
	default int reminder6PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder6PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder6
	)
	default int reminder6PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder6ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder6
	)
	default int reminder6ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder6Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder6
	)
	default Sound reminder6Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder6ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder6
	)
	default boolean reminder6ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder6PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder6
	)
	default int reminder6PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder6PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder6
	)
	default TextSize reminder6PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder6PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder6
	)
	default Color reminder6PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder6PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder6
	)
	default boolean reminder6PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder6ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder6
	)
	default boolean reminder6ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder6ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder6
	)
	default int reminder6ImageOffset() { return 0; }


	@ConfigSection(
			name = "7",
			description = "Reminder 7",
			position = 27,
			closedByDefault = true
	)
	String reminder7= "reminder7";

	@ConfigItem(
			keyName = "enableReminder7",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder7
	)
	default boolean reminder7Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder7Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder7
	)
	default String reminder7Text() { return ""; }

	@ConfigItem(
			keyName = "reminder7Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder7
	)
	default Color reminder7Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder7Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder7
	)
	default String reminder7Times() { return ""; }

	@ConfigItem(
			keyName = "reminder7DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder7
	)
	default String reminder7DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder7Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder7
	)
	default String reminder7Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder7Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder7
	)
	default String reminder7Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder7Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder7
	)
	default String reminder7Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder7Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder7
	)
	default int reminder7Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder7RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder7
	)
	default String reminder7RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder7NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder7
	)
	default String reminder7NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder7ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder7
	)
	default String reminder7ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder7Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder7
	)
	default int reminder7Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder7Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder7
	)
	default int reminder7Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder7TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder7
	)
	default TimeUnit reminder7TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder7ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder7
	)
	default String reminder7ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder7Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder7
	)
	default boolean reminder7Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder7SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder7
	)
	default Location reminder7Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder7panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder7
	)
	default RSAnchorType reminder7PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder7PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder7
	)
	default int reminder7PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder7PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder7
	)
	default int reminder7PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder7ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder7
	)
	default int reminder7ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder7Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder7
	)
	default Sound reminder7Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder7ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder7
	)
	default boolean reminder7ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder7PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder7
	)
	default int reminder7PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder7PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder7
	)
	default TextSize reminder7PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder7PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder7
	)
	default Color reminder7PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder7PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder7
	)
	default boolean reminder7PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder7ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder7
	)
	default boolean reminder7ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder7ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder7
	)
	default int reminder7ImageOffset() { return 0; }


	@ConfigSection(
			name = "8",
			description = "Reminder 8",
			position = 28,
			closedByDefault = true
	)
	String reminder8= "reminder8";

	@ConfigItem(
			keyName = "enableReminder8",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder8
	)
	default boolean reminder8Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder8Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder8
	)
	default String reminder8Text() { return ""; }

	@ConfigItem(
			keyName = "reminder8Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder8
	)
	default Color reminder8Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder8Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder8
	)
	default String reminder8Times() { return ""; }

	@ConfigItem(
			keyName = "reminder8DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder8
	)
	default String reminder8DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder8Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder8
	)
	default String reminder8Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder8Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder8
	)
	default String reminder8Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder8Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder8
	)
	default String reminder8Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder8Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder8
	)
	default int reminder8Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder8RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder8
	)
	default String reminder8RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder8NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder8
	)
	default String reminder8NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder8ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder8
	)
	default String reminder8ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder8Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder8
	)
	default int reminder8Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder8Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder8
	)
	default int reminder8Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder8TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder8
	)
	default TimeUnit reminder8TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder8ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder8
	)
	default String reminder8ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder8Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder8
	)
	default boolean reminder8Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder8SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder8
	)
	default Location reminder8Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder8panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder8
	)
	default RSAnchorType reminder8PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder8PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder8
	)
	default int reminder8PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder8PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder8
	)
	default int reminder8PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder8ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder8
	)
	default int reminder8ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder8Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder8
	)
	default Sound reminder8Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder8ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder8
	)
	default boolean reminder8ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder8PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder8
	)
	default int reminder8PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder8PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder8
	)
	default TextSize reminder8PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder8PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder8
	)
	default Color reminder8PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder8PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder8
	)
	default boolean reminder8PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder8ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder8
	)
	default boolean reminder8ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder8ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder8
	)
	default int reminder8ImageOffset() { return 0; }


	@ConfigSection(
			name = "9",
			description = "Reminder 9",
			position = 29,
			closedByDefault = true
	)
	String reminder9= "reminder9";

	@ConfigItem(
			keyName = "enableReminder9",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder9
	)
	default boolean reminder9Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder9Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder9
	)
	default String reminder9Text() { return ""; }

	@ConfigItem(
			keyName = "reminder9Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder9
	)
	default Color reminder9Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder9Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder9
	)
	default String reminder9Times() { return ""; }

	@ConfigItem(
			keyName = "reminder9DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder9
	)
	default String reminder9DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder9Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder9
	)
	default String reminder9Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder9Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder9
	)
	default String reminder9Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder9Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder9
	)
	default String reminder9Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder9Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder9
	)
	default int reminder9Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder9RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder9
	)
	default String reminder9RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder9NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder9
	)
	default String reminder9NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder9ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder9
	)
	default String reminder9ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder9Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder9
	)
	default int reminder9Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder9Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder9
	)
	default int reminder9Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder9TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder9
	)
	default TimeUnit reminder9TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder9ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder9
	)
	default String reminder9ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder9Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder9
	)
	default boolean reminder9Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder9SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder9
	)
	default Location reminder9Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder9panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder9
	)
	default RSAnchorType reminder9PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder9PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder9
	)
	default int reminder9PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder9PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder9
	)
	default int reminder9PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder9ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder9
	)
	default int reminder9ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder9Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder9
	)
	default Sound reminder9Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder9ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder9
	)
	default boolean reminder9ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder9PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder9
	)
	default int reminder9PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder9PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder9
	)
	default TextSize reminder9PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder9PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder9
	)
	default Color reminder9PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder9PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder9
	)
	default boolean reminder9PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder9ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder9
	)
	default boolean reminder9ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder9ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder9
	)
	default int reminder9ImageOffset() { return 0; }


	@ConfigSection(
			name = "10",
			description = "Reminder 10",
			position = 30,
			closedByDefault = true
	)
	String reminder10= "reminder10";

	@ConfigItem(
			keyName = "enableReminder10",
			position = 0,
			name = "Enable",
			description = "Configures whether or not reminder is enabled.",
			section = reminder10
	)
	default boolean reminder10Enable()  { return false; }

	@ConfigItem(
			keyName = "reminder10Text",
			position = 1,
			name = "Text",
			description = "Configures the reminder text.",
			section = reminder10
	)
	default String reminder10Text() { return ""; }

	@ConfigItem(
			keyName = "reminder10Color",
			position = 2,
			name = "Color",
			description = "Configures the text color.",
			section = reminder10
	)
	default Color reminder10Color() { return Color.WHITE; }

	@ConfigItem(
			keyName = "reminder10Times",
			position = 37,
			name = "Times",
			description = "Configures what times reminder is shown. Set times 10:00pm, 7:30pm or spans 8:30am-12:00pm, 10:00pm-1:00am. (comma separated).",
			section = reminder10
	)
	default String reminder10Times() { return ""; }

	@ConfigItem(
			keyName = "reminder10DaysOfWeek",
			position = 38,
			name = "Days",
			description = "Configures what days of week reminder is shown, e.g. Mon, Thu (comma separated).",
			section = reminder10
	)
	default String reminder10DaysOfWeek() { return ""; }

	@ConfigItem(
			keyName = "reminder10Dates",
			position = 39,
			name = "Dates",
			description = "Configures what dates reminder is shown. Set dates 04/30, 05/28/23 or ranges 9/20/22-10/30/22, 7/20-8/9. (comma separated).",
			section = reminder10
	)
	default String reminder10Dates() { return ""; }

	@ConfigItem(
			keyName = "reminder10Coordinates",
			position = 30,
			name = "Coordinates",
			description = "Configures coordinates where reminder is shown (x, y), e.g. (40, 50), (1000, 750) (comma separated).",
			section = reminder10
	)
	default String reminder10Coordinates() { return ""; }

	@ConfigItem(
			keyName = "reminder10Geofences",
			position = 31,
			name = "Geofences",
			description = "Configures geofences where reminder is shown (west, north, east, south), e.g. (40, 50, 1000, 750) (comma separated).",
			section = reminder10
	)
	default String reminder10Geofences() { return ""; }

	@ConfigItem(
			keyName = "reminder10Radius",
			position = 31,
			name = "Coordinate Radius",
			description = "Configures how far away from coordinates reminder is shown.",
			section = reminder10
	)
	default int reminder10Radius() { return 0; }

	@ConfigItem(
			keyName = "Reminder10RegionId",
			position = 33,
			name = "Regions",
			description = "Configures region ids when reminder is shown (comma separated).",
			section = reminder10
	)
	default String reminder10RegionIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder10NpcIds",
			position = 34,
			name = "Npcs",
			description = "Configures npc ids where reminder is shown (comma separated).",
			section = reminder10
	)
	default String reminder10NpcIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder10ItemIds",
			position = 35,
			name = "Items",
			description = "Configures item ids where reminder is shown (comma separated).",
			section = reminder10
	)
	default String reminder10ItemIds() { return ""; }

	@ConfigItem(
			keyName = "Reminder10Duration",
			position = 3,
			name = "Duration",
			description = "Configures duration of reminder.",
			section = reminder10
	)
	default int reminder10Duration() { return 0; }

	@ConfigItem(
			keyName = "Reminder10Cooldown",
			position = 4,
			name = "Cooldown",
			description = "Configures cooldown of reminder.",
			section = reminder10
	)
	default int reminder10Cooldown() { return 0; }

	@ConfigItem(
			keyName = "Reminder10TimeUnit",
			position = 5,
			name = "Time Unit",
			description = "Configures the time unit for duration and cooldown.",
			section = reminder10
	)
	default TimeUnit reminder10TimeUnit() { return TimeUnit.SECONDS; }

	@ConfigItem(
			keyName = "Reminder10ChatPatterns",
			position = 36,
			name = "Messages",
			description = "Configures text or regex to match messages to show reminder. (comma separated)",
			section = reminder10
	)
	default String reminder10ChatPatterns() { return ""; }

	@ConfigItem(
			keyName = "Reminder10Notification",
			position = 6,
			name = "Notification",
			description = "Configures whether or not to show a background notification.",
			section = reminder10
	)
	default boolean reminder10Notify() { return false; }

	@ConfigItem(
			keyName = "Reminder10SeparatePanel",
			position = 7,
			name = "Location",
			description = "Configures the location for reminder.",
			section = reminder10
	)
	default Location reminder10Location() { return Location.IN_LIST; }

	@ConfigItem(
			keyName = "Reminder10panelAnchorType",
			position = 8,
			name = "Anchor",
			description = "Configures the anchor type of panel.",
			section = reminder10
	)
	default RSAnchorType reminder10PanelAnchorType() { return RSAnchorType.TOP_LEFT; }

	@ConfigItem(
			keyName = "Reminder10PanelAnchorX",
			position = 9,
			name = "X Position",
			description = "Configures the x position of panel.",
			section = reminder10
	)
	default int reminder10PanelAnchorX() { return 0; }

	@ConfigItem(
			keyName = "Reminder10PanelAnchorY",
			position = 10,
			name = "Y Position",
			description = "Configures the y position of panel.",
			section = reminder10
	)
	default int reminder10PanelAnchorY() { return 0; }

	@ConfigItem(
			keyName = "Reminder10ImageId",
			position = 14,
			name = "Image ID",
			description = "Configures image id to display in reminder.",
			section = reminder10
	)
	default int reminder10ImageId() { return 0; }

	@ConfigItem(
			keyName = "Reminder10Sound",
			position = 13,
			name = "Sound",
			description = "Configures sound that plays with reminder.",
			section = reminder10
	)
	default Sound reminder10Sound() { return Sound.NONE; }

	@ConfigItem(
			keyName = "Reminder10ImageOffsetNegative",
			position = 14,
			name = "Offset Negative",
			description = "Configures whether or not image offset is a negative number.",
			section = reminder10
	)
	default boolean reminder10ImageOffsetNegative() { return false; }


	@ConfigItem(
			keyName = "Reminder10PanelWidth",
			position = 11,
			name = "Width",
			description = "Configures width of panel.",
			section = reminder10
	)
	default int reminder10PanelWidth() { return 140; }


	@ConfigItem(
			keyName = "Reminder10PanelTextSize",
			position = 12,
			name = "Text Size",
			description = "Configures text size of reminder.",
			section = reminder10
	)
	default TextSize reminder10PanelTextSize() { return TextSize.SMALL; }


	@ConfigItem(
			keyName = "Reminder10PanelColor",
			position = 12,
			name = "Background Color",
			description = "Configures panel color.",
			section = reminder10
	)
	default Color reminder10PanelColor() { return new Color(115, 115, 115); }

	@Alpha
	@ConfigItem(
			keyName = "Reminder10PanelBorder",
			position = 12,
			name = "Border",
			description = "Configures whether or not panel has border.",
			section = reminder10
	)
	default boolean reminder10PanelBorder() { return true; }


	@ConfigItem(
			keyName = "Reminder10ForceShow",
			position = 0,
			name = "Force",
			description = "Configures whether or not to force the reminder visible.",
			section = reminder10
	)
	default boolean reminder10ForceShow() { return false; }

	@ConfigItem(
			keyName = "Reminder10ImageOffset",
			position = 14,
			name = "Image Offset",
			description = "Configures offset for image.",
			section = reminder10
	)
	default int reminder10ImageOffset() { return 0; }
}