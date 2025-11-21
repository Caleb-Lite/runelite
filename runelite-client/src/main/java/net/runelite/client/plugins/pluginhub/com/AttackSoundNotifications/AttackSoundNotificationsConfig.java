package net.runelite.client.plugins.pluginhub.com.AttackSoundNotifications;

import static com.AttackSoundNotifications.AttackSoundNotificationsPlugin.CONFIG_GROUP;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup(CONFIG_GROUP)
public interface AttackSoundNotificationsConfig extends Config
{

	@Range(min = 0, max = 200)
	@ConfigItem(position = 1, keyName = "Volume", name = "Volume", description = "Control how loud the audio should be")
	default int Volume()
	{
		return 25;
	}

	@ConfigItem(keyName = "help", name = "Help", description = "Panel Help", position = 2, warning = "Reset this field if you accidentally remove it.")
	default String help()
	{
		return "When you use the plugin panel " +
			"the default weapon is ALL weapons (-1). " +
			"If you don't select a weapon, it will " +
			"play the sound for any weapon you hit with. " +
			"Otherwise it will play only for the weapon you select";
	}

	// Instructions to add custom sounds
	@ConfigItem(keyName = "customHitSound", name = "Set Custom Sound", description = "Instructions to set custom sounds", position = 3, warning = "Reset this field if you accidentally remove it.")
	default String customHitSound()
	{
		return "Adding a custom sound:\n" +
			"Add your custom sound file anywhere you want\n" +
			"Copy the absolute filepath\n" +
			"Paste the filepath into the text box under the dropdown menus\n" +
			"Click \"Test the sound\" to make sure the plugin found it\n\n" +
			"You'll receive a chat message in-game if your file can't be found when you attack.";
	}
}

/* Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
 * Copyright (c) 2022, Ferrariic <ferrariictweet@gmail.com>
 * Copyright (c) 2018, Raqes <j.raqes@gmail.com>
 * Copyright (c) 2019, Ron Young <https://github.com/raiyni>
 * Copyright (c) 2023, Jacob Browder <https://github.com/DominickCobb-rs>
 * Copyright (c) 2024, TJ Stein <https://github.com/AverageToaster>
 * All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
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
