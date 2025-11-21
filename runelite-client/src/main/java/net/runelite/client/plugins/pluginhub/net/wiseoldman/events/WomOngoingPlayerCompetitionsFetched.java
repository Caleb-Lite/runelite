package net.runelite.client.plugins.pluginhub.net.wiseoldman.events;

import net.runelite.client.plugins.pluginhub.net.wiseoldman.beans.ParticipantWithStanding;
import lombok.Value;

@Value
public class WomOngoingPlayerCompetitionsFetched
{
	String username;
	ParticipantWithStanding[] competitions;
}
