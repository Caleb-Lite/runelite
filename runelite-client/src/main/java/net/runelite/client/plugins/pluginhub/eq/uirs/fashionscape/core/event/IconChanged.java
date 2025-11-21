package net.runelite.client.plugins.pluginhub.eq.uirs.fashionscape.core.event;

import net.runelite.client.plugins.pluginhub.eq.uirs.fashionscape.data.kit.JawIcon;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class IconChanged extends SwapEvent
{
	JawIcon icon;
}
