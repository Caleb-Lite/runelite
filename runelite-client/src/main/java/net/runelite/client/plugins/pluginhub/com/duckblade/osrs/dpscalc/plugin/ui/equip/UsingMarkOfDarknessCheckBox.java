package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.equip;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.gearbonus.MageDemonbaneGearBonus;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelState;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelStateManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.StateVisibleComponent;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.component.StateBoundJCheckBox;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.util.ComputeUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.BorderFactory;

@Singleton
public class UsingMarkOfDarknessCheckBox extends StateBoundJCheckBox implements StateVisibleComponent
{

	private final MageDemonbaneGearBonus mageDemonbaneGearBonus;

	@Inject
	public UsingMarkOfDarknessCheckBox(PanelStateManager manager, MageDemonbaneGearBonus mageDemonbaneGearBonus)
	{
		super(
			"Using Mark of Darkness",
			manager,
			PanelState::setUsingMarkOfDarkness,
			PanelState::isUsingMarkOfDarkness
		);
		this.mageDemonbaneGearBonus = mageDemonbaneGearBonus;

		setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		setValue(false);
		setEditable(true);
		setVisible(false);
	}

	@Override
	public void updateVisibility()
	{
		ComputeUtil.computeSilent(() ->
		{
			ComputeContext ctx = new ComputeContext();
			ctx.put(ComputeInputs.ATTACKER_ITEMS, getState().getAttackerItems());
			ctx.put(ComputeInputs.ATTACK_STYLE, getState().getAttackStyle());
			ctx.put(ComputeInputs.SPELL, getState().getSpell());

			setVisible(mageDemonbaneGearBonus.isApplicable(ctx));
		});
	}
}
