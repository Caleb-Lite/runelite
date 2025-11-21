package net.runelite.client.plugins.pluginhub.mvdicarlo.crabmanmode;

import lombok.Value;
import java.time.OffsetDateTime;
import net.runelite.client.util.AsyncBufferedImage;

public class ItemObject {
    int id;
    String name;
    boolean tradeable;
    OffsetDateTime acquiredOn;
    AsyncBufferedImage icon;

    public ItemObject(int id, String name, boolean tradeable, OffsetDateTime acquiredOn, AsyncBufferedImage icon) {
        this.id = id;
        this.name = name;
        this.tradeable = tradeable;
        this.acquiredOn = acquiredOn;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isTradeable() {
        return tradeable;
    }

    public OffsetDateTime getAcquiredOn() {
        return acquiredOn;
    }

    public AsyncBufferedImage getIcon() {
        return icon;
    }
}

/*
 * Original License
 * Copyright (c) 2019, CodePanter <https://github.com/codepanter>
 * Copyright (c) 2024, mvdicarlo <https://github.com/mvdicarlo>
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