package net.runelite.client.plugins.pluginhub.com.nexsplits.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CoughMode
{
	OFF("Off"),
	OLM("Olm Burn"),
	CAT("Cats");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
