package net.runelite.client.plugins.pluginhub.com.phoenixNecklaceJingle;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("phoenixNecklaceJingle")
public interface PhoenixNecklaceJingleConfig extends Config
{
    @ConfigSection(
            name = "Custom Jingle",
            description = "Use a custom jingle when your Phoenix Necklace breaks.",
            position = 1
    )
    String CUSTOM_JINGLE = "customJingle";

	@Range(min = 1, max = 100)
	@ConfigItem(
			keyName = "volume",
			name = "Volume",
			description = "Sound effect volume",
			position = 2
	)
	default int volume() {
		return 100;
	}

    @ConfigItem(
            keyName = "soundID",
            name = "Sound ID",
            description = "The sound ID you wish to play. <br>" +
                    "Sound List: https://oldschool.runescape.wiki/w/List_of_in-game_sound_IDs",
            position = 1
    )
    default int soundID()
    {
        return 3924;
    }
    @ConfigItem(
            keyName = "enableCustomSound",
            name = "Enable Custom Sound",
            description = "Use a custom sound to play rather than an in-game sound  <br>" +
                    "Replace custom.wav in the .runelite folder with your desired sound",
            position = 3,
            section = CUSTOM_JINGLE
    )
    default boolean enableCustomSoundsVolume() { return false; }



}

/*
 * Copyright (c) 2023, petertalbanese <https://github.com/petertalbanese>
 * Copyright (c) 2023, damencs <https://github.com/damencs>
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