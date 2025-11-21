package net.runelite.client.plugins.pluginhub.com.nexsplits;

import net.runelite.client.plugins.pluginhub.com.nexsplits.config.KillTimerMode;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class NexTimerBox extends InfoBox
{
	private final NexSplitsConfig config;
	private final NexSplitsPlugin plugin;
	private final Client client;

	NexTimerBox(BufferedImage image, NexSplitsConfig config, NexSplitsPlugin plugin, Client client)
	{
		super(image, plugin);
		this.config = config;
		this.plugin = plugin;
		this.client = client;
		setPriority(InfoBoxPriority.LOW);
	}

	@Override
	public String getText()
	{
		return plugin.isChickenDead() ? plugin.getTime(plugin.getP5Tick() - plugin.getStartTick())
			: plugin.getTime(client.getTickCount() - plugin.getStartTick());
	}

	@Override
	public Color getTextColor()
	{
		return plugin.isChickenDead() ? Color.GREEN : Color.WHITE;
	}

	@Override
	public String getTooltip()
	{
		return plugin.getPhaseTimes();
	}

	@Override
	public boolean render()
	{
		return (config.killTimer() == KillTimerMode.INFOBOX || config.killTimer() == KillTimerMode.BOTH) && plugin.inNexBossArea();
	}
}

/*
 * Copyright (c) 2023, Buchus <http://github.com/MoreBuchus>
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