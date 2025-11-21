package net.runelite.client.plugins.pluginhub.autoretaliatewarning;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class AutoRetaliateWarningOverlay extends OverlayPanel
{
	private final AutoRetaliateWarningPlugin plugin;
	private final AutoRetaliateWarningConfig config;

	@Inject
	private AutoRetaliateWarningOverlay(AutoRetaliateWarningPlugin plugin, AutoRetaliateWarningConfig config)
	{
		super(plugin);
		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		graphics.setFont(new Font(FontManager.getRunescapeFont().getName(), Font.PLAIN, config.fontSize()));

		// Get custom text from config
		String autoRetaliateWarning = config.autoRetaliateText();
		String autoRetaliateOffWarning = config.autoRetaliateOffText();
		String npcAttackWarning = config.npcAttackOptionsText();

		boolean autoRetaliateSelected = plugin.getAutoRetaliateStatus();
		boolean getNPCAttackSelected = plugin.getNPCAttackOptions();

		if (autoRetaliateSelected && config.autoRetaliateWarn())
		{
			panelComponent.getChildren().add(TitleComponent.builder()
				.text(autoRetaliateWarning)
				.color(config.autoRetaliateColor())
				.build());
		}
		if (!autoRetaliateSelected && config.autoRetaliateOffWarn())
		{
			panelComponent.getChildren().add(TitleComponent.builder()
				.text(autoRetaliateOffWarning)
				.color(config.autoRetaliateOffColor())
				.build());
		}

		if (getNPCAttackSelected && config.npcAttackOptionsWarn())
		{
			panelComponent.getChildren().add(TitleComponent.builder()
				.text(npcAttackWarning)
				.color(config.npcAttackOptionsColor())
				.build());
		}

		// Calculate the preferred size based on the longest possible text
		String longestText = getLongestText(autoRetaliateWarning, autoRetaliateOffWarning, npcAttackWarning);
		panelComponent.setPreferredSize(new Dimension(
			graphics.getFontMetrics().stringWidth(longestText) + 10,
			graphics.getFontMetrics().getHeight()));

		return super.render(graphics);
	}

	private String getLongestText(String... texts)
	{
		String longest = "";
		for (String text : texts)
		{
			if (text.length() > longest.length())
			{
				longest = text;
			}
		}
		return longest;
	}
}