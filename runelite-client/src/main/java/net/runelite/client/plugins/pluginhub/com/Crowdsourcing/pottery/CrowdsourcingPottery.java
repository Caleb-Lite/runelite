package net.runelite.client.plugins.pluginhub.com.Crowdsourcing.pottery;

import net.runelite.client.plugins.pluginhub.com.Crowdsourcing.CrowdsourcingManager;
import javax.inject.Inject;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.eventbus.Subscribe;

public class CrowdsourcingPottery
{
	private static final String FIRING_SUCCESS_PREFIX = "You remove the ";
	private static final String FIRING_SUCCESS_SUFFIX = " from the oven.";
	private static final String FIRING_FAILURE_PREFIX = "The ";
	private static final String FIRING_FAILURE_SUFFIX = " cracks in the oven.";

	@Inject
	private CrowdsourcingManager manager;

	@Inject
	private Client client;

	@Subscribe
	public void onChatMessage(final ChatMessage event)
	{
		if (!ChatMessageType.SPAM.equals(event.getType()))
		{
			return;
		}

		final String message = event.getMessage();

		if ((message.startsWith(FIRING_SUCCESS_PREFIX) && message.endsWith(FIRING_SUCCESS_SUFFIX)) ||
			(message.startsWith(FIRING_FAILURE_PREFIX) && message.endsWith(FIRING_FAILURE_SUFFIX)))
		{
			final int craftingLevel = client.getBoostedSkillLevel(Skill.CRAFTING);
			PotteryData data = new PotteryData(message, craftingLevel);
			manager.storeEvent(data);
		}
	}
}
