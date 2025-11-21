package net.runelite.client.plugins.pluginhub.com.afkdimmer;

import com.google.inject.Provides;
import javax.inject.Inject;

import net.runelite.api.Constants;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "Afk Dimmer",
		description = "Dims the screen when you are afk."
)

public class AfkDimmerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private AfkDimmerConfig config;

	@Inject
	private AfkDimmerOverlay overlay;

	@Inject
	public OverlayManager overlayManager;

	@Provides
	AfkDimmerConfig provideConfig(ConfigManager configManager){
		return configManager.getConfig((AfkDimmerConfig.class));
	}
	public boolean afkDimmerEnabled;

	@Override
	protected void startUp() throws Exception
	{
		afkDimmerEnabled = afkCheck();
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameTick(GameTick event){
		afkDimmerEnabled = afkCheck();
	}

	public boolean afkCheck(){
		boolean playerIdle = client.getLocalPlayer() != null
				&& client.getLocalPlayer().getAnimation() == -1; // Standing still animation codes
		if (config.idleUndim() && playerIdle){
			return false;
		}

		final int idleClientTicks = Math.min(client.getKeyboardIdleTicks(), client.getMouseIdleTicks());
		int afkTicks = (int) Math.ceil(config.afkTime()*1000 / Constants.CLIENT_TICK_LENGTH);
        return idleClientTicks > afkTicks;
	}
}
