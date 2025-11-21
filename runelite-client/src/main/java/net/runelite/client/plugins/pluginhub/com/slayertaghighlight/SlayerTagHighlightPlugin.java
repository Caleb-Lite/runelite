package net.runelite.client.plugins.pluginhub.com.slayertaghighlight;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.plugins.slayer.SlayerPlugin;
import net.runelite.client.plugins.slayer.SlayerPluginService;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.Text;
import net.runelite.client.util.WildcardMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@PluginDescriptor(
		name = "Slayer Tag Highlight",
		description = "Highlights on task NPCs that aren't tagged yet (not interacting with you)",
		tags = {"Slayer", "Tag", "Highlight", "NPC"}
)
@PluginDependency(SlayerPlugin.class)
public class SlayerTagHighlightPlugin extends Plugin {

	@Inject
	private Client client;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private SlayerTagHighlightOverlay overlay;
	@Inject
	private SlayerTagHighlightMinimapOverlay minimapOverlay;
	@Inject
	private SlayerTagHighlightConfig config;
	@Inject
	private SlayerPluginService slayerPluginService;
	@Inject
	private PluginManager pluginManager;

	@Provides
	SlayerTagHighlightConfig getConfig(ConfigManager configManager) {
		return configManager.getConfig(SlayerTagHighlightConfig.class);
	}

	@Getter
	private ArrayList<NPC> highlights = new ArrayList<>();
	private List<String> filterNames;

	@Override
	protected void startUp() {
		final Optional<Plugin> slayerPlugin = pluginManager.getPlugins().stream().filter(p -> p.getName().equals("Slayer")).findFirst();
		if (slayerPlugin.isPresent() && pluginManager.isPluginEnabled(slayerPlugin.get())) {
			pluginManager.setPluginEnabled(slayerPlugin.get(), true);
		}
		overlayManager.add(overlay);
		overlayManager.add(minimapOverlay);
		filterNames = Text.fromCSV(config.filterList());
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(overlay);
		overlayManager.remove(minimapOverlay);
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		highlights.clear();
		for (NPC npc : client.getNpcs()) {
			if (slayerPluginService.getTargets().contains(npc)
					&& !highlights.contains(npc)
					&& !npc.isInteracting()
					&& !npc.isDead()
					&& (highlightMatchesNPCName(npc.getName()) || !config.filterByList())
			) {
				highlights.add(npc);
			}
		}
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event) {
		final MenuEntry menuEntry = event.getMenuEntry();
		final NPC npc = menuEntry.getNpc();

		if (highlights.contains(npc) && config.menuHighlight()) {
			String string = ColorUtil.prependColorTag(Text.removeTags(event.getTarget()), config.highlightColor());
			menuEntry.setTarget(string);
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if (event.getKey().equals("filterList")) {
			filterNames = Text.fromCSV(config.filterList());
		}
	}

	private boolean highlightMatchesNPCName(String npcName)
	{
		for (String filterName : filterNames)
		{
			if (WildcardMatcher.matches(filterName, npcName))
			{
				return true;
			}
		}

		return false;
	}

}