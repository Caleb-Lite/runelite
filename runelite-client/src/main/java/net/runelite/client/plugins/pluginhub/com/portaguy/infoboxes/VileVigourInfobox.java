package net.runelite.client.plugins.pluginhub.com.portaguy.infoboxes;

import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderConfig;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderInfobox;
import net.runelite.client.plugins.pluginhub.com.portaguy.SpellReminderPlugin;
import net.runelite.client.plugins.pluginhub.com.portaguy.trackers.VileVigourTracker;
import net.runelite.api.SpriteID;

import javax.inject.Inject;

public class VileVigourInfobox extends SpellReminderInfobox {
  @Inject
  public VileVigourInfobox(SpellReminderPlugin plugin,
                           SpellReminderConfig config,
                           VileVigourTracker tracker) {
    super(plugin, config, tracker);
  }

  @Override
  protected int getSpriteId() {
    return SpriteID.SPELL_VILE_VIGOUR;
  }

  @Override
  protected int getTimeoutSeconds() {
    return config.vileVigourTimeoutSeconds();
  }
}