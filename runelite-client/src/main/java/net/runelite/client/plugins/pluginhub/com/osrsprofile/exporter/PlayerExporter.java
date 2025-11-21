package net.runelite.client.plugins.pluginhub.com.osrsprofile.exporter;

import net.runelite.client.plugins.pluginhub.com.osrsprofile.api.Api;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.kit.KitType;
import net.runelite.client.RuneLite;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Slf4j
public class PlayerExporter {
    @Inject
    private Api api;

    @Inject
    private Client client;

    @Inject
    private PlayerModelExporter playerModelExporter;

    @Inject
    @Named("developerMode")
    boolean developerMode;

    /*
     6570 - Fire Cape
     21285 - Infernal Max Cape
     21295 - Infernal Cape
     */
    private final int[] capeBlacklist = {6570, 21285, 21295};

    /*
    12788 - Magic shortbow (i)
     */
    private final int[] weaponBlacklist = {12788};

    public void export() {
        try {
            if (this.hasBlacklistedEquipment(KitType.CAPE, this.capeBlacklist)) {
                client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "<col=ff0000>Animated capes are not allowed. Please unequip or switch for another cape", null);
                return;
            }

            if (this.hasBlacklistedEquipment(KitType.WEAPON, this.weaponBlacklist)) {
                client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "<col=ff0000>Animated weapons are not allowed. Please unequip or switch for another weapon", null);
                return;
            }

            ByteArrayOutputStream stream = this.playerModelExporter.export();
            String fileName = client.getAccountHash()+".ply";

            if (developerMode) {
                File file = new File(RuneLite.RUNELITE_DIR, fileName);
                if (file.isFile()) {
                    boolean deleted = file.delete();
                    if (!deleted) {
                        log.debug("Could not delete model file in runelite dir");
                    }
                }

                FileOutputStream fos = new FileOutputStream(file);
                stream.writeTo(fos);
            }

            RequestBody formBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("model", fileName,
                            RequestBody.create(MediaType.parse("application/ply"), stream.toByteArray()))
                    .build();

            this.api.post(client.getAccountHash() + "/model", formBody);
        } catch (Exception e) {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "<col=ff0000>Could not export your player model, please try again. It might have been an animation or item equipped.", null);
            log.error("Could not export player model", e);
        }
    }

    private boolean hasBlacklistedEquipment(KitType equipmentType, int[] blacklist) {
        int currentCape = this.client.getLocalPlayer().getPlayerComposition().getEquipmentId(equipmentType);
        return ArrayUtils.contains(blacklist, currentCape);
    }
}

/*
 * Copyright (c) 2020 Abex
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
 *
 * Sourced from https://github.com/Bram91/Model-Dumper
 */