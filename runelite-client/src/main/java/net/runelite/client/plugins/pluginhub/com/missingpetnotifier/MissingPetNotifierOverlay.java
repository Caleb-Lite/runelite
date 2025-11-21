package net.runelite.client.plugins.pluginhub.com.missingpetnotifier;

import java.awt.Dimension;
import java.awt.Graphics2D;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class MissingPetNotifierOverlay extends OverlayPanel
{
	private static final String MISSING_PET_TEXT = "Your pet is missing!";

	@Override
	public Dimension render(Graphics2D graphics)
	{
		panelComponent.getChildren().clear();
		panelComponent.getChildren().add((LineComponent.builder().left(MISSING_PET_TEXT).build()));
		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
		return panelComponent.render(graphics);
	}
}
