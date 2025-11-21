package net.runelite.client.plugins.pluginhub.io.github.mmagicala.gnomeRestaurant.overlay;

import net.runelite.client.plugins.pluginhub.io.github.mmagicala.gnomeRestaurant.GnomeRestaurantPlugin;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

public class GnomeRestaurantOverlay extends OverlayPanel {

    // Overlay contents
    private final GnomeRestaurantPlugin plugin;
    private final List<OverlayTableEntry> stepIngredientsOverlayTable;
    private final List<OverlayTableEntry> futureRawIngredientsOverlayTable;

    private static final int PADDING = 10;

    @Inject
    public GnomeRestaurantOverlay(
            GnomeRestaurantPlugin plugin,
            List<OverlayTableEntry> stepIngredientsOverlayTable,
            List<OverlayTableEntry> futureRawIngredientsOverlayTable
    ) {
        super(plugin);
        this.plugin = plugin;
        this.stepIngredientsOverlayTable = stepIngredientsOverlayTable;
        this.futureRawIngredientsOverlayTable = futureRawIngredientsOverlayTable;

        // Padding
        panelComponent.setBorder(new Rectangle(PADDING, PADDING, PADDING, PADDING));

        // Gap between panel items
        panelComponent.setGap(new Point(0, ComponentConstants.STANDARD_BORDER));
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        // Header
        var header = plugin.overlayHeader();
        String headerText = "Step " + header.stepNum + "/" + header.totalSteps + ": " + header.instruction;

        LineComponent headerComponent = LineComponent.builder().left(headerText).build();
        panelComponent.getChildren().add(headerComponent);

        // Overlay tables

        renderOverlayTable(stepIngredientsOverlayTable, "Current Step");

        if (!futureRawIngredientsOverlayTable.isEmpty()) {
            // Only render future ingredients if there are any left
            renderOverlayTable(futureRawIngredientsOverlayTable, "Needed Later");
        }

        return super.render(graphics);
    }

    private void renderOverlayTable(List<OverlayTableEntry> overlayTable, String title) {
        // Table header

        TitleComponent titleComponent = TitleComponent.builder().text(title).build();
        panelComponent.getChildren().add(titleComponent);

        // Table contents

        for (OverlayTableEntry tableEntry : overlayTable) {
            Color ingredientColor;
            if (tableEntry.getInventoryCount() >= tableEntry.getRequiredCount()) {
                ingredientColor = Color.GREEN;
            } else if (tableEntry.getInventoryCount() == 0) {
                ingredientColor = Color.RED;
            } else {
                ingredientColor = Color.YELLOW;
            }

            LineComponent ingredientRow = LineComponent.builder()
                                                       .left(tableEntry.getItemName())
                                                       .leftColor(ingredientColor)
                                                       .right(tableEntry.getInventoryCount() + "/" + tableEntry.getRequiredCount())
                                                       .rightColor(ingredientColor)
                                                       .build();
            panelComponent.getChildren().add(ingredientRow);
        }
    }
}
