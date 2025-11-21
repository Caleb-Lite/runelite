package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.playerownedhouse;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.StorageTabPanel;

/** PlayerOwnedHouseStorageTabPanel is responsible for displaying POH storage data to the player. */
public class PlayerOwnedHouseStorageTabPanel
    extends StorageTabPanel<
    PlayerOwnedHouseStorageType, PlayerOwnedHouseStorage, PlayerOwnedHouseStorageManager> {

  public PlayerOwnedHouseStorageTabPanel(
      DudeWheresMyStuffPlugin plugin, PlayerOwnedHouseStorageManager storageManager) {
    super(plugin, storageManager);
  }
}
