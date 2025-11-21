package net.runelite.client.plugins.pluginhub.com.localtime;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TextComponent;

import javax.inject.Inject;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeOverlay extends Overlay {

    private final LocalTimeConfig config;
    private String timeFormat;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public LocalTimeOverlay(LocalTimeConfig config) {
        setResizable(true);
        setPreferredPosition(OverlayPosition.TOP_RIGHT);
        setSnappable(true);

        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        // This needs improving. Don't do this on every render!
        this.timeFormat = config.use24Hr() ? "HH:mm" : "h:mm";
        if (config.showSeconds()) {
            this.timeFormat += ":ss";
        }
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern(this.timeFormat));

        panelComponent.getChildren().clear();
        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(formattedTime) + 8,
                0
        ));
        panelComponent.getChildren().add(
                LineComponent
                        .builder()
                        .left(formattedTime)
                        .build()
        );

        return panelComponent.render(graphics);
    }
}
