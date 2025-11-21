package net.runelite.client.plugins.pluginhub.com.bettertileexporter;

import java.awt.Toolkit;
import java.util.Iterator;
import java.awt.datatransfer.StringSelection;
import java.awt.Polygon;
import com.google.inject.Provides;
import com.google.common.base.Strings;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.MenuEntry;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.widgets.ComponentID;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.menus.WidgetMenuOption;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.coords.LocalPoint;


@Slf4j
@PluginDescriptor(
	name = "Better Tile Exporter"
)
public class BetterTilePlugin extends Plugin
{
	private static final String GROUND_MARKER_CONFIG_GROUP = "groundMarker";
	private static final String REGION_PREFIX = "region_";

	@Inject
	private Client client;

	@Inject
	private BetterTileConfig config;

	@Inject
	private MenuManager menuManager;

	@Inject
	private ConfigManager configManager;
	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private Gson gson;

	private static final WidgetMenuOption EXPORT_VISIBLE_OPTION = new WidgetMenuOption("Export", "Visible Ground Markers", ComponentID.MINIMAP_WORLDMAP_OPTIONS);
	private static final WidgetMenuOption EXPORT_CLOSE_OPTION = new WidgetMenuOption("Export", "Close Ground Markers", ComponentID.MINIMAP_WORLDMAP_OPTIONS);

	@Override
	protected void startUp() throws Exception
	{
		syncMenusToConfig();
		log.info("Better tile exporter started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		removeMenus();
		log.info("Better tile exporter stopped!");
	}

	protected void removeMenus()
	{
		menuManager.removeManagedCustomMenu(EXPORT_VISIBLE_OPTION);
		menuManager.removeManagedCustomMenu(EXPORT_CLOSE_OPTION);
	}

	protected void syncMenusToConfig()
	{
		if (config.show_export_visible_menu())
		{
			menuManager.addManagedCustomMenu(EXPORT_VISIBLE_OPTION, this::exportVisibleMarkers);
		}
		if (config.show_export_close_menu())
		{
			menuManager.addManagedCustomMenu(EXPORT_CLOSE_OPTION, this::exportCloseMarkers);
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) throws Exception
	{
		if (event.getGroup().equals(BetterTileConfig.BETTER_TILE_CONFIG_GROUP))
		{
			removeMenus();
			syncMenusToConfig();
		}
	}

	boolean is_marker_visible(GroundMarkerPoint marker)
	{
		WorldPoint point = WorldPoint.fromRegion(marker.getRegionId(), marker.getRegionX(), marker.getRegionY(), marker.getZ());
		int viewportWidth = client.getViewportWidth();
		int viewportHeight = client.getViewportHeight();

		LocalPoint lp = LocalPoint.fromWorld(client, point);
		if (lp == null)
		{
			return false;
		}

		Polygon poly = Perspective.getCanvasTilePoly(client, lp);
		if (poly == null)
		{
			return false;
		}
		boolean poly_is_on_screen = false;
		for (int i = 0; i < poly.npoints; i++) {
			int x = poly.xpoints[i];
			int y = poly.ypoints[i];
			if ((x > 0) && (y > 0))
			{
				if ((x < viewportWidth) && (y < viewportHeight))
				{
					poly_is_on_screen = true;
				}
			}
		}
		return poly_is_on_screen;
	}

	boolean is_marker_close(GroundMarkerPoint marker)
	{
		final WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();
		boolean is_close = marker.getZ() == playerLocation.getPlane();
		double distance = Math.hypot(
				(marker.getRegionX() - playerLocation.getRegionX()),
				(marker.getRegionY() - playerLocation.getRegionY())
		);

		if (distance > config.close_distance_threshold())
		{
			is_close = false;
		}
		return is_close;
	}

	Collection<GroundMarkerPoint> getPoints(int regionId)
	{
		String json = configManager.getConfiguration(GROUND_MARKER_CONFIG_GROUP, REGION_PREFIX + regionId);
		if (Strings.isNullOrEmpty(json))
		{
			return Collections.emptyList();
		}
		return gson.fromJson(json, new TypeToken<List<GroundMarkerPoint>>(){}.getType());
	}

	private void exportMarkersOnCondition(boolean filter_on_distance, boolean filter_on_visibile)
	{
		final WorldPoint playerPos = client.getLocalPlayer().getWorldLocation();
		if (playerPos == null) {
			log.debug("playerPos null");
			return;
		}

		int[] regions = client.getMapRegions();
		if (regions == null) {
			return;
		}

		List<GroundMarkerPoint> activePoints = Arrays.stream(regions)
				.mapToObj(regionId -> getPoints(regionId).stream())
				.flatMap(Function.identity())
				.collect(Collectors.toList());

		Iterator<GroundMarkerPoint> iterator = activePoints.iterator();
		while (iterator.hasNext()) {
			GroundMarkerPoint marker = iterator.next();
			if (filter_on_distance) {
				if (!is_marker_close(marker)) {
					iterator.remove();
				}
			}
			if (filter_on_visibile) {
				if (!is_marker_visible(marker)) {
					iterator.remove();
				}
			}
		}

		if (activePoints.isEmpty()) {
			sendChatMessage("You have no ground markers to export.");
			return;
		}

		final String exportDump = gson.toJson(activePoints);
		log.debug("Exported ground markers: {}", exportDump);

		Toolkit.getDefaultToolkit()
				.getSystemClipboard()
				.setContents(new StringSelection(exportDump), null);
		sendChatMessage(activePoints.size() + " ground markers were copied to your clipboard.");
	}

	private void exportVisibleMarkers(MenuEntry menuEntry)
	{
		exportMarkersOnCondition(false, true);
	}

	private void exportCloseMarkers(MenuEntry menuEntry)
	{
		exportMarkersOnCondition(true, false);
	}

	@Provides
	BetterTileConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BetterTileConfig.class);
	}

	private void sendChatMessage(final String message)
	{
		chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.CONSOLE)
				.runeLiteFormattedMessage(message)
				.build());
	}
}

