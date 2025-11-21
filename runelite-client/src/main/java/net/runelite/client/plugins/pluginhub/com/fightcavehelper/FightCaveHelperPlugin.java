package net.runelite.client.plugins.pluginhub.com.fightcavehelper;

import net.runelite.client.plugins.pluginhub.com.fightcavehelper.ui.FightCaveHelperPanel;
import java.io.IOException;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

@Slf4j
@PluginDescriptor(
        name = "Fight Cave Helper",
        description = "Panel to predict and show Fight Cave waves",
        tags = {"fight", "cave", "jad", "pvm"}
)
public class FightCaveHelperPlugin extends Plugin {

    @Inject
    private ClientToolbar clientToolbar;
	private NavigationButton navButton;

	@Override
    protected void startUp() throws IOException
	{
		log.debug("fight cave helper started.");
		FightCaveHelperPanel panel = new FightCaveHelperPanel();

        navButton = NavigationButton.builder()
                .tooltip("Fight Cave Helper")
                .icon(ImageUtil.loadImageResource(getClass(), "/firecape.png"))
                .priority(50)
                .panel(panel)
                .build();

        clientToolbar.addNavigation(navButton);
    }

    @Override
    protected void shutDown(){
        clientToolbar.removeNavigation(navButton);
        log.info("fight cave helper stopped!");
    }

}
