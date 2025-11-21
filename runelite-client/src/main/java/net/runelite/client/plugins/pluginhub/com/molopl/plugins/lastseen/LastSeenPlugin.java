package net.runelite.client.plugins.pluginhub.com.molopl.plugins.lastseen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Friend;
import net.runelite.api.GameState;
import net.runelite.api.Nameable;
import net.runelite.api.NameableContainer;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.NameableNameChanged;
import net.runelite.api.events.RemovedFriend;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.Text;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@PluginDescriptor(
	name = "Last Seen Online",
	description = "Track when you've last seen your friends play",
	tags = {"last", "seen", "online", "friends", "activity", "watch"}
)
public class LastSeenPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private LastSeenOverlay overlay;
	@Inject
	private LastSeenDao dao;

	private final Set<String> currentlyOnline = new HashSet<>();
	// in-memory buffer of 'last seen online', persisted periodically
	private final Map<String, Long> lastSeenBuffer = new HashMap<>();

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		dao.clearCache();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		persistLastSeen();
		dao.clearCache();
	}

	@Subscribe
	public void onNameableNameChanged(NameableNameChanged event)
	{
		final Nameable nameable = event.getNameable();
		if (nameable instanceof Friend && StringUtils.isNotBlank(nameable.getPrevName()))
		{
			dao.migrateLastSeen(
				Text.toJagexName(nameable.getPrevName()),
				Text.toJagexName(nameable.getName())
			);
		}
	}

	@Subscribe
	public void onRemovedFriend(RemovedFriend event)
	{
		dao.deleteLastSeen(Text.toJagexName(event.getNameable().getName()));
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		final int groupId = WidgetInfo.TO_GROUP(event.getActionParam1());

		overlay.setTooltip(null);
		if (groupId == WidgetInfo.FRIENDS_LIST.getGroupId() && event.getOption().equals("Message"))
		{
			final String displayName = Text.toJagexName(Text.removeTags(event.getTarget()));
			if (StringUtils.isNotBlank(displayName) && !currentlyOnline.contains(displayName))
			{
				final Long lastSeen = lastSeenBuffer.getOrDefault(displayName, dao.getLastSeen(displayName));
				overlay.setTooltip("Last online: " + LastSeenFormatter.format(lastSeen));
			}
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		// persist in-memory state every minute
		if (client.getTickCount() % 100 == 0)
		{
			persistLastSeen();
		}

		final NameableContainer<Friend> friendContainer = client.getFriendContainer();
		if (friendContainer == null)
		{
			return;
		}

		currentlyOnline.clear();
		Arrays.stream(friendContainer.getMembers())
			.filter(friend -> friend.getWorld() > 0)
			.forEach(friend -> currentlyOnline.add(Text.toJagexName(friend.getName())));

		final long currentTimeMillis = System.currentTimeMillis();
		currentlyOnline.forEach(displayName -> lastSeenBuffer.put(displayName, currentTimeMillis));
	}

	private void persistLastSeen()
	{
		lastSeenBuffer.forEach(dao::setLastSeen);
		lastSeenBuffer.clear();
	}
}

