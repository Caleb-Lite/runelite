package net.runelite.client.plugins.pluginhub.com.agilitypyramidsliderblocktimer;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.NpcID;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Agility Pyramid Slider Block Timer"
)
public class AgilityPyramidSliderBlockTimerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Getter
	private final Set<NPC> npcs = new HashSet<>();

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private AgilityPyramidSliderBlockTimerOverlay agilityPyramidSliderBlockTimerOverlay;

	@Inject
	private AgilityPyramidSliderBlockTimerConfig config;

	private static final Set<Integer> AGILITY_PYRAMID_TRAP_NPCS = ImmutableSet.of(
		NpcID.PYRAMID_BLOCK, NpcID.PYRAMID_BLOCK_5788  // sliding pyramid blocks
	);

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(agilityPyramidSliderBlockTimerOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(agilityPyramidSliderBlockTimerOverlay);
		npcs.clear();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		switch (event.getGameState())
		{
			case HOPPING:
			case LOGIN_SCREEN:
				npcs.clear();
				break;
		}
	}

	@Provides
	AgilityPyramidSliderBlockTimerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AgilityPyramidSliderBlockTimerConfig.class);
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned)
	{
		NPC npc = npcSpawned.getNpc();

		if (AGILITY_PYRAMID_TRAP_NPCS.contains(npc.getId()))
		{
			npcs.add(npc);
		}
	}
}
