package net.runelite.client.plugins.pluginhub.com.vorkathrunwarning;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class VorkathRunWarningTintOverlay extends Overlay {

    private final VorkathRunWarningConfig config;
    private final  VorkathRunWarningPlugin plugin;
    private final Client client;

    @Inject
    private VorkathRunWarningTintOverlay(VorkathRunWarningPlugin plugin, VorkathRunWarningConfig config, Client client) {
        this.plugin = plugin;
        this.config = config;
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!this.plugin.isShouldWarn() || !this.config.displayTintWarning()) {
            return null;
        }
        Rectangle screenRect = new Rectangle(0, 0, this.client.getCanvasWidth(), this.client.getCanvasHeight());
        graphics.setColor(this.config.tintColor());
        graphics.fill(screenRect);
        graphics.draw(screenRect);
        TitleComponent pluginIndicator = TitleComponent.builder().text("Vorkath Run Warning Active").build();
        pluginIndicator.setPreferredLocation(new Point(8, 20));
        pluginIndicator.render(graphics);
        return null;
    }
}
