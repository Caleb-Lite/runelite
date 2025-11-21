package net.runelite.client.plugins.pluginhub.com.portaguy.infoboxes;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderInfobox;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderPlugin;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.ChargeTracker;
import net.runelite.api.SpriteID;

import javax.inject.Inject;

public class ChargeInfobox extends SpellReminderInfobox {
  @Inject
  public ChargeInfobox(SpellReminderPlugin plugin,
                       SpellReminderConfig config,
                       ChargeTracker tracker) {
    super(plugin, config, tracker);
  }

  @Override
  protected int getSpriteId() {
    return SpriteID.SPELL_CHARGE;
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.chargeTimeoutSeconds();
  }
}