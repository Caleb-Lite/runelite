package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.tomb;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.TombsOfAmascutConfig;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.module.PluginLifecycleComponent;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.InventoryUtil;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidRoom;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidState;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidStateTracker;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;

@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CursedPhalanxDetector implements PluginLifecycleComponent
{
	private static final Set<Integer> CURSED_PHALANX_ITEM_IDS = ImmutableSet.of(
		ItemID.OSMUMTENS_FANG_ORNAMENT_KIT,
		ItemID.OSMUMTENS_FANG_ORNAMENT
	);

	private boolean isEligibleForKit = true;

	private final EventBus eventBus;
	private final Client client;
	private final RaidStateTracker raidStateTracker;

	@Override
	public boolean isEnabled(final TombsOfAmascutConfig config, final RaidState raidState)
	{
		return raidState.isInRaid() &&
			config.cursedPhalanxDetect();
	}

	@Override
	public void startUp()
	{
		isEligibleForKit = true;
		eventBus.register(this);
	}

	@Override
	public void shutDown()
	{
		eventBus.unregister(this);
	}

	@Subscribe
	private void onChatMessage(ChatMessage e)
	{
		if (e.getType() != ChatMessageType.GAMEMESSAGE || !isEligibleForKit)
		{
			return;
		}

		if (e.getMessage().contains("Total deaths"))
		{
			isEligibleForKit = false;
		}
	}

	@Subscribe
	private void onMenuOptionClicked(final MenuOptionClicked event)
	{
		if (!isEligibleForKit ||
			raidStateTracker.getCurrentState().getCurrentRoom() != RaidRoom.TOMB ||
			client.getVarbitValue(VarbitID.TOA_CLIENT_RAID_LEVEL) < 500)
		{
			return;
		}

		final MenuEntry menuEntry = event.getMenuEntry();
		if (!menuEntry.getOption().equals("Open"))
		{
			return;
		}

		boolean wearingPhalanx = InventoryUtil.containsAny(client.getItemContainer(InventoryID.WORN), CURSED_PHALANX_ITEM_IDS);
		boolean carryingPhalanx = InventoryUtil.containsAny(client.getItemContainer(InventoryID.INV), CURSED_PHALANX_ITEM_IDS);

		if (wearingPhalanx || carryingPhalanx)
		{
			event.consume();
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Remove and/or drop cursed phalanx before doing that.", null);
		}
	}
}

/*
 * Copyright (c) 2022, TheStonedTurtle <https://github.com/TheStonedTurtle>
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