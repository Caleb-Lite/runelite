package net.runelite.client.plugins.pluginhub.com.rainbowrave;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.inventorytags.InventoryTagsConfig;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.ImageUtil;

public class RainbowRaveInventoryTagsOverlay extends WidgetItemOverlay
{
	private final ItemManager itemManager;
	private final RainbowRavePlugin rainbowRavePlugin;
	private final InventoryTagsConfig config;
	private final RainbowRaveConfig rainbowRaveConfig;

	private ConfigManager configManager;

	public RainbowRaveInventoryTagsOverlay(ItemManager itemManager, RainbowRavePlugin rainbowRavePlugin, InventoryTagsConfig config, RainbowRaveConfig rainbowRaveConfig, ConfigManager configManager)
	{
		this.itemManager = itemManager;
		this.rainbowRavePlugin = rainbowRavePlugin;
		this.config = config;
		this.rainbowRaveConfig = rainbowRaveConfig;
		this.configManager = configManager;
		showOnEquipment();
		showOnInventory();
		showOnBank();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem)
	{
		if (rainbowRaveConfig.whichItemsToInventoryTag() == RainbowRaveConfig.ItemsToTag.NONE) return;
		String tag = configManager.getConfiguration(InventoryTagsConfig.GROUP, "item_" + itemId);
		if ((tag != null && !tag.isEmpty()) || rainbowRaveConfig.whichItemsToInventoryTag() == RainbowRaveConfig.ItemsToTag.ALL)
		{
			final Color color = rainbowRavePlugin.getColor(itemId);
			if (color != null)
			{
				Rectangle bounds = widgetItem.getCanvasBounds();
				if (config.showTagOutline())
				{
					final BufferedImage outline = itemManager.getItemOutline(itemId, widgetItem.getQuantity(), color);
					graphics.drawImage(outline, (int) bounds.getX(), (int) bounds.getY(), null);
				}

				if (config.showTagFill())
				{
					final Image image = getFillImage(color, widgetItem.getId(), widgetItem.getQuantity());
					graphics.drawImage(image, (int) bounds.getX(), (int) bounds.getY(), null);
				}

				if (config.showTagUnderline())
				{
					int heightOffSet = (int) bounds.getY() + (int) bounds.getHeight() + 2;
					graphics.setColor(color);
					graphics.drawLine((int) bounds.getX(), heightOffSet, (int) bounds.getX() + (int) bounds.getWidth(), heightOffSet);
				}
			}
		}
	}

	private Image getFillImage(Color color, int itemId, int qty)
	{
		final Color fillColor = ColorUtil.colorWithAlpha(color, config.fillOpacity());
		return ImageUtil.fillImage(itemManager.getImage(itemId, qty, false), fillColor);
	}
}
