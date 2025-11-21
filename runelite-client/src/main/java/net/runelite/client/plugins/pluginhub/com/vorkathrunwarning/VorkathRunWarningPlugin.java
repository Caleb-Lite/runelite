package net.runelite.client.plugins.pluginhub.com.vorkathrunwarning;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NpcID;
import net.runelite.api.events.NpcChanged;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.Set;

@Slf4j
@PluginDescriptor(
	name = "Vorkath Run Warning"
)
public class VorkathRunWarningPlugin extends Plugin
{
	private static final int RUN_VARIABLE_ID = 173;
	private static final Set<Integer> VORKATH_ALIVE_IDS = Set.of(
			NpcID.VORKATH,
			NpcID.VORKATH_8060,
			NpcID.VORKATH_8061,
			NpcID.VORKATH_11959);

	@Inject
	private Client client;

	@Inject
	private VorkathRunWarningConfig config;

	@Inject
	private VorkathRunWarningTintOverlay tintOverlay;

	@Inject
	private VorkathRunWarningTextOverlay textOverlay;

	@Inject
	private OverlayManager overlayManager;

	@Getter (AccessLevel.PACKAGE)
	private boolean shouldWarn;

	private boolean isFighting;


	@Override
	protected void startUp() throws Exception
	{
		updateShouldWarn();
		overlayManager.add(tintOverlay);
		overlayManager.add(textOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(tintOverlay);
		overlayManager.remove(textOverlay);
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event) {
		if(event.getVarpId() == RUN_VARIABLE_ID) {
			this.updateShouldWarn();
		}
	}

	@Subscribe
	public void onNpcChanged(NpcChanged event) {
		if (event.getOld().getId() == NpcID.VORKATH_8059 && event.getNpc().getId() == NpcID.VORKATH_8058) {
			isFighting = true;
			this.updateShouldWarn();
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned event) {
		if (VORKATH_ALIVE_IDS.contains(event.getNpc().getId())) {
			isFighting = false;
			this.updateShouldWarn();
		}
	}

	private void updateShouldWarn() {
        this.shouldWarn = this.isFighting && this.isRunEnabled();
	}

	private boolean isRunEnabled() {
		return client.getVarpValue(RUN_VARIABLE_ID) == 1;
	}

	@Provides
	VorkathRunWarningConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(VorkathRunWarningConfig.class);
	}
}
