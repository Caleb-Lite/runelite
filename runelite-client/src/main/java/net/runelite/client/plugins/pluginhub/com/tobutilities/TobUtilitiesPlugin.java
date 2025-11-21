package net.runelite.client.plugins.pluginhub.com.tobutilities;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.com.tobutilities.bloat.BloatHandler;
import net.runelite.client.plugins.pluginhub.com.tobutilities.bloat.BloatPlayerOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.metronome.MetronomeService;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.metronome.MetronomeOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.enums.Region;
import net.runelite.client.plugins.pluginhub.com.tobutilities.maiden.MaidenHandler;
import net.runelite.client.plugins.pluginhub.com.tobutilities.maiden.ScuffWarningOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.maiden.ScuffedNylocasOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.player.PlayerOneOrbOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.player.PlayerTwoOrbOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.player.PlayerThreeOrbOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.player.PlayerFourOrbOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.common.player.PlayerFiveOrbOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.nylocas.NylocasHandler;
import net.runelite.client.plugins.pluginhub.com.tobutilities.nylocas.NylocasOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.verzik.DawnbringerStatusMessage;
import net.runelite.client.plugins.pluginhub.com.tobutilities.verzik.LightbearerWarningOverlay;
import net.runelite.client.plugins.pluginhub.com.tobutilities.verzik.VerzikHandler;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;

import net.runelite.client.callback.Hooks;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.input.KeyManager;
import net.runelite.client.party.WSClient;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.HotkeyListener;

import javax.inject.Inject;
import java.awt.event.KeyEvent;

@PluginDescriptor(
	name = "ToB Utilities",
	description = "Various tools for the theatre of blood",
	tags = {"timers", "overlays", "tick", "theatre", "metronome", "tob", "maiden", "bloat", "nylo", "xarpus", "verzik"}
)
@Slf4j
public class TobUtilitiesPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;
	@Inject
	public InfoBoxManager infoBoxManager;
	@Inject
	public ConfigManager configManager;
	@Inject
	private Client client;
	@Inject
	private MetronomeOverlay metronomeOverlay;
	@Inject
	private PlayerOneOrbOverlay playerOneOrbOverlay;
	@Inject
	private PlayerTwoOrbOverlay playerTwoOrbOverlay;
	@Inject
	private PlayerThreeOrbOverlay playerThreeOrbOverlay;
	@Inject
	private PlayerFourOrbOverlay playerFourOrbOverlay;
	@Inject
	private PlayerFiveOrbOverlay playerFiveOrbOverlay;
	@Inject
	private ScuffedNylocasOverlay scuffedNylocasOverlay;
	@Inject
	private ScuffWarningOverlay scuffWarningOverlay;
	@Inject
	private BloatPlayerOverlay bloatPlayerOverlay;
	@Inject
	private LightbearerWarningOverlay lightbearerWarningOverlay;
	@Inject
	private NylocasOverlay nylocasOverlay;
	@Inject
	private TobUtilitiesConfig config;
	@Inject
	private KeyManager keyManager;
	@Inject
	private Hooks hooks;
	@Inject
	private MaidenHandler maidenHandler;
	@Inject
	private BloatHandler bloatHandler;
	@Inject
	private NylocasHandler nylocasHandler;

	@Inject
	private VerzikHandler verzikHandler;
	@Inject
	private MetronomeService metronomeService;
	@Inject
	private WSClient wsClient;


	public Region region = Region.UNKNOWN;
	private final Hooks.RenderableDrawListener drawListener = this::shouldDraw;

	@Provides
	TobUtilitiesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TobUtilitiesConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		Region oldRegion = region;
        // metronomeService updates region
		metronomeService.onGameTick(tick);
		if (oldRegion.equals(Region.BLOAT) && !region.equals(Region.BLOAT))
		{
			bloatHandler.onRoomExit();
		}
		if (region.equals(Region.MAIDEN))
		{
			maidenHandler.onGameTick(tick);
		}
		else if (region.equals(Region.VERZIK))
		{
			verzikHandler.onGameTick(tick);
		}
		else if (region.equals(Region.BLOAT))
		{
			bloatHandler.onGameTick(tick);
		}
	}


	private final HotkeyListener hideVerzikHotkeyListener = new HotkeyListener(() -> config.hideVerzikHotkey())
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			if (Region.VERZIK.equals(region))
			{
				verzikHandler.keyPressed(e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			hotkeyReleased();
		}
	};

	private final HotkeyListener metronomeResetHotkeyListener = new HotkeyListener(() -> config.metronomeResetHotkey())
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			metronomeService.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			hotkeyReleased();
		}
	};


	@Subscribe
	public void onNpcSpawned(NpcSpawned event)
	{
		if (Region.MAIDEN.equals(region))
		{
			maidenHandler.onNpcSpawned(event);
		}
		else if (Region.NYLOCAS.equals(region))
		{
			nylocasHandler.onNpcSpawned(event);
		}
	}


	@VisibleForTesting
	boolean shouldDraw(Renderable renderable, boolean drawingUI)
	{
		if (Region.BLOAT.equals(region))
		{
			return bloatHandler.shouldDraw(renderable, drawingUI);
		}

		if (Region.VERZIK.equals(region))
		{
			return verzikHandler.shouldDraw(renderable, drawingUI);
		}
		return true;
	}


	@Subscribe
	void onActorDeath(ActorDeath event)
	{
		if (Region.NYLOCAS.equals(region))
		{
			nylocasHandler.onActorDeath(event);
		}
	}

	@Subscribe
	public void onNpcChanged(NpcChanged event)
	{
		if (Region.NYLOCAS.equals(region))
		{
			nylocasHandler.onNpcChanged(event);
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned event)
	{
		if (Region.MAIDEN.equals(region))
		{
			maidenHandler.onNpcDespawned(event);
		}
		else if (Region.NYLOCAS.equals(region))
		{
			nylocasHandler.onNpcDespawned(event);
		}
	}

	@Subscribe
	public void onDawnbringerStatusMessage(DawnbringerStatusMessage message)
	{
		verzikHandler.onDawnbringerStatusMessage(message);
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		verzikHandler.onItemContainerChanged(event);
	}

    @Subscribe (priority = -1.0f)
    public void onBeforeRender(BeforeRender r)
    {
        if (region.equals(Region.BLOAT)) {
            bloatHandler.onBeforeRender(r);
        }
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event)
    {
        bloatHandler.onConfigChanged(event);
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        bloatHandler.onGameStateChanged(event);
    }

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(metronomeOverlay);
		overlayManager.add(playerOneOrbOverlay);
		overlayManager.add(playerTwoOrbOverlay);
		overlayManager.add(playerThreeOrbOverlay);
		overlayManager.add(playerFourOrbOverlay);
		overlayManager.add(playerFiveOrbOverlay);
		overlayManager.add(scuffedNylocasOverlay);
		overlayManager.add(scuffWarningOverlay);
		overlayManager.add(bloatPlayerOverlay);
		overlayManager.add(lightbearerWarningOverlay);
		overlayManager.add(nylocasOverlay);
        bloatHandler.startUp();
		verzikHandler.startUp();
		wsClient.registerMessage(DawnbringerStatusMessage.class);
		keyManager.registerKeyListener(hideVerzikHotkeyListener);
		keyManager.registerKeyListener(metronomeResetHotkeyListener);
		hooks.registerRenderableDrawListener(drawListener);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(metronomeOverlay);
		overlayManager.remove(playerOneOrbOverlay);
		overlayManager.remove(playerTwoOrbOverlay);
		overlayManager.remove(playerThreeOrbOverlay);
		overlayManager.remove(playerFourOrbOverlay);
		overlayManager.remove(playerFiveOrbOverlay);
		overlayManager.remove(scuffedNylocasOverlay);
		overlayManager.remove(scuffWarningOverlay);
		overlayManager.remove(bloatPlayerOverlay);
		overlayManager.remove(lightbearerWarningOverlay);
		overlayManager.remove(nylocasOverlay);
		nylocasHandler.shutDown();
		maidenHandler.shutDown();
        bloatHandler.shutDown();
		verzikHandler.shutDown();
		wsClient.unregisterMessage(DawnbringerStatusMessage.class);
		keyManager.unregisterKeyListener(hideVerzikHotkeyListener);
		keyManager.unregisterKeyListener(metronomeResetHotkeyListener);
		hooks.unregisterRenderableDrawListener(drawListener);
	}
}
