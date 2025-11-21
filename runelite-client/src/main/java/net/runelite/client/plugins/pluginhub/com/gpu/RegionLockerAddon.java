package net.runelite.client.plugins.pluginhub.com.gpu;

import net.runelite.client.plugins.pluginhub.com.regionlocker.RegionLocker;
import java.util.Arrays;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import org.lwjgl.opengl.GL43C;

public class RegionLockerAddon
{
	@Inject
	private Client client;

	private static final int LOCKED_REGIONS_SIZE = 16;
	private final int[] loadedLockedRegions = new int[LOCKED_REGIONS_SIZE];	private int uniUseGray;
	private int uniUseHardBorder;
	private int uniGrayAmount;
	private int uniGrayColor;
	private int uniBaseX;
	private int uniBaseY;
	private int uniLockedRegions;

	public void initUniforms(int glProgram) {
		uniUseGray = GL43C.glGetUniformLocation(glProgram, "useGray");
		uniUseHardBorder = GL43C.glGetUniformLocation(glProgram, "useHardBorder");
		uniGrayAmount = GL43C.glGetUniformLocation(glProgram, "configGrayAmount");
		uniGrayColor = GL43C.glGetUniformLocation(glProgram, "configGrayColor");
		uniBaseX = GL43C.glGetUniformLocation(glProgram, "baseX");
		uniBaseY = GL43C.glGetUniformLocation(glProgram, "baseY");
		uniLockedRegions = GL43C.glGetUniformLocation(glProgram, "lockedRegions");
	}

	private boolean instanceRegionUnlocked()
	{
		if (client.getMapRegions() != null && client.getMapRegions().length > 0 && (client.getGameState() == GameState.LOGGED_IN || client.getGameState() == GameState.LOADING))
		{
			for (int i = 0; i < client.getMapRegions().length; i++)
			{
				int region = client.getMapRegions()[i];
				if (RegionLocker.hasRegion(region)) return true;
			}
		}
		return false;
	}

	private void initRegionLockerGpu()
	{
		int bx, by;
		bx = client.getBaseX() * 128;
		by = client.getBaseY() * 128;

		Arrays.fill(loadedLockedRegions, 0);

		if (client.getMapRegions() != null && client.getMapRegions().length > 0 && (client.getGameState() == GameState.LOGGED_IN || client.getGameState() == GameState.LOADING))
		{
			for (int i = 0; i < client.getMapRegions().length; i++)
			{
				int region = client.getMapRegions()[i];

				if(RegionLocker.invertShader && !RegionLocker.hasRegion(region))
				{
					loadedLockedRegions[i] = region;
				}
				else if (!RegionLocker.invertShader && RegionLocker.hasRegion(region))
				{
					loadedLockedRegions[i] = region;
				}
			}
		}

		GL43C.glUniform1i(uniBaseX, bx);
		GL43C.glUniform1i(uniBaseY, by);
		GL43C.glUniform1iv(uniLockedRegions, loadedLockedRegions);
	}

	public void beforeDrawRegionLockerGpu() {
		GL43C.glUniform1i(uniUseHardBorder, RegionLocker.hardBorder ? 1 : 0);
		GL43C.glUniform1f(uniGrayAmount, RegionLocker.grayAmount / 255f);
		GL43C.glUniform4f(uniGrayColor, RegionLocker.grayColor.getRed() / 255f, RegionLocker.grayColor.getGreen() / 255f, RegionLocker.grayColor.getBlue() / 255f, RegionLocker.grayColor.getAlpha() / 255f);
		if (!RegionLocker.renderLockedRegions || (client.isInInstancedRegion() && instanceRegionUnlocked()))
		{
			GL43C.glUniform1i(uniUseGray, 0);
		}
		else
		{
			GL43C.glUniform1i(uniUseGray, 1);
			initRegionLockerGpu();
		}
	}
}

/*
 * Copyright (c) 2019, Slay to Stay, <https://github.com/slaytostay>
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