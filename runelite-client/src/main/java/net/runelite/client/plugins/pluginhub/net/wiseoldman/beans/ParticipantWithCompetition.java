package net.runelite.client.plugins.pluginhub.net.wiseoldman.beans;

import lombok.Value;

@Value
public class ParticipantWithCompetition
{
    int playerId;
    int competitionId;
    String teamName;
    String createdAt;
    String updatedAt;
    Competition competition;
}
