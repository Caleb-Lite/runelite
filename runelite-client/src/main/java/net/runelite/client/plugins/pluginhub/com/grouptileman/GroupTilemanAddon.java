package net.runelite.client.plugins.pluginhub.com.grouptileman;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.com.grouptileman.runelite.config.Tile;
import net.runelite.client.plugins.pluginhub.com.grouptileman.runelite.config.TilemanModeConfig;
import net.runelite.client.plugins.pluginhub.com.grouptileman.runelite.overlay.TilemanModeMinimapOverlay;
import net.runelite.client.plugins.pluginhub.com.grouptileman.runelite.overlay.TilemanModeOverlay;
import net.runelite.client.plugins.pluginhub.com.grouptileman.runelite.overlay.TilemanModeWorldMapOverlay;
import net.runelite.client.plugins.pluginhub.com.grouptileman.runelite.share.GroundMarkerSharingManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@PluginDescriptor(
        name = "Group tileman addon",
        description = "Automatically share and draw group tiles",
        tags = {"overlay", "tiles"}
)
public class GroupTilemanAddon extends Plugin {
    public static final String CONFIG_GROUP = "groupTilemanAddon";
    public static final String TILEMAN_CONFIG_GROUP = "tilemanMode";
    public static final String REGION_PREFIX = "region_";

    private static final Gson GSON = new Gson();

    @Getter(AccessLevel.PUBLIC)
    private final List<WorldPoint> points = new ArrayList<>();

    @Inject
    private Client client;

    @Inject
    private ConfigManager configManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private TilemanModeOverlay overlay;

    @Inject
    private TilemanModeMinimapOverlay minimapOverlay;

    @Inject
    private TilemanModeWorldMapOverlay worldMapOverlay;

    @Inject
    private TilemanModeConfig config;

    @Inject
    private GroundMarkerSharingManager sharing;

    @Inject
    private ClientToolbar clientToolbar;

    @Provides
    TilemanModeConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(TilemanModeConfig.class);
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged) {
        reloadPoints();
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        reloadPoints();
    }

    private void reloadPoints() {
        points.clear();
        int[] regions = client.getMapRegions();

        if (regions == null) {
            return;
        }

        for (final String player: config.groupPlayerNames().split(",")) {
            for (int regionId : regions) {
                // load points for region
                log.debug("Loading points for region {}", regionId);
                Collection<WorldPoint> worldPoint = translateToWorldPoint(getTiles(player, regionId));
                points.addAll(worldPoint);
            }
        }
    }

    @Override
    protected void startUp() {
        log.error("startUp");

        overlayManager.add(overlay);
        overlayManager.add(minimapOverlay);
        overlayManager.add(worldMapOverlay);

        sharing.addImportExportMenuOptions();

        reloadPoints();
    }

    @Override
    protected void shutDown() {
        log.error("shutDown");

        overlayManager.remove(overlay);
        overlayManager.remove(minimapOverlay);
        overlayManager.remove(worldMapOverlay);
        points.clear();

        sharing.removeMenuOptions();
    }

    public String getPlayerName() {
        return client.getLocalPlayer() != null && client.getLocalPlayer().getName() != null ? client.getLocalPlayer().getName() : "";
    }

    public Collection<Tile> getTiles(String player, int regionId) {
        if (player == null || player.isEmpty()) throw new NullPointerException("Empty player");
        return getConfiguration(player + "-" + REGION_PREFIX + regionId);
    }

    private Collection<Tile> getTiles(String player, String regionId) {
        if (player == null || player.isEmpty()) throw new NullPointerException("Empty player");
        return getConfiguration(player + "-" + REGION_PREFIX + regionId);
    }

    private Collection<Tile> getConfiguration(String key) {
        String json = configManager.getConfiguration(CONFIG_GROUP, key);

        if (Strings.isNullOrEmpty(json)) {
            return Collections.emptyList();
        }

        return GSON.fromJson(json, new TypeToken<List<Tile>>() {}.getType());
    }

    private Collection<WorldPoint> translateToWorldPoint(Collection<Tile> points) {
        if (points.isEmpty()) {
            return Collections.emptyList();
        }

        return points.stream()
                .map(point -> WorldPoint.fromRegion(point.getRegionId(), point.getRegionX(), point.getRegionY(), point.getZ()))
                .flatMap(worldPoint ->
                {
                    final Collection<WorldPoint> localWorldPoints = WorldPoint.toLocalInstance(client, worldPoint);
                    return localWorldPoints.stream();
                })
                .collect(Collectors.toList());
    }
}
