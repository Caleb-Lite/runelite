package net.runelite.client.plugins.pluginhub.com.pcrewards;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import net.runelite.client.plugins.pluginhub.com.pcrewards.PcRewardExpOptions;

@ConfigGroup("pcrewards")
public interface PcRewardsConfig extends Config
{

	@ConfigSection(
			name = "Armor Rewards",
			description = "Configure what armor rewards you want displayed",
			position = 98
	)
	String armorSection = "armor";
	@ConfigSection(
			name = "Exp Rewards",
			description = "Configure what exp rewards you want displayed",
			position = 96
	)
	String expSection = "experience";
	@ConfigSection(
			name = "Misc Rewards",
			description = "Configure what misc rewards you want displayed",
			position = 97
	)
	String miscSection = "Misc";




	@ConfigItem(
			position = 0,
			keyName = "displayExp",
			name = "Exp Calculation",
			description = "Calculate experience from current points",
			section = expSection
	)
	default PcRewardExpOptions getCalcStyle() { return PcRewardExpOptions.PRAYER; }
	// This is a different enum from the rest for the exp calculations.

	@ConfigItem(
			position = 1,
			keyName = "attack",
			name = "Attack",
			description = "Configure what Attack experience options displayed.",
			section = expSection
	)
	default PcRewardsConfigOptions getAtkOp(){
		return PcRewardsConfigOptions.ALL;
	}
	@ConfigItem(
			position = 2,
			keyName = "strength",
			name = "Strength",
			description = "Configure what Strength experience options displayed.",
			section = expSection
	)
	default PcRewardsConfigOptions getStrOp(){
		return PcRewardsConfigOptions.ALL;
	}
	@ConfigItem(
			position = 3,
			keyName = "defence",
			name = "Defence",
			description = "Configure what Defence experience options displayed.",
			section = expSection
	)
	default PcRewardsConfigOptions getDefOp(){
		return PcRewardsConfigOptions.ALL;
	}
	@ConfigItem(
			position = 4,
			keyName = "range",
			name = "Range",
			description = "Configure what Range experience options displayed.",
			section = expSection
	)
	default PcRewardsConfigOptions getRngOp(){
		return PcRewardsConfigOptions.ALL;
	}
	@ConfigItem(
			position = 5,
			keyName = "magic",
			name = "Magic",
			description = "Configure what Magic experience options displayed.",
			section = expSection
	)
	default PcRewardsConfigOptions getMagicOp(){
		return PcRewardsConfigOptions.ALL;
	}
	@ConfigItem(
			position = 6,
			keyName = "hitpoints",
			name = "Hitpoints",
			description = "Configure what Hitpoints experience options displayed.",
			section = expSection
	)
	default PcRewardsConfigOptions getHpOp(){
		return PcRewardsConfigOptions.ALL;
	}
	@ConfigItem(
			position = 7,
			keyName = "prayer",
			name = "Prayer",
			description = "Configure what Prayer experience options displayed.",
			section = expSection
	)
	default PcRewardsConfigOptions getPrayOp(){
		return PcRewardsConfigOptions.ALL;
	}

	@ConfigItem(
			position = 8,
			keyName = "expMult",
			name = "Exp. Mult",
			description = "Configure what Prayer experience options displayed.",
			section = expSection
	)
	default int getExpMult(){
		return 0;
	}

	@ConfigItem(
			position = 9,
			keyName = "herbpack",
			name = "Herb Pack",
			description = "Configure if the Herb pack is displayed.",
			section = miscSection
	)
	default boolean getHerb(){
		return false;
	}
	@ConfigItem(
			position = 10,
			keyName = "mineralpack",
			name = "Mineral Pack",
			description = "Configure if the Mineral pack is displayed.",
			section = miscSection
	)
	default boolean getMine(){
		return false;
	}

	@ConfigItem(
			position = 11,
			keyName = "seedpack",
			name = "Seed Pack",
			description = "Configure if the Seed pack is displayed.",
			section = miscSection
	)
	default boolean getSeed(){
		return false;
	}

	@ConfigItem(
			position = 11,
			keyName = "mace",
			name = "Mace",
			description = "Configure if the Mace is displayed.",
			section = armorSection
	)
	default boolean getMace(){
		return false;
	}

	@ConfigItem(
			position = 12,
			keyName = "robetop",
			name = "Robe Top",
			description = "Configure if the Robe top is displayed.",
			section = armorSection
	)
	default boolean getRTop(){
		return false;
	}

	@ConfigItem(
			position = 14,
			keyName = "robebottom",
			name = "Robe Bottoms",
			description = "Configure if the Robe Bottom is displayed.",
			section = armorSection
	)
	default boolean getRBtm(){
		return false;
	}

	@ConfigItem(
			position = 15,
			keyName = "gloves",
			name = "Gloves",
			description = "Configure if the Gloves are displayed.",
			section = armorSection
	)
	default boolean getGlove(){
		return false;
	}

	@ConfigItem(
			position = 15,
			keyName = "magehelm",
			name = "Mage Helm",
			description = "Configure if the Mage Helm is displayed.",
			section = armorSection
	)
	default boolean getMageHelm(){
		return false;
	}

	@ConfigItem(
			position = 16,
			keyName = "rangehelm",
			name = "Range Helm",
			description = "Configure if the Range Helm is displayed.",
			section = armorSection
	)
	default boolean getRhelm(){
		return false;
	}

	@ConfigItem(
			position = 17,
			keyName = "meleehelm",
			name = "Melee Helm",
			description = "Configure if the Melee Helm is displayed.",
			section = armorSection
	)
	default boolean getMelHelm(){
		return false;
	}

	@ConfigItem(
			position = 18,
			keyName = "seal",
			name = "Seal",
			description = "Configure if the Seal is displayed.",
			section = armorSection
	)
	default boolean getSeal(){
		return false;
	}





}
