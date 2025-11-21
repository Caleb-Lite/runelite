package net.runelite.client.plugins.pluginhub.com.pwootage.runelite;

import net.runelite.api.SoundEffectVolume;
import net.runelite.client.config.*;

@ConfigGroup("multimetronome")
public interface MultiMetronomeConfig extends Config {
  int VOLUME_MAX = SoundEffectVolume.HIGH;

  @ConfigSection(
    name = "Metronome 1",
    description = "",
    position = 100
  )
  String SECTION_BEEPER1 = "beeper1";

  @ConfigSection(
    name = "Metronome 2",
    description = "",
    position = 200
  )
  String SECTION_BEEPER2 = "beeper2";

  @ConfigSection(
    name = "Metronome 3",
    description = "",
    position = 300
  )
  String SECTION_BEEPER3 = "beeper3";

  // BEEPER 1

  @Range(max = VOLUME_MAX)
  @ConfigItem(
    keyName = "beepVolume1",
    name = "Tick volume",
    description = "Configures the volume of the tick sound. A value of 0 will disable tick sounds.",
    section = SECTION_BEEPER1
  )
  default int beep1_beepVolume() {
    return SoundEffectVolume.MEDIUM_HIGH;
  }

  @Range(max = VOLUME_MAX)
  @ConfigItem(
    keyName = "boopVolume1",
    name = "Tock volume",
    description = "Configures the volume of the tock sound. A value of 0 will disable tock sounds.",
    section = SECTION_BEEPER1
  )
  default int beep1_boopVolume() {
    return SoundEffectVolume.MUTED;
  }

  @ConfigItem(
    keyName = "tickCountBeeper1",
    name = "Tick count",
    description = "Configures the tick on which a sound will be played.",
    section = SECTION_BEEPER1
  )
  @Units(Units.TICKS)
  default int beep1_tickCount() {
    return 1;
  }

  @Range(max = 600)
  @ConfigItem(
    keyName = "offset1",
    name = "Tick offset (ms)",
    description = "Configures the offset after the tick which the audio will play (in millseconds)",
    section = SECTION_BEEPER1
  )
  @Units(Units.MILLISECONDS)
  default int beep1_offset() {
    return 0;
  }

  // BEEPER 2

  @Range(max = VOLUME_MAX)
  @ConfigItem(
    keyName = "beepVolume2",
    name = "Tick volume",
    description = "Configures the volume of the tick sound. A value of 0 will disable tick sounds.",
    section = SECTION_BEEPER2
  )
  default int beep2_beepVolume() {
    return SoundEffectVolume.MUTED;
  }

  @Range(max = VOLUME_MAX)
  @ConfigItem(
    keyName = "boopVolume2",
    name = "Tock volume",
    description = "Configures the volume of the tock sound. A value of 0 will disable tock sounds.",
    section = SECTION_BEEPER2
  )
  default int beep2_boopVolume() {
    return SoundEffectVolume.MEDIUM_HIGH;
  }

  @ConfigItem(
    keyName = "tickCountBeeper2",
    name = "Tick count",
    description = "Configures the tick on which a sound will be played.",
    section = SECTION_BEEPER2
  )
  @Units(Units.TICKS)
  default int beep2_tickCount() {
    return 1;
  }

  @Range(max = 600)
  @ConfigItem(
    keyName = "offset2",
    name = "Tick offset (ms)",
    description = "Configures the offset after the tick which the audio will play (in millseconds)",
    section = SECTION_BEEPER2
  )
  @Units(Units.MILLISECONDS)
  default int beep2_offset() {
    return 300;
  }

  // BEEPER 3

  @Range(max = VOLUME_MAX)
  @ConfigItem(
    keyName = "beepVolume3",
    name = "Tick volume",
    description = "Configures the volume of the tick sound. A value of 0 will disable tick sounds.",
    section = SECTION_BEEPER3
  )
  default int beep3_beepVolume() {
    return SoundEffectVolume.MUTED;
  }

  @Range(max = VOLUME_MAX)
  @ConfigItem(
    keyName = "boopVolume3",
    name = "Tock volume",
    description = "Configures the volume of the tock sound. A value of 0 will disable tock sounds.",
    section = SECTION_BEEPER3
  )
  default int beep3_boopVolume() {
    return SoundEffectVolume.MUTED;
  }

  @ConfigItem(
    keyName = "tickCountBeeper3",
    name = "Tick count",
    description = "Configures the tick on which a sound will be played.",
    section = SECTION_BEEPER3
  )
  @Units(Units.TICKS)
  default int beep3_tickCount() {
    return 1;
  }

  @Range(max = 600)
  @ConfigItem(
    keyName = "offset3",
    name = "Tick offset (ms)",
    description = "Configures the offset after the tick which the audio will play (in millseconds)",
    section = SECTION_BEEPER3
  )
  @Units(Units.MILLISECONDS)
  default int beep3_offset() {
    return 500;
  }
}
