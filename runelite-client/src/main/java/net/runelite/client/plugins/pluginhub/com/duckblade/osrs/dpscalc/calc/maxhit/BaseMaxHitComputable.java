package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.maxhit;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.Computable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.maxhit.limiters.MaxHitLimitComputable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class BaseMaxHitComputable implements Computable<Integer>
{

	private final PreLimitBaseMaxHitComputable preLimitBaseMaxHitComputable;
	private final MaxHitLimitComputable maxHitLimitComputable;

	@Override
	public Integer compute(ComputeContext context)
	{
		return maxHitLimitComputable.coerce(context.get(preLimitBaseMaxHitComputable), context);
	}

}
