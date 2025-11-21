package net.runelite.client.plugins.pluginhub.com.pwootage.runelite;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.time.Instant;

@Slf4j
@PluginDescriptor(
  name = "Multi Metronome",
  tags = {"tick"}
)
public class MultiMetronomePlugin extends Plugin {

  @Inject
  private Client client;

  @Inject
  private MultiMetronomeConfig config;

  private Beeper beeper1 = new Beeper(() -> config.beep1_offset(), () -> config.beep1_tickCount());
  private Beeper beeper2 = new Beeper(() -> config.beep2_offset(), () -> config.beep2_tickCount());
  private Beeper beeper3 = new Beeper(() -> config.beep3_offset(), () -> config.beep3_tickCount());

  private Instant lastTick = null;

  @Override
  protected void startUp() throws Exception {
  }

  @Override
  protected void shutDown() throws Exception {
  }

  @Provides
  MultiMetronomeConfig provideConfig(ConfigManager configManager) {
    return configManager.getConfig(MultiMetronomeConfig.class);
  }

  @Subscribe
  public void onGameTick(GameTick tick) {
    lastTick = Instant.now();
  }

  @Subscribe
  public void onBeforeRender(BeforeRender tick) {
    if (lastTick == null) {
      return;
    }

    playBeepBoop(beeper1.shouldBeep(lastTick), config.beep1_beepVolume(), config.beep1_boopVolume());
    playBeepBoop(beeper2.shouldBeep(lastTick), config.beep2_beepVolume(), config.beep2_boopVolume());
    playBeepBoop(beeper3.shouldBeep(lastTick), config.beep3_beepVolume(), config.beep3_boopVolume());
  }


  private void playBeepBoop(Beeper.BeepBoop beepBoop, int beepVolume, int boopVolume) {
    if (beepBoop == Beeper.BeepBoop.NONE) {
      return;
    }
    if (beepVolume <= 0 && boopVolume <= 0) {
      return;
    }

    if (beepBoop == Beeper.BeepBoop.BEEP && beepVolume <= 0) {
      beepBoop = Beeper.BeepBoop.BOOP;
    } else if (beepBoop == Beeper.BeepBoop.BOOP && boopVolume <= 0) {
      beepBoop = Beeper.BeepBoop.BEEP;
    }

    // As playSoundEffect only uses the volume argument when the in-game volume isn't muted, sound effect volume
    // needs to be set to the value desired for beeps or boops then reset to the previous value.
    Preferences preferences = client.getPreferences();
    int previousVolume = preferences.getSoundEffectVolume();

    if (beepBoop == Beeper.BeepBoop.BEEP) {
      preferences.setSoundEffectVolume(beepVolume);
      client.playSoundEffect(SoundEffectID.GE_INCREMENT_PLOP, beepVolume);
    } else {
      preferences.setSoundEffectVolume(boopVolume);
      client.playSoundEffect(SoundEffectID.GE_DECREMENT_PLOP, boopVolume);
    }

    preferences.setSoundEffectVolume(previousVolume);
  }
}

