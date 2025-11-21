package net.runelite.client.plugins.pluginhub.com.templeosrs.util.comp;

import com.google.gson.annotations.SerializedName;
import net.runelite.client.plugins.pluginhub.com.templeosrs.util.TempleError;

public class TempleCompetitionResponse
{
	@SerializedName("data")
	public TempleCompetitionData data;

	@SerializedName("error")
	public TempleError error;
}
