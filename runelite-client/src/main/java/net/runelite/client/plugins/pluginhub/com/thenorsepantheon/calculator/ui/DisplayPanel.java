package net.runelite.client.plugins.pluginhub.com.thenorsepantheon.calculator.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lombok.Getter;

public class DisplayPanel extends JPanel
{
	@Getter
	private final DisplayField displayField = new DisplayField();

	public DisplayPanel()
	{
		super();

		setLayout(new BorderLayout(4, 4));
		setBorder(new EmptyBorder(0, 1, 0, 1));

		displayField.setPreferredSize(new Dimension(228, 50));
		displayField.setMinimumSize(new Dimension(170, 50));
		displayField.setEditable(false);

		add(displayField, BorderLayout.CENTER);
	}
}

