package net.runelite.client.plugins.pluginhub.com.github.dappermickie.wildernessmaplocations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.worldmap.WorldMap;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class WorldMapLocationsOverlay extends Overlay
{
	private final Client client;
	private final WildernessMapLocationsConfig config;

	@Inject
	public WorldMapLocationsOverlay(Client client, WildernessMapLocationsConfig config)
	{
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setPriority(PRIORITY_HIGHEST);
		setLayer(OverlayLayer.MANUAL);
		drawAfterInterface(InterfaceID.WORLD_MAP);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.wildernessMapLocationsOverlay())
		{
			return null;
		}

		// Ensure the world map is open
		if (!isWorldMapOpen())
		{
			return null;
		}

		// Define points to render
		for (WildernessMapPoint wMapPoint : WildernessMapLocationsPlugin.getMapPoints())
		{
			drawText(graphics, wMapPoint);
		}

		return null; // Return null when there's no preferred size
	}

	private void drawText(Graphics2D graphics, WildernessMapPoint wMapPoint)
	{
		WorldPoint worldPoint = wMapPoint.getWorldPoint();
		String text = wMapPoint.getText();

		Point mapPoint = mapWorldPointToGraphicsPoint(worldPoint);
		if (mapPoint != null) // Ensure it's within the viewable area
		{
			// Get the map widget bounds
			Widget map = client.getWidget(ComponentID.WORLD_MAP_MAPVIEW);
			if (map == null)
			{
				return;
			}

			Rectangle worldMapRect = map.getBounds();

			// Calculate text box dimensions
			int textWidth = graphics.getFontMetrics().stringWidth(text) + 10; // Add padding
			int textHeight = 20; // Fixed height for the text box

			// Center the text box on the world point
			int textX = (int) mapPoint.getX() - textWidth / 2; // Center horizontally
			int textY = (int) mapPoint.getY() - textHeight / 2; // Center vertically

			Rectangle textBounds = new Rectangle(textX, textY, textWidth, textHeight);

			// Check if the text box is fully within the map bounds
			if (!worldMapRect.contains(textBounds))
			{
				return; // Skip rendering if the text box is outside the map
			}

			// Render the background and text
			var fill = wMapPoint.getFillColor();
			Color opacityColor = new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), config.wildernessMapLocationsBackgroundAlpha());

			graphics.setColor(opacityColor);
			graphics.fillRect(textX, textY, textWidth, textHeight);
			graphics.setColor(wMapPoint.getTextColor());
			graphics.drawString(text, textX + 5, textY + 15); // Offset for padding
		}
	}

	public Point mapWorldPointToGraphicsPoint(WorldPoint worldPoint)
	{
		WorldMap worldMap = client.getWorldMap();

		if (!worldMap.getWorldMapData().surfaceContainsPosition(worldPoint.getX(), worldPoint.getY()))
		{
			return null;
		}

		float pixelsPerTile = worldMap.getWorldMapZoom();

		Widget map = client.getWidget(ComponentID.WORLD_MAP_MAPVIEW);
		if (map != null)
		{
			Rectangle worldMapRect = map.getBounds();

			int widthInTiles = (int) Math.ceil(worldMapRect.getWidth() / pixelsPerTile);
			int heightInTiles = (int) Math.ceil(worldMapRect.getHeight() / pixelsPerTile);

			net.runelite.api.Point worldMapPosition = worldMap.getWorldMapPosition();

			//Offset in tiles from anchor sides
			int yTileMax = worldMapPosition.getY() - heightInTiles / 2;
			int yTileOffset = (yTileMax - worldPoint.getY() - 1) * -1;
			int xTileOffset = worldPoint.getX() + widthInTiles / 2 - worldMapPosition.getX();

			int xGraphDiff = ((int) (xTileOffset * pixelsPerTile));
			int yGraphDiff = (int) (yTileOffset * pixelsPerTile);

			//Center on tile.
			yGraphDiff -= pixelsPerTile - Math.ceil(pixelsPerTile / 2);
			xGraphDiff += pixelsPerTile - Math.ceil(pixelsPerTile / 2);

			yGraphDiff = worldMapRect.height - yGraphDiff;
			yGraphDiff += (int) worldMapRect.getY();
			xGraphDiff += (int) worldMapRect.getX();

			return new Point(xGraphDiff, yGraphDiff);
		}
		return null;
	}

	private boolean isWorldMapOpen()
	{
		return client.getWorldMap() != null && client.getWidget(ComponentID.WORLD_MAP_MAPVIEW) != null;
	}
}