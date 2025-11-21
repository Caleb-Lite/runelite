package net.runelite.client.plugins.pluginhub.Posiedien_Leagues_Planner;

import java.awt.Dimension;
import javax.swing.JPanel;
import net.runelite.client.ui.PluginPanel;

public class FixedWidthPanel extends JPanel
{
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(PluginPanel.PANEL_WIDTH, super.getPreferredSize().height);
    }

}