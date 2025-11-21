package net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.autosync;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.eventbus.Subscribe;
import static net.runelite.client.util.Text.removeTags;

@Slf4j
public class CollectionLogAutoSyncChatMessageSubscriber
{
	private final Pattern NEW_COLLECTION_LOG_ITEM_PATTERN = Pattern.compile("New item added to your collection log: (.*)");

	@Inject
	private CollectionLogAutoSyncManager collectionLogAutoSyncManager;

	/**
	 * This method watches for in-game chat messages that match the new collection log item pattern.
	 * If a matching message is found, it extracts the item name and adds it to the obtainedItemNames set,
	 * which is then used by the ItemContainerChanged and NpcLootReceived events to determine if the item should be synced.
	 */
	@Subscribe
	private void onChatMessage(ChatMessage chatMessage)
	{
		if (chatMessage.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		Matcher matcher = NEW_COLLECTION_LOG_ITEM_PATTERN.matcher(chatMessage.getMessage());

		if (matcher.matches())
		{
			String itemName = removeTags(matcher.group(1));

			collectionLogAutoSyncManager.obtainedItemNames.add(itemName);
		}
	}
}

/*
 * Copyright (c) 2025, andmcadams
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
