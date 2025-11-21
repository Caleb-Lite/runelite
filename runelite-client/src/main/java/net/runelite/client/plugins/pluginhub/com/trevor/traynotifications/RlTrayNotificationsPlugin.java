package net.runelite.client.plugins.pluginhub.com.trevor.traynotifications;

import com.google.inject.Provides;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.TrayIcon;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.SwingUtilities;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.NotificationFired;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientUI;

@Slf4j
@PluginDescriptor(
	name = "RL Tray Notifications"
)
public class RlTrayNotificationsPlugin extends Plugin
{
	@Inject
	private RlTrayNotificationsConfig config;

	@Inject
	private ClientUI clientUI;

	@Inject
	private RuneLiteConfig runeLiteConfig;

	@Inject
	private Client client;

	@Inject
	@Named("runelite.title")
	private String appName;

	public enum MonitorConfig
	{
		CURRENT_MONITOR,
		MAIN_MONITOR
	}

	public enum CornerConfig
	{
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT
	}

	@Subscribe
	public void onNotificationFired(NotificationFired event)
	{
		if (!runeLiteConfig.sendNotificationsWhenFocused() && clientUI.isFocused())
		{
			return;
		}

		String title;

		if (client.getLocalPlayer() != null && client.getLocalPlayer().getName() != null)
		{
			title = client.getLocalPlayer().getName();
		}
		else
		{
			title = appName;
		}

		SwingUtilities.invokeLater(() -> sendCustomNotification(title, event.getMessage(), event.getType()));
	}

	private void sendCustomNotification(
		final String title,
		final String message,
		final TrayIcon.MessageType type
	)
	{
		GraphicsConfiguration graphicsConfiguration;

		if (config.monitor() == MonitorConfig.CURRENT_MONITOR)
		{
			graphicsConfiguration = clientUI.getGraphicsConfiguration();
		}
		else
		{
			GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = environment.getDefaultScreenDevice();
			graphicsConfiguration = device.getDefaultConfiguration();
		}

		CustomNotification.sendCustomNotification(title, message, graphicsConfiguration.getBounds(), config.corner(), config.expireTime());
	}

	@Provides
	RlTrayNotificationsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RlTrayNotificationsConfig.class);
	}
}

/*
 * Copyright (c) 2018 Abex
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