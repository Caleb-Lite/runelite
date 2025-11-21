package net.runelite.client.plugins.pluginhub.rsfost.ba_world_scouter;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemContainer;
import net.runelite.api.Player;
import net.runelite.api.WorldView;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static rsfost.ba_world_scouter.BaWorldScouterConfig.*;

@Slf4j
@PluginDescriptor(
	name = "BA World Scouter",
	description = "Helps scout for worlds with good quick-start conditions in Barbarian Assault"
)
public class BaWorldScouterPlugin extends Plugin
{
	private static final int BA_LOBBY_REGION = 10322;
	private static final int BA_WAVE_REGION = 7509;
	private static final int BA_WAVE_10_REGION = 7508;
	private static final int PREMOVE_Y_THRESHOLD = 5300;

	@Inject
	private Client client;

	@Inject
	private BaWorldScouterConfig config;

	@Inject
	private InstanceInfoService instanceInfoService;

	@Inject
	private ItemManager itemManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ChatMessageManager chatManager;

	@Inject
	private EventBus eventBus;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private ScheduledExecutorService executorService;

	private NavigationButton navButton;
	private WorldInfoPanel panel;

	private PremoveInfoBox premoveInfoBox;
	private boolean premoveInfoBoxVisible;

	private boolean shouldCheckLocation;
	private int lastRegionId;
	private ScheduledFuture<?> fetchWorldsFuture;
	private volatile boolean updatingWorlds;

	@Override
	protected void startUp() throws Exception
	{
		BufferedImage icon = ImageUtil.loadImageResource(BaWorldScouterPlugin.class, "icon.png");
		panel = new WorldInfoPanel(this, config);
		navButton = NavigationButton.builder()
			.tooltip("BA World Scouter")
			.priority(4)
			.panel(panel)
			.icon(icon)
			.build();
		clientToolbar.addNavigation(navButton);
		eventBus.register(panel);

		fetchWorldsFuture = executorService.scheduleAtFixedRate(this::updateWorlds, 10, 30, TimeUnit.SECONDS);
		eventBus.register(instanceInfoService);

		if (premoveInfoBox == null)
		{
			premoveInfoBox = new PremoveInfoBox(itemManager.getImage(ItemID.TRAIL_WATCH), this);
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		fetchWorldsFuture.cancel(true);
		clientToolbar.removeNavigation(navButton);
		eventBus.unregister(instanceInfoService);
		eventBus.unregister(panel);
		setInfoBoxVisible(false);
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (!shouldCheckLocation)
		{
			return;
		}

		final Player player = client.getLocalPlayer();
		final WorldView worldView = player.getWorldView();
		final WorldPoint wp = player.getWorldLocation();
		final int currentRegionId = wp.getRegionID();

		if (!worldView.isInstance())
		{
			setInfoBoxVisible(false);
			return;
		}
		if (currentRegionId == lastRegionId)
		{
			return;
		}

		final int templateRegionId = WorldPoint.fromLocalInstance(client,
			client.getLocalPlayer().getLocalLocation()).getRegionID();
		log.debug("y = {}, region id = {}", wp.getY(), templateRegionId);
		instanceInfoService.putInstanceInfo(wp, templateRegionId);
		announcePremoveCondition(wp, templateRegionId);
		shouldCheckLocation = false;
		lastRegionId = currentRegionId;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOADING)
		{
			shouldCheckLocation = true;
		}
	}

	@Provides
	BaWorldScouterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BaWorldScouterConfig.class);
	}

	private void updateWorlds()
	{
		if (updatingWorlds)
		{
			log.info("Instance info update in progress -- skipping this update");
			return;
		}

		updatingWorlds = true;
		instanceInfoService.getInstanceInfos(
			instanceInfos -> {
				updatingWorlds = false;
				SwingUtilities.invokeLater(() -> {
					panel.populate(instanceInfos);
				});
			},
			error -> {
				updatingWorlds = false;
				log.error("Unable to update instance information", error);
				panel.updateList();
			}
		);
	}

	private void announcePremoveCondition(WorldPoint wp, int regionId)
	{
		if (config.indicatorActiveMode() == IndicatorActiveMode.DISABLE)
		{
			return;
		}

		final boolean baRegionOnly =
			config.indicatorActiveMode() == IndicatorActiveMode.SCROLLER_ONLY ||
			config.indicatorActiveMode() == IndicatorActiveMode.BA_ONLY;
		if (baRegionOnly && regionId != BA_WAVE_REGION && regionId != BA_WAVE_10_REGION)
		{
			return;
		}

		if (config.indicatorActiveMode() == IndicatorActiveMode.SCROLLER_ONLY)
		{
			ItemContainer inventory = client.getItemContainer(InventoryID.INV);
			if (inventory == null || !inventory.contains(ItemID.BARBASSAULT_SCROLL))
			{
				return;
			}
		}

		final String yStr = formatInt(wp.getY());
		final boolean goodPremove = wp.getY() < PREMOVE_Y_THRESHOLD;
		final String premoveStr = goodPremove ? "Good premove" : "Bad premove";
		switch (config.indicatorDisplayMode())
		{
			case INFO_BOX_AND_CHAT:
			case INFO_BOX:
				premoveInfoBox.setGoodPremove(goodPremove);
				premoveInfoBox.setText(yStr);
				premoveInfoBox.setTooltip(premoveStr);
				setInfoBoxVisible(true);
		}
		switch (config.indicatorDisplayMode())
		{
			case INFO_BOX_AND_CHAT:
			case CHAT:
				String message = ColorUtil.wrapWithColorTag(
					String.format("%s (%s)", premoveStr, yStr),
					goodPremove ? Color.GREEN : Color.RED);
				chatManager.queue(QueuedMessage.builder()
					.type(ChatMessageType.CONSOLE)
					.runeLiteFormattedMessage(message)
					.build());
		}
	}

	private void setInfoBoxVisible(boolean visible)
	{
		if (visible && !this.premoveInfoBoxVisible)
		{
			infoBoxManager.addInfoBox(premoveInfoBox);
		}
		else if (!visible && this.premoveInfoBoxVisible)
		{
			infoBoxManager.removeInfoBox(premoveInfoBox);
		}
		this.premoveInfoBoxVisible = visible;
	}

	private static String formatInt(int a)
	{
		return String.format("%d.%dk", a / 1000, (a % 1000) / 100);
	}
}

/*
 * Copyright (c) 2018, Psikoi <https://github.com/Psikoi>
 * Copyright (c) 2025, rsfost <https://github.com/rsfost>
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