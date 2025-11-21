package net.runelite.client.plugins.pluginhub.com.godwarsprot;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.ui.overlay.OverlayPosition;

@ConfigGroup("godwarsprotectionoverlay")
public interface GodWarsProtConfig extends Config
{
	@ConfigItem(
		keyName = "itemHighlight",
		name = "Item Highlight",
		description = "Highlight god related items in bank and inventory",
		position = 1
	)
	default boolean itemHighlight()
	{
		return false;
	}
	@ConfigItem(
			keyName = "highlightStyle",
			name = "Highlight Style",
			description = "How to highlight items",
			position = 2
	)
	default GodWarsProtHighlightStyle highlightStyle(){
		return GodWarsProtHighlightStyle.OUTLINE;
	}
	@ConfigItem(
			keyName = "showOverlay",
			name = "Show Overlay",
			description = "Toggle to disable/enable the yes/no protection overlay",
			position = 3
	)
	default boolean showOverlay(){
		return true;
	}
	@ConfigItem(
			keyName = "iconOverlay",
			name = "Icon Overlay",
			description = "Replaces the overlay with an icon version",
			position = 4
	)
	default boolean iconOverlay(){
		return true;
	}
	@ConfigItem(
			keyName = "overlayLoc",
			name = "Overlay location",
			description = "Choose location of Overlay",
			position = 5
	)
	default OverlayPosition overlayLoc(){
		return OverlayPosition.ABOVE_CHATBOX_RIGHT;
	}



}
