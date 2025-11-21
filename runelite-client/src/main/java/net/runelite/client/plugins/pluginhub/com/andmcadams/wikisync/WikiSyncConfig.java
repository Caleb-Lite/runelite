package net.runelite.client.plugins.pluginhub.com.andmcadams.wikisync;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(WikiSyncPlugin.CONFIG_GROUP_KEY)
public interface WikiSyncConfig extends Config
{
	String WIKISYNC_VERSION_KEYNAME = "version";
	String ENABLE_LOCAL_WEB_SOCKET_SERVER_KEYNAME = "enableLocalWebSocketServer";

	@ConfigItem(keyName = WIKISYNC_VERSION_KEYNAME, name = "Version", description = "The last version of WikiSync used by the player", hidden = true)
	default int wikiSyncVersion()
	{
		return WikiSyncPlugin.VERSION;
	}


	@ConfigItem(keyName = ENABLE_LOCAL_WEB_SOCKET_SERVER_KEYNAME,
		name = "Enable local WebSocket server",
		description = "If enabled, a WebSocket server will be served on localhost to be used by the OSRS DPS calculator (and other tools in the future!).")
	default boolean enableLocalWebSocketServer()
	{
		return true;
	}
}
