package net.runelite.client.plugins.pluginhub.dev.evaan;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "BA Heal Codes"
)
public class BAHealCodesPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private HealCodeOverlay healCodeOverlay;

	@Override
	public void startUp() {
		overlayManager.add(healCodeOverlay);
	}

	@Override
	public void shutDown() {
		overlayManager.remove(healCodeOverlay);
	}

	@Provides
	BAHealCodesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BAHealCodesConfig.class);
	}

	private static final Pattern wavePattern = Pattern.compile(".*---- Wave: (10|[1-9]) ----.*");

	public int wave = 0;
	public boolean hasHealerHorn = false;

	@Subscribe
	public void onGameTick(GameTick event) {
		if (wave != 0 && !ArrayUtils.contains(client.getTopLevelWorldView().getMapRegions(), 7509) && !ArrayUtils.contains(client.getTopLevelWorldView().getMapRegions(), 7508)) wave = 0;

		final ItemContainer itemContainer = client.getItemContainer(InventoryID.INVENTORY);
		if (itemContainer == null) {
			hasHealerHorn = false;
			return;
		}

		for (Item item : itemContainer.getItems()) {
			final int id = item.getId();
			if (id >= 10526 && id <= 10530) {
				hasHealerHorn = true;
				break;
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event) {
		if (event.getType() != ChatMessageType.GAMEMESSAGE) return;
		final Matcher waveMatcher = wavePattern.matcher(event.getMessage());
		if (waveMatcher.matches()) wave = Integer.parseInt(waveMatcher.group(1));
	}
}
