package net.runelite.client.plugins.pluginhub.com.thralldamagecounter;

import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;
import java.text.DecimalFormat;

import static net.runelite.api.MenuAction.RUNELITE_OVERLAY;

public class ThrallDamageCounterOverlay extends OverlayPanel {
    private static final DecimalFormat DPS_FORMAT = new DecimalFormat("#0.0");
    private static final int PANEL_WIDTH_OFFSET = 0; // switched to 0 from 10 to match the other overlays

    private final ThrallDamageCounterPlugin thrallDamageCounterPlugin;
    private final ThrallDamageCounterConfig thrallDamageCounterConfig;
    private static final String THRALL_COUNTER_RESET = "Reset";

    @Inject
    ThrallDamageCounterOverlay(ThrallDamageCounterPlugin thrallDamageCounterPlugin, ThrallDamageCounterConfig thrallDamageCounterConfig)
    {
        super(thrallDamageCounterPlugin);
        this.thrallDamageCounterPlugin = thrallDamageCounterPlugin;
        this.thrallDamageCounterConfig = thrallDamageCounterConfig;

        setPosition(OverlayPosition.TOP_LEFT);
        addMenuEntry(RUNELITE_OVERLAY, THRALL_COUNTER_RESET, "Thrall damage counter", e -> thrallDamageCounterPlugin.resetTracking());
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (thrallDamageCounterConfig.overlayHide())
        {
            return null;
        }

        DamageMember dpsMember = thrallDamageCounterPlugin.activeThrallDamage;

        if (dpsMember == null || (thrallDamageCounterConfig.overlayAutoHide() && DamageMember.overlayHide))
        {
            return null;
        }

        boolean showDamage = thrallDamageCounterConfig.showDamage();

        final String title = "Thrall Damage Counter";
        panelComponent.getChildren().add(
                TitleComponent.builder()
                        .text(title)
                        .build());

        int maxWidth = ComponentConstants.STANDARD_WIDTH;
        panelComponent.setPreferredSize(new Dimension(maxWidth + PANEL_WIDTH_OFFSET, 0));

        panelComponent.getChildren().add(
                LineComponent.builder()
                        .left(dpsMember.getName())
                        .right(showDamage ? Integer.toString(dpsMember.getDamage()) : DPS_FORMAT.format(dpsMember.getDps()))
                        .build());

        return super.render(graphics);
    }
}
