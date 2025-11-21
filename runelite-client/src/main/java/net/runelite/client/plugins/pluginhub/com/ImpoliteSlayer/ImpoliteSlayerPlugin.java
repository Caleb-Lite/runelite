package net.runelite.client.plugins.pluginhub.com.ImpoliteSlayer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.slayer.SlayerPlugin;
import net.runelite.client.plugins.slayer.SlayerPluginService;

import java.util.Arrays;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Impolite Slayer"
)
@PluginDependency(SlayerPlugin.class)
public class ImpoliteSlayerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ImpoliteSlayerConfig config;

	@Inject
	private SlayerPluginService slayerPluginService;

	private List<String> badTasks;
	private Widget[] dialogueOptions;

	@Override
	protected void startUp()
	{
		initImpoliteSlayer();
	}

	private void initImpoliteSlayer()
	{
		log.debug("Loading Impolite Slayer config...");
		this.badTasks = Arrays.asList(this.config.badTasks().split(","));
	}

	@Subscribe
	public void onGameTick(GameTick tick) {
		String task = slayerPluginService.getTask();
		if(!badTasks.contains(task) && !config.goodMessageEnabled()) {
			return;
		}
		Widget playerDialogueTextWidget = client.getWidget(ComponentID.DIALOG_PLAYER_TEXT);

		if (playerDialogueTextWidget != null) {
			String playerText = playerDialogueTextWidget.getText();
			if(playerText.equals("Okay, great!")) {
				if (badTasks.contains(task)) {
					playerDialogueTextWidget.setText(config.message());
				} else {
					playerDialogueTextWidget.setText(config.goodMessage());
				}
			}
		}

		Widget playerDialogueOptionsWidget = client.getWidget(InterfaceID.DIALOG_OPTION, 1);
		if (playerDialogueOptionsWidget != null && playerDialogueOptionsWidget.getChildren() != dialogueOptions) {
			dialogueOptions = playerDialogueOptionsWidget.getChildren();
            if (dialogueOptions != null) {
				for (Widget dialogueOption : dialogueOptions) {
					if (dialogueOption.getText().equals("Okay, great!")) {
						if (badTasks.contains(task)) {
							dialogueOption.setText(config.message());
						} else {
							dialogueOption.setText(config.goodMessage());
						}
					}
				}
				dialogueOptions = playerDialogueOptionsWidget.getChildren();
			}
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if(event.getGroup().equals("impolite-slayer"))
		{
			initImpoliteSlayer();
		}
	}

	@Provides
	ImpoliteSlayerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ImpoliteSlayerConfig.class);
	}
}
