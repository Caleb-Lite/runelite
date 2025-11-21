package net.runelite.client.plugins.pluginhub.com.dataexport.ui;

import net.runelite.client.plugins.pluginhub.com.dataexport.DataExport;
import net.runelite.client.plugins.pluginhub.com.dataexport.DataExportConfig;
import net.runelite.client.plugins.pluginhub.com.dataexport.DataExportPlugin;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

@Slf4j
public class DataExportPluginPanel extends PluginPanel
{
	final JPanel wrapperPanel = new JPanel();

	private final ItemManager itemManager;

	private final DataExportPlugin plugin;

	private final DataExportConfig config;

	private final DataExport dataExport;

	private JPanel containerContainer = new JPanel();

	private Map<Tab, DataExportTabPanel> containers = new LinkedHashMap<>();

	public DataExportPluginPanel(ItemManager itemManager, DataExportPlugin plugin, DataExportConfig config, DataExport dataExport)
	{
		super(true);

		this.itemManager = itemManager;
		this.plugin = plugin;
		this.config = config;
		this.dataExport = dataExport;

		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(7, 7, 7, 7));

		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
		wrapperPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);

		containerContainer.setLayout(new GridLayout(0, 1, 0, 8));
		containerContainer.setBackground(ColorScheme.DARK_GRAY_COLOR);
		containerContainer.setVisible(true);

		Arrays.asList(Tab.CONTAINER_TABS).forEach(t ->
		{
			DataExportTabPanel p = new DataExportTabPanel(plugin, this, config, dataExport, itemManager, t, t.getName(), t.getFilePrefix(), "Not ready");
			containers.put(t, p);
		});

		containers.forEach((tab, panel) ->
			containerContainer.add(panel));

		wrapperPanel.add(containerContainer);

		this.add(wrapperPanel);

		updateVisibility();
		rebuild();
	}

	public void updateVisibility()
	{
		containerContainer.removeAll();

		log.debug("Containers: {}", containers.values());

		containers.forEach((t, p) ->
		{
			if (p.isVisibility())
			{
				containerContainer.add(p);
			}
		});

		rebuild();
	}

	public void setVisibility(Tab tab, boolean visibility)
	{
		log.debug("Containers: {}", containers.values());

		Map<Tab, DataExportTabPanel> containersTemp = new LinkedHashMap<>();

		containers.forEach((t, p) ->
		{
			if (p.isVisibility() && t.getName().compareTo(p.getTitle()) != 0)
			{
				setVisibility(Tab.ALL_ITEMS, true);
			}
			if (tab.getName().equals(t.getName()))
			{
				DataExportTabPanel panel = containers.get(tab);
				panel.setVisibility(visibility);
				containersTemp.put(t, panel);
			}

			containersTemp.put(t, p);
		});

		containers = containersTemp;
	}

	public void updateTab(String container, String newStatus)
	{
		containers.forEach((tab, panel) ->
		{
			if (panel.getTitle().equals(container))
			{
				panel.updateStatus(newStatus);
			}
			containers.put(tab, panel);
		});

		containers.forEach((tab, panel) ->
			containerContainer.add(panel));

		rebuild();
	}

	public void rebuild()
	{
		revalidate();
		repaint();
	}
}

/*
 * Copyright (c) 2018 Abex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */