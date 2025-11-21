package net.runelite.client.plugins.pluginhub.com.templeosrs.util.player;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.com.templeosrs.util.TempleError;

public class TemplePlayerResponse
{
	@SerializedName("data")
	public TemplePlayerData data;

	@SerializedName("error")
	public TempleError error;
}
