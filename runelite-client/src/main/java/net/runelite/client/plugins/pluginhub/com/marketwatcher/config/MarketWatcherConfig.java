package net.runelite.client.plugins.pluginhub.com.marketwatcher.config;


import net.runelite.client.plugins.pluginhub.com.marketwatcher.MarketWatcherPlugin;
import net.runelite.client.plugins.pluginhub.com.marketwatcher.config.PricePeriodType;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;
import net.runelite.client.config.Units;

@ConfigGroup(MarketWatcherPlugin.CONFIG_GROUP)
public interface MarketWatcherConfig extends Config
{
	@ConfigSection(
		name = "General",
		description = "General settings",
		position = 0
	)
	String generalSettings = "generalSettings";
	String AUTO_REFRESH_INTERVAL = "autoRefreshInterval";

	@ConfigItem(
		keyName = AUTO_REFRESH_INTERVAL,
		name = "Auto Refresh Interval",
		description = "Set a time to automatically refresh item prices.",
		position = 0,
		section = generalSettings
	)
	@Range(min = 6, max = 24)
	@Units(" hours")
	default int refreshInterval()
	{
		return 12;
	}

	String COLOR_BLIND_MODE = "colorBlindMode";

	@ConfigItem(
		keyName = COLOR_BLIND_MODE,
		name = "Color Blind Mode",
		description = "Colorblind friendly prices",
		position = 1,
		section = generalSettings
	)

	default boolean colorBlindMode()
	{
		return false;
	}


	String PRICE_PERIOD_ONE_QUANTITY = "pricePeriodOneQuantity";
	@ConfigSection(
		name = "Price Period 1",
		description = "First Price Period",
		position = 1
	)
	String pricePeriodOne = "pricePeriodOne";

	@ConfigItem(
		keyName = PRICE_PERIOD_ONE_QUANTITY,
		name = "Quantity",
		description = "Set the quantity for the first price period.",
		position = 0,
		section = pricePeriodOne
	)
	@Range(min = 1, max = 24)
	default int pricePeriodOneQty()
	{
		return 1;
	}

	String PRICE_PERIOD_ONE_TYPE = "pricePeriodOneType";

	@ConfigItem(
		keyName = PRICE_PERIOD_ONE_TYPE,
		name = "Type",
		description = "Set the first price period type.",
		position = 1,
		section = pricePeriodOne
	)
	default PricePeriodType pricePeriodOneType()
	{
		return PricePeriodType.Days;
	}

	String PRICE_PERIOD_TWO_QUANTITY = "pricePeriodTwoQuantity";
	@ConfigSection(
		name = "Price Period 2",
		description = "Second Price Period",
		position = 2
	)
	String pricePeriodTwo = "pricePeriodTwo";

	@ConfigItem(
		keyName = PRICE_PERIOD_TWO_QUANTITY,
		name = "Quantity",
		description = "Set the quantity for the second price period.",
		position = 0,
		section = pricePeriodTwo
	)
	@Range(min = 1, max = 24)
	default int pricePeriodTwoQty()
	{
		return 3;
	}

	String PRICE_PERIOD_TWO_TYPE = "pricePeriodTwoType";

	@ConfigItem(
		keyName = PRICE_PERIOD_TWO_TYPE,
		name = "Type",
		description = "Set the second price period type.",
		position = 1,
		section = pricePeriodTwo
	)
	default PricePeriodType pricePeriodTwoType()
	{
		return PricePeriodType.Weeks;
	}

	String PRICE_PERIOD_THREE_QUANTITY = "pricePeriodThreeQuantity";
	@ConfigSection(
		name = "Price Period 3",
		description = "Third Price Period",
		position = 2
	)
	String pricePeriodThree = "pricePeriodThree";

	@ConfigItem(
		keyName = PRICE_PERIOD_THREE_QUANTITY,
		name = "Quantity",
		description = "Set the quantity for the third price period.",
		position = 0,
		section = pricePeriodThree
	)
	@Range(min = 1, max = 24)
	default int pricePeriodThreeQty()
	{
		return 3;
	}

	String PRICE_PERIOD_THREE_TYPE = "pricePeriodThreeType";

	@ConfigItem(
		keyName = PRICE_PERIOD_THREE_TYPE,
		name = "Type",
		description = "Set the third price period type.",
		position = 1,
		section = pricePeriodThree
	)
	default PricePeriodType pricePeriodThreeType()
	{
		return PricePeriodType.Months;
	}

}
