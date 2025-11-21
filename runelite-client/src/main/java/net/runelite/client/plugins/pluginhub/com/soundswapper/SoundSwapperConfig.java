package net.runelite.client.plugins.pluginhub.com.soundswapper;

import net.runelite.client.config.*;

@ConfigGroup("soundswapper")
public interface SoundSwapperConfig extends Config
{
    /**
     * Sections
     */
    @ConfigSection(
            name = "Sound Effects",
            description = "Configuration settings for Sound Effects",
            position = 1
    )
    String SOUND_EFFECTS_SECTION = "soundEffects";

    @ConfigSection(
            name = "Area Sound Effects",
            description = "Configuration settings for Area Sound Effects",
            position = 2
    )
    String AREA_SOUND_EFFECTS_SECTION = "areaSoundEffects";

    /**
     * Config Items
     */
    @ConfigItem(
            keyName = "soundEffects",
            name = "Swap Sound Effects",
            description = "Swap sound effects with custom sounds",
            position = 1,
            section = SOUND_EFFECTS_SECTION
    )
    default boolean soundEffects() { return false; }

    @ConfigItem(
            keyName = "customSounds",
            name = "Custom Sounds",
            description = "Area Sounds to replace with your own custom .wav files. Separate with comma.<br>" +
                    "Sound List: https://oldschool.runescape.wiki/w/List_of_in-game_sound_IDs",
            position = 2,
            section = SOUND_EFFECTS_SECTION
    )
    default String customSounds()
    {
        return "";
    }

    @ConfigItem(
            keyName = "consumeSoundEffects",
            name = "Consume Sounds Effects",
            description = "Consume any game sound effect that is not custom",
            position = 3,
            section = SOUND_EFFECTS_SECTION
    )
    default boolean consumeSoundEffects() { return false; }

    @ConfigItem(
            keyName = "whitelistSounds",
            name = "Whitelist Sounds",
            description = "Sound ids allowed to bypass the 'Consume Sound Effects' config option<br>" +
                    "Format: 123,456,789",
            position = 4,
            section = SOUND_EFFECTS_SECTION
    )
    default String whitelistSounds()
    {
        return "";
    }

    @ConfigItem(
            keyName = "blacklistedSounds",
            name = "Blacklist Sounds",
            description = "Sound ids consumed regardless of the 'Consume Sound Effects' config option being enabled<br>" +
                    "Format: 123,456,789",
            position = 5,
            section = SOUND_EFFECTS_SECTION
    )
    default String blacklistedSounds()
    {
        return "";
    }

    @ConfigItem(
            keyName = "enableCustomSoundsVolume",
            name = "Enable Custom Volume",
            description = "Enable the ability to set the volume of custom sound effects",
            position = 6,
            section = SOUND_EFFECTS_SECTION
    )
    default boolean enableCustomSoundsVolume() { return false; }

    @Range(max = 100)
    @ConfigItem(
            name = "Custom Sounds Volume",
            keyName = "customSoundsVolume",
            description = "Sets the volume of the sound clips for Sound Effects",
            position = 7,
            section = SOUND_EFFECTS_SECTION
    )
    default int customSoundsVolume()
    {
        return 65;
    }

    @ConfigItem(
            keyName = "areaSoundEffects",
            name = "Swap Area Sounds",
            description = "Swap area sound effects with custom sounds",
            position = 1,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default boolean areaSoundEffects() { return false; }

    @ConfigItem(
            keyName = "customAreaSounds",
            name = "Custom Area Sounds",
            description = "Area Sounds to replace with your own custom .wav files. Separate with comma.<br>" +
                    "Sound List: https://oldschool.runescape.wiki/w/List_of_in-game_sound_IDs",
            position = 2,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default String customAreaSounds()
    {
        return "";
    }

    @ConfigItem(
            keyName = "consumeAreaSounds",
            name = "Consume Area Sounds",
            description = "Consume any area sound effect that is not custom<br>" +
                    "(Must have a custom sound file in the folder to allow this to work)",
            position = 3,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default boolean consumeAreaSounds() { return false; }

    @ConfigItem(
            keyName = "whitelistAreaSounds",
            name = "Whitelist Area Sounds",
            description = "Sound ids allowed to bypass the 'Consume Area Sound Effects' config option<br>" +
                    "Format: 123,456,789",
            position = 4,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default String whitelistAreaSounds()
    {
        return "";
    }

    @ConfigItem(
            keyName = "blacklistedAreaSounds",
            name = "Blacklist Area Sounds",
            description = "Sound ids consumed regardless of the 'Consume Area Sound Effects' config option being enabled<br>" +
                    "Format: 123,456,789",
            position = 5,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default String blacklistedAreaSounds()
    {
        return "";
    }

    @ConfigItem(
            keyName = "enableCustomAreaSoundsVolume",
            name = "Enable Custom Volume",
            description = "Enable the ability to set the volume of custom sound effects",
            position = 6,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default boolean enableCustomAreaSoundsVolume() { return false; }

    @Range(max = 100)
    @ConfigItem(
            name = "Custom Sounds Volume",
            keyName = "customAreaSoundsVolume",
            description = "Sets the volume of the sound clips for Sound Effects",
            position = 7,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default int customAreaSoundsVolume()
    {
        return 65;
    }

    @ConfigItem(
            keyName = "consumeAmbientSounds",
            name = "Consume Ambient Sounds",
            description = "Consume any ambient sound effects while in-game AoE sounds are enabled",
            position = 8,
            section = AREA_SOUND_EFFECTS_SECTION
    )
    default boolean consumeAmbientSounds() { return false; }

    @ConfigItem(
            keyName = "debugSoundEffects",
            name = "Debug Sounds Effects",
            description = "Display the sound effects that play (max 10 lines displayed)<br><br>" +
                    "White: Sound Effect (G)<br>" +
                    "Yellow: Area Sound Effect (A)<br>" +
                    "Gray: Silent Area Sound Effect (SA)",
            position = 98
    )
    default boolean debugSoundEffects() { return false; }
}
