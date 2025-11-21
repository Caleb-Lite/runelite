package net.runelite.client.plugins.pluginhub.com.brooklyn.toblightup;

import javax.inject.Inject;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import java.util.Set;

@Slf4j
@PluginDescriptor(
	name = "ToB Light Up",
	description = "Removes the dark overlay from outside ToB",
	tags = {"tob", "hub", "brooklyn", "theatre", "tob", "overlay", "blood"}
)
public class TobLightUpPlugin extends Plugin
{
	@Inject
	private Client client;

	@Override
	protected void startUp() throws Exception
	{
		hideDarkness(false);
	}

	@Override
	protected void shutDown() throws Exception
	{
		hideDarkness(false);
	}

	private static final Set<Integer> VER_SINHAZA_REGIONS = ImmutableSet.of(
		14386,
		14642
	);

	private boolean isInVerSinhaza()
	{
		return VER_SINHAZA_REGIONS.contains(client.getLocalPlayer().getWorldLocation().getRegionID());
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		hideDarkness(isInVerSinhaza());
	}

	protected void hideDarkness(boolean hide)
	{
		Widget darkness = client.getWidget(28, 1);
		if (darkness != null)
		{
			darkness.setHidden(hide);
		}
	}
}

