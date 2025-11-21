package net.runelite.client.plugins.pluginhub.com.currentspellbook;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("com.currentspellbook")
public interface CurrentSpellbookConfig extends Config
{
	@ConfigItem(
		keyName = "fontSize",
		name = "Font size",
		description = "How big the overlay text should be"
	)
	default int fontSize()
	{
		return 32;
	}

	@ConfigItem(
			position = 2,
			keyName = "changeColors",
			name = "Change font colors",
			description = "Whether or not the font color should change according to the spellbook"
	)
	default boolean changeColors()
	{
		return true;
	}

	@ConfigItem(
		keyName = "prependWith",
		name = "Prepend with",
		description = "Add this word or phrase before the spellbook name. A space will automatically be added between the two. You can leave this blank to only have the spellbook name."
	)
	default String prependWith()
	{
		return "Spellbook:";
	}
}
