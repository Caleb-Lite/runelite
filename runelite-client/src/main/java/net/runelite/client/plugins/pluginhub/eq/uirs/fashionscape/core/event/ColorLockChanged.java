package net.runelite.client.plugins.pluginhub.eq.uirs.fashionscape.core.event;

import net.runelite.client.plugins.pluginhub.eq.uirs.fashionscape.data.color.ColorType;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class ColorLockChanged extends SwapEvent
{
	ColorType type;
	boolean isLocked;
}
