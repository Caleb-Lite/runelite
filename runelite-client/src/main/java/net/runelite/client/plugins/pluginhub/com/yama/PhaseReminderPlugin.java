package net.runelite.client.plugins.pluginhub.com.yama;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.Arrays;

@Slf4j
@PluginDescriptor(
	name = "Yama Phase Reminder"
)
public class PhaseReminderPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private PhaseReminderConfig config;

	@Inject
	private PhaseReminderOverlay phaseOverlay;

	@Inject
	private OverlayManager overlayManager;

	private int currentPhase = 0;
	private final int YAMA_ID = 14176;

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(phaseOverlay);
		currentPhase = 0;
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		LocalPoint lp = client.getLocalPlayer().getLocalLocation();
		int regionId = WorldPoint.fromLocalInstance(client,lp).getRegionID();

		if (regionId != 6045) {
			currentPhase = 0;
			overlayManager.remove(phaseOverlay);
		}

		if (currentPhase != 0) {
			overlayManager.add(phaseOverlay);
			phaseOverlay.renderPhase(currentPhase);
		}
	}

	@Subscribe
	private void onNpcSpawned(NpcSpawned event)
	{
		int npcId = event.getNpc().getId();
		if(npcId == YAMA_ID && currentPhase == 0)
		{
			currentPhase = 1;
		}
        int JUDGE_ID = 14180;
        if(npcId == JUDGE_ID && (currentPhase == 1 || currentPhase == 2))
		{
			currentPhase = currentPhase + 1;
		}
	}

	@Subscribe
	private void onNpcDespawned(NpcDespawned event)
	{
		int npcId = event.getNpc().getId();
		if(npcId == YAMA_ID && currentPhase == 3){
			overlayManager.remove(phaseOverlay);
			currentPhase = 0;
		}
	}

	@Subscribe
	public void onActorDeath(ActorDeath actorDeath)
	{
		Actor actor = actorDeath.getActor();
		if (actor == client.getLocalPlayer())
		{
			overlayManager.remove(phaseOverlay);
			currentPhase = 0;
		}
	}

	@Provides
	PhaseReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PhaseReminderConfig.class);
	}
}