package net.runelite.client.plugins.pluginhub.com.dt2PerfectBossFailure.vardorvisUtils;

import net.runelite.client.plugins.pluginhub.com.dt2PerfectBossFailure.dt2pbfConfig;
import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.GameObject;
import net.runelite.api.GameState;
import net.runelite.api.Scene;
import net.runelite.api.Tile;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import org.apache.commons.lang3.ArrayUtils;

@Slf4j
public class VardorvisPillarHider
{
	@Inject
	private dt2pbfConfig config;

	@Inject
	private ClientThread clientThread;

	private static final Set<Integer> HIDE = ImmutableSet.of(
		48419,
		48420,
		48422,
		48423,
		48424,
		48426,
		48427,
		48428
	);

	@Getter
	private HashSet<Tile> pillarTiles = new HashSet<>();

	@Inject
	private Client client;

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			hide();
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged)
	{
		if(!configChanged.getGroup().equalsIgnoreCase(config.DT2_UTILITIES_CONFIG_GROUP))
		{
			return;
		}
		if(configChanged.getKey().equals("hidePillars"))
		{
			if(Boolean.parseBoolean(configChanged.getNewValue()))
			{
				hide();
				return;
			}
			clientThread.invoke(() ->
			{
				if (client.getGameState() == GameState.LOGGED_IN)
				{
					client.setGameState(GameState.LOADING);
				}
			});
		}
	}

	public void hide()
	{
		pillarTiles.clear();
		if (!isInVardorvisArea() || !config.hidePillars())
		{
			return;
		}

		Scene scene = client.getScene();
		Tile[][] tiles = scene.getTiles()[0];
		int cnt = 0;
		for (int x = 0; x < Constants.SCENE_SIZE; ++x)
		{
			for (int y = 0; y < Constants.SCENE_SIZE; ++y)
			{
				Tile tile = tiles[x][y];
				if (tile == null)
				{
					continue;
				}

				for (GameObject gameObject : tile.getGameObjects())
				{
					if (gameObject != null && HIDE.contains(gameObject.getId()))
					{
						scene.removeGameObject(gameObject);
						pillarTiles.add(tile);
						++cnt;
						break;
					}
				}
			}
			log.debug("Removed {} objects", cnt);
		}
	}

	private boolean isInVardorvisArea()
	{
		// 17, 53
		return ArrayUtils.contains(client.getMapRegions(), (17 << 8) | 53);
	}
}
