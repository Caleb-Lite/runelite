package net.runelite.client.plugins.pluginhub.com.example.enemydata.toa.ampken;

import net.runelite.client.plugins.pluginhub.com.example.enemydata.Enemy;
import net.runelite.client.plugins.pluginhub.com.example.enemydata.toa.ToaEnemy;
import net.runelite.api.NPC;

public class Thrall extends ToaEnemy {
    public Thrall(NPC npc, int invocation, int partySize, int pathLevel) {
        super(npc, invocation, partySize, pathLevel,
                2, 40, 40, 12,
                20, 0,
                0, 0, 0, false); // HP does indeed seem to scale by invocation?
    }
}
