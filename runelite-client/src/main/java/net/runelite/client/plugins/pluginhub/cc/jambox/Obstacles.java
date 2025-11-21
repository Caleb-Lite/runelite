package net.runelite.client.plugins.pluginhub.cc.jambox;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import net.runelite.client.game.AgilityShortcut;
import java.util.Set;
import static net.runelite.api.NullObjectID.*;
import static net.runelite.api.ObjectID.*;

public class Obstacles {
    static final Multimap<Integer, AgilityShortcut> SHORTCUT_OBSTACLE_IDS;
    static
    {
        final ImmutableMultimap.Builder<Integer, AgilityShortcut> builder = ImmutableMultimap.builder();
        for (final AgilityShortcut item : AgilityShortcut.values())
        {
            for (int obstacle : item.getObstacleIds())
            {
                builder.put(obstacle, item);
            }
        }
        SHORTCUT_OBSTACLE_IDS = builder.build();
    }

}

