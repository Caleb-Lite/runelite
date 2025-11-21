package net.runelite.client.plugins.pluginhub.com.npcdialoguereplacement;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("npcdialoguereplacement")
public interface NpcDialogueReplacementConfig extends Config
{
	@ConfigItem(keyName = "uriDialogFilePath", name = "Uri dialog file path", description = "Path to the txt file that stores lines that Uri can possibly say")
	default String uriDialogFilePath()
	{
		return "";
	}
}
