package net.runelite.client.plugins.pluginhub.com.patchtimer;

import net.runelite.client.config.*;
import java.awt.*;

@ConfigGroup("patchtimer")
public interface PatchTimerConfig extends Config {
	@ConfigSection(
		name = "Timer Settings",
		description = "",
		position = 100
	)
	String timerSettings = "timersettings";

	@Alpha

	@ConfigItem(
			keyName = "backgroundSize",
			name = "Background Size",
			description = "How large the timer background should be",
			position = 0,
			section = timerSettings
	)
	default int getBackgroundSize() {
		return 16;
	}

	@ConfigItem(
			keyName = "backgroundColor",
			name = "Background Color",
			description = "The color the timer background should be",
			position = 1,
			section = timerSettings
	)

	@Alpha
	default Color getBackgroundColor() {
		return new Color(0,0,0,167);
	}

	@ConfigItem(
			keyName = "textColor",
			name = "Text Color",
			description = "The color the timer text should be",
			position = 3,
			section = timerSettings
	)
	default Color getTextColor() {
		return new Color(255,255,255,255);
	}

	@ConfigItem(
			keyName = "tick_early_color",
			name = "Early Tick Color",
			description = "The color to be 1t early to chop on tree respawn",
			position = 4,
			section = timerSettings
	)
	default Color getTick_early_color() {
		return new Color(255, 0, 0,255);
	}
	@ConfigItem(
			keyName = "tick_perfect_color",
			name = "Perfect Tick Color",
			description = "The color to be tick perfect to chop on tree respawn",
			position = 5,
			section = timerSettings
	)
	default Color getTick_perfect_color() {
		return new Color(64, 255, 0,255);
	}
	@ConfigItem(
			keyName = "tick_late_color",
			name = "Late Tick Color",
			description = "The color to be 1 tick late to chop on tree respawn",
			position = 6,
			section = timerSettings
	)
	default Color getTick_late_color() {
		return new Color(255, 239, 0,255);
	}
	@Alpha
	@ConfigItem(
			keyName = "assumeTick",
			name = "Assume Respawn tick",
			description = "If the timer should assume that the tree will respawn 1 tick sooner on single log roll",
			position = 101
	)
	default boolean getAssumeTick(){
		return false;
	}
}
