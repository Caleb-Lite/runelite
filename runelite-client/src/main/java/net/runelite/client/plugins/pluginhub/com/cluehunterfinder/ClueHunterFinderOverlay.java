package net.runelite.client.plugins.pluginhub.com.cluehunterfinder;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

@Slf4j
public class ClueHunterFinderOverlay extends OverlayPanel
{
	private final ClueHunterFinderPlugin plugin;
	private final ItemManager itemManager;

	@Inject
	private ClueHunterFinderOverlay(final ClueHunterFinderPlugin plugin, final ItemManager itemManager)
	{
		this.plugin = plugin;
		this.itemManager = itemManager;
	}

	@Override
	public Dimension render(final Graphics2D graphics)
	{
		final ClueHunterItem item = plugin.getItemToFind();
		if (item == null)
		{
			return null;
		}
		final String titleText = itemManager.getItemComposition(item.getId()).getName();
		panelComponent.getChildren().add(TitleComponent.builder().text(titleText).build());
		panelComponent.getChildren().add(LineComponent.builder().build());
		panelComponent.getChildren().add(LineComponent.builder()
			.left(item.getFindInstruction())
			.build());
		return super.render(graphics);
	}
}
