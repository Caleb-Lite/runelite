package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.Computable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.maxhit.BaseMaxHitComputable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class BaseHitDptComputable implements Computable<Double>
{

	private final HitChanceComputable hitChanceComputable;
	private final BaseMaxHitComputable baseMaxHitComputable;
	private final AttackSpeedComputable attackSpeedComputable;

	@Override
	public Double compute(ComputeContext context)
	{
		double hitChance = context.get(hitChanceComputable);
		int maxHit = context.get(baseMaxHitComputable);
		int attackSpeed = context.get(attackSpeedComputable);

		return byComponents(hitChance, maxHit, attackSpeed);
	}

	public static double byComponents(double hitChance, int maxHit, int attackSpeed)
	{
		return (hitChance * maxHit) / (2.0 * attackSpeed);
	}

}
