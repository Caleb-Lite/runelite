package net.runelite.client.plugins.pluginhub.com.globalchat;

public class GlobalChatMessage {
	public String username;
	public String symbol;
	public String message;
	public String global;
	public String to;

	public String type;

	public GlobalChatMessage(String username, String symbol, String message, String type, String to) {
		this.username = username;
		this.symbol = symbol;
		this.message = message;
		this.type = type;
		this.to = to;

	}
}
