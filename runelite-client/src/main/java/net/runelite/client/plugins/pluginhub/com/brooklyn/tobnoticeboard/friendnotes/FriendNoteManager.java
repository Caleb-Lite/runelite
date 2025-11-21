package net.runelite.client.plugins.pluginhub.com.brooklyn.tobnoticeboard.friendnotes;

import net.runelite.client.plugins.pluginhub.com.brooklyn.tobnoticeboard.TobNoticeBoardConfig;
import net.runelite.client.plugins.pluginhub.com.brooklyn.tobnoticeboard.TobNoticeBoardPlugin;
import com.google.common.base.Strings;
import java.awt.image.BufferedImage;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ChatIconManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.Text;

/**
 * Adapter to interact with the default RuneLite "Friend Notes" plugin.
 *
 * @see net.runelite.client.plugins.friendnotes.FriendNotesPlugin
 */
@Slf4j
public class FriendNoteManager
{
	private static final String FRIEND_NOTES_CONFIG_GROUP = "friendNotes";
	private static final String FRIEND_NOTES_KEY_PREFIX = "note_";
	private static final int ICON_WIDTH = 14;
	private static final int ICON_HEIGHT = 12;
	private int iconId = -1;
	private int chatIconIndex = -1;

	@Inject
	private TobNoticeBoardConfig config;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ChatIconManager chatIconManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private FriendNoteOverlay overlay;

	@Inject
	private PluginManager pluginManager;

	@Getter
	private HoveredFriend hoveredFriend = null;

	public void startUp()
	{
		overlayManager.add(overlay);
		loadIcon();
	}

	public void shutDown()
	{
		overlayManager.remove(overlay);
	}

	/**
	 * Get the friend note of a display name, or null if no friend note exists for it.
	 */
	@Nullable
	public String getNote(String displayName)
	{
		final String sanitizedName = Text.toJagexName(Text.removeTags(displayName));
		return configManager.getConfiguration(FRIEND_NOTES_CONFIG_GROUP, FRIEND_NOTES_KEY_PREFIX + sanitizedName);
	}

	public void updateWidget(Widget widget, String playerName)
	{
		widget.setText(playerName + " <img=" + getNoteIcon() + ">");
		widget.setHasListener(true);
		widget.setOnMouseOverListener((JavaScriptCallback) ev -> setHoveredFriend(playerName));
		widget.setOnMouseLeaveListener((JavaScriptCallback) ev -> setHoveredFriend(null));
	}

	/**
	 * Set the currently hovered display name, if a friend note exists for it.
	 */
	private void setHoveredFriend(String displayName)
	{
		hoveredFriend = null;

		if (!Strings.isNullOrEmpty(displayName))
		{
			final String note = getNote(displayName);
			if (note != null)
			{
				hoveredFriend = new HoveredFriend(displayName, note);
			}
		}
	}

	private Integer getNoteIcon()
	{
		if (chatIconIndex != -1)
		{
			return chatIconIndex;
		}

		if (iconId != -1)
		{
			chatIconIndex = chatIconManager.chatIconIndex(iconId);
			return chatIconIndex;
		}

		return null;
	}

	private void loadIcon()
	{
		if (iconId != -1)
		{
			return;
		}

		final BufferedImage iconImg = ImageUtil.loadImageResource(TobNoticeBoardPlugin.class, "/note_icon.png");
		if (iconImg == null)
		{
			throw new RuntimeException("unable to load icon");
		}

		final BufferedImage resized = ImageUtil.resizeImage(iconImg, ICON_WIDTH, ICON_HEIGHT);
		iconId = chatIconManager.registerChatIcon(resized);
	}

	public boolean isEnabled()
	{
		if (!config.friendNotes())
		{
			return false;
		}

		final Optional<Plugin> friendNotePlugin = pluginManager.getPlugins().stream().filter(p -> p.getName().equals("Friend Notes")).findFirst();
		return friendNotePlugin.isPresent() && pluginManager.isPluginEnabled(friendNotePlugin.get());
	}
}

/*
 * Copyright (c) 2018, Rheon <https://github.com/Rheon-D>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */