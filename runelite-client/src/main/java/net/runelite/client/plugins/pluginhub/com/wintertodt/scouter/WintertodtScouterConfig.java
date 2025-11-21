package net.runelite.client.plugins.pluginhub.com.wintertodt.scouter;

import net.runelite.client.plugins.pluginhub.com.wintertodt.scouter.ui.WintertodtScouterPanelType;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("wintertodtscouter")

public interface WintertodtScouterConfig extends Config
{
	String NETWORK_UPLINK = "wtUplink";
	String NETWORK_DOWNLINK = "wtDownlink";

	@ConfigItem
			(
				keyName = NETWORK_UPLINK,
				position = 0, name = "Realtime Uplink",
				description = "Web endpoint to upload boss data to"
			)
	default String wintertodtGetDownlinkConfig()
	{
		return "https://wintertodt-scouter.com/";
	}

	@ConfigItem(keyName = NETWORK_DOWNLINK,
				position = 1,
				name = "Realtime Downlink",
				description = "Web endpoint to get boss data from"
	)
	default String wintertodtGetUplinkConfig()
	{

		return "https://wintertodt-scouter.com/";
	}

	default WintertodtScouterPanelType wintertodtScouterPanelType()
	{
		return WintertodtScouterPanelType.CONDENSED;
	}

	@ConfigItem(
			keyName = "worldHopperEnabled",
			position = 2,
			name = "Double click to Hop",
			description = "Enables double clicking worlds in the side view panels to quick-hop to them"
	)
	default boolean isWorldHopperEnabled()
	{
		return true;
	}
}

/*
 * Copyright (c) 2021, Cyborger1
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