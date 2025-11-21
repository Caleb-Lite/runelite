package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.equip;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.ammo.BlowpipeDartsItemStatsComputable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.ItemStats;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.config.BlowpipeDarts;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.wiki.ItemStatsProvider;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelState;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelStateManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.StateVisibleComponent;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.component.StateBoundJComboBox;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.util.ComputeUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BlowpipeDartsSelectPanel extends StateBoundJComboBox<ItemStats> implements StateVisibleComponent
{

	private final BlowpipeDartsItemStatsComputable blowpipeDartsItemStatsComputable;

	private static List<ItemStats> getDarts(ItemStatsProvider itemStatsProvider)
	{
		return Arrays.stream(BlowpipeDarts.values())
			.map(BlowpipeDarts::getItemId)
			.map(itemStatsProvider::getById)
			.collect(Collectors.toList());
	}

	@Inject
	public BlowpipeDartsSelectPanel(PanelStateManager manager, ItemStatsProvider itemStatsProvider, BlowpipeDartsItemStatsComputable blowpipeDartsItemStatsComputable)
	{
		super(
			getDarts(itemStatsProvider),
			ItemStats::getName,
			"Blowpipe Darts",
			manager,
			PanelState::setBlowpipeDarts,
			PanelState::getBlowpipeDarts
		);
		this.blowpipeDartsItemStatsComputable = blowpipeDartsItemStatsComputable;

		setAlignmentX(CENTER_ALIGNMENT);
		setVisible(false);
		addBottomPadding(10);
	}

	public void updateVisibility()
	{
		ComputeUtil.computeSilent(() ->
		{
			ComputeContext ctx = new ComputeContext();
			ctx.put(ComputeInputs.ATTACKER_ITEMS, getState().getAttackerItems());
			ctx.put(ComputeInputs.ATTACK_STYLE, getState().getAttackStyle());

			setVisible(blowpipeDartsItemStatsComputable.isApplicable(ctx));
		});
	}
}
