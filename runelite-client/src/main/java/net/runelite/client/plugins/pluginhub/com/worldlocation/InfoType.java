package net.runelite.client.plugins.pluginhub.com.worldlocation;

public enum InfoType
{
	UNIQUE_ID("Unique ID"),
	LOCAL_COORDINATES("Local coordinates");

	private final String name;

	InfoType(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
