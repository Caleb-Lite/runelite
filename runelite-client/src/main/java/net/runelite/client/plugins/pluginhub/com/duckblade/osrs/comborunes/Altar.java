package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.comborunes;

import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.coords.WorldPoint;

@RequiredArgsConstructor
public enum Altar
{

	AIR(11339, ComboRunesOnlyConfig::removeAir),
	WATER(10827, ComboRunesOnlyConfig::removeWater),
	EARTH(10571, ComboRunesOnlyConfig::removeEarth),
	FIRE(10315, ComboRunesOnlyConfig::removeFire);

	private final int region;
	private final Function<ComboRunesOnlyConfig, Boolean> configGetter;

	@Getter
	private boolean configEnabled;

	public boolean inAltar(WorldPoint wp)
	{
		return wp.getRegionID() == this.region;
	}

	public void refreshConfig(ComboRunesOnlyConfig config)
	{
		this.configEnabled = configGetter.apply(config);
	}

	public static void refreshAllConfig(ComboRunesOnlyConfig config)
	{
		for (Altar a : Altar.values())
		{
			a.refreshConfig(config);
		}
	}

}