package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.live;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.ComputeInput;
import lombok.Value;

@Value
public class TargetedDpsChanged
{

	private final TargetedDps targetedDps;
	private final ComputeInput input;
	private final ComputeContext context;

}
