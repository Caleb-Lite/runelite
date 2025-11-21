package net.runelite.client.plugins.pluginhub.com.hunllefprojectilerecolor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(HunllefProjectilesConfig.GROUP)
public interface HunllefProjectilesConfig extends Config
{
	String GROUP = "hunllef-projectiles";

	@Getter
	@AllArgsConstructor
	enum Style
	{
		Inferno(1380),
		CoX(1341),
		ToB(1606),
		ToA(2224),
		CG(1714);

		private final int magic;
	}

	@ConfigItem(
		keyName = "style",
		name = "Projectile Style",
		description = "The projectile styles."
	)
	default Style style()
	{
		return Style.CG;
	}
}

