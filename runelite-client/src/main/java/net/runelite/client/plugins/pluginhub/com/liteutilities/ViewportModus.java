package net.runelite.client.plugins.pluginhub.com.liteutilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Point;
import net.runelite.api.widgets.ComponentID;

@Getter
@AllArgsConstructor
public enum ViewportModus
{
	RESIZED_BOX(ComponentID.RESIZABLE_VIEWPORT_RESIZABLE_VIEWPORT_OLD_SCHOOL_BOX, ComponentID.RESIZABLE_VIEWPORT_INTERFACE_CONTAINER),
	RESIZED_BOTTOM(ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_RESIZABLE_VIEWPORT_BOTTOM_LINE, ComponentID.RESIZABLE_VIEWPORT_BOTTOM_LINE_INTERFACE_CONTAINER),
	FIXED(ComponentID.FIXED_VIEWPORT_FIXED_VIEWPORT, ComponentID.FIXED_VIEWPORT_INTERFACE_CONTAINER),
	FIXED_BANK(ComponentID.BANK_CONTAINER, ComponentID.BANK_INVENTORY_ITEM_CONTAINER);

	private int container;
	private final int viewport;
}
