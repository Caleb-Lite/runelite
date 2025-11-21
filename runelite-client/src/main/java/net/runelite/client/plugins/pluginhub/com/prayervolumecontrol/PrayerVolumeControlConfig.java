package net.runelite.client.plugins.pluginhub.com.prayervolumecontrol;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("Prayer volume control")
public interface PrayerVolumeControlConfig extends Config
{
	@ConfigSection(
			name = "Prayer volume",
			description = "Manage the volume level for all prayers",
			position = 0
	)
	String allPrayersSection = "allPrayersSection";

	@ConfigItem(
			keyName = "use_override_prayer_volume",
			name = "Global prayer volume",
			description = "Overrides the individual prayer volumes",
			section = allPrayersSection
	)
	default boolean useOverridePrayerVolume() { return true; }

	@ConfigItem(
			keyName = "override_prayer_volume",
			name = "Volume level",
			description = "Modify volume for every prayer sound",
			section = allPrayersSection
	)
	default int overridePrayerVolume() { return 50; }

	@ConfigSection(
			name = "Misc",
			description = "Miscellaneous sounds related to prayers",
			position = 1
	)
	String miscSection = "miscSection";

	@ConfigItem(
			keyName = "ui_boop",
			name = "UI buttons (including quick prayer button)",
			description = "<html>Modify volume for any UI buttons (set to 100 for default volume)" +
					"<br>Changing this volume will affect all UI buttons (quick prayers, settings, world map, checkboxes, etc.)</html>",
			section = miscSection
	)
	default int uiBoop() { return 100; }

	@ConfigItem(
			keyName = "prayer_deactivate",
			name = "Prayer deactivation",
			description = "Modify volume for the prayer deactivation sound (set to 100 for default volume)",
			section = miscSection
	)
	default int prayerDeactivate() { return 100; }

	@ConfigSection(
			name = "Individual prayers",
			description = "Manage the volume level for individual prayers",
			position = 2,
			closedByDefault = true
	)
	String prayerSection = "prayerSection";

	@ConfigItem(
			keyName = "augury_mystic_will",
			name = "Augury / Mystic Will",
			description = "Modify volume for Augury and Mystic Will (set to 100 for default volume)",
			section = prayerSection
	)
	default int auguryMysticWill() { return 100; }

	@ConfigItem(
			keyName = "burst_of_strength",
			name = "Burst of Strength",
			description = "Modify volume for Burst of Strength (set to 100 for default volume)",
			section = prayerSection
	)
	default int burstOfStrength() { return 100; }

	@ConfigItem(
			keyName = "chivalry",
			name = "Chivalry",
			description = "Modify volume for Chivalry (set to 100 for default volume)",
			section = prayerSection
	)
	default int chivalry() { return 100; }

	@ConfigItem(
			keyName = "clarity_of_thought",
			name = "Clarity of Thought",
			description = "Modify volume for Clarity of Thought (set to 100 for default volume)",
			section = prayerSection
	)
	default int clarityOfThought() { return 100; }

	@ConfigItem(
			keyName = "eagle_eye",
			name = "Eagle Eye",
			description = "Modify volume for Eagle Eye (set to 100 for default volume)",
			section = prayerSection
	)
	default int eagleEye() { return 100; }

	@ConfigItem(
			keyName = "hawk_eye",
			name = "Hawk Eye",
			description = "Modify volume for Hawk Eye (set to 100 for default volume)",
			section = prayerSection
	)
	default int hawkEye() { return 100; }

	@ConfigItem(
			keyName = "improved_reflexes",
			name = "Improved Reflexes",
			description = "Modify volume for Improved Reflexes (set to 100 for default volume)",
			section = prayerSection
	)
	default int improvedReflexes() { return 100; }

	@ConfigItem(
			keyName = "incredible_reflexes",
			name = "Incredible Reflexes",
			description = "Modify volume for Incredible Reflexes (set to 100 for default volume)",
			section = prayerSection
	)
	default int incredibleReflexes() { return 100; }

	@ConfigItem(
			keyName = "mystic_lore",
			name = "Mystic Lore",
			description = "Modify volume for Mystic Lore (set to 100 for default volume)",
			section = prayerSection
	)
	default int mysticLore() { return 100; }

