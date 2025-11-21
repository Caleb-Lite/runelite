package net.runelite.client.plugins.pluginhub.com.sextantlocator;

public class SextantPoint
{
	private SextantCoordinate x;
	private SextantCoordinate y;

	public SextantPoint(SextantCoordinate x, SextantCoordinate y)
	{
		if (x == null)
		{
			this.x = new SextantCoordinate(0, SextantAxis.HORIZONTAL);
		}
		else
		{
			this.x = x;
		}

		if (y == null)
		{
			this.y = new SextantCoordinate(0, SextantAxis.VERTICAL);
		}
		else
		{
			this.y = y;
		}
	}

	public SextantPoint(float worldX, float worldY)
	{
		x = new SextantCoordinate(worldX, SextantAxis.HORIZONTAL);
		y = new SextantCoordinate(worldY, SextantAxis.VERTICAL);
	}

	public void setXCoordinate(SextantCoordinate newX)
	{
		if (newX == null)
		{
			this.x = new SextantCoordinate(0, SextantAxis.HORIZONTAL);
		}
		else
		{
			this.x = newX;
		}
	}

	public void setYCoordinate(SextantCoordinate newY)
	{
		if (newY == null)
		{
			this.y = new SextantCoordinate(0, SextantAxis.VERTICAL);
		}
		else
		{
			this.y = newY;
		}
	}

	public SextantCoordinate getXCoordinate()
	{
		return x;
	}

	public SextantCoordinate getYCoordinate()
	{
		return y;
	}

	@Override
	public String toString()
	{
		if (x == null || y == null)
		{
			return "";
		}

		return y + " | " + x;
	}
}

/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2021, Miguel Sousa
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