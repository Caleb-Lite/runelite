package net.runelite.client.plugins.pluginhub.com.onetickflick;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import net.runelite.api.SoundEffectVolume;

@ConfigGroup("metronome")
public interface OneTickFlickPluginConfiguration extends Config
{
	int VOLUME_MAX = SoundEffectVolume.HIGH;

	@Range(
		max = VOLUME_MAX
	)
	@ConfigItem(
		keyName = "tickVolume",
		name = "Tick volume",
		description = "Configures the volume of the tick sound. A value of 0 will disable tick sounds."
	)
	default int tickVolume()
	{
		return SoundEffectVolume.MEDIUM_HIGH;
	}

	@Range(
		max = VOLUME_MAX
	)
	@ConfigItem(
		keyName = "tockVolume",
		name = "Tock volume",
		description = "Configures the volume of the tock sound. A value of 0 will disable tock sounds."
	)
	default int tockVolume()
	{
		return SoundEffectVolume.MUTED;
	}
}

