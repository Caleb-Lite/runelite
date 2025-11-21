package net.runelite.client.plugins.pluginhub.org.RaidSpellChecker;

import java.util.List;

public class SpellDefinition
{
    private final String name;
    private final List<SpellRuneRequirement> runeRequirements;

    public SpellDefinition(String name, List<SpellRuneRequirement> runeRequirements)
    {
        this.name = name;
        this.runeRequirements = runeRequirements;
    }

    public String getName()
    {
        return name;
    }

    public List<SpellRuneRequirement> getRuneRequirements()
    {
        return runeRequirements;
    }
}