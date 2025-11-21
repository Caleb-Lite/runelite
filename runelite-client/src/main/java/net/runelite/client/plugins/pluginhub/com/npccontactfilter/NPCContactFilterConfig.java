package net.runelite.client.plugins.pluginhub.com.npccontactfilter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("npc contact filter")
public interface NPCContactFilterConfig extends Config
{
	@ConfigSection(name = "NPCs", description = "NPCs to be filtered or kept", position = 0, closedByDefault  = true)
	String npcSection = "NPCs";

	@ConfigItem(
		keyName = "honestJimmy",
		name = "Honest Jimmy",
		description = "Show Honest Jimmy",
		section = npcSection,
		position = 1
	)
	default boolean honestJimmy()
	{
		return true;
	}

	@ConfigItem(
		keyName = "bert",
		name = "Bert the Sandman",
		description = "Show Bert the Sandman",
		section = npcSection,
		position = 2
	)
	default boolean bert()
	{
		return true;
	}

	@ConfigItem(
		keyName = "advisorGhrim",
		name = "Advisor Ghrim",
		description = "Show Advisor Ghrim",
		section = npcSection,
		position = 3
	)
	default boolean advisorGhrim()
	{
		return true;
	}

	@ConfigItem(
		keyName = "darkMage",
		name = "Dark Mage",
		description = "Show Dark Mage",
		section = npcSection,
		position = 4
	)
	default boolean darkMage()
	{
		return true;
	}

	@ConfigItem(
		keyName = "lanthus",
		name = "Lanthus",
		description = "Show Lanthus",
		section = npcSection,
		position = 5
	)
	default boolean lanthus()
	{
		return true;
	}

	@ConfigItem(
		keyName = "spria",
		name = "Spria",
		description = "Show Spria",
		section = npcSection,
		position = 6
	)
	default boolean spria()
	{
		return true;
	}

	@ConfigItem(
		keyName = "turael",
		name = "Turael/Aya",
		description = "Show Turael/Aya",
		section = npcSection,
		position = 7
	)
	default boolean turael()
	{
		return true;
	}

	@ConfigItem(
		keyName = "mazchna",
		name = "Mazchna",
		description = "Show Mazchna",
		section = npcSection,
		position = 8
	)
	default boolean mazchna()
	{
		return true;
	}

	@ConfigItem(
		keyName = "vannaka",
		name = "Vannaka",
		description = "Show Vannaka",
		section = npcSection,
		position = 9
	)
	default boolean vannaka()
	{
		return true;
	}

	@ConfigItem(
		keyName = "chaeldar",
		name = "Chaeldar",
		description = "Show Chaeldar",
		section = npcSection,
		position = 10
	)
	default boolean chaeldar()
	{
		return true;
	}

	@ConfigItem(
		keyName = "nieve",
		name = "Nieve/Steve",
		description = "Show Nieve/Steve",
		section = npcSection,
		position = 11
	)
	default boolean nieve()
	{
		return true;
	}

	@ConfigItem(
		keyName = "duradel",
		name = "Duradel/Kuradal",
		description = "Show Duradel/Kuradal",
		section = npcSection,
		position = 12
	)
	default boolean duradel()
	{
		return true;
	}

	@ConfigItem(
		keyName = "krystilia",
		name = "Krystilia",
		description = "Show Krystilia",
		section = npcSection,
		position = 13
	)
	default boolean krystilia()
	{
		return true;
	}

	@ConfigItem(
		keyName = "konar",
		name = "Konar",
		description = "Show Konar",
		section = npcSection,
		position = 14
	)
	default boolean konar()
	{
		return true;
	}

	@ConfigItem(
		keyName = "murphy",
		name = "Murphy",
		description = "Show Murphy",
		section = npcSection,
		position = 15
	)
	default boolean murphy()
	{
		return true;
	}

	@ConfigItem(
		keyName = "cyrisus",
		name = "Cyrisus",
		description = "Show Cyrisus",
		section = npcSection,
		position = 16
	)
	default boolean cyrisus()
	{
		return true;
	}

	@ConfigItem(
		keyName = "smoggy",
		name = "Smoggy",
		description = "Show Smoggy",
		section = npcSection,
		position = 17
	)
	default boolean smoggy()
	{
		return true;
	}

	@ConfigItem(
		keyName = "ginea",
		name = "Ginea",
		description = "Show Ginea",
		section = npcSection,
		position = 18
	)
	default boolean ginea()
	{
		return true;
	}

	@ConfigItem(
		keyName = "watson",
		name = "Watson",
		description = "Show Watson",
		section = npcSection,
		position = 19
	)
	default boolean watson()
	{
		return true;
	}

	@ConfigItem(
		keyName = "barbarianGuard",
		name = "Barbarian Guard",
		description = "Show Barbarian Guard",
		section = npcSection,
		position = 20
	)
	default boolean barbarianGuard()
	{
		return true;
	}

	@ConfigItem(
		keyName = "amy",
		name = "Amy",
		description = "Show Amy",
		section = npcSection,
		position = 21
	)
	default boolean amy()
	{
		return true;
	}

	@ConfigItem(
		keyName = "random",
		name = "Random",
		description = "Show Random",
		section = npcSection,
		position = 22
	)
	default boolean random()
	{
		return true;
	}
}
