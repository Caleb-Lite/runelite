package net.runelite.client.plugins.pluginhub.com.timetomax;

import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.util.QuantityFormatter;

@Getter
@AllArgsConstructor
public enum XpPanelLabel
{
	TIME_TO_LEVEL("TTL", XpSnapshotSingle::getTimeTillGoalShort),

	XP_GAINED("XP Gained", snap -> format(snap.getXpGainedInSession())),
	XP_HOUR("XP/hr", snap -> format(snap.getXpPerHour())),
	XP_LEFT("XP Left", snap -> format(snap.getXpRemainingToGoal())),

	ACTIONS_LEFT("Actions", snap -> format(snap.getActionsRemainingToGoal())),
	ACTIONS_HOUR("Actions/hr", snap -> format(snap.getActionsPerHour())),
	ACTIONS_DONE("Actions Done", snap -> format(snap.getActionsInSession())),
	;

	private final String key;
	private final Function<XpSnapshotSingle, String> valueFunc;

	private static String format(int val)
	{
		// actions remaining uses Integer.MAX_VALUE if the action history isn't initialized, which can happen
		// from restoring a save.
		return val == Integer.MAX_VALUE ? "N/A" : QuantityFormatter.quantityToRSDecimalStack(val, true);
	}
}
