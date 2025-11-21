package net.runelite.client.plugins.pluginhub.com.CosRoom;

import com.CosRoom.items.*;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CostumeHighlightOverlay extends WidgetItemOverlay {
    private final ItemManager itemMan;
    private final CosRoomConfig config;
    private final ToyBox toyBox;
    private final ArmorCase armorCase;
    private final CapeRack capeRack;
    private final FancyDressBox fancyDressBox;
    private final MagicWardrobe magicWardrobe;
    private final TreasureChest treasureChest;

    @Inject
    private CostumeHighlightOverlay(ItemManager itemMan, CosRoomPlugin plugin, CosRoomConfig config)
    {
        this.config = config;
        this.itemMan = itemMan;
        this.toyBox = new ToyBox();
        this.armorCase = new ArmorCase();
        this.capeRack = new CapeRack();
        this.fancyDressBox = new FancyDressBox();
        this.magicWardrobe = new MagicWardrobe();
        this.treasureChest = new TreasureChest();
        showOnBank();
        if(config.outsideBankEnabled()) {
            showOnInterfaces(InterfaceID.SHOP, InterfaceID.SHOP_INVENTORY);
            showOnInventory();
        }
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemID, WidgetItem itemWidget)
    {
        Color highlightColor = config.getHouseColor();
        if(checkStorability(itemID))
        {
            Rectangle bounds = itemWidget.getCanvasBounds();
            final BufferedImage outline = itemMan.getItemOutline(itemID, itemWidget.getQuantity(), highlightColor);
            graphics.drawImage(outline, (int) bounds.getX(), (int) bounds.getY(), null);
        }
    }

    private boolean checkStorability(int itemID)
    {
        if(config.toyBoxEnabled() && toyBox.checkList(itemID)) return true;
        if(config.armorCaseEnabled() && armorCase.checkList(itemID)) return true;
        if(config.CapeRackEnabled() && capeRack.checkList(itemID)) return true;
        if(config.FancyDressBoxEnabled() && fancyDressBox.checkList(itemID)) return true;
        if(config.MagicWardrobeEnabled() && magicWardrobe.checkList(itemID)) return true;
        if(config.TreasureChestEnabled() && treasureChest.checkList(itemID)) return true;
        return false;
    }
}

