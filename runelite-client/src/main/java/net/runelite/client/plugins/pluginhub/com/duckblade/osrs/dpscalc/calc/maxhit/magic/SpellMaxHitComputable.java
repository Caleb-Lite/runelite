package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.maxhit.magic;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.WeaponComputable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.AttackStyle;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.AttackType;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.WeaponCategory;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SpellMaxHitComputable implements MagicMaxHitComputable
{

	private final WeaponComputable weaponComputable;
	private final SpellcastingMaxHitBonusComputable spellcastingMaxHitBonusComputable;

	@Override
	public boolean isApplicable(ComputeContext context)
	{
		AttackStyle attackStyle = context.get(ComputeInputs.ATTACK_STYLE);
		if (attackStyle.getAttackType() != AttackType.MAGIC)
		{
			return false;
		}

		if (attackStyle.isManualCast())
		{
			return true;
		}

		WeaponCategory category = context.get(weaponComputable).getWeaponCategory();
		return category != WeaponCategory.SALAMANDER && category != WeaponCategory.POWERED_STAFF;
	}

	@Override
	public Integer compute(ComputeContext context)
	{
		int baseMaxHit = context.get(ComputeInputs.SPELL).getBaseMaxHit();
		int spellBonus = context.get(spellcastingMaxHitBonusComputable);
		return baseMaxHit + spellBonus;
	}
}
