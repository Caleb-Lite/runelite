package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.carryable;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.StorageTabPanel;

/** CarryableStorageTabPanel is responsible for displaying carryable storage data to the player. */
public class CarryableStorageTabPanel
    extends StorageTabPanel<CarryableStorageType, CarryableStorage, CarryableStorageManager> {

  public CarryableStorageTabPanel(
      DudeWheresMyStuffPlugin plugin, CarryableStorageManager storageManager) {
    super(plugin, storageManager);
  }
}
