package net.runelite.client.plugins.pluginhub.com.fishingspot;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.NPC;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.FishingSpot;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@PluginDescriptor(
	name = "Fishing Spot Timer",
    description = "Display a timer above fishing spots. The timer is not exact, it runs until a configurable expected move time.",
    tags = {"overlay", "fishing"}
)
public class FishingSpotTimerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private FishingSpotTimerConfig config;

    @Inject
    private FishingSpotTimerOverlay spotOverlay;

    @Inject
    private OverlayManager overlayManager;

    @Getter(AccessLevel.PACKAGE)
    private final List<NPC> fishingSpots = new ArrayList<>();

    @Getter(AccessLevel.PACKAGE)
    private final Map<Integer, FishingSpotSpawn> fishingSpotSpawns = new HashMap<>();

	@Override
	protected void startUp() throws Exception
	{
        overlayManager.add(spotOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
        overlayManager.remove(spotOverlay);
        fishingSpots.clear();
        fishingSpotSpawns.clear();
	}

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged)
    {
        GameState gameState = gameStateChanged.getGameState();
        if (gameState == GameState.CONNECTION_LOST || gameState == GameState.LOGIN_SCREEN || gameState == GameState.HOPPING)
        {
            fishingSpots.clear();
            fishingSpotSpawns.clear();
        }
    }

    @Subscribe
    public void onGameTick(GameTick event) {
        for (NPC npc : fishingSpots)
        {
            final int id = npc.getIndex();
            final FishingSpotSpawn spawn = fishingSpotSpawns.get(id);

            if (spawn == null || !spawn.getLoc().equals(npc.getWorldLocation()))
            {
                fishingSpotSpawns.put(id, new FishingSpotSpawn(npc.getWorldLocation(), Instant.now()));
            }
        }
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event)
    {
        final NPC npc = event.getNpc();

        if (FishingSpot.findSpot(npc.getId()) == null)
        {
            return;
        }

        fishingSpots.add(npc);
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned npcDespawned)
    {
        final NPC npc = npcDespawned.getNpc();

        fishingSpots.remove(npc);
        fishingSpotSpawns.remove(npc.getIndex());
    }

	@Provides
    FishingSpotTimerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FishingSpotTimerConfig.class);
	}
}
