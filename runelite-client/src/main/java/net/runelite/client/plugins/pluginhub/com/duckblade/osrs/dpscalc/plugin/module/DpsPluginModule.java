package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.module;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.DpsMenuActionListener;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.live.LiveDpsService;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.live.overlay.LiveDpsOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.live.overlay.OverlayMinimizerService;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.live.party.PartyDpsService;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.clientdata.ClientDataProvider;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.clientdata.InteractingNpcTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.clientdata.RuneLiteClientDataProvider;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.wiki.ItemStatsProvider;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.wiki.NpcDataProvider;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.wiki.WikiItemStatsProvider;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.wiki.WikiNpcDataProvider;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.NavButtonManager;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DpsPluginModule extends AbstractModule
{

	@Override
	protected void configure()
	{
		Multibinder<PluginLifecycleComponent> lifecycleComponents = Multibinder.newSetBinder(binder(), PluginLifecycleComponent.class);
		lifecycleComponents.addBinding().to(DpsMenuActionListener.class);
		lifecycleComponents.addBinding().to(InteractingNpcTracker.class);
		lifecycleComponents.addBinding().to(LiveDpsService.class);
		lifecycleComponents.addBinding().to(LiveDpsOverlay.class);
		lifecycleComponents.addBinding().to(PartyDpsService.class);
		lifecycleComponents.addBinding().to(NavButtonManager.class);
		lifecycleComponents.addBinding().to(OverlayMinimizerService.class);

		bind(ItemStatsProvider.class).to(WikiItemStatsProvider.class);
		bind(NpcDataProvider.class).to(WikiNpcDataProvider.class);
		bind(ClientDataProvider.class).to(RuneLiteClientDataProvider.class);
	}

}
