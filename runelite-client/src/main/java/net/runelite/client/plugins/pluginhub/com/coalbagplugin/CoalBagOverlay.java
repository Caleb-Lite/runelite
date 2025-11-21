package net.runelite.client.plugins.pluginhub.com.coalbagplugin;

import com.google.common.collect.ImmutableList;
import net.runelite.api.ItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.components.TextComponent;

import javax.inject.Inject;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.Collection;

public class CoalBagOverlay extends WidgetItemOverlay
{
	private final CoalBagConfig config;
	private static final Collection<Integer> COAL_BAG_IDS = ImmutableList.of(
			ItemID.OPEN_COAL_BAG,
			ItemID.COAL_BAG,
			ItemID.COAL_BAG_12019,
			ItemID.COAL_BAG_25627
	);

	@Inject
	private CoalBagOverlay(CoalBagConfig config)
	{
		this.config = config;
		showOnInventory();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget)
	{

		if (COAL_BAG_IDS.contains(itemId))
		{
			final Rectangle bounds = itemWidget.getCanvasBounds();
			final TextComponent textComponent = new TextComponent();
			textComponent.setPosition(new Point(bounds.x - 1, bounds.y + 8));

			if (CoalBag.isUnknown())
			{
				textComponent.setColor(config.unknownCoalBagColor());
				textComponent.setText("?");
			}
			else if (CoalBag.isEmpty())
			{
				textComponent.setColor(config.emptyCoalBagColor());
				textComponent.setText("0");
			}
			else
			{
				textComponent.setColor(config.knownCoalBagColor());
				textComponent.setText(CoalBag.getAmount());
			}

			textComponent.render(graphics);
		}
	}
}

