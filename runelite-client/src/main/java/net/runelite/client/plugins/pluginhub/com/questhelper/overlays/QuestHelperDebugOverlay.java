package net.runelite.client.plugins.pluginhub.com.questhelper.overlays;

import net.runelite.client.plugins.pluginhub.com.questhelper.QuestHelperPlugin;
import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestDebugRenderer;
import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;

public class QuestHelperDebugOverlay extends OverlayPanel implements QuestDebugRenderer
{
	private final QuestHelperPlugin plugin;
	private QuestHelper lastSeenQuest;

	@Inject
	public QuestHelperDebugOverlay(QuestHelperPlugin plugin)
	{
		this.plugin = plugin;
		setLayer(OverlayLayer.ALWAYS_ON_TOP);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		QuestHelper quest = plugin.getSelectedQuest();

		renderDebugOverlay(graphics, plugin, panelComponent);
		renderDebugWorldOverlayHint(graphics, plugin, quest, panelComponent);
		renderDebugWidgetOverlayHint(graphics, plugin, quest, panelComponent);

		return super.render(graphics);
	}

	@Override
	public void renderDebugOverlay(Graphics graphics, QuestHelperPlugin plugin, PanelComponent panelComponent)
	{
		QuestHelper currentQuest = plugin.getSelectedQuest();
		if ((lastSeenQuest == null || (currentQuest != lastSeenQuest)) && currentQuest != null)
		{
			lastSeenQuest = currentQuest;
		}

		if (plugin.isDeveloperMode() && lastSeenQuest != null)
		{
			lastSeenQuest.renderDebugOverlay(graphics, plugin, panelComponent);
		}
	}
}
