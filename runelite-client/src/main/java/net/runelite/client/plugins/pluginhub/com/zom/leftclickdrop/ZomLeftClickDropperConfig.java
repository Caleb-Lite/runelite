package net.runelite.client.plugins.pluginhub.com.zom.leftclickdrop;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("leftclickdrop")
public interface ZomLeftClickDropperConfig extends Config
{
	@ConfigItem(
		keyName = "itemList",
		name = "Item list to left click drop",
		description = "Comma delimited list of items you want to left click drop",
		position = 1
	)
	default String itemList()
	{
		return "";
	}
}
