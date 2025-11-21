package net.runelite.client.plugins.pluginhub.dev.yequi.emotes;

import com.google.common.base.Strings;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Map;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.components.TextComponent;

public class EmoteOverlay extends Overlay
{
	private final EmotesPlugin plugin;
	private final EmotesConfig config;
	private final Client client;
	private final TextComponent textComponent = new TextComponent();

	@Inject
	private EmoteOverlay(EmotesPlugin plugin, EmotesConfig config, Client client)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		this.plugin = plugin;
		this.config = config;
		this.client = client;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		Widget emoteContainer = client.getWidget(ComponentID.EMOTES_EMOTE_CONTAINER);
		if (emoteContainer == null || emoteContainer.isHidden())
		{
			return null;
		}
		Widget emoteWindow = client.getWidget(ComponentID.EMOTES_WINDOW);
		if (emoteWindow == null)
		{
			return null;
		}
		Map<Integer, EmoteHighlight> highlights = plugin.getHighlights();
		int[] spriteIds = config.emoteToScrollTo().getSpriteIds();
		for (Widget emoteWidget : emoteContainer.getDynamicChildren())
		{
			// scroll to the specified item
			for (int spriteId : spriteIds)
			{
				if (spriteId == emoteWidget.getSpriteId())
				{
					plugin.scrollToHighlight(emoteWidget);
					break;
				}
			}
			// highlight the emote
			Emote emote = EmotesPlugin.emoteFromWidget(emoteWidget);
			if (emote != null)
			{
				EmoteHighlight value = highlights.get(emote.ordinal());
				if (value != null)
				{
					highlight(graphics, value, emoteWidget, emoteWindow);
				}
			}
		}
		return null;
	}

	private void highlight(Graphics2D graphics, EmoteHighlight value, Widget emoteWidget, Widget container)
	{
		Point canvasLocation = emoteWidget.getCanvasLocation();
		if (canvasLocation == null)
		{
			return;
		}

		Point windowLocation = container.getCanvasLocation();
		if (windowLocation.getY() > canvasLocation.getY() + emoteWidget.getHeight()
			|| windowLocation.getY() + container.getHeight() < canvasLocation.getY())
		{
			return;
		}

		// Visible area of widget
		Area widgetArea = new Area(
			new Rectangle(
				canvasLocation.getX(),
				Math.max(canvasLocation.getY(), windowLocation.getY()),
				emoteWidget.getWidth(),
				Math.min(
					Math.min(windowLocation.getY() + container.getHeight() - canvasLocation.getY(), emoteWidget.getHeight()),
					Math.min(canvasLocation.getY() + emoteWidget.getHeight() - windowLocation.getY(), emoteWidget.getHeight()))
			));

		Color fillColor = config.rememberEmoteColors() ? value.getFillColor() : config.fillColor();
		Color borderColor = config.rememberEmoteColors() ? value.getBorderColor() : config.borderColor();
		Color borderHoverColor = borderColor.darker();
		OverlayUtil.renderHoverableArea(graphics, widgetArea, client.getMouseCanvasPosition(), fillColor, borderColor,
			borderHoverColor);

		String text = value.getLabel();
		if (!config.displayLabels() || Strings.isNullOrEmpty(text)
			|| emoteWidget.getHeight() + canvasLocation.getY() > windowLocation.getY() + container.getHeight())
		{
			return;
		}
		Color textColor = config.rememberEmoteColors() ? value.getTextColor() : config.labelColor();
		FontMetrics fontMetrics = graphics.getFontMetrics();
		textComponent.setPosition(new java.awt.Point(
			canvasLocation.getX() + emoteWidget.getWidth() / 2 - fontMetrics.stringWidth(text) / 2,
			canvasLocation.getY() + emoteWidget.getHeight()));
		textComponent.setText(text);
		textComponent.setColor(textColor);
		textComponent.render(graphics);
	}
}
