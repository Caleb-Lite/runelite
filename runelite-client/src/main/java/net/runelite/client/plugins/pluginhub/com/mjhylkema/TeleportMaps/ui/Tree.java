package net.runelite.client.plugins.pluginhub.com.mjhylkema.TeleportMaps.ui;

import net.runelite.client.plugins.pluginhub.com.mjhylkema.TeleportMaps.definition.TreeDefinition;
import lombok.Getter;
import net.runelite.api.widgets.Widget;

@Getter
public class Tree extends AdventureLogEntry
{
	private String displayedName;

	public Tree(TreeDefinition definition, Widget widget, String shortcut, String displayedName)
	{
		super(definition, widget, shortcut);
		this.displayedName = displayedName;
	}
}
