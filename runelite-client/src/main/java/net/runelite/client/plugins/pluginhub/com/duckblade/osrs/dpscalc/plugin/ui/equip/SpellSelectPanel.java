package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.equip;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeContext;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.maxhit.magic.SpellMaxHitComputable;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.model.Spell;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelState;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.PanelStateManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.StateVisibleComponent;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state.component.StateBoundJComboBox;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.util.ComputeUtil;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SpellSelectPanel extends StateBoundJComboBox<Spell> implements StateVisibleComponent
{

	private final SpellMaxHitComputable spellMaxHitComputable;

	@Inject
	public SpellSelectPanel(PanelStateManager manager, SpellMaxHitComputable spellMaxHitComputable)
	{
		super(
			Arrays.asList(Spell.values()),
			Spell::getDisplayName,
			"Spell",
			manager,
			PanelState::setSpell,
			PanelState::getSpell
		);
		this.spellMaxHitComputable = spellMaxHitComputable;

		setAlignmentX(CENTER_ALIGNMENT);
		setVisible(false);
		addBottomPadding(10);
	}

	@Override
	public void updateVisibility()
	{
		Boolean visibleOpt = ComputeUtil.tryCompute(() ->
		{
			ComputeContext context = new ComputeContext();
			context.put(ComputeInputs.ATTACKER_ITEMS, getState().getAttackerItems());
			context.put(ComputeInputs.ATTACK_STYLE, getState().getAttackStyle());

			return spellMaxHitComputable.isApplicable(context);
		});

		boolean visible = visibleOpt != null && visibleOpt;
		setVisible(visible);
		if (!visible)
		{
			setValue(null);
		}
	}
}
