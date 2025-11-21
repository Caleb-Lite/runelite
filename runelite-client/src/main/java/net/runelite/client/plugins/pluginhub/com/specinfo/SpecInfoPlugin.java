package net.runelite.client.plugins.pluginhub.com.specinfo;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;

import javax.imageio.ImageIO;
import java.util.Objects;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@Slf4j
@PluginDescriptor(
		name = "Special Attack Infobox"
)
public class SpecInfoPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private SpecInfoConfig config;

	@Override
	protected void startUp() throws Exception
	{
		infoBoxManager.addInfoBox(new SpecInfoBox(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/special_attack_sprite.png"))), this, client, config));
	}

	@Override
	protected void shutDown() throws Exception
	{
		infoBoxManager.removeIf(t -> t instanceof SpecInfoBox);
	}

	@Provides
	SpecInfoConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SpecInfoConfig.class);
	}
}