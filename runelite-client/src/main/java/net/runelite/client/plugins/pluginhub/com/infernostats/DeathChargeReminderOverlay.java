package net.runelite.client.plugins.pluginhub.com.infernostats;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class DeathChargeReminderOverlay extends OverlayPanel
{
	private final DeathChargeReminderConfig config;
	private final Client client;

	@Inject
	private DeathChargeReminderOverlay(DeathChargeReminderConfig config, Client client)
	{
		this.config = config;
		this.client = client;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		final String deathChargeMessage = "You need to death charge!";
		final int length = graphics.getFontMetrics().stringWidth(deathChargeMessage);

		panelComponent.getChildren().clear();

		panelComponent.getChildren().add((LineComponent.builder())
			.left(deathChargeMessage)
			.build());

		panelComponent.setPreferredSize(new Dimension(length + 10, 0));
		panelComponent.setBackgroundColor(config.overlayColor());

		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);

		return panelComponent.render(graphics);
	}
}
