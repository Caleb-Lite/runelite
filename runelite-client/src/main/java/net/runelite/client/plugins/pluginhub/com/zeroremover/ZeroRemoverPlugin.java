package net.runelite.client.plugins.pluginhub.com.zeroremover;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.MessageNode;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
		name = "Zero Remover",
		description = "Removes the redundant 0 from precise timers.",
		tags = {"zero","timer","remove","0"}
)

@Slf4j
public class ZeroRemoverPlugin extends Plugin
{
	@Inject
	private Client client;

	private boolean ShouldReplace = false;
	private boolean ReplaceNow = false;

	@Override
	protected void startUp()
	{
		ShouldReplace = true;
	}

	@Override
	protected void shutDown()
	{
		ShouldReplace = false;
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		ReplaceNow = true;
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (ShouldReplace && ReplaceNow)
		{
			if (client.isResized())
			{
				ReplaceWText(new Widget[]{client.getWidget(10551316)}); //Classic
				ReplaceWText(new Widget[]{client.getWidget(10747924)}); //Modern
			}
			else
			{
				ReplaceWText(new Widget[]{client.getWidget(35913756)});
			}
			ReplaceNow = false;
		}
	}

	private void ReplaceWText(Widget[] parent)
	{
		if (parent == null)
		{
			return;
		}
		for (Widget w : parent)
		{
			if (w != null)
			{
				final String message = w.getText();
				if (message != null) {
					String replacement = message.replaceAll("([0-9]*?:*)([0-9]{1,2})\\.([0-9])0", "$1$2.$3");
					w.setText(replacement);
				}
				ReplaceWText(w.getNestedChildren());
				ReplaceWText(w.getStaticChildren());
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		if (!ShouldReplace)
		{
			return;
		}
		ChatMessageType chatMessageType = chatMessage.getType();
		if (chatMessageType == ChatMessageType.GAMEMESSAGE || chatMessageType == ChatMessageType.FRIENDSCHATNOTIFICATION)
		{
			MessageNode messageNode = chatMessage.getMessageNode();
			final String message = messageNode.getValue();
			String replacement = message.replaceAll("([0-9]*?:*)([0-9]{1,2})\\.([0-9])0","$1$2.$3");
			messageNode.setValue(replacement);
		}
	}
}
