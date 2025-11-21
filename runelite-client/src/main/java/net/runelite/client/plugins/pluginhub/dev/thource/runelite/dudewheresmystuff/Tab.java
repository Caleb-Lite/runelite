package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff;

import com.google.common.collect.ImmutableMap;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.carryable.CarryableStorageManager;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.coins.CoinsStorageManager;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.death.DeathStorageManager;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.minigames.MinigamesStorageManager;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.playerownedhouse.PlayerOwnedHouseStorageManager;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.stash.StashStorageManager;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.world.WorldStorageManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.gameval.ItemID;

/** Tab is used to define tabs that the user can click to swap panels. */
@RequiredArgsConstructor
@Getter
public enum Tab {
  OVERVIEW("Overview", ItemID.HW16_CLUE_LIBRARY),
  COINS("Coins", ItemID.COINS, 0xBADCA7),
  CARRYABLE_STORAGE("Carry-able Storage", ItemID.LOOTING_BAG),
  WORLD("World Storage", ItemID.ROGUESDEN_CRATE),
  MINIGAMES("Minigames", ItemID.MAGICTRAINING_PROGHAT_DULL),
  DEATH("Death Storage", ItemID.SKULL),
  POH_STORAGE("POH Storage", ItemID.POH_COS_ROOM_ARMOUR_CASE_MAHOGANY),
  STASH_UNITS("STASH Units", ItemID.POH_WALLCHART_4),
  SEARCH("Search", -1),
  DEBUG("Debug", ItemID.BLUECOG);

  public static final ImmutableMap<Class<? extends StorageManager<?, ?>>, Tab> MANAGER_TAB_MAP =
      ImmutableMap.<Class<? extends StorageManager<?, ?>>, Tab>builder()
          .put(DeathStorageManager.class, DEATH)
          .put(CoinsStorageManager.class, COINS)
          .put(CarryableStorageManager.class, CARRYABLE_STORAGE)
          .put(WorldStorageManager.class, WORLD)
          .put(StashStorageManager.class, STASH_UNITS)
          .put(PlayerOwnedHouseStorageManager.class, POH_STORAGE)
          .put(MinigamesStorageManager.class, MINIGAMES)
          .build();
  public static final List<Tab> TABS =
      Collections.unmodifiableList(
          Arrays.asList(
              OVERVIEW,
              DEATH,
              COINS,
              CARRYABLE_STORAGE,
              STASH_UNITS,
              POH_STORAGE,
              WORLD,
              MINIGAMES,
              SEARCH));

  private final String name;
  private final int itemId;
  private final int itemQuantity;

  Tab(String name, int itemId) {
    this.name = name;
    this.itemId = itemId;
    this.itemQuantity = 1;
  }
}
