package net.runelite.client.plugins.pluginhub.com.dryness;

import static com.dryness.DryCalculatorService.calculateDryness;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

public class DryCalculatorPanel extends PluginPanel
{
	private final JTextField droprate = new JTextField();
	private final JTextField drops = new JTextField();
	private final JTextField kc = new JTextField();
	private final JTextArea result = new JTextArea();
	private final ImageIcon resultIcon = new ImageIcon();

	DryCalculatorPanel()
	{
		result.setLineWrap(true);
		result.setEditable(false);
		JButton calculate = new JButton();
		calculate.setText("Calculate");
		calculate.addActionListener(this::handleClick);

		setLayout(new BorderLayout());
		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setBorder(new EmptyBorder(10, 10, 10, 10));


		JPanel dryPanel = new JPanel();
		dryPanel.setLayout(new GridLayout(0, 1));
		JLabel title = new JLabel();
		title.setFont(FontManager.getDefaultBoldFont());
		title.setText("Dryness calculator");

		JLabel dropRateLabel = new JLabel();
		dropRateLabel.setText("Droprate");

		JLabel kcLabel = new JLabel();
		kcLabel.setText("Kc");

		JLabel dropsLabel = new JLabel();
		dropsLabel.setText("Amount of drops");

		dryPanel.add(title);

		dryPanel.add(dropRateLabel);
		dryPanel.add(droprate);

		dryPanel.add(kcLabel);
		dryPanel.add(kc);

		dryPanel.add(dropsLabel);
		dryPanel.add(drops);

		dryPanel.add(calculate);

		add(dryPanel, BorderLayout.NORTH);
		add(result, BorderLayout.CENTER);
		JLabel resultImage = new JLabel(resultIcon);
		add(resultImage, BorderLayout.SOUTH);
	}

	private void handleClick(final ActionEvent actionEvent)
	{
		DrynessInput input = null;
		try
		{
			input = new DrynessInput(droprate.getText(), kc.getText(), drops.getText());
		}
		catch (Exception exception)
		{
			result.setText("Invalid input: " + exception.getMessage());
			resultIcon.setImage(null);
		}
		if (input != null)
		{
			DrynessResult drynessResult = calculateDryness(input);
			result.setText(drynessResult.getText());
			resultIcon.setImage(drynessResult.getImage());
		}
	}
}
