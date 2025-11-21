package net.runelite.client.plugins.pluginhub.com.questhelper.overlays;

import net.runelite.client.plugins.pluginhub.com.questhelper.QuestHelperPlugin;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class QuestHelperTooltipOverlay extends OverlayPanel
{
	private final QuestHelperPlugin questHelperPlugin;
	private final Client client;

	@Inject
	public QuestHelperTooltipOverlay(QuestHelperPlugin questHelperPlugin, Client client)
	{
		setPriority(PRIORITY_HIGHEST);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setDragTargetable(false);
		setPosition(OverlayPosition.TOOLTIP);

		this.questHelperPlugin = questHelperPlugin;
		this.client = client;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (questHelperPlugin.getSelectedQuest() != null && questHelperPlugin.getSelectedQuest().getCurrentStep() != null)
		{
			questHelperPlugin.getSelectedQuest().getCurrentStep().renderQuestStepTooltip(panelComponent, !client.isMenuOpen(), false);
		}

		questHelperPlugin.getBackgroundHelpers().forEach(((s, questHelper) -> {
			questHelper.getCurrentStep().renderQuestStepTooltip(panelComponent, !client.isMenuOpen(), true);
		}));

		return super.render(graphics);
	}
}
