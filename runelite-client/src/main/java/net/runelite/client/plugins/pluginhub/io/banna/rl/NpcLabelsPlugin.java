package net.runelite.client.plugins.pluginhub.io.banna.rl;

import com.google.common.base.Strings;
import com.google.common.util.concurrent.Runnables;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.io.banna.rl.domain.NpcLabel;
import net.runelite.client.plugins.pluginhub.io.banna.rl.item.ItemUtil;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.KeyCode;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.Model;
import net.runelite.api.NPC;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.chatbox.ChatboxPanelManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.components.colorpicker.ColorPickerManager;
import net.runelite.client.ui.components.colorpicker.RuneliteColorPicker;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import javax.swing.SwingUtilities;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@PluginDescriptor(
	name = "NPC Labels"
)
public class NpcLabelsPlugin extends Plugin
{

	private static final String LABEL = "Label";
	private static final String MANAGE_LABEL = "Manage-Label";
	private static final String SET_LABEL_TEXT = "Set Label Text";
	private static final String SET_LABEL_ICON = "Set Label Item";
	private static final String SET_LABEL_COLOR = "Set Label Color";
	private static final String CLEAR_LABEL = "Clear Label";

	private static final String LABEL_CONFIG_PREFIX = "npc_";

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private NpcLabelsConfig config;

	@Inject
	private ConfigManager configManager;

	@Inject
	private Gson gson;

	@Inject
	private NpcLabelsOverlay overlay;

	@Inject
	private ChatboxPanelManager chatboxPanelManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ColorPickerManager colorPickerManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ItemUtil itemUtil;

	private Map<Integer, NpcLabel> labelMap = new HashMap<>();

	private List<NPC> relevantNpcs = new ArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		log.info("NPC Labels started!");
		overlayManager.add(overlay);
		rebuildNpcList();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("NPC Labels stopped!");
		save();
		relevantNpcs.clear();
		overlayManager.remove(overlay);
	}

	@Provides
	NpcLabelsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NpcLabelsConfig.class);
	}

	@Subscribe
	public void onCommandExecuted(CommandExecuted commandExecuted)
	{
		if (commandExecuted.getCommand().equals("export-npc-labels"))
		{
			String exportString  = getExportJson();

			Toolkit.getDefaultToolkit()
					.getSystemClipboard()
					.setContents(new StringSelection(exportString), null);
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Exported all NPC labels to clipboard", null);
		}
		else if (commandExecuted.getCommand().equals("import-npc-labels"))
		{
			final String clipboardText;
			try
			{
				clipboardText = Toolkit.getDefaultToolkit()
						.getSystemClipboard()
						.getData(DataFlavor.stringFlavor)
						.toString();
			}
			catch (IOException | UnsupportedFlavorException ex)
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Error reading clipboard contents", null);
				log.warn("error reading clipboard", ex);
				return;
			}

			log.debug("Clipboard contents: {}", clipboardText);
			if (Strings.isNullOrEmpty(clipboardText))
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You don't have any NPC labels in your clipboard", null);
				return;
			}

			List<NpcLabel> importLabels;
			try
			{
				// CHECKSTYLE:OFF
				importLabels = gson.fromJson(clipboardText, new TypeToken<List<NpcLabel>>(){}.getType());
				// CHECKSTYLE:ON
			}
			catch (JsonSyntaxException e)
			{
				log.debug("Malformed JSON for clipboard import", e);
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You don't have any NPC labels in your clipboard", null);
				return;
			}

			if (importLabels.isEmpty())
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You don't have any NPC labels in your clipboard", null);
				return;
			}

			chatboxPanelManager.openTextMenuInput("Are you sure you want to import " + importLabels.size() + " NPC labels?")
					.option("Yes", () -> importLabels(importLabels))
					.option("No", Runnables.doNothing())
					.build();
		}
	}

	private void importLabels(List<NpcLabel> importLabels) {
		for (NpcLabel label : importLabels) {
			labelMap.put(label.getNpcId(), label);
		}
		save();
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Successfully imported " + importLabels.size() + " NPC labels", null);
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		final MenuEntry menuEntry = event.getMenuEntry();
		final MenuAction menuAction = menuEntry.getType();
		final NPC npc = menuEntry.getNpc();

		if (npc == null)
		{
			return;
		}

		if (menuAction == MenuAction.EXAMINE_NPC && client.isKeyPressed(KeyCode.KC_SHIFT))
		{
			if (npc.getName() == null)
			{
				return;
			}

			int idx = -1;

			if (getNpcLabel(npc) == null) {
				client.createMenuEntry(idx--)
						.setOption(LABEL)
						.setTarget(event.getTarget())
						.setIdentifier(event.getIdentifier())
						.setType(MenuAction.RUNELITE)
						.onClick(this::label);
			} else {
				MenuEntry menuManage = client.createMenuEntry(idx--)
						.setOption(MANAGE_LABEL)
						.setTarget(event.getTarget())
						.setType(MenuAction.RUNELITE_SUBMENU);

				client.createMenuEntry(idx--)
						.setOption(SET_LABEL_TEXT)
						.setTarget(event.getTarget())
						.setIdentifier(event.getIdentifier())
						.setType(MenuAction.RUNELITE)
						.setParent(menuManage)
						.onClick(this::setLabelText);

				client.createMenuEntry(idx--)
						.setOption(SET_LABEL_ICON)
						.setTarget(event.getTarget())
						.setIdentifier(event.getIdentifier())
						.setType(MenuAction.RUNELITE)
						.setParent(menuManage)
						.onClick(this::setLabelIcon);

				client.createMenuEntry(idx--)
						.setOption(SET_LABEL_COLOR)
						.setTarget(event.getTarget())
						.setIdentifier(event.getIdentifier())
						.setType(MenuAction.RUNELITE)
						.setParent(menuManage)
						.onClick(this::setLabelColor);

				client.createMenuEntry(idx--)
						.setOption(CLEAR_LABEL)
						.setTarget(event.getTarget())
						.setIdentifier(event.getIdentifier())
						.setType(MenuAction.RUNELITE)
						.setParent(menuManage)
						.onClick(this::clearLabel);

			}
		}
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned npcSpawned) {
		final NPC npc = npcSpawned.getNpc();
		if (getNpcLabel(npc) != null && !exclude(npc)) {
			// This is relevant!
			relevantNpcs.add(npc);
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned npcDespawned) {
		final NPC npc = npcDespawned.getNpc();
		relevantNpcs.remove(npc);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged) {
		if (NpcLabelsConfig.CONFIG_GROUP.equals(configChanged.getGroup())) {
			rebuildNpcList();
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged) {
		if (gameStateChanged.getGameState() == GameState.LOGIN_SCREEN ||
				gameStateChanged.getGameState() == GameState.HOPPING) {
			relevantNpcs.clear();
		}
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			rebuildNpcList();
		}
	}

	private void rebuildNpcList() {
		relevantNpcs.clear();
		if (client.getGameState() != GameState.LOGGED_IN &&
				client.getGameState() != GameState.LOADING) {
			// NPCs are still in the client after logging out, ignore them
			return;
		}

		for (NPC npc : client.getNpcs()) {
			if (getNpcLabel(npc) != null && !exclude(npc)) {
				relevantNpcs.add(npc);
			}
		}
	}

	private void label(MenuEntry entry) {
		final int id = entry.getIdentifier();
		final NPC[] cachedNPCs = client.getCachedNPCs();
		final NPC npc = cachedNPCs[id];

		log.info("Clicked label on {}", npc.getName());

		NpcLabel existing = getNpcLabel(npc);

		String existingValue = "";
		if (existing != null)
		{
			existingValue = existing.getLabel();
		}
		chatboxPanelManager.openTextInput("NPC label")
				.value(existingValue)
				.onDone((input) ->
				{
					input = Strings.emptyToNull(input);

					log.info("Adding label [{}] to NPC [{}]", input, npc.getName());

					NpcLabel label = new NpcLabel();
					label.setNpcId(npc.getId());
					label.setNpcName(npc.getName());
					label.setLabel(input);
					label.setColor(Color.GREEN);

					labelMap.put(npc.getId(), label);
					save();
					rebuildNpcList();
				})
				.build();
	}

	private void setLabelText(MenuEntry entry) {
		final int id = entry.getIdentifier();
		final NPC[] cachedNPCs = client.getCachedNPCs();
		final NPC npc = cachedNPCs[id];
		NpcLabel existing = getNpcLabel(npc);
		if (existing == null) {
			log.info("Not sure how you got here!");
			return;
		}

		chatboxPanelManager.openTextInput("NPC label")
				.value(existing.getLabel() == null ? "" : existing.getLabel())
				.onDone((input) ->
				{
					input = Strings.emptyToNull(input);
					log.info("Adding label [{}] to NPC [{}]", input, npc.getName());
					existing.setLabel(input);
					existing.setColor(Color.GREEN);
					save();
				})
				.build();
	}

	private void setLabelIcon(MenuEntry entry) {
		final int id = entry.getIdentifier();
		final NPC[] cachedNPCs = client.getCachedNPCs();
		final NPC npc = cachedNPCs[id];

		NpcLabel existing = getNpcLabel(npc);
		if (existing == null) {
			log.info("Not sure how you got here!");
			return;
		}
		String existingValue = "";
		if (existing.getItemIconName() != null) {
			existingValue = existing.getItemIconName();
		}
		chatboxPanelManager.openTextInput("NPC label icon item ID")
				.value(existingValue)
				.onDone((input) ->
				{
					input = Strings.emptyToNull(input);
					if (input != null) {
						String finalInput = input;
						clientThread.invoke(() -> client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Searching for item with name " + finalInput, null));
						itemUtil.getId(input, itemId ->
							clientThread.invokeLater(() -> {
								if (itemId == -1) {
									client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Failed to find item with name " + finalInput + ", have not updated the icon for " + npc.getName(), null);
								} else {
									String itemName = itemManager.getItemComposition(itemId).getMembersName();
									client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Found item with name " + itemName + ", set as icon for " + npc.getName(), null);
									existing.setItemIconId(itemId);
									existing.setItemIconName(itemName);
									save();
								}
							})
						);
					}

				})
				.build();
	}

	private void setLabelColor(MenuEntry entry) {
		final int id = entry.getIdentifier();
		final NPC[] cachedNPCs = client.getCachedNPCs();
		final NPC npc = cachedNPCs[id];

		NpcLabel existing = getNpcLabel(npc);

		Color color = existing.getColor();
		SwingUtilities.invokeLater(() ->
		{
			RuneliteColorPicker colorPicker = colorPickerManager.create(SwingUtilities.windowForComponent((Applet) client),
					color, "NPC Label color", false);
			colorPicker.setOnClose(c -> {
				existing.setColor(c);
				save();
			});
			colorPicker.setVisible(true);
		});
	}

	private void clearLabel(MenuEntry entry) {
		final int id = entry.getIdentifier();
		final NPC[] cachedNPCs = client.getCachedNPCs();
		final NPC npc = cachedNPCs[id];

		if (config.confirmClear()) {
			chatboxPanelManager.openTextMenuInput("Are you sure you want to clear the label for " + npc.getName())
					.option("Yes", () ->
					{
						clearConfig(npc.getId());
						labelMap.remove(npc.getId());
						save();
					})
					.option("No", Runnables.doNothing())
					.build();
		} else {
			clearConfig(npc.getId());
			labelMap.remove(npc.getId());
			save();
		}
	}

	private void save() {
		for (Map.Entry<Integer,NpcLabel> entry : labelMap.entrySet()) {
			String labelJson = gson.toJson(entry.getValue());
			configManager.setConfiguration(NpcLabelsConfig.CONFIG_GROUP, LABEL_CONFIG_PREFIX + entry.getKey(), labelJson);
		}
	}

	public String getExportJson() {
		return gson.toJson(configManager.getConfigurationKeys(NpcLabelsConfig.CONFIG_GROUP)
				.stream()
				.map(c -> {
					String[] wholeKeyParts = c.split("\\.");
					return wholeKeyParts[wholeKeyParts.length - 1];
				})
				.filter(key -> key.startsWith(LABEL_CONFIG_PREFIX))
				.map(key -> {
					String labelJson = configManager.getConfiguration(NpcLabelsConfig.CONFIG_GROUP, key);
					if (labelJson == null || "".equals(labelJson)) {
						return null;
					}
					return gson.fromJson(labelJson, NpcLabel.class);
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toList()));
	}

	private void clearConfig(int npcId) {
		configManager.unsetConfiguration(NpcLabelsConfig.CONFIG_GROUP, LABEL_CONFIG_PREFIX + npcId);
	}

	public NpcLabel getNpcLabel(NPC npc) {
		if (labelMap.containsKey(npc.getId())) {
			return labelMap.get(npc.getId());
		} else {
			String labelJson = configManager.getConfiguration(NpcLabelsConfig.CONFIG_GROUP, LABEL_CONFIG_PREFIX + npc.getId());
			if (labelJson != null) {
				NpcLabel label = gson.fromJson(labelJson, NpcLabel.class);
				labelMap.put(npc.getId(), label);
				return label;
			}
		}
		return null;
	}

	public boolean exclude(NPC npc) {
		return npc == null || npc.getName() == null || npc.getName().isEmpty() || "null".equals(npc.getName());
	}

	public List<NPC> getRelevantNpcs() {
		return relevantNpcs;
	}
}