	@ConfigItem(
			keyName = "mystic_might",
			name = "Mystic Might",
			description = "Modify volume for Mystic Might (set to 100 for default volume)",
			section = prayerSection
	)
	default int mysticMight() { return 100; }

	@ConfigItem(
			keyName = "piety",
			name = "Piety",
			description = "Modify volume for Piety (set to 100 for default volume)",
			section = prayerSection
	)
	default int piety() { return 100; }

	@ConfigItem(
			keyName = "preserve_rapid_restore",
			name = "Preserve / Rapid Restore",
			description = "Modify volume for Preserve and Rapid Restore (set to 100 for default volume)",
			section = prayerSection
	)
	default int preserveRapidRestore() { return 100; }

	@ConfigItem(
			keyName = "protect_from_magic",
			name = "Protect from Magic",
			description = "Modify volume for Protect from Magic (set to 100 for default volume)",
			section = prayerSection
	)
	default int protectFromMagic() { return 100; }

	@ConfigItem(
			keyName = "protect_from_melee",
			name = "Protect from Melee",
			description = "Modify volume for Protect from Melee (set to 100 for default volume)",
			section = prayerSection
	)
	default int protectFromMelee() { return 100; }

	@ConfigItem(
			keyName = "protect_from_missiles",
			name = "Protect from Missiles",
			description = "Modify volume for Protect from Missiles (set to 100 for default volume)",
			section = prayerSection
	)
	default int protectFromMissiles() { return 100; }

	@ConfigItem(
			keyName = "protect_item",
			name = "Protect Item",
			description = "Modify volume for Protect Item (set to 100 for default volume)",
			section = prayerSection
	)
	default int protectItem() { return 100; }

	@ConfigItem(
			keyName = "rapid_heal",
			name = "Rapid Heal",
			description = "Modify volume for Rapid Heal (set to 100 for default volume)",
			section = prayerSection
	)
	default int rapidHeal() { return 100; }

	@ConfigItem(
			keyName = "redemption",
			name = "Redemption",
			description = "Modify volume for Redemption (set to 100 for default volume)",
			section = prayerSection
	)
	default int redemption() { return 100; }

	@ConfigItem(
			keyName = "retribution",
			name = "Retribution",
			description = "Modify volume for Retribution (set to 100 for default volume)",
			section = prayerSection
	)
	default int retribution() { return 100; }

	@ConfigItem(
			keyName = "rigour_sharp_eye",
			name = "Rigour / Sharp Eye",
			description = "Modify volume for Rigour and Sharp Eye (set to 100 for default volume)",
			section = prayerSection
	)
	default int rigourSharpEye() { return 100; }

	@ConfigItem(
			keyName = "rock_skin",
			name = "Rock Skin",
			description = "Modify volume for Rock Skin (set to 100 for default volume)",
			section = prayerSection
	)
	default int rockSkin() { return 100; }

	@ConfigItem(
			keyName = "smite",
			name = "Smite",
			description = "Modify volume for Smite (set to 100 for default volume)",
			section = prayerSection
	)
	default int smite() { return 100; }

	@ConfigItem(
			keyName = "steel_skin",
			name = "Steel Skin",
			description = "Modify volume for Steel Skin (set to 100 for default volume)",
			section = prayerSection
	)
	default int steelSkin() { return 100; }

	@ConfigItem(
			keyName = "superhuman_strength",
			name = "Superhuman Strength",
			description = "Modify volume for Superhuman Strength (set to 100 for default volume)",
			section = prayerSection
	)
	default int superhumanStrength() { return 100; }

	@ConfigItem(
			keyName = "thick_skin",
			name = "Thick Skin",
			description = "Modify volume for Thick Skin (set to 100 for default volume)",
			section = prayerSection
	)
	default int thickSkin() { return 100; }

	@ConfigItem(
			keyName = "ultimate_strength",
			name = "Ultimate Strength",
			description = "Modify volume for Ultimate Strength (set to 100 for default volume)",
			section = prayerSection
	)
	default int ultimateStrength() { return 100; }
}
