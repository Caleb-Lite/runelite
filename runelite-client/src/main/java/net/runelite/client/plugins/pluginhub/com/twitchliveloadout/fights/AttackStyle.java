package net.runelite.client.plugins.pluginhub.com.twitchliveloadout.fights;

import lombok.Getter;
import net.runelite.api.Skill;

public enum AttackStyle
{
    ACCURATE("Accurate", CombatStyle.MELEE, Skill.ATTACK),
    AGGRESSIVE("Aggressive", CombatStyle.MELEE, Skill.STRENGTH),
    DEFENSIVE("Defensive", CombatStyle.MELEE, Skill.DEFENCE),
    CONTROLLED("Controlled", CombatStyle.MELEE, Skill.ATTACK, Skill.STRENGTH, Skill.DEFENCE),
    RANGING("Ranging", CombatStyle.RANGED, Skill.RANGED),
    LONGRANGE("Longrange", CombatStyle.RANGED, Skill.RANGED, Skill.DEFENCE),
    CASTING("Casting", CombatStyle.MAGIC, Skill.MAGIC),
    DEFENSIVE_CASTING("Defensive Casting", CombatStyle.MAGIC, Skill.MAGIC, Skill.DEFENCE),
    OTHER("Other", CombatStyle.MELEE);

    @Getter
    private final String name;
    @Getter
    private final CombatStyle combatStyle;
    @Getter
    private final Skill[] skills;

    AttackStyle(String name, CombatStyle combatStyle, Skill... skills)
    {
        this.name = name;
        this.combatStyle = combatStyle;
        this.skills = skills;
    }
}
