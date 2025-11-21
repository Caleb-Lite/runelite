package net.runelite.client.plugins.pluginhub.com.osrsprofile.exporter;

import net.runelite.api.Client;

import javax.inject.Inject;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class TextureColor {
    @Inject
    private Client client;

    private static final Map<Integer, Color> colorCache = new HashMap<>();

    // get single average color from Jagex texture id
    public Color getColor(int textureId)
    {
        if (colorCache.containsKey(textureId)) {
            return colorCache.get(textureId);
        }

        if (client == null) {
            return new Color(255, 255, 255);
        }

        int[] pixels = client.getTextureProvider().load(textureId);

        int r = 0;
        int g = 0;
        int b = 0;
        int n = 0;
        for (int pixel : pixels) {
            // skip transparent (black)
            if (pixel == 0) {
                continue;
            }

            Color c = new Color(pixel);
            r += c.getRed();
            g += c.getGreen();
            b += c.getBlue();
            n++;
        }

        Color c = new Color(r/n, g/n, b/n);
        colorCache.put(textureId, c);

        return c;
    }
}
