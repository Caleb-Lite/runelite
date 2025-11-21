package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.testutil;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.AttackStyle;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.AttackType;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.CombatStyle;

public class AttackStyleUtil
{

	public static AttackStyle ofAttackType(AttackType attackType)
	{
		return AttackStyle.builder()
			.attackType(attackType)
			.isManualCast(false)
			.build();
	}

	public static AttackStyle ofCombatStyle(CombatStyle combatStyle)
	{
		return AttackStyle.builder()
			.combatStyle(combatStyle)
			.build();
	}

}
