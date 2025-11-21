package net.runelite.client.plugins.pluginhub.com.portaguy.infoboxes;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderInfobox;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderPlugin;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.CorruptionTracker;
import net.runelite.api.SpriteID;

import javax.inject.Inject;

public class CorruptionInfobox extends SpellReminderInfobox {
  @Inject
  public CorruptionInfobox(SpellReminderPlugin plugin,
                           SpellReminderConfig config,
                           CorruptionTracker tracker) {
    super(plugin, config, tracker);
  }

  @Override
  protected int getSpriteId() {
    return SpriteID.SPELL_GREATER_CORRUPTION;
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.corruptionTimeoutSeconds();
  }
}