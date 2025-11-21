package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.comborunes;

import java.util.Arrays;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import net.runelite.api.MenuEntry;

@RequiredArgsConstructor
public enum RemoveMode
{

	REMOVE_COMPLETELY(menuEntries ->
		Arrays.stream(menuEntries)
			.filter(me -> !ComboRunesOnlyPlugin.isCraftRuneEntry(me))
			.toArray(MenuEntry[]::new)
	),
	DEPRIORITIZE(menuEntries ->
	{
		Arrays.stream(menuEntries)
			.filter(ComboRunesOnlyPlugin::isCraftRuneEntry)
			.findFirst()
			.ifPresent(me -> me.setDeprioritized(true));
		return menuEntries;
	}
	),
	;

	private final Function<MenuEntry[], MenuEntry[]> menuEntryModifier;

	public MenuEntry[] processMenuEntries(MenuEntry[] menuEntries)
	{
		return this.menuEntryModifier.apply(menuEntries);
	}

}