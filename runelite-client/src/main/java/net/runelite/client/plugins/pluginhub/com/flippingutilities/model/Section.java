package net.runelite.client.plugins.pluginhub.com.flippingutilities.model;

import lombok.Data;

import java.util.*;

/**
 * Represents one of the sections in the FlippingItemPanel. Since a user can heavily customize what a FlippingItemPanel
 * looks like (its sections), we have to store what their customizations are. This stores the customizations to a section.
 */
@Data
public class Section {
    String name;
    public static final String WIKI_BUY_PRICE = "wiki buy price";
    public static final String WIKI_SELL_PRICE = "wiki sell price";
    public static final String LAST_INSTA_SELL_PRICE = "price check buy price";
    public static final String LAST_INSTA_BUY_PRICE = "price check sell price";
    public static final String LAST_BUY_PRICE = "latest buy price";
    public static final String LAST_SELL_PRICE = "latest sell price";
    public static final String WIKI_PROFIT_EACH = "profit each";
    public static final String POTENTIAL_PROFIT = "potential profit";
    public static final String ROI = "roi";
    public static final String REMAINING_GE_LIMIT = "remaining ge limit";
    public static final String GE_LIMIT_REFRESH_TIMER = "ge limit refresh timer";
    public static final String MARGIN_CHECK_PROFIT_EACH = "margin check profit each";
    public static final List<String> possibleLabels = Arrays.asList(WIKI_BUY_PRICE, WIKI_SELL_PRICE, LAST_INSTA_SELL_PRICE, LAST_INSTA_BUY_PRICE, LAST_BUY_PRICE,
        LAST_SELL_PRICE, WIKI_PROFIT_EACH, POTENTIAL_PROFIT, ROI, REMAINING_GE_LIMIT, GE_LIMIT_REFRESH_TIMER, MARGIN_CHECK_PROFIT_EACH);
    Map<String, Boolean> labels;
    boolean defaultExpanded;

    public Section(String name) {
        this.name = name;
        this.labels = new LinkedHashMap<>();
        for (String label:Section.possibleLabels) {
            this.labels.put(label, false);
        }
    }

    public boolean isShowingLabel(String labelName) {
        if (labels.containsKey(labelName)) {
            return labels.get(labelName);
        }
        return false;
    }

    public void showLabel(String labelName, boolean shouldShow) {
            labels.put(labelName, shouldShow);
    }
}

/*
 * Copyright (c) 2020, Belieal <https://github.com/Belieal>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
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

