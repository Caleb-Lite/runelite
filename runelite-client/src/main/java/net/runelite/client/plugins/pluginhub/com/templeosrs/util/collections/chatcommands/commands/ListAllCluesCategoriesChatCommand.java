package net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.chatcommands.commands;

import net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.CollectionLogCategoryGroup;
import net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.chatcommands.ChatCommand;
import net.runelite.api.events.ChatMessage;

public class ListAllCluesCategoriesChatCommand extends ChatCommand
{
	public ListAllCluesCategoriesChatCommand()
	{
		super("!col list clues", "Lists all available clues categories", true);
	}

	@Override
	public void command(ChatMessage event)
	{
		listAvailableCollectionLogCategories(CollectionLogCategoryGroup.clues);
	}
}
