package net.runelite.client.plugins.pluginhub.com.clairemira;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.Skill;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Untrimmed Slayer Cape Helper",
	description = "Provides helpful information to obtain an untrimmed 99 Slayer cape.",
	tags = {"untrimmed","slayer","cape","helper","cannon"}
)
public class UntrimmedSlayerCapeHelperPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private UntrimmedSlayerCapeHelperConfig config;

	@Inject
	private UntrimmedSlayerCapeHelperOverlayPanel overlayPanel;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlayPanel);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlayPanel);
	}

	@Provides
	UntrimmedSlayerCapeHelperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(UntrimmedSlayerCapeHelperConfig.class);
	}

	public int getSlayerExpRemaining()
	{
		int exp99 = Experience.getXpForLevel(99);
		int slayerExp = client.getSkillExperience(Skill.SLAYER);

		return exp99 - slayerExp;
	}

	public int getProjectedHitpointsExp()
	{
		int hpExp = client.getSkillExperience(Skill.HITPOINTS);
		double projectedHitpointsExp = getSlayerExpRemaining() * 1.33 + hpExp;

		return (int) Math.round(projectedHitpointsExp);
	}

	public int getSlayerOnlyExpRemaining()
    {
        int exp99 = Experience.getXpForLevel(99);
        double slayerOnlyExpRemaining = (getProjectedHitpointsExp() - exp99) / 1.33;

        return (int) Math.round(slayerOnlyExpRemaining);
    }
}
