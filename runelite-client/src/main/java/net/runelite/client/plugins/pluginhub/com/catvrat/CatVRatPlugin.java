package net.runelite.client.plugins.pluginhub.com.catvrat;

import net.runelite.api.*;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

@PluginDescriptor(
	name = "Cat V Rat",
	description = "Help remind yourself to feed your cat while it is fighting Hell-Rat Behemoths for spices!",
	tags = {"cat", "rat", "health", "notifications", "spicy", "stew"}
)
public class CatVRatPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CatOverlay catOverlay;
	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private Notifier notifier;

	private KarambwanjiCounter KarambwanjiCounterBox;
	private CatCounter catCounterBox;

	public static String catState = "null";


	@Override
	protected void startUp() throws Exception
	{

		clientThread.invokeLater(() ->
		{
			overlayManager.add(catOverlay);
			final ItemContainer container = client.getItemContainer(InventoryID.INVENTORY);

			if (container != null)
			{
				checkInventory(container);
			}
		});
	}


	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(catOverlay);
		infoBoxManager.removeInfoBox(KarambwanjiCounterBox);
		infoBoxManager.removeInfoBox(catCounterBox);
		KarambwanjiCounterBox = null;
		catCounterBox = null;
	}


	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getItemContainer() != client.getItemContainer(InventoryID.INVENTORY))
		{
			return;
		}

		checkInventory(event.getItemContainer());
	}


	@Subscribe
	public void onAnimationChanged(AnimationChanged event)
	{
		Actor actor = event.getActor();
		if (actor.getName() == null) return;
		if (!(actor instanceof NPC)) return;
		if (!(actor.getName().equalsIgnoreCase("Hell-Rat Behemoth"))) return;

		if (actor.getAnimation() == 2705) // Animation ID of Hell-Rat Behemoth att/def
			checkCatState(Color.orange);
		else if (actor.getAnimation() == 2706)
			checkCatState(Color.yellow);
		else if (actor.getAnimation() == 2707) // Animation ID of Hell-Rat Behemoth dying
		{
			sendChatMessage(Color.green, "Hell-Rat Behemoth has been defeated!");
			catState = "null";
		}
	}


	private void checkCatState(Color warning_color)
	{
		NPC follower = client.getFollower();
		int max_follower_health = follower.getHealthScale();
		int curr_follower_health = follower.getHealthRatio();

		// if cat has -1 max health, it hasn't been hit yet
		if (max_follower_health == -1) return;

		// check if the cat is below half health
		double warning_threshold = 0.6;
		double dangerous_threshold = 0.3;

		double curr_health_ratio = (double)curr_follower_health/(double)max_follower_health;


		if (curr_health_ratio <= dangerous_threshold) {
			if (!(catState.equalsIgnoreCase("dangerous"))) {
				sendChatMessage(Color.red, "Cat is at dangerous health!");
				client.setHintArrow(follower);
				notifier.notify("DANGER: HEAL YOUR CAT RIGHT NOW!");
				catState = "dangerous";
			}
		}
		else if (curr_health_ratio <= warning_threshold) {
			if (!(catState.equalsIgnoreCase("warning"))) {
				sendChatMessage(warning_color, "Cat is low health!");
				client.setHintArrow(follower);
				notifier.notify("WARNING: HEAL YOUR CAT RIGHT NOW!");
				catState = "warning";
			}
		}
		else {
			if (!(catState.equalsIgnoreCase("good"))) {
				sendChatMessage(Color.CYAN, "Cat is at good health!");
				client.clearHintArrow();
				catState = "good";
			}
		}

	}


	private void checkInventory(final ItemContainer inventory)
	{
		// Check for karambwanji in the inventory
		Item[] items = inventory.getItems();
		int karambwanji_idx = -1;
		if (inventory.contains(ItemID.RAW_KARAMBWANJI)) {
			int idx = 0;
			for (Item item : inventory.getItems()) {
				if (item.getId() == ItemID.RAW_KARAMBWANJI) {
					karambwanji_idx = idx;
					break;
				}
				idx += 1;
			}
		}
		if (karambwanji_idx != -1)
		{
			System.out.println("Found " + items[karambwanji_idx].getQuantity() + " raw Karambwanji at inventory idx: " + karambwanji_idx);
		}
		else
		{
			System.out.println("No raw Karambwanji was found in inventory");
			removeInfobox();
			return;
		}

		// Check for follower
		NPC follower = client.getFollower();
		// if follower exists
		if (follower != null) {
			// Figure out what kind of cat the user has
			int cat_item_id = -1;

			if (follower.getName().equalsIgnoreCase("WILY HELLCAT"))
				cat_item_id = ItemID.WILY_HELLCAT;
			else if (follower.getName().equalsIgnoreCase("LAZY HELLCAT"))
				cat_item_id = ItemID.LAZY_HELL_CAT;
			else if (follower.getName().equalsIgnoreCase("OVERGROWN HELLCAT"))
				cat_item_id = ItemID.OVERGROWN_HELLCAT;
			else if (follower.getName().equalsIgnoreCase("HELLCAT"))
				cat_item_id = ItemID.HELL_CAT;
			else if (follower.getName().equalsIgnoreCase("WILY CAT"))
				cat_item_id = ItemID.WILY_CAT;
			else if (follower.getName().equalsIgnoreCase("LAZY CAT"))
				cat_item_id = ItemID.LAZY_CAT;
			else
				cat_item_id = ItemID.PET_CAT;

			// clean out old cat infobox
			infoBoxManager.removeInfoBox(catCounterBox);
			catCounterBox = null;

			// place new cat infobox
			final BufferedImage image = itemManager.getImage(cat_item_id, 1, false);
			catCounterBox = new CatCounter(this, cat_item_id, 1, follower.getName(), image);
			infoBoxManager.addInfoBox(catCounterBox);

		}
		else {
			// remove cat infobox if no follower exists
			infoBoxManager.removeInfoBox(catCounterBox);
			catCounterBox = null;
		}

		final Item karambwanji = items[karambwanji_idx];
		final ItemComposition comp = itemManager.getItemComposition(karambwanji.getId());

		updateInfobox(karambwanji, comp);
	}


	private void updateInfobox(final Item item, final ItemComposition comp)
	{
		if (KarambwanjiCounterBox != null && KarambwanjiCounterBox.getItemID() == item.getId())
		{
			KarambwanjiCounterBox.setCount(item.getQuantity());
			return;
		}

		removeInfobox();
		final BufferedImage image = itemManager.getImage(item.getId(), 1, false);
		KarambwanjiCounterBox = new KarambwanjiCounter(this, item.getId(), item.getQuantity(), comp.getName(), image);
		infoBoxManager.addInfoBox(KarambwanjiCounterBox);
	}


	private void removeInfobox()
	{
		infoBoxManager.removeInfoBox(KarambwanjiCounterBox);
		KarambwanjiCounterBox = null;
	}


	private void sendChatMessage(Color color, String chatMessage)
	{
		final String message = new ChatMessageBuilder()
				.append(color, chatMessage).build();

		chatMessageManager.queue(
				QueuedMessage.builder()
						.type(ChatMessageType.CONSOLE)
						.runeLiteFormattedMessage(message)
						.build());
	}


}

