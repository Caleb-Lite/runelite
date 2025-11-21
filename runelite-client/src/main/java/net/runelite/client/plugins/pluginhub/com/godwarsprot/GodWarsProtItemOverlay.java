package net.runelite.client.plugins.pluginhub.com.godwarsprot;

import com.godwarsprot.items.*;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GodWarsProtItemOverlay extends WidgetItemOverlay {

    private final GodWarsProtConfig config;
    private final ItemManager itemManager;

    private final Armadyl armadyl;
    private final Bandos bandos;
    private final Zamorak zamorak;
    private final Saradomin saradomin;
    private final Ancient ancient;



    @Inject
    public GodWarsProtItemOverlay(ItemManager itemManager, GodWarsProtConfig config, Armadyl armadyl,Bandos bandos,Zamorak zamorak, Saradomin saradomin, Ancient ancient){
            this.config = config;
            this.armadyl = armadyl;
            this.bandos = bandos;
            this.zamorak = zamorak;
            this.saradomin = saradomin;
            this.ancient = ancient;
            this.itemManager = itemManager;
            showOnInventory();
            showOnBank();



    }

    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget)
    {
        if (!config.itemHighlight()) {
            return;
        }
        Color colorToUse = checkGod(itemId);
        if (colorToUse == null){
            return;
        }
        Rectangle bounds = itemWidget.getCanvasBounds();
        if(config.highlightStyle() == GodWarsProtHighlightStyle.OUTLINE || config.highlightStyle() == GodWarsProtHighlightStyle.BOTH){
            final BufferedImage outline = itemManager.getItemOutline(itemId, itemWidget.getQuantity(), colorToUse);
            graphics.drawImage(outline, (int) bounds.getX(), (int) bounds.getY(), null);
        }
        // Underline is taken from Inventory Tags plugin.
        if(config.highlightStyle() == GodWarsProtHighlightStyle.UNDERLINE || config.highlightStyle() == GodWarsProtHighlightStyle.BOTH){
            int heightOffSet = (int) bounds.getY() + (int) bounds.getHeight() + 2;
            graphics.setColor(colorToUse);
            graphics.drawLine((int) bounds.getX(), heightOffSet, (int) bounds.getX() + (int) bounds.getWidth(), heightOffSet);

        }




    }

    private Color checkGod(int itemId){
        if (armadyl.checkList(itemId)){
            return Color.white;
        }
        if (zamorak.checkList(itemId)){
            return Color.red;
        }
        if (ancient.checkList(itemId)){
            return Color.MAGENTA;
        }
        if (bandos.checkList(itemId)){
            return Color.ORANGE;
        }
        if (saradomin.checkList(itemId)){
            return Color.blue;
        }
        return null;



    }


}
