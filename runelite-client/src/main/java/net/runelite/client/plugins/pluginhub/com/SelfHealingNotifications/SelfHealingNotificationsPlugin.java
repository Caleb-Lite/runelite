package net.runelite.client.plugins.pluginhub.com.SelfHealingNotifications;

import com.google.inject.Provides;
import javax.inject.Inject;
import java.util.Arrays;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.Skill;


@PluginDescriptor(
	name = "Self Healing Notifications",
	description = "Shows self healing including regen to the player"
)

@Slf4j
public class SelfHealingNotificationsPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private SelfHealingNotificationsConfig SelfHealingNotificationsconfig;

	@Inject
	private ConfigManager configManager;

	@Provides
	SelfHealingNotificationsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SelfHealingNotificationsConfig.class);
	}

	@Getter
	private final int[] lastSkillLevels = new int[Skill.values().length - 1];


	@Override
	protected void startUp() throws Exception {
		Arrays.fill(lastSkillLevels, 126);
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged) {
		Player player = client.getLocalPlayer();
		Skill skill = statChanged.getSkill();
		int ignoreRegen = SelfHealingNotificationsconfig.ignoreRegen();

		if (skill != Skill.HITPOINTS) {
			return;
		}

		int skillIdx = skill.ordinal();
		int last = lastSkillLevels[skillIdx];
		int cur = client.getBoostedSkillLevel(skill);
		int dif = cur - last;

		if (cur > last)
		{
			if(dif > ignoreRegen)
			{
					if (this.SelfHealingNotificationsconfig.sendToChar()== SelfHealingNotificationsconfig.sendToChar().Default)
					{
						player.setOverheadText("You just healed for " + String.valueOf(dif) + " health.");
						player.setOverheadCycle(120);
					}
					if (this.SelfHealingNotificationsconfig.sendToChar()== SelfHealingNotificationsconfig.sendToChar().Simple)
					{
						player.setOverheadText("+" + String.valueOf(dif));
						player.setOverheadCycle(120);
					}
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You just healed for " + String.valueOf(dif) + " health.", null);
			}

			}
		lastSkillLevels[skillIdx] = cur;
	}
}
