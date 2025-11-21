package net.runelite.client.plugins.pluginhub.com.cluesaver;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum ClueTier
{
	BEGINNER(0),
	EASY(1),
	MEDIUM(2),
	HARD(3),
	ELITE(4),
	MASTER(5);

	private final int value;

	ClueTier(int value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return StringUtils.capitalize(StringUtils.lowerCase(super.toString()));
	}
}
