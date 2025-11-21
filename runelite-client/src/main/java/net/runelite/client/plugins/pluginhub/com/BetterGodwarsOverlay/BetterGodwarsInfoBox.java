package net.runelite.client.plugins.pluginhub.com.BetterGodwarsOverlay;

import net.runelite.api.MenuAction;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BetterGodwarsInfoBox extends InfoBox {
	private final BetterGodwarsGodInfo godInfo;

	public BetterGodwarsInfoBox(BufferedImage image, @Nonnull Plugin plugin, BetterGodwarsGodInfo godInfo) {
		super(image, plugin);
		this.godInfo = godInfo;

		setPriority(InfoBoxPriority.LOW);
		setTooltip(godInfo.getGod().getName());

		getMenuEntries().add(new OverlayMenuEntry(MenuAction.RUNELITE_INFOBOX, "Hide", godInfo.getGod().getName()));
	}

	@Override
	public String getText() {
		return Integer.toString(godInfo.getCurrentKc());
	}

	@Override
	public Color getTextColor() {
		return godInfo.getTextColor();
	}
}
