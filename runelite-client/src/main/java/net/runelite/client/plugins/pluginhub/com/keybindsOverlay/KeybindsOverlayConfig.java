package net.runelite.client.plugins.pluginhub.com.keybindsOverlay;

import net.runelite.client.config.*;

import java.awt.event.KeyEvent;

@ConfigGroup("example")
public interface KeybindsOverlayConfig extends Config
{
	@ConfigSection(
			position = 1,
			name = "Overlay",
			description = "Settings for the overlay."

	)
	String keybindsSection = "keybinds";

	@ConfigSection(
			position = 2,
			name = "Order of the icons",
			description = "Settings for the icons \n 1 <--> 7"

	)
	String iconOrderSection = "layout";

	@ConfigItem(
			position = 0,
			keyName = "combat_icon",
			name = "Combat icon",
			description = "",
			section = iconOrderSection
	)
	default int combatLocation(){ return 1; }

	@ConfigItem(
			position = 1,
			keyName = "skills_icon",
			name = "Skills icon",
			description = "",
			section = iconOrderSection
	)
	default int skillsLocation(){ return 2; }

	@ConfigItem(
			position = 2,
			keyName = "quests_icon",
			name = "Quests icon",
			description = "",
			section = iconOrderSection
	)
	default int questsLocation(){ return 3; }

	@ConfigItem(
			position = 3,
			keyName = "inventory_icon",
			name = "Inventory icon",
			description = "",
			section = iconOrderSection
	)
	default int inventoryLocation(){ return 4; }

	@ConfigItem(
			position = 4,
			keyName = "equipment_icon",
			name = "Equipment icon",
			description = "",
			section = iconOrderSection
	)
	default int equipmentLocation(){ return 5; }

	@ConfigItem(
			position = 5,
			keyName = "prayer_icon",
			name = "Prayer icon",
			description = "",
			section = iconOrderSection
	)

	default int prayerLocation(){ return 6; }

	@ConfigItem(
			position = 6,
			keyName = "spellbook_icon",
			name = "Spellbook icon",
			description = "",
			section = iconOrderSection
	)
	default int spellbookLocation(){ return 7; }

	@ConfigItem(
			position = 3,
			keyName = "combat",
			name = "Combat",
			description = "",
			section = keybindsSection
	)
	default Keybind combatKey(){ return new Keybind(KeyEvent.VK_F1, 0); }

	@ConfigItem(
			position = 4,
			keyName = "skills",
			name = "Skills",
			description = "",
			section = keybindsSection
	)
	default Keybind skillsKey(){ return new Keybind(KeyEvent.VK_F2, 0); }

	@ConfigItem(
			position = 5,
			keyName = "quests",
			name = "Quests",
			description = "",
			section = keybindsSection
	)
	default Keybind questsKey(){ return new Keybind(KeyEvent.VK_F3, 0); }

	@ConfigItem(
			position = 6,
			keyName = "inventory",
			name = "Inventory",
			description = "",
			section = keybindsSection
	)
	default Keybind inventoryKey(){ return new Keybind(KeyEvent.VK_ESCAPE, 0); }

	@ConfigItem(
			position = 7,
			keyName = "equipment",
			name = "Equipment",
			description = "",
			section = keybindsSection
	)
	default Keybind equipmentKey(){ return new Keybind(KeyEvent.VK_F4, 0); }

	@ConfigItem(
			position = 8,
			keyName = "prayers",
			name = "Prayers",
			description = "",
			section = keybindsSection
	)
	default Keybind prayerKey(){ return new Keybind(KeyEvent.VK_F5, 0); }

	@ConfigItem(
			position = 9,
			keyName = "spellbook",
			name = "Spellbook",
			description = "",
			section = keybindsSection
	)
	default Keybind spellbookKey(){ return new Keybind(KeyEvent.VK_F6, 0); }
}
