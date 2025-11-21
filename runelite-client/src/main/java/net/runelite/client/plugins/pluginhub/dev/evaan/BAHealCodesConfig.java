package net.runelite.client.plugins.pluginhub.dev.evaan;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("ba-heal-codes")
public interface BAHealCodesConfig extends Config
{
	@ConfigItem(
			keyName = "wave4",
			name = "Wave 4",
			description = "Configures which heal code to display in wave 4",
			position = 0
	)
	default Waves.Wave4 wave4()
	{
		return Waves.Wave4.REGULAR;
	}

	@ConfigItem(
			keyName = "wave4custom",
			name = "Wave 4 Custom Text",
			description = "Custom text to be displayed for wave 4 if custom is selected",
			position = 1
	)
	default String wave4custom()
	{
		return "";
	}

	@ConfigItem(
			keyName = "wave5",
			name = "Wave 5",
			description = "Configures which heal code to display in wave 5",
			position = 2
	)
	default Waves.Wave5 wave5()
	{
		return Waves.Wave5.REGULAR;
	}

	@ConfigItem(
			keyName = "wave5custom",
			name = "Wave 5 Custom Text",
			description = "Custom text to be displayed for wave 5 if custom is selected",
			position = 3
	)
	default String wave5custom()
	{
		return "";
	}

	@ConfigItem(
			keyName = "wave6",
			name = "Wave 6",
			description = "Configures which heal code to display in wave 6",
			position = 4
	)
	default Waves.Wave6 wave6()
	{
		return Waves.Wave6.REGULAR;
	}

	@ConfigItem(
			keyName = "wave6custom",
			name = "Wave 6 Custom Text",
			description = "Custom text to be displayed for wave 6 if custom is selected",
			position = 5
	)
	default String wave6custom()
	{
		return "";
	}

	@ConfigItem(
			keyName = "wave7",
			name = "Wave 7",
			description = "Configures which heal code to display in wave 7",
			position = 6
	)
	default Waves.Wave7 wave7()
	{
		return Waves.Wave7.REGULAR;
	}

	@ConfigItem(
			keyName = "wave7custom",
			name = "Wave 7 Custom Text",
			description = "Custom text to be displayed for wave 7 if custom is selected",
			position = 7
	)
	default String wave7custom()
	{
		return "";
	}

	@ConfigItem(
			keyName = "wave8",
			name = "Wave 8",
			description = "Configures which heal code to display in wave 8",
			position = 8
	)
	default Waves.Wave8 wave8()
	{
		return Waves.Wave8.REGULAR;
	}

	@ConfigItem(
			keyName = "wave8custom",
			name = "Wave 8 Custom Text",
			description = "Custom text to be displayed for wave 8 if custom is selected",
			position = 9
	)
	default String wave8custom()
	{
		return "";
	}

	@ConfigItem(
			keyName = "wave9",
			name = "Wave 9",
			description = "Configures which heal code to display in wave 9",
			position = 10
	)
	default Waves.Wave9 wave9()
	{
		return Waves.Wave9.REGULAR;
	}

	@ConfigItem(
			keyName = "wave9custom",
			name = "Wave 9 Custom Text",
			description = "Custom text to be displayed for wave 9 if custom is selected",
			position = 11
	)
	default String wave9custom()
	{
		return "";
	}

	@ConfigItem(
			keyName = "wave10",
			name = "Wave 10",
			description = "Configures which heal code to display in wave 10",
			position = 12
	)
	default Waves.Wave10 wave10()
	{
		return Waves.Wave10.REGULAR;
	}

	@ConfigItem(
			keyName = "wave10custom",
			name = "Wave 10 Custom Text",
			description = "Custom text to be displayed for wave 10 if custom is selected",
			position = 13
	)
	default String wave10custom()
	{
		return "";
	}
}
