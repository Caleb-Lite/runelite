package net.runelite.client.plugins.pluginhub.com.wastedbankspace.ui.overlay;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import net.runelite.client.plugins.pluginhub.com.wastedbankspace.WastedBankSpacePlugin;
import net.runelite.client.plugins.pluginhub.com.wastedbankspace.model.StorableItem;
import net.runelite.client.plugins.pluginhub.com.wastedbankspace.model.StorageLocations;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.tooltip.Tooltip;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import net.runelite.client.util.ColorUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
public class StorageItemOverlay extends WidgetItemOverlay
{
	private final Point point;

	private final Client client;
	private final WastedBankSpacePlugin plugin;
	private final ItemManager itemManager;
	private final TooltipManager tooltipManager;

	@Getter
	private final Cache<Integer, BufferedImage> wastedSpaceImages = CacheBuilder.newBuilder()
		.maximumSize(160)
		.expireAfterWrite(2, TimeUnit.MINUTES)
		.build();

	@Inject
	StorageItemOverlay(Client client, WastedBankSpacePlugin plugin, ItemManager itemManager, TooltipManager tooltipManager)
	{
		this.client = client;
		this.plugin = plugin;
		this.itemManager = itemManager;
		this.tooltipManager = tooltipManager;
		this.point = new Point();
		showOnBank();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget)
	{
		Set<Integer> items = plugin.getEnabledItems();

		if (items.isEmpty()
			|| itemWidget.getWidget().getParentId() != ComponentID.BANK_ITEM_CONTAINER
			|| !items.contains(itemId)
		)
		{
			return;
		}

		StorableItem item = StorageLocations.getStorableItem(itemId);
		Rectangle bounds = itemWidget.getCanvasBounds();

		if (bounds.contains(client.getMouseCanvasPosition().getX(), client.getMouseCanvasPosition().getY()))
		{
			Tooltip t = new Tooltip(ColorUtil.prependColorTag("Store @ " + item.getLocation(), new Color(238, 238, 238)));
			tooltipManager.add(t);
		}

		renderRibbon(graphics, plugin.getOverlayImage().getImage(), bounds.x + bounds.width - 12, bounds.y + bounds.height - 12);
	}

	private void renderRibbon(Graphics2D graphics, ImageComponent ribbon, int x, int y)
	{
		this.point.setLocation(x, y);
		ribbon.setPreferredLocation(this.point);
		ribbon.render(graphics);
	}
}