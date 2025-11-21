package net.runelite.client.plugins.pluginhub.com.timetomax;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import net.runelite.api.Skill;

/**
 * Utility class for XP calculations and display
 */
public class XpCalculator
{
	// XP required for level 99 in each skill
	public static final int LEVEL_99_XP = 13_034_431;
	public static final int MAX_XP = 200_000_000;

	// Store starting XP for each skill for target tracking
	private static final Map<Skill, LocalDate> intervalStartDates = new HashMap<>();

	/**
	 * Get the required XP per day to reach max level by the target date
	 *
	 * @param startXp    Start XP in the skill
	 * @param config     Instance of the TimeToMaxConfig
	 * @return XP required per day
	 */
	public static int getRequiredXpPerDay(int startXp, TimeToMaxConfig config)
	{
		long daysUntilTarget = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.parse(config.targetDate()));
		if (daysUntilTarget <= 0)
		{
			if (config.maxSkillMode().equals(MaxSkillMode.NORMAL))
			{
				return LEVEL_99_XP - startXp; // Target date is today or in the past
			}
			else if (config.maxSkillMode() == MaxSkillMode.COMPLETIONIST)
			{
				return MAX_XP - startXp;
			}
		}

		int xpRemaining = 0;

		if (config.xpOverride())
		{
			return config.minimumXpOverride();
		}

		if (config.maxSkillMode() == MaxSkillMode.NORMAL)
		{
			xpRemaining = LEVEL_99_XP - startXp;
			if (xpRemaining <= 0)
			{
				return 0;
			}
		}
		else if (config.maxSkillMode() == MaxSkillMode.COMPLETIONIST)
		{
			xpRemaining = MAX_XP - startXp;
			if (xpRemaining <= 0)
			{
				return 0;
			}

		}

		return (int) Math.ceil((double) xpRemaining / daysUntilTarget);
	}

	/**
	 * Get the required XP per interval to reach max level by the target date
	 *
	 * @param startXp    Start XP in the skill
	 * @param config instance of TimeToMaxConfig
	 * @return XP required per interval
	 */
	public static int getRequiredXpPerInterval(int startXp, TimeToMaxConfig config)
	{
		int xpPerDay = getRequiredXpPerDay(startXp, config);

		switch (config.trackingInterval())
		{
			case WEEK:
				return xpPerDay * 7;
			case MONTH:
				return xpPerDay * 30;
			default:
				return xpPerDay;
		}
	}

	/**
	 * Check if a new interval should start based only on the interval type and reference date.
	 * This is interval-specific but skill-agnostic.
	 *
	 * @param interval      The current tracking interval
	 * @param referenceDate The reference date to compare against
	 * @return true if a new interval should start
	 */
	public static boolean shouldStartNewIntervalForDate(TrackingInterval interval, LocalDate referenceDate)
	{
		if (referenceDate == null)
		{
			return true;
		}

		LocalDate now = LocalDate.now();
		switch (interval)
		{
			case DAY:
				return !now.equals(referenceDate);
			case WEEK:
				// Check if the current date is in a different ISO week than the reference date
				return now.get(ChronoField.ALIGNED_WEEK_OF_YEAR) != referenceDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR)
						|| now.getYear() != referenceDate.getYear();
			case MONTH:
				// Check if the current date is in a different calendar month than the reference date
				return now.getMonth() != referenceDate.getMonth() || now.getYear() != referenceDate.getYear();
			default:
				return true;
		}
	}

	/**
	 * Get the interval start date for a skill
	 *
	 * @param skill The skill to get the interval start date for
	 * @return The interval start date for the skill or null if not set
	 */
	public static LocalDate getIntervalStartDate(Skill skill)
	{
		return intervalStartDates.get(skill);
	}

	public static LocalDate getMaxDateForLowestSkillWithOverride(int lowestSkillXp, TimeToMaxConfig config)
	{
		if (config.xpOverride())
		{
			int xpRequired = MAX_XP;

			if (config.maxSkillMode().equals(MaxSkillMode.NORMAL))
			{
				xpRequired = LEVEL_99_XP - lowestSkillXp;
			}
			else if (config.maxSkillMode().equals(MaxSkillMode.COMPLETIONIST))
			{
				xpRequired = MAX_XP - lowestSkillXp;
			}

			var daysUntilTarget = (long) Math.ceil((double) xpRequired / config.minimumXpOverride());
			if (daysUntilTarget <= 0)
			{
				return LocalDate.now();
			}
			return LocalDate.now().plusDays(daysUntilTarget);
		}
		return null;
	}
}
/*
 * Copyright (c) 2017, Cameron <moberg@tuta.io>
 * Copyright (c) 2018, Levi <me@levischuck.com>
 * Copyright (c) 2020, Anthony <https://github.com/while-loop>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */