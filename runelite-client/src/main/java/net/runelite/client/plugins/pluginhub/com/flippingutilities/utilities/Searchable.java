package net.runelite.client.plugins.pluginhub.com.flippingutilities.utilities;

import java.time.Instant;

public interface Searchable {
    boolean isInInterval(Instant intervalStart);
    String getNameForSearch();
}
