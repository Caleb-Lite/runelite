package net.runelite.client.plugins.pluginhub.com.BetterGodwarsOverlay;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class BetterGodwarsOverlayOverlay extends OverlayPanel
{

	private final BetterGodwarsOverlayConfig config;
	private final BetterGodwarsOverlayPlugin plugin;

	@Inject
	private BetterGodwarsOverlayOverlay(BetterGodwarsOverlayPlugin plugin, BetterGodwarsOverlayConfig config)
	{
		super(plugin);
		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(PRIORITY_LOW);

		this.plugin = plugin;
		this.config = config;

		getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Godwars Overlay"));
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		final boolean inGwd = plugin.playerInGwd();
		final boolean shouldShowOverlay = inGwd && config.showOverlay();

		if (shouldShowOverlay) {
			Arrays.stream(plugin.godsInfo).filter(Objects::nonNull).forEach(godInfo -> {
				if (godInfo.getConfigIsHidden()) {
					return;
				}

				final int killCount = godInfo.getCurrentKc();
				final String name = godInfo.getGod().getName();

				panelComponent.getChildren().add(LineComponent.builder()
								.left(config.shortGodNames() ? name.substring(0, 2) : name)
								.leftColor(config.godNameColor())
								.right(Integer.toString(killCount))
								.rightColor(godInfo.getTextColor())
								.build());
			});
		}
		return super.render(graphics);
	}
}

