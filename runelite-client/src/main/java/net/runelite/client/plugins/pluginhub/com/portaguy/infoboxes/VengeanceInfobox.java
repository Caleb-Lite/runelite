package net.runelite.client.plugins.pluginhub.com.portaguy.infoboxes;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderInfobox;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderPlugin;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.VengeanceTracker;
import net.runelite.api.SpriteID;

import javax.inject.Inject;

public class VengeanceInfobox extends SpellReminderInfobox {
  @Inject
  public VengeanceInfobox(SpellReminderPlugin plugin,
                          SpellReminderConfig config,
                          VengeanceTracker tracker) {
    super(plugin, config, tracker);
  }

  @Override
  protected int getSpriteId() {
    return SpriteID.SPELL_VENGEANCE;
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.vengeanceTimeoutSeconds();
  }
}