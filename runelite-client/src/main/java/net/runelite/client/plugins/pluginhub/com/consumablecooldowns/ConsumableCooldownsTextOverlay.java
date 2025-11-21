package net.runelite.client.plugins.pluginhub.com.consumablecooldowns;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.NullItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

@Slf4j
public class ConsumableCooldownsTextOverlay extends WidgetItemOverlay
{
	private final ConsumableCooldownsPlugin plugin;
	private final ConsumableCooldownsConfig config;

	@Inject
	public ConsumableCooldownsTextOverlay(ConsumableCooldownsPlugin plugin, ConsumableCooldownsConfig config)
	{
		this.plugin = plugin;
		this.config = config;

		showOnInventory();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem)
	{
		graphics.setFont(config.getFontType().getFont());
		renderConsumableCooldowns(graphics, widgetItem);
	}

	private void renderConsumableCooldowns(Graphics2D graphics, WidgetItem widgetItem)
	{
		if (plugin.isNoConsumableCooldownActive())
		{
			return;
		}

		Rectangle slotBounds = widgetItem.getCanvasBounds();
		int itemId = widgetItem.getId();

		// Empty inventory item
		if (itemId == NullItemID.NULL_6512)
		{
			return;
		}

		ConsumableItem consumableItem = plugin.getConsumableItemFromId(itemId);
		if (consumableItem == null)
		{
			return;
		}

		ConsumableItemCooldown cooldownRemaining = plugin.getCooldownForConsumableItem(consumableItem);
		if (cooldownRemaining == null)
		{
			return;
		}

		switch (config.cooldownTextMode())
		{
			case GAME_TICKS:
				renderCooldownText(graphics, cooldownRemaining.toGameTicks(), slotBounds);
				break;
			case SECONDS_MILLISECONDS:
				renderCooldownText(graphics, cooldownRemaining.toSecondsMilliseconds(), slotBounds);
				break;
			case NONE:
				break;
		}
	}

	private void renderCooldownText(Graphics2D graphics, String delayText, Rectangle slotBounds)
	{
		if (delayText.equals("0.0"))
		{
			return;
		}

		FontMetrics fm = graphics.getFontMetrics();
		Rectangle2D textBounds = fm.getStringBounds(delayText, graphics);

		int textX = (int) (slotBounds.getX() + (slotBounds.getWidth() / 2) - (textBounds.getWidth() / 2)) - config.getTextXOffset();
		int textY = (int) (slotBounds.getY() + (slotBounds.getHeight() / 2) + (textBounds.getHeight() / 2)) - config.getTextYOffset();

		graphics.setColor(config.getTextShadowColor());
		graphics.drawString(delayText, textX + 1, textY + 1);
		graphics.setColor(config.getTextColor());
		graphics.drawString(delayText, textX, textY);
	}
}