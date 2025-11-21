package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.death;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.DudeWheresMyStuffPlugin;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.ItemStorage;
import lombok.Getter;

/**
 * DeathStorage is responsible for tracking death storages that hold the players items (deathpiles,
 * deathbanks).
 */
@Getter
public class DeathStorage extends ItemStorage<DeathStorageType> {

  protected DeathStorage(DeathStorageType type, DudeWheresMyStuffPlugin plugin) {
    super(type, plugin);
  }
}
