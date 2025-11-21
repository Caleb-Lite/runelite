package net.runelite.client.plugins.pluginhub.com.twitchliveloadout.marketplace.notifications;

import net.runelite.client.plugins.pluginhub.com.twitchliveloadout.marketplace.MarketplaceEffect;
import net.runelite.client.plugins.pluginhub.com.twitchliveloadout.marketplace.products.EbsNotification;
import net.runelite.client.plugins.pluginhub.com.twitchliveloadout.marketplace.products.MarketplaceProduct;

public class Notification {
	public final MarketplaceProduct marketplaceProduct;
	public final MarketplaceEffect marketplaceEffect;
	public final EbsNotification ebsNotification;

	public Notification(MarketplaceProduct marketplaceProduct, MarketplaceEffect marketplaceEffect, EbsNotification ebsNotification)
	{
		this.marketplaceProduct = marketplaceProduct;
		this.marketplaceEffect = marketplaceEffect;
		this.ebsNotification = ebsNotification;
	}

	public boolean isDonationMessage()
	{
		return ebsNotification.message == null || ebsNotification.message.isEmpty();
	}
}
