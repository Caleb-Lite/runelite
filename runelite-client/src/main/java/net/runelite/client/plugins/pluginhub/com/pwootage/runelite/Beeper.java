package net.runelite.client.plugins.pluginhub.com.pwootage.runelite;

import java.time.Instant;
import java.util.function.Supplier;

public class Beeper {

  private Supplier<Integer> config_offset;
  private Supplier<Integer> config_tickCount;

  private int tickCount = 0;
  private Instant lastTicked = Instant.MIN;
  private BeepBoop lastBeepBoop = BeepBoop.NONE;

  enum BeepBoop {
    BEEP,
    BOOP,
    NONE
  }

  public Beeper(Supplier<Integer> offset, Supplier<Integer> tickCount) {
    this.config_offset = offset;
    this.config_tickCount = tickCount;
  }

  public BeepBoop shouldBeep(Instant lastTick) {
    // don't tick if we've already ticked this tick
    if (lastTicked.equals(lastTick) || lastTicked.isAfter(lastTick)) {
      return BeepBoop.NONE;
    }
    Instant now = Instant.now();

    boolean triggered = false;
    if (now.isAfter(lastTick.plusMillis(this.config_offset.get()))) {
      triggered = true;
      lastTicked = now;
    }

    if (triggered) {
      tickCount++;
      if (tickCount >= this.config_tickCount.get()) {
        tickCount = 0;
      } else {
        triggered = false;
      }
    }

    if (triggered) {
      if (lastBeepBoop == BeepBoop.BEEP) {
        lastBeepBoop = BeepBoop.BOOP;
      } else {
        lastBeepBoop = BeepBoop.BEEP;
      }
      return lastBeepBoop;
    } else {
      return BeepBoop.NONE;
    }
  }
}
