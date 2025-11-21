package net.runelite.client.plugins.pluginhub.com.areaSoundsWhitelist;

import com.google.inject.Provides;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.AreaSoundEffectPlayed;
import net.runelite.client.callback.ClientThread;


import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

@Slf4j
@PluginDescriptor(
	name = "Area Sounds Whitelist"
)
public class AreaSoundsWhitelistPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private AreaSoundsWhitelistConfig config;

	private Set<Integer> soundsToWhitelist;

	@Override
	protected void startUp() throws Exception
	{
		setupWhitelist();
	}

	@Override
	public void shutDown()
	{
		soundsToWhitelist.clear();
	}

	@Subscribe(priority = -2) // priority -2 to run after music plugin
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		GameState gameState = gameStateChanged.getGameState();

		// on map load mute ambient sounds
		if (gameState == GameState.LOGGED_IN)
		{
			if (config.muteAmbient()) {
				client.getAmbientSoundEffects().clear();
			}
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged) {
		setupWhitelist();
	}

	@Subscribe
	public void onAreaSoundEffectPlayed(AreaSoundEffectPlayed areaSoundEffectPlayed) {
		int soundId = areaSoundEffectPlayed.getSoundId();
		if (!soundsToWhitelist.contains(soundId)) {
			areaSoundEffectPlayed.consume();
		}
	}

	@Provides
	AreaSoundsWhitelistConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AreaSoundsWhitelistConfig.class);
	}

	private void setupWhitelist() {
		List<String> ids = Text.fromCSV(config.whitelist());
		soundsToWhitelist = ids.stream()
			.map(Integer::parseInt)
			.collect(Collectors.toCollection(HashSet::new));

		// Add NPC area sounds =========================================================================================
		// CoX ---------------------------------------------------------------------------------------------------------
		if (config.whitelistOlmAutos())
		{
			for (int sid : AreaSoundEffectID.COX_OLM_AUTOS)
			{
				soundsToWhitelist.add(sid);
			}
		}

		if (config.whitelistOlmSpecials())
		{
			for (int sid : AreaSoundEffectID.COX_OLM_SPECIALS)
			{
				soundsToWhitelist.add(sid);
			}
		}

		// ToB ---------------------------------------------------------------------------------------------------------
		if (config.whitelistMaidenBlood())
		{
			for (int sid : AreaSoundEffectID.TOB_MAIDEN_BLOOD_TARGET)
			{
				soundsToWhitelist.add(sid);
			}
		}

		if (config.whitelistBloatLimbs())
		{
			soundsToWhitelist.add(AreaSoundEffectID.TOB_BLOAT_LIMBS);
		}

		if (config.whitelistSotetsegAutos())
		{
			for (int sid : AreaSoundEffectID.TOB_SOTETSEG_AUTOS)
			{
				soundsToWhitelist.add(sid);
			}
		}

		if (config.whitelistSotetsegDeathBall())
		{
			soundsToWhitelist.add(AreaSoundEffectID.TOB_SOTETSEG_DEATH_BALL);
		}

		if (config.whitelistXarpusSpit())
		{
			soundsToWhitelist.add(AreaSoundEffectID.TOB_XARPUS_SPIT);
		}

		if (config.whitelistVerzikP2Autos())
		{
			soundsToWhitelist.add(AreaSoundEffectID.TOB_VERZIK_P2_AUTOS);
		}

		if (config.whitelistVerzikP3Autos())
		{
			for (int sid : AreaSoundEffectID.TOB_VERZIK_P3_AUTOS)
			{
				soundsToWhitelist.add(sid);
			}
		}

		if (config.whitelistVerzikYellows())
		{
			soundsToWhitelist.add(AreaSoundEffectID.TOB_VERZIK_YELLOW_POOLS);
		}

		// Misc --------------------------------------------------------------------------------------------------------
		if (config.whitelistRoyalTitansSlam())
		{
			for (int sid : AreaSoundEffectID.ROYAL_TITANS_SLAM)
			{
				soundsToWhitelist.add(sid);
			}
		}

		if (config.whitelistSolHereditAttacks())
		{
			for (int sid : AreaSoundEffectID.SOL_HEREDIT_ATTACKS)
			{
				soundsToWhitelist.add(sid);
			}
		}

		if (config.whitelistYama())
		{
			for (int sid : AreaSoundEffectID.YAMA)
			{
				soundsToWhitelist.add(sid);
			}
		}

		// Add item area sounds ========================================================================================
		if (config.whitelistBurningClaws())
		{
			soundsToWhitelist.add(AreaSoundEffectID.BURNING_CLAWS_SPEC);
		}

		if (config.whitelistDragonClaws())
		{
			for (int sid : AreaSoundEffectID.DRAGON_CLAWS_SPEC)
			{
				soundsToWhitelist.add(sid);
			}
		}
	}
}
