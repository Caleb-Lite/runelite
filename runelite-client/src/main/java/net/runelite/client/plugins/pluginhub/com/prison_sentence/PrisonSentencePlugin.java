package net.runelite.client.plugins.pluginhub.com.prison_sentence;

import net.runelite.client.plugins.pluginhub.com.prison_sentence.sources.Current;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.sources.ISource;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.sources.Manual;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Item;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "PrisonSentence"
)
public class PrisonSentencePlugin extends Plugin
{
	@Inject
	public Client client;

	@Inject
	private PrisonSentenceConfig config;

	@Inject
	public ItemManager itemManager;
	@Inject
	public InfoBoxManager infoBoxManager;
	@Inject
	public ClientThread clientThread;

	private Current myCurrent = null;
	private Sentence sentence = null;

	private boolean myLoadInitialValues = false;

	private List<String> myErrors = new ArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		myCurrent = new Current(this);
		Setup();

		if (client.getGameState() == GameState.LOGGED_IN)
		{
			myLoadInitialValues = true;
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		TearDown();
		myCurrent = null;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			FlushErrors();
			myLoadInitialValues = true;
		}
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (myLoadInitialValues)
		{
			myCurrent.LoadInitialValues();
			myLoadInitialValues = false;
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged)
	{
		if (!configChanged.getGroup().equals(Constants.CONFIG_NAME))
			return;

		ReloadConfig();
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		assert myCurrent != null;

		myCurrent.onVarbitChanged(varbitChanged);
	}

	@Provides
    PrisonSentenceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PrisonSentenceConfig.class);
	}

	public void Error(String aMessage)
	{
		if (!client.isClientThread())
		{
			clientThread.invokeLater(() -> Error(aMessage));
			return;
		}

		if (client.getGameState() != GameState.LOGGED_IN)
		{
			myErrors.add(aMessage);
			return;
		}

		client.addChatMessage(ChatMessageType.PLAYERRELATED, "", "[PrisonSentence] " + aMessage, null);
	}

	void FlushErrors()
	{
		for (String error : myErrors)
		{
			client.addChatMessage(ChatMessageType.PLAYERRELATED, "",  "[PrisonSentence] " + error, null);
		}

		myErrors.clear();
	}

	void ReloadConfig()
	{
		TearDown();
		Setup();
	}

	void Setup()
	{
		assert sentence == null;

		ISource source = null;

		try
		{
			source = new Manual(config.period(), Instant.parse(config.startTime()), config.startAmount() );
		}
		catch (DateTimeParseException exception)
		{
			Error("The manual start date could not be parsed: " + config.startTime());
			Error(exception.getMessage());
			return;
		}

		sentence = new Sentence(source, myCurrent, config.prisonType(), this, config.amount());
	}

	void TearDown()
	{
		if (sentence != null)
		{
			sentence.Shutdown();
			sentence = null;
		}
	}
}
