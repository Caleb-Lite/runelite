package net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item;

import net.runelite.client.plugins.pluginhub.com.questhelper.collections.ItemCollections;
import net.runelite.api.Client;

import java.util.List;
import java.util.Objects;

public class FollowerItemRequirement extends ItemRequirement
{
	private final List<Integer> followerIDs;
	private final List<Integer> itemIDs;

	public FollowerItemRequirement(String name, List<Integer> itemIDs, List<Integer> followerIDs)
	{
		super(name, itemIDs);

		assert(itemIDs.stream().noneMatch(Objects::isNull));
		assert(followerIDs.stream().noneMatch(Objects::isNull));

		this.itemIDs = itemIDs;
		this.followerIDs = followerIDs;
	}

	public FollowerItemRequirement(String name, ItemCollections itemIDs, List<Integer> followerIDs)
	{
		super(name, itemIDs);

		assert(followerIDs.stream().noneMatch(Objects::isNull));

		this.itemIDs = itemIDs.getItems();
		this.followerIDs = followerIDs;
	}

	@Override
	protected FollowerItemRequirement copyOfClass()
	{
		return new FollowerItemRequirement(getName(), itemIDs, followerIDs);
	}

	@Override
	public boolean check(Client client)
	{
		boolean match = client.getTopLevelWorldView().npcs().stream()
			.filter(npc -> npc.getInteracting() != null) // we need this check because Client#getLocalPlayer is Nullable
			.filter(npc -> npc.getInteracting() == client.getLocalPlayer())
			.anyMatch(npc -> followerIDs.contains(npc.getId()));

		if (match)
		{
			return true;
		}

		return super.check(client);
	}
}
