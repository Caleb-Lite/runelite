package net.runelite.client.plugins.pluginhub.com.worldmaprunes;

import com.google.inject.Inject;
import java.util.Arrays;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
		name = "World Map Runes",
		description = "Displays runes required for teleportation spells on world map.",
		tags = {"teleports"}
)
public class WorldMapRunes extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private WorldMapPointManager worldMapPointManager;

	@Override
	protected void startUp() throws Exception
	{
		updateShownIcons();
	}

	@Override
	protected void shutDown() throws Exception
	{
		worldMapPointManager.removeIf(MapPoint.class::isInstance);
	}

	private void updateShownIcons()
	{
		Arrays.stream(TeleportLocationData.values())
				.map(l ->
						MapPoint.builder()
								.type(MapPoint.Type.CUSTOM_TELEPORT)
								.worldPoint(l.getLocation())
								.tooltip(l.getTooltip())
								.image(ImageUtil.loadImageResource(com.worldmaprunes.WorldMapRunes.class, l.getIconPath()))
								.build()
				)
				.forEach(worldMapPointManager::add);
	}

	private static Predicate<WorldMapPoint> isType(MapPoint.Type type)
	{
		return w -> w instanceof MapPoint && ((MapPoint) w).getType() == type;
	}
}