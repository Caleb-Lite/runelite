package net.runelite.client.plugins.pluginhub.com.sextantlocator;

import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.com.sextantlocator.ui.CoordinatesMapOverlay;
import net.runelite.client.plugins.pluginhub.com.sextantlocator.ui.CoordinatesOverlay;
import net.runelite.client.plugins.pluginhub.com.sextantlocator.ui.CoordinatesTooltipOverlay;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Sextant Locator",
	description = "Sextant's coordinates helper.",
	tags = {
		"sextant",
		"coordinates",
		"world",
		"location",
		"locator",
		"clues"
	}
)
public class SextantLocatorPlugin extends Plugin
{
	@Inject
	private SextantLocatorConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	CoordinatesOverlay coordinatesOverlay;

	@Inject
	CoordinatesTooltipOverlay coordinatesTooltipOverlay;

	@Inject
	CoordinatesMapOverlay coordinatesMapOverlay;

	@Provides
	SextantLocatorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SextantLocatorConfig.class);
	}

	@Override
	protected void startUp()
	{
		addOverlays();
	}

	@Override
	protected void shutDown()
	{
		removeOverlays();
	}

	private void addOverlays()
	{
		overlayManager.add(coordinatesOverlay);
		overlayManager.add(coordinatesTooltipOverlay);
		overlayManager.add(coordinatesMapOverlay);
	}

	private void removeOverlays()
	{
		overlayManager.remove(coordinatesOverlay);
		overlayManager.remove(coordinatesTooltipOverlay);
		overlayManager.remove(coordinatesMapOverlay);
	}
}

/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2021, Miguel Sousa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
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
 */