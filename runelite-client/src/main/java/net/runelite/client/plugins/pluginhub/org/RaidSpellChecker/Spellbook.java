package net.runelite.client.plugins.pluginhub.org.RaidSpellChecker;

import net.runelite.api.ItemID;
import java.util.List;

public class Spellbook
{
    public static final String AIR_SPELL = "Air Spell";
    public static final String EARTH_SPELL = "Earth Spell";
    public static final String FIRE_SPELL = "Fire Spell";
    public static final String WATER_SPELL = "Water Spell";
    public static final String DEMONBANE_SPELL = "Demonbane";
    public static final String GRASP_SPELL = "Grasp";
    public static final String THRALL_SPELL = "Thralls";
    public static final String DEATH_CHARGE_SPELL = "Death Charge";
    public static final String MARK_OF_DARKNESS_SPELL = "Mark of Darkness";
    public static final String SMOKE_SPELL = "Smoke Spell";
    public static final String SHADOW_SPELL = "Shadow Spell";
    public static final String BLOOD_SPELL = "Blood Spell";
    public static final String ICE_SPELL = "Ice Spell";
    public static final String CURE_SPELL = "Cure Spell";
    public static final String POT_SHARE = "Pot Share";
    public static final String VENGE_SPELL = "Venge";
    public static final String HUMIDIFY_SPELL = "Humidify";
    public static final String WRONG_SPELLBOOK = "WRONG SPELLBOOK";
    public static final String MISSING_RUNES = "MISSING RUNES";
    public static final String NO_BOOK_OF_THE_DEAD = "NO BOOK OF THE DEAD";

    // === ELEMENTAL GROUPS === //
    public static final List<SpellDefinition> AIR_SPELLS = List.of(
            new SpellDefinition("Wind Strike", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 1),
                    new SpellRuneRequirement(ItemID.MIND_RUNE, 1)
            )),
            new SpellDefinition("Wind Bolt", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 2),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 1)
            )),
            new SpellDefinition("Wind Blast", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 3),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 1)
            )),
            new SpellDefinition("Wind Wave", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 5),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 1)
            )),
            new SpellDefinition("Wind Surge", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 7),
                    new SpellRuneRequirement(ItemID.WRATH_RUNE, 1)
            ))
    );

    public static final List<SpellDefinition> WATER_SPELLS = List.of(
            new SpellDefinition("Water Strike", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 1),
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 1),
                    new SpellRuneRequirement(ItemID.MIND_RUNE, 1)
            )),
            new SpellDefinition("Water Bolt", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 2),
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 2),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 1)
            )),
            new SpellDefinition("Water Blast", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 3),
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 3),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 1)
            )),
            new SpellDefinition("Water Wave", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 5),
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 7),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 1)
            )),
            new SpellDefinition("Water Surge", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 7),
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 10),
                    new SpellRuneRequirement(ItemID.WRATH_RUNE, 1)
            ))
    );

    public static final List<SpellDefinition> EARTH_SPELLS = List.of(
            new SpellDefinition("Earth Strike", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 1),
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 2),
                    new SpellRuneRequirement(ItemID.MIND_RUNE, 1)
            )),
            new SpellDefinition("Earth Bolt", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 2),
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 3),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 1)
            )),
            new SpellDefinition("Earth Blast", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 3),
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 4),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 1)
            )),
            new SpellDefinition("Earth Wave", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 5),
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 7),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 1)
            )),
            new SpellDefinition("Earth Surge", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 7),
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 10),
                    new SpellRuneRequirement(ItemID.WRATH_RUNE, 1)
            ))
    );

    public static final List<SpellDefinition> FIRE_SPELLS = List.of(
            new SpellDefinition("Fire Strike", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 2),
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 3),
                    new SpellRuneRequirement(ItemID.MIND_RUNE, 1)
            )),
            new SpellDefinition("Fire Bolt", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 3),
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 4),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 1)
            )),
            new SpellDefinition("Fire Blast", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 4),
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 5),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 1)
            )),
            new SpellDefinition("Fire Wave", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 5),
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 7),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 1)
            )),
            new SpellDefinition("Fire Surge", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 7),
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 10),
                    new SpellRuneRequirement(ItemID.WRATH_RUNE, 1)
            ))
    );

    // === GRASP SPELLS === //
    public static final List<SpellDefinition> GRASP_SPELLS = List.of(
            new SpellDefinition("Ghostly Grasp", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 4),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 1)
            )),
            new SpellDefinition("Skeletal Grasp", List.of(
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 8),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 1)
            )),
            new SpellDefinition("Undead Grasp", List.of(
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 12),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 1)
            ))
    );

    // === DEMONBANE SPELLS === //
    public static final List<SpellDefinition> DEMONBANE_SPELLS = List.of(
            new SpellDefinition("Inferior Demonbane", List.of(
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 3),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 1)
            )),
            new SpellDefinition("Superior Demonbane", List.of(
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 5),
                    new SpellRuneRequirement(ItemID.SOUL_RUNE, 1)
            )),
            new SpellDefinition("Dark Demonbane", List.of(
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 7),
                    new SpellRuneRequirement(ItemID.SOUL_RUNE, 2)
            ))
    );

    // === THRALL SPELLS === //
    public static final List<SpellDefinition> THRALL_SPELLS = List.of(
            new SpellDefinition("Lesser Ghost", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 10),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.MIND_RUNE, 5),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Lesser Skeleton", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 10),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.MIND_RUNE, 5),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Lesser Zombie", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 10),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.MIND_RUNE, 5),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Superior Ghost", List.of(
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 10),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 5),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Superior Skeleton", List.of(
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 10),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 5),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Superior Zombie", List.of(
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 10),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 5),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Greater Ghost", List.of(
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 10),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 5),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Greater Skeleton", List.of(
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 10),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 5),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            )),
            new SpellDefinition("Greater Zombie", List.of(
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 10),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 5),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
                    new SpellRuneRequirement(ItemID.BOOK_OF_THE_DEAD, 1)
            ))
    );

    // === MARK OF DARKNESS === //
    public static final SpellDefinition MARK_OF_DARKNESS = new SpellDefinition("Mark of Darkness", List.of(
            new SpellRuneRequirement(ItemID.COSMIC_RUNE, 1),
            new SpellRuneRequirement(ItemID.SOUL_RUNE, 1)
    ));

    // === DEATH CHARGE === //
    public static final SpellDefinition DEATH_CHARGE = new SpellDefinition("Death Charge", List.of(
            new SpellRuneRequirement(ItemID.BLOOD_RUNE, 1),
            new SpellRuneRequirement(ItemID.DEATH_RUNE, 1),
            new SpellRuneRequirement(ItemID.SOUL_RUNE, 1)
    ));

    // === SMOKE SPELLS === //
    public static final List<SpellDefinition> SMOKE_SPELLS = List.of(
            new SpellDefinition("Smoke Burst", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 2),
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 2),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 4),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 2)
            )),
            new SpellDefinition("Smoke Barrage", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 4),
                    new SpellRuneRequirement(ItemID.FIRE_RUNE, 4),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 2),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 4)
            ))
    );

    // === SHADOW SPELLS === //
    public static final List<SpellDefinition> SHADOW_SPELLS = List.of(
            new SpellDefinition("Shadow Burst", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 1),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 4),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 2),
                    new SpellRuneRequirement(ItemID.SOUL_RUNE, 2)
            )),
            new SpellDefinition("Shadow Barrage", List.of(
                    new SpellRuneRequirement(ItemID.AIR_RUNE, 4),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 2),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 4),
                    new SpellRuneRequirement(ItemID.SOUL_RUNE, 3)
            ))
    );

    // === BLOOD SPELLS === //
    public static final List<SpellDefinition> BLOOD_SPELLS = List.of(
            new SpellDefinition("Blood Burst", List.of(
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 2),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 4),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 2)
            )),
            new SpellDefinition("Blood Barrage", List.of(
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 4),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 4),
                    new SpellRuneRequirement(ItemID.SOUL_RUNE, 1)
            ))
    );

    // === ICE SPELLS === //
    public static final List<SpellDefinition> ICE_SPELLS = List.of(
            new SpellDefinition("Ice Burst", List.of(
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 4),
                    new SpellRuneRequirement(ItemID.CHAOS_RUNE, 4),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 2)
            )),
            new SpellDefinition("Ice Barrage", List.of(
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 6),
                    new SpellRuneRequirement(ItemID.BLOOD_RUNE, 2),
                    new SpellRuneRequirement(ItemID.DEATH_RUNE, 4)
            ))
    );

    // === CURE SPELLS === //
    public static final List<SpellDefinition> CURE_SPELLS = List.of(
            new SpellDefinition("Cure Other", List.of(
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 10),
                    new SpellRuneRequirement(ItemID.ASTRAL_RUNE, 1),
                    new SpellRuneRequirement(ItemID.LAW_RUNE, 1)
            )),
            new SpellDefinition("Cure Me", List.of(
                    new SpellRuneRequirement(ItemID.ASTRAL_RUNE, 2),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 2),
                    new SpellRuneRequirement(ItemID.LAW_RUNE, 1)
            )),
            new SpellDefinition("Cure Group", List.of(
                    new SpellRuneRequirement(ItemID.ASTRAL_RUNE, 2),
                    new SpellRuneRequirement(ItemID.COSMIC_RUNE, 2),
                    new SpellRuneRequirement(ItemID.LAW_RUNE, 2)
            ))
    );

    // === POTION SHARE SPELLS === //
    public static final List<SpellDefinition> POT_SHARE_SPELLS = List.of(
            new SpellDefinition("Stat Restore Pot Share", List.of(
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 10),
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 10),
                    new SpellRuneRequirement(ItemID.ASTRAL_RUNE, 2)
            )),
            new SpellDefinition("Boost Potion Share", List.of(
                    new SpellRuneRequirement(ItemID.EARTH_RUNE, 12),
                    new SpellRuneRequirement(ItemID.WATER_RUNE, 10),
                    new SpellRuneRequirement(ItemID.ASTRAL_RUNE, 3)
            ))
    );

    // === VENGEANCE === //
    public static final SpellDefinition VENGEANCE = new SpellDefinition("Vengeance", List.of(
            new SpellRuneRequirement(ItemID.EARTH_RUNE, 10),
            new SpellRuneRequirement(ItemID.ASTRAL_RUNE, 4),
            new SpellRuneRequirement(ItemID.DEATH_RUNE, 2)
    ));

    // === HUMIDIFY === //
    public static final SpellDefinition HUMIDIFY = new SpellDefinition("Humidify", List.of(
            new SpellRuneRequirement(ItemID.FIRE_RUNE, 1),
            new SpellRuneRequirement(ItemID.WATER_RUNE, 3),
            new SpellRuneRequirement(ItemID.ASTRAL_RUNE, 1)
    ));
}