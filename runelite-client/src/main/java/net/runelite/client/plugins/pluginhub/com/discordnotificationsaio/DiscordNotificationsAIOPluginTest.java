package net.runelite.client.plugins.pluginhub.com.discordnotificationsaio;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DiscordNotificationsAIOPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin( DiscordNotificationsAIOPlugin.class);
		RuneLite.main(args);
	}
}
	package com.discordnotificationsaio;
	
	import lombok.Data;
	
	@Data
	class NonLootWebhookBody
	{
	private String content;
	private Embed embed;
	
	@Data
	static class Embed
	{
		final UrlEmbed image;
	}
	
	@Data
	static class UrlEmbed
	{
		final String url;
	}
	}