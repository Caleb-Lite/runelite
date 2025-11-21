package net.runelite.client.plugins.pluginhub.com.toamistaketracker.events;

import net.runelite.client.plugins.pluginhub.com.toamistaketracker.RaidRoom;
import net.runelite.client.plugins.pluginhub.com.toamistaketracker.RaidState;
import lombok.Builder;
import lombok.Value;

/**
 * An event where the current {@link RaidRoom} has changed in the {@link RaidState}.
 */
@Value
@Builder
public class RaidRoomChanged {

    /**
     * The new RaidRoom
     */
    RaidRoom newRaidRoom;

    /**
     * The previous RaidRoom
     */
    RaidRoom prevRaidRoom;
}