package net.runelite.client.plugins.pluginhub.com.tileman;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("tilemanMode")
public interface TilemanModeConfig extends Config {
    @ConfigSection(
            name = "Game Mode",
            description = "Select your Tileman game mode'",
            position = 1
    )
    String gameModeSection = "gameMode";

    @ConfigSection(
            name = "Settings",
            description = "Settings'",
            position = 2
    )
    String settingsSection = "settings";

    @ConfigSection(
            name = "Custom Game Mode",
            description = "Create a custom Tileman game mode. Be sure to 'Enable Custom Game Mode'",
            position = 99,
            closedByDefault = true
    )
    String customGameModeSection = "customGameMode";

    public enum TilemanGameMode {
        COMMUNITY,
        STRICT,
        ACCELERATED
    }

    @Alpha
    @ConfigItem(
            keyName = "gameMode",
            name = "Game Mode",
            section = gameModeSection,
            description = "Select your Tileman game mode",
            position = 1
    )
    default TilemanGameMode gameMode() {
        return TilemanGameMode.COMMUNITY;
    }

    @ConfigItem(
            keyName = "automarkTiles",
            name = "Auto-mark tiles",
            section = settingsSection,
            description = "Automatically mark tiles as you walk.",
            position = 2
    )
    default boolean automarkTiles() {
        return false;
    }

    @Range(
            min = Integer.MIN_VALUE
    )
    @ConfigItem(
            keyName = "warningLimit",
            name = "Unspent tiles warning",
            section = settingsSection,
            description = "Highlights overlay when limit reached",
            position = 3
    )
    default int warningLimit() {
        return 20;
    }

    @ConfigItem(
            keyName = "enableTilesWarning",
            name = "Enable Tiles Warning",
            section = settingsSection,
            description = "Turns on tile warnings when you reach your set limit or 0.",
            position = 4
    )
    default boolean enableTileWarnings() {
        return false;
    }

    @ConfigItem(
            keyName = "allowTileDeficit",
            name = "Allow Tile Deficit",
            section = settingsSection,
            description = "Allows you to place tiles after you have none left.",
            position = 5
    )
    default boolean allowTileDeficit() {
        return false;
    }

    @ConfigItem(
            keyName = "drawOnMinimap",
            name = "Draw tiles on minimap",
            section = settingsSection,
            description = "Configures whether marked tiles should be drawn on minimap",
            position = 6
    )
    default boolean drawTilesOnMinimap() {
        return false;
    }

    @ConfigItem(
            keyName = "drawTilesOnWorldMap",
            name = "Draw tiles on world map",
            section = settingsSection,
            description = "Configures whether marked tiles should be drawn on world map",
            position = 5
    )
    default boolean drawTilesOnWorldMap() {
        return false;
    }

    @Alpha
    @ConfigItem(
            keyName = "markerColor",
            name = "Tile Color",
            section = settingsSection,
            description = "Configures the color of the tiles",
            position = 6
    )
    default Color markerColor() {
        return Color.YELLOW;
    }

    /***   Custom Game Mode section   ***/
    @ConfigItem(
            keyName = "enableCustomGameMode",
            name = "Enable Custom Game Mode",
            description = "Settings below will override Game Mode defaults",
            section = customGameModeSection,
            position = 1
    )
    default boolean enableCustomGameMode() {
        return false;
    }

    @Range(
            min = Integer.MIN_VALUE
    )
    @ConfigItem(
            keyName = "tilesOffset",
            name = "Bonus tiles",
            description = "Add more tiles to your limit, set to 0 for off",
            section = customGameModeSection,
            position = 2
    )
    default int tilesOffset() {
        return 9;
    }

    @ConfigItem(
            keyName = "includeTotalLevels",
            name = "Include total level",
            description = "Includes total level in usable tiles",
            section = customGameModeSection,
            position = 3
    )
    default boolean includeTotalLevel() {
        return false;
    }

    @ConfigItem(
            keyName = "excludeExp",
            name = "Exclude Experience",
            description = "Includes experience / 1000 in usable tiles",
            section = customGameModeSection,
            position = 4
    )
    default boolean excludeExp() {
        return false;
    }

    @Range(
            min = 500
    )
    @ConfigItem(
            keyName = "expPerTile",
            name = "Exp per Tile",
            description = "Determines how much exp you require per tile",
            section = customGameModeSection,
            position = 5
    )
    default int expPerTile() {
        return 1000;
    }
}
