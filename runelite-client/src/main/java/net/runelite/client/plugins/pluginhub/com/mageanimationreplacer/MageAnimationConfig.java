package net.runelite.client.plugins.pluginhub.com.mageanimationreplacer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("mageAnimationReplacer")
public interface MageAnimationConfig extends Config
{
	@ConfigItem(
		keyName = "tridentReplace",
		name = "Replace trident animations",
		description = "Replace animations for tridents."
	)
	default boolean tridentReplace() { return false; }

	@ConfigItem(
			keyName = "barrageReplace",
			name = "Replace barrage animations",
			description = "Replace animations for barrages."
	)
	default boolean barrageReplace() { return false; }
}
