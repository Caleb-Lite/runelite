package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.fortis.module;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.fortis.FortisColosseumConfig;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.fortis.util.ColosseumState;

public interface PluginLifecycleComponent
{

	default boolean isEnabled(FortisColosseumConfig config, ColosseumState colosseumState)
	{
		return true;
	}

	void startUp();

	void shutDown();

}
