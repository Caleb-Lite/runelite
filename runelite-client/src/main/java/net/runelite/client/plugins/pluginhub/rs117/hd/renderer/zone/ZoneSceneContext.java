package net.runelite.client.plugins.pluginhub.rs117.hd.renderer.zone;

import javax.annotation.Nullable;
import net.runelite.api.*;
import net.runelite.client.plugins.pluginhub.rs117.hd.scene.SceneContext;

public class ZoneSceneContext extends SceneContext {
//	public final WorldView worldView;
//	public final Zone[][] zones;
//	public final int numZonesX, numZonesY;

	public ZoneSceneContext(
		Client client,
		WorldView worldView,
		Scene scene,
		int expandedMapLoadingChunks,
		@Nullable SceneContext previous
	) {
		super(client, scene, expandedMapLoadingChunks);
//		this.worldView = worldView;
		if (worldView.getId() != -1) {
			sceneOffset = 0;
			sizeX = worldView.getSizeX();
			sizeZ = worldView.getSizeY();
		}
//		numZonesX = worldView.getSizeX();
//		numZonesY = worldView.getSizeY();
//		zones = new Zone[numZonesX][numZonesY];
	}
}

/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2021, 117 <https://twitter.com/117scape>
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