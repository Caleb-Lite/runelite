package net.runelite.client.plugins.pluginhub.com.trackscapeconnector;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TrackScapeConnectorPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TrackScapeConnectorPlugin.class);
		RuneLite.main(args);
	}
}
/**
 * Forked from hex-agon/chat-logger
 * https://github.com/hex-agon/chat-logger/blob/master/src/main/java/fking/work/chatlogger/RemoteSubmitter.java
 */