package net.runelite.client.plugins.pluginhub.com.dklights.overlay;

import net.runelite.client.plugins.pluginhub.com.dklights.DKLightsConfig;
import net.runelite.client.plugins.pluginhub.com.dklights.DKLightsPlugin;
import net.runelite.client.plugins.pluginhub.com.dklights.enums.Area;
import net.runelite.client.plugins.pluginhub.com.dklights.enums.Lamp;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class LegacyOverlay extends OverlayPanel
{

	private final DKLightsPlugin plugin;
    private final DKLightsConfig config;

	@Inject
	private LegacyOverlay(DKLightsPlugin plugin, DKLightsConfig config)
	{
		super(plugin);
        this.config = config;
		this.plugin = plugin;

		setPosition(OverlayPosition.TOP_LEFT);
	}

	private void addTextToOverlayPanel(String text)
	{
		panelComponent.getChildren().add(LineComponent.builder().left(text).build());
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
        if (!config.showLegacyOverlay())
        {
            return null;
        }

		Set<Lamp> areaLampPoints = plugin.getStateManager().getBrokenLamps();
		Area currentArea = plugin.getStateManager().getCurrentArea();

		panelComponent.getChildren().clear();
		if (currentArea == null)
		{
			return null;
		}

		boolean addedText = false;
		if (areaLampPoints != null && areaLampPoints.size() != 10)
		{
			addTextToOverlayPanel("Unknown lamps: " + (10 - areaLampPoints.size()));
		}
		for (Area area : Area.values())
		{
			LinkedHashMap<String, Integer> descriptionCount = new LinkedHashMap<>();
			for (Lamp l : areaLampPoints)
			{
				if (l.getArea() != area)
				{
					continue;
				}

				if (!descriptionCount.containsKey(l.getDescription()))
				{
					descriptionCount.put(l.getDescription(), 1);
				}
				else
				{
					descriptionCount.put(l.getDescription(), descriptionCount.get(l.getDescription()) + 1);
				}
			}

			if (descriptionCount.size() != 0)
			{
				addTextToOverlayPanel(area.getName());
			}
			for (String s : descriptionCount.keySet())
			{
				String num = " (x" + descriptionCount.get(s) + ")";
				if (descriptionCount.get(s) == 1)
				{
					num = "";
				}
				addTextToOverlayPanel("* " + s + num);
				addedText = true;
			}
		}

		if (!addedText)
		{
			addTextToOverlayPanel("No broken lamps in this area");
		}


		return super.render(graphics);
	}
}
