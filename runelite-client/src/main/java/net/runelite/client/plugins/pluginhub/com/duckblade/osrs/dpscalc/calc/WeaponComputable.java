package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.Computable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.ItemStats;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.runelite.api.EquipmentInventorySlot;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class WeaponComputable implements Computable<ItemStats>
{

	@Override
	public ItemStats compute(ComputeContext context)
	{
		Map<EquipmentInventorySlot, ItemStats> items = context.get(ComputeInputs.ATTACKER_ITEMS);
		return items.getOrDefault(EquipmentInventorySlot.WEAPON, ItemStats.EMPTY);
	}

}
