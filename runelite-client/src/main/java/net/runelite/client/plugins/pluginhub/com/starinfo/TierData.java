package net.runelite.client.plugins.pluginhub.com.starinfo;

public class TierData
{
	private static final TierData[] data = new TierData[] {
		new TierData(0.29 * 255, 0.46 * 255, 32, 0, 700), //t1
		new TierData( 0.29 * 255, 0.46 * 255, 32, 0, 700), //t2
		new TierData( 0.29 * 255, 0.46 * 255, 32, 0, 700), //t3
		new TierData( 0.29 * 255, 0.46 * 255, 32, 0, 700), //t4
		new TierData( 0.29 * 255, 0.46 * 255, 32, 0, 700), //t5
		new TierData( 0.29 * 255, 0.46 * 255, 32, 0, 700), //t6
		new TierData(0.29 * 255, 0.46 * 255, 32, 0, 700), //t7
		new TierData(0.29 * 255, 0.46 * 255, 32, 0, 700), //t8
		new TierData(0.29 * 255, 0.46 * 255, 32, 0, 700) //t9
	};

	public final double lowChance;
	public final double highChance;
	public final double xp;
	public final double doubleDustChance;
	public final int tickTime;

	TierData(double lowChance, double highChance, double xp, double doubleDustChance, int tickTime)
	{
		this.lowChance = lowChance;
		this.highChance = highChance;
		this.xp = xp;
		this.doubleDustChance = doubleDustChance;
		this.tickTime = tickTime;
	}

	public static TierData get(int tier)
	{
		if (tier < 1 || tier > data.length) {
			return null;
		}
		return data[tier - 1];
	}

	public double getChance(int level) {
		return (1 + Math.floor(lowChance * (99 - level) / 98) + Math.floor(highChance * (level - 1) / 98)) / 256;
	}
}

/*
 * Copyright (c) 2022, Cute Rock
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
