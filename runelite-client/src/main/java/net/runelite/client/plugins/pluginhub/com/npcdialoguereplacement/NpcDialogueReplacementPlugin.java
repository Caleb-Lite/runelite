package net.runelite.client.plugins.pluginhub.com.npcdialoguereplacement;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@PluginDescriptor(
	name = "NPC dialog replacer",
	tags = {"uri", "clues", "npc", "dialogue", "changer"}
)
public class NpcDialogueReplacementPlugin extends Plugin
{
	@Inject
	private NpcDialogueReplacementConfig config;
	@Inject
	private Client client;

	private static String activeDialog = null;
	private static boolean dialogueOpened = false;

	@Override
	protected void startUp()
	{
		CustomDialogStorage.load(this.config.uriDialogFilePath());
	}

	@Subscribe
	public void onBeforeRender(final BeforeRender event)
	{
		if (dialogueOpened) {
			final Widget dialog = this.client.getWidget(ComponentID.DIALOG_NPC_TEXT);

			if (dialog != null && !dialog.getText().equals(activeDialog)) {
				activeDialog = dialog.getText();
				final Widget name = this.client.getWidget(ComponentID.DIALOG_NPC_NAME);

				if (name != null) {
					// Uri - Clue Steps
					if (name.getText().equals("Uri") &&  !activeDialog.equalsIgnoreCase("I do not believe we have any business, Comrade.") && CustomDialogStorage.hasText()) {
						final String customText = CustomDialogStorage.getRandom();
						dialog.setLineHeight(getLineHeight(customText));
						dialog.setText(customText);
						activeDialog = null;
					}
				}
			}

			dialogueOpened = false;
		}
	}

	@Subscribe
	public void onConfigChanged(final ConfigChanged event)
	{
		if (event.getGroup().equals("npcdialoguereplacement")) {
			if (event.getKey().equals("uriDialogFilePath")) {
				CustomDialogStorage.load(event.getNewValue());
				dialogueOpened = false;
				activeDialog = null;
			}
		}
	}

	@Subscribe
	public void onWidgetLoaded(final WidgetLoaded event)
	{
		if (event.getGroupId() == InterfaceID.DIALOG_NPC) {
			dialogueOpened = true;
		}
	}

	private int getLineHeight(final String text)
	{
		final int count = StringUtils.countMatches(text, "<br>");

		if (count == 1) {
			return 28;
		} else if (count == 2) {
			return 20;
		} else if (count >= 4) {
			return 14;
		}

		return 16;
	}

	@Provides
	NpcDialogueReplacementConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NpcDialogueReplacementConfig.class);
	}
}

