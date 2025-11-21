package net.runelite.client.plugins.pluginhub.com.speshkitty.giantsfoundryitemvalues;

import net.runelite.api.ItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.components.TextComponent;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.awt.*;
import java.util.HashMap;

public class ItemBarValuesOverlay extends WidgetItemOverlay {

    private final ItemBarValuesPlugin itemBarValuesPlugin;
    private final ItemBarValuesConfig config;
    private final Logger log;
    private final ItemManager itemManager;

    private final HashMap<Integer, Integer> itemBarValues = new HashMap<>();

    @Inject
    ItemBarValuesOverlay(ItemBarValuesPlugin itemBarValuesPlugin, ItemBarValuesConfig config, ItemManager itemManager)
    {
        this.itemBarValuesPlugin = itemBarValuesPlugin;
        this.config = config;
        this.log = itemBarValuesPlugin.getLogger();
        this.itemManager = itemManager;

        showOnInventory();
        showOnBank();
        InputItems();
    }

    private void InputItems(){
        //region BRONZE
        itemBarValues.put(ItemID.BRONZE_SCIMITAR, 1);
        itemBarValues.put(ItemID.BRONZE_LONGSWORD, 1);
        itemBarValues.put(ItemID.BRONZE_FULL_HELM, 1);
        itemBarValues.put(ItemID.BRONZE_SQ_SHIELD, 1);
        itemBarValues.put(ItemID.BRONZE_CLAWS, 1);
        itemBarValues.put(ItemID.BRONZE_WARHAMMER, 2);
        itemBarValues.put(ItemID.BRONZE_BATTLEAXE, 2);
        itemBarValues.put(ItemID.BRONZE_CHAINBODY, 2);
        itemBarValues.put(ItemID.BRONZE_KITESHIELD, 2);
        itemBarValues.put(ItemID.BRONZE_2H_SWORD, 2);
        itemBarValues.put(ItemID.BRONZE_PLATELEGS, 2);
        itemBarValues.put(ItemID.BRONZE_PLATESKIRT, 2);
        itemBarValues.put(ItemID.BRONZE_PLATEBODY, 4);
        //endregion
        //region IRON
        itemBarValues.put(ItemID.IRON_SCIMITAR, 1);
        itemBarValues.put(ItemID.IRON_LONGSWORD, 1);
        itemBarValues.put(ItemID.IRON_FULL_HELM, 1);
        itemBarValues.put(ItemID.IRON_SQ_SHIELD, 1);
        itemBarValues.put(ItemID.IRON_CLAWS, 1);
        itemBarValues.put(ItemID.IRON_WARHAMMER, 2);
        itemBarValues.put(ItemID.IRON_BATTLEAXE, 2);
        itemBarValues.put(ItemID.IRON_CHAINBODY, 2);
        itemBarValues.put(ItemID.IRON_KITESHIELD, 2);
        itemBarValues.put(ItemID.IRON_2H_SWORD, 2);
        itemBarValues.put(ItemID.IRON_PLATELEGS, 2);
        itemBarValues.put(ItemID.IRON_PLATESKIRT, 2);
        itemBarValues.put(ItemID.IRON_PLATEBODY, 4);
        //endregion
        //region STEEL
        itemBarValues.put(ItemID.STEEL_SCIMITAR, 1);
        itemBarValues.put(ItemID.STEEL_LONGSWORD, 1);
        itemBarValues.put(ItemID.STEEL_FULL_HELM, 1);
        itemBarValues.put(ItemID.STEEL_SQ_SHIELD, 1);
        itemBarValues.put(ItemID.STEEL_CLAWS, 1);
        itemBarValues.put(ItemID.STEEL_WARHAMMER, 2);
        itemBarValues.put(ItemID.STEEL_BATTLEAXE, 2);
        itemBarValues.put(ItemID.STEEL_CHAINBODY, 2);
        itemBarValues.put(ItemID.STEEL_KITESHIELD, 2);
        itemBarValues.put(ItemID.STEEL_2H_SWORD, 2);
        itemBarValues.put(ItemID.STEEL_PLATELEGS, 2);
        itemBarValues.put(ItemID.STEEL_PLATESKIRT, 2);
        itemBarValues.put(ItemID.STEEL_PLATEBODY, 4);
        //endregion
        //region MITHRIL
        itemBarValues.put(ItemID.MITHRIL_SCIMITAR, 1);
        itemBarValues.put(ItemID.MITHRIL_LONGSWORD, 1);
        itemBarValues.put(ItemID.MITHRIL_FULL_HELM, 1);
        itemBarValues.put(ItemID.MITHRIL_SQ_SHIELD, 1);
        itemBarValues.put(ItemID.MITHRIL_CLAWS, 1);
        itemBarValues.put(ItemID.MITHRIL_WARHAMMER, 2);
        itemBarValues.put(ItemID.MITHRIL_BATTLEAXE, 2);
        itemBarValues.put(ItemID.MITHRIL_CHAINBODY, 2);
        itemBarValues.put(ItemID.MITHRIL_KITESHIELD, 2);
        itemBarValues.put(ItemID.MITHRIL_2H_SWORD, 2);
        itemBarValues.put(ItemID.MITHRIL_PLATELEGS, 2);
        itemBarValues.put(ItemID.MITHRIL_PLATESKIRT, 2);
        itemBarValues.put(ItemID.MITHRIL_PLATEBODY, 4);
        //endregion
        //region ADAMANT
        itemBarValues.put(ItemID.ADAMANT_SCIMITAR, 1);
        itemBarValues.put(ItemID.ADAMANT_LONGSWORD, 1);
        itemBarValues.put(ItemID.ADAMANT_FULL_HELM, 1);
        itemBarValues.put(ItemID.ADAMANT_SQ_SHIELD, 1);
        itemBarValues.put(ItemID.ADAMANT_CLAWS, 1);
        itemBarValues.put(ItemID.ADAMANT_WARHAMMER, 2);
        itemBarValues.put(ItemID.ADAMANT_BATTLEAXE, 2);
        itemBarValues.put(ItemID.ADAMANT_CHAINBODY, 2);
        itemBarValues.put(ItemID.ADAMANT_KITESHIELD, 2);
        itemBarValues.put(ItemID.ADAMANT_2H_SWORD, 2);
        itemBarValues.put(ItemID.ADAMANT_PLATELEGS, 2);
        itemBarValues.put(ItemID.ADAMANT_PLATESKIRT, 2);
        itemBarValues.put(ItemID.ADAMANT_PLATEBODY, 4);
        //endregion
        //region RUNE
        itemBarValues.put(ItemID.RUNE_SCIMITAR, 1);
        itemBarValues.put(ItemID.RUNE_LONGSWORD, 1);
        itemBarValues.put(ItemID.RUNE_FULL_HELM, 1);
        itemBarValues.put(ItemID.RUNE_SQ_SHIELD, 1);
        itemBarValues.put(ItemID.RUNE_CLAWS, 1);
        itemBarValues.put(ItemID.RUNE_WARHAMMER, 2);
        itemBarValues.put(ItemID.RUNE_BATTLEAXE, 2);
        itemBarValues.put(ItemID.RUNE_CHAINBODY, 2);
        itemBarValues.put(ItemID.RUNE_KITESHIELD, 2);
        itemBarValues.put(ItemID.RUNE_2H_SWORD, 2);
        itemBarValues.put(ItemID.RUNE_PLATELEGS, 2);
        itemBarValues.put(ItemID.RUNE_PLATESKIRT, 2);
        itemBarValues.put(ItemID.RUNE_PLATEBODY, 4);
        //endregion
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
        if(!itemBarValuesPlugin.ShouldDrawInfo()) {
            return;
        }

        if(!itemBarValues.containsKey(itemId)) {
            return;
        }

        graphics.setFont(FontManager.getRunescapeSmallFont());

        final Rectangle bounds = widgetItem.getCanvasBounds();
        final TextComponent textComponent = new TextComponent();

        int xPos = 0;
        int yPos = 0;

        switch(config.priceDisplayMode()){
            case TOP_LEFT:
                xPos = bounds.x;
                yPos = bounds.y + 10;
                break;
            case TOP_RIGHT:
                xPos = bounds.x + bounds.width - 5;
                yPos = bounds.y + 10;
                break;
            case BOTTOM_LEFT:
                xPos = bounds.x;
                yPos = bounds.y + bounds.height;
                break;
            case BOTTOM_RIGHT:
                xPos = bounds.x + bounds.width - 5;
                yPos = bounds.y + bounds.height;
                break;
        }
        textComponent.setPosition(new Point(xPos, yPos));
        textComponent.setText(String.valueOf(itemBarValues.get(itemId)));
        textComponent.setColor(Color.WHITE);
        textComponent.render(graphics);

    }

}
