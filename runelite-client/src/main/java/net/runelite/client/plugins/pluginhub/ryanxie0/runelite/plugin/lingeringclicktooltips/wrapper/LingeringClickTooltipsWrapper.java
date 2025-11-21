package net.runelite.client.plugins.pluginhub.ryanxie0.runelite.plugin.lingeringclicktooltips.wrapper;

import lombok.Data;
import net.runelite.client.plugins.pluginhub.ryanxie0.runelite.plugin.lingeringclicktooltips.renderable.alpha.AlphaTooltipComponent;

import java.awt.Point;
import java.awt.Color;
import java.time.Duration;
import java.time.Instant;

@Data
public class LingeringClickTooltipsWrapper {
    private boolean isFaded;
    private boolean isInfoTooltip;
    private boolean isClamped;
    private String text;
    private Color backgroundColor;
    private Instant timeOfCreation;
    private Point location;
    private AlphaTooltipComponent renderableComponent;
    private Duration tooltipDuration;
}

