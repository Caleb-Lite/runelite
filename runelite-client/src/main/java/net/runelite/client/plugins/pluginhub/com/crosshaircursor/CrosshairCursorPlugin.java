package net.runelite.client.plugins.pluginhub.com.crosshaircursor;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.FocusChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientUI;

import java.awt.*;

@Slf4j
@PluginDescriptor(
		name = "Crosshair Cursor",
		description = "Changes your cursor to a crosshair. Also supports animated cursors (see help page)"
)
public class CrosshairCursorPlugin extends Plugin
{
	@Inject private Client client;
	@Inject private ClientUI clientUI;
	@Inject private CrosshairCursorConfig config;
	@Inject private KeyManager keyManager;
	@Inject private ClientThread clientThread;
	@Inject private CrosshairHotkeyListener hotkeyListener;

	private boolean crosshairEnabled = true;

	@Override
	protected void startUp()
	{
		hotkeyListener.setToggleCallback(this::toggleCrosshair);
		keyManager.registerKeyListener(hotkeyListener);

		if (crosshairEnabled)
		{
			setCursor();
		}
	}

	@Override
	protected void shutDown()
	{
		keyManager.unregisterKeyListener(hotkeyListener);
		resetCursor();
	}

	private void toggleCrosshair()
	{
		crosshairEnabled = !crosshairEnabled;

		if (crosshairEnabled)
		{
			setCursor();
			sendMessage("Crosshair Cursor - Enabled");
		}
		else
		{
			resetCursor();
			sendMessage("Crosshair Cursor - Disabled");
		}
	}

	private void setCursor()
	{
		clientUI.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	private void resetCursor()
	{
		clientUI.resetCursor();
	}

	private void sendMessage(String message)
	{
		if (message == null || message.isEmpty())
		{
			return;
		}

		clientThread.invoke(() ->
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message, null)
		);
	}

	@Provides
	CrosshairCursorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CrosshairCursorConfig.class);
	}

	@Subscribe
	public void onFocusChanged(FocusChanged event)
	{
		if (crosshairEnabled && event.isFocused())
		{
			setCursor();
		}
	}
}
