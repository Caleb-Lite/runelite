package net.runelite.client.plugins.pluginhub.net.wiseoldman.events;

import net.runelite.client.plugins.pluginhub.net.wiseoldman.beans.ParticipantWithCompetition;
import lombok.Value;

@Value
public class WomUpcomingPlayerCompetitionsFetched
{
	String username;
	ParticipantWithCompetition[] competitions;
}
