package net.runelite.client.plugins.pluginhub.com.sacredoil;

import net.runelite.client.config.*;

@ConfigGroup("sacredoil")
public interface SacredOilConfig extends Config
{
	@ConfigItem(
		keyName = "braceletcounter",
		name = "Flamtaer Bracelet Counter",
		description = "Displays Flamtaer Bracelet Charge Counter",
		position = 1
	)
	default boolean braceletCounter()
	{
		return true;
	}

	@ConfigItem(
			keyName = "bracletnotify",
			name = "Flamtaer Bracelet Notification",
			description = "Notifies User when Flamtaer Bracelet breaks",
			position = 2
	)
	default boolean braceletNotify() { return true; }

	@ConfigItem(
			keyName = "sanctitycheck",
			name = "Sanctity Notification",
			description = "Toggles Sanctity Notifications",
			position = 3
	)
	default boolean sanctityCheck() { return true; }

	@ConfigItem(
			keyName = "sanctitynotify",
			name = "Sanctity Level",
			description = "Notifies User when Sanctity reaches spcified level",
			position = 4
	)
	@Units(Units.PERCENT)
	@Range(max = 100)
	default int sanctityNotify() { return 100; }

	@ConfigItem(
			keyName = "sanctifydelay",
			name = "Sanctity Notification Delay",
			description = "Time until a notification is sent",
			position = 5
	)
	@Units(Units.MILLISECONDS)
	default int getSanctityDelay() { return 1000; }
}

/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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