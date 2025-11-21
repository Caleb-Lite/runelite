package net.runelite.client.plugins.pluginhub.io.hydrox.masterscrollbook;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Scroll
{
	NARDAH("Nardah", 5672),
	DIGSITE("Digsite", 5673),
	FELDIP_HILLS("Feldip Hills", 5674),
	LUNAR_ISLE("Lunar Isle", 5675),
	MORTTON("Mort'ton", 5676),
	PEST_CONTROL("Pest Control", 5677),
	PISCATORIS("Piscatoris", 5678),
	TAI_BWO_WANNAI("Tai Bwo Wannai", 5679),
	IOWERTH_CAMP("Iowerth Camp", 5680),
	MOS_LE_HARMLESS("Mos Le' Harmless", 5681),
	LUMBERYARD("Lumberyard", 5682),
	ZUL_ANDRA("Zul'andra", 5683),
	KEY_MASTER("Key Master", 5684),
	REVENANT_CAVES("Revenant Caves", 6056),
	WATSON("Watson", 8253),
	;

	private final String name;
	private final int varbit;

	public int getIndex()
	{
		return this.ordinal();
	}

	public static Scroll get(int idx)
	{
		return values()[idx];
	}

	public static int size()
	{
		return values().length;
	}
}

