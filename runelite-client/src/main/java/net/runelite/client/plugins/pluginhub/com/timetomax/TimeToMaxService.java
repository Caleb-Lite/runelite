package net.runelite.client.plugins.pluginhub.com.timetomax;

import net.runelite.api.Skill;

public interface TimeToMaxService
{
	/**
	 * Get the number of actions done
	 */
	int getActions(Skill skill);

	/**
	 * Get the number of actions per hour
	 */
	int getActionsHr(Skill skill);

	/**
	 * Get the number of actions remaining
	 */
	int getActionsLeft(Skill skill);

	/**
	 * Get the amount of xp per hour
	 */
	int getXpHr(Skill skill);

	/**
	 * Get the start goal XP
	 */
	int getStartGoalXp(Skill skill);

	/**
	 * Get the amount of XP left until goal level
	 */
	int getEndGoalXp(Skill skill);

	/**
	 * Get the amount of time left until goal level
	 */
	String getTimeTilGoal(Skill skill);
}
