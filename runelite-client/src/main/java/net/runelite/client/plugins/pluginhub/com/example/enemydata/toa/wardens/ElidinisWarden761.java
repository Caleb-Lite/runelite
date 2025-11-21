package net.runelite.client.plugins.pluginhub.com.example.enemydata.toa.wardens;

import net.runelite.client.plugins.pluginhub.com.example.enemydata.Enemy;
import net.runelite.client.plugins.pluginhub.com.example.enemydata.toa.ToaEnemy;
import net.runelite.api.NPC;

public class ElidinisWarden761 extends ToaEnemy {
    public ElidinisWarden761(NPC npc, int invocation, int partySize, int pathLevel) {
        super(npc, invocation, partySize, pathLevel,
                880, 150, 150, 150,
                0, 40,
                40, 40, 20);
    }
}
