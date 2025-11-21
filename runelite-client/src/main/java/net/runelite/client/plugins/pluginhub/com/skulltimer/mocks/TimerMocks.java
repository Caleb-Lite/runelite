package net.runelite.client.plugins.pluginhub.com.skulltimer.mocks;

import net.runelite.client.plugins.pluginhub.com.skulltimer.SkullTimerConfig;
import net.runelite.client.plugins.pluginhub.com.skulltimer.SkullTimerPlugin;
import net.runelite.client.plugins.pluginhub.com.skulltimer.managers.EquipmentManager;
import net.runelite.client.plugins.pluginhub.com.skulltimer.managers.StatusManager;
import net.runelite.client.plugins.pluginhub.com.skulltimer.managers.TimerManager;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import org.mockito.Mock;

public class TimerMocks
{
	@Mock
	protected Client client;
	@Mock
	protected ClientThread clientThread;
	@Mock
	protected SkullTimerConfig config;
	@Mock
	protected InfoBoxManager infoBoxManager;
	@Mock
	protected ItemManager itemManager;
	@Mock
	protected SkullTimerPlugin skullTimerPlugin;
	@Mock
	protected TimerManager timerManager;
	@Mock
	protected StatusManager statusManager;
	@Mock
	protected EquipmentManager equipmentManager;
}
