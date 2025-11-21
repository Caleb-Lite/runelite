package net.runelite.client.plugins.pluginhub.com.example.enemydata.toa.scabaras;

import net.runelite.client.plugins.pluginhub.com.example.enemydata.Enemy;
import net.runelite.client.plugins.pluginhub.com.example.enemydata.toa.ToaEnemy;
import net.runelite.api.NPC;

public class AgileScarab extends ToaEnemy {
    public AgileScarab(NPC npc, int invocation, int partySize, int pathLevel) {
        super(npc, invocation, partySize, pathLevel,
                30, 60, 20, 5,
                0, 25,
                0, 0, 0, true);
    }
}
