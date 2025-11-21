package net.runelite.client.plugins.pluginhub.com.flippingutilities.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class AccountWideData {
    List<Option> options = new ArrayList<>();
    List<Section> sections = new ArrayList<>();
    boolean shouldMakeNewAdditions = true;
    boolean enhancedSlots = true;
    String jwt;

    public boolean setDefaults() {
        boolean didChangeData = changeOldPropertyNames();

        if (options.isEmpty()) {
            setDefaultOptions();
            shouldMakeNewAdditions = false;
            didChangeData = true;
        }
        //adding wiki options to users' existing options only once and making sure that its not added again by setting shouldMakeNewAdditions.
        //i need to use that flag so i don't add it multiple times in case a user deletes those options.
        boolean alreadyHasWikiOptions = options.stream().anyMatch(o -> o.getProperty().equals(Option.WIKI_BUY) || o.getProperty().equals(Option.WIKI_SELL));
        if (shouldMakeNewAdditions && !alreadyHasWikiOptions) {
            options.add(0, new Option("n", Option.WIKI_SELL, "+0", false));
            options.add(0, new Option("j", Option.WIKI_BUY, "+0", false));
            shouldMakeNewAdditions = false;
            didChangeData = true;
        }

        if (sections.isEmpty()) {
            didChangeData = true;
            setDefaultFlippingItemPanelSections();
        }
        return didChangeData;
    }

    private boolean changeOldPropertyNames() {
        boolean changedOldNames = false;
        for (Option o : options) {
            if (o.getProperty().equals("marg sell")) {
                o.setProperty(Option.INSTA_BUY);
                changedOldNames = true;
            }
            if (o.getProperty().equals("marg buy")) {
                o.setProperty(Option.INSTA_SELL);
                changedOldNames = true;
            }
        }

        return changedOldNames;
    }

    private void setDefaultOptions() {
        options.add(new Option("n", Option.WIKI_SELL, "+0", false));
        options.add(new Option("j", Option.WIKI_BUY, "+0", false));
        options.add(new Option("p", Option.INSTA_BUY, "+0", false));
        options.add(new Option("l", Option.INSTA_SELL, "+0", false));
        options.add(new Option("o", Option.LAST_BUY, "+0", false));
        options.add(new Option("u", Option.LAST_SELL, "+0", false));

        options.add(new Option("p", Option.GE_LIMIT, "+0", true));
        options.add(new Option("l", Option.REMAINING_LIMIT, "+0", true));
        options.add(new Option("o", Option.CASHSTACK, "+0", true));
    }

    private void setDefaultFlippingItemPanelSections() {
        Section importantSection = new Section("Important");
        Section otherSection = new Section("Other");

        importantSection.defaultExpanded = true;
        importantSection.showLabel(Section.WIKI_BUY_PRICE, true);
        importantSection.showLabel(Section.WIKI_SELL_PRICE, true);
        importantSection.showLabel(Section.LAST_BUY_PRICE, true);
        importantSection.showLabel(Section.LAST_SELL_PRICE, true);
        importantSection.showLabel(Section.LAST_INSTA_SELL_PRICE, true);
        importantSection.showLabel(Section.LAST_INSTA_BUY_PRICE, true);

        otherSection.showLabel(Section.WIKI_PROFIT_EACH, true);
        otherSection.showLabel(Section.MARGIN_CHECK_PROFIT_EACH, true);
        otherSection.showLabel(Section.POTENTIAL_PROFIT, true);
        otherSection.showLabel(Section.REMAINING_GE_LIMIT, true);
        otherSection.showLabel(Section.ROI, true);
        otherSection.showLabel(Section.GE_LIMIT_REFRESH_TIMER, true);

        sections.add(importantSection);
        sections.add(otherSection);
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
