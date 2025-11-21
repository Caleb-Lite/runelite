package net.runelite.client.plugins.pluginhub.com.github.dappermickie.wildernessmaplocations;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Wilderness Map Locations"
)
public class WildernessMapLocationsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private WildernessMapLocationsConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private WorldMapLocationsOverlay worldMapLocationsOverlay;

	@Inject
	private ClosestLocationOverlay closestLocationOverlay;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(worldMapLocationsOverlay);
		overlayManager.add(closestLocationOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(worldMapLocationsOverlay);
		overlayManager.remove(closestLocationOverlay);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		closestLocationOverlay.updateClosestLocation();
	}

	@Provides
	WildernessMapLocationsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(WildernessMapLocationsConfig.class);
	}

	public static WildernessMapPoint[] getMapPoints()
	{
		return mapPoints;
	}

	private final static WildernessMapPoint[] mapPoints = new WildernessMapPoint[]{
		new WildernessMapPoint("Rev Cave", new WorldPoint(3130, 3838, 0)), // REV CAVE
		new WildernessMapPoint("35 Port", new WorldPoint(3106, 3789, 0)), // 35 PORT
		new WildernessMapPoint("East Mossy", new WorldPoint(3180, 3795, 0)), // EAST MOSS GIANTS
		new WildernessMapPoint("27 Port", new WorldPoint(3035, 3724, 0)), // 27 PORT
		new WildernessMapPoint("GWD", new WorldPoint(3015, 3745, 0)), // GWD ENTRANCE
		new WildernessMapPoint("Bandit Camp", new WorldPoint(3037, 3698, 0)), // BANDIT CAMP
		new WildernessMapPoint("Cemetery", new WorldPoint(2975, 3749, 0)), // CEMETERY
		new WildernessMapPoint("Chaos Altar", new WorldPoint(2965, 3820, 0)), // CHAOS ALTAR
		new WildernessMapPoint("44 Port", new WorldPoint(2980, 3859, 0)), // 44 PORT
		new WildernessMapPoint("Rune Rock", new WorldPoint(3055, 3889, 0)), // RUNE ROCK
		new WildernessMapPoint("Spider Hill", new WorldPoint(3168, 3884, 0)), // SPIDER HILL
		new WildernessMapPoint("Lavas", new WorldPoint(3202, 3832, 0)), // LAVAS
		new WildernessMapPoint("50 Port", new WorldPoint(3307, 3920, 0)), // 50 PORT
		new WildernessMapPoint("New Gate", new WorldPoint(3336, 3900, 0)), // NEW GATE
		new WildernessMapPoint("Glory Hill", new WorldPoint(3340, 3875, 0)), // GLORY HILL
		new WildernessMapPoint("Fountain", new WorldPoint(3375, 3890, 0)), // FOUNTAIN OF RUNE
		new WildernessMapPoint("GDZ", new WorldPoint(3287, 3876, 0)), // GDZ
		new WildernessMapPoint("GAP", new WorldPoint(3250, 3857, 0)), // GAP
		new WildernessMapPoint("Light (E)", new WorldPoint(3250, 3887, 0)), // EAST LIGHT
		new WildernessMapPoint("NW Exit", new WorldPoint(3260, 3827, 0)), // NW EXIT
		new WildernessMapPoint("Vet'ion", new WorldPoint(3220, 3780, 0)), // VETION
		new WildernessMapPoint("Mid Exit", new WorldPoint(3284, 3803, 0)), // MID EXIT
		new WildernessMapPoint("Callisto", new WorldPoint(3292, 3855, 0)), // CALLISTO
		new WildernessMapPoint("NE Exit", new WorldPoint(3320, 3825, 0)), // NE EXIT
		new WildernessMapPoint("Venenatis", new WorldPoint(3316, 3796, 0)), // VENENATIS
		new WildernessMapPoint("South Exit", new WorldPoint(3282, 3770, 0)), // SOUTH EXIT
		new WildernessMapPoint("HSHOE", new WorldPoint(3311, 3771, 0)), // HORSE SHOE
		new WildernessMapPoint("SLAYER (N)", new WorldPoint(3293, 3751, 0)), // NORTH SLAYER
		new WildernessMapPoint("SLAYER (S)", new WorldPoint(3260, 3658, 0)), // SOUTH SLAYER
		new WildernessMapPoint("Chaos Temple (N)", new WorldPoint(3235, 3635, 0)), // CHAOS TEMPLE NORTH
		new WildernessMapPoint("Chaos Temple (S)", new WorldPoint(3237, 3578, 0)), // CHAOS TEMPLE SOUTH
		new WildernessMapPoint("19 Port", new WorldPoint(3227, 3662, 0)), // 19 PORT
		new WildernessMapPoint("13 Port", new WorldPoint(3156, 3613, 0)), // 13 PORT
		new WildernessMapPoint("Carralanger", new WorldPoint(3167, 3673, 0)), // CARRALANGER
		new WildernessMapPoint("Crab TP", new WorldPoint(3351, 3783, 0)), // WILDY CRAB
		new WildernessMapPoint("Old Gate", new WorldPoint(3224, 3911, 0)), // OLD GATE
		new WildernessMapPoint("Scorpia", new WorldPoint(3235, 3945, 0)), // OLD GATE
		new WildernessMapPoint("Kbd Cage (N)", new WorldPoint(3014, 3870, 0)), // NORTH KBD CAGE
		new WildernessMapPoint("Spindel", new WorldPoint(3180, 3744, 0)), // SPINDEL
		new WildernessMapPoint("Dwarves", new WorldPoint(3246, 3791, 0)), // DWARVES
		new WildernessMapPoint("Exit Cave (S)", new WorldPoint(3360, 10246, 0)), // EXIT CAVE SOUTH
		new WildernessMapPoint("Exit Cave (M)", new WorldPoint(3361, 10272, 0)), // EXIT CAVE MIDDLE
		new WildernessMapPoint("Exit Cave (NW)", new WorldPoint(3337, 10286, 0)), // EXIT CAVE NORTHWEST
		new WildernessMapPoint("Exit Cave (NE)", new WorldPoint(3381, 10286, 0)), // EXIT CAVE NORTHEAST
		new WildernessMapPoint("Slayer Cave Exit (N)", new WorldPoint(3406, 10145, 0)), // SLAYER CAVE EXIT NORTH
		new WildernessMapPoint("Black Demons", new WorldPoint(3369, 10125, 0)), // BLACK DEMONS
		new WildernessMapPoint("Black Dragons", new WorldPoint(3364, 10158, 0)), // BLACK DRAGONS
		new WildernessMapPoint("Abby Demons", new WorldPoint(3341,10163, 0)), // ABBY DEMONS
		new WildernessMapPoint("Lesser Demons", new WorldPoint(3338,10134, 0)), // LESSER DEMONS
		new WildernessMapPoint("Nechs", new WorldPoint(3335,10105, 0)), // NECHS
		new WildernessMapPoint("Ankous", new WorldPoint(3358,10078, 0)), // ANKOUS
		new WildernessMapPoint("Ice Giants", new WorldPoint(3342,10056, 0)), // ICE GIANTS
		new WildernessMapPoint("Green Dragons (N)", new WorldPoint(3400, 10125, 0)), // GREEN DRAGONS NORTH
		new WildernessMapPoint("Green Dragons (S)", new WorldPoint(3415, 10067, 0)), // GREEN DRAGONS SOUTH
		new WildernessMapPoint("Jellies", new WorldPoint(3430, 10102, 0)), // JELLIES
		new WildernessMapPoint("Hellhounds", new WorldPoint(3443, 10084, 0)), // HELLHOUNDS
		new WildernessMapPoint("Bandits", new WorldPoint(3430, 10073, 0)), // BANDITS
		new WildernessMapPoint("Dust Devils", new WorldPoint(3440, 10123, 0)), // DUST DEVILS
		new WildernessMapPoint("Greater Demons", new WorldPoint(3428, 10149, 0)), // GREATER DEMONS
		new WildernessMapPoint("Slayer Cave Exit (S)", new WorldPoint(3385, 10052, 0)), // SLAYER CAVE EXIT SOUTH
		new WildernessMapPoint("Bone Yard", new WorldPoint(3244, 3743, 0)), // BONE YARD
		new WildernessMapPoint("Ice Plat Gate", new WorldPoint(2947, 3905, 0)), // ICE PLATE GATE
		new WildernessMapPoint("Ice Plat", new WorldPoint(2972, 3935, 0)), // ICE PLATEAU
		new WildernessMapPoint("Wildy Agi", new WorldPoint(3001, 3949, 0)), // WILDY AGILITY
		new WildernessMapPoint("Wildy Agi Bridge", new WorldPoint(2998, 3920, 0)), // WILDY AGILITY BRIDGE
		new WildernessMapPoint("Larrans Ship", new WorldPoint(3018, 3959, 0)), // LARRANS SHIP
		new WildernessMapPoint("Pirate Hideout", new WorldPoint(3041, 3954, 0)), // PIRATE HIDEOUT
		new WildernessMapPoint("Deep Dungeon Entrance", new WorldPoint(3046, 3927, 0)), // DEEP WILDY DUNGEON ENTRANCE
		new WildernessMapPoint("Deep Wildy Dungeon", new WorldPoint(3044, 10322, 0)), // DEEP WILDY DUNGEON
		new WildernessMapPoint("Mining Hill", new WorldPoint(3056, 3943, 0)), // MINING HILL
		new WildernessMapPoint("Mage Bank", new WorldPoint(3091, 3959, 0)), // MAGE BANK
		new WildernessMapPoint("Mage Arena", new WorldPoint(3107, 3932, 0)), // MAGE ARENA
		new WildernessMapPoint("Lever", new WorldPoint(3155, 3925, 0)), // LEVER
		new WildernessMapPoint("Resource Area", new WorldPoint(3186, 3933, 0)), // RESOURCE AREA
		new WildernessMapPoint("Axe Hut", new WorldPoint(3190, 3960, 0)), // AXE HUT
		new WildernessMapPoint("South Rev Exit", new WorldPoint(3075, 3653, 0)), // SOUTH REV EXIT
		new WildernessMapPoint("Dark Warrior Castle", new WorldPoint(3029, 3631, 0)), // DARK WARRIOR CASTLE
		new WildernessMapPoint("Crazy Arch", new WorldPoint(2977, 3691, 0)), // CRAZY ARCH
		new WildernessMapPoint("Black Salamender", new WorldPoint(3298, 3677, 0)), // BLACK SALAMENDER
		new WildernessMapPoint("Zombie Pirate Chest", new WorldPoint(3367, 3625, 0)), // ZOMBIE PIRATE CHEST
	};
}
