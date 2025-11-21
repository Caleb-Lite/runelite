package net.runelite.client.plugins.pluginhub.com.compactorbs.widget.elements;

import net.runelite.client.plugins.pluginhub.com.compactorbs.CompactOrbsConstants.Widgets.Classic;
import net.runelite.client.plugins.pluginhub.com.compactorbs.CompactOrbsConstants.Widgets.Modern;
import net.runelite.client.plugins.pluginhub.com.compactorbs.widget.TargetWidget;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Minimap implements TargetWidget
{
	//classic-resizable
	CLASSIC_NO_CLICK_0(Classic.MAP_NOCLICK_0),
	CLASSIC_NO_CLICK_1(Classic.MAP_NOCLICK_1),
	CLASSIC_NO_CLICK_2(Classic.MAP_NOCLICK_2),
	CLASSIC_NO_CLICK_3(Classic.MAP_NOCLICK_3),
	CLASSIC_NO_CLICK_4(Classic.MAP_NOCLICK_4),
	CLASSIC_NO_CLICK_5(Classic.MAP_NOCLICK_5),
	CLASSIC_MINIMAP_MASK(Classic.MINIMAP_MASK),
	CLASSIC_MINIMAP(Classic.MINIMAP),

	//modern-resizable
	MODERN_NO_CLICK_0(Modern.MAP_NOCLICK_0),
	MODERN_NO_CLICK_1(Modern.MAP_NOCLICK_1),
	MODERN_NO_CLICK_2(Modern.MAP_NOCLICK_2),
	MODERN_NO_CLICK_3(Modern.MAP_NOCLICK_3),
	MODERN_NO_CLICK_4(Modern.MAP_NOCLICK_4),
	MODERN_NO_CLICK_5(Modern.MAP_NOCLICK_5),
	MODERN_MINIMAP_MASK(Modern.MINIMAP_MASK),
	MODERN_MINIMAP(Modern.MINIMAP);

	private final int componentId;

}
