package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.components;

import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.EmoteClueItemsImages;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.EmoteClueItemsPalette;
import lombok.Setter;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.components.shadowlabel.JShadowedLabel;
import net.runelite.client.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Displays a notification, supported by a question mark icon and a close button with configurable onClick behaviour.
 *
 * @author Lars van Soest
 * @since 3.0.0
 */
public class DisclaimerPanel extends JPanel
{
	private final JLabel textLabel;

	@Setter
	private Runnable onClose;

	/**
	 * Creates the panel.
	 *
	 * @param emoteClueItemsPalette Colour scheme for the panel.
	 * @param onClose               Behaviour to run when the notification's close button is pressed.
	 */
	public DisclaimerPanel(final EmoteClueItemsPalette emoteClueItemsPalette, final Runnable onClose)
	{
		super(new GridBagLayout());
		super.setBackground(emoteClueItemsPalette.getDisclaimerColor());

		final JLabel questionCircleIconLabel = new JLabel(new ImageIcon(EmoteClueItemsImages.Icons.QUESTION));

		this.textLabel = new JShadowedLabel();
		this.textLabel.setHorizontalAlignment(JLabel.LEFT);
		this.textLabel.setVerticalAlignment(JLabel.CENTER);
		this.textLabel.setFont(FontManager.getRunescapeSmallFont());

		this.onClose = onClose;

		final Icon closeIllumatedIcon = new ImageIcon(ImageUtil.luminanceScale(EmoteClueItemsImages.Icons.CLOSE, 150));
		final Icon closeIcon = new ImageIcon(EmoteClueItemsImages.Icons.CLOSE);
		final JLabel closeIconLabel = new JLabel(closeIcon);
		closeIconLabel.setToolTipText("Close");
		closeIconLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(final MouseEvent e)
			{
				DisclaimerPanel.this.onClose.run();
			}

			@Override
			public void mouseEntered(final MouseEvent e)
			{
				closeIconLabel.setIcon(closeIllumatedIcon);
			}

			@Override
			public void mouseExited(final MouseEvent e)
			{
				closeIconLabel.setIcon(closeIcon);
			}
		});


		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 10, 5, 0);
		c.weightx = 0;
		c.weighty = 0;
		super.add(questionCircleIconLabel, c);

		c.gridx++;
		c.weightx = 1;
		c.weighty = 1;
		super.add(this.textLabel, c);

		c.gridx++;
		c.weightx = 0;
		c.weighty = 0;
		c.insets.right = 10;
		super.add(closeIconLabel, c);
	}

	/**
	 * Sets the notification to given text. Applied html formatting for automatic line breaks.
	 *
	 * @param text The text of the notification.
	 */
	public void setText(final String text)
	{
		this.textLabel.setText(String.format("<html><p style=\"width:100%%\">%s</p></html>", text));
	}
}
