package net.runelite.client.plugins.pluginhub.com.questhelper.steps.choice;

import net.runelite.client.plugins.pluginhub.com.questhelper.QuestHelperConfig;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Client;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class WidgetChoiceStep
{
	protected final QuestHelperConfig config;

	@Getter
	private final String choice;

	@Setter
	String expectedTextInWidget;

	private Pattern pattern;

	protected List<String> excludedStrings;
	protected int excludedGroupId;
	protected int excludedChildId;

	private final int choiceById;

	@Getter
	protected final int groupId;
	protected final int childId;

	protected boolean shouldNumber = false;

	@Setter
	@Getter
	private int groupIdForChecking;

	public WidgetChoiceStep(QuestHelperConfig config, String choice, int groupId, int childId)
	{
		this.config = config;
		this.choice = choice;
		this.choiceById = -1;
		this.groupId = groupId;
		this.groupIdForChecking = groupId;
		this.childId = childId;
		this.pattern = null;
	}

	public WidgetChoiceStep(QuestHelperConfig config, int groupId, int childId)
	{
		this.config = config;
		this.choice = null;
		this.choiceById = -1;
		this.groupId = groupId;
		this.groupIdForChecking = groupId;
		this.childId = childId;
		this.pattern = null;
	}

	public WidgetChoiceStep(QuestHelperConfig config, Pattern pattern, int groupId, int childId)
	{
		this.config = config;
		this.choice = null;
		this.choiceById = -1;
		this.groupId = groupId;
		this.groupIdForChecking = groupId;
		this.childId = childId;
		this.pattern = pattern;
	}

	public WidgetChoiceStep(QuestHelperConfig config, int choiceId, int groupId, int childId)
	{
		this.config = config;
		this.choice = null;
		this.choiceById = choiceId;
		this.groupId = groupId;
		this.groupIdForChecking = groupId;
		this.childId = childId;
	}

	public WidgetChoiceStep(QuestHelperConfig config, int choiceId, String choice, int groupId, int childId)
	{
		this.config = config;
		this.choice = choice;
		this.choiceById = choiceId;
		this.groupId = groupId;
		this.groupIdForChecking = groupId;
		this.childId = childId;
	}

	public WidgetChoiceStep(QuestHelperConfig config, int choiceId, Pattern pattern, int groupId, int childId)
	{
		this.config = config;
		this.choice = null;
		this.choiceById = choiceId;
		this.groupId = groupId;
		this.groupIdForChecking = groupId;
		this.childId = childId;
		this.pattern = pattern;
	}

	public void addExclusion(int excludedGroupId, int excludedChildId, String excludedString)
	{
		this.excludedStrings = Collections.singletonList(excludedString);
		this.excludedGroupId = excludedGroupId;
		this.excludedChildId = excludedChildId;
	}

	public void addExclusions(int excludedGroupId, int excludedChildId, String... excludedStrings)
	{
		this.excludedStrings = Arrays.asList(excludedStrings);
		this.excludedGroupId = excludedGroupId;
		this.excludedChildId = excludedChildId;
	}

	public void highlightChoice(Client client)
	{
		Widget exclusionDialogChoice = client.getWidget(excludedGroupId, excludedChildId);
		if (exclusionDialogChoice != null)
		{
			Widget[] exclusionChoices = exclusionDialogChoice.getChildren();
			if (exclusionChoices != null)
			{
				for (Widget currentExclusionChoice : exclusionChoices)
				{
					for (String excludedString : excludedStrings)
					{
						if (currentExclusionChoice.getText().contains(excludedString))
						{
							return;
						}
					}
				}
			}
		}
		Widget dialogChoice = client.getWidget(groupId, childId);

		if (dialogChoice == null)
		{
			return;
		}

		Widget[] choices = dialogChoice.getChildren();
		checkWidgets(choices);
		Widget[] nestedChildren = dialogChoice.getNestedChildren();
		checkWidgets(nestedChildren);
	}

	protected void checkWidgets(Widget[] choices)
	{
		if (choices != null && choices.length > 0)
		{
			if (choiceById != -1 && choices[choiceById] != null)
			{
				if ((choice != null && choice.equals(choices[choiceById].getText())) ||
					(pattern != null && pattern.matcher(choices[choiceById].getText()).find()) ||
					(choice == null && pattern == null))
				{
					highlightText(choices[choiceById], choiceById);
				}
			}
			else
			{
				for (int i = 0; i < choices.length; i++)
				{
					if (choices[i].getText().equals(choice) ||
						(pattern != null && pattern.matcher(choices[i].getText()).find()))
					{
						highlightText(choices[i], i);
						return;
					}
				}
			}
		}
	}

	protected void highlightText(Widget text, int option)
	{
		if (!config.showTextHighlight())
		{
			return;
		}

		if (shouldNumber)
		{
			text.setText("[" + option + "] " + text.getText());
		}

		text.setTextColor(config.textHighlightColor().getRGB());
		text.setOnMouseLeaveListener((JavaScriptCallback) ev -> text.setTextColor(config.textHighlightColor().getRGB()));
	}
}
