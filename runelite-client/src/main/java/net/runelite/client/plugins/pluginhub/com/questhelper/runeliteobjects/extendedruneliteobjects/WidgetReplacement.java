package net.runelite.client.plugins.pluginhub.com.questhelper.runeliteobjects.extendedruneliteobjects;

import net.runelite.client.plugins.pluginhub.com.questhelper.steps.widget.WidgetDetails;
import lombok.Getter;

public class WidgetReplacement
{
	@Getter
	private final WidgetDetails widgetDetails;
	@Getter
	private final String textToReplace;
	@Getter
	private final String replacementText;

//	Requirement requirement;

	public WidgetReplacement(WidgetDetails widgetDetails, String textToReplace, String replacementText)
	{
		this.widgetDetails = widgetDetails;
		this.textToReplace = textToReplace;
		this.replacementText = replacementText;
	}
}
