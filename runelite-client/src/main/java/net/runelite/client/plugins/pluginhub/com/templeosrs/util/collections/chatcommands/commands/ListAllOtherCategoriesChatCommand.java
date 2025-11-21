package net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.chatcommands.commands;

import net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.CollectionLogCategoryGroup;
import net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.chatcommands.ChatCommand;
import net.runelite.api.events.ChatMessage;

public class ListAllOtherCategoriesChatCommand extends ChatCommand
{
	public ListAllOtherCategoriesChatCommand()
	{
		super("!col list other", "Lists all available other categories", true);
	}

	@Override
	public void command(ChatMessage event)
	{
		listAvailableCollectionLogCategories(CollectionLogCategoryGroup.other);
	}
}
