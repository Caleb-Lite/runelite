package net.runelite.client.plugins.pluginhub.io.robrichardson.skillsorganizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Skill;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum SkillOrganizerData {
    ATTACK(Skill.ATTACK, SkillsOrganizerConfig.SkillPositionOption.ONE, SkillsOrganizerConfig::attackPosition, SkillsOrganizerConfig::attackVisibility),
    STRENGTH(Skill.STRENGTH, SkillsOrganizerConfig.SkillPositionOption.TWO, SkillsOrganizerConfig::strengthPosition, SkillsOrganizerConfig::strengthVisibility),
    DEFENCE(Skill.DEFENCE, SkillsOrganizerConfig.SkillPositionOption.THREE, SkillsOrganizerConfig::defencePosition, SkillsOrganizerConfig::defenceVisibility),
    RANGED(Skill.RANGED, SkillsOrganizerConfig.SkillPositionOption.FOUR, SkillsOrganizerConfig::rangedPosition, SkillsOrganizerConfig::rangedVisibility),
    PRAYER(Skill.PRAYER, SkillsOrganizerConfig.SkillPositionOption.FIVE, SkillsOrganizerConfig::prayerPosition, SkillsOrganizerConfig::prayerVisibility),
    MAGIC(Skill.MAGIC, SkillsOrganizerConfig.SkillPositionOption.SIX, SkillsOrganizerConfig::magicPosition, SkillsOrganizerConfig::magicVisibility),
    RUNECRAFT(Skill.RUNECRAFT, SkillsOrganizerConfig.SkillPositionOption.SEVEN, SkillsOrganizerConfig::runecraftPosition, SkillsOrganizerConfig::runecraftVisibility),
    CONSTRUCTION(Skill.CONSTRUCTION, SkillsOrganizerConfig.SkillPositionOption.EIGHT, SkillsOrganizerConfig::constructionPosition, SkillsOrganizerConfig::constructionVisibility),
    HITPOINTS(Skill.HITPOINTS, SkillsOrganizerConfig.SkillPositionOption.NINE, SkillsOrganizerConfig::hitpointsPosition, SkillsOrganizerConfig::hitpointsVisibility),
    AGILITY(Skill.AGILITY, SkillsOrganizerConfig.SkillPositionOption.TEN, SkillsOrganizerConfig::agilityPosition, SkillsOrganizerConfig::agilityVisibility),
    HERBLORE(Skill.HERBLORE, SkillsOrganizerConfig.SkillPositionOption.ELEVEN, SkillsOrganizerConfig::herblorePosition, SkillsOrganizerConfig::herbloreVisibility),
    THIEVING(Skill.THIEVING, SkillsOrganizerConfig.SkillPositionOption.TWELVE, SkillsOrganizerConfig::thievingPosition, SkillsOrganizerConfig::thievingVisibility),
    CRAFTING(Skill.CRAFTING, SkillsOrganizerConfig.SkillPositionOption.THIRTEEN, SkillsOrganizerConfig::craftingPosition, SkillsOrganizerConfig::craftingVisibility),
    FLETCHING(Skill.FLETCHING, SkillsOrganizerConfig.SkillPositionOption.FOURTEEN, SkillsOrganizerConfig::fletchingPosition, SkillsOrganizerConfig::fletchingVisibility),
    SLAYER(Skill.SLAYER, SkillsOrganizerConfig.SkillPositionOption.FIFTEEN, SkillsOrganizerConfig::slayerPosition, SkillsOrganizerConfig::slayerVisibility),
    HUNTER(Skill.HUNTER, SkillsOrganizerConfig.SkillPositionOption.SIXTEEN, SkillsOrganizerConfig::hunterPosition, SkillsOrganizerConfig::hunterVisibility),
    MINING(Skill.MINING, SkillsOrganizerConfig.SkillPositionOption.SEVENTEEN, SkillsOrganizerConfig::miningPosition, SkillsOrganizerConfig::miningVisibility),
    SMITHING(Skill.RANGED, SkillsOrganizerConfig.SkillPositionOption.EIGHTEEN, SkillsOrganizerConfig::smithingPosition, SkillsOrganizerConfig::smithingVisibility),
    FISHING(Skill.FISHING, SkillsOrganizerConfig.SkillPositionOption.NINETEEN, SkillsOrganizerConfig::fishingPosition, SkillsOrganizerConfig::fishingVisibility),
    COOKING(Skill.COOKING, SkillsOrganizerConfig.SkillPositionOption.TWENTY, SkillsOrganizerConfig::cookingPosition, SkillsOrganizerConfig::cookingVisibility),
    FIREMAKING(Skill.FIREMAKING, SkillsOrganizerConfig.SkillPositionOption.TWENTY_ONE, SkillsOrganizerConfig::firemakingPosition, SkillsOrganizerConfig::firemakingVisibility),
    WOODCUTTING(Skill.WOODCUTTING, SkillsOrganizerConfig.SkillPositionOption.TWENTY_TWO, SkillsOrganizerConfig::woodcuttingPosition, SkillsOrganizerConfig::woodcuttingVisibility),
    FARMING(Skill.FARMING, SkillsOrganizerConfig.SkillPositionOption.TWENTY_THREE, SkillsOrganizerConfig::farmingPosition, SkillsOrganizerConfig::farmingVisibility),
    OVERALL(Skill.OVERALL, SkillsOrganizerConfig.SkillPositionOption.TWENTY_FOUR, SkillsOrganizerConfig::overallPosition, SkillsOrganizerConfig::overallVisibility),
    ;

    private final Skill skill;
    private final SkillsOrganizerConfig.SkillPositionOption defaultPosition;
    private final Function<SkillsOrganizerConfig, SkillsOrganizerConfig.SkillPositionOption> skillPositionConfig;
    private final Function<SkillsOrganizerConfig, SkillsOrganizerConfig.SkillVisibilityOption> skillVisibilityConfig;

    static SkillOrganizerData get(int idx) {
        if (idx < 0 || idx >= values().length) {
            return null;
        }
        return values()[idx];
    }
}
