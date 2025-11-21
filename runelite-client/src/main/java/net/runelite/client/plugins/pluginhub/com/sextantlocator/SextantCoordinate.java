package net.runelite.client.plugins.pluginhub.com.sextantlocator;

import net.runelite.api.coords.Direction;

import static java.lang.Math.abs;

public class SextantCoordinate
{
	private final float minutes;
	private final SextantAxis axis;

	private final float OBSERVATORY_X = 2440f;
	private final float OBSERVATORY_Y = 3161f;
	private final float MINUTES_PER_TILE = 15f / 8f;

	public SextantCoordinate(float degrees, float minutes, Direction direction)
	{
		if (direction == Direction.NORTH || direction == Direction.SOUTH)
		{
			axis = SextantAxis.VERTICAL;
		}
		else
		{
			axis = SextantAxis.HORIZONTAL;
		}

		this.minutes = degrees * 60 + minutes;
	}

	public SextantCoordinate(float worldCoordinate, SextantAxis axis)
	{
		this.axis = axis;
		this.minutes = worldCoordinateToMinutes(worldCoordinate, axis);
	}

	public int getDegrees()
	{
		return (int) abs(minutes / 60);
	}

	public float getMinutes()
	{
		return abs(minutes % 60);
	}

	public int getMinutesInteger()
	{
		return (int) abs(minutes % 60);
	}

	public Direction getDirection()
	{
		if (minutes >= 0)
		{
			if (axis == SextantAxis.HORIZONTAL)
			{
				return Direction.EAST;
			}

			return Direction.NORTH;
		}

		if (axis == SextantAxis.HORIZONTAL)
		{
			return Direction.WEST;
		}

		return Direction.SOUTH;
	}

	private float worldCoordinateToMinutes(float worldCoordinate, SextantAxis axis)
	{
		if (axis == SextantAxis.HORIZONTAL)
		{
			worldCoordinate -= OBSERVATORY_X;
		}
		else
		{
			worldCoordinate -= OBSERVATORY_Y;
		}

		return worldCoordinate * MINUTES_PER_TILE;
	}

	@Override
	public String toString()
	{
		return getDegrees() + " deg. " + getMinutesInteger() + " min. " + getDirection().toString().charAt(0);
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