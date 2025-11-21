package net.runelite.client.plugins.pluginhub.com.notloc.targettruetile;

import lombok.Getter;
import net.runelite.api.NPC;

import java.util.*;

public class TargetMemory {

    @Getter
    private final Set<NPC> npcs = new HashSet<>();
    private final Map<NPC, Target> targets = new HashMap<>();

    public void acknowledgeTarget(NPC npc) {
        Target target = targets.get(npc);
        if (target == null) {
            target = add(npc);
        }
        target.setLastTargetedAt(now());
    }

    public void forgetTarget(NPC npc) {
        remove(npc);
    }

    public void forgetOldTargets(int threshold_s) {
        long now = now();
        List<NPC> npcsToForget = new ArrayList<>();

        for (Target target : targets.values()) {
            long delta = now - target.getLastTargetedAt();
            if (delta >= threshold_s || !target.isVisible()) {
                npcsToForget.add(target.getNpc());
            }
        }

        for (NPC npc : npcsToForget) {
            remove(npc);
        }
    }

    public void forgetAll() {
        npcs.clear();
        targets.clear();
    }

    private Target add(NPC npc) {
        Target target = new Target(npc, now());
        targets.put(npc, target);
        npcs.add(npc);
        return target;
    }

    private void remove(NPC npc) {
        targets.remove(npc);
        npcs.remove(npc);
    }

    private static long now() {
        return java.time.Instant.now().getEpochSecond();
    }
}

/*
 * Copyright (c) 2021, LeikvollE
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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