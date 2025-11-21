package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.equip;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.gearbonus.RevenantWeaponGearBonus;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelState;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelStateManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.StateVisibleComponent;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.component.StateBoundJCheckBox;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.util.ComputeUtil;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.BorderFactory;

@Singleton
public class InWildernessCheckBox extends StateBoundJCheckBox implements StateVisibleComponent
{

	private final RevenantWeaponGearBonus revenantWeaponGearBonus;

	@Inject
	public InWildernessCheckBox(PanelStateManager manager, RevenantWeaponGearBonus revenantWeaponGearBonus)
	{
		super(
			"In Wilderness",
			manager,
			PanelState::setInWilderness,
			PanelState::isInWilderness
		);
		this.revenantWeaponGearBonus = revenantWeaponGearBonus;

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

			setVisible(revenantWeaponGearBonus.isApplicable(ctx));
		});
	}
}
