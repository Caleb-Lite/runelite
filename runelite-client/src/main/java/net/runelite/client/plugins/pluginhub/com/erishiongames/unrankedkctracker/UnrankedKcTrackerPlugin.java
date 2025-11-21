package net.runelite.client.plugins.pluginhub.com.erishiongames.unrankedkctracker;

import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
	name = "Unranked KC Tracker"
)
public class UnrankedKcTrackerPlugin extends Plugin
{
	public static final String CONFIG_GROUP = "unrankedkctracker";
//	public Widget baShopWidget;
//	public int baShopWidgetID = 32178178;
//	public int queenKills;

	@Inject
	ClientToolbar clientToolbar;

	private UnrankedKcTrackerPanel panel;
	private NavigationButton navigationButton;

	@Inject
	private Client client;

	@Inject
	private UnrankedKcTrackerConfig config;

	@Inject
	private ScheduledExecutorService executor;

	@Override
	protected void startUp() throws Exception
	{
		panel = injector.getInstance(UnrankedKcTrackerPanel.class);
		panel.init();

		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "icon.png");

		navigationButton = NavigationButton.builder()
			.tooltip("Unranked KC Tracker")
			.icon(icon)
			.priority(20)
			.panel(panel)
			.build();

		clientToolbar.addNavigation(navigationButton);
	}

//	@Subscribe
//	public void onWidgetLoaded(WidgetLoaded widgetLoaded){
//		int groupID = widgetLoaded.getGroupId();
//		if(groupID == 491)
//		{
//			baShopWidget = client.getWidget(baShopWidgetID);
//				//It's okay Intellij, the potential null pointer can't hurt you now
//				assert baShopWidget != null;
//
//			String queenKillsUncleaned = baShopWidget.getChild(27).getText();
//			queenKillsUncleaned = Text.removeTags(queenKillsUncleaned);
//			queenKills = Integer.parseInt(queenKillsUncleaned.substring(12, 14));
//			System.out.println(queenKills);
//		}
//	}


	@Override
	protected void shutDown() throws Exception
	{
		panel.deinit();
		clientToolbar.removeNavigation(navigationButton);
		panel = null;
		navigationButton = null;
	}

	@Provides
	UnrankedKcTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(UnrankedKcTrackerConfig.class);
	}
}

/* BSD 2-Clause License
 * Copyright (c) 2023, Erishion Games LLC <https://github.com/Erishion-Games-LLC>
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