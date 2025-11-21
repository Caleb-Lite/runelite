package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.Computable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.ItemStats;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.runelite.api.EquipmentInventorySlot;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
// utility class helps with checking for item requirements
// without having to null-check
public class EquipmentItemIdsComputable implements Computable<Map<EquipmentInventorySlot, Integer>>
{

	@Override
	public Map<EquipmentInventorySlot, Integer> compute(ComputeContext context)
	{
		Map<EquipmentInventorySlot, ItemStats> equipment = context.get(ComputeInputs.ATTACKER_ITEMS);
		return Arrays.stream(EquipmentInventorySlot.values())
			.collect(Collectors.toMap(
				k -> k,
				k -> equipment.getOrDefault(k, ItemStats.EMPTY).getItemId()
			));
	}

}
