package net.runelite.client.plugins.pluginhub.com.flippingutilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//need a no args constructor if you want field defaults to be respected when json is turned into object and its missing the field (when the field is
//newly added for example).
@NoArgsConstructor
public class Option {
    public static final String GE_LIMIT = "ge limit";
    public static final String REMAINING_LIMIT = "rem limit";
    public static final String CASHSTACK = "cashstack";
    public static final String LAST_BUY = "last buy";
    public static final String LAST_SELL = "last sell";
    public static final String INSTA_SELL = "insta sell";
    public static final String INSTA_BUY = "insta buy";
    public static final String WIKI_BUY = "wiki buy";
    public static final String WIKI_SELL = "wiki sell";
    public static final String[] QUANTITY_OPTIONS = new String[]{Option.REMAINING_LIMIT, Option.GE_LIMIT, Option.CASHSTACK};
    public static final String[] PRICE_OPTIONS = new String[]{Option.WIKI_BUY, Option.WIKI_SELL, Option.INSTA_SELL, Option.INSTA_BUY, Option.LAST_BUY, Option.LAST_SELL};
    String key;
    String property;
    String modifier;
    boolean isQuantityOption = true;

    public static Option defaultQuantityOption() {
        return new Option("", Option.GE_LIMIT, "+0", true);
    }

    public static Option defaultPriceOption() {
        return new Option("", Option.INSTA_BUY,"+0", false);
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
