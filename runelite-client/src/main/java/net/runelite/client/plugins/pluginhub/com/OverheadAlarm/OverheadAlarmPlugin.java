package net.runelite.client.plugins.pluginhub.com.OverheadAlarm;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Overhead Alarm"
)
public class OverheadAlarmPlugin extends Plugin
{
	@Inject
	private Client client;

    @Inject
    private Notifier notifier;

	@Inject
	private OverheadAlarmConfig config;

	@Subscribe
    public void onHitsplatApplied(HitsplatApplied event)
	{
        Actor target = event.getActor();
        Player local = client.getLocalPlayer();

        if(target != null && target.equals(local)){
            boolean meleeOn = client.getVarbitValue(Varbits.PRAYER_PROTECT_FROM_MELEE) == 1;
            boolean rangeOn = client.getVarbitValue(Varbits.PRAYER_PROTECT_FROM_MISSILES) == 1;
            boolean mageOn  = client.getVarbitValue(Varbits.PRAYER_PROTECT_FROM_MAGIC) == 1;

            boolean valid =
                (meleeOn && config.enableMelee()) ||
                (rangeOn && config.enableRange()) ||
                (mageOn  && config.enableMagic());

            if (!valid)
            {
                notifier.notify("You're being hit without a valid overhead prayer!");
            }
        }
	}

	@Provides
    OverheadAlarmConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(OverheadAlarmConfig.class);
	}
}
