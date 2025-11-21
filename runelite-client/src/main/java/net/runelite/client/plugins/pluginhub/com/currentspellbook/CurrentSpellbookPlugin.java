package net.runelite.client.plugins.pluginhub.com.currentspellbook;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Current Spellbook"
)
public class CurrentSpellbookPlugin extends Plugin
{
	private static final int SPELLBOOK_VARBIT = 4070;

	@Inject
	private Client client;

	@Inject
	private CurrentSpellbookConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CurrentSpellbookOverlay currentSpellbookOverlay;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(currentSpellbookOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(currentSpellbookOverlay);
	}

	@Provides
	CurrentSpellbookConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CurrentSpellbookConfig.class);
	}

	int getCurrentSpellbook()
	{
		return client.getVarbitValue(SPELLBOOK_VARBIT);
	}

}
