package net.runelite.client.plugins.pluginhub.com.portaguy.overlays;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderOverlay;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderStyle;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.DeathChargeTracker;
import net.runelite.api.Client;

import javax.inject.Inject;
import java.awt.*;

public class DeathChargeOverlay extends SpellReminderOverlay {
  @Inject
  public DeathChargeOverlay(SpellReminderConfig config, Client client, DeathChargeTracker tracker) {
    super(config, client, tracker);
  }

  @Override
  protected String getLongText() {
    return "You need to cast Death Charge!";
  }

  @Override
  protected String getShortText() {
    return "Death Charge";
  }

  @Override
  protected String getCustomText() {
    return config.deathChargeCustomText();
  }

  @Override
  protected SpellReminderStyle getReminderStyle() {
    return config.deathChargeReminderStyle();
  }

  @Override
  protected boolean shouldFlash() {
    return config.deathChargeShouldFlash();
  }

  @Override
  protected Color getColor() {
    return config.deathChargeColor();
  }

  @Override
  protected Color getFlashColor() {
    return config.deathChargeFlashColor();
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.deathChargeTimeoutSeconds();
  }
}
