package net.runelite.client.plugins.pluginhub.com.pluginstats.services;

import com.google.inject.Inject;
import java.util.concurrent.atomic.AtomicReference;

import net.runelite.client.plugins.pluginhub.com.pluginstats.PluginStatsConfig;
import net.runelite.client.plugins.PluginManager;

public class PluginStatsService
{
    public static final class Counts {
        private final int total, enabled;
        public Counts(int total, int enabled) { this.total = total; this.enabled = enabled; }
        public int total() { return total; }
        public int enabled() { return enabled; }
    }

    @Inject private PluginManager pluginManager;
    @Inject private PluginStatsConfig config;

    private final AtomicReference<Counts> cached = new AtomicReference<>(new Counts(0, 0));

    public Counts getCounts()
    {

        recalc();

        return cached.get();
    }

    public void recalc()
    {
        int total = pluginManager.getPlugins().size();
        int enabled = (int) pluginManager.getPlugins().stream()
                .filter(pluginManager::isPluginEnabled)
                .count();
        cached.set(new Counts(total, enabled));
    }
}