package net.runelite.client.plugins.pluginhub.com.clairemira;

import java.awt.*;
import java.text.NumberFormat;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class UntrimmedSlayerCapeHelperOverlayPanel extends OverlayPanel {
    private final UntrimmedSlayerCapeHelperPlugin plugin;
    private final UntrimmedSlayerCapeHelperConfig config;

    @Inject
    public UntrimmedSlayerCapeHelperOverlayPanel(Client client, UntrimmedSlayerCapeHelperPlugin plugin, UntrimmedSlayerCapeHelperConfig config)
    {
        super(plugin);
        this.plugin = plugin;
        this.config = config;

        panelComponent.setPreferredSize(new Dimension(110,100));
        setPosition(OverlayPosition.TOP_LEFT);
    }

    public void renderSlayerExpRemaining()
    {
        int slayerExpRemaining = plugin.getSlayerExpRemaining();
        int exp99 = Experience.getXpForLevel(99);
        String expStr = NumberFormat.getInstance().format(slayerExpRemaining);
        Color expTextColor = slayerExpRemaining < exp99 ? Color.ORANGE : Color.GREEN;

        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Slayer XP to 99")
                .color(Color.WHITE)
                .build());

        panelComponent.getChildren().add(TitleComponent.builder()
                .text(expStr)
                .color(expTextColor)
                .build());
    }

    public void renderProjectedHitpointsExp()
    {
        int projectedHitpointsExp = plugin.getProjectedHitpointsExp();
        int exp99 = Experience.getXpForLevel(99);
        String expStr = NumberFormat.getInstance().format(projectedHitpointsExp);
        Color expTextColor = projectedHitpointsExp < exp99 ? Color.GREEN : Color.RED;

        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Projected HP XP")
                .color(Color.WHITE)
                .build());

        panelComponent.getChildren().add(TitleComponent.builder()
                .text(expStr)
                .color(expTextColor)
                .build());
    }

    public void renderCannonExpRemaining()
    {
        int slayerOnlyExpRemaining = plugin.getSlayerOnlyExpRemaining();
        String expStr = NumberFormat.getInstance().format(slayerOnlyExpRemaining);
        Color expTextColor = slayerOnlyExpRemaining <= 0 ? Color.GREEN : Color.RED;

        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Slayer Only XP")
                .color(Color.WHITE)
                .build());

        panelComponent.getChildren().add(TitleComponent.builder()
                .text(expStr)
                .color(expTextColor)
                .build());
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (config.showSlayerExpRemaining()) {
            renderSlayerExpRemaining();
        }

        if (config.showProjectedHitpointsExp()) {
            renderProjectedHitpointsExp();
        }

        if (config.showSlayerOnlyExp()) {
            renderCannonExpRemaining();
        }

        return super.render(graphics);
    }
}
