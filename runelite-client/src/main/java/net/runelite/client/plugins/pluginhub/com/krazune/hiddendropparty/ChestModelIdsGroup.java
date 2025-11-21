package net.runelite.client.plugins.pluginhub.com.krazune.hiddendropparty;

public enum ChestModelIdsGroup
{
	DEFAULT("11123,15567,15885"),
	CRATES("12152,15509,29973,33922,31450"),
	CHESTS("11123,11204,12146,12150,12530"),
	CUSTOM("");

	private final String idsString;

	ChestModelIdsGroup(String idsString)
	{
		this.idsString = idsString;
	}

	public String getValue()
	{
		return idsString;
	}
}

