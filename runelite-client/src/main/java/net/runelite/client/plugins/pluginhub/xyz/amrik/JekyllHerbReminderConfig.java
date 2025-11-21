package net.runelite.client.plugins.pluginhub.xyz.amrik;

import lombok.AllArgsConstructor;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("JekyllHerbs")
public interface JekyllHerbReminderConfig extends Config {

	@AllArgsConstructor
	enum RewardWantedType {
		DEFAULT("Default"),
		STRENGTH_POTION("Strength Potion"),
		ANTIPOISON("Antipoison"),
		ATTACK_POTION("Attack Potion"),
		RESTORE_POTION("Restore Potion"),
		ENERGY_POTION("Energy Potion"),
		DEFENCE_POTION("Defence Potion"),
		AGILITY_POTION("Agility Potion"),
		SUPER_ATTACK("Super Attack"),
		SUPER_ENERGY("Super Energy"),
		SUPER_STRENGTH("Super Strength"),
		SUPER_RESTORE("Super Restore"),
		SUPER_DEFENCE("Super Defence"),
		MAGIC_POTION("Magic Potion"),
		STAMINA_POTION("Stamina Potion");

		private final String value;

		@Override
		public String toString() {
			return value;
		}
	}

	@ConfigItem(keyName = "rewardWanted", name = "Reward", description = "Configures reward you wish to get from Jekyll & Hyde.")
	default RewardWantedType rewardWantedType() {
		return RewardWantedType.DEFAULT;
	}
}
