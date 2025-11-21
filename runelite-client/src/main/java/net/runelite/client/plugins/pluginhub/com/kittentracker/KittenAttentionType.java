package net.runelite.client.plugins.pluginhub.com.kittentracker;

public enum KittenAttentionType {
    NEW_KITTEN(0, KittenPlugin.ATTENTION_TIME_NEW_KITTEN_IN_SECONDS),
    SINGLE_STROKE(1, KittenPlugin.ATTENTION_TIME_SINGLE_STROKE_IN_SECONDS),
    MULTIPLE_STROKES(2, KittenPlugin.ATTENTION_TIME_MULTIPLE_STROKES_IN_SECONDS),
    BALL_OF_WOOL(3, KittenPlugin.ATTENTION_TIME_BALL_OF_WOOL_IN_SECONDS);

    private final int id;
    private final int attentionTime;

    KittenAttentionType(int id, int attentionTime) {
        this.id = id;
        this.attentionTime = attentionTime;
    }

    int getId() {
        return id;
    }

    int getAttentionTime() {
        return attentionTime;
    }

    int getTimeBeforeKittenRunsAway() {
        return attentionTime + KittenPlugin.ATTENTION_FIRST_WARNING_TIME_LEFT_IN_SECONDS;
    }
}

/*
 * Copyright (c) 2018, Nachtmerrie <https://github.com/Nachtmerrie>
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