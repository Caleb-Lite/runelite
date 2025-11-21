package net.runelite.client.plugins.pluginhub.com.portaguy.infoboxes;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderInfobox;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderPlugin;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.DeathChargeTracker;
import net.runelite.api.SpriteID;

import javax.inject.Inject;

public class DeathChargeInfobox extends SpellReminderInfobox {
  @Inject
  public DeathChargeInfobox(SpellReminderPlugin plugin,
                            SpellReminderConfig config,
                            DeathChargeTracker tracker) {
    super(plugin, config, tracker);
  }

  @Override
  protected int getSpriteId() {
    return SpriteID.SPELL_DEATH_CHARGE;
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.deathChargeTimeoutSeconds();
  }
}