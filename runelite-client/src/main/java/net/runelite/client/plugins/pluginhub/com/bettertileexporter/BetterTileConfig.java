package net.runelite.client.plugins.pluginhub.com.bettertileexporter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(BetterTileConfig.BETTER_TILE_CONFIG_GROUP)
public interface BetterTileConfig extends Config
{
	String BETTER_TILE_CONFIG_GROUP = "better-tile-exporter";
	@ConfigItem(
			keyName = "show_export_visible_menu",
			name = "Export visible tiles menu",
			description = "Add menu option to world map menu to export visible tile markers",
			position = 0
	)
	default boolean show_export_visible_menu()
	{
		return true;
	}

	@ConfigItem(
			keyName = "show_export_close_menu",
			name = "Export close tiles menu",
			description = "Add menu option to world map menu to export close tile markers",
			position = 1
	)
	default boolean show_export_close_menu()
	{
		return true;
	}

	@ConfigItem(
			keyName = "close_distance_threshold",
			name = "Close distance threshold",
			description = "How close should a marker be to true tile to be exported",
			position = 2
	)
	default int close_distance_threshold()
	{
		return 20;
	}


}

/*
 * Copyright (c) 2018, TheLonelyDev <https://github.com/TheLonelyDev>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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