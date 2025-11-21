package net.runelite.client.plugins.pluginhub.com.runeprofile.utils;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.runelite.api.ItemComposition;
import net.runelite.client.game.ItemManager;

import javax.annotation.Nullable;

public class ItemUtils {
    public static final String ITEM_CACHE_BASE_URL = "https://static.runelite.net/cache/item/";

    public static final int VALUABLE_DROP_THRESHOLD = 1_000_000;

    @Getter
    @RequiredArgsConstructor
    public enum ClanBroadcastValue {
        BELLATOR_VESTIGE(28279, 5_000_000),
        MAGUS_VESTIGE(28281, 5_000_000),
        VENATOR_VESTIGE(28283, 5_000_000),
        ULTOR_VESTIGE(28285, 5_000_000),

        NOXIOUS_POINT(29790, 10_000_000),
        NOXIOUS_BLADE(29792, 10_000_000),
        NOXIOUS_POMMEL(29794, 10_000_000),
        ARAXYTE_FANG(29799, 50_000_000),

        MOKHAIOTL_CLOTH(31109, 75_000_000);

        private final int itemId;
        private final int value;

        public static @Nullable ClanBroadcastValue getByItemId(int itemId) {
            for (ClanBroadcastValue value : ClanBroadcastValue.values()) {
                if (value.getItemId() == itemId) {
                    return value;
                }
            }
            return null;
        }
    }

    public static int getUnnotedItemId(@NonNull ItemComposition comp) {
        return isItemNoted(comp) ? comp.getLinkedNoteId() : comp.getId();
    }

    public static int getPerceivedItemValue(@NonNull ItemManager itemManager, int itemId) {
        ClanBroadcastValue clanBroadcastValue = ClanBroadcastValue.getByItemId(itemId);
        if (clanBroadcastValue != null) {
            return clanBroadcastValue.getValue();
        }
        return itemManager.getItemPriceWithSource(itemId, true);
    }

    public static boolean isItemNoted(@NonNull ItemComposition item) {
        return item.getNote() != -1;
    }
}

/* BSD 2-Clause License

Copyright (c) 2022, Jake Barter
All rights reserved.

Copyright (c) 2022, pajlads

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */
