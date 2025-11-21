package net.runelite.client.plugins.pluginhub.com.portaguy.overlays;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderOverlay;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderStyle;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.ThrallTracker;
import net.runelite.api.Client;

import javax.inject.Inject;
import java.awt.*;

public class ThrallOverlay extends SpellReminderOverlay {
  @Inject
  public ThrallOverlay(SpellReminderConfig config, Client client, ThrallTracker tracker) {
    super(config, client, tracker);
  }

  @Override
  protected String getLongText() {
    return "You need to summon a thrall!";
  }

  @Override
  protected String getShortText() {
    return "Thrall";
  }

  @Override
  protected String getCustomText() {
    return config.customText();
  }

  @Override
  protected SpellReminderStyle getReminderStyle() {
    return config.reminderStyle();
  }

  @Override
  protected boolean shouldFlash() {
    return config.shouldFlash();
  }

  @Override
  protected Color getColor() {
    return config.color();
  }

  @Override
  protected Color getFlashColor() {
    return config.flashColor();
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.thrallTimeoutSeconds();
  }
}