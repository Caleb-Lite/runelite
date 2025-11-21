package net.runelite.client.plugins.pluginhub.com.templeosrs.util.clan;

import com.google.gson.annotations.SerializedName;

public class TempleClanCurrentTopPlayer
{
	@SerializedName("player")
	public String player;

	@SerializedName("xp")
	public Double xp;

	@SerializedName("rank")
	public Integer rank;
}
