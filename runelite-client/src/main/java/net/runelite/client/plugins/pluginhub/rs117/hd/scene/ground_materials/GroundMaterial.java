package net.runelite.client.plugins.pluginhub.rs117.hd.scene.ground_materials;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.pluginhub.rs117.hd.scene.GroundMaterialManager;
import net.runelite.client.plugins.pluginhub.rs117.hd.scene.materials.Material;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.GsonUtils;

@Getter
@Slf4j
public class GroundMaterial {
	public static final GroundMaterial NONE = new GroundMaterial("NONE", Material.NONE);

	public static GroundMaterial DIRT;
	public static GroundMaterial UNDERWATER_GENERIC;

	public final String name;
	private final Material[] materials;

	public GroundMaterial(String name, Material... materials) {
		this.name = name;
		this.materials = materials;
	}

	public void normalize() {
		for (int j = 0; j < materials.length; j++)
			if (materials[j] == null)
				materials[j] = Material.NONE;
	}

	/**
	 * Get a random material based on the given coordinates.
	 */
	public Material getRandomMaterial(int... worldPos) {
		long hash = 0;
		for (int coord : worldPos)
			hash = hash * 31 + coord;
		long seed = (hash ^ 0x5DEECE66DL) & ((1L << 48) - 1);
		seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
		int r = (int) (seed >>> (48 - 31));
		return materials[r % materials.length];
	}

	@Override
	public String toString() {
		return name;
	}

	@Slf4j
	public static class Adapter extends TypeAdapter<GroundMaterial> {
		@Override
		public GroundMaterial read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL)
				return null;

			if (in.peek() == JsonToken.STRING) {
				String name = in.nextString();
				for (var groundMaterial : GroundMaterialManager.GROUND_MATERIALS)
					if (name.equals(groundMaterial.name))
						return groundMaterial;

				log.warn("No ground material exists with the name '{}' at {}", name, GsonUtils.location(in), new Throwable());
			} else {
				log.warn("Unexpected type {} at {}", in.peek(), GsonUtils.location(in), new Throwable());
			}

			return null;
		}

		@Override
		public void write(JsonWriter out, GroundMaterial groundMaterial) throws IOException {
			if (groundMaterial == null) {
				out.nullValue();
			} else {
				out.value(groundMaterial.name);
			}
		}
	}
}

/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2021, 117 <https://twitter.com/117scape>
 * Copyright (c) 2025, Hooder <ahooder@protonmail.com>
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