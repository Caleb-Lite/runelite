package net.runelite.client.plugins.pluginhub.com.PublicChatAndNPCOverheadRemover;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("PublicChatAndNPCOverheadRemoverConfigGroup")
public interface PublicChatAndNPCOverheadRemoverPluginConfig extends Config
{
	String GROUP = "com/PublicChatAndNPCOverheadRemover";

	// Special Attacks - Overheads
	@ConfigSection(
			name = "Special Attacks - Overhead",
			description = "Special attack overhead messages",
			position = 0
	)
	String specialAttacksSectionOH = "specialAttacksSectionOH";

	@ConfigItem(
			keyName = "muteAxesOH",
			name = "Hide Axe Specs",
			description = "Hides the 'Chop chop!' overhead message",
			section = specialAttacksSectionOH
	)
	default boolean muteAxesOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "mutePickaxesOH",
			name = "Hide Pickaxe Specs",
			description = "Hides the 'Smashing!' overhead message",
			section = specialAttacksSectionOH
	)
	default boolean mutePickaxesOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteHarpoonsOH",
			name = "Hide Harpoon Specs",
			description = "Hides the 'Here fishy fishies!' overhead message",
			section = specialAttacksSectionOH
	)
	default boolean muteHarpoonsOH()
	{
		return true;
	}

	// Special Attacks - Public Chat
	@ConfigSection(
			name = "Special Attacks - Public Chat",
			description = "Special attack public chat messages",
			position = 1
	)
	String specialAttacksSectionPC = "specialAttacksSectionPC";

	@ConfigItem(
			keyName = "muteAxesPC",
			name = "Hide Axe Specs",
			description = "Hides the 'Chop chop!' public chat message",
			section = specialAttacksSectionPC
	)
	default boolean muteAxesPC()
	{
		return true;
	}

	@ConfigItem(
			keyName = "mutePickaxesPC",
			name = "Hide Pickaxe Specs",
			description = "Hides the 'Smashing!' public chat message",
			section = specialAttacksSectionPC
	)
	default boolean mutePickaxesPC()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteHarpoonsPC",
			name = "Hide Harpoon Specs",
			description = "Hides the 'Here fishy fishies!' public chat message",
			section = specialAttacksSectionPC
	)
	default boolean muteHarpoonsPC()
	{
		return true;
	}


	// NPCs - Overheads
	@ConfigSection(
			name = "NPC - Overhead",
			description = "NPC overhead messages",
			position = 2
	)
	String npcOHSection = "npcOHSection";

	@ConfigItem(
			keyName = "muteRoguesOH",
			name = "Rogues",
			description = "Hides the 'Someones stealing from us, get them!' overhead message when stealing from the chest at Rogue's Castle",
			section = npcOHSection
	)
	default boolean muteRoguesOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteAmoxliatlOH",
			name = "Amoxliatl",
			description = "Hides all overhead messages during the Amoxliatl fight",
			section = npcOHSection
	)
	default boolean muteAmoxliatlOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteDerangedArchOH",
			name = "Deranged Archaeologist",
			description = "Hides all overhead messages during the Deranged Archaeologist fight",
			section = npcOHSection
	)
	default boolean muteDerangedArchOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteCrazyArchOH",
			name = "Crazy Archaeologist",
			description = "Hides all overhead messages during the Crazy Archaeologist fight",
			section = npcOHSection
	)
	default boolean muteCrazyArchOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteCerberusOH",
			name = "Cerberus",
			description = "Hides all overhead messages during the Cerberus fight",
			section = npcOHSection
	)
	default boolean muteCerberusOH()
	{
		return false;
	}

	@ConfigItem(
			keyName = "muteSummonedSoulOH",
			name = "Cerberus: Summoned Souls",
			description = "Hides all overhead messages from the Summoned Souls during the Cerberus fight",
			section = npcOHSection
	)
	default boolean muteSummonedSoulOH()
	{
		return false;
	}

	@ConfigItem(
			keyName = "muteRTBrandaOH",
			name = "Royal Titans: Branda",
			description = "Hides all overhead messages from Branda during the Royal Titans fight",
			section = npcOHSection
	)
	default boolean muteRTBrandaOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteRTEldricOH",
			name = "Royal Titans: Eldric",
			description = "Hides all overhead messages from Eldric during the Royal Titans fight",
			section = npcOHSection
	)
	default boolean muteRTEldricOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteBarbarianOH",
			name = "Barbarian",
			description = "Hides the 'YYEEEEEAAARRRRGGHHHH!!!' overhead message when fighting a Barbarian",
			section = npcOHSection
	)
	default boolean muteBarbarianOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteNexOH",
			name = "Nex",
			description = "Hides all overhead messages during the Nex fight",
			section = npcOHSection
	)
	default boolean muteNexOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteGeneralGraardorOH",
			name = "General Graardor",
			description = "Hides all overhead messages during the General Graardor fight",
			section = npcOHSection
	)
	default boolean muteGeneralGraardorOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteCommanderZilyanaOH",
			name = "Commander Zilyana",
			description = "Hides all overhead messages during the Commander Zilyana fight",
			section = npcOHSection
	)
	default boolean muteCommanderZilyanaOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteKreeOH",
			name = "Kree'arra",
			description = "Hides all overhead messages during the Kree'arra fight",
			section = npcOHSection
	)
	default boolean muteKreeOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteKrilOH",
			name = "K'ril Tsutsaroth",
			description = "Hides all overhead messages during the K'ril Tsutsaroth fight",
			section = npcOHSection
	)
	default boolean muteKrilOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteTownCrierOH",
			name = "Town Crier",
			description = "Hides all overhead messages from the Town Criers around the world",
			section = npcOHSection
	)
	default boolean muteTownCrierOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteBennyOH",
			name = "Benny",
			description = "Hides all overhead messages from Benny in Varrock square",
			section = npcOHSection
	)
	default boolean muteBennyOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteChaosFanaticOH",
			name = "Chaos Fanatic",
			description = "Hides all overhead messages during the Chaos Fanatic fight",
			section = npcOHSection
	)
	default boolean muteChaosFanaticOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteHueySeerOH",
			name = "Hueycoatl: Seer",
			description = "Hides all Seer overhead messages during the Hueycoatl fight",
			section = npcOHSection
	)
	default boolean muteHueySeerOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteHueyWorkerOH",
			name = "Hueycoatl: Worker",
			description = "Hides all Worker overhead messages during the Hueycoatl fight",
			section = npcOHSection
	)
	default boolean muteHueyWorkerOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteRamoceanOH",
			name = "Ramocean",
			description = "Hides all overhead messages from Ramocean in the Hosidius Kitchen",
			section = npcOHSection
	)
	default boolean muteRamoceanOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteProspectorPercyOH",
			name = "Prospector Percy",
			description = "Hides all overhead messages from Prospector Percy in the Motherlode Mine",
			section = npcOHSection
	)
	default boolean muteProspectorPercyOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "mutePostiePeteOH",
			name = "Postie Pete",
			description = "Hides all overhead messages from Postie Pete (not the random event one)",
			section = npcOHSection
	)
	default boolean mutePostiePeteOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteArdyKnightOH",
			name = "Pickpocketing: Ardy Knights",
			description = "Hides the 'What do you think you're doing?' overhead message when pickpocketing an Ardy Knight",
			section = npcOHSection
	)
	default boolean muteArdyKnightOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteMasterFarmerOH",
			name = "Pickpocketing: Master Famers",
			description = "Hides the 'Cor blimey mate, what are ye doing in me pockets?' overhead message when pickpocketing a Master Farmer",
			section = npcOHSection
	)
	default boolean muteMasterFarmerOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteFarmingGuildCatOH",
			name = "Cat (Farming Guild)",
			description = "Hides the 'Zzz' overhead message from the sleeping cat in the Farming Guild",
			section = npcOHSection
	)
	default boolean muteFarmingGuildCatOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteCalvOH",
			name = "Calvar'ion",
			description = "Hides all overhead messages during the Calvar'ion fight",
			section = npcOHSection
	)
	default boolean muteCalvOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteVetionOH",
			name = "Vet'ion",
			description = "Hides all overhead messages during the Vet'ion fight",
			section = npcOHSection
	)
	default boolean muteVetionOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteGEGuidesOH",
			name = "Grand Exchange: Market Price Guides",
			description = "Hides all overhead messages during from the various guides around the GE (such as Bob Barter and Murky Matt)",
			section = npcOHSection
	)
	default boolean muteGEGuidesOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteGERecruitersOH",
			name = "Grand Exchange: Recruiters",
			description = "Hides all overhead messages during from the Castle Wars recruiters in the SW corner of the GE",
			section = npcOHSection
	)
	default boolean muteGERecruitersOH()
	{
		return true;
	}

	// Other - Overheads
	@ConfigSection(
			name = "Other - Overhead",
			description = "Other overhead messages",
			position = 3
	)
	String otherSectionOH = "otherSectionOH";

	@ConfigItem(
			keyName = "muteTeaOH",
			name = "Hide Cuppa Tea",
			description = "Hides the 'Aaah, nothing like a nice cuppa tea!' overhead message",
			section = otherSectionOH
	)
	default boolean muteTeaOH()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteToadyOH",
			name = "Hide Toady",
			description = "Hides the 'Come here toady!' public chat message",
			section = otherSectionOH
	)
	default boolean muteToadyOH()
	{
		return true;
	}

	// Other - Public Chat
	@ConfigSection(
			name = "Other - Public Chat",
			description = "Other public chat messages",
			position = 4
	)
	String otherSectionPC = "otherSectionPC";

	@ConfigItem(
			keyName = "muteTeaPC",
			name = "Hide Cuppa Tea",
			description = "Hides the 'Aaah, nothing like a nice cuppa tea!' public chat message",
			section = otherSectionPC
	)
	default boolean muteTeaPC()
	{
		return true;
	}

	@ConfigItem(
			keyName = "muteToadyPC",
			name = "Hide Toady",
			description = "Hides the 'Come here toady!' public chat message",
			section = otherSectionPC
	)
	default boolean muteToadyPC()
	{
		return true;
	}

	// Manual Entries
	@ConfigSection(
			name = "Manual NPC IDs - Overhead",
			description = "Manually enter NPC IDs to mute overhead messages",
			position = 5
	)
	String manualOHSection = "manualOHSection";

	@ConfigItem(
			position = 1,
			keyName = "NPCIDsOH",
			name = "NPC IDs",
			description = "List of NPC IDs to mute overhead messages [Separate each NPC ID with a comma ', ']",
			section = manualOHSection
	)
	default String NPCIDsOH()
	{
		return "";
	}

	@ConfigItem(
			position = 2,
			keyName = "debugNPC",
			name = "Show NPC IDs Overhead",
			description = "Shows all NPCs with their Name and ID",
			section = manualOHSection
	)
	default boolean debugNPC()
	{
		return false;
	}

}