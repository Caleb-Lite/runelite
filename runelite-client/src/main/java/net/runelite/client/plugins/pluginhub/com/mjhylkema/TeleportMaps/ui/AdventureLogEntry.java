package net.runelite.client.plugins.pluginhub.com.mjhylkema.TeleportMaps.ui;

import net.runelite.client.plugins.pluginhub.com.mjhylkema.TeleportMaps.definition.AdventureLogEntryDefinition;
import lombok.Getter;
import net.runelite.api.widgets.Widget;

@Getter
public class AdventureLogEntry<T extends AdventureLogEntryDefinition>
{
	private Widget widget;
	private T definition;
	private String keyShortcut;

	public AdventureLogEntry(T definition, Widget widget, String shortcut)
	{
		this.definition = definition;
		this.widget = widget;
		this.keyShortcut = shortcut;
	}
}
