package net.runelite.client.plugins.pluginhub.com.randomscreenshot;

import com.google.inject.Provides;
import java.util.Random;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
	name = "Random Screenshot",
	description = "Randomly take screenshots as you go about your adventures",
	tags = {"screenshot", "images", "random", "memories"}
)
@Slf4j
public class RandomScreenshotPlugin extends Plugin
{
	static final String CONFIG_GROUP_KEY = "randomscreenshot";

	@Inject
	private RandomScreenshotConfig config;

	@Inject
	private ScreenshotUtil screenShotUtil;

	@Inject
	private Client client;

	@Inject
	private RandomUtil rand;

	@Provides
	RandomScreenshotConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RandomScreenshotConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (shouldTakeScreenshot())
		{
			takeScreenshot();
		}
	}

	/* TODO: Create a decider interface so that decision strategy can be made modular. */
	boolean shouldTakeScreenshot() {
		if (isBankPinContainerVisible() || isOnLoginScreen()) {
			return false;
		}

		return rand.randInt(config.sampleWeight()) == 0;
	}

	private void takeScreenshot() {
		screenShotUtil.takeScreenshot();
	}

	private boolean isBankPinContainerVisible()
	{
		Widget pinContainer = client.getWidget(ComponentID.BANK_PIN_CONTAINER);
		if (pinContainer == null) {
			return false;
		}

		return !pinContainer.isSelfHidden();
	}

	private boolean isOnLoginScreen() {
		return client.getGameState() == GameState.LOGIN_SCREEN;
	}
}

/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
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
