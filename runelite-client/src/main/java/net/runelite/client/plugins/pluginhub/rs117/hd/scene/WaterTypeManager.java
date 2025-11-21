package net.runelite.client.plugins.pluginhub.rs117.hd.scene;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.plugins.pluginhub.rs117.hd.HdPlugin;
import net.runelite.client.plugins.pluginhub.rs117.hd.opengl.uniforms.UBOWaterTypes;
import net.runelite.client.plugins.pluginhub.rs117.hd.scene.materials.Material;
import net.runelite.client.plugins.pluginhub.rs117.hd.scene.water_types.WaterType;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.FileWatcher;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.Props;
import net.runelite.client.plugins.pluginhub.rs117.hd.utils.ResourcePath;

import static rs117.hd.utils.ResourcePath.path;

@Slf4j
@Singleton
public class WaterTypeManager {
	private static final ResourcePath WATER_TYPES_PATH = Props
		.getFile("rlhd.water-types-path", () -> path(WaterTypeManager.class, "water_types.json"));

	@Inject
	private ClientThread clientThread;

	@Inject
	private HdPlugin plugin;

	@Inject
	private MaterialManager materialManager;

	@Inject
	private TileOverrideManager tileOverrideManager;

	@Inject
	private FishingSpotReplacer fishingSpotReplacer;

	public static WaterType[] WATER_TYPES = {};

	public UBOWaterTypes uboWaterTypes;

	private FileWatcher.UnregisterCallback fileWatcher;

	public void startUp() {
		fileWatcher = WATER_TYPES_PATH.watch((path, first) -> clientThread.invoke(() -> {
			try {
				var rawWaterTypes = path.loadJson(plugin.getGson(), WaterType[].class);
				if (rawWaterTypes == null)
					throw new IOException("Empty or invalid: " + path);
				log.debug("Loaded {} water types", rawWaterTypes.length);

				var waterTypes = new WaterType[rawWaterTypes.length + 1];
				waterTypes[0] = WaterType.NONE;
				System.arraycopy(rawWaterTypes, 0, waterTypes, 1, rawWaterTypes.length);

				Material fallbackNormalMap = materialManager.getMaterial("WATER_NORMAL_MAP_1");
				for (int i = 0; i < waterTypes.length; i++)
					waterTypes[i].normalize(i, fallbackNormalMap);

				var oldWaterTypes = WATER_TYPES;
				WATER_TYPES = waterTypes;
				// Update statically accessible water types
				WaterType.WATER = get("WATER");
				WaterType.WATER_FLAT = get("WATER_FLAT");
				WaterType.SWAMP_WATER_FLAT = get("SWAMP_WATER_FLAT");
				WaterType.ICE = get("ICE");

				if (uboWaterTypes != null)
					uboWaterTypes.destroy();
				uboWaterTypes = new UBOWaterTypes(waterTypes);

				if (first)
					return;

				fishingSpotReplacer.despawnRuneLiteObjects();
				fishingSpotReplacer.update();

				boolean indicesChanged = oldWaterTypes == null || oldWaterTypes.length != waterTypes.length;
				if (!indicesChanged) {
					for (int i = 0; i < waterTypes.length; i++) {
						if (!waterTypes[i].name.equals(oldWaterTypes[i].name)) {
							indicesChanged = true;
							break;
						}
					}
				}

				if (indicesChanged) {
					// Reload everything which depends on water type indices
					tileOverrideManager.shutDown();
					tileOverrideManager.startUp();
					plugin.renderer.clearCaches();
					plugin.renderer.reloadScene();
				}
			} catch (IOException ex) {
				log.error("Failed to load water types:", ex);
			}
		}));
	}

	public void shutDown() {
		if (fileWatcher != null)
			fileWatcher.unregister();
		fileWatcher = null;

		if (uboWaterTypes != null)
			uboWaterTypes.destroy();
		uboWaterTypes = null;

		WATER_TYPES = new WaterType[0];
	}

	public void restart() {
		shutDown();
		startUp();
	}

	public WaterType get(String name) {
		for (var type : WATER_TYPES)
			if (name.equals(type.name))
				return type;
		return WaterType.NONE;
	}
}
