package net.runelite.client.plugins.pluginhub.com.telegramnotifier;

import com.google.inject.Provides;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.inject.Inject;
import net.runelite.client.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
@PluginDescriptor(
		name = "Telegram Notifier",
		description = "Sends Telegram messages on valuable drops",
		tags = {"drop", "telegram", "loot", "notify"}
)
public class TelegramNotifierPlugin extends Plugin
{
	@Inject
	private ScheduledExecutorService executor;

	@SuppressWarnings("unused")
	@Provides
	TelegramNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TelegramNotifierConfig.class);
	}

	@Inject
	private TelegramNotifierConfig config;

	@Inject
	private TelegramNotifierService telegramService;

	private Pattern cachedPattern;

	@Override
	protected void startUp() throws Exception
	{
		rebuildPattern();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("telegramnotifier") && event.getKey().equals("keywords"))
		{
			rebuildPattern();
		}
	}

	private void rebuildPattern()
	{
		try {
			cachedPattern = buildPattern();
		} catch (PatternSyntaxException e) {
			cachedPattern = Pattern.compile("a^"); // Match nothing as fallback
		}
	}

	private Pattern buildPattern() throws PatternSyntaxException {
		String keywords = config.keywords().trim();
		if (keywords.isEmpty()) {
			return Pattern.compile("a^"); // Match nothing if no keywords specified
		}
		
		String[] keywordArray = keywords.split(",");
		StringBuilder patternString = new StringBuilder("(?i).*?\\b(");
		
		for (int i = 0; i < keywordArray.length; i++) {
			String keyword = keywordArray[i].trim();
			if (!keyword.isEmpty()) {
				// Escape special regex characters in the keyword
				keyword = keyword.replaceAll("[\\[\\](){}.*+?^$|\\\\]", "\\\\$0");
				patternString.append(keyword);
				if (i < keywordArray.length - 1) {
					patternString.append("|");
				}
			}
		}
		
		patternString.append(")\\b.*?");
		return Pattern.compile(patternString.toString());
	}

	@SuppressWarnings("unused")
	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		String rawMessage = event.getMessage();
		
		final String message = rawMessage.replaceAll("<[^>]*>", "");

		if (cachedPattern.matcher(message).matches())
		{
			executor.execute(() -> telegramService.sendMessage(message));
		}
	}
}
