package net.runelite.client.plugins.pluginhub.thestonedturtle.partypanel;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("partypanel")
public interface PartyPanelConfig extends Config
{
	@ConfigItem(
		keyName = "alwaysShowIcon",
		name = "Always show sidebar",
		description = "<html>Controls whether the sidebar icon is always shown (checked) or only shown while inside a party (unchecked)</html>"
	)
	default boolean alwaysShowIcon()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showPartyControls",
		name = "Show Party Controls",
		description = "<html>Controls whether we display the party control buttons like create and leave party</html>",
		position = 0
	)
	default boolean showPartyControls()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showPartyPassphrase",
		name = "Show Party Passphrase",
		description = "<html>Controls whether the party passphrase is displayed within the UI<br/>If disabled and party controls are shown you can still copy</html>",
		position = 1
	)
	default boolean showPartyPassphrase()
	{
		return true;
	}

	@ConfigItem(
		keyName = "autoExpandMembers",
		name = "Expand members by default",
		description = "<html>Controls whether party member details are automatically expanded (checked) or collapsed into banners (unchecked)</html>",
		position = 2
	)
	default boolean autoExpandMembers()
	{
		return false;
	}

	@ConfigItem(
		keyName = "displayVirtualLevels",
		name = "Display Virtual Levels",
		description = "<html>Controls whether we display a players virtual level as their base level</html>",
		position = 3
	)
	default boolean displayVirtualLevels()
	{
		return true;
	}

	@ConfigItem(
		keyName = "displayPlayerWorlds",
		name = "Display Player Worlds",
		description = "<html>Controls whether we display the world a player is currently on</html>",
		position = 4
	)
	default boolean displayPlayerWorlds()
	{
		return true;
	}



	@ConfigItem(
		keyName = "previousPartyId",
		name = "",
		description = "",
		hidden = true
	)
	default String previousPartyId()
	{
		return "";
	}

	@ConfigItem(
		keyName = "previousPartyId",
		name = "",
		description = "",
		hidden = true
	)
	void setPreviousPartyId(String id);
}

/*
 * Copyright (c) 2020, TheStonedTurtle <https://github.com/TheStonedTurtle>
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