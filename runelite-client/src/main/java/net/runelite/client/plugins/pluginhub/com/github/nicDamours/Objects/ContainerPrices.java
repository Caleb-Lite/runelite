package net.runelite.client.plugins.pluginhub.com.github.nicDamours.Objects;

import lombok.Value;

/**
 * Taken from the original Runelite bank plugin:
 * @see https://github.com/runelite/runelite/blob/500e294fc06884734cbf74590446930363f20334/runelite-client/src/main/java/net/runelite/client/plugins/bank/ContainerPrices.java#L30
 */
@Value
public class ContainerPrices
{
	private long gePrice;
	private long highAlchPrice;
}
