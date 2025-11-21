package net.runelite.client.plugins.pluginhub.com.quicksearch;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.CommandExecuted;
import net.runelite.client.util.LinkBrowser;
import java.util.Map;
import java.util.HashMap;

@Slf4j
@PluginDescriptor(
		name = "Quick Search",
		description = "Use in-game commands to search various platforms",
		tags = {"search", "quick", "wiki", "osrs", "youtube", "yt", "kick", "twitch"}
)
public class QuickSearchPlugin extends Plugin {
	private static final Map<String, String> URL_PATTERNS = new HashMap<>();

	@Inject
	private Client client;

	@Inject
	private QuickSearchConfig config;

	@Override
	protected void startUp() {
		initializeUrlPatterns();
	}

	private void initializeUrlPatterns() {
		// Wiki
		URL_PATTERNS.put("w", "https://oldschool.runescape.wiki/w/%s");
		URL_PATTERNS.put("osrs", "https://oldschool.runescape.wiki/w/%s");
		// Google
		URL_PATTERNS.put("g", "https://www.google.com/search?q=%s");
		URL_PATTERNS.put("google", "https://www.google.com/search?q=%s");
		// YouTube
		URL_PATTERNS.put("yt", "https://www.youtube.com/results?search_query=%s");
		URL_PATTERNS.put("youtube", "https://www.youtube.com/results?search_query=%s");
		// Twitch
		URL_PATTERNS.put("ttv", "https://www.twitch.tv/search?term=%s");
		URL_PATTERNS.put("twitch", "https://www.twitch.tv/search?term=%s");
		// Kick
		URL_PATTERNS.put("kick", "https://kick.com/search?query=%s");
		// Reddit
		// URL_PATTERNS.put("reddit", "https://www.reddit.com/search/?q=%s");
		// URL_PATTERNS.put("r", "https://www.reddit.com/search/?q=%s");
	}

	@Subscribe
	public void onCommandExecuted(CommandExecuted event) {
		String platform = event.getCommand().toLowerCase();

		if (!URL_PATTERNS.containsKey(platform)) {
			return;
		}

		String[] arguments = event.getArguments();
		if (arguments.length == 0) {
			String example = getExampleForPlatform(platform);
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "[Quick Search] Usage: " + example, null);
			return;
		}

		if (!isPlatformEnabled(platform)) {
			if (!config.suppressDisabled()) {
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
						"[Quick Search] " + platform + " search is disabled. Enable it in the plugin settings.", null);
			}
			return;
		}

		String query = String.join(" ", arguments);
		String separator = (platform.equals("w") || platform.equals("osrs")) ? "_" : "+";
		String url = String.format(URL_PATTERNS.get(platform), query.replace(" ", separator));
		LinkBrowser.browse(url);
	}

	private String getExampleForPlatform(String platform) {
		switch (platform) {
			case "w":
			case "osrs":
				return "::w abyssal whip";
			case "google":
			case "g":
				return "::g latest news";
			case "youtube":
			case "yt":
				return "::yt zulrah guide";
			case "twitch":
			case "ttv":
				return "::ttv streamer";
			case "kick":
				return "::kick streamer";
			default:
				return "::" + platform + " <query>";
		}
	}

	private boolean isPlatformEnabled(String platform) {
		switch (platform) {
			case "w":
			case "osrs":
				return config.enableWiki();
			case "google":
			case "g":
				return config.enableGoogle();
			case "youtube":
			case "yt":
				return config.enableYoutube();
			case "kick":
				return config.enableKick();
			case "twitch":
			case "ttv":
				return config.enableTwitch();
			default:
				return false;
		}
	}

	@Provides
	QuickSearchConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(QuickSearchConfig.class);
	}
}
