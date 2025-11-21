package net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles;

import com.google.gson.Gson;
import com.google.inject.Provides;
import javax.inject.Inject;

import net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles.ui.GroundMarkerOverlay;
import net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles.ui.GroundMarkerMinimapOverlay;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

@PluginDescriptor(
	name = "Ground Marker Profiles"
)
public class GroundMarkerProfilesPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private GroundMarkerProfilesConfig config;

	@Inject
	private Gson gson;

	@Inject
	private OverlayManager overlayManager;

	public ProfileSwapper profileSwapper;
	private PointManager pointManager;
	private GroundMarkerOverlay groundMarkerOverlay;
	private GroundMarkerMinimapOverlay groundMarkerMinimapOverlay;
	private GroundMarkerProfilesSidePanel sidePanel;
	private NavigationButton sidePanelButton;

	public static final Logger log = LoggerFactory.getLogger(GroundMarkerProfilesPlugin.class);

	@Override
	protected void startUp() throws Exception
	{
		log.info("Ground Marker Profiles started");

		profileSwapper = new ProfileSwapper(this, config);
		pointManager = new PointManager(profileSwapper, gson, client);

		groundMarkerOverlay = new GroundMarkerOverlay(pointManager, client, config);
		groundMarkerMinimapOverlay = new GroundMarkerMinimapOverlay(pointManager, client, config);
		overlayManager.add(groundMarkerOverlay);
		overlayManager.add(groundMarkerMinimapOverlay);

		pointManager.loadPoints();
		createSidePanel();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Ground Marker Profiles stopped");
		overlayManager.remove(groundMarkerOverlay);
		overlayManager.remove(groundMarkerMinimapOverlay);
		removeSidePanel();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			pointManager.loadPoints();
		}
	}

	@Provides
	GroundMarkerProfilesConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(GroundMarkerProfilesConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged) {
		if (configChanged.getGroup().equals(GroundMarkerProfilesConfig.GROUP)) {
			// Example key name is "nameProfile9" NOTE: these can go to multiple digits
			String keyChanged = configChanged.getKey();
			if (keyChanged.startsWith("nameProfile")) {
				Integer indexChanged = Integer.valueOf(keyChanged.substring(11));
				sidePanel.updateButtonName(indexChanged, configChanged.getNewValue());
			} else {
				profileSwapper.updateTileProfiles();
				pointManager.loadPoints();
			}
		}
	}

	private void createSidePanel() {
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/ico.png");
		sidePanel = new GroundMarkerProfilesSidePanel(this, config);
		sidePanelButton = NavigationButton.builder().tooltip("Ground Marker Profiles").icon(icon).priority(6).panel(sidePanel).build();
		clientToolbar.addNavigation(sidePanelButton);
		sidePanel.startPanel();
	}

	private void removeSidePanel()
	{
		clientToolbar.removeNavigation(sidePanelButton);
	}
}

/*
 * Copyright (c) 2019, Jordan Atwood <nightfirecat@protonmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */