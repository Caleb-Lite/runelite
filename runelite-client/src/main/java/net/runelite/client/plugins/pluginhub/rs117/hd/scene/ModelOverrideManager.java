package net.runelite.client.plugins.pluginhub.rs117.hd.scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.plugins.pluginhub.rs117.hd.HdPlugin;
import net.runelite.client.plugins.pluginhub.rs117.hd.scene.model_overrides.ModelOverride;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.FileWatcher;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.ModelHash;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.Props;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.ResourcePath;

import static rs117.hd.utils.ResourcePath.path;

@Slf4j
@Singleton
public class ModelOverrideManager {
	private static final ResourcePath MODEL_OVERRIDES_PATH = Props
		.getFile("rlhd.model-overrides-path", () -> path(ModelOverrideManager.class, "model_overrides.json"));

	@Inject
	private ClientThread clientThread;

	@Inject
	private HdPlugin plugin;

	@Inject
	private GamevalManager gamevalManager;

	@Inject
	private FishingSpotReplacer fishingSpotReplacer;

	private final HashMap<Integer, ModelOverride> modelOverrides = new HashMap<>();

	private FileWatcher.UnregisterCallback fileWatcher;

	public void startUp() {
		fileWatcher = MODEL_OVERRIDES_PATH.watch((path, first) -> clientThread.invoke(() -> {
			try {
				ModelOverride[] parsedOverrides = path.loadJson(plugin.getGson(), ModelOverride[].class);
				if (parsedOverrides == null)
					throw new IOException("Empty or invalid: " + path);

				modelOverrides.clear();
				for (ModelOverride override : parsedOverrides) {
					try {
						override.normalize(plugin);
					} catch (IllegalStateException ex) {
						log.error("Invalid model override '{}': {}", override.description, ex.getMessage());
						continue;
					}

					addOverride(override);

					if (override.hideInAreas.length > 0) {
						var hider = override.copy();
						hider.hide = true;
						hider.areas = override.hideInAreas;
						addOverride(hider);
					}
				}

				addOverride(fishingSpotReplacer.getModelOverride());

				log.debug("Loaded {} model overrides", modelOverrides.size());

				if (first)
					return;

				plugin.renderer.clearCaches();
				plugin.renderer.reloadScene();
			} catch (Exception ex) {
				log.error("Failed to load model overrides:", ex);
			}
		}));
	}

	public void shutDown() {
		if (fileWatcher != null)
			fileWatcher.unregister();
		fileWatcher = null;

		modelOverrides.clear();
	}

	public void reload() {
		shutDown();
		startUp();
	}

	private void addOverride(@Nullable ModelOverride override) {
		if (override == null || override.seasonalTheme != null && override.seasonalTheme != plugin.configSeasonalTheme)
			return;

		for (int id : override.npcIds)
			addEntry(ModelHash.TYPE_NPC, id, override);
		for (int id : override.objectIds)
			addEntry(ModelHash.TYPE_OBJECT, id, override);
		for (int id : override.projectileIds)
			addEntry(ModelHash.TYPE_PROJECTILE, id, override);
		for (int id : override.graphicsObjectIds)
			addEntry(ModelHash.TYPE_GRAPHICS_OBJECT, id, override);
	}

	private void addEntry(int type, int id, ModelOverride entry) {
		int uuid = ModelHash.packUuid(type, id);
		ModelOverride current = modelOverrides.get(uuid);

		if (current != null && !Objects.equals(current.seasonalTheme, entry.seasonalTheme)) {
			// Seasonal theme overrides should take precedence
			if (current.seasonalTheme != null)
				return;
			current = null;
		}

		boolean isDuplicate = false;

		if (entry.areas.length == 0) {
			// Non-area-restricted override, of which there can only be one per UUID

			// A dummy override is used as the base if only area-specific overrides exist
			isDuplicate = current != null && !current.isDummy;

			if (isDuplicate && Props.DEVELOPMENT) {
				String name = null;
				switch (type) {
					case ModelHash.TYPE_NPC:
						name = gamevalManager.getNpcName(id);
						break;
					case ModelHash.TYPE_OBJECT:
						name = gamevalManager.getObjectName(id);
						break;
					case ModelHash.TYPE_PROJECTILE:
					case ModelHash.TYPE_GRAPHICS_OBJECT:
						name = gamevalManager.getSpotanimName(id);
						break;
				}

				// This should ideally not be reached, so print helpful warnings in development mode
				if (entry.hideInAreas.length > 0) {
					log.error(
						"Replacing {} ({}) from '{}' with hideInAreas-override '{}'. This is likely a mistake...",
						name, id, current.description, entry.description
					);
				} else {
					log.error(
						"Replacing {} ({}) from '{}' with '{}'. The first-mentioned override should be removed.",
						name, id, current.description, entry.description
					);
				}
			}

			if (current != null && current.areaOverrides != null && !current.areaOverrides.isEmpty()) {
				var areaOverrides = current.areaOverrides;
				current = entry.copy();
				current.areaOverrides = areaOverrides;
			} else {
				current = entry;
			}

			modelOverrides.put(uuid, current);
		} else {
			if (current == null)
				current = ModelOverride.NONE;

			if (current.areaOverrides == null) {
				// We need to replace the override with a copy that has a separate list of area overrides to avoid conflicts
				current = current.copy();
				current.areaOverrides = new HashMap<>();
				modelOverrides.put(uuid, current);
			}

			for (var area : entry.areas)
				current.areaOverrides.put(area, entry);
		}
	}

	@Nonnull
	public ModelOverride getOverride(int uuid, int[] worldPos) {
		var override = modelOverrides.get(ModelHash.getUuidWithoutSubType(uuid));
		if (override == null)
			return ModelOverride.NONE;

		if (override.areaOverrides != null)
			for (var entry : override.areaOverrides.entrySet())
				if (entry.getKey().contains(worldPos))
					return entry.getValue();

		return override;
	}

	@Nonnull
	public ModelOverride getOverride(TileObject tileObject, int[] worldPos) {
		return getOverride(ModelHash.packUuid(ModelHash.TYPE_OBJECT, tileObject.getId()), worldPos);
	}
}

/*
 * Copyright (c) 2019 Abex
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