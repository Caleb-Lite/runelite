package net.runelite.client.plugins.pluginhub.com.wintertodtIdle;

import static net.runelite.api.AnimationID.IDLE;

import java.time.Duration;
import java.time.Instant;

import javax.inject.Inject;

import com.google.inject.Provides;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(name = "Wintertodt Idle")
public class WintertodtIdlePlugin extends Plugin {
	private static final int WINTERTODT_REGION = 6462;

	private Instant lastActionTime;

	@Inject
	private Client client;

	@Inject
	private WintertodtIdleConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private WintertodtIdleOverlay overlay;

	@Getter(AccessLevel.PACKAGE)
	private boolean isOverlayShown;

	@Subscribe
	public void onGameTick(GameTick gameTick) {
		if (isInWintertodtRegion()) {
			if (!isPlayerIdle()) {
				this.lastActionTime = Instant.now();

				if (isOverlayShown) {
					log.info("Hiding overlay");

					toggleOverlay(false);
				}
			} else if (isIdleTimerMet() && !isOverlayShown) {
				log.info("Showing overlay");

				toggleOverlay(true);
			}
		} else {
			this.lastActionTime = null;

			if (isOverlayShown) {
				toggleOverlay(false);
			}
		}
	}

	@Provides
	WintertodtIdleConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(WintertodtIdleConfig.class);
	}

	private boolean isInWintertodtRegion() {
		boolean isInWintertodtRegion = false;

		if (client.getLocalPlayer() != null) {
			isInWintertodtRegion = client.getLocalPlayer().getWorldLocation().getRegionID() == WINTERTODT_REGION;
		}

		return isInWintertodtRegion;
	}

	private boolean isPlayerIdle() {
		boolean isPlayerIdle = false;

		if (client.getLocalPlayer() != null) {
			int currentAnimation = client.getLocalPlayer().getAnimation();
			isPlayerIdle = currentAnimation == IDLE;
		}

		return isPlayerIdle;
	}

	private void toggleOverlay(boolean isOverlayShown) {
		if (isOverlayShown) {
			overlayManager.add(overlay);
		} else {
			overlayManager.remove(overlay);
		}

		this.isOverlayShown = isOverlayShown;
	}

	private boolean isIdleTimerMet() {
		boolean isIdleTimerMet = false;

		if (config.idleDelay() > 0 && lastActionTime != null) {
			Duration actionTimeout = Duration.ofSeconds(config.idleDelay());
			Duration sinceAction = Duration.between(lastActionTime, Instant.now());

			if (sinceAction.compareTo(actionTimeout) >= 0) {
				log.debug("Now idle!");
				isIdleTimerMet = true;
			}
		}

		return isIdleTimerMet;
	}
}
