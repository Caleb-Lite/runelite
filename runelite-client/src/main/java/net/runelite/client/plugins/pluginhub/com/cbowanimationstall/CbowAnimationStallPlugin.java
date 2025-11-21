package net.runelite.client.plugins.pluginhub.com.cbowanimationstall;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import net.runelite.api.*;
import net.runelite.api.events.*;

@Slf4j
@PluginDescriptor(
	name = "Cbow Animation Stall",
	description = "Revert crossbow animation during PVM, uses the original/PVP variant with an animation stall"
)
public class CbowAnimationStallPlugin extends Plugin
{
	private static final int CROSSBOW_PVM_ANIMATION = 7552;
	private static final int CROSSBOW_PVP_ANIMATION = 4230;

	@Inject
	private Client client;

	@Inject
	private CbowAnimationStallConfig config;

	@Subscribe(priority = -1001.0f) // Run after the "Weapon/Gear/Animation Replacer" plugin
	public void onAnimationChanged(AnimationChanged e)
	{
		if (client.getVarbitValue(Varbits.PVP_SPEC_ORB) == 1) return; //disable during pvp

		Actor actor = e.getActor();
		if (config.localPlayerOnly() && !actor.equals(client.getLocalPlayer())) return;

		if (actor.getAnimation() == CROSSBOW_PVM_ANIMATION)
		{
			actor.setAnimation(CROSSBOW_PVP_ANIMATION);
		}
	}

	@Provides
	CbowAnimationStallConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CbowAnimationStallConfig.class);
	}
}
