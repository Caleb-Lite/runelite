package net.runelite.client.plugins.pluginhub.com.agilitypyramidsliderblocktimer;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("agilitypyramidsliderblocktimer")
public interface AgilityPyramidSliderBlockTimerConfig extends Config
{
	@Alpha
	@ConfigItem(
		keyName = "sliderBlockHighlight",
		name = "Slider Block Color",
		description = "Color of slider block overlay",
		position = 1
	)
	default Color getSliderBlockColor()
	{
		return Color.RED;
	}
}
