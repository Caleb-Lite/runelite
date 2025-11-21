package net.runelite.client.plugins.pluginhub.com.AttackSoundNotifications.ui;

public class EntryPanelState
{
	private String panelName;
	private String weaponId;
	private String audible_status;
	private String customSoundTextField_contents;
	private String replacing_value;

	public EntryPanelState(EntryPanel panel)
	{
		this.panelName = panel.getName();
		this.weaponId = panel.getWeaponIdString();
		this.audible_status = panel.getAudibleString();
		this.customSoundTextField_contents = panel.getCustomSoundPath();
		this.replacing_value = panel.getReplacingString();
	}

	public String getPanelName()
	{
		return panelName;
	}

	public String getWeaponId()
	{
		return weaponId;
	}

	public String getAudible()
	{
		return audible_status;
	}

	public String getCustomSoundPath()
	{
		return customSoundTextField_contents;
	}

	public String getReplacing()
	{
		return replacing_value;
	}
}
// License from ScreenMarkerPluginPanel
/*
 * Copyright (c) 2018, Kamiel, <https://github.com/Kamielvf>
 * Copyright (c) 2018, Psikoi <https://github.com/psikoi>
 * Copyright (c) 2023, DominickCobb-rs <https://github.com/DominickCobb-rs>
 * Copyright (c) 2024, TJ Stein <https://github.com/AverageToaster>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
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
