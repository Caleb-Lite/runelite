package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.hporbs;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.TombsOfAmascutConfig;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.module.PluginLifecycleComponent;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidState;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.ui.overlay.OverlayManager;

@Singleton
public class HpOrbManager implements PluginLifecycleComponent
{

	private static final int BUILD_ORBS_WIDGET_SCRIPT_ID = 6579;
	private static final int WIDGET_ID_ORBS = WidgetUtil.packComponentId(481, 4);

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private EventBus eventBus;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private HealthBarsOverlay healthBarsOverlay;

	@Override
	public boolean isEnabled(TombsOfAmascutConfig config, RaidState raidState)
	{
		return config.hpOrbsMode() != HpOrbMode.ORBS && raidState.isInRaid();
	}

	@Override
	public void startUp()
	{
		eventBus.register(this);
		clientThread.invokeLater(this::hideOrbs);
		overlayManager.add(healthBarsOverlay);
	}

	@Override
	public void shutDown()
	{
		eventBus.unregister(this);
		overlayManager.remove(healthBarsOverlay);
	}

	@Subscribe
	public void onScriptPostFired(ScriptPostFired e)
	{
		if (e.getScriptId() == BUILD_ORBS_WIDGET_SCRIPT_ID)
		{
			hideOrbs();
		}
	}

	private void hideOrbs()
	{
		Widget orbW = client.getWidget(WIDGET_ID_ORBS);
		if (orbW != null)
		{
			orbW.setHidden(true);

			orbW.getParent().setOriginalHeight(95);
			orbW.getParent().revalidate();
		}
	}
}

/*
 * Copyright (c) 2020, Trevor <https://github.com/Trevor159/runelite-external-plugins/blob/b9d58dd864ce33a23b34eac91865bdb1521a379a/LICENSE>
 * Copyright (c) 2023, LlemonDuck <napkinorton@gmail.com>
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
