package net.runelite.client.plugins.pluginhub.com.toaspellbookreminder;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.api.Point;

import java.awt.*;
import java.util.Objects;

import static net.runelite.client.ui.overlay.OverlayUtil.renderTextLocation;

@Slf4j
public class ToaSpellbookReminderOverlay extends Overlay {
    private final ToaSpellbookReminderPlugin plugin;
    private final ToaSpellbookReminderConfig config;

    @Inject
    private ToaSpellbookReminderOverlay(ToaSpellbookReminderPlugin plugin, ToaSpellbookReminderConfig config) {
        this.config = config;
        this.plugin = plugin;

        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics2D) {
        if (plugin.getRegionId() == 13454) {
            String text = "Current Spellbook: " + plugin.getSpellbook().toUpperCase();
            Point textLocation = plugin.getToaDoor().getCanvasTextLocation(graphics2D, text, 200);

            if (textLocation != null) {
                if (Objects.equals(config.spellbook().toString(), plugin.getSpellbook())) {
                    renderTextLocation(graphics2D, new Point(textLocation.getX(), textLocation.getY()), text, config.correctSpellbook());
                } else {
                    renderTextLocation(graphics2D, new Point(textLocation.getX(), textLocation.getY()), text, config.wrongSpellbook());
                }
            }
        }

        return null;
    }
}
