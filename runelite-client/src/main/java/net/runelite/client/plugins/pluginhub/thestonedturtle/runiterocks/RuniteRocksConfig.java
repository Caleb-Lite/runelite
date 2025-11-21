package net.runelite.client.plugins.pluginhub.thestonedturtle.runiterocks;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(RuniteRocksConfig.GROUP)
public interface RuniteRocksConfig extends Config
{
	String GROUP = "runiterocks";

	@ConfigItem(
		position = 0,
		keyName = "respawnCounter",
		name = "Respawn Counter",
		description = "<html>If enabled shows a ticking countdown to the respawn time" +
			"<br/>If disabled shows the time at which the rock should respawn</html>"
	)
	default boolean respawnCounter()
	{
		return true;
	}

	@ConfigItem(
		position = 1,
		keyName = "visitCounter",
		name = "Last Visit Counter",
		description = "<html>If enabled shows a ticking timer for how long since you checked on that rock" +
			"<br/>If disabled shows the time at which you last checked on that rock</html>"
	)
	default boolean visitCounter()
	{
		return false;
	}

	@ConfigItem(
		position = 2,
		keyName = "accurateRespawnPriority",
		name = "Accurate Respawn Priority",
		description = "<html>When enabled and sorting by respawn time Accurate times will be prioritized over Inaccurate times</html>"
	)
	default boolean accurateRespawnPriority()
	{
		return false;
	}

	@ConfigItem(
		position = 3,
		keyName = "ignoreInaccurate",
		name = "Ignore Inaccurate",
		description = "<html>Should rocks that are inaccurate be ignored from the tracker?</html>"
	)
	default boolean ignoreInaccurate()
	{
		return false;
	}

	@ConfigItem(
		position = 4,
		keyName = "doubleClickToHop",
		name = "Hop on double click",
		description = "<html>Do you want to hop worlds when double left clicking an entry in the table?</html>"
	)
	default boolean doubleLeftClickToHop()
	{
		return true;
	}
}

/*
 * Copyright (c) 2018, Psikoi <https://github.com/Psikoi>
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