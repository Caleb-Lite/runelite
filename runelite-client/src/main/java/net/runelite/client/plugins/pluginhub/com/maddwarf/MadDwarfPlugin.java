package net.runelite.client.plugins.pluginhub.com.maddwarf;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Mad Dwarf"
)
public class MadDwarfPlugin extends Plugin
{
	@Inject
	private Client client;

	private int[] dwarfId = { NpcID.DRUNKEN_DWARF, NpcID.DRUNKEN_DWARF_2408, NpcID.DRUNKEN_DWARF_2409,
								NpcID.DRUNKEN_DWARF_2429, NpcID.DRUNKEN_DWARF_4305 };
	public boolean kebabDropped = false;
	public boolean beerDropped = false;
	Insults insultsClass = new Insults();
	String[] insults = insultsClass.getInsults();

	@Override
	protected void startUp() throws Exception
	{
		log.info("Mad Dwarf started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Mad Dwarf stopped!");
	}

	@Subscribe
	public void onGameTick (GameTick gameTick)
	{
		int currentTick = client.getTickCount();
		if (kebabDropped || beerDropped)
		{
			if (currentTick % 8 == 0)
			{
				List<NPC> npcList = client.getNpcs();
				for (NPC npc : npcList)
				{
					for (int i = 0; i < dwarfId.length; i++)
					{
						if (npc.getId() == dwarfId[i])
						{
							npc.setOverheadText(insults[getRandomNumber(0, insults.length)]);
						}
					}
				}
			}
		}
	}

	@Subscribe
	public void onNpcDespawned (NpcDespawned npcDespawned)
	{
		NPC npc = npcDespawned.getNpc();
		for (int i = 0; i < dwarfId.length; i++)
		{
			if (npc.getId() == dwarfId[i])
			{
				kebabDropped = false;
				beerDropped = false;
			}
		}
	}

	@Subscribe
	public void onMenuOptionClicked (MenuOptionClicked menuOptionClicked)
	{
		MenuAction menuAction = menuOptionClicked.getMenuAction();
		int itemId = menuOptionClicked.getItemId();
		MenuEntry clickedMenuEntry = menuOptionClicked.getMenuEntry();

		if (itemId == ItemID.KEBAB)
		{
			if (clickedMenuEntry.getOption() == "Drop")
			{
				kebabDropped = true;
			}
		}

		if (itemId == ItemID.BEER)
		{
			if (clickedMenuEntry.getOption() == "Drop")
			{
				beerDropped = true;
			}
		}
	}

	public int getRandomNumber(int min, int max)
	{
		return (int) ((Math.random() * (max - min)) + min);
	}
}
