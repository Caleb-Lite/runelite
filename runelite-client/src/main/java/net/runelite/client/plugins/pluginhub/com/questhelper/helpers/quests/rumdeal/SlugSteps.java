package net.runelite.client.plugins.pluginhub.com.questhelper.helpers.quests.rumdeal;

import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.Requirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.zone.Zone;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.zone.ZoneRequirement;
import com.questhelper.steps.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.FishingSpot;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SlugSteps extends DetailedOwnerStep
{

	DetailedQuestStep addSluglings, talkToPete, goDownFromTop, fish5Slugs, goDownToSluglings, goUpFromSluglings, goUpToDropSluglings, goUpF1ToPressure, goUpToF2ToPressure, pressure;
	ConditionalStep getSluglings, pressureSluglings, pullPressureLever;

	Zone islandF0, islandF1, islandF2;

	Requirement onIslandF0, onIslandF1, onIslandF2;

	ItemRequirement sluglings;
	ItemRequirement sluglingsHighlight;
	ItemRequirement netBowl;

	public SlugSteps(QuestHelper questHelper)
	{
		super(questHelper);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		updateSteps();
	}

	protected void updateSteps()
	{
		int numHandedIn = client.getVarbitValue(VarbitID.DEAL_BARREL);

		sluglings.setQuantity(5 - numHandedIn);

		if (numHandedIn >= 5)
		{
			startUpStep(pullPressureLever);
		}
		else if (sluglings.check(client))
		{
			startUpStep(pressureSluglings);
		}
		else
		{
			startUpStep(getSluglings);
		}
	}

	@Override
	protected void setupSteps()
	{
		sluglings = new ItemRequirement("Sluglings or Karamthulu", ItemID.DEAL_SLUGLING, 5);
		sluglingsHighlight = new ItemRequirement("Sluglings or Karamthulu", ItemID.DEAL_SLUGLING, 5);
		netBowl = new ItemRequirement("Fishbowl and net", ItemID.FISHBOWL_NET);
		netBowl.setTooltip("You can get another from Captain Braindeath, or make it with a fishbowl and large net");
		sluglingsHighlight.setHighlightInInventory(true);
		sluglingsHighlight.addAlternates(ItemID.DEAL_KARAMTHULHU, ItemID.INACTIVEPET_SQUID);
		sluglings.addAlternates(ItemID.DEAL_KARAMTHULHU, ItemID.INACTIVEPET_SQUID);

		islandF0 = new Zone(new WorldPoint(2110, 5054, 0), new WorldPoint(2178, 5185, 0));
		islandF1 = new Zone(new WorldPoint(2110, 5054, 1), new WorldPoint(2178, 5185, 1));
		islandF2 = new Zone(new WorldPoint(2110, 5054, 2), new WorldPoint(2178, 5185, 2));
		onIslandF0 = new ZoneRequirement(islandF0);
		onIslandF1 = new ZoneRequirement(islandF1);
		onIslandF2 = new ZoneRequirement(islandF2);

		talkToPete = new NpcStep(getQuestHelper(), NpcID.DEAL_PETE, new WorldPoint(3680, 3537, 0), "Talk to Pirate Pete north east of the Ectofuntus.");
		talkToPete.addDialogSteps("Okay!");
		addSluglings = new ObjectStep(getQuestHelper(), ObjectID.DEAL_PRESSURE, new WorldPoint(2142, 5102, 2),
			"Add the sea creatures to the pressure barrel on the top floor.", sluglings.highlighted());
		addSluglings.addIcon(ItemID.DEAL_SLUGLING);
		goDownFromTop = new ObjectStep(getQuestHelper(), ObjectID.DEAL_LADDERTOP, new WorldPoint(2163, 5092, 2), "Go down the ladder and fish for sea creatures.");

		fish5Slugs = new NpcStep(getQuestHelper(), FishingSpot.QUEST_RUM_DEAL.getIds(), new WorldPoint(2173, 5073, 0), "Fish 5 sluglings or karamthulu from " +
				"around the coast of the island.", netBowl);
		goDownToSluglings = new ObjectStep(getQuestHelper(), ObjectID.DEAL_STAIRS_TOP, new WorldPoint(2150, 5088, 1), "Go fish 5 sluglings.", netBowl);
		goUpFromSluglings = new ObjectStep(getQuestHelper(), ObjectID.DEAL_STAIRS_BOTTOM, new WorldPoint(2150, 5088, 0),
			"Add the sea creatures to the pressure barrel on the top floor.", sluglings);

		fish5Slugs.addSubSteps(goDownFromTop, goDownToSluglings, talkToPete);

		goUpToDropSluglings = new ObjectStep(getQuestHelper(), ObjectID.DEAL_LADDER_UP, new WorldPoint(2163, 5092, 1),
			"Add the sea creatures to the pressure barrel on the top floor.", sluglings);

		goUpFromSluglings = new ObjectStep(getQuestHelper(), ObjectID.DEAL_STAIRS_BOTTOM, new WorldPoint(2150, 5088, 0),
			"Go to the top floor to pull the pressure lever.", sluglings);
		goUpF1ToPressure = new ObjectStep(getQuestHelper(), ObjectID.DEAL_STAIRS_BOTTOM, new WorldPoint(2150, 5088, 0),
			"Go to the top floor to pull the pressure lever.");
		goUpToF2ToPressure = new ObjectStep(getQuestHelper(), ObjectID.DEAL_LADDER_UP, new WorldPoint(2163, 5092, 1),
			"Go to the top floor to pull the pressure lever.");

		pressure = new ObjectStep(getQuestHelper(), ObjectID.DEAL_MULTI_LEVER, new WorldPoint(2141, 5103, 2), "Pull the pressure lever.");
		pressure.addSubSteps(goUpToF2ToPressure, goUpF1ToPressure);

		getSluglings = new ConditionalStep(getQuestHelper(), talkToPete);
		getSluglings.addStep(onIslandF0, fish5Slugs);
		getSluglings.addStep(onIslandF1, goDownToSluglings);
		getSluglings.addStep(onIslandF2, goDownToSluglings);

		pressureSluglings = new ConditionalStep(getQuestHelper(), talkToPete);
		pressureSluglings.addStep(onIslandF2, addSluglings);
		pressureSluglings.addStep(onIslandF1, goUpToDropSluglings);
		pressureSluglings.addStep(onIslandF0, goUpFromSluglings);

		pullPressureLever = new ConditionalStep(getQuestHelper(), talkToPete);
		pullPressureLever.addStep(onIslandF2, pressure);
		pullPressureLever.addStep(onIslandF1, goUpToF2ToPressure);
		pullPressureLever.addStep(onIslandF0, goUpF1ToPressure);
	}

	@Override
	public Collection<QuestStep> getSteps()
	{
		return Arrays.asList(talkToPete, goDownToSluglings, fish5Slugs, goUpFromSluglings, goUpToDropSluglings, addSluglings,
			getSluglings, pressureSluglings, goUpF1ToPressure, goUpToF2ToPressure, pressure, pullPressureLever);
	}

	public List<QuestStep> getDisplaySteps()
	{
		return Arrays.asList(fish5Slugs, addSluglings, pressure);
	}
}

