package net.runelite.client.plugins.pluginhub.rs117.hd.scene.lights;

public enum Alignment
{
	CUSTOM(0, false, true),
	@Deprecated
	CENTER(0, false, false),

	NORTH(0, true, false),
	NORTHEAST(256, true, false),
	NORTHEAST_CORNER(256, false, false),
	EAST(512, true, false),
	SOUTHEAST(768, true, false),
	SOUTHEAST_CORNER(768, false, false),
	SOUTH(1024, true, false),
	SOUTHWEST(1280, true, false),
	SOUTHWEST_CORNER(1280, false, false),
	WEST(1536, true, false),
	NORTHWEST(1792, true, false),
	NORTHWEST_CORNER(1792, false, false),

	BACK(0, true, true),
	BACKLEFT(256, true, true),
	BACKLEFT_CORNER(256, false, true),
	LEFT(512, true, true),
	FRONTLEFT(768, true, true),
	FRONTLEFT_CORNER(768, false, true),
	FRONT(1024, true, true),
	FRONTRIGHT(1280, true, true),
	FRONTRIGHT_CORNER(1280, false, true),
	RIGHT(1536, true, true),
	BACKRIGHT(1792, true, true),
	BACKRIGHT_CORNER(1792, false, true);

	public final int orientation;
	public final boolean radial;
	public final boolean relative;

	Alignment(int orientation, boolean radial, boolean relative)
	{
		this.orientation = orientation;
		this.radial = radial;
		this.relative = relative;
	}
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