package net.runelite.client.plugins.pluginhub.com.flippingcopilot.ui.graph;


@FunctionalInterface
public interface CoordinateConverter {
    int toValue(int coordinate);
}