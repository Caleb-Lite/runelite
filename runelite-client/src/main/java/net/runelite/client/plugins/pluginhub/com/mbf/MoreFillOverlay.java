package net.runelite.client.plugins.pluginhub.com.mbf;


import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

@Slf4j
public class MoreFillOverlay extends WidgetItemOverlay
{

    private final MoreFillConfig config;
    private final BufferedImage empty = ImageUtil.loadImageResource(MoreFillPlugin.class,"/empty_tile.png");
    private final BufferedImage addydot = ImageUtil.loadImageResource(MoreFillPlugin.class,"/addy_dot.png");
    private final BufferedImage addysq = ImageUtil.loadImageResource(MoreFillPlugin.class,"/addy_sq.png");
    private final BufferedImage addyswirl = ImageUtil.loadImageResource(MoreFillPlugin.class,"/addy_swirl.png");
    private final BufferedImage bronzedot = ImageUtil.loadImageResource(MoreFillPlugin.class, "/bronze_dot.png");
    private final BufferedImage bronzesq = ImageUtil.loadImageResource(MoreFillPlugin.class, "/bronze_sq.png");
    private final BufferedImage bronzeswirl = ImageUtil.loadImageResource(MoreFillPlugin.class, "/bronze_swirl.png");
    private final BufferedImage dragondot = ImageUtil.loadImageResource(MoreFillPlugin.class, "/dragon_dot.png");
    private final BufferedImage dragonsq = ImageUtil.loadImageResource(MoreFillPlugin.class, "/dragon_sq.png");
    private final BufferedImage dragonswirl = ImageUtil.loadImageResource(MoreFillPlugin.class, "/dragon_swirl.png");
    private final BufferedImage golddot = ImageUtil.loadImageResource(MoreFillPlugin.class, "/gold_dot.png");
    private final BufferedImage goldsq = ImageUtil.loadImageResource(MoreFillPlugin.class, "/gold_sq.png");
    private final BufferedImage goldswirl = ImageUtil.loadImageResource(MoreFillPlugin.class, "/gold_swirl.png");
    private final BufferedImage runedot = ImageUtil.loadImageResource(MoreFillPlugin.class, "/rune_dot.png");
    private final BufferedImage runesq = ImageUtil.loadImageResource(MoreFillPlugin.class, "/rune_sq.png");
    private final BufferedImage runeswirl = ImageUtil.loadImageResource(MoreFillPlugin.class, "/rune_swirl.png");
    private final BufferedImage bronzestandard = ImageUtil.loadImageResource(MoreFillPlugin.class, "/bronze_standard.png");
    private final BufferedImage goldstandard = ImageUtil.loadImageResource(MoreFillPlugin.class, "/gold_standard.png");
    private final BufferedImage addystandard = ImageUtil.loadImageResource(MoreFillPlugin.class, "/addy_standard.png");
    private final BufferedImage runestandard = ImageUtil.loadImageResource(MoreFillPlugin.class, "/rune_standard.png");
    private final BufferedImage dragonstandard = ImageUtil.loadImageResource(MoreFillPlugin.class, "/dragon_standard.png");


    @Inject
    private MoreFillOverlay(MoreFillConfig config)
    {
        this.config = config;
        showOnBank();
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget)
    {

        if (config.overlayType() == MoreFillOverlayTypes.EMPTY){
            // Only need to hide the model. no overlay needed
            return;
        }

        // Get the proper image we should use as overlay
        BufferedImage imageToShow = getImage(config.overlayTheme(), config.overlayType());

        if (itemId == ItemID.BANK_FILLER){
            Rectangle bounds = itemWidget.getCanvasBounds();
            graphics.drawImage(imageToShow, (int) bounds.getX(), (int) bounds.getY(), null);
        }


    }
    private BufferedImage getImage(MoreFillOverlayThemes overlayTheme, MoreFillOverlayTypes overlayType){
        BufferedImage imageToShow = null;

        MoreFillOverlayTypes endType = overlayType; // Redundant but may be useful in future.
        MoreFillOverlayThemes endTheme = overlayTheme;



        switch(endType){

            case FILLER:
                switch (endTheme){
                    case Bronze:
                        imageToShow = bronzestandard;
                        break;
                    case Gold:
                        imageToShow = goldstandard;
                        break;
                    case Adamant:
                        imageToShow = addystandard;
                        break;
                    case Rune:
                        imageToShow = runestandard;
                        break;
                    case Dragon:
                        imageToShow = dragonstandard;
                        break;

                }
                break;

            case SQUARE:
                switch (endTheme){
                    case Bronze:
                        imageToShow = bronzesq;
                        break;
                    case Gold:
                        imageToShow = goldsq;
                        break;
                    case Adamant:
                        imageToShow = addysq;
                        break;
                    case Rune:
                        imageToShow = runesq;
                        break;
                    case Dragon:
                        imageToShow = dragonsq;
                        break;

                }
                break;

            case DOT:
                switch (endTheme){
                    case Bronze:
                        imageToShow = bronzedot;
                        break;
                    case Gold:
                        imageToShow = golddot;
                        break;
                    case Adamant:
                        imageToShow = addydot;
                        break;
                    case Rune:
                        imageToShow = runedot;
                        break;
                    case Dragon:
                        imageToShow = dragondot;
                        break;

                }
                break;

            case SWIRL:
                switch (endTheme){
                    case Bronze:
                        imageToShow = bronzeswirl;
                        break;
                    case Gold:
                        imageToShow = goldswirl;
                        break;
                    case Adamant:
                        imageToShow = addyswirl;
                        break;
                    case Rune:
                        imageToShow = runeswirl;
                        break;
                    case Dragon:
                        imageToShow = dragonswirl;
                        break;

                }
                break;
        }

        if (imageToShow == null){
            imageToShow = empty;
        }

        return imageToShow;
    }


}
