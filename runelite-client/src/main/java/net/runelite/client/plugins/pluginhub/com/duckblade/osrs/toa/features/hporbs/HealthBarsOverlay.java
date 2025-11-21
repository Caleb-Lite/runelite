package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.hporbs;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.TombsOfAmascutConfig;
import com.google.common.base.Strings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ProgressBarComponent;

public class HealthBarsOverlay extends OverlayPanel
{

	@Inject
	private Client client;

	@Inject
	private TombsOfAmascutConfig config;

	@Inject
	public HealthBarsOverlay(Client client, TombsOfAmascutConfig config)
	{
		this.client = client;
		this.config = config;

		panelComponent.setGap(new Point(0, 3));
		if (getPreferredPosition() == null)
		{
			setPreferredPosition(OverlayPosition.TOP_LEFT);
		}
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (config.hpOrbsMode() != HpOrbMode.HEALTH_BARS)
		{
			return null;
		}

		// solo raid
		if (Strings.isNullOrEmpty(client.getVarcStrValue(1100)))
		{
			String playerName = client.getVarcStrValue(1099);
			double hpFactor = hpFactor(client.getVarbitValue(VarbitID.TOA_CLIENT_P0) - 1);
			panelComponent.getChildren().add(buildHpBar(playerName, hpFactor));

			return super.render(graphics);
		}

		DoubleHpBarComponent current = new DoubleHpBarComponent();
		for (int i = 0; i < 8; i++)
		{
			String playerName = client.getVarcStrValue(1099 + i);
			double hpFactor = hpFactor(client.getVarbitValue(VarbitID.TOA_CLIENT_P0 + i) - 1);
			if (hpFactor > 1.0)
			{
				// reports over 100% for death for some reason
				// we can use it to colour gray for death
				hpFactor = -1.0;
			}

			if (Strings.isNullOrEmpty(playerName))
			{
				continue;
			}

			if (i % 2 == 0)
			{
				current.setCenterLabel1(playerName);
				current.setValue1(hpFactor);
				panelComponent.getChildren().add(current);
			}
			else
			{
				current.setCenterLabel2(playerName);
				current.setValue2(hpFactor);
				current = new DoubleHpBarComponent();
			}
		}

		return super.render(graphics);
	}

	private static double hpFactor(int hpVarb)
	{
		return (double) Math.max(hpVarb, 0) / 26.0;
	}

	private ProgressBarComponent buildHpBar(String name, double hpFactor)
	{
		ProgressBarComponent hpBar = new ProgressBarComponent();
		hpBar.setBackgroundColor(new Color(102, 15, 16, 230));
		hpBar.setForegroundColor(new Color(0, 146, 54, 230));
		hpBar.setLabelDisplayMode(ProgressBarComponent.LabelDisplayMode.TEXT_ONLY);
		hpBar.setCenterLabel(name);
		hpBar.setValue(hpFactor);
		hpBar.setMinimum(0);
		hpBar.setMaximum(1);
		hpBar.setPreferredSize(new Dimension(60, 20));
		return hpBar;
	}
}