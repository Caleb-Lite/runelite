package net.runelite.client.plugins.pluginhub.com.sextantlocator;

import net.runelite.api.coords.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SextantCoordinateTest
{
	private final float OBSERVATORY_X = 2440f;
	private final float OBSERVATORY_Y = 3161f;
	private final float minutePerTile = 15f / 8f;

	@Test
	public void observatoryXTest()
	{
		SextantCoordinate x = new SextantCoordinate(OBSERVATORY_X, SextantAxis.HORIZONTAL);

		assertEquals(0, x.getDegrees());
		assertEquals(0f, x.getMinutes(), 0f);
		assertSame(x.getDirection(), Direction.EAST);
	}

	@Test
	public void observatoryYTest()
	{
		SextantCoordinate y = new SextantCoordinate(OBSERVATORY_Y, SextantAxis.VERTICAL);

		assertEquals(0, y.getDegrees());
		assertEquals(0f, y.getMinutes(), 0f);
		assertSame(y.getDirection(), Direction.NORTH);
	}

	@Test
	public void singleStepTest()
	{
		SextantCoordinate coordinate = new SextantCoordinate(OBSERVATORY_X + 1f, SextantAxis.HORIZONTAL);

		assertEquals(0, coordinate.getDegrees());
		assertEquals(1.875f, coordinate.getMinutes(), 0f);
		assertSame(coordinate.getDirection(), Direction.EAST);
	}

	@Test
	public void sixtyMinutesTest()
	{
		SextantCoordinate coordinate = new SextantCoordinate(OBSERVATORY_X + 60 / minutePerTile, SextantAxis.HORIZONTAL);

		assertEquals(1, coordinate.getDegrees());
		assertEquals(0f, coordinate.getMinutes(), 0f);
		assertSame(coordinate.getDirection(), Direction.EAST);
	}

	@Test
	public void ninetyMinutesTest()
	{
		SextantCoordinate coordinate = new SextantCoordinate(OBSERVATORY_X + 90 / minutePerTile, SextantAxis.HORIZONTAL);

		assertEquals(1, coordinate.getDegrees());
		assertEquals(30f, coordinate.getMinutes(), 0f);
		assertSame(coordinate.getDirection(), Direction.EAST);
	}

	@Test
	public void positiveCoordinatesRoundingTest()
	{
		SextantCoordinate coordinate;

		// Good...
		coordinate = new SextantCoordinate(3069f, SextantAxis.HORIZONTAL);

		assertEquals(19, coordinate.getDegrees());
		assertEquals(39f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.EAST);

		coordinate = new SextantCoordinate(3225f, SextantAxis.HORIZONTAL);

		assertEquals(24, coordinate.getDegrees());
		assertEquals(31f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.EAST);

		coordinate = new SextantCoordinate(3178f, SextantAxis.HORIZONTAL);

		assertEquals(23, coordinate.getDegrees());
		assertEquals(3f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.EAST);

		// ... luck
		coordinate = new SextantCoordinate(3517f, SextantAxis.VERTICAL);

		assertEquals(11, coordinate.getDegrees());
		assertEquals(7f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.NORTH);

		coordinate = new SextantCoordinate(3219f, SextantAxis.VERTICAL);

		assertEquals(1, coordinate.getDegrees());
		assertEquals(48f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.NORTH);

		coordinate = new SextantCoordinate(3475f, SextantAxis.VERTICAL);

		assertEquals(9, coordinate.getDegrees());
		assertEquals(48f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.NORTH);
	}

	@Test
	public void negativeCoordinatesRoundingTest()
	{
		SextantCoordinate coordinate;

		coordinate = new SextantCoordinate(2210f, SextantAxis.HORIZONTAL);

		assertEquals(7f, coordinate.getDegrees(), 0f);
		assertEquals(11f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.WEST);

		coordinate = new SextantCoordinate(2152f, SextantAxis.HORIZONTAL);

		assertEquals(9f, coordinate.getDegrees(), 0f);
		assertEquals(0f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.WEST);

		coordinate = new SextantCoordinate(2377f, SextantAxis.HORIZONTAL);

		assertEquals(1f, coordinate.getDegrees(), 0f);
		assertEquals(58f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.WEST);

		coordinate = new SextantCoordinate(2855f, SextantAxis.VERTICAL);

		assertEquals(9f, coordinate.getDegrees(), 0f);
		assertEquals(33f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.SOUTH);

		coordinate = new SextantCoordinate(3072f, SextantAxis.VERTICAL);

		assertEquals(2f, coordinate.getDegrees(), 0f);
		assertEquals(46f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.SOUTH);

		coordinate = new SextantCoordinate(3036f, SextantAxis.VERTICAL);

		assertEquals(3f, coordinate.getDegrees(), 0f);
		assertEquals(54f, coordinate.getMinutesInteger(), 0f);
		assertSame(coordinate.getDirection(), Direction.SOUTH);
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