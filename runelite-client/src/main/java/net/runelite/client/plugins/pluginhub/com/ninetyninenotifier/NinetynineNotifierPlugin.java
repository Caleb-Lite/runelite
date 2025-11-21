package net.runelite.client.plugins.pluginhub.com.ninetyninenotifier;


import net.runelite.client.ui.overlay.OverlayManager;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.Skill;
import net.runelite.api.events.StatChanged;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@PluginDescriptor(
		name = "99 Notifier",
		description = "Notifies you when you're close to 99 in any skill.",
		tags = {"99", "xp", "notifier"}
)
public class NinetynineNotifierPlugin extends Plugin
{
	private static final int XP_99 = 13034431;
	private long pluginStartTime;

	@Inject private Client client;
	@Inject private NinetynineNotifierConfig config;
	@Inject private Notifier notifier;
	@Inject private NinetynineNotifierOverlay overlay;
	@Inject private OverlayManager overlayManager;

	private final Set<Skill> notifiedSkills = new HashSet<>();

	@Override
	protected void startUp()
	{
		pluginStartTime = System.currentTimeMillis();
		notifiedSkills.clear();

		if (config.enableOverlay())
		{
			overlayManager.add(overlay);
		}
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

    @Subscribe
	public void onStatChanged(StatChanged event)
	{
		Skill skill = event.getSkill();

		if (noNotify(skill))
		{
			return;
		}

		int xp = event.getXp();
		int level = Experience.getLevelForXp(xp);
		long timeSinceStart = System.currentTimeMillis() - pluginStartTime;

		if (level >= 99 || notifiedSkills.contains(skill))
		{
			return;
		}

		int remainingXp = XP_99 - xp;
		int threshold = config.threshold();

		if (remainingXp <= threshold)
		{
			String message = "<col=ff0000>Only " + remainingXp + " XP left to 99 in " + skill.getName() + "!";

			// Avoid notifies during client startup phase
			if (config.enableSystemNotifier() && timeSinceStart < 5000)
			{
				notifier.notify(message);
			}else{
				client.addChatMessage(
						net.runelite.api.ChatMessageType.GAMEMESSAGE,
						"",
						message,
						null
				);
			}

			notifiedSkills.add(skill);

		}
	}

	public int getNumberOfSkillsCloseTo99()
	{
		int threshold = config.threshold();
		int count = 0;

		for (Skill skill : Skill.values())
		{
			if (skill == Skill.OVERALL || noNotify(skill))
			{
				continue;
			}

			int xp = client.getSkillExperience(skill);
			int level = Experience.getLevelForXp(xp);

			if (level < 99 && (XP_99 - xp) <= threshold)
			{
				count++;
			}
		}

		return count;
	}

	// Returns true if skill is disabled in config
	private boolean noNotify(Skill skill)
	{
		switch (skill)
		{
			case ATTACK: return !config.enableAttack();
			case DEFENCE: return !config.enableDefence();
			case STRENGTH: return !config.enableStrength();
			case HITPOINTS: return !config.enableHitpoints();
			case RANGED: return !config.enableRanged();
			case PRAYER: return !config.enablePrayer();
			case MAGIC: return !config.enableMagic();
			case COOKING: return !config.enableCooking();
			case WOODCUTTING: return !config.enableWoodcutting();
			case FLETCHING: return !config.enableFletching();
			case FISHING: return !config.enableFishing();
			case FIREMAKING: return !config.enableFiremaking();
			case CRAFTING: return !config.enableCrafting();
			case SMITHING: return !config.enableSmithing();
			case MINING: return !config.enableMining();
			case HERBLORE: return !config.enableHerblore();
			case AGILITY: return !config.enableAgility();
			case THIEVING: return !config.enableThieving();
			case SLAYER: return !config.enableSlayer();
			case FARMING: return !config.enableFarming();
			case RUNECRAFT: return !config.enableRunecraft();
			case HUNTER: return !config.enableHunter();
			case CONSTRUCTION: return !config.enableConstruction();
			default: return true;
		}
	}


	@Subscribe
	public void onConfigChanged(net.runelite.client.events.ConfigChanged event)
	{
		if (!event.getGroup().equals("ninetyninenotifier"))
		{
			return;
		}

		if (event.getKey().equals("enableOverlay"))
		{
			if (config.enableOverlay())
			{
				overlayManager.add(overlay);
			}
			else
			{
				overlayManager.remove(overlay);
			}
		}
	}


	@Provides
	NinetynineNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NinetynineNotifierConfig.class);
	}
}


