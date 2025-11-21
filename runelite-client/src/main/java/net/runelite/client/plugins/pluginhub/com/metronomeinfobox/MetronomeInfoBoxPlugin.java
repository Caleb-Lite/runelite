package net.runelite.client.plugins.pluginhub.com.metronomeinfobox;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
	name = "Metronome Infobox"
)
public class MetronomeInfoBoxPlugin extends Plugin
{
	@Inject
	private MetronomeInfoBoxConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	private MetronomeInfoBox infoBox;

	@Override
	protected void startUp() throws Exception
	{
		infoBox = new MetronomeInfoBox(ImageUtil.loadImageResource(getClass(), "metronome-0.png"), this, config);
		infoBoxManager.addInfoBox(infoBox);
	}

	@Override
	protected void shutDown() throws Exception
	{
		if (infoBox != null) {
			infoBoxManager.removeInfoBox(infoBox);
		}
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		if (infoBox != null) {
			infoBox.onGameTick();
			infoBoxManager.updateInfoBoxImage(infoBox);
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if (infoBox != null && event.getGroup().equals(MetronomeInfoBoxConfig.GROUP)) {
			infoBox.onConfigChanged(config);
		}
	}

	@Provides
	MetronomeInfoBoxConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MetronomeInfoBoxConfig.class);
	}
}

