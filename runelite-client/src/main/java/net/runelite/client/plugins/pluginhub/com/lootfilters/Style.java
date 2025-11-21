package net.runelite.client.plugins.pluginhub.com.lootfilters;

import lombok.RequiredArgsConstructor;
import net.runelite.api.Animation;
import net.runelite.api.AnimationID;
import net.runelite.api.Client;
import net.runelite.api.JagexColor;
import net.runelite.api.Model;
import net.runelite.api.ModelData;
import net.runelite.api.RuneLiteObject;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.callback.ClientThread;

import java.awt.Color;
import java.util.function.Function;

// copied verbatim (including copyright notice & disclaimer) from
// https://github.com/runelite/runelite/blob/master/runelite-client/src/main/java/net/runelite/client/plugins/grounditems/Lootbeam.java
//
// local modifications:
// remove() - wrapped setActive() call in clientThread.invoke(), where setActive must be called (throws otherwise)
class Lootbeam
{
    private final RuneLiteObject runeLiteObject;
    private final Client client;
    private final ClientThread clientThread;
    private Color color;
    private Style style;

    @RequiredArgsConstructor
    public enum Style
    {
        LIGHT(l -> l.client.loadModel(
                5809,
                new short[]{6371},
                new short[]{JagexColor.rgbToHSL(l.color.getRGB(), 1.0d)}
        ), anim(AnimationID.RAID_LIGHT_ANIMATION)),
        MODERN(l ->
        {
            ModelData md = l.client.loadModelData(43330);
            if (md == null)
            {
                return null;
            }

            short hsl = JagexColor.rgbToHSL(l.color.getRGB(), 1.0d);
            int hue = JagexColor.unpackHue(hsl);
            int sat = JagexColor.unpackSaturation(hsl);
            int lum = JagexColor.unpackLuminance(hsl);
            int satDelta = sat > 2 ? 1 : 0;

            return md.cloneColors()
                    .recolor((short) 26432, JagexColor.packHSL(hue, sat - satDelta, lum))
                    .recolor((short) 26584, JagexColor.packHSL(hue, sat, Math.min(lum + 24, JagexColor.LUMINANCE_MAX)))
                    .light(75 + ModelData.DEFAULT_AMBIENT, 1875 + ModelData.DEFAULT_CONTRAST,
                            ModelData.DEFAULT_X, ModelData.DEFAULT_Y, ModelData.DEFAULT_Z);
        }, anim(AnimationID.LOOTBEAM_ANIMATION)),
        ;

        private final Function<Lootbeam, Model> modelSupplier;
        private final Function<Lootbeam, Animation> animationSupplier;
    }

    private static Function<Lootbeam, Animation> anim(int id)
    {
        return b -> b.client.loadAnimation(id);
    }

    public Lootbeam(Client client, ClientThread clientThread, WorldPoint worldPoint, Color color, Style style)
    {
        this.client = client;
        this.clientThread = clientThread;
        runeLiteObject = client.createRuneLiteObject();

        this.color = color;
        this.style = style;
        update();
        runeLiteObject.setShouldLoop(true);

        LocalPoint lp = LocalPoint.fromWorld(client, worldPoint);
        runeLiteObject.setLocation(lp, client.getPlane());

        runeLiteObject.setActive(true);
    }

    public void setColor(Color color)
    {
        if (this.color != null && this.color.equals(color))
        {
            return;
        }

        this.color = color;
        update();
    }

    public void setStyle(Style style)
    {
        if (this.style == style)
        {
            return;
        }

        this.style = style;
        update();
    }

    private void update()
    {
        clientThread.invoke(() ->
        {
            Model model = style.modelSupplier.apply(this);
            if (model == null)
            {
                return false;
            }

            Animation anim = style.animationSupplier.apply(this);

            runeLiteObject.setAnimation(anim);
            runeLiteObject.setModel(model);
            return true;
        });
    }

    public void remove()
    {
        clientThread.invoke(() -> runeLiteObject.setActive(false));
    }

}