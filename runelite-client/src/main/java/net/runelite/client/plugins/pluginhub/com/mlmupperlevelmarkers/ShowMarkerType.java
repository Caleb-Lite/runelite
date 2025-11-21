package net.runelite.client.plugins.pluginhub.com.mlmupperlevelmarkers;

public enum ShowMarkerType
{
	BOTH("Both Levels"),
	UPPER("Upper Level"),
	LOWER("Lower Level");

	private final String name;

	ShowMarkerType(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}

