package net.runelite.client.plugins.pluginhub.rs117.hd.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Saturation
{
	HIGHEST("Highest", 1.2f),
	HIGHER("Higher", 1.1f),
	DEFAULT("Default", 1.0f),
	LOWER("Lower", 0.9f),
	LOWEST("Lowest", 0.8f),
	NONE("None", 0.0f);

	private final String name;
	private final float amount;

	@Override
	public String toString()
	{
		return name;
	}
}
