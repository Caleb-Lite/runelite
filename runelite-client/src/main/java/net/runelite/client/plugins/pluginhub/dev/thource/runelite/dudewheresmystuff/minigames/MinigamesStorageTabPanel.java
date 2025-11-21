package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.minigames;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.StorageTabPanel;
import java.util.Comparator;

/** MinigamesStorageTabPanel is responsible for displaying minigame data to the player. */
public class MinigamesStorageTabPanel
    extends StorageTabPanel<MinigamesStorageType, MinigamesStorage, MinigamesStorageManager> {

  /** A constructor. */
  public MinigamesStorageTabPanel(
      DudeWheresMyStuffPlugin plugin, MinigamesStorageManager storageManager) {
    super(plugin, storageManager);

    remove(sortItemsDropdown);
  }

  @Override
  protected Comparator<MinigamesStorage> getStorageSorter() {
    return Comparator.comparing(s -> s.getType().getName());
  }
}
