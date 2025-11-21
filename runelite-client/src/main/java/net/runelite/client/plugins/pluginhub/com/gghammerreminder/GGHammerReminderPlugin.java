package net.runelite.client.plugins.pluginhub.com.gghammerreminder;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.MenuOpened;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@PluginDescriptor(
		name = "GG Hammer Reminder",
		description = "Reminds you to bring a hammer to the Grotesque Guardians",
		tags = {"gg", "rock hammer", "reminder", "Grotesque Guardians", "Gargoyle"}
)
public class GGHammerReminderPlugin extends Plugin {
	private static final int CLOISTER_BELL_ID = 31669;

	@Inject
	private Client client;

	@Inject
	private GGHammerReminderConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private GGHammerReminderOverlay ggHammerReminderOverlay;

	@Override
	protected void startUp() {
		log.info("GG Hammer Reminder started!");
		overlayManager.add(ggHammerReminderOverlay);
	}

	@Override
	protected void shutDown() {
		log.info("GG Hammer Reminder stopped!");
		overlayManager.remove(ggHammerReminderOverlay);
	}

	@Subscribe(priority = -1)
	public void onMenuOpened(MenuOpened event) {
		if (!config.removeRightClickMenu()) {
			return;
		}

		MenuEntry[] menuEntries = client.getMenuEntries();
		List<MenuEntry> alteredMenuEntries = new ArrayList<>();
		boolean showHammerReminder = false;
		boolean hasHammer = GGHammerReminderUtils.hasHammerInInventory(client);

		for (MenuEntry menuEntry : menuEntries) {
			MenuAction menuAction = menuEntry.getType();
			int identifier = menuEntry.getIdentifier();

			if (identifier == CLOISTER_BELL_ID && (isRing(menuAction) || isQuickStart(menuAction))) {
				if (!hasHammer) {
					showHammerReminder = true;
					continue;
				}
			}
			alteredMenuEntries.add(menuEntry);
		}

		client.setMenuEntries(alteredMenuEntries.toArray(new MenuEntry[0]));

		if (showHammerReminder) {
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Reminder: Bring a rock hammer to fight the Grotesque Guardians!", null);
		}
	}

	@Subscribe(priority = -2)
	public void onMenuOptionClicked(MenuOptionClicked event) {
		String option = event.getMenuOption();
		int identifier = event.getId();

		boolean shouldRemoveMenu = config.removeRightClickMenu();
		boolean hasHammer = GGHammerReminderUtils.hasHammerInInventory(client);

		if (identifier == CLOISTER_BELL_ID && (option.equals("Quick-start") || option.equals("Ring")) && !hasHammer && shouldRemoveMenu) {
			event.consume();
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You can't start this without a rock hammer, rock thrownhammer, or granite hammer!", null);
		}
	}

	private boolean isRing(MenuAction menuAction) {
		return MenuAction.GAME_OBJECT_FIRST_OPTION.equals(menuAction);
	}

	private boolean isQuickStart(MenuAction menuAction) {
		return MenuAction.GAME_OBJECT_SECOND_OPTION.equals(menuAction);
	}

	@Provides
	GGHammerReminderConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(GGHammerReminderConfig.class);
	}
}