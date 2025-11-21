package net.runelite.client.plugins.pluginhub.io.robrichardson.skillsorganizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(SkillsOrganizerConfig.GROUP)
public interface SkillsOrganizerConfig extends Config
{
	String GROUP = "SkillsOrganizer";

	@ConfigItem(
		keyName = "attackPosition",
		name = "Attack Position",
		description = "Set the attack skill position"
	)
	default SkillPositionOption attackPosition()
	{
		return SkillPositionOption.ONE;
	}

	@ConfigItem(
			keyName = "strengthPosition",
			name = "Strength Position",
			description = "Set the strength skill position"
	)
	default SkillPositionOption strengthPosition()
	{
		return SkillPositionOption.TWO;
	}

	@ConfigItem(
			keyName = "defencePosition",
			name = "Defence Position",
			description = "Set the defence skill position"
	)
	default SkillPositionOption defencePosition()
	{
		return SkillPositionOption.THREE;
	}

	@ConfigItem(
			keyName = "rangedPosition",
			name = "Ranged Position",
			description = "Set the ranged skill position"
	)
	default SkillPositionOption rangedPosition()
	{
		return SkillPositionOption.FOUR;
	}

	@ConfigItem(
			keyName = "prayerPosition",
			name = "Prayer Position",
			description = "Set the prayer skill position"
	)
	default SkillPositionOption prayerPosition()
	{
		return SkillPositionOption.FIVE;
	}

	@ConfigItem(
			keyName = "magicPosition",
			name = "Magic Position",
			description = "Set the magic skill position"
	)
	default SkillPositionOption magicPosition()
	{
		return SkillPositionOption.SIX;
	}

	@ConfigItem(
			keyName = "runecraftPosition",
			name = "Runecraft Position",
			description = "Set the runecraft skill position"
	)
	default SkillPositionOption runecraftPosition()
	{
		return SkillPositionOption.SEVEN;
	}

	@ConfigItem(
			keyName = "constructionPosition",
			name = "Construction Position",
			description = "Set the construction skill position"
	)
	default SkillPositionOption constructionPosition()
	{
		return SkillPositionOption.EIGHT;
	}

	@ConfigItem(
			keyName = "agilityPosition",
			name = "Agility Position",
			description = "Set the agility skill position"
	)
	default SkillPositionOption agilityPosition()
	{
		return SkillPositionOption.TEN;
	}

	@ConfigItem(
			keyName = "herblorePosition",
			name = "Herblore Position",
			description = "Set the herblore skill position"
	)
	default SkillPositionOption herblorePosition()
	{
		return SkillPositionOption.ELEVEN;
	}

	@ConfigItem(
			keyName = "thievingPosition",
			name = "Thieving Position",
			description = "Set the thieving skill position"
	)
	default SkillPositionOption thievingPosition()
	{
		return SkillPositionOption.TWELVE;
	}

	@ConfigItem(
			keyName = "craftingPosition",
			name = "Crafting Position",
			description = "Set the crafting skill position"
	)
	default SkillPositionOption craftingPosition()
	{
		return SkillPositionOption.THIRTEEN;
	}

	@ConfigItem(
			keyName = "fletchingPosition",
			name = "Fletching Position",
			description = "Set the fletching skill position"
	)
	default SkillPositionOption fletchingPosition()
	{
		return SkillPositionOption.FOURTEEN;
	}

	@ConfigItem(
			keyName = "slayerPosition",
			name = "Slayer Position",
			description = "Set the slayer skill position"
	)
	default SkillPositionOption slayerPosition()
	{
		return SkillPositionOption.FIFTEEN;
	}

	@ConfigItem(
			keyName = "hunterPosition",
			name = "Hunter Position",
			description = "Set the hunter skill position"
	)
	default SkillPositionOption hunterPosition()
	{
		return SkillPositionOption.SIXTEEN;
	}

