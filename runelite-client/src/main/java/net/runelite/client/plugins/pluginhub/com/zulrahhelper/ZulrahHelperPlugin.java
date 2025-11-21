package net.runelite.client.plugins.pluginhub.com.zulrahhelper;

import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.tree.Node;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.tree.PatternTree;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.ui.ZulrahHelperPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.swing.SwingUtilities;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.HotkeyListener;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
	name = "Zulrah Helper",
	description = "Panel to show Zulrah rotations",
	tags = {"zulrah", "pvm"}
)
public class ZulrahHelperPlugin extends Plugin
{
	static final String CONFIG_GROUP = "zulrahhelper";
	static final String SECTION_IMAGE_OPTIONS = "Image Options";
	static final String SECTION_HOTKEYS = "Hotkeys";
	static final String SECTION_MISC = "Miscellaneous";

	static final String DARK_MODE_KEY = "darkMode";
	static final String DISPLAY_PRAYER_KEY = "displayPrayer";
	static final String DISPLAY_ATTACK_KEY = "displayAttack";
	static final String DISPLAY_VENOM_KEY = "displayVenom";
	static final String DISPLAY_SNAKELINGS_KEY = "displaySnakelings";
	static final String IMAGE_ORIENTATION_KEY = "imageOrientation";
	static final String AUTO_HIDE_KEY = "autoHide";
	static final String RESET_ON_LEAVE_KEY = "resetOnLeave";
	static final String MAGE_COLOR_KEY = "mageColor";
	static final String RANGE_COLOR_KEY = "rangeColor";
	static final String MELEE_COLOR_KEY = "meleeColor";


	private static final int ZULANDRA_REGION_ID = 8751;
	private static final int ZULRAH_SPAWN_REGION_ID = 9007;
	private static final int ZULRAH_REGION_ID = 9008;

	private static final List<String> OPTION_KEYS = Arrays.asList(
		DARK_MODE_KEY,
		DISPLAY_PRAYER_KEY,
		DISPLAY_ATTACK_KEY,
		IMAGE_ORIENTATION_KEY,
		RESET_ON_LEAVE_KEY,
		DISPLAY_VENOM_KEY,
		DISPLAY_SNAKELINGS_KEY,
		MAGE_COLOR_KEY,
		MELEE_COLOR_KEY,
		RANGE_COLOR_KEY
	);

	@Inject
	private KeyManager keyManager;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private Client client;

	@Inject
	private PatternTree tree;

	@Inject
	@Getter
	private ZulrahHelperConfig config;

	private ZulrahHelperPanel panel;
	private NavigationButton navButton;

	private final List<HotkeyListener> hotkeys = new ArrayList<>();
	private boolean hotkeysEnabled = false;
	private boolean panelEnabled = false;
	private boolean wasInInstance = false;

	@Override
	protected void startUp() throws Exception
	{
		panel = injector.getInstance(ZulrahHelperPanel.class);
		navButton = NavigationButton.builder()
			.tooltip("Zulrah Helper")
			.icon(ImageUtil.loadImageResource(getClass(), "/icon.png"))
			.priority(70)
			.panel(panel)
			.build();
		clientToolbar.addNavigation(navButton);

		initHotkeys();
		togglePanel(!config.autoHide(), false);
		reset();
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientToolbar.removeNavigation(navButton);
		hotkeys.forEach(keyManager::unregisterKeyListener);
		hotkeys.clear();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals(CONFIG_GROUP))
		{
			return;
		}

		if (OPTION_KEYS.contains(event.getKey()))
		{
			rebuildPanel();
		}

		if (event.getKey().equals(AUTO_HIDE_KEY) && !config.autoHide())
		{
			togglePanel(true, false);
		}
	}

	@Provides
	ZulrahHelperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ZulrahHelperConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		checkRegion();
	}

	private void checkRegion()
	{
		if (inZulrahRegion())
		{
			if (!hotkeysEnabled)
			{
				toggleHotkeys();
			}

			if (config.autoHide() && !panelEnabled)
			{
				togglePanel(true, true);
			}

			if (wasInInstance && !client.isInInstancedRegion())
			{
				reset();
			}

			wasInInstance = client.isInInstancedRegion();
		}
		else
		{
			if (hotkeysEnabled)
			{
				toggleHotkeys();
			}

			if (panelEnabled && config.autoHide())
			{
				togglePanel(false, false);
			}

			if (wasInInstance)
			{
				if (config.resetOnLeave())
				{
					reset();
				}

				wasInInstance = false;
			}
		}
	}

	private boolean inZulrahRegion()
	{
		final int regionId = getRegionId();

		return regionId == ZULANDRA_REGION_ID ||
			((regionId == ZULRAH_SPAWN_REGION_ID || regionId == ZULRAH_REGION_ID) && client.isInInstancedRegion());
	}

	private int getRegionId()
	{
		Player player = client.getLocalPlayer();
		if (player == null)
		{
			return -1;
		}

		return WorldPoint.fromLocalInstance(client, player.getLocalLocation()).getRegionID();
	}

	public void reset()
	{
		tree.reset();
		SwingUtilities.invokeLater(() -> panel.rebuildPanel());
	}

	public void setState(Node node)
	{
		var s = tree.find(node.getValue());
		if (s == null)
		{
			log.debug("state not found {}", node);
			return;
		}

		if (s.getValue().isReset())
		{
			reset();
			return;
		}

		tree.setState(s);
		rebuildPanel();
	}

	private void rebuildPanel()
	{
		SwingUtilities.invokeLater(() -> panel.rebuildPanel());
	}

	private void selectOption(int choice)
	{
		var node = tree.getState();
		var choices = node.getChildren();
		if (choice >= choices.size())
		{
			log.error("trying to select nonexistent phase: {} {}", choice, choices.size());
			return;
		}

		setState(choices.get(choice));
	}

	private void initHotkeys()
	{
		hotkeys.forEach(keyManager::unregisterKeyListener);
		hotkeys.clear();

		hotkeys.add(new HotkeyListener(() -> config.phaseSelection1Hotkey())
		{
			@Override
			public void hotkeyPressed()
			{
				selectOption(0);
			}
		});

		hotkeys.add(new HotkeyListener(() -> config.phaseSelection2Hotkey())
		{
			@Override
			public void hotkeyPressed()
			{
				selectOption(1);
			}
		});

		hotkeys.add(new HotkeyListener(() -> config.phaseSelection3Hotkey())
		{
			@Override
			public void hotkeyPressed()
			{
				selectOption(2);
			}
		});

		hotkeys.add(new HotkeyListener(() -> config.nextPhaseHotkey())
		{
			@Override
			public void hotkeyPressed()
			{
				selectOption(0);
			}
		});

		hotkeys.add(new HotkeyListener(() -> config.resetPhasesHotkey())
		{
			@Override
			public void hotkeyPressed()
			{
				reset();
			}
		});
	}

	private void toggleHotkeys()
	{
		hotkeys.forEach(hotkeysEnabled ? keyManager::unregisterKeyListener : keyManager::registerKeyListener);
		hotkeysEnabled = !hotkeysEnabled;
	}

	private void togglePanel(boolean enable, boolean show)
	{
		panelEnabled = enable;
		if (enable)
		{
			clientToolbar.addNavigation(navButton);
			if (show)
			{
				SwingUtilities.invokeLater(() -> {
					clientToolbar.openPanel(navButton);
				});
			}
		}
		else
		{
			clientToolbar.removeNavigation(navButton);
		}
	}
}

/*
 * Copyright (c) 2024, Ron Young <https://github.com/raiyni>
 * All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
