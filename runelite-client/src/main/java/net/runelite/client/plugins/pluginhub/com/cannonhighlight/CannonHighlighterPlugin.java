package net.runelite.client.plugins.pluginhub.com.cannonhighlight;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.ArrayList;

import static net.runelite.api.ObjectID.CANNON_BASE;
import static net.runelite.api.ObjectID.CANNON_BASE_43029;

@Slf4j
@PluginDescriptor(
	name = "Cannon Highlighter"
)
public class CannonHighlighterPlugin extends Plugin
{
	@Getter(AccessLevel.PACKAGE)
	private boolean cannonPlaced = false;

	@Getter(AccessLevel.PACKAGE)
	private WorldPoint cannonPosition = null;

	@Inject
	private Client client;

	@Inject
	private CannonHighlighterConfig config;

	@Inject
	private CannonHighlighterOverlay npcOverlay;

	@Inject
	private OverlayManager overlayManager;

	ArrayList<LocalPoint> cannonDoubleHitSpots = new ArrayList<>();
	ArrayList<LocalPoint> cannonNeverHitSpots = new ArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(npcOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(npcOverlay);
		cannonPlaced = false;
		cannonPosition = null;
		cannonNeverHitSpots.clear();
		cannonDoubleHitSpots.clear();
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event)
	{
		GameObject gameObject = event.getGameObject();
		int placedObjectId = gameObject.getId();

		Player localPlayer = client.getLocalPlayer();
		if ((placedObjectId == CANNON_BASE || placedObjectId == CANNON_BASE_43029) && !cannonPlaced)
		{
			if (localPlayer.getWorldLocation().distanceTo(gameObject.getWorldLocation()) <= 2
					&& localPlayer.getAnimation() == AnimationID.BURYING_BONES)
			{
				cannonPosition = gameObject.getWorldLocation();
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.SPAM && event.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		if (event.getMessage().equals("You add the furnace."))
		{
			cannonPlaced = true;
		}

		if (event.getMessage().contains("You pick up the cannon")
				|| event.getMessage().contains("Your cannon has decayed. Speak to Nulodion to get a new one!"))
		{
			cannonPlaced = false;
			cannonPosition = null;
			cannonNeverHitSpots.clear();
			cannonDoubleHitSpots.clear();
		}
	}

	@Provides
	CannonHighlighterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CannonHighlighterConfig.class);
	}
}
