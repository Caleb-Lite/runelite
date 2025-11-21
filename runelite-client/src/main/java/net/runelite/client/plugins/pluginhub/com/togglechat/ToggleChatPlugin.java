package net.runelite.client.plugins.pluginhub.com.togglechat;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ScriptPreFired;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.Keybind;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.inject.Inject;
import java.awt.event.KeyEvent;

@Slf4j
@PluginDescriptor(
	name = "Toggle Chat",
	description = "Uses a hotkey to open/close chat",
	tags = {"hotkey", "toggle", "chat"}
)
public class ToggleChatPlugin extends Plugin implements KeyListener
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private KeyManager keyManager;

	@Inject
	private ToggleChatConfig config;

	@Provides
	ToggleChatConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ToggleChatConfig.class);
	}

	private boolean consumeKeys = false;

	@Override
	protected void startUp()
	{
		keyManager.registerKeyListener(this);
	}

	@Override
	protected void shutDown()
	{
		keyManager.unregisterKeyListener(this);
	}

	@Subscribe
	public void onScriptPreFired(ScriptPreFired event)
	{
		if (event.getScriptId() == 179)
		{
			// If the user does not want to disable flashing.
			if (!config.removeFlashingTabs())
			{
				return;
			}

			// Allows notifications to appear in chat if the chat box is open.
			if (config.notifyWithOpenChat() && !isChatClosed())
			{
				return;
			}

			// Disables the flashing of specified chats.
			if (config.gameChat())
			{
				client.setVarcIntValue(44, 0);
			}

			if (config.publicChat())
			{
				client.setVarcIntValue(45, 0);
			}

			if (config.privateChat())
			{
				client.setVarcIntValue(46, 0);
			}

			if (config.clanChat())
			{
				client.setVarcIntValue(47, 0);
			}

			if (config.tradeChat())
			{
				client.setVarcIntValue(48, 0);
			}

			if (config.channelChat())
			{
				client.setVarcIntValue(438, 0);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if (consumeKeys)
		{
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (config.hotKey().matches(e))
		{
			if (Keybind.getModifierForKeyCode(e.getKeyCode()) == null && e.getKeyCode() != KeyEvent.VK_ESCAPE)
			{
				consumeKeys = true;
				e.consume();
			}

			clientThread.invokeLater(() -> client.runScript(175, 1, isChatClosed() ? config.defaultTab().getTab() : getChatboxId()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (config.hotKey().matches(e))
		{
			consumeKeys = false;
		}
	}

	private boolean isChatClosed()
	{
		return client.getVarcIntValue(41) == 1337;
	}

	private int getChatboxId()
	{
		return client.getVarcIntValue(41);
	}
}



