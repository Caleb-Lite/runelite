package net.runelite.client.plugins.pluginhub.com.portaguy.overlays;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderOverlay;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderStyle;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.ShadowVeilTracker;
import net.runelite.api.Client;

import javax.inject.Inject;
import java.awt.*;

public class ShadowVeilOverlay extends SpellReminderOverlay {
  @Inject
  public ShadowVeilOverlay(SpellReminderConfig config, Client client, ShadowVeilTracker tracker) {
    super(config, client, tracker);
  }

  @Override
  protected String getLongText() {
    return "You need to cast Shadow Veil!";
  }

  @Override
  protected String getShortText() {
    return "Veil";
  }

  @Override
  protected String getCustomText() {
    return config.shadowVeilCustomText();
  }

  @Override
  protected SpellReminderStyle getReminderStyle() {
    return config.shadowVeilReminderStyle();
  }

  @Override
  protected boolean shouldFlash() {
    return config.shadowVeilShouldFlash();
  }

  @Override
  protected Color getColor() {
    return config.shadowVeilColor();
  }

  @Override
  protected Color getFlashColor() {
    return config.shadowVeilFlashColor();
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.shadowVeilTimeoutSeconds();
  }
}