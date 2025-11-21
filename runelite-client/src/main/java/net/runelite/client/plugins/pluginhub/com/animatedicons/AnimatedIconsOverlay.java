package net.runelite.client.plugins.pluginhub.com.animatedicons;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.swing.ImageIcon;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

@Slf4j
public class AnimatedIconsOverlay extends WidgetItemOverlay
{
    Map<Integer, Image> images = new HashMap<>();
    @Inject
    private AnimatedIconsOverlay()
    {
        showOnEquipment();
        showOnInventory();
        showOnBank();
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget)
    {
        final Icon icon = Icon.getAnimatedIcon(itemId);
        if (icon != null)
        {
            Rectangle bounds = itemWidget.getCanvasBounds();
            Integer defaultItemID = new Integer(icon.getDefaultID());
            Image animatedIcon;
            if (images.containsKey(defaultItemID))
            {
                animatedIcon = images.get(defaultItemID);
            }
            else
            {
                animatedIcon = new ImageIcon(AnimatedIconsPlugin.class.getClassLoader().getResource(defaultItemID.toString() + ".gif")).getImage();
                images.put(defaultItemID, animatedIcon);
            }
            graphics.drawImage(animatedIcon, (int) bounds.getX(), (int) bounds.getY(), null);
        }
    }
}
