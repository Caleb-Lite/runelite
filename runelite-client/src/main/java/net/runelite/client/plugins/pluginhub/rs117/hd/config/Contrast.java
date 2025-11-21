package net.runelite.client.plugins.pluginhub.rs117.hd.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Contrast
{
	HIGHER("Highest", 1.1f),
	HIGHEST("Higher", 1.05f),
	DEFAULT("Default", 1.0f),
	LOWER("Lower", 0.95f),
	LOWEST("Lowest", 0.9f);

	private final String name;
	private final float amount;

	@Override
	public String toString()
	{
		return name;
	}
}
