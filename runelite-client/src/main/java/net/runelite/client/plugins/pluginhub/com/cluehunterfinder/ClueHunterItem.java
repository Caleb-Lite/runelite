package net.runelite.client.plugins.pluginhub.com.cluehunterfinder;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;

public enum ClueHunterItem
{
	CLUE_HUNTER_BOOTS(new WorldPoint(2580, 3377, 0), ItemID.CLUE_HUNTER_BOOTS, "chboots", "Dig next to an oak tree south-west of the Fishing Guild, just north of East Ardougne."),
	CLUE_HUNTER_GLOVES(new WorldPoint(2580, 3377, 0), ItemID.CLUE_HUNTER_GLOVES, "chgloves", "Dig next to an oak tree south-west of the Fishing Guild, just north of East Ardougne."),
	CLUE_HUNTER_GARB(new WorldPoint(1595, 3626, 0), ItemID.CLUE_HUNTER_GARB, "chgarb", "Dig just north-east of the Shayzien minecart station."),
	CLUE_HUNTER_TROUSERS(new WorldPoint(2819, 3126, 0), ItemID.CLUE_HUNTER_TROUSERS, "chtrousers", "Dig near the entrance to the Pothole Dungeon north of Tai Bwo Wannai."),
	CLUE_HUNTER_CLOAK(new WorldPoint(2614, 3064, 0), ItemID.CLUE_HUNTER_CLOAK, "chcloak", "Dig between two willow trees south-east of Yanille."),
	HELM_OF_RAEDWALD(new WorldPoint(2590, 3231, 0), ItemID.HELM_OF_RAEDWALD, "helmor", "Dig near the iron rocks by the cave entrance east of the Clock Tower. Requires 1 nature rune, 1 superantipoison(1) and leather boots.");

	@Getter
	private final WorldPoint location;

	@Getter
	private final int id;

	@Getter
	private final String findInstruction;

	private final String keyword;

	// TODO: Extract this map in its own class to avoid ugly static
	private static final Map<String, ClueHunterItem> itemMap = generateClueHunterItemMap();
	
	ClueHunterItem(final WorldPoint location, final int id, final String keyword, final String findInstruction)
	{
		this.location = location;
		this.id = id;
		this.keyword = keyword;
		this.findInstruction = findInstruction;
	}

	static Map<String, ClueHunterItem> getClueHunterItemMap()
	{
		return itemMap;
	}

	private static Map<String, ClueHunterItem> generateClueHunterItemMap()
	{
		final Map<String, ClueHunterItem> map = new HashMap<>();
		for (final ClueHunterItem item : ClueHunterItem.values())
		{
			map.put(item.keyword, item);
		}
		return map;
	}
}
