package net.runelite.client.plugins.pluginhub.com.shuddie.overhead;

import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.Player;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.Color;

public class Overhead {
    @Inject
    private transient Client client;

    public void show(String template, String[] triggerValues, Color textColor, int displayTimeSeconds) {
        String message = processTriggerValues(template, triggerValues);

        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null) {
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            localPlayer.setOverheadText(null);
            localPlayer.setOverheadCycle(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (textColor != null) {
            sb.append("<col=")
                    .append(ColorUtil.colorToHexCode(textColor))
                    .append(">")
                    .append(message)
                    .append("</col>");
        } else {
            sb.append(message);
        }

        localPlayer.setOverheadText(sb.toString());
        localPlayer.setOverheadCycle(displayTimeSeconds * 1000 / Constants.CLIENT_TICK_LENGTH);
    }

    private static String processTriggerValues(String message, String[] triggerValues) {
        if (triggerValues == null) {
            return message;
        }

        for (int i = 0; i < triggerValues.length; i++) {
            message = message.replace("{" + i + "}", triggerValues[i]);
        }

        return message;
    }
}
