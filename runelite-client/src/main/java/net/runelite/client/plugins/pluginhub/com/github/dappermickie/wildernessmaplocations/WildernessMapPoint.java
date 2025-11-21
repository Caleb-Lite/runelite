package net.runelite.client.plugins.pluginhub.com.github.dappermickie.wildernessmaplocations;

import java.awt.Color;
import lombok.Getter;
import net.runelite.api.coords.WorldPoint;

public class WildernessMapPoint
{

	public WildernessMapPoint(String text, WorldPoint worldPoint)
	{
		this.text = text;
		this.worldPoint = worldPoint;
		this.fillColor = Color.BLACK;
		this.textColor = Color.WHITE;
	}

	public WildernessMapPoint(String text, WorldPoint worldPoint, Color fillColor, Color textColor)
	{
		this.text = text;
		this.worldPoint = worldPoint;
		this.fillColor = fillColor;
		this.textColor = textColor;
	}

	@Getter
	private final String text;

	@Getter
	private final Color fillColor;

	@Getter
	private final WorldPoint worldPoint;

	@Getter
	private final Color textColor;
}
