package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.module;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.TombsOfAmascutConfig;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidState;

public interface PluginLifecycleComponent
{

	default boolean isEnabled(TombsOfAmascutConfig config, RaidState raidState)
	{
		return true;
	}

	void startUp();

	void shutDown();

}
