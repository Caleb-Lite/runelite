package net.runelite.client.plugins.pluginhub.com.playerhighlighter;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("playerhighlighter")
public interface PlayerHighlighterConfig extends Config
{
	@Alpha
	@ConfigItem(
		keyName = "outlineColor",
		name = "Player Outline Color",
		description = "This is the color that will outline the character"
	)
	default Color outlineColor()
	{
		return new Color(0, 0, 255, 100);
	}

	@Alpha
	@ConfigItem(
			keyName = "selfColor",
			name = "Self Outline Color",
			description = "This is the color that will outline your character"
	)
	default Color selfColor()
	{
		return new Color(0, 255, 0, 100);
	}

	@Alpha
	@ConfigItem(
			keyName = "underfootColor",
			name = "Underfoot Color",
			description = "This is the color that will outline players standing underneath you"
	)
	default Color underfootColor()
	{
		return new Color(255, 0, 0, 100);
	}
	@ConfigItem(
			keyName = "borderWidth",
			name = "Outline Border Width",
			description = "This is the width of the outline around a player"
	)
	@Range(
			min=1
	)

	default int borderWidth(){return 3;}

	@ConfigItem(
			keyName = "borderFeather",
			name = "Outline Feather",
			description = "Amount to feather the outline around a player"
	)
	@Range(
			min=0
	)

	default int borderFeather(){return 5;}

	@ConfigItem(
			keyName = "highlightRadius",
			name = "Highlight Radius",
			description = "Players within this radius (from you) will be highlighted"
	)
	@Range(
			min=0
	)

	default int highlightRadius(){return 3;}

	@ConfigItem(
			keyName = "highlightSelf",
			name = "Highlight Self",
			description = "Highlight your own player"
	)
	default boolean highlightSelf() {return false;}

}


