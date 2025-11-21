package net.runelite.client.plugins.pluginhub.com.examinetooltip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.ui.overlay.OverlayLayer;

@AllArgsConstructor
@Getter
public enum TooltipOverlayLayer
{
	ALWAYS_ON_TOP(OverlayLayer.ALWAYS_ON_TOP),
	ABOVE_WIDGETS(OverlayLayer.ABOVE_WIDGETS);

	private final OverlayLayer rlLayer;
}
