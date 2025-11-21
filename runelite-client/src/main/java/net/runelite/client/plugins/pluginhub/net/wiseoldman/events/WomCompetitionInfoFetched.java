package net.runelite.client.plugins.pluginhub.net.wiseoldman.events;

import net.runelite.client.plugins.pluginhub.net.wiseoldman.beans.CompetitionInfo;
import lombok.Value;

@Value
public class WomCompetitionInfoFetched
{
	CompetitionInfo comp;
}
