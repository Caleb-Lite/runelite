package net.runelite.client.plugins.pluginhub.com.portaguy.overlays;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderOverlay;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderStyle;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.VileVigourTracker;
import net.runelite.api.Client;

import javax.inject.Inject;
import java.awt.*;

public class VileVigourOverlay extends SpellReminderOverlay {
  @Inject
  public VileVigourOverlay(SpellReminderConfig config, Client client, VileVigourTracker tracker) {
    super(config, client, tracker);
  }

  @Override
  protected String getLongText() {
    return "You need to cast Vile Vigour!";
  }

  @Override
  protected String getShortText() {
    return "Vile";
  }

  @Override
  protected String getCustomText() {
    return config.vileVigourCustomText();
  }

  @Override
  protected SpellReminderStyle getReminderStyle() {
    return config.vileVigourReminderStyle();
  }

  @Override
  protected boolean shouldFlash() {
    return config.vileVigourShouldFlash();
  }

  @Override
  protected Color getColor() {
    return config.vileVigourColor();
  }

  @Override
  protected Color getFlashColor() {
    return config.vileVigourFlashColor();
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.vileVigourTimeoutSeconds();
  }
}
