package net.runelite.client.plugins.pluginhub.xyz.amrik;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Provides;

import java.util.Arrays;

import javax.inject.Inject;
import net.runelite.api.Item;
import net.runelite.api.ItemID;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.pluginhub.xyz.amrik.JekyllHerbReminderConfig.RewardWantedType;

@PluginDescriptor(name = "Jekyll Herb Reminder")
public class JekyllHerbReminderPlugin extends Plugin {

	@Inject
	private Notifier notifier;

	@Inject
	private JekyllHerbReminderConfig config;

	private static final int INVENTORY_ID = 93;

	private static final ImmutableMap<RewardWantedType, Integer> REWARD_TO_HERB = ImmutableMap
			.<RewardWantedType, Integer>builder()
			.put(RewardWantedType.STRENGTH_POTION, ItemID.GUAM_LEAF)
			.put(RewardWantedType.ANTIPOISON, ItemID.MARRENTILL)
			.put(RewardWantedType.ATTACK_POTION, ItemID.TARROMIN)
			.put(RewardWantedType.RESTORE_POTION, ItemID.HARRALANDER)
			.put(RewardWantedType.ENERGY_POTION, ItemID.RANARR_WEED)
			.put(RewardWantedType.DEFENCE_POTION, ItemID.TOADFLAX)
			.put(RewardWantedType.AGILITY_POTION, ItemID.IRIT_LEAF)
			.put(RewardWantedType.SUPER_ATTACK, ItemID.AVANTOE)
			.put(RewardWantedType.SUPER_ENERGY, ItemID.KWUARM)
			.put(RewardWantedType.SUPER_STRENGTH, ItemID.SNAPDRAGON)
			.put(RewardWantedType.SUPER_RESTORE, ItemID.CADANTINE)
			.put(RewardWantedType.SUPER_DEFENCE, ItemID.LANTADYME)
			.put(RewardWantedType.MAGIC_POTION, ItemID.DWARF_WEED)
			.put(RewardWantedType.STAMINA_POTION, ItemID.TORSTOL)
			.build();

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged) {
		if (itemContainerChanged.getContainerId() != INVENTORY_ID || config.rewardWantedType() == RewardWantedType.DEFAULT) {
			return;
		}

		int herbWantedID = REWARD_TO_HERB.get(config.rewardWantedType());
		Item[] inventory = itemContainerChanged.getItemContainer().getItems();
		boolean inventoryContainsHerb = Arrays.stream(inventory).map(Item::getId).anyMatch(id -> id == herbWantedID);

		if (!inventoryContainsHerb) {
			notifier.notify("You no longer have the correct herb for Jekyll & Hyde event in your inventory!");
		}
	}

	@Provides
	JekyllHerbReminderConfig getConfig(ConfigManager configManager) {
		return configManager.getConfig(JekyllHerbReminderConfig.class);
	}
}

