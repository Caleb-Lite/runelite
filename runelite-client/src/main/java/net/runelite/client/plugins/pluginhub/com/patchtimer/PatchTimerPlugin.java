package net.runelite.client.plugins.pluginhub.com.patchtimer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "1.5 Woodcutting"
)

public class PatchTimerPlugin extends Plugin
{
	static final int LEFT_TREE = 4771;
	static final int MIDDLE_TREE = 4772;
	static final int RIGHT_TREE = 4773;
	static final int TEAK_STUMP = 17;
	static final int MAHOGANY_STUMP = 40;

	static final int TEAK_XP = 85;
	static final int MAHOGANY_XP = 125;

	static final int TEAK_RESPAWN = 18;
	static final int MAHOGANY_RESPAWN = 41;

	@Getter
	private final List<TreeTimer> treeTimerList = new LinkedList<>();

	private int lastWoodcuttingXp = 0;
	private boolean treeFell = false;

	@Inject
	private Client client;

	@Inject
	private PatchTimerConfig config;

	@Inject
	private TimerOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		this.treeTimerList.clear();
	}
	private int lastFallenTreeId = -1;

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if ((event.getVarbitId() == LEFT_TREE || event.getVarbitId() == MIDDLE_TREE || event.getVarbitId() == RIGHT_TREE)
				&& (event.getValue() == TEAK_STUMP || event.getValue() == MAHOGANY_STUMP)){
			treeFell = true;
			lastFallenTreeId= event.getVarbitId();
			log.debug("Tree Fell: ID = " + lastFallenTreeId);
		}
	}
	@Subscribe
	public void onStatChanged(StatChanged statChanged) {
		if (statChanged.getSkill() != Skill.WOODCUTTING) {
			return;
		}
		int woodcuttingXp = statChanged.getXp();
		int skillGained = woodcuttingXp - lastWoodcuttingXp;
		lastWoodcuttingXp = woodcuttingXp;

		if (treeFell && lastFallenTreeId != -1)
		{
			int teakRespawn = config.getAssumeTick() ? TEAK_RESPAWN -1 : TEAK_RESPAWN;
			int mahoganyRespawn = config.getAssumeTick() ? MAHOGANY_RESPAWN -1 : MAHOGANY_RESPAWN;

			if (isInRange(skillGained, TEAK_XP)) {
				log.debug("Single Teak log");
				addTreeTimer(lastFallenTreeId, teakRespawn);
			}
			else if (isInRange(skillGained, MAHOGANY_XP)) {
				log.debug("Single Mahogany log");
				addTreeTimer(lastFallenTreeId, mahoganyRespawn);
			}
		else if (isInRange(skillGained, TEAK_XP * 2)) {
		log.debug("Double Teak log");
		addTreeTimer(lastFallenTreeId, TEAK_RESPAWN);
		}

		else if (isInRange(skillGained, MAHOGANY_XP * 2)) {
		log.debug("Double Mahogany log");
		addTreeTimer(lastFallenTreeId, MAHOGANY_RESPAWN);
			}
			treeFell = false;
			lastFallenTreeId = -1;
		}
	}
private void addTreeTimer(int treeId, int respawnDuration)
{
	WorldPoint treeLocation = getTreeLocation(treeId);
	if (treeLocation != null)
	{
		treeTimerList.add(new TreeTimer(treeLocation, respawnDuration));
		log.debug("Added TreeTimer for Tree ID " + treeId + " at " + treeLocation + " with respawn duration " + respawnDuration);
	}
	else
	{
		log.warn("Tree ID " + treeId + " does not have a mapped location!");
	}
}

private WorldPoint getTreeLocation(int treeId)
{
	switch (treeId)
	{
		case LEFT_TREE:
			return new WorldPoint(3715, 3835, 0);
		case MIDDLE_TREE:
			return new WorldPoint(3708, 3833, 0);
		case RIGHT_TREE:
			return new WorldPoint(3702, 3837, 0);
		default:
			return null;
	}
}
	@Subscribe
	public void onGameTick(GameTick event){
			this.treeTimerList.removeIf(TreeTimer -> {
				TreeTimer.decrement();
				return TreeTimer.getTicksLeft() < 0;
			});
	}
private boolean isInRange(int value, int base)
{
	return value >= base && value <= base * 1.13;
}
	@Provides
	PatchTimerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PatchTimerConfig.class);
	}
}
