package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.TombsOfAmascutConfig;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.module.PluginLifecycleComponent;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidRoom;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidState;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.runelite.api.Client;

@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CameraShakeDisabler implements PluginLifecycleComponent
{

	private final Client client;

	private boolean wasDisabled;

	@Override
	public boolean isEnabled(TombsOfAmascutConfig config, RaidState raidState)
	{
		return config.disableCameraShake() && raidState.getCurrentRoom() == RaidRoom.WARDENS;
	}

	@Override
	public void startUp()
	{
		wasDisabled = client.isCameraShakeDisabled();
		client.setCameraShakeDisabled(true);
	}

	@Override
	public void shutDown()
	{
		client.setCameraShakeDisabled(wasDisabled);
	}
}
