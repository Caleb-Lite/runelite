package net.runelite.client.plugins.pluginhub.com.stevenwaterman.blindfold;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup(BlindfoldPluginConfig.GROUP)
public interface BlindfoldPluginConfig extends Config
{
	String GROUP = "blindfold";

	@ConfigItem(
		keyName = "hotkey",
		name = "Toggle hotkey",
		description = "Toggle plugin functionality",
		position = 1
	)
	default Keybind hotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "enableUI",
			name = "Show UI",
			description = "Disable this to remove ALL interface elements from the screen. Useful as a greenscreen for content creation.",
			position = 1
	)
	default boolean enableUi()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableTerrain",
			name = "Show Terrain",
			description = "Disable this to hide the terrain / landscape.",
			position = 2
	)
	default boolean enableTerrain()
	{
		return false;
	}

	@ConfigItem(
			keyName = "enableScenery",
			name = "Show Scenery",
			description = "Disable this to hide the static scenery.",
			position = 3
	)
	default boolean enableScenery()
	{
		return false;
	}

	@ConfigItem(
			keyName = "enableEntities",
			name = "Show Entities",
			description = "Disable this to hide NPCs, Players, projectiles, and ground items.",
			position = 4
	)
	default boolean enableEntities()
	{
		return false;
	}

	@ConfigItem(
			keyName = "enableRuneLiteObjects",
			name = "Show RuneLite Objects (broken)",
			description = "Disable this to hide objects spawned in by RuneLite plugins.<br>Currently non-functional",
			position = 5
	)
	default boolean enableRuneLiteObjects()
	{
		return false;
	}

	@ConfigItem(
		keyName = "disableRendering",
		name = "Pause when unfocused (broken)",
		description = "Stops the screen from rendering when client is unfocused.<br>Rendering resumes when a notification is received.<br>Currently non-functional",
		position = 6
	)
	default boolean disableRendering()
	{
		return false;
	}
}
