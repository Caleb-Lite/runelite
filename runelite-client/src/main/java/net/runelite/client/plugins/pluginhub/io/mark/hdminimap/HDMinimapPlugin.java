package net.runelite.client.plugins.pluginhub.io.mark.hdminimap;

import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.io.mark.hdminimap.mapelement.MapElementManager;
import net.runelite.client.plugins.pluginhub.io.mark.hdminimap.mapelement.MapElementSetting;
import net.runelite.client.plugins.pluginhub.io.mark.hdminimap.render.MinimapStyle;
import net.runelite.client.plugins.pluginhub.io.mark.hdminimap.render.impl.HDRenderer;
import net.runelite.client.plugins.pluginhub.io.mark.hdminimap.ui.MinimapPanel;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.util.Objects;

@PluginDescriptor(
	name = "HD Minimap",
	description = "Adds a HD Minimap from 2008!",
	tags = {"hd", "minimap"}
)

@Slf4j
public class HDMinimapPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private ClientThread clientThread;

    @Inject
    private HDRenderer hdRenderer;

    @Inject
    private HDMinimapConfig config;

    @Inject
    private MapElementManager mapElementManager;

    private MinimapStyle currentStyle;

    @Setter(AccessLevel.PACKAGE)
    private MinimapPanel panel;

    private NavigationButton button;

    @Inject
    private ClientToolbar clientToolbar;

    private Double lastZoom = null;


    @Provides
    HDMinimapConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(HDMinimapConfig.class);
    }

    @Override
	protected void startUp() {
        clientThread.invoke((() -> {
            mapElementManager.start();

            panel = injector.getInstance(MinimapPanel.class);

            final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "icon.png");

            button = NavigationButton.builder()
                    .tooltip("Enhanced Minimap")
                    .icon(icon)
                    .priority(3)
                    .panel(panel)
                    .build();

            if (config.displaySidebar()) {
                clientToolbar.addNavigation(button);
            }
        }));

        currentStyle = config.minimapStyle();
        setMinimapDrawer();
        reloadGame();
        lastZoom = client.getMinimapZoom();
	}


	@Inject
	private PluginManager pluginManager;

	@Override
	public void shutDown() {
        clientToolbar.removeNavigation(button);
        client.setMinimapTileDrawer(null);
        client.getObjectCompositionCache().reset();
        reloadGame();
        mapElementManager.clear();
	}

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        if (event.getGroup().equals(HDMinimapConfig.CONFIG_GROUP)) {
            if (Objects.equals(event.getKey(), "minimapStyle")) {
                currentStyle = config.minimapStyle();
                setMinimapDrawer();
                log.debug("Minimap style changed to: {}", currentStyle);
            }
            if (Objects.equals(event.getKey(), "minimapSideBar")) {
                if (config.displaySidebar()) {
                    clientToolbar.addNavigation(button);
                } else {
                    clientToolbar.removeNavigation(button);
                }
                client.getObjectCompositionCache().reset();
                reloadGame();
            }
        }
        if (event.getGroup().equals(MapElementManager.CONFIG_GROUP)) {
            if (mapElementManager.isCategoryInCurrentArea(event.getKey())) {
                client.getObjectCompositionCache().reset();
                reloadGame();
            }
        }
    }

    public void setMinimapDrawer() {
        if (currentStyle != MinimapStyle.DEFAULT) {
            client.setMinimapTileDrawer(this::drawMapTile);
        } else {
            client.setMinimapTileDrawer(null);
        }
    }

    @Subscribe
    public void onGameTick(GameTick gameTick) {
        if (!config.displaySidebar()) return;
        double zoom = client.getMinimapZoom();
        if (lastZoom != zoom) {
            lastZoom = zoom;

            for (String category : mapElementManager.getCurrentAreaCategories()) {
                MapElementSetting setting = mapElementManager.getSetting(category);
                if (setting.isDisabled() && setting.getScale() != null) {
                    client.getObjectCompositionCache().reset();
                    reloadGame();
                    return;
                }
            }
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged stateChanged) {
        if (stateChanged.getGameState() == GameState.LOGGED_IN && config.displaySidebar()) {
            mapElementManager.updateIcons();
        }
    }

    /**
     * Refreshes the game state if needed when switching to HD117 minimap style
     */
    private void reloadGame() {
        clientThread.invoke(() -> {
            if (client.getGameState() == GameState.LOGGED_IN) {
                client.setGameState(GameState.LOADING);
                if (client.getWorldMap().getWorldMapRenderer().isLoaded()) {
                    client.getWorldMap().initializeWorldMap(client.getWorldMap().getWorldMapData());
                }
            }
        });
    }

    /**
     * Draws a minimap tile using the currently selected renderer
     *
     * @param tile the tile to draw
     * @param tx   tile x coordinate
     * @param ty   tile y coordinate
     * @param px0  pixel x start coordinate
     * @param py0  pixel y start coordinate
     * @param px1  pixel x end coordinate
     * @param py1  pixel y end coordinate
     */
    public void drawMapTile(Tile tile, int tx, int ty, int px0, int py0, int px1, int py1) {
        hdRenderer.drawMapTile(tile, tx, ty, px0, py0, px1, py1);
    }

}
