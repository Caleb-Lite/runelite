package net.runelite.client.plugins.pluginhub.com.fightcavehelper.ui;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.PluginPanel;

@Slf4j
@Singleton
public class FightCaveHelperPanel extends PluginPanel
{
	public FightCaveHelperPanel() throws IOException
	{
		super();

		TopPanel topPanel = new TopPanel();
		ButtonPanel buttonPanel = new ButtonPanel();
		WaveSpawnPanel waveSpawnPanel = new WaveSpawnPanel();


		setLayout(new BorderLayout(5, 5));


		add(topPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		add(waveSpawnPanel, BorderLayout.SOUTH);
	}

}

