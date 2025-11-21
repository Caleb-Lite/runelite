package net.runelite.client.plugins.pluginhub.com.afkhellratbehemoth;

import net.runelite.client.config.*;

@ConfigGroup("afkhellratbehemoth")
public interface AfkHellratBehemothConfig extends Config {
	@ConfigItem(
			keyName = "successthreshold",
			name = "Notify Below % Success",
			description = "Notify when the cat is less likely to win than the given percent",
			position = 1
	)
	// so even though this is in percent units, it still returns it as a whole number.
	// ex.: user input of 75 = 75.0 not 0.75
	@Units(Units.PERCENT)
	@Range(max = 100)
	default int getSuccessThreshold() { return 75; }


	@ConfigItem(
			keyName = "hpthreshold",
			name = "Hitpoints Threshold",
			description = "Notify when your cat is at or below a certain hitpoints threshold",
			position = 2
	)
	@Range(max = 14)
	default int getHitpointsThreshold() {
		return 2;
	}

	@ConfigItem(
			keyName = "notifykill",
			name = "Notify on Kill",
			description = "Notify when you kill a Hell-Rat Behemoth",
			position = 3
	)
	default boolean notifyOnKill()
	{
		return false;
	}

}