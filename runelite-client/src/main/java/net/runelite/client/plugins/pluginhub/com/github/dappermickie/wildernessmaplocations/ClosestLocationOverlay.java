package net.runelite.client.plugins.pluginhub.com.github.dappermickie.wildernessmaplocations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class ClosestLocationOverlay extends OverlayPanel
{
	private final Client client;
	private final WildernessMapLocationsConfig config;

	private WildernessMapPoint closestLocation = null;

	@Inject
	public ClosestLocationOverlay(Client client, WildernessMapLocationsConfig config)
	{
		this.client = client;
		this.config = config;
		setPriority(PRIORITY_HIGHEST);
		setPosition(OverlayPosition.TOP_RIGHT); // This sets the position of the overlay
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		panelComponent.getChildren().clear(); // Clear previous text

		if (!config.closestLocationOverlayEnabled())
		{
			return null;
		}

		if (closestLocation != null)
		{
			// Get player's current position
			WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();

			// Get the direction to the closest location
			String direction = getDirectionToLocation(playerLocation, closestLocation.getWorldPoint());
			String overlayText = direction + " " + closestLocation.getText();

			// Calculate the width of the text to adjust the panel size
			final int overlayWidth = calculateWidth(graphics, overlayText) + 10;

			panelComponent.setOrientation(ComponentOrientation.VERTICAL); // You can change this to horizontal if preferred
			panelComponent.setPreferredSize(new Dimension(overlayWidth, 20)); // Set panel size to fit text

			// Create and add TitleComponent for the text
			panelComponent.getChildren().add(TitleComponent.builder()
				.text(overlayText)
				.color(Color.WHITE)
				.build()); // Add the text to the panel
		}

		return super.render(graphics); // This will render the panel with the text
	}

	public void updateClosestLocation()
	{
		// Get player's current position
		WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();

		// Check if the player is in the wilderness and on plane 0
		if (!isInWilderness())
		{
			closestLocation = null;
			return;
		}
		closestLocation = getClosestLocation(playerLocation);
	}

	private int calculateWidth(Graphics2D graphics, String textToDisplay)
	{
		return graphics.getFontMetrics().stringWidth(textToDisplay); // Calculate text width for panel size
	}

	private boolean isInWilderness()
	{
		// Check if the Varbit for wilderness is set to 1
		return client.getVarbitValue(Varbits.IN_WILDERNESS) == 1;
	}

	private WildernessMapPoint getClosestLocation(WorldPoint playerLocation)
	{
		WildernessMapPoint closestPoint = null;
		double closestDistance = Double.MAX_VALUE;

		// Find the closest point to the player
		for (WildernessMapPoint point : WildernessMapLocationsPlugin.getMapPoints())
		{
			if (point.getWorldPoint().getPlane() != playerLocation.getPlane())
			{
				continue;
			}
			double distance = playerLocation.distanceTo(point.getWorldPoint());
			if (distance < closestDistance)
			{
				closestDistance = distance;
				closestPoint = point;
			}
		}

		return closestPoint;
	}

	private String getDirectionToLocation(WorldPoint playerLocation, WorldPoint targetLocation)
	{
		int dx = targetLocation.getX() - playerLocation.getX();
		int dy = targetLocation.getY() - playerLocation.getY();

		// Check if player is within 8 tiles horizontally and vertically (near the target location)
		if (Math.abs(dx) <= 8 && Math.abs(dy) <= 8)
		{
			return "At"; // Player is within 8 tiles in both directions
		}

		// Check for horizontal proximity (within 8 tiles east or west)
		if (Math.abs(dx) <= 8)
		{
			if (dy > 0)
			{
				return "South of";  // Within 8 tiles east/west and north of target
			}
			else if (dy < 0)
			{
				return "North of";  // Within 8 tiles east/west and south of target
			}
		}

		// Check for vertical proximity (within 8 tiles north or south)
		if (Math.abs(dy) <= 8)
		{
			if (dx > 0)
			{
				return "West of";   // Within 8 tiles north/south and east of target
			}
			else if (dx < 0)
			{
				return "East of";   // Within 8 tiles north/south and west of target
			}
		}

		// If neither, return the diagonal directions
		if (dx > 0 && dy > 0)
		{
			return "SW of";
		}
		else if (dx < 0 && dy > 0)
		{
			return "SE of";
		}
		else if (dx > 0 && dy < 0)
		{
			return "NW of";
		}
		else if (dx < 0 && dy < 0)
		{
			return "NE of";
		}

		return "At"; // Fallback (shouldn't happen unless exact location)
	}
}
