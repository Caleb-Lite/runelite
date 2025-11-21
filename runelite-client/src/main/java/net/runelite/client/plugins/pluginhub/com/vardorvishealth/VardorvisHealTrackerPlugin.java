package net.runelite.client.plugins.pluginhub.com.vardorvishealth;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.NpcDespawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;


@Slf4j
@PluginDescriptor(
	name = "Vardorvis Health Tracker",
		description = "Tracks how much Vardorvis heals over the fight"
)
public class VardorvisHealTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private VardorvisHealTrackerOverlay overlay;  // Injecting the overlay class

	@Inject
	private OverlayManager overlayManager;
	@Getter
	private int totalHealing = 0;

	public void sendChatMessage(String message) {
		ChatMessageBuilder messageBuilder = new ChatMessageBuilder();
		messageBuilder.append(ChatColorType.NORMAL)
				.append(message);
		String builtMessage = messageBuilder.build();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", builtMessage, null);
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned event) {
		if ((event.getNpc().getId() == 12223) && (getTotalHealing()!=0)){
			sendChatMessage("Vardorvis healed for " + getTotalHealing() + " health in total that fight.");
			resetHealing();
		}
	}

	public void resetHealing() {
		totalHealing = 0;
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event) {
		try {
			Actor actor = event.getActor();
			if (actor instanceof NPC) {
				NPC npc = (NPC) actor;
				if (npc.getId() == 12223) {
					Hitsplat hitsplat = event.getHitsplat();
					if (hitsplat.getHitsplatType() == HitsplatID.HEAL) {
						totalHealing += hitsplat.getAmount();
					}
				}
			}
		} catch (Exception e) {
			log.error("Error processing HitsplatApplied event", e);
		}
	}

	@Override
	protected void startUp() throws Exception {
		log.info("Starting up Vardorvis Heal Tracker Plugin");
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception {
		overlayManager.remove(overlay);
	}



	@Inject
	private VardorvisHealthConfig config;

	@Inject
	private ConfigManager configManager;

	@Provides
	VardorvisHealthConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(VardorvisHealthConfig.class);
	}
}

