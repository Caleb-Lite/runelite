package net.runelite.client.plugins.pluginhub.com.mjhylkema.TeleportMaps.ui;

import net.runelite.client.plugins.pluginhub.com.mjhylkema.TeleportMaps.definition.AdventureLogEntryDefinition;
import lombok.Getter;
import net.runelite.api.widgets.Widget;

@Getter
public class Xerics extends AdventureLogEntry
{
	private String displayedName;

	public Xerics(AdventureLogEntryDefinition definition, Widget widget, String shortcut, String displayedName)
	{
		super(definition, widget, shortcut);
		this.displayedName = displayedName;
	}
}
