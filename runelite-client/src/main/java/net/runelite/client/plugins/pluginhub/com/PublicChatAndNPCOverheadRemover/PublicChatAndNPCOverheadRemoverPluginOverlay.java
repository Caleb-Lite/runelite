package net.runelite.client.plugins.pluginhub.com.PublicChatAndNPCOverheadRemover;

import com.google.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

public class PublicChatAndNPCOverheadRemoverPluginOverlay extends Overlay
{
    private final Client client;
    private final PublicChatAndNPCOverheadRemoverPlugin plugin;

    @Inject
    PublicChatAndNPCOverheadRemoverPluginOverlay(Client client, PublicChatAndNPCOverheadRemoverPlugin plugin)
    {
        this.client = client;
        this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!plugin.debugNPC)
        {
            return null;
        }

        for (NPC npc : client.getNpcs())
        {
            renderNpcOverlay(graphics, npc, Color.WHITE);
        }

        return null;
    }

    private void renderNpcOverlay(Graphics2D graphics, NPC npc, Color colour)
    {
        if (npc == null || npc.getId() < 0 || npc.getName() == null || npc.getName().isEmpty() || "null".equals(npc.getName()))
        {
            return;
        }

        // Clean the name
        String name = npc.getName().replaceAll("</?[=\\w]*>", "");

        // Show name + ID if debugging
        String text = name + " (" + npc.getId() + ")";

        final Point textLocation = npc.getCanvasTextLocation(graphics, text, npc.getLogicalHeight() + 40);

        if (textLocation != null)
        {
            OverlayUtil.renderTextLocation(graphics, textLocation, text, colour);
        }
    }
}