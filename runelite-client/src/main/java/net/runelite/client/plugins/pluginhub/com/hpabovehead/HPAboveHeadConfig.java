package net.runelite.client.plugins.pluginhub.com.hpabovehead;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("settings")
public interface HPAboveHeadConfig extends Config
{
	@ConfigItem(
			keyName = "fontSize",
			name = "Font Size",
			description = "Size of the text above the HP bar"
	)
	@Range(min = 8, max = 40)
	default int fontSize()
	{
		return 14;
	}

	public enum FontOption
	{
		ARIAL,
		VERDANA,
		TIMES_NEW_ROMAN,
		MONOSPACED,
		UNICA_ONE,
		TAHOMA,
		CALIBRI,
		COURIER_NEW,
		GEORGIA,
		COMIC_SANS_MS,
		SEGOE_UI,
		LUCIDA_CONSOLE,
		ROBOTO

	}

	@ConfigItem(
			keyName = "font",
			name = "Font",
			description = "Font used for the text"
	)
	default FontOption font()
	{
		return FontOption.VERDANA;
	}

	@ConfigItem(
			keyName = "color",
			name = "Text Colour",
			description = "Colour of the text above the HP bar"
	)
	default Color color()
	{
		return Color.GREEN;
	}

	@ConfigItem(
			keyName = "textHeightOffset",
			name = "Text vertical offset",
			description = "How high above the HP bar to render the HP text"
	)
	default int textHeightOffset()
	{
		return 47;
	}

	@ConfigItem(
			keyName = "textXOffset",
			name = "Text horizontal offset",
			description = "Horizontal offset for the HP text relative to the player's centre"
	)
	default int textXOffset()
	{
		return 0;
	}

	@ConfigItem(
			keyName = "showBackground",
			name = "Show Background",
			description = "Draw a background behind the HP text"
	)
	default boolean showBackground()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
			keyName = "backgroundColor",
			name = "Background Colour",
			description = "Colour of the HP text background"
	)
	default Color backgroundColor()
	{
		return new Color(0, 0, 0, 125); // semi-transparent black
	}

}
