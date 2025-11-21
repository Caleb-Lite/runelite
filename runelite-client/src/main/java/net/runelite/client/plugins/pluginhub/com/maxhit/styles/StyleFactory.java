package net.runelite.client.plugins.pluginhub.com.maxhit.styles;

import lombok.Setter;
import net.runelite.api.Client;
import net.runelite.api.EnumID;
import net.runelite.api.ParamID;
import net.runelite.api.StructComposition;
import lombok.extern.slf4j.Slf4j;
import static com.maxhit.styles.AttackStyle.OTHER;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.api.gameval.VarbitID;

@Slf4j
@Setter
public class StyleFactory {

	public static AttackType getAttackType(Client client)
	{
		int comMode = client.getVarpValue(VarPlayerID.COM_MODE);
		int weaponCategory = client.getVarbitValue(VarbitID.COMBAT_WEAPON_CATEGORY);
		int castingMode = client.getVarbitValue(VarbitID.AUTOCAST_DEFMODE);
		boolean isDefensiveCasting = (castingMode == 1 && comMode == 4);
		AttackType[] attackTypes = WeaponAttackType.getWeaponAttackType(weaponCategory).getAttackTypes();

		if (comMode < attackTypes.length)
		{
			return attackTypes[comMode];
		}
		else if (comMode == 4 || isDefensiveCasting)
		{
			return AttackType.MAGIC;
		}

		return AttackType.NONE;
	}

    private static AttackStyle[] getAttackStyles(Client client, int currentWeaponCategory)
    {
        int BLUE_MOON_SPEAR = 22;
        int KERIS_PARTISAN = 30;
        int weaponStyleEnum = client.getEnum(EnumID.WEAPON_STYLES).getIntValue(currentWeaponCategory);
        if (weaponStyleEnum == -1)
        {
            // Blue moon spear

            if (currentWeaponCategory == BLUE_MOON_SPEAR)
            {
                return new AttackStyle[]{
                        AttackStyle.ACCURATE,
                        AttackStyle.AGGRESSIVE,
                        null,
                        AttackStyle.DEFENSIVE,
                        AttackStyle.CASTING,
                        AttackStyle.DEFENSIVE_CASTING
                };
            }

            // Partisan
            if (currentWeaponCategory == KERIS_PARTISAN)
            {
                return new AttackStyle[]{
                        AttackStyle.ACCURATE,
                        AttackStyle.AGGRESSIVE,
                        AttackStyle.AGGRESSIVE,
                        AttackStyle.DEFENSIVE
                };
            }
            return new AttackStyle[0];
        }
        int[] weaponStyleStructs = client.getEnum(weaponStyleEnum).getIntVals();

        AttackStyle[] styles = new AttackStyle[weaponStyleStructs.length];
        int i = 0;
        for (int style : weaponStyleStructs)
        {
            StructComposition attackStyleStruct = client.getStructComposition(style);
            String attackStyleName = attackStyleStruct.getStringValue(ParamID.ATTACK_STYLE_NAME);

            AttackStyle attackStyle = AttackStyle.valueOf(attackStyleName.toUpperCase());
            if (attackStyle == AttackStyle.OTHER)
            {
                // "Other" is used for no style
                ++i;
                continue;
            }

            // "Defensive" is used for Defensive and also Defensive casting
            if (i == 5 && attackStyle == AttackStyle.DEFENSIVE)
            {
                attackStyle = AttackStyle.DEFENSIVE_CASTING;
            }

            styles[i++] = attackStyle;
        }
        return styles;
    }

	public static AttackStyle getAttackStyle(Client client)
	{
		int comMode = client.getVarpValue(VarPlayerID.COM_MODE);
		int weaponCategory = client.getVarbitValue(VarbitID.COMBAT_WEAPON_CATEGORY);
		int castingMode = client.getVarbitValue(VarbitID.AUTOCAST_DEFMODE);
		AttackStyle[] attackStyles = StyleFactory.getAttackStyles(client, weaponCategory);
		AttackStyle attackStyle = null;
		if (comMode < attackStyles.length)
		{
			// from script4525
			// Even though the client has 5 attack styles for Staffs, only attack styles 0-4 are used, with an additional
			// casting mode set for defensive casting
			if (comMode == 4)
				comMode += castingMode;

			attackStyle = attackStyles[comMode];

		}
		if (attackStyle == null)
			attackStyle = OTHER;
		return attackStyle;
	}

	//Combat type of equipped weapon (Melee, ranged, magic, other)
	public static CombatStyle getCombatType(AttackStyle attackStyle) {
		if (attackStyle == AttackStyle.ACCURATE ||
			attackStyle == AttackStyle.AGGRESSIVE ||
			attackStyle == AttackStyle.CONTROLLED ||
			attackStyle == AttackStyle.DEFENSIVE)
			return CombatStyle.MELEE;
		if (attackStyle.getName().contains("ang")) return CombatStyle.RANGED;
		if (attackStyle.getName().contains("Casting")) return CombatStyle.MAGE;
		log.debug("Null combat stlye: {}", attackStyle.getName());
		return null;
	}
}

  package com.maxhit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.Prayer;
import net.runelite.api.gameval.VarbitID;

// Based off of https://github.com/runelite/runelite/blob/master/runelite-client/src/main/java/net/runelite/client/plugins/prayer/PrayerType.java
@AllArgsConstructor
@Getter
public enum PrayerType
{
    // Melee
    BURST_OF_STRENGTH(Prayer.BURST_OF_STRENGTH),
    SUPERHUMAN_STRENGTH(Prayer.SUPERHUMAN_STRENGTH),
    ULTIMATE_STRENGTH(Prayer.ULTIMATE_STRENGTH),
    CHIVALRY(Prayer.CHIVALRY),
    PIETY(Prayer.PIETY),

    //Ranged
    SHARP_EYE(Prayer.SHARP_EYE),
    HAWK_EYE(Prayer.HAWK_EYE),
    EAGLE_EYE(Prayer.EAGLE_EYE)
            {
                @Override
                public boolean isEnabled(Client client)
                {
                    return !DEADEYE.isEnabled(client);
                }
            },
    DEADEYE(Prayer.DEADEYE)
            {
                @Override
                public boolean isEnabled(Client client)
                {
                    boolean inLms = client.getVarbitValue(VarbitID.BR_INGAME) != 0;
                    boolean deadeye = client.getVarbitValue(VarbitID.PRAYER_DEADEYE_UNLOCKED) != 0;
                    return deadeye && !inLms;
                }
            },
    RIGOUR(Prayer.RIGOUR),

    //Magic
    MYSTIC_WILL(Prayer.MYSTIC_WILL),
    MYSTIC_LORE(Prayer.MYSTIC_LORE),
    MYSTIC_MIGHT(Prayer.MYSTIC_MIGHT)
            {
                @Override
                public boolean isEnabled(Client client)
                {
                    return !MYSTIC_VIGOUR.isEnabled(client);
                }
            },
    MYSTIC_VIGOUR(Prayer.MYSTIC_VIGOUR)
            {
                @Override
                public boolean isEnabled(Client client)
                {
                    boolean inLms = client.getVarbitValue(VarbitID.BR_INGAME) != 0;
                    boolean vigour = client.getVarbitValue(VarbitID.PRAYER_MYSTIC_VIGOUR_UNLOCKED) != 0;
                    return vigour && !inLms;
                }
            },
    AUGURY(Prayer.AUGURY),

    // Leagues
    RP_ANCIENT_STRENGTH(Prayer.RP_ANCIENT_STRENGTH),
    RP_ANCIENT_SIGHT(Prayer.RP_ANCIENT_SIGHT),
    RP_TRINITAS(Prayer.RP_TRINITAS),
    RP_DECIMATE(Prayer.RP_DECIMATE),
    RP_ANNIHILATE(Prayer.RP_ANNIHILATE),
    RP_VAPORISE(Prayer.RP_VAPORISE),
    ;

    private final Prayer prayer;

    public boolean isEnabled(Client client)
    {
        return true;
    }

    public boolean isActive(Client client)
    {
        return client.isPrayerActive(prayer) && isEnabled(client);
    }
}
