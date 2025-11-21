package net.runelite.client.plugins.pluginhub.com.gotrleech.event;

import net.runelite.client.plugins.pluginhub.com.gotrleech.GotrGameState;
import lombok.Value;

/**
 * An event denoting that the GotR game state has changed.
 */
@Value
public class GotrGameStateChanged {

    GotrGameState.State state;
}