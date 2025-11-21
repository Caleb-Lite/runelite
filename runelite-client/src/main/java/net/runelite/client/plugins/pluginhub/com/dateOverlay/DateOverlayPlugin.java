package net.runelite.client.plugins.pluginhub.com.dateOverlay;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.task.Schedule;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Slf4j
@PluginDescriptor(
	name = "Date Overlay"
)
public class DateOverlayPlugin extends Plugin
{
	public String currentDateTime;
	@Inject
	private Client client;
	@Inject
	private DateOverlayConfig config;
	@Inject
	private DateOverlayOverlay overlay;
	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception {
		overlayManager.add(overlay);
	}

	@Schedule(period = 1, unit = ChronoUnit.SECONDS)
	public void timeSchedule() {
		if (client.getGameState() == GameState.LOGGED_IN) {
			String dateFormat = getDateFormat();
			LocalDateTime localDateTime = java.time.LocalDateTime.now();
			DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(dateFormat);
			currentDateTime = localDateTime.format(formatter);
		}
	}

	public String getDateFormat() {
		String dateformat = "dd-MM-yyyy";

		switch (config.dateTimeFormat()) {
			case yyyyMMdd:
				dateformat = "yyyy-MM-dd";
				break;
			case MMddyyyy:
				dateformat = "MM-dd-yyyy";
				break;
		}

		if(config.nameOfMonth())
			dateformat = dateformat.replace("MM", "MMMM");
		if(config.shortYear())
			dateformat = dateformat.replace("yyyy", "yy");

		if(config.showTime())
			dateformat += " HH:mm:ss";

		return dateformat;
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(overlay);
	}

	@Provides
	DateOverlayConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(DateOverlayConfig.class);
	}
}

