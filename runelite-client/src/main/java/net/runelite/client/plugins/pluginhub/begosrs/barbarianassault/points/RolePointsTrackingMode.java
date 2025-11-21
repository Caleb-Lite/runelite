package net.runelite.client.plugins.pluginhub.begosrs.barbarianassault.points;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RolePointsTrackingMode
{
	MINE("Mine"),
	ALL("All"),
	PLAYER_ONE("Player 1"),
	PLAYER_TWO("Player 2"),
	PLAYER_THREE("Player 3"),
	PLAYER_FOUR("Player 4"),
	PLAYER_FIVE("Player 5"),
	ATTACKER("Attacker"),
	DEFENDER("Defender"),
	COLLECTOR("Collector"),
	HEALER("Healer");

	private final String name;

	@Override
	public String toString()
	{
		return name;
	}
}
