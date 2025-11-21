package net.runelite.client.plugins.pluginhub.com.tobinfoboxes;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("tobinfoboxes")
public interface TobInfoboxesConfig extends Config
{
	@ConfigItem(
		keyName = "showMaiden",
		name = "Show Maiden",
		description = "Show Maiden completion time",
		position = 1
	)
	default boolean showMaiden()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showBloat",
		name = "Show Bloat",
		description = "Show Bloat completion time",
		position = 2
	)
	default boolean showBloat()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showNylo",
		name = "Show Nylocas",
		description = "Show Nylocas completion time",
		position = 3
	)
	default boolean showNylo()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSote",
		name = "Show Sotetseg",
		description = "Show Sotetseg completion time",
		position = 4
	)
	default boolean showSotetseg()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showXarpus",
		name = "Show Xarpus",
		description = "Show Xarpus completion time",
		position = 5
	)
	default boolean showXarpus()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showVerzik",
		name = "Show Verzik",
		description = "Show Verzik completion time",
		position = 6
	)
	default boolean showVerzik()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showTotal",
		name = "Show Total",
		description = "Show total room completion time",
		position = 7
	)
	default boolean showTotal()
	{
		return true;
	}
}

/*
 * Copyright (c) 2020, winterdaze
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