package net.runelite.client.plugins.pluginhub.com.brastasauce.wildernessslayer;

import com.google.inject.Provides;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Wilderness Slayer"
)
public class WildernessSlayerPlugin extends Plugin
{
	private static final String KRYSTILIA = "Krystilia";

	// NPC messages
	private static final Pattern KRYSTILIA_ASSIGN_MESSAGE = Pattern.compile(".*(?:Your new task is to kill \\d+) (?<name>.+)(?:.)");
	private static final Pattern KRYSTILIA_CURRENT_MESSAGE = Pattern.compile(".*(?:You're still meant to be slaying) (?<name>.+)(?: in the Wilderness.+)");

	@Getter
	private Task task;

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private WorldMapPointManager worldMapPointManager;

	@Inject
	private WildernessSlayerOverlay overlay;

	@Inject
	private WildernessSlayerConfig config;

	private void setTask(String taskName)
	{
		task = Task.getTask(taskName);
		createWorldPoints();
	}

	private void completeTask()
	{
		task = null;
		removeWorldPoints();
	}

	private void createWorldPoints()
	{
		removeWorldPoints();
		if (task != null && config.displayMapIcon())
		{
			for (WorldPoint worldPoint : task.getWorldPoints())
			{
				worldMapPointManager.add(new TaskWorldMapPoint(worldPoint));
			}
		}
	}

	private void removeWorldPoints()
	{
		worldMapPointManager.removeIf(TaskWorldMapPoint.class::isInstance);
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		// Getting tasks
		Widget npcName = client.getWidget(ComponentID.DIALOG_NPC_NAME);
		Widget npcDialog = client.getWidget(ComponentID.DIALOG_NPC_TEXT);

		if (npcDialog != null && npcName.getText().equals(KRYSTILIA))
		{
			String npcText = Text.sanitizeMultilineText(npcDialog.getText());
			final Matcher mAssign = KRYSTILIA_ASSIGN_MESSAGE.matcher(npcText);
			final Matcher mCurrent = KRYSTILIA_CURRENT_MESSAGE.matcher(npcText);

			if (mAssign.find())
			{
				String name = mAssign.group("name");
				setTask(name);
			}

			if (mCurrent.find())
			{
				String name = mCurrent.group("name");
				setTask(name);
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		// Completing tasks
		if (event.getType() != ChatMessageType.GAMEMESSAGE && event.getType() != ChatMessageType.SPAM)
		{
			return;
		}

		String chatMessage = Text.removeTags(event.getMessage());

		if (chatMessage.startsWith("You've completed") && (chatMessage.contains("Slayer master") || chatMessage.contains("Slayer Master")))
		{
			completeTask();
		}
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		task = null;
		removeWorldPoints();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("wildernessslayer"))
		{
			createWorldPoints();
		}
	}

	@Provides
	WildernessSlayerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(WildernessSlayerConfig.class);
	}
}

