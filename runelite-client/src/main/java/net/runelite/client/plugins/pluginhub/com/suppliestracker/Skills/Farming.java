package net.runelite.client.plugins.pluginhub.com.suppliestracker.Skills;

import com.google.common.collect.ImmutableSet;
import net.runelite.client.plugins.pluginhub.com.suppliestracker.SuppliesTrackerPlugin;

import java.util.Set;
import static net.runelite.api.ItemID.BOTTOMLESS_COMPOST_BUCKET;
import static net.runelite.api.ItemID.BOTTOMLESS_COMPOST_BUCKET_22997;
import static net.runelite.api.ItemID.CABBAGE_SEED;
import static net.runelite.api.ItemID.COMPOST;
import static net.runelite.api.ItemID.ONION_SEED;
import static net.runelite.api.ItemID.POTATO_SEED;
import static net.runelite.api.ItemID.SNAPE_GRASS_SEED;
import static net.runelite.api.ItemID.STRAWBERRY_SEED;
import static net.runelite.api.ItemID.SUPERCOMPOST;
import static net.runelite.api.ItemID.SWEETCORN_SEED;
import static net.runelite.api.ItemID.TOMATO_SEED;
import static net.runelite.api.ItemID.ULTRACOMPOST;
import static net.runelite.api.ItemID.WATERMELON_SEED;

import net.runelite.client.game.ItemManager;

import javax.inject.Singleton;

@Singleton
public class Farming
{
	private final SuppliesTrackerPlugin plugin;
	private final ItemManager itemManager;
	private int plantId = 0;
	private int compostId = 0;
	private int bucketId = 0;
	private final Set<Integer> ALLOTMENT_SEEDS = ImmutableSet.of(POTATO_SEED, ONION_SEED, CABBAGE_SEED, TOMATO_SEED, SWEETCORN_SEED, STRAWBERRY_SEED, WATERMELON_SEED, SNAPE_GRASS_SEED);

	/***	Will be switching all to onMenuOptionClicked similar to how
	 *		hardwoods work next update so its automated just pushing this for now
	 ***/
	public Farming(SuppliesTrackerPlugin plugin, ItemManager itemManager)
	{
		this.plugin = plugin;
		this.itemManager = itemManager;
	}

    public void onChatPlant(String message)
    {
        if (plantId <= 0)
        {
            return;
        }

        String name = itemManager.getItemComposition(plantId).getName().toLowerCase();

        if (name.contains(" seed") || name.contains(" sapling") || name.contains(" spore"))
        {
			if (ALLOTMENT_SEEDS.contains(plantId))
			{
				plugin.buildEntries(plantId, 3);
				return;
			}
            plugin.buildEntries(plantId);
        }
    }

    public void onChatTreat(String message)
    {
        if (bucketId <= 0)
        {
            return;
        }

        String name = itemManager.getItemComposition(bucketId).getName().toLowerCase();

        if (name.contains("compost") || name.contains("plant cure"))
        {
            if (bucketId == BOTTOMLESS_COMPOST_BUCKET || bucketId == BOTTOMLESS_COMPOST_BUCKET_22997)
            {
                plugin.buildEntries(compostId);
            }
            else
            {
                plugin.buildEntries(bucketId);
            }
        }
    }

    public void setPlantId(int plantId)
    {
        this.plantId = plantId;
    }

    public void setBucketId(int bucketId)
    {
        this.bucketId = bucketId;
    }

	public void setEndlessBucket(String message)
	{
		if (message.toLowerCase().contains("ultracompost"))
		{
			compostId = ULTRACOMPOST;
		}
		else if (message.toLowerCase().contains("supercompost"))
		{
			compostId = SUPERCOMPOST;
		}
		else
		{
			compostId = COMPOST;
		}
	}
}

/*
 * Copyright (c) 2022, Patrick <https://github.com/pwatts6060>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *	list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *	this list of conditions and the following disclaimer in the documentation
 *	and/or other materials provided with the distribution.
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