package net.runelite.client.plugins.pluginhub.com.portaguy.overlays;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderOverlay;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderStyle;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.MarkOfDarknessTracker;
import net.runelite.api.Client;

import javax.inject.Inject;
import java.awt.*;

public class MarkOfDarknessOverlay extends SpellReminderOverlay {
  @Inject
  public MarkOfDarknessOverlay(SpellReminderConfig config, Client client, MarkOfDarknessTracker tracker) {
    super(config, client, tracker);
  }

  @Override
  protected String getLongText() {
    return "You need to cast Mark of Darkness!";
  }

  @Override
  protected String getShortText() {
    return "Mark";
  }

  @Override
  protected String getCustomText() {
    return config.markOfDarknessCustomText();
  }

  @Override
  protected SpellReminderStyle getReminderStyle() {
    return config.markOfDarknessReminderStyle();
  }

  @Override
  protected boolean shouldFlash() {
    return config.markOfDarknessShouldFlash();
  }

  @Override
  protected Color getColor() {
    return config.markOfDarknessColor();
  }

  @Override
  protected Color getFlashColor() {
    return config.markOfDarknessFlashColor();
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.markOfDarknessTimeoutSeconds();
  }
}