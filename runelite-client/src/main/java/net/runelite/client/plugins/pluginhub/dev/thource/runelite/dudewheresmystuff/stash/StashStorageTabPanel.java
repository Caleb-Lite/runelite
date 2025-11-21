package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.stash;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.StorageTabPanel;

/** StashStorageTabPanel is responsible for displaying STASH unit data to the player. */
public class StashStorageTabPanel
    extends StorageTabPanel<StashStorageType, StashStorage, StashStorageManager> {

  public StashStorageTabPanel(DudeWheresMyStuffPlugin plugin, StashStorageManager storageManager) {
    super(plugin, storageManager);
  }
}