	@ConfigItem(
			keyName = "miningPosition",
			name = "Mining Position",
			description = "Set the mining skill position"
	)
	default SkillPositionOption miningPosition()
	{
		return SkillPositionOption.SEVENTEEN;
	}

	@ConfigItem(
			keyName = "smithingPosition",
			name = "Smithing Position",
			description = "Set the smithing skill position"
	)
	default SkillPositionOption smithingPosition()
	{
		return SkillPositionOption.EIGHTEEN;
	}

	@ConfigItem(
			keyName = "fishingPosition",
			name = "Fishing Position",
			description = "Set the fishing skill position"
	)
	default SkillPositionOption fishingPosition()
	{
		return SkillPositionOption.NINETEEN;
	}

	@ConfigItem(
			keyName = "cookingPosition",
			name = "Cooking Position",
			description = "Set the cooking skill position"
	)
	default SkillPositionOption cookingPosition()
	{
		return SkillPositionOption.TWENTY;
	}

	@ConfigItem(
			keyName = "firemakingPosition",
			name = "Firemaking Position",
			description = "Set the firemaking skill position"
	)
	default SkillPositionOption firemakingPosition()
	{
		return SkillPositionOption.TWENTY_ONE;
	}

	@ConfigItem(
			keyName = "woodcuttingPosition",
			name = "Woodcutting Position",
			description = "Set the woodcutting skill position"
	)
	default SkillPositionOption woodcuttingPosition()
	{
		return SkillPositionOption.TWENTY_TWO;
	}

	@ConfigItem(
			keyName = "farmingPosition",
			name = "Farming Position",
			description = "Set the farming skill position"
	)
	default SkillPositionOption farmingPosition()
	{
		return SkillPositionOption.TWENTY_THREE;
	}

	@ConfigItem(
			keyName = "overallPosition",
			name = "Total Level Position",
			description = "Set the total level position"
	)
	default SkillPositionOption overallPosition() {
		return SkillPositionOption.TWENTY_FOUR;
	}

	@ConfigItem(
			keyName = "attackVisibility",
			name = "Attack Visibility",
			description = "Set the attack skill visibility"
	)
	default SkillVisibilityOption attackVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "strengthVisibility",
			name = "Strength Visibility",
			description = "Set the strength skill visibility"
	)
	default SkillVisibilityOption strengthVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "defenceVisibility",
			name = "Defence Visibility",
			description = "Set the defence skill visibility"
	)
	default SkillVisibilityOption defenceVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "rangedVisibility",
			name = "Ranged Visibility",
			description = "Set the ranged skill visibility"
	)
	default SkillVisibilityOption rangedVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "prayerVisibility",
			name = "Prayer Visibility",
			description = "Set the prayer skill visibility"
	)
	default SkillVisibilityOption prayerVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "magicVisibility",
			name = "Magic Visibility",
			description = "Set the magic skill visibility"
	)
	default SkillVisibilityOption magicVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "runecraftVisibility",
			name = "Runecraft Visibility",
			description = "Set the runecraft skill visibility"
	)
	default SkillVisibilityOption runecraftVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "constructionVisibility",
			name = "Construction Visibility",
			description = "Set the construction skill visibility"
	)
	default SkillVisibilityOption constructionVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "hitpointsPosition",
			name = "Hitpoints Position",
			description = "Set the hitpoints skill position"
	)
	default SkillPositionOption hitpointsPosition()
	{
		return SkillPositionOption.NINE;
	}

	@ConfigItem(
			keyName = "hitpointsVisibility",
			name = "Hitpoints Visibility",
			description = "Set the hitpoints skill visibility"
	)
	default SkillVisibilityOption hitpointsVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "agilityVisibility",
			name = "Agility Visibility",
			description = "Set the agility skill visibility"
	)
	default SkillVisibilityOption agilityVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "herbloreVisibility",
			name = "Herblore Visibility",
			description = "Set the herblore skill visibility"
	)
	default SkillVisibilityOption herbloreVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "thievingVisibility",
			name = "Thieving Visibility",
			description = "Set the thieving skill visibility"
	)
	default SkillVisibilityOption thievingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "craftingVisibility",
			name = "Crafting Visibility",
			description = "Set the crafting skill visibility"
	)
	default SkillVisibilityOption craftingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "fletchingVisibility",
			name = "Fletching Visibility",
			description = "Set the fletching skill visibility"
	)
	default SkillVisibilityOption fletchingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "slayerVisibility",
			name = "Slayer Visibility",
			description = "Set the slayer skill visibility"
	)
	default SkillVisibilityOption slayerVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "hunterVisibility",
			name = "Hunter Visibility",
			description = "Set the hunter skill visibility"
	)
	default SkillVisibilityOption hunterVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "miningVisibility",
			name = "Mining Visibility",
			description = "Set the mining skill visibility"
	)
	default SkillVisibilityOption miningVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "smithingVisibility",
			name = "Smithing Visibility",
			description = "Set the smithing skill visibility"
	)
	default SkillVisibilityOption smithingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "fishingVisibility",
			name = "Fishing Visibility",
			description = "Set the fishing skill visibility"
	)
	default SkillVisibilityOption fishingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "cookingVisibility",
			name = "Cooking Visibility",
			description = "Set the cooking skill visibility"
	)
	default SkillVisibilityOption cookingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "firemakingVisibility",
			name = "Firemaking Visibility",
			description = "Set the firemaking skill visibility"
	)
	default SkillVisibilityOption firemakingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "woodcuttingVisibility",
			name = "Woodcutting Visibility",
			description = "Set the woodcutting skill visibility"
	)
	default SkillVisibilityOption woodcuttingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "farmingVisibility",
			name = "Farming Visibility",
			description = "Set the farming skill visibility"
	)
	default SkillVisibilityOption farmingVisibility()
	{
		return SkillVisibilityOption.NORMAL;
	}

	@ConfigItem(
			keyName = "overallVisibility",
			name = "Total Level Visibility",
			description = "Set the total level visibility"
	)
	default SkillVisibilityOption overallVisibility() {
		return SkillVisibilityOption.NORMAL;
	}

	@Getter
	@AllArgsConstructor
	enum SkillPositionOption
	{
		ONE("Position 1", 1, 1),
		TWO("Position 2", 1,33),
		THREE("Position 3", 1,65),
		FOUR("Position 4", 1,97),
		FIVE("Position 5", 1,129),
		SIX("Position 6", 1,161),
		SEVEN("Position 7", 1,193),
		EIGHT("Position 8", 1,225),
		NINE("Position 9", 64,1),
		TEN("Position 10", 64,33),
		ELEVEN("Position 11", 64,65),
		TWELVE("Position 12", 64,97),
		THIRTEEN("Position 13", 64,129),
		FOURTEEN("Position 14", 64,161),
		FIFTEEN("Position 15", 64,193),
		SIXTEEN("Position 16", 64,225),
		SEVENTEEN("Position 17", 127,1),
		EIGHTEEN("Position 18", 127,33),
		NINETEEN("Position 19", 127,65),
		TWENTY("Position 20", 127,97),
		TWENTY_ONE("Position 21", 127,129),
		TWENTY_TWO("Position 22", 127,161),
		TWENTY_THREE("Position 23", 127,193),
		TWENTY_FOUR("Position 24", 127,225),
		;

		private final String name;
		private final int x;
		private final int y;

		@Override
		public String toString()
		{
			return name;
		}
	}

	@Getter
	@AllArgsConstructor
	enum SkillVisibilityOption
	{
		NORMAL("Normal"),
		HIDE("Hide"),
		FADE("Fade"),
		DARK("Dark"),
		;

		private final String name;

		@Override
		public String toString()
		{
			return name;
		}
	}
}
