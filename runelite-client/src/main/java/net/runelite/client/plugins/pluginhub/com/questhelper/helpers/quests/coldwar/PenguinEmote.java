package net.runelite.client.plugins.pluginhub.com.questhelper.helpers.quests.coldwar;

import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.WidgetStep;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.widget.WidgetDetails;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.gameval.VarbitID;

import java.util.Collections;

public class PenguinEmote extends WidgetStep
{
	public PenguinEmote(QuestHelper questHelper)
	{
		super(questHelper, "Perform the 3 emotes the penguins performed in the bird hide cutscene.");
	}

	@Override
	public void startUp()
	{
		super.startUp();
		updateWidgets();
	}

	@Override
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		super.onVarbitChanged(varbitChanged);
		updateWidgets();
	}

	public void updateWidgets()
	{
		int currentEmoteStep = client.getVarbitValue(VarbitID.PENG_EMOTE_CHECK);
		int currentEmoteID = 0;
		if (currentEmoteStep == 0)
		{
			currentEmoteID = 8 + client.getVarbitValue(VarbitID.PENG_EMOTE_1);
		}
		else if (currentEmoteStep == 1)
		{
			currentEmoteID = 8 + client.getVarbitValue(VarbitID.PENG_EMOTE_2);
		}
		else if (currentEmoteStep == 2)
		{
			currentEmoteID = 8 + client.getVarbitValue(VarbitID.PENG_EMOTE_3);
		}
		this.setWidgetDetails(Collections.singletonList(new WidgetDetails(223, currentEmoteID, -1)));
	}
}

