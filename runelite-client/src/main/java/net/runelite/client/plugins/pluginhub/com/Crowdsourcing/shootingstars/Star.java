package net.runelite.client.plugins.pluginhub.com.Crowdsourcing.shootingstars;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.runelite.api.GameObject;
import net.runelite.api.NPC;
import net.runelite.api.coords.WorldPoint;

@Data
@RequiredArgsConstructor
public class Star
{
	private int tier = -1;
	private int hp = -1;

	private NPC npc;
	private GameObject gameObject;

	private final int world;
	private final WorldPoint location;
}