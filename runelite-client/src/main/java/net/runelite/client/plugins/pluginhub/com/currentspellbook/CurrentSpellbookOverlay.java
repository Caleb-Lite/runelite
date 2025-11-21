package net.runelite.client.plugins.pluginhub.com.currentspellbook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.util.ColorUtil;

import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class CurrentSpellbookOverlay extends OverlayPanel {
    private final com.currentspellbook.CurrentSpellbookConfig config;
    private final CurrentSpellbookPlugin plugin;

    @Inject
    private CurrentSpellbookOverlay(Client client, CurrentSpellbookConfig config, CurrentSpellbookPlugin plugin)
    {
        super(plugin);
        this.config = config;
        this.plugin = plugin;

        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Current spellbook overlay."));
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        int spellbook = plugin.getCurrentSpellbook();
        final Color strColor = getTextColor(spellbook);
        graphics.setFont(new Font(FontManager.getRunescapeFont().getName(), Font.PLAIN, config.fontSize()));

        String str = ColorUtil.prependColorTag(getName(spellbook), strColor);

		if (config.prependWith().isBlank())
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left(str)
				.leftColor(strColor)
				.build());
		} else {
			panelComponent.getChildren().add(LineComponent.builder()
				.left(config.prependWith() + " " + str)
				.leftColor(strColor)
				.build());
		}

        return super.render(graphics);
    }

    private String getName(int spellbook)
    {
        switch (spellbook)
        {
            case 0: return "Standard";
            case 1: return "Ancient";
            case 2: return "Lunar";
            case 3: return "Arceuus";
            default: return "Unknown";
        }
    }

    private Color getTextColor(int spellbook)
    {
        if (!config.changeColors())
        {
            return Color.WHITE;
        }
        switch (spellbook)
        {
            case 0: return new Color(196, 169, 132);
            case 1: return new Color(169, 132, 196);
            case 2: return Color.WHITE;
            case 3: return Color.CYAN;
            default: return Color.RED;
        }
    }
}
