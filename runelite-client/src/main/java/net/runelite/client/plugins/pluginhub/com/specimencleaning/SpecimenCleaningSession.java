package net.runelite.client.plugins.pluginhub.com.specimencleaning;

import java.awt.Color;
import java.time.Instant;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.ItemID;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;

@Slf4j
@Singleton
public class SpecimenCleaningSession
{
	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private SpecimenCleaningConfig config;

	@Getter
	private int uncleanedFindCount;

	@Getter
	private int artefactCount;

	@Getter
	private int antiqueLampCount;

	@Getter
	@Setter
	private Instant lastActionTime = null;

	void incrementItemObtained(int itemId)
	{
		switch (itemId)
		{
			case ItemID.UNCLEANED_FIND:
				uncleanedFindCount++;
				break;
			case ItemID.ARROWHEADS:
			case ItemID.JEWELLERY:
			case ItemID.POTTERY:
			case ItemID.OLD_CHIPPED_VASE:
				artefactCount++;
				break;
			case ItemID.ANTIQUE_LAMP_11185:
			case ItemID.ANTIQUE_LAMP_11186:
			case ItemID.ANTIQUE_LAMP_11187:
			case ItemID.ANTIQUE_LAMP_11188:
			case ItemID.ANTIQUE_LAMP_11189:
				antiqueLampCount++;

				if (config.showNotifs())
				{
					final String formattedMessage = new ChatMessageBuilder()
						.append(Color.RED, "You found an antique lamp!")
						.build();

					chatMessageManager.queue(QueuedMessage.builder()
						.type(ChatMessageType.GAMEMESSAGE)
						.runeLiteFormattedMessage(formattedMessage)
						.build());
				}

				break;
		}
	}

	void resetTracker()
	{
		setLastActionTime(null);

		uncleanedFindCount = 0;
		artefactCount = 0;

		if (!config.neverResetLamps())
		{
			antiqueLampCount = 0;
		}

		if (config.showNotifs())
		{
			final String formattedMessage = new ChatMessageBuilder()
				.append("Specimen cleaning tracker reset.")
				.build();

			chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.GAMEMESSAGE)
				.runeLiteFormattedMessage(formattedMessage)
				.build());
		}
	}
}

