package net.runelite.client.plugins.pluginhub.com.bankvaluechanges;

import java.awt.*;
import java.text.DecimalFormat;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import javax.inject.Inject;


@Slf4j
public class BankValueChangesOverlay extends WidgetItemOverlay {
    private final Client client;
    private final BankValueChangesPlugin plugin;
    private final BankValueChangesConfig config;

    @Inject
    BankValueChangesOverlay(Client client, BankValueChangesPlugin plugin, BankValueChangesConfig config) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        showOnBank();
        showOnInterfaces(
            InterfaceID.BANK_INVENTORY
        );
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
        if (!config.showOverlay()) {
            return;
        }

        // Don't bother drawing anything for gold or plat tokens
        if (itemId == 995 || itemId == 13204) {
            return;
        }

        if (plugin.valueChanges.containsKey(itemId)) {
            Color fillColor;
            Double percentage = plugin.valueChanges.get(itemId);

            if (percentage > 0) {
                fillColor = new Color(0, 255, 0, 30);
            } else if (percentage < 0) {
                fillColor = new Color(255, 0, 0, 50);
            } else {
                fillColor = new Color(128, 128, 128, 100);
            }

            Rectangle bounds = widgetItem.getCanvasBounds();
            graphics.setColor(fillColor);
            graphics.draw(bounds);
            graphics.fill(bounds);

            DecimalFormat df = new DecimalFormat("#.#");
            String percentageText = df.format(percentage) + "%";
            Color textColor = new Color(255, 255, 255, 255);
            graphics.setColor(textColor);
            graphics.drawString(percentageText, bounds.x, bounds.y + bounds.height);
        }
    }
}
