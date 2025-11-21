package net.runelite.client.plugins.pluginhub.com.guestindicators;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.clan.ClanChannel;
import net.runelite.api.clan.ClanChannelMember;
import net.runelite.api.clan.ClanRank;

@Singleton
public class ClanGuestTracker
{
	@Inject
	private Client client;

	private Map<String, ClanRank> clanPlayersToRank = new HashMap<>();
	private Map<String, ClanRank> guestClanPlayersToRank = new HashMap<>();

	void updateClan(boolean isGuestClan)
	{
		ClanChannel clanChannel = isGuestClan ? client.getGuestClanChannel() : client.getClanChannel();
		Map<String, ClanRank> playersToRank = isGuestClan ? guestClanPlayersToRank : clanPlayersToRank;
		if (clanChannel == null)
		{
			playersToRank.clear();
			return;
		}

		Map<String, ClanRank> updatedMap = new HashMap<>(playersToRank.size() * 4 / 3);
		for (ClanChannelMember member : clanChannel.getMembers())
		{
			updatedMap.put(member.getName(), member.getRank());
		}

		if (isGuestClan)
		{
			guestClanPlayersToRank = updatedMap;
		}
		else
		{
			clanPlayersToRank = updatedMap;
		}
	}

	boolean isClanGuest(Player player)
	{
		if (clanPlayersToRank.containsKey(player.getName()))
		{
			return clanPlayersToRank.get(player.getName()).equals(ClanRank.GUEST);
		}
		return false;
	}

	boolean isGuestClanGuest(Player player)
	{
		if (guestClanPlayersToRank.containsKey(player.getName()))
		{
			return guestClanPlayersToRank.get(player.getName()).equals(ClanRank.GUEST);
		}
		return false;
	}

	public boolean isGuestClanMember(Player player)
	{
		if (guestClanPlayersToRank.containsKey(player.getName()))
		{
			return !guestClanPlayersToRank.get(player.getName()).equals(ClanRank.GUEST);
		}
		return false;
	}
}
