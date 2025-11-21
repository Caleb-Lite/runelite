package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.maxhit;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.Computable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.gearbonus.AggregateGearBonusesComputable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.AttackType;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.AttackStyle;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MeleeRangedMaxHitComputable implements Computable<Integer>
{

	private final MeleeEffectiveStrengthLevelComputable meleeStrengthComputable;
	private final RangedEffectiveStrengthLevelComputable rangedStrengthComputable;
	private final StrengthBonusComputable strengthBonusComputable;
	private final AggregateGearBonusesComputable aggregateGearBonusesComputable;

	@Override
	public Integer compute(ComputeContext context)
	{
		AttackStyle attackStyle = context.get(ComputeInputs.ATTACK_STYLE);
		int effectiveStrength = attackStyle.getAttackType() == AttackType.RANGED
			? context.get(rangedStrengthComputable)
			: context.get(meleeStrengthComputable);

		int strengthBonus = context.get(strengthBonusComputable).intValue();
		int baseMaxHit = (effectiveStrength * (strengthBonus + 64) + 320) / 640;

		double strengthGearBonus = context.get(aggregateGearBonusesComputable).getStrengthBonus();
		return (int) (baseMaxHit * strengthGearBonus);
	}
}
