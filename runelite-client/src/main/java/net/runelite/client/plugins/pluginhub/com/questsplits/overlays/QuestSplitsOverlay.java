package net.runelite.client.plugins.pluginhub.com.questsplits.overlays;

import net.runelite.client.plugins.pluginhub.com.questsplits.QuestSplitsPlugin;

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;

public class QuestSplitsOverlay extends OverlayPanel
{
	private final QuestSplitsPlugin plugin;
	private Widget[] textFields;

	@Inject
	public QuestSplitsOverlay(QuestSplitsPlugin plugin, Widget[] textFields)
	{
		this.plugin = plugin;
		setMovable(true);
		setPriority(OverlayPriority.HIGHEST);
	}

	public Widget[] getTextFields(){
		return this.textFields;
	}

	public void setTextFields(Widget[] textFields) {
		this.textFields = textFields;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (textFields == null || !plugin.getConfig().showSplits())
		{
			return super.render(graphics);
		}

		panelComponent.setPreferredSize(new Dimension(300, 100));
		panelComponent.setPreferredLocation(new Point(0,0));
		for(String time : plugin.getTimes().keySet())
		{
			panelComponent.getChildren().add(LineComponent.builder().left(time).right(plugin.getTimes().get(time) + " " + plugin.getBestTimes().get(time)).build());
		}

		return super.render(graphics);
	}
}
