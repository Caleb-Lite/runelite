package net.runelite.client.plugins.pluginhub.com.catracker.util;

import net.runelite.client.plugins.pluginhub.com.catracker.config.CombatAchievementsConfig;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TierUtil
{
	private static final Map<Integer, String> TIER_VARBIT_MAP = Map.of(
		4132, "Easy",
		10660, "Medium",
		10661, "Hard",
		14812, "Elite",
		14813, "Master",
		14814, "Grandmaster"
	);

	private static Map<String, Integer> cachedThresholds = null;

	public static void initializeTierThresholds(Client client)
	{
		Map<String, Integer> thresholds = new HashMap<>();

		for (Map.Entry<Integer, String> entry : TIER_VARBIT_MAP.entrySet())
		{
			int varbitId = entry.getKey();
			String tierName = entry.getValue();

			try
			{
				int value = client.getVarbitValue(varbitId);
				if (value > 0)
				{
					thresholds.put(tierName, value);
					log.debug("Loaded {} tier threshold: {} from varbit {}", tierName, value, varbitId);
				}
				else
				{
					log.warn("Varbit {} ({}) returned 0", varbitId, tierName);
				}
			}
			catch (Exception e)
			{
				log.warn("Failed to read varbit {} for {} tier: {}", varbitId, tierName, e.getMessage());
			}
		}

		cachedThresholds = thresholds;
		log.debug("Initialized tier thresholds: {}", cachedThresholds);
	}

	public static int getPointsFromGoal(CombatAchievementsConfig.TierGoal tierGoal, int completedPoints)
	{
		if (cachedThresholds == null)
		{
			log.warn("Tier thresholds not initialized - call initializeTierThresholds first");
			return 0;
		}

		if (tierGoal.toString().equalsIgnoreCase("AUTO"))
		{
			String[] tierOrder = {"Easy", "Medium", "Hard", "Elite", "Master", "Grandmaster"};

			for (String tier : tierOrder)
			{
				int threshold = cachedThresholds.getOrDefault(tier, 0);
				if (threshold > 0 && completedPoints < threshold)
				{
					return threshold;
				}
			}

			return cachedThresholds.getOrDefault("Grandmaster", 0);
		}

		String tierName = tierGoal.toString().replace("TIER_", "");
		tierName = tierName.substring(0, 1).toUpperCase() + tierName.substring(1).toLowerCase();

		return cachedThresholds.getOrDefault(tierName, 0);
	}

	public static String getActualTierName(CombatAchievementsConfig.TierGoal tierGoal, int completedPoints)
	{
		if (cachedThresholds == null)
		{
			log.warn("Tier thresholds not initialized - call initializeTierThresholds first");
			return "Unknown";
		}

		if (tierGoal.toString().equalsIgnoreCase("AUTO"))
		{
			String[] tierOrder = {"Easy", "Medium", "Hard", "Elite", "Master", "Grandmaster"};

			for (String tier : tierOrder)
			{
				int threshold = cachedThresholds.getOrDefault(tier, 0);
				if (threshold > 0 && completedPoints < threshold)
				{
					return tier;
				}
			}

			return "Grandmaster";
		}

		String tierName = tierGoal.toString().replace("TIER_", "");
		return tierName.substring(0, 1).toUpperCase() + tierName.substring(1).toLowerCase();
	}

	public static Map<String, Integer> getCachedThresholds()
	{
		return cachedThresholds != null ? new HashMap<>(cachedThresholds) : new HashMap<>();
	}
}
/*
 * Copyright (c) 2025, Ethan Hubbartt <ehubbartt@gmail.com>
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