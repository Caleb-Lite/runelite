package net.runelite.client.plugins.pluginhub.com.portaguy.infoboxes;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderInfobox;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderPlugin;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.MarkOfDarknessTracker;
import net.runelite.api.SpriteID;

import javax.inject.Inject;

public class MarkOfDarknessInfobox extends SpellReminderInfobox {
  @Inject
  public MarkOfDarknessInfobox(SpellReminderPlugin plugin,
                               SpellReminderConfig config,
                               MarkOfDarknessTracker tracker) {
    super(plugin, config, tracker);
  }

  @Override
  protected int getSpriteId() {
    return SpriteID.SPELL_MARK_OF_DARKNESS;
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.markOfDarknessTimeoutSeconds();
  }
}