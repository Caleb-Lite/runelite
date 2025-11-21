package net.runelite.client.plugins.pluginhub.com.portaguy.infoboxes;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderInfobox;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderPlugin;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.ShadowVeilTracker;
import net.runelite.api.SpriteID;

import javax.inject.Inject;

public class ShadowVeilInfobox extends SpellReminderInfobox {
  @Inject
  public ShadowVeilInfobox(SpellReminderPlugin plugin,
                           SpellReminderConfig config,
                           ShadowVeilTracker tracker) {
    super(plugin, config, tracker);
  }

  @Override
  protected int getSpriteId() {
    return SpriteID.SPELL_SHADOW_VEIL;
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.shadowVeilTimeoutSeconds();
  }
}