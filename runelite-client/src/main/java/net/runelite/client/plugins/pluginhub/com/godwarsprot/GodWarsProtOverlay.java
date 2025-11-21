package net.runelite.client.plugins.pluginhub.com.godwarsprot;


import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.*;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.*;

public class GodWarsProtOverlay extends OverlayPanel
{

    private final PanelComponent panelComponent = new PanelComponent();
    private final ItemManager itemManager;
    @Inject
    GodWarsProtConfig config;

    private final ImageComponent saraGreen = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/saragreen.png"));
    private final ImageComponent saraRed = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/sarared.png"));
    private final ImageComponent armaGreen = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/armagreen.png"));
    private final ImageComponent armaRed = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/armared.png"));
    private final ImageComponent bandosGreen = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/bandosgreen.png"));
    private final ImageComponent bandosRed = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/bandosred.png"));
    private final ImageComponent zamGreen = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/zamgreen.png"));
    private final ImageComponent zamRed = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/zamred.png"));
    private final ImageComponent ancGreen = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/zarosgreen.png"));
    private final ImageComponent ancRed = new ImageComponent(ImageUtil.loadImageResource(GodWarsProtPlugin.class,"/zarosred.png"));



    @Override
    public Dimension render(Graphics2D graphics){
        setPosition(config.overlayLoc());
        panelComponent.getChildren().clear();
        if (config.iconOverlay())
        {
            panelComponent.setOrientation(ComponentOrientation.HORIZONTAL);
            panelComponent.getChildren().add(GodWarsProtPlugin.hasArma ? armaGreen : armaRed);
            panelComponent.getChildren().add(GodWarsProtPlugin.hasBandos ? bandosGreen : bandosRed);
            panelComponent.getChildren().add(GodWarsProtPlugin.hasSara ? saraGreen : saraRed);
            panelComponent.getChildren().add(GodWarsProtPlugin.hasZam ? zamGreen : zamRed);
            panelComponent.getChildren().add(GodWarsProtPlugin.hasAnc ? ancGreen : ancRed);
        }
        else
        {
            panelComponent.setOrientation(ComponentOrientation.VERTICAL);
            String overlayTitle = "God Wars Protection:";

            panelComponent.getChildren().add(TitleComponent.builder()
                    .text(overlayTitle)
                    .color(Color.YELLOW)
                    .build());
            panelComponent.setPreferredSize(new Dimension(
                    graphics.getFontMetrics().stringWidth(overlayTitle) + 60,
                    50));
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Armadyl")
                    .right(GodWarsProtPlugin.hasArma ? "Yes" : "No")
                    .rightColor(GodWarsProtPlugin.hasArma ? Color.green : Color.red)
                    .build());
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Bandos")
                    .right(GodWarsProtPlugin.hasBandos ? "Yes" : "No")
                    .rightColor(GodWarsProtPlugin.hasBandos ? Color.green : Color.red)
                    .build());
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Saradomin")
                    .right(GodWarsProtPlugin.hasSara ? "Yes" : "No")
                    .rightColor(GodWarsProtPlugin.hasSara ? Color.green : Color.red)
                    .build());
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Zamorak")
                    .right(GodWarsProtPlugin.hasZam ? "Yes" : "No")
                    .rightColor(GodWarsProtPlugin.hasZam ? Color.green : Color.red)
                    .build());
            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Ancient")
                    .right(GodWarsProtPlugin.hasAnc ? "Yes" : "No")
                    .rightColor(GodWarsProtPlugin.hasAnc ? Color.green : Color.red)
                    .build());
        }
        if(config.showOverlay())
        {
            return panelComponent.render(graphics);
        }
        else
        {
            return null;
        }


    }

    @Inject
    private GodWarsProtOverlay(ItemManager itemManager)
    {
        this.itemManager = itemManager;
    }

}
