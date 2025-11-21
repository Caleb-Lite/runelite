package net.runelite.client.plugins.pluginhub.com.afkhellratbehemoth;

import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.NumberFormat;
import java.util.Collections;


public class AfkHellratBehemothOverlay extends OverlayPanel {
    private final Client client;
    private final AfkHellratBehemothPlugin plugin;
    private final AfkHellratBehemothConfig config;


    @Inject
    private AfkHellratBehemothOverlay(Client client, AfkHellratBehemothPlugin plugin,
                                      AfkHellratBehemothConfig config) {
        super(plugin);
        setPosition(OverlayPosition.BOTTOM_LEFT);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        getMenuEntries().add(new OverlayMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OverlayManager.OPTION_CONFIGURE,
                "Afk Behemoth Hell-Rat Overlay"));
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (AfkHellratBehemothPlugin.cat_in_combat) {
            setPreferredSize(new Dimension(150, 88));

            LineComponent titleline = LineComponent.builder()
                            .leftFont(graphics.getFont().deriveFont(Collections.singletonMap(
                                    TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD)))
                            .left("Cat's Success Rate")
                            .build();
            panelComponent.getChildren().add(titleline);

            // get cat type in advance since I want to  make fun of you if you're using a kitten
            String cat_panel_desc = plugin.catName;
            if (cat_panel_desc.equals("Kitten")) {
                cat_panel_desc += "... but why??";
                setPreferredSize(new Dimension(175, 88));
            }
            LineComponent cat_desc = LineComponent.builder()
                    .left("Cat Type:")
                    .right(cat_panel_desc)
                    .build();
            panelComponent.getChildren().add(cat_desc);

            // convert success rate to percent
            NumberFormat percentageFormat = NumberFormat.getPercentInstance();
            percentageFormat.setMinimumFractionDigits(2);
            String success_rate_string = percentageFormat.format(plugin.successRate);

            LineComponent success_rate_line = LineComponent.builder()
                    .left("Success Rate:")
                    .right(success_rate_string)
                    .build();
            panelComponent.getChildren().add(success_rate_line);
        }
        return super.render(graphics);
    }
}

