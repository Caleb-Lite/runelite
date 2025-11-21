package net.runelite.client.plugins.pluginhub.com.preeocxp;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.OverlayMenuClicked;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.xptracker.XpTrackerPlugin;
import net.runelite.client.task.Schedule;
import net.runelite.client.ui.overlay.OverlayManager;
import javax.inject.Inject;
import java.time.temporal.ChronoUnit;

@PluginDescriptor(
		name = "Pre Eoc Xp Drops",
		description = "Customize Xp drops and display the 2010 xp Counter (WIP - currently just 2010 xp drops)",
		tags = {"experience", "levels", "overlay", "xp drop"},
		enabledByDefault = false
)

@PluginDependency(XpTrackerPlugin.class)


public class PreEocXpPlugin extends Plugin
{
	private static long loginXp = 0;
	long preXp = 0;
	public static int xpDrop;
	public static int tickCounter = 0;
	public static boolean sentXp = true;
	private static boolean configWasChanged = true;
	public static int newTick;
	public static long skillXp;

	@Inject
	private PreEocXpConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private PreEocXpOverlay overlay;

	@Inject
	Client client;

	/**
	 *
	 * @param configManager get the Plugin's configuration
	 * @return
	 */
	@Provides
	PreEocXpConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PreEocXpConfig.class);
	}

	/**
	 * starts up the overlay
	 */
	@Override
	protected void startUp() {
		overlayManager.add(overlay);
	}

	/**
	 * removes the overlay
	 */
	@Override
	protected void shutDown() {
		overlayManager.remove(overlay);
		client.getWidget(WidgetInfo.EXPERIENCE_TRACKER).setHidden(false);
	}

	/**
	 * onGameTick cause it works with xp drops disabled, and longs so people with more than 2b xp can use this.
	 * on login, grabs the overall xp, and signals to the overlay class that xp has been updated.
	 * if overallXp is to be displayed, skillXp is set.
	 * Once xp has been fetched once, check if XP has been gained on the gameTick.
	 * If so, update xpDrop by comparing to the last fetched xp loginXp. Update fetched xp loginXp.
	 * @param gameTick when a gameTick event is sent - doStuff.
	 */
	@Subscribe
	public void onGameTick(GameTick gameTick)
	{

		tickCounter ++;
		long overallXp = client.getOverallExperience();
		if (config.displaySkill() == ConfigSkill.OVERALL) {
			skillXp = overallXp;
		}
		preXp = loginXp;

		//skillXp = client.getSkillExperience(config.displaySkill());

		if (loginXp != 0 && (overallXp - preXp <= 0))
		{
			return;
		}
		if (loginXp != 0)
		{
			xpDrop = (int) (overallXp - preXp);
			sentXp = true;
			preXp = overallXp;
			loginXp = preXp;
		}
		else
		{
			loginXp = client.getOverallExperience();
			sentXp = true;
		}
	}

	/**
	 * grabs the fake xp, and signals to the overlay class that xp has been updated.
	 * Once FakeXp has been fetched once, check if more FakeXp has been gained on the same gameTick and add it.
	 * Otherwise, reset the FakeXp to the next amount.
	 * @param event when a FakeXpDrop event is sent - doStuff.
	 */
	@Subscribe
	public void onFakeXpDrop(FakeXpDrop event)
	{

		if ( newTick == tickCounter ){
			xpDrop = xpDrop + event.getXp();
		}
		else {
			   xpDrop = event.getXp(); 
		}
		newTick = tickCounter;
		sentXp = true;
	}

	@Subscribe
	public void onScriptPreFired (ScriptPreFired scriptPreFired)
	{
		Widget xpDisplay = client.getWidget(WidgetInfo.EXPERIENCE_TRACKER);
		if(xpDisplay!=null){
			xpDisplay.setHidden(true);
		}
	}

	/**
	 * @param statChanged when a statChanged event is sent - update the skill xp.
	 */
	@Subscribe
	public void onStatChanged(StatChanged statChanged)
	{
		if (config.displaySkill() != ConfigSkill.OVERALL) {
			skillXp = client.getSkillExperience(config.displaySkill().toSkill());
	}
	}
	/**
	 * sets a global toggle when a config has been changed.
	 * updates the skillXp as well, since it won't trigger a statchange, and thus an update.
	 * @param configChanged
	 */
	@Subscribe
    public void onConfigChanged(ConfigChanged configChanged)
    {
	    setConfigUpdateState(true);
		if (config.displaySkill() != ConfigSkill.OVERALL) {
			skillXp = client.getSkillExperience(config.displaySkill().toSkill());
		}
    }


    public static boolean getConfigUpdateState()
	{
		return configWasChanged;
	}


	public static void setConfigUpdateState (boolean configSetter)
	{
		configWasChanged = configSetter;
	}
	public static long getLoginXp()
	{
		return loginXp;
	}

	@Schedule(
			period = 1,
			unit = ChronoUnit.SECONDS
	)
	/**
	 * resets the xp when called.
	 */
	private void resetState()
	{
		loginXp = 0;
	}

	@Subscribe
	public void onOverlayMenuClicked(final OverlayMenuClicked event)
	{
		if (!(event.getEntry().getMenuAction() == MenuAction.RUNELITE_OVERLAY
				&& event.getOverlay() == overlay))
		{
			return;
		}
	}

	/**
	 * Reset the xp when logging out.
	 * @param event when the GameState changes, do things according to the state.
	 */
	@Subscribe
	public void onGameStateChanged(GameStateChanged event) {
		switch (event.getGameState())
		{
			case LOGGED_IN:
				loginXp = client.getOverallExperience();
				startUp();

			case HOPPING:
			case LOGGING_IN:
			case LOGIN_SCREEN:
				resetState();
				break;
		}
	}
}
