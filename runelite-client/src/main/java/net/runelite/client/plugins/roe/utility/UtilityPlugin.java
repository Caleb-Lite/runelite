package net.runelite.client.plugins.roe.utility;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@PluginDescriptor(
	name = "ROE Utility",
	description = "Utility tools for reverse-engineering game mechanics",
	tags = {"roe", "utility", "imposter", "varbit", "multiloc", "objects"}
)
public class UtilityPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private UtilityConfig config;

	private AnimationSession currentSession = null;

	private static class AnimationSession
	{
		WorldPoint location;
		int startTick;
		int lastAnimationTick;
		List<StateChange> changes = new ArrayList<>();
		Map<Integer, Integer> initialVarbits = new HashMap<>();
		Map<Integer, Integer> currentVarbits = new HashMap<>();
		int initialObjectId;
		String objectName;
	}

	private static class StateChange
	{
		int tick;
		int varbitId;
		int oldValue;
		int newValue;
		int oldObjectId;
		int newObjectId;
	}

	@Provides
	UtilityConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(UtilityConfig.class);
	}

	@Override
	protected void startUp()
	{
		log.info("ROE Utility plugin started");
	}

	@Override
	protected void shutDown()
	{
		currentSession = null;
		log.info("ROE Utility plugin stopped");
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (!config.trackImposters())
		{
			return;
		}

		if (!isGameObjectAction(event.getMenuAction()))
		{
			return;
		}

		WorldPoint location = WorldPoint.fromScene(client, event.getParam0(),
			event.getParam1(), client.getPlane());
		GameObject gameObject = findGameObjectAtLocation(location);
		if (gameObject == null)
		{
			return;
		}

		ObjectComposition comp = client.getObjectDefinition(gameObject.getId());
		if (comp.getImpostorIds() == null)
		{
			return;
		}

		// Collect all relevant varbits
		Map<Integer, Integer> varbits = new HashMap<>();
		int varbitId = comp.getVarbitId();
		if (varbitId != -1)
		{
			varbits.put(varbitId, client.getVarbitValue(varbitId));
		}

		int varPlayerId = comp.getVarPlayerId();
		if (varPlayerId != -1)
		{
			varbits.put(varPlayerId, client.getVarpValue(varPlayerId));
		}

		if (varbits.isEmpty())
		{
			return;
		}

		// Start a new session
		AnimationSession session = new AnimationSession();
		session.location = location;
		session.startTick = client.getTickCount();
		session.lastAnimationTick = client.getTickCount();
		session.initialVarbits = new HashMap<>(varbits);
		session.currentVarbits = new HashMap<>(varbits);
		session.initialObjectId = gameObject.getId();
		session.objectName = comp.getName();

		currentSession = session;
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged event)
	{
		if (event.getActor() != client.getLocalPlayer())
		{
			return;
		}

		if (currentSession == null)
		{
			return;
		}

		int animation = client.getLocalPlayer().getAnimation();

		// Check if this is a relevant animation (not idle/walking)
		if (animation != -1 && animation != 808 && animation != 813)
		{
			// Update last animation tick - this resets the 2-tick countdown
			currentSession.lastAnimationTick = client.getTickCount();
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (currentSession == null)
		{
			return;
		}

		int currentTick = client.getTickCount();
		int ticksSinceLastAnimation = currentTick - currentSession.lastAnimationTick;

		// Check for varbit changes
		boolean anyChange = false;
		for (Map.Entry<Integer, Integer> entry : currentSession.currentVarbits.entrySet())
		{
			int varbitId = entry.getKey();
			int lastValue = entry.getValue();
			int newValue = client.getVarbitValue(varbitId);

			if (newValue != lastValue)
			{
				anyChange = true;

				// Find the old and new object IDs
				GameObject obj = findGameObjectAtLocation(currentSession.location);
				int oldObjectId = findObjectIdForVarbitValue(currentSession.initialObjectId,
					varbitId, lastValue);
				int newObjectId = obj != null ? obj.getId() : -1;

				// Record the change
				StateChange change = new StateChange();
				change.tick = currentTick;
				change.varbitId = varbitId;
				change.oldValue = lastValue;
				change.newValue = newValue;
				change.oldObjectId = oldObjectId;
				change.newObjectId = newObjectId;

				currentSession.changes.add(change);
				currentSession.currentVarbits.put(varbitId, newValue);
			}
		}

		// If animation stopped 2+ ticks ago and we have changes, output summary
		if (ticksSinceLastAnimation >= 2 && !currentSession.changes.isEmpty())
		{
			outputSummary(currentSession);
			currentSession = null;
			return;
		}

		// Timeout after 10 ticks of no activity
		if (currentTick - currentSession.startTick > 10 && ticksSinceLastAnimation >= 2)
		{
			if (!currentSession.changes.isEmpty())
			{
				outputSummary(currentSession);
			}
			currentSession = null;
		}
	}

	private void outputSummary(AnimationSession session)
	{
		if (session.changes.isEmpty())
		{
			return;
		}

		// Group changes by varbit
		Map<Integer, List<StateChange>> changesByVarbit = session.changes.stream()
			.collect(Collectors.groupingBy(c -> c.varbitId));

		StringBuilder summary = new StringBuilder();
		summary.append("=== Imposter Changes ===\n");

		if (config.showObjectName())
		{
			summary.append(String.format("Object: %s", session.objectName));
			if (config.showLocation())
			{
				summary.append(String.format(" @ (%d, %d)",
					session.location.getX(), session.location.getY()));
			}
			summary.append("\n");
		}
		else if (config.showLocation())
		{
			summary.append(String.format("Location: (%d, %d)\n",
				session.location.getX(), session.location.getY()));
		}

		summary.append(String.format("Duration: %d ticks\n",
			client.getTickCount() - session.startTick));

		for (Map.Entry<Integer, List<StateChange>> entry : changesByVarbit.entrySet())
		{
			int varbitId = entry.getKey();
			List<StateChange> changes = entry.getValue();

			// Get initial and final values
			int initialValue = session.initialVarbits.get(varbitId);
			int finalValue = session.currentVarbits.get(varbitId);

			// Get initial and final object IDs
			StateChange firstChange = changes.get(0);
			StateChange lastChange = changes.get(changes.size() - 1);

			summary.append(String.format("Imposter: %d -> %d | Varbit %d: %d -> %d",
				firstChange.oldObjectId, lastChange.newObjectId,
				varbitId, initialValue, finalValue));

			// Show intermediate changes if multiple and config enabled
			if (config.showIntermediateChanges() && changes.size() > 1)
			{
				summary.append(" (");
				for (int i = 0; i < changes.size(); i++)
				{
					StateChange change = changes.get(i);
					summary.append(String.format("%d->%d", change.oldValue, change.newValue));
					if (i < changes.size() - 1)
					{
						summary.append(", ");
					}
				}
				summary.append(")");
			}

			if (config.showObjectName())
			{
				summary.append(String.format(" | %s", session.objectName));
			}

			if (config.showLocation())
			{
				summary.append(String.format(" @ (%d, %d)",
					session.location.getX(), session.location.getY()));
			}

			summary.append("\n");
		}

		summary.append("========================");

		// Output to chat
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", summary.toString(), "");
		log.info(summary.toString());
	}

	private int findObjectIdForVarbitValue(int baseObjectId, int varbitId, int value)
	{
		ObjectComposition comp = client.getObjectDefinition(baseObjectId);
		int[] impostorIds = comp.getImpostorIds();

		if (impostorIds != null && value >= 0 && value < impostorIds.length)
		{
			return impostorIds[value];
		}

		return baseObjectId;
	}

	private GameObject findGameObjectAtLocation(WorldPoint location)
	{
		if (client.getLocalPlayer() == null)
		{
			return null;
		}

		Tile[][][] tiles = client.getScene().getTiles();
		int plane = location.getPlane();

		if (plane < 0 || plane >= tiles.length)
		{
			return null;
		}

		int sceneX = location.getX() - client.getBaseX();
		int sceneY = location.getY() - client.getBaseY();

		if (sceneX < 0 || sceneX >= tiles[plane].length ||
			sceneY < 0 || sceneY >= tiles[plane][sceneX].length)
		{
			return null;
		}

		Tile tile = tiles[plane][sceneX][sceneY];
		if (tile == null)
		{
			return null;
		}

		// Check all game objects on the tile
		for (GameObject gameObject : tile.getGameObjects())
		{
			if (gameObject != null)
			{
				return gameObject;
			}
		}

		return null;
	}

	private boolean isGameObjectAction(MenuAction action)
	{
		return action == MenuAction.GAME_OBJECT_FIRST_OPTION ||
			action == MenuAction.GAME_OBJECT_SECOND_OPTION ||
			action == MenuAction.GAME_OBJECT_THIRD_OPTION ||
			action == MenuAction.GAME_OBJECT_FOURTH_OPTION ||
			action == MenuAction.GAME_OBJECT_FIFTH_OPTION ||
			action == MenuAction.WIDGET_TARGET_ON_GAME_OBJECT;
	}
}
