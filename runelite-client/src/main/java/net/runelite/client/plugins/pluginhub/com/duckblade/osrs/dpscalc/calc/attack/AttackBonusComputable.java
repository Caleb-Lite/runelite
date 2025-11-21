package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.attack;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.AttackerItemStatsComputable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.Computable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.ItemStats;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.AttackStyle;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AttackBonusComputable implements Computable<Integer>
{

	private final AttackerItemStatsComputable attackerItemStatsComputable;

	@Override
	public Integer compute(ComputeContext context)
	{
		ItemStats attackerItemStats = context.get(attackerItemStatsComputable);
		AttackStyle attackStyle = context.get(ComputeInputs.ATTACK_STYLE);
		switch (attackStyle.getAttackType())
		{
			case STAB:
				return attackerItemStats.getAccuracyStab();
			case SLASH:
				return attackerItemStats.getAccuracySlash();
			case CRUSH:
				return attackerItemStats.getAccuracyCrush();
			case RANGED:
				return attackerItemStats.getAccuracyRanged();
			default:
				return attackerItemStats.getAccuracyMagic();
		}
	}
}
