package net.runelite.client.plugins.pluginhub.com.cwjoshuak;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class AttackTypesOverlay extends OverlayPanel {
	private final AttackTypesPlugin plugin;

	@Inject
	private AttackTypesOverlay(AttackTypesPlugin plugin)
	{
		super(plugin);
		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
		this.plugin = plugin;
	}
	@Override
	public Dimension render(Graphics2D graphics)
	{
			final AttackType attackType = plugin.getAttackType();
			if (attackType == null)
			{
				return null;
			}

			final String attackTypeString = attackType.getName();

			panelComponent.getChildren().add(TitleComponent.builder()
				.text(attackTypeString)
				.color(Color.WHITE)
				.build());

			panelComponent.setPreferredSize(new Dimension(
				graphics.getFontMetrics().stringWidth(attackTypeString) + 10,
				0));

			return super.render(graphics);
	}
}

