package net.runelite.client.plugins.pluginhub.com.BetterGodwarsOverlay;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ClientTick;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.ComponentID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.InfoBoxMenuClicked;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@PluginDescriptor(
	name = "Better Godwars Overlay",
	description = "Goodbye to Jagex's ugly GWD overlay",
	tags = {"gwd", "pvm", "bossing"}
)
public class BetterGodwarsOverlayPlugin extends Plugin
{
	public static final String CONFIG_GROUP = "BetterGodwarsOverlayPlugin";

	@Inject
	private OverlayManager overlayManager;

	@Inject
	Client client;

	@Inject
	private BetterGodwarsOverlayOverlay gwdOverlay;

	@Inject
	InfoBoxManager infoBoxManager;

	@Inject
	BetterGodwarsOverlayConfig config;

	@Inject
	private ConfigManager configManager;

	BetterGodwarsGodInfo[] godsInfo = new BetterGodwarsGodInfo[5];

	// Completed combat achievement varbit value
	public static final int COMBAT_ACHIEVEMENT_COMPLETE = 2;

	@Provides
	BetterGodwarsOverlayConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BetterGodwarsOverlayConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		godsInfo = new BetterGodwarsGodInfo[]{
				new BetterGodwarsGodInfo(this, BetterGodwarsOverlayGods.ARMADYL, config.hideArmadyl()),
				new BetterGodwarsGodInfo(this, BetterGodwarsOverlayGods.BANDOS, config.hideBandos()),
				new BetterGodwarsGodInfo(this, BetterGodwarsOverlayGods.SARADOMIN, config.hideSaradomin()),
				new BetterGodwarsGodInfo(this, BetterGodwarsOverlayGods.ZAMORAK, config.hideZamorak()),
				new BetterGodwarsGodInfo(this, BetterGodwarsOverlayGods.ZAROS, config.hideAncient())
		};
		hideGwdWidget();
		overlayManager.add(gwdOverlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(gwdOverlay);
		Arrays.stream(godsInfo).filter(Objects::nonNull).forEach(BetterGodwarsGodInfo::remove);

		//restore widgets
		restoreGwdWidget();
	}

	@Subscribe
	public void onClientTick(ClientTick event) {
		if (playerInGwd()) {
			hideGwdWidget();
			Arrays.stream(godsInfo).filter(Objects::nonNull).forEach(BetterGodwarsGodInfo::update);
		} else {
			Arrays.stream(godsInfo).filter(Objects::nonNull).forEach(BetterGodwarsGodInfo::remove);
		}
	}

	@Subscribe
	public void onInfoBoxMenuClicked(InfoBoxMenuClicked event) {
		if (!(event.getInfoBox() instanceof BetterGodwarsInfoBox) || !event.getEntry().getOption().equals("Hide")) {
			return;
		}
		Arrays.stream(godsInfo).filter(Objects::nonNull).forEach(godInfo -> {
			if (godInfo.getGod().getName().equals(event.getEntry().getTarget())) {
				godInfo.setHidden(true);
				String configKey = "hide" + godInfo.getGod().getName();
				configManager.setConfiguration(CONFIG_GROUP, configKey, true);
				log.debug("Setting config key {}.{} to true", CONFIG_GROUP, configKey);
			}
		});
	}

	private Widget getGwdWidget() {
		return client.getWidget(ComponentID.GWD_KC_LAYER);
	}

	public boolean playerInGwd() {
		return getGwdWidget() != null;
	}

	public void hideGwdWidget() {
		Widget gwdWidget = getGwdWidget();
		if (gwdWidget != null) {
			gwdWidget.setHidden(true);
		}
	}

	public void restoreGwdWidget() {
		Widget gwdWidget = getGwdWidget();
		if (gwdWidget != null) {
			gwdWidget.setHidden(false);
		}
	}

	public int getRequiredKc() {
		for (BetterGodwarsKcRequired required : BetterGodwarsKcRequired.values()) {
			if (required == BetterGodwarsKcRequired.DEFAULT) break;

			boolean completedCa = client.getVarbitValue(required.getCombatAchievementVarbitId()) == COMBAT_ACHIEVEMENT_COMPLETE;
			if (completedCa) {
				return required.getKcRequired();
			}
		}
		return BetterGodwarsKcRequired.DEFAULT.getKcRequired();
	}
}
