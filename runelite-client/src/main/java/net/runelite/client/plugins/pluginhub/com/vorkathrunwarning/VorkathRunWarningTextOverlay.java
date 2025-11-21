package net.runelite.client.plugins.pluginhub.com.vorkathrunwarning;

import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class VorkathRunWarningTextOverlay extends OverlayPanel {

    private final VorkathRunWarningConfig config;
    private final  VorkathRunWarningPlugin plugin;

    @Inject
    private VorkathRunWarningTextOverlay(VorkathRunWarningPlugin plugin, VorkathRunWarningConfig config) {
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!this.plugin.isShouldWarn() || !this.config.displayTextWarning()) {
            return null;
        }
        this.panelComponent.getChildren().add(TitleComponent.builder().text("Run Enabled!").color(this.config.textColor()).build());
        return super.render(graphics);
    }
}
