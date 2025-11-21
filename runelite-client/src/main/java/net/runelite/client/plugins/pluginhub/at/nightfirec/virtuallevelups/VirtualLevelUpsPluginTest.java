package net.runelite.client.plugins.pluginhub.at.nightfirec.virtuallevelups;

import com.google.common.primitives.Ints;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import java.util.concurrent.ScheduledExecutorService;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import static net.runelite.api.Experience.MAX_REAL_LEVEL;
import static net.runelite.api.Experience.MAX_SKILL_XP;
import static net.runelite.api.Experience.MAX_VIRT_LEVEL;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.Notifier;
import net.runelite.client.RuneLite;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.game.chatbox.ChatboxPanelManager;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageCapture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VirtualLevelUpsPluginTest
{
	private static final Skill SKILL = Skill.HITPOINTS;
	private static GameStateChanged HOPPING = new GameStateChanged();
	private static GameStateChanged LOADING = new GameStateChanged();
	private static GameStateChanged LOGGED_IN = new GameStateChanged();

	@Inject
	private VirtualLevelUpsPlugin plugin;

	@Mock
	@Bind
	private ImageCapture imageCapture;

	@Mock
	@Bind
	private ClientUI clientUI;

	@Mock
	@Bind
	private OverlayManager overlayManager;

	@Mock
	@Bind
	private Client client;

	@Mock
	@Bind
	private ChatboxPanelManager chatboxPanelManager;

	@Mock
	@Bind
	private Notifier notifier;

	@Mock
	@Bind
	private ConfigManager configManager;

	@Mock
	@Bind
	private RuneLiteConfig runeLiteConfig;

	@Mock
	@Bind
	private VirtualLevelUpsConfig config;

	@Mock
	@Bind
	private ScheduledExecutorService executor;

	static {
		HOPPING.setGameState(GameState.HOPPING);
		LOADING.setGameState(GameState.LOADING);
		LOGGED_IN.setGameState(GameState.LOGGED_IN);
	}

	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VirtualLevelUpsPlugin.class);
		RuneLite.main(args);
	}

	@Before
	public void before()
	{
		Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
	}

	@Test
	public void virtualLevelGained()
	{
		int level = MAX_REAL_LEVEL;
		statChanged(SKILL, Experience.getXpForLevel(level), level);

		level++;
		statChanged(SKILL, Experience.getXpForLevel(level), level);

		assertEquals(1, plugin.getSkillsLeveledUp().size());
		assertTrue(plugin.getSkillsLeveledUp().contains(SKILL));
	}

	@Test
	public void testXpSetAfterNullState()
	{
		final int level = MAX_REAL_LEVEL + 1;
		statChanged(SKILL, Experience.getXpForLevel(level), level);

		assertTrue(plugin.getSkillsLeveledUp().isEmpty());
	}

	@Test
	public void testXpBelow100()
	{
		int level = MAX_REAL_LEVEL - 1;
		statChanged(SKILL, Experience.getXpForLevel(level), level);

		level++;
		statChanged(SKILL, Experience.getXpForLevel(level), level);

		assertTrue(plugin.getSkillsLeveledUp().isEmpty());
	}

	@Test
	public void testNoLevelGained()
	{
		final int level = MAX_REAL_LEVEL;
		statChanged(SKILL, Experience.getXpForLevel(level), level);
		statChanged(SKILL, Experience.getXpForLevel(level) + 1, level);

		assertTrue(plugin.getSkillsLeveledUp().isEmpty());
	}

	@Test
	public void textMaxXp()
	{
		final int level = MAX_VIRT_LEVEL;
		statChanged(SKILL, MAX_SKILL_XP - 1, level);
		statChanged(SKILL, MAX_SKILL_XP, level);

		assertEquals(1, plugin.getSkillsLeveledUp().size());
		assertTrue(plugin.getSkillsLeveledUp().contains(SKILL));
	}

	@Test
	public void testFakeXpDropAtMaxXp()
	{
		statChanged(SKILL, MAX_SKILL_XP, MAX_VIRT_LEVEL);
		statChanged(SKILL, MAX_SKILL_XP, MAX_VIRT_LEVEL);

		assertEquals(0, plugin.getSkillsLeveledUp().size());
	}

	@Test
	public void testSkillBoostAtMaxXp()
	{
		statChanged(SKILL, MAX_SKILL_XP, MAX_VIRT_LEVEL);
		statChanged(SKILL, MAX_SKILL_XP, MAX_VIRT_LEVEL, MAX_REAL_LEVEL + 1);

		assertEquals(0, plugin.getSkillsLeveledUp().size());
	}

	@Test
	public void testWorldHopAtMaxXp()
	{
		statChanged(SKILL, MAX_SKILL_XP, MAX_VIRT_LEVEL);

		plugin.onGameStateChanged(HOPPING);
		plugin.onGameStateChanged(LOADING);
		plugin.onGameStateChanged(LOGGED_IN);

		statChanged(SKILL, MAX_SKILL_XP, MAX_VIRT_LEVEL);

		assertEquals(0, plugin.getSkillsLeveledUp().size());
	}

	@Test
	public void testLoadingGameStateBeforeVirtualLevelUp()
	{
		int level = MAX_REAL_LEVEL;
		statChanged(SKILL, Experience.getXpForLevel(level), level);

		plugin.onGameStateChanged(LOADING);
		plugin.onGameStateChanged(LOGGED_IN);

		level++;
		statChanged(SKILL, Experience.getXpForLevel(level), level);

		assertEquals(1, plugin.getSkillsLeveledUp().size());
		assertTrue(plugin.getSkillsLeveledUp().contains(SKILL));
	}

	private void statChanged(final Skill skill, final int xp, final int level)
	{
		final int constrainedLevel = Ints.constrainToRange(level, 0, MAX_REAL_LEVEL);
		statChanged(skill, xp, constrainedLevel, constrainedLevel);
	}

	private void statChanged(final Skill skill, final int xp, final int level, final int boostedLevel)
	{
		when(client.getSkillExperience(skill)).thenReturn(xp);
		plugin.onStatChanged(new StatChanged(skill, xp, level, boostedLevel));
	}
}

