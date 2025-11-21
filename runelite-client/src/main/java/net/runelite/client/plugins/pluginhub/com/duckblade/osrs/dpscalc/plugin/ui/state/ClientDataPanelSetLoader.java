package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.ui.state;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.plugin.osdata.clientdata.ClientDataProvider;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import net.runelite.client.callback.ClientThread;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ClientDataPanelSetLoader
{

	private final ClientDataProvider clientDataProvider;
	private final ClientThread clientThread;

}
