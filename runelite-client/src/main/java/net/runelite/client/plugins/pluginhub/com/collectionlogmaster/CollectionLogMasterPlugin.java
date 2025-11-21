package net.runelite.client.plugins.pluginhub.com.collectionlogmaster;

import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.command.DevCommandsManager;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.command.TaskmanCommandManager;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.input.MouseManager;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.synchronization.clog.CollectionLogService;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.task.TaskService;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.ui.InterfaceManager;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.ui.TaskOverlay;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.util.GsonOverride;
import com.google.inject.Injector;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "Collection Log Master",
		conflicts = {"[DEPRECATED] Collection Log Master"})
public class CollectionLogMasterPlugin extends Plugin {
	@Inject
	@SuppressWarnings("unused")
	private GsonOverride gsonOverride;

	@Getter
	private static Injector staticInjector;

	@Inject
	protected TaskOverlay taskOverlay;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private MouseManager mouseManager;

	@Inject
	private InterfaceManager interfaceManager;

	@Inject
	public CollectionLogService collectionLogService;

	@Inject
	public PluginUpdateNotifier pluginUpdateNotifier;

	@Inject
	public TaskService taskService;

	@Inject
	public TaskmanCommandManager taskmanCommand;

	@Inject
	public DevCommandsManager devCommands;

	@Override
	protected void startUp() {
		CollectionLogMasterPlugin.staticInjector = getInjector();

		mouseManager.startUp();
		taskService.startUp();
		collectionLogService.startUp();
		pluginUpdateNotifier.startUp();
		interfaceManager.startUp();
		taskmanCommand.startUp();
		devCommands.startUp();
		this.taskOverlay.setResizable(true);
		this.overlayManager.add(this.taskOverlay);
	}

	@Override
	protected void shutDown() {
		mouseManager.shutDown();
		taskService.shutDown();
		collectionLogService.shutDown();
		pluginUpdateNotifier.shutDown();
		interfaceManager.shutDown();
		taskmanCommand.shutDown();
		devCommands.shutDown();
		this.overlayManager.remove(this.taskOverlay);
	}

	@Provides
	CollectionLogMasterConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(CollectionLogMasterConfig.class);
	}
}
