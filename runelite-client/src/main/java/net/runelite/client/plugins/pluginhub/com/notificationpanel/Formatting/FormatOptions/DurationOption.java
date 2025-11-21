package net.runelite.client.plugins.pluginhub.com.notificationpanel.Formatting.FormatOptions;

import net.runelite.client.plugins.pluginhub.com.notificationpanel.Formatting.FormatOption;
import net.runelite.client.plugins.pluginhub.com.notificationpanel.NotificationPanelConfig;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

public class DurationOption extends FormatOption
{
	@Getter
	@Setter
	private static NotificationPanelConfig.TimeUnit timeUnit = NotificationPanelConfig.TimeUnit.SECONDS;
	@Getter
	private int duration;

	public DurationOption()
	{
		optionName = "duration";
	}

	public DurationOption(int duration)
	{
		this.duration = duration;
	}

	public Optional<DurationOption> parseValue(String value)
	{
		int newDuration = Integer.parseInt(value);
		DurationOption option = new DurationOption(newDuration);
		return Optional.of(option);
	}
}

