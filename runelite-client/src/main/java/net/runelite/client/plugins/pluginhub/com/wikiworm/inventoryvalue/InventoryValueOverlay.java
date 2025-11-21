package net.runelite.client.plugins.pluginhub.com.wikiworm.inventoryvalue;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import net.runelite.client.util.QuantityFormatter;

import javax.inject.Inject;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

@Slf4j
public class InventoryValueOverlay extends Overlay
{
    private Long inventoryValue = 0L;
    private Long startingValue = Long.MIN_VALUE;
    private Long profitInvValue = 0L;
    private Long profitBankValue = 0L;
    private Color profitInvColor = Color.GREEN;
    private Color profitBankColor = Color.GREEN;
    private final InventoryValueConfig inventoryValueConfig;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private InventoryValueOverlay(InventoryValueConfig config) {
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        this.inventoryValueConfig = config;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        String titleText = "Inventory Value";
        String valueString = inventoryValueConfig.useHighAlchemyValue() ? "HA Price:" : "GE Price:";
        String profitInvString = "Profit (inv):";
        String profitBankString = "Profit (banked):";

        panelComponent.getChildren().clear();

        panelComponent.getChildren().add(TitleComponent.builder()
                .text(titleText)
                .color(Color.GREEN)
                .build());

        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(titleText) + 30,
                0
        ));

        panelComponent.getChildren().add(LineComponent.builder()
                .left(valueString)
                .leftColor(Color.WHITE)
                .right(QuantityFormatter.quantityToStackSize(inventoryValue))
                .rightColor(Color.YELLOW)
                .build());

        if(inventoryValueConfig.displayProfit()) {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left(profitInvString)
                    .leftColor(Color.WHITE)
                    .right(QuantityFormatter.quantityToStackSize(profitInvValue))
                    .rightColor(profitInvColor)
                    .build());
        }

        if(inventoryValueConfig.displayProfit()) {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left(profitBankString)
                    .leftColor(Color.WHITE)
                    .right(QuantityFormatter.quantityToStackSize(profitBankValue))
                    .rightColor(profitBankColor)
                    .build());
        }

        return panelComponent.render(graphics);
    }


    public void updateInventoryValue(final long newInventoryValue, final long newProfitInvValue, final long newProfitBankValue) {
        final Color updateInvProfitColor = newProfitInvValue >= 0 ? Color.GREEN : Color.RED;
        final Color updateBankProfitColor = newProfitBankValue >= 0 ? Color.GREEN : Color.RED;
        SwingUtilities.invokeLater(() -> inventoryValue = newInventoryValue);
        if(inventoryValueConfig.displayProfit()) {
            SwingUtilities.invokeLater(() -> {
                profitBankValue = newProfitBankValue;
                profitInvValue = newProfitInvValue;
                profitInvColor = updateInvProfitColor;
                profitBankColor = updateBankProfitColor;
            });
        }
    }


}