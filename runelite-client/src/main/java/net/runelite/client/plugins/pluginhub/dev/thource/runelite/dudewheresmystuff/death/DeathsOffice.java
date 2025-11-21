package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.death;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;

/** DeathsOffice shows the user what items they have stored in death's office. */
public class DeathsOffice extends DeathStorage {

  protected DeathsOffice(DudeWheresMyStuffPlugin plugin) {
    super(DeathStorageType.DEATHS_OFFICE, plugin);
  }
}
