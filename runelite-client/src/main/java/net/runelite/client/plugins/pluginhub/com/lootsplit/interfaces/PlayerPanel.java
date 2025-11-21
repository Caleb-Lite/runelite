package net.runelite.client.plugins.pluginhub.com.lootsplit.interfaces;

import net.runelite.client.plugins.pluginhub.com.lootsplit.LootPanel;
import net.runelite.client.plugins.pluginhub.com.lootsplit.PlayerInfo;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PlayerPanel extends JPanel
{
	LootPanel lootPanel;

	public PlayerPanel(LootPanel lootPanel) {
		super();
		this.lootPanel = lootPanel;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new LineBorder(Color.BLACK, 2));
	}

	public void add(PlayerInfo playerInfo)
	{
		PlayerEntry playerEntry = new PlayerEntry(this, playerInfo);
		add(playerEntry);
		revalidate();
		repaint();
	}
}
