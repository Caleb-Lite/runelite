package net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.scrubber;

import net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.item.view.TouchBarScrubber;
import net.runelite.client.plugins.pluginhub.dev.kocken.nativebridge.scrubber.view.ScrubberView;

public interface ScrubberDataSource {

	int getNumberOfItems(TouchBarScrubber scrubber);

    ScrubberView getViewForIndex(TouchBarScrubber scrubber, long index);
}
