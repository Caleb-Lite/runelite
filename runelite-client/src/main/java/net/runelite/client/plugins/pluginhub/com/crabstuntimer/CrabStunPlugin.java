package net.runelite.client.plugins.pluginhub.com.crabstuntimer;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GraphicChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@PluginDescriptor(
		name = "Crab Stun Timers",
		description = "Show crab stun timers",
		tags = {"overlay", "raid", "pvm", "timers"}
)
public class CrabStunPlugin extends Plugin {
	@Inject
	private Client client;

	@Provides
	CrabStunConfig getConfig(ConfigManager configManager) {
		return configManager.getConfig(CrabStunConfig.class);
	}

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CrabStunOverlay overlay;

	@Getter(AccessLevel.PACKAGE)
	private final List<CrabStun> stunEvents = new ArrayList<>();

	private static final int RAID_PARTY_SIZE = 5424;

	@Override
	protected void startUp() {
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception {
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGraphicChanged(GraphicChanged event) {
		if (client.getVarbitValue(Varbits.IN_RAID) != 1) {
			return;
		}
		final int CRAB_STUN_GRAPHIC = 245;
		Actor actor = event.getActor();
		if (actor.getName() != null && actor.getName().contains("Jewelled Crab") && actor.hasSpotAnim(CRAB_STUN_GRAPHIC)) {
			WorldPoint worldPoint = actor.getWorldLocation();
			CrabStun stunEvent = new CrabStun(actor, worldPoint, Instant.now(), getStunDurationTicks(), 0);
			for (CrabStun stun : stunEvents) {
				if (stun.getCrab().equals(actor)) {
					stun.setStartTime(Instant.now());
				}
			}
			overlay.getRandomIntervalTimers().removeIf(timer -> (timer.getCrab().equals(stunEvent.getCrab())));
			stunEvents.add(stunEvent);
		}
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		for (Iterator<CrabStun> it = overlay.getRandomIntervalTimers().iterator(); it.hasNext(); ) {
			try {
				CrabStun stun = it.next();
				Point crabStunPoint = new Point(stun.getWorldPoint().getX(), stun.getWorldPoint().getY());
				Point crabCurrentPoint = new Point(stun.getCrab().getWorldLocation().getX(), stun.getCrab().getWorldLocation().getY());

				if (crabStunPoint.distance(crabCurrentPoint) > 0) {
					it.remove();
				}
			} catch (Exception e) {
				return;
			}
		}
	}

	private int getStunDurationTicks() {
		switch (client.getVarbitValue(RAID_PARTY_SIZE)) {
			case 1:
				return TeamSize.ONE.getStunDuration();
			case 2:
			case 3:
				return TeamSize.TWO_TO_THREE.getStunDuration();
			case 4:
			case 5:
				return TeamSize.FOUR_TO_FIVE.getStunDuration();
			default:
				return TeamSize.SIX_PLUS.getStunDuration();
		}
	}
}
