package net.runelite.client.plugins.pluginhub.com.vardorvishealth;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.util.QuantityFormatter;
import javax.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class VardorvisHealTrackerOverlay extends OverlayPanel {
    private final VardorvisHealTrackerPlugin plugin;

    @Inject
    public VardorvisHealTrackerOverlay(VardorvisHealTrackerPlugin plugin) {
        this.plugin = plugin;

    }

    @Inject
    private VardorvisHealthConfig config;


    @Override
    public Dimension render(Graphics2D graphics) {

        if ((plugin.getTotalHealing()==0) || !config.showOverlay()) {
            return null;
        }

        String left = "Total Healing:";
        String right = QuantityFormatter.formatNumber(plugin.getTotalHealing());
        panelComponent.getChildren().add(
                LineComponent.builder()
                        .left(left)
                        .right(right)
                        .build());


        return super.render(graphics);
    }
}

