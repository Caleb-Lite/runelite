package net.runelite.client.plugins.pluginhub.com.agilitypyramidsliderblocktimer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import static net.runelite.api.NpcID.*;

import java.util.Set;

public class Npcs
{
	static final Set<Integer> TRAP_NPC_IDS = ImmutableSet.of(
		// Agility pyramid
		PYRAMID_BLOCK, PYRAMID_BLOCK_5788
	);

	static final Map<Integer, Integer> TRAP_RESTING_POSITION = ImmutableMap.of(
		PYRAMID_BLOCK, 2845,
		PYRAMID_BLOCK_5788, 3372
	);

	static final Map<Integer, String> TRAP_MOVEMENT_DIMENSION = ImmutableMap.of(
		PYRAMID_BLOCK, "y",
		PYRAMID_BLOCK_5788, "x"
	);

	static final Map<Integer, String> TRAP_RESTING_TIME = ImmutableMap.of(
		PYRAMID_BLOCK, "PT4.8S",
		PYRAMID_BLOCK_5788, "PT6.0S"
	);
}