package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.coins;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.StorageTabPanel;

/** CoinsStorageTabPanel is responsible for displaying coins storage data to the player. */
public class CoinsStorageTabPanel
    extends StorageTabPanel<CoinsStorageType, CoinsStorage, CoinsStorageManager> {

  public CoinsStorageTabPanel(DudeWheresMyStuffPlugin plugin, CoinsStorageManager storageManager) {
    super(plugin, storageManager);
  }
}
