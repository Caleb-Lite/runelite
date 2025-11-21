package net.runelite.client.plugins.pluginhub.com.tsbreuer.multilines;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("Multi-lines")
public interface MultiLinesConfig extends Config
{
	String warning = "Warning, this plugin does not include Wilderness Multi Areas. Please use Wilderness Lines for that.";

	@ConfigItem(
			keyName = "UsageWarning",
			name = "Warning",
			description = "Warning about plugin",
			position = 0,
			section = warning
	)
	default String getWarning()
	{
		return "";
	}

	@ConfigItem(
			keyName = "UsageWarning",
			name = "",
			description = ""
	)
	void setWarning(String key);

	@ConfigItem(
			position = 1,
			keyName = "showLoginMessage",
			name = "Show Login Message on chat",
			description = "Enable or disable the message in chat when loggin in or hopping",
			section = multiLines
	)
	default boolean showLoginMessage()
	{
		return true;
	}

	@ConfigSection(
		name = "Multi Lines",
		description = "",
		position = 2
	)
	String multiLines = "multiLines";

	@ConfigItem(
		position = 1,
		keyName = "multiLinesColor",
		name = "Multi lines color",
		description = "Color of lines bordering multi-combat zones",
		section = multiLines
	)
	@Alpha
	default Color multiLinesColor()
	{
		return Color.RED;
	}

	@ConfigItem(
		position = 2,
		keyName = "showSpearLines",
		name = "Show spear lines",
		description = "Show the area in which you can be potentially speared into a multi-combat zone",
		section = multiLines
	)
	default boolean showSpearLines()
	{
		return false;
	}

	@ConfigItem(
		position = 3,
		keyName = "spearLinesColor",
		name = "Spear lines color",
		description = "Color of lines bordering spear areas surrounding multi-combat zones",
		section = multiLines
	)
	@Alpha
	default Color spearLinesColor()
	{
		return Color.ORANGE;
	}
}

