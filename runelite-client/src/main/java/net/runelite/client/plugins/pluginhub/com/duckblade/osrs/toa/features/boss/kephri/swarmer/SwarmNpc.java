package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.boss.kephri.swarmer;

import lombok.Data;
import net.runelite.api.NPC;

@Data
public class SwarmNpc
{
	private final NPC npc;
	private final int waveSpawned;
}