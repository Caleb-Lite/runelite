package net.runelite.client.plugins.pluginhub.io.banna.rl;

import net.runelite.client.plugins.pluginhub.io.banna.rl.domain.NpcLabel;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.NPCManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

@Slf4j
public class NpcLabelsOverlay extends Overlay
{

    private final NpcLabelsPlugin plugin;
    private final NpcLabelsConfig config;

    private final ItemManager itemManager;

    @Inject
    public NpcLabelsOverlay(NpcLabelsPlugin plugin, NpcLabelsConfig config, ItemManager itemManager)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
        this.config = config;
        this.itemManager = itemManager;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (config.showOverlay())
        {
            plugin.getRelevantNpcs().forEach((npc) -> renderLabel(npc, graphics));
        }
        return null;
    }

    private void renderLabel(NPC npc, Graphics2D graphics) {
        if (npc.isDead()) {
            return;
        }

        NpcLabel label = plugin.getNpcLabel(npc);
        if (label != null) {
            if (label.getLabel() != null) {
                Point textCanvasLocation = npc.getCanvasTextLocation(graphics, label.getLabel(), config.labelHeight());
                if (textCanvasLocation != null) {
                    Point dropShadow = new Point(textCanvasLocation.getX() + 1, textCanvasLocation.getY() + 1);
                    if (config.dropShadow()) {
                        OverlayUtil.renderTextLocation(graphics, dropShadow, label.getLabel(), Color.BLACK);
                    }
                    OverlayUtil.renderTextLocation(graphics, textCanvasLocation, label.getLabel(), label.getColor());

                }
            }
            if (label.getItemIconId() != null) {
                Image itemIcon = itemManager.getImage(label.getItemIconId());
                BufferedImage bufferedItemIcon = toBufferedImage(itemIcon);
                Point imageCanvasLocation = npc.getCanvasImageLocation(bufferedItemIcon, config.iconHeight());
                OverlayUtil.renderImageLocation(graphics, imageCanvasLocation, bufferedItemIcon);
            }
        }
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
