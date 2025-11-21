package net.runelite.client.plugins.pluginhub.Posiedien_Leagues_Planner;

import net.runelite.client.plugins.pluginhub.Posiedien_Leagues_Planner.pathfinder.PathfinderConfig;
import net.runelite.client.plugins.pluginhub.Posiedien_Leagues_Planner.pathfinder.SplitFlagMap;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.NavigationButton;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class PluginInitializer implements Runnable
{
    PosiedienLeaguesPlannerPlugin plugin;
    PluginInitializer(PosiedienLeaguesPlannerPlugin InPlugin)
    {
        plugin = InPlugin;
    }
    @Override
    public void run()
    {
        plugin.InitializeFromOtherThread();
    }
}
