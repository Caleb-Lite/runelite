package net.runelite.client.plugins.pluginhub.com.cyneris.predictedhit;

/**
 * An enumeration of skills that a player can level.
 */
public enum Skill
{
    ATTACK("Attack"),
    DEFENCE("Defence"),
    STRENGTH("Strength"),
    HITPOINTS("Hitpoints"),
    RANGED("Ranged"),
    PRAYER("Prayer"),
    MAGIC("Magic"),
    COOKING("Cooking"),
    WOODCUTTING("Woodcutting"),
    FLETCHING("Fletching"),
    FISHING("Fishing"),
    FIREMAKING("Firemaking"),
    CRAFTING("Crafting"),
    SMITHING("Smithing"),
    MINING("Mining"),
    HERBLORE("Herblore"),
    AGILITY("Agility"),
    THIEVING("Thieving"),
    SLAYER("Slayer"),
    FARMING("Farming"),
    RUNECRAFT("Runecraft"),
    HUNTER("Hunter"),
    CONSTRUCTION("Construction"),
    /**
     * The level of all skills added together.
     */
    OVERALL("Overall");

    private final String name;

    Skill(String name)
    {
        this.name = name;
    }

    /**
     * Gets the name of the skill.
     *
     * @return the skill name
     */
    public String getName()
    {
        return name;
    }

    public static Skill fromSkill(net.runelite.api.Skill skill) {
        return skill.ordinal() < Skill.values().length ? Skill.values()[skill.ordinal()] : null;
//		return Skill.valueOf(skill.getName().toUpperCase());
    }

    public net.runelite.api.Skill toSkill() {
        return ordinal() < net.runelite.api.Skill.values().length ? net.runelite.api.Skill.values()[ordinal()] : null;
//		return net.runelite.api.Skill.valueOf(getName().toUpperCase());
    }
}