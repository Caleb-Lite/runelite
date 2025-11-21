package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.world;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.StorageTabPanel;

/** WorldStorageTabPanel is responsible for displaying world storage data to the player. */
public class WorldStorageTabPanel
    extends StorageTabPanel<WorldStorageType, WorldStorage, WorldStorageManager> {

  public WorldStorageTabPanel(DudeWheresMyStuffPlugin plugin, WorldStorageManager storageManager) {
    super(plugin, storageManager);
  }
}
