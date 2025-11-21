package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util;

import lombok.Value;

@Value
public class RaidStateChanged
{

	private final RaidState previousState;
	private final RaidState newState;

}
