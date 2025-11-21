package net.runelite.client.plugins.pluginhub.com.xpdrops.attackstyles;

import net.runelite.client.plugins.pluginhub.com.xpdrops.Skill;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.StructComposition;

public enum AttackStyle
{
	ACCURATE("Accurate", Skill.ATTACK),
	AGGRESSIVE("Aggressive", Skill.STRENGTH),
	DEFENSIVE("Defensive", Skill.DEFENCE),
	CONTROLLED("Controlled", Skill.ATTACK, Skill.STRENGTH, Skill.DEFENCE),
	RANGING("Ranging", Skill.RANGED),
	LONGRANGE("Longrange", Skill.RANGED, Skill.DEFENCE),
	CASTING("Casting", Skill.MAGIC),
	DEFENSIVE_CASTING("Defensive Casting", Skill.MAGIC, Skill.DEFENCE),
	OTHER("Other");

	@Getter
	private final String name;
	@Getter
	private final Skill[] skills;

	private static final int WEAPON_STYLES = 3908; // EnumID.WEAPON_STYLES
	private static final int ATTACK_STYLE_NAME = 1407; // ParamID.ATTACK_STYLE_NAME

	AttackStyle(String name, Skill... skills)
	{
		this.name = name;
		this.skills = skills;
	}

	// Duplicated from RuneLite's AttackStylesPlugin.java
	public static AttackStyle[] getAttackStylesForWeaponType(Client client, int weaponType)
	{
		// from script4525
		int weaponStyleEnum = client.getEnum(WEAPON_STYLES).getIntValue(weaponType);
		if (weaponStyleEnum == -1)
		{
			// Blue moon spear
			if (weaponType == 22)
			{
				return new AttackStyle[]{
					AttackStyle.ACCURATE, AttackStyle.AGGRESSIVE, null, DEFENSIVE, CASTING, DEFENSIVE_CASTING
				};
			}

			if (weaponType == 30)
			{
				// Partisan
				return new AttackStyle[]{
					AttackStyle.ACCURATE, AttackStyle.AGGRESSIVE, AttackStyle.AGGRESSIVE, DEFENSIVE
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
			String attackStyleName = attackStyleStruct.getStringValue(ATTACK_STYLE_NAME);

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

			// Replaces defensive with defensive casting for powered staves
			if (i == 3 && attackStyle == AttackStyle.DEFENSIVE && weaponType == 24)
			{
				attackStyle = AttackStyle.DEFENSIVE_CASTING;
			}

			styles[i++] = attackStyle;
		}
		return styles;
	}
}
