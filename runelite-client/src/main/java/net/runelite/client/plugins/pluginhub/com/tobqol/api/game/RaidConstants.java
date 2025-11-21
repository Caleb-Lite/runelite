package net.runelite.client.plugins.pluginhub.com.tobqol.api.game;

import com.google.common.collect.ImmutableSet;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public interface RaidConstants
{
    DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##0.0");

    int PRECISE_TIMER = 11866;
    int THEATRE_OF_BLOOD_ROOM_STATUS = 6447;
    int THEATRE_OF_BLOOD_BOSS_HP = 6448;
    int TOB_BANK_CHEST_UNLOOTED = 41435;
    int TOB_BANK_CHEST_LOOTED = 41436;
    int TOB_BANK_CHEST = 41437;
    int TOB_ENTRANCE = 32653;
    int TOB_LOOT_ROOM_CHEST_PURPLE_PERSONAL = 32993;
    int TOB_LOOT_ROOM_CHEST_WHITE_PERSONAL = 32992;
    int TOB_LOOT_ROOM_CHEST_PURPLE_OTHER = 32991;

    List<Integer> LOOT_ROOM_PURPLE_CHEST_IDS = Arrays.asList(TOB_LOOT_ROOM_CHEST_PURPLE_PERSONAL, TOB_LOOT_ROOM_CHEST_PURPLE_OTHER);
    List<Integer> LOOT_ROOM_REGULAR_CHEST_IDS = Arrays.asList(33086, 33087, 33088, 33089, 33090);
    List<Integer> LOOT_ROOM_ALL_CHEST_IDS = Arrays.asList(33086, 33087, 33088, 33089, 33090, TOB_LOOT_ROOM_CHEST_PURPLE_PERSONAL, TOB_LOOT_ROOM_CHEST_WHITE_PERSONAL, TOB_LOOT_ROOM_CHEST_PURPLE_OTHER);

    Set<Integer> VER_SINHAZA_REGIONS = ImmutableSet.of(
            14386,
            14642
    );

    Set<String> TOB_CHEST_TARGETS = ImmutableSet.of(
            "Stamina potion(4)",
            "Prayer potion(4)",
            "Saradomin brew(4)",
            "Super restore(4)",
            "Mushroom potato",
            "Shark",
            "Sea turtle",
            "Manta ray"
    );

    String TOB_DRY_COMMAND = "!tobdry";
    String TOB_DRY_STREAK_COMMAND = "!tobdrystreak";
    String TOB_LAST_COMMAND = "!toblast";
    String TOB_LAST_ITEM_COMMAND = "!toblastitem";
}

/*
 * Copyright (c) 2022, Damen <gh: damencs>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:

 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.

 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */