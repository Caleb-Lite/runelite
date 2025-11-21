package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.*;

public class ItemXPVersion
{
    public static String CURRENT_VERSION = "0.2";
    @Inject	private ConfigManager configManager;
    @Inject private ClientThread clientThread;
    @Inject private Client client;

    public void CheckForNewVersion()
    {
        var oldVersion = configManager.getConfiguration(ItemXPConfig.CONFIG_GROUP, ItemXPConfig.PLUGIN_VERSION);
        var newVersion = CURRENT_VERSION;

        if(!newVersion.equals(oldVersion))
        {
            clientThread.invokeLater(() -> {
                client.addChatMessage(ChatMessageType.GAMEMESSAGE,
                        "",
                        "[" + ColorUtil.wrapWithColorTag("Item XP Plugin" , Color.MAGENTA) + "] "
                                + "New Version: v" + CURRENT_VERSION,
                        "plugin:item-xp");

                String whatsNew = getWhatsNew(CURRENT_VERSION);

                if(whatsNew != null)
                {
                    client.addChatMessage(ChatMessageType.GAMEMESSAGE,
                            "",
                            "[" + ColorUtil.wrapWithColorTag("Item XP Plugin" , Color.MAGENTA) + "] "
                                    + "What's New: " + whatsNew,
                            "plugin:item-xp");
                }

                configManager.setConfiguration(ItemXPConfig.CONFIG_GROUP, ItemXPConfig.PLUGIN_VERSION, CURRENT_VERSION);
            });
        }
    }

    private String getWhatsNew(String versionNumber)
    {
        switch(versionNumber)
        {
            case "0.2":
                return "Prayer and Firemaking have been added!";
            default:
                return null;
        }
    }
}
