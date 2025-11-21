package net.runelite.client.plugins.pluginhub.com.tilepacks;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.runelite.client.plugins.pluginhub.com.tilepacks.data.TilePackConfig;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for all functions that interface with or modify the custom configs of a TilePack.
 * A custom config is settings for a tile pack.
 * Both built in and custom packs have a config.
 */
@Slf4j
@Value
public class TilePackConfigManager {
    private static final String CONFIG_GROUP = "tilePacks";
    private static final String TILE_PACK_CONFIGS = "tilePackConfigs";

    @NonFinal
    private Map<Integer, TilePackConfig> customConfigs = new HashMap<Integer, TilePackConfig>();

    private final Gson gson;
    private final ConfigManager configManager;

    @Inject
    TilePackConfigManager(Gson gson, ConfigManager configManager) {
        this.gson = gson;
        this.configManager = configManager;
        this.loadTilePackConfigs();
    }

    //loads the custom pack configs from the settings file
    private void loadTilePackConfigs() {
        String json = configManager.getConfiguration(CONFIG_GROUP, TILE_PACK_CONFIGS);

        if (Strings.isNullOrEmpty(json)) {
            return;
        }
        customConfigs = gson.fromJson(json, new TypeToken<Map<Integer, TilePackConfig>>() {
        }.getType());
    }

    //replaces a config with the latest version, then saves the custom config list.
    public void updateTilePackConfig(TilePackConfig tilePackConfig) {
        customConfigs.put(tilePackConfig.packId, tilePackConfig);

        String json = gson.toJson(customConfigs);
        configManager.setConfiguration(CONFIG_GROUP, TILE_PACK_CONFIGS, json);
    }

    //returns the custom config of a pack, or a default config if it doesn't exist.
    public TilePackConfig getTilePackConfig(Integer packId) {
        TilePackConfig tilePackConfig = customConfigs.get(packId);
        if(tilePackConfig != null) {
            return tilePackConfig;
        }

        return new TilePackConfig(packId, true);
    }
}
