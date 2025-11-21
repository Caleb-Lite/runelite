package net.runelite.client.plugins.pluginhub.com.monkeymetrics;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import net.runelite.api.Skill;

@Data
public class AttackMetrics
{
    private int hitsplats = 0;
    private int damage = 0;
    private final Map<Skill, Integer> gainedExp = new HashMap<>();
}

