package net.runelite.client.plugins.pluginhub.com.exchangelogger;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GrandExchangeOfferChanged;
import net.runelite.client.RuneLite;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import java.io.File;

@Slf4j
@PluginDescriptor(
	name = "Exchange Logger",
	description = "Stores all GE transactions in log file(s)"
)
public class ExchangeLoggerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ExchangeLoggerConfig config;

	private final String dirName = File.separator + "exchange-logger";
	private final String logName = File.separator + "exchange.log";

	public static final String CONFIG_GROUP = "exchangelogger";
	private ExchangeLoggerFormat format;
	private boolean rewrite;
	public String logPath;

	private ExchangeLoggerWriter writer;

	@Override
	protected void startUp() throws Exception
	{
		format = config.logFormat();
		rewrite = config.rewriteLog();

		String dir = RuneLite.RUNELITE_DIR.getPath() + dirName;
		new File(dir).mkdirs();
		logPath = dir + logName;

		writer = new ExchangeLoggerWriter(logPath, format, rewrite);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("ExchangeLogger stopped!");
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals(CONFIG_GROUP))
		{
			if (event.getKey().equals("logFormat"))		//Change log file output format
			{
				format = config.logFormat();
				writer.setFormat(format);
			}
			else if (event.getKey().equals("rewriteLog"))	//Delete all old data when logging in. Only uses one log file
			{
				rewrite = config.rewriteLog();
				writer.setRewrite(rewrite);

			}
		}
	}

	@Subscribe
	public void onGrandExchangeOfferChanged(GrandExchangeOfferChanged offerEvent)
	{
		// Trades are cleared by the client during LOGIN_SCREEN/HOPPING/LOGGING_IN, ignore those
		if (client.getGameState() == GameState.LOGGED_IN)
		{
			writer.grandExchangeEvent(offerEvent);
		}
	}

	@Provides
	ExchangeLoggerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExchangeLoggerConfig.class);
	}
}

