package net.runelite.client.plugins.pluginhub.com.erishiongames.unrankedkctracker;

public enum Bosses
{
	SIRE("Abyssal Sire", 20),
	HYDRA("Alchemical Hydra", 20),
	ARTIO("Artio", 20),
	BARROWS("Barrows Chests", 20),
	BRYO("Bryophyta", 5),
	CALLISTO("Callisto", 20),
	CALVARION("Calvar'ion", 20),
	CERBERUS("Cerberus", 20),
	COX("Chambers of Xeric", 20),
	COX_CM("Chambers of Xeric Challenge Mode", 5),
	CHAOS_ELE("Chaos Elemental", 20),
	CHAOS_FANATIC("Chaos Fanatic", 20),
	SARA("Commander Zilyana", 20),
	CORPOREAL_BEAST("Corporeal Beast", 20),
	CORRUPTED_GAUNTLET("Corrupted Gauntlet", 5),
	CRAZY_ARCH("Crazy Archaeologist", 20),
	PRIME("Dagannoth Prime", 20),
	REX("Dagannoth Rex", 20),
	SUPREME("Dagannoth Supreme", 20),
	DERANGED_ARCH("Deranged Archaeologist", 20),
	GAUNTLET("Gauntlet", 20),
	BANDOS("General Graardor", 20),
	MOLE("Giant Mole", 20),
	GG("Grotesque Guardians", 20),
	HESPORI("Hespori", 5),
	KQ("Kalphite Queen", 20),
	KBD("King Black Dragon", 20),
	KRAKEN("Kraken", 20),
	ARMA("Kree'arra", 20),
	ZAMMY("K'ril Tsutsaroth", 20),
	MIMIC("Mimic", 1),
	NEX("Nex", 20),
	NIGHTMARE("Nightmare", 20),
	OBOR("Obor", 5),
	MUSPAH("Phantom Muspah", 20),
	PH_NIGHTMARE("Phosani's Nightmare", 20),
	SARACHNIS("Sarachnis", 20),
	SCORPIA("Scorpia", 20),
	SKOTIZO("Skotizo", 5),
	SPINDEL("Spindel", 20),
	TEMPOROSS("Tempoross", 20),
	TOB("Theatre of Blood", 20),
	TOB_EM("Theatre of Blood Entry Mode", 20),
	TOB_HM("Theatre of Blood Hard Mode", 20),
	THERMY("Thermonuclear Smoke Devil", 20),
	TOA("Tombs of Amascut", 20),
	TOA_EM("Tombs of Amascut Entry Mode", 20),
	TOA_HM("Tombs of Amascut Expert Mode", 20),
	ZUK("TzKal-Zuk", 1),
	JAD("TzTok-Jad", 5),
	VENENATIS("Venenatis", 20),
	VETION("Vet'ion", 20),
	VORKATH("Vorkath", 20),
	WINTERTODT("Wintertodt", 20),
	ZALCANO("Zalcano", 20),
	ZULRAH("Zulrah", 20),
	;


	private final String name;
	private final int minimumKC;

	Bosses(String name, int minimumKC)
	{
	this.name = name;
	this.minimumKC = minimumKC;
	}

	public String getName(){
		return this.name;
	}
	public int getMinumiumKC(){
		return this.minimumKC;
	}
}



