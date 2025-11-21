package net.runelite.client.plugins.pluginhub.com.Crowdsourcing.shootingstars;


import java.util.List;
import net.runelite.api.gameval.ObjectID;

public class Tiers
{
	private static final List<Integer> TIERS = List.of(
		ObjectID.STAR_SIZE_ONE_STAR,
		ObjectID.STAR_SIZE_TWO_STAR,
		ObjectID.STAR_SIZE_THREE_STAR,
		ObjectID.STAR_SIZE_FOUR_STAR,
		ObjectID.STAR_SIZE_FIVE_STAR,
		ObjectID.STAR_SIZE_SIX_STAR,
		ObjectID.STAR_SIZE_SEVEN_STAR,
		ObjectID.STAR_SIZE_EIGHT_STAR,
		ObjectID.STAR_SIZE_NINE_STAR
	);

	public static int of(int id)
	{
		int idx = TIERS.indexOf(id);
		return idx > -1 ? idx + 1 : -1;
	}
}
