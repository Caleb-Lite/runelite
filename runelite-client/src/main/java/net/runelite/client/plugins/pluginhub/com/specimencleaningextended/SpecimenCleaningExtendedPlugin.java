package net.runelite.client.plugins.pluginhub.com.specimencleaningextended;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.Arrays;
import java.util.Set;
import java.text.DecimalFormat;

@Slf4j
@PluginDescriptor(
	name = "Specimen Cleaning Extended"
)
public class SpecimenCleaningExtendedPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private SpecimenCleaningExtendedOverlay overlay;

	private float totalTicks = 0.0f;
	private float totalArtefacts = 0.0f;
	private float totalSpecimens = 0.0f;
	private float artefactsPerHour = 0.0f;
	private float specimensPerHour = 0.0f;
	private float predictedXpPerHour = 0.0f;

	@Getter
	private String artefactsPerHourFormatted;
	@Getter
	private String predictedXpPerHourFormatted;
	@Getter
	private String specimensPerHourFormatted;

	DecimalFormat formatter = new DecimalFormat("#,###");

	private static final Set<Integer> ITEMS = ImmutableSet.of(
			ItemID.ARROWHEADS, ItemID.JEWELLERY,
			ItemID.POTTERY, ItemID.OLD_CHIPPED_VASE, ItemID.UNCLEANED_FIND);


	private Multiset<Integer> lastInventory;


	@Inject
	private SpecimenCleaningExtendedConfig config;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		configureSkillsInLampMenu();
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		displayAllSkills();
		resetPredictedRateVariables();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if(config.hideLampSkills())
		{
			configureSkillsInLampMenu();
		}
		else
		{
			displayAllSkills();
		}

		if(config.reset())
		{
			resetPredictedRateVariables();
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		if(config.hideLampSkills()) {
			configureSkillsInLampMenu();
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if(!config.reset())
		{
			if(!config.pause()){
				totalTicks += 1;
				artefactsPerHour = (totalArtefacts / totalTicks) * 6000;
				artefactsPerHourFormatted = formatter.format(artefactsPerHour);

				//									lamp is 1/97.5, lamp xp = 500
				predictedXpPerHour = (float)((artefactsPerHour / 97.5) * 500);
				predictedXpPerHourFormatted = formatter.format(predictedXpPerHour);

				specimensPerHour = (totalSpecimens / totalTicks) * 6000;
				specimensPerHourFormatted = formatter.format(specimensPerHour);
			}
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event) {
		if(!config.reset()) {
			if (!config.pause()) {
				ItemContainer container = event.getItemContainer();

				if (container != client.getItemContainer(InventoryID.INVENTORY)) {
					return;
				}

				Multiset<Integer> currentInventory = HashMultiset.create();
				Arrays.stream(container.getItems())
						.filter(item -> ITEMS.contains(item.getId()))
						.forEach(item -> currentInventory.add(item.getId(), item.getQuantity()));

				if (lastInventory != null) {
					Multiset<Integer> delta = Multisets.difference(currentInventory, lastInventory);
					delta.forEach(itemId -> addItemCounts(itemId));
				}

				lastInventory = currentInventory;
			}
		}
	}

	private void addItemCounts(int itemId)
	{
		switch(itemId)
		{
			case ItemID.ARROWHEADS:
			case ItemID.JEWELLERY:
			case ItemID.POTTERY:
			case ItemID.OLD_CHIPPED_VASE:
				totalArtefacts++;
				break;
			case ItemID.UNCLEANED_FIND:
				totalSpecimens++;
				break;
		}
	}


	private void resetPredictedRateVariables()
	{
		totalTicks = 0.0f;
		totalArtefacts = 0.0f;
		artefactsPerHour = 0.0f;
		predictedXpPerHour = 0.0f;
		specimensPerHour = 0.0f;
		totalSpecimens = 0.0f;
		lastInventory = null;
	}

	private void configureSkillsInLampMenu()
	{
		if(config.hideLampSkills()) {

			if (config.attack()) {
				hideWidget(client.getWidget(15728642), true);
			}
			else
			{
				hideWidget(client.getWidget(15728642), false);
			}

			if (config.strength()) {
				hideWidget(client.getWidget(15728643), true);
			}
			else
			{
				hideWidget(client.getWidget(15728643), false);
			}

			if (config.ranged()) {
				hideWidget(client.getWidget(15728644), true);
			}
			else
			{
				hideWidget(client.getWidget(15728644), false);
			}

			if (config.magic()) {
				hideWidget(client.getWidget(15728645), true);
			}
			else
			{
				hideWidget(client.getWidget(15728645), false);
			}

			if (config.defence()) {
				hideWidget(client.getWidget(15728646), true);
			}
			else
			{
				hideWidget(client.getWidget(15728646), false);
			}

			if (config.hitpoints()) {
				hideWidget(client.getWidget(15728647), true);
			}
			else
			{
				hideWidget(client.getWidget(15728647), false);
			}

			if (config.prayer()) {
				hideWidget(client.getWidget(15728648), true);
			}
			else
			{
				hideWidget(client.getWidget(15728648), false);
			}

			if (config.agility()) {
				hideWidget(client.getWidget(15728649), true);
			}
			else
			{
				hideWidget(client.getWidget(15728649), false);
			}

			if (config.herblore()) {
				hideWidget(client.getWidget(15728650), true);
			}
			else
			{
				hideWidget(client.getWidget(15728650), false);
			}

			if (config.thieving()) {
				hideWidget(client.getWidget(15728651), true);
			}
			else
			{
				hideWidget(client.getWidget(15728651), false);
			}

			if (config.crafting()) {
				hideWidget(client.getWidget(15728652), true);
			}
			else
			{
				hideWidget(client.getWidget(15728652), false);
			}

			if (config.runecraft()) {
				hideWidget(client.getWidget(15728653), true);
			}
			else
			{
				hideWidget(client.getWidget(15728653), false);
			}

			if (config.slayer()) {
				hideWidget(client.getWidget(15728654), true);
			}
			else
			{
				hideWidget(client.getWidget(15728654), false);
			}

			if (config.farming()) {
				hideWidget(client.getWidget(15728655), true);
			}
			else
			{
				hideWidget(client.getWidget(15728655), false);
			}

			if (config.mining()) {
				hideWidget(client.getWidget(15728656), true);
			}
			else
			{
				hideWidget(client.getWidget(15728656), false);
			}

			if (config.smithing()) {
				hideWidget(client.getWidget(15728657), true);
			}
			else
			{
				hideWidget(client.getWidget(15728657), false);
			}

			if (config.fishing()) {
				hideWidget(client.getWidget(15728658), true);
			}
			else
			{
				hideWidget(client.getWidget(15728658), false);
			}

			if (config.cooking()) {
				hideWidget(client.getWidget(15728659), true);
			}
			else
			{
				hideWidget(client.getWidget(15728659), false);
			}

			if (config.firemaking()) {
				hideWidget(client.getWidget(15728660), true);
			}
			else
			{
				hideWidget(client.getWidget(15728660), false);
			}

			if (config.woodcutting()) {
				hideWidget(client.getWidget(15728661), true);
			}
			else
			{
				hideWidget(client.getWidget(15728661), false);
			}

			if (config.fletching()) {
				hideWidget(client.getWidget(15728662), true);
			}
			else
			{
				hideWidget(client.getWidget(15728662), false);
			}

			if (config.construction()) {
				hideWidget(client.getWidget(15728663), true);
			}
			else
			{
				hideWidget(client.getWidget(15728663), false);
			}

			if (config.hunter()) {
				hideWidget(client.getWidget(15728664), true);
			}
			else
			{
				hideWidget(client.getWidget(15728664), false);
			}
		}
		else //if hide lamp skills disabled then show all widgets
		{
			displayAllSkills();
		}
	}

	private void displayAllSkills()
	{
		hideWidget(client.getWidget(15728642), false);
		hideWidget(client.getWidget(15728643), false);
		hideWidget(client.getWidget(15728644), false);
		hideWidget(client.getWidget(15728645), false);
		hideWidget(client.getWidget(15728646), false);
		hideWidget(client.getWidget(15728647), false);
		hideWidget(client.getWidget(15728648), false);
		hideWidget(client.getWidget(15728649), false);
		hideWidget(client.getWidget(15728650), false);
		hideWidget(client.getWidget(15728651), false);
		hideWidget(client.getWidget(15728652), false);
		hideWidget(client.getWidget(15728653), false);
		hideWidget(client.getWidget(15728654), false);
		hideWidget(client.getWidget(15728655), false);
		hideWidget(client.getWidget(15728656), false);
		hideWidget(client.getWidget(15728657), false);
		hideWidget(client.getWidget(15728658), false);
		hideWidget(client.getWidget(15728659), false);
		hideWidget(client.getWidget(15728660), false);
		hideWidget(client.getWidget(15728661), false);
		hideWidget(client.getWidget(15728662), false);
		hideWidget(client.getWidget(15728663), false);
		hideWidget(client.getWidget(15728664), false);
	}




	private void hideWidget(Widget widget, boolean hidden)
	{
		if (widget != null)
		{
			widget.setHidden(hidden);
		}
	}

	@Provides
	SpecimenCleaningExtendedConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SpecimenCleaningExtendedConfig.class);
	}

}

