package net.runelite.client.plugins.pluginhub.com.ninetyninenotifier;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import javax.inject.Inject;
import java.awt.*;

public class NinetynineNotifierOverlay extends Overlay
{
    private final NinetynineNotifierPlugin plugin;

    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public NinetynineNotifierOverlay(Client client, NinetynineNotifierPlugin plugin)
    {
        this.plugin = plugin;
        setPosition(OverlayPosition.BOTTOM_LEFT);
        panelComponent.setPreferredSize(new Dimension(200, 0));
        panelComponent.setBorder(new Rectangle(5, 5, 5, 5));
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        int skillsCloseTo99 = plugin.getNumberOfSkillsCloseTo99();
        if (skillsCloseTo99 == 0)
        {
            return null;
        }

        String warningText = "You have " + skillsCloseTo99 + " skill" + (skillsCloseTo99 > 1 ? "s" : "") + " close to 99!";

        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(warningText)
                .color(Color.RED)
                .build());

        return panelComponent.render(graphics);
    }
}
