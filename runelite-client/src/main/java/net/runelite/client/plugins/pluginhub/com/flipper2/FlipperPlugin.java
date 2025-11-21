package net.runelite.client.plugins.pluginhub.com.flipper2;

import net.runelite.client.plugins.pluginhub.com.flipper2.controllers.BuysController;
import net.runelite.client.plugins.pluginhub.com.flipper2.controllers.FlipsController;
import net.runelite.client.plugins.pluginhub.com.flipper2.controllers.SellsController;
import net.runelite.client.plugins.pluginhub.com.flipper2.helpers.GrandExchange;
import net.runelite.client.plugins.pluginhub.com.flipper2.helpers.Log;
import net.runelite.client.plugins.pluginhub.com.flipper2.helpers.Persistor;
import net.runelite.client.plugins.pluginhub.com.flipper2.helpers.UiUtilities;
import net.runelite.client.plugins.pluginhub.com.flipper2.models.Transaction;
import net.runelite.client.plugins.pluginhub.com.flipper2.views.TabManager;
import net.runelite.client.plugins.pluginhub.com.flipper2.views.inprogress.InProgressPage;
import com.google.gson.Gson;
import com.google.inject.Provides;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.swing.SwingUtilities;

import net.runelite.api.GrandExchangeOffer;
import net.runelite.api.GrandExchangeOfferState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GrandExchangeOfferChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ClientShutdown;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;
import net.runelite.api.GameState;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import java.awt.image.BufferedImage;

@PluginDescriptor(name = "Flipper2")
public class FlipperPlugin extends Plugin
{
	@Inject
	private ClientToolbar clientToolbar;
	@Inject
	private ItemManager itemManager;
	@Inject
	private FlipperConfig config;
	@Inject
	private ClientThread cThread;
	@Inject
	private Client client;
	private BuysController buysController;
	private SellsController sellsController;
	private FlipsController flipsController;

	private NavigationButton navButton;
	private TabManager tabManager;
	private InProgressPage inProgressPage;
	@Inject
	private Gson gson;

	@Override
	protected void startUp() throws Exception
	{
		try
		{
			Persistor.setUp();
			Persistor.gson = this.gson;
			this.tabManager = new TabManager();
			this.setUpNavigationButton();
			this.inProgressPage = new InProgressPage();
			this.buysController = new BuysController(itemManager, config, cThread);
			this.sellsController = new SellsController(itemManager, config, cThread);
			this.flipsController = new FlipsController(itemManager, config, cThread);
			this.changeToLoggedInView();

		}
		catch (Exception e)
		{
			Log.info("Flipper2 failed to start: " + e.getMessage());
		}
	}

	private void setUpNavigationButton()
	{
		navButton = NavigationButton
			.builder()
			.tooltip("Flipper2")
			.icon(
				ImageUtil.loadImageResource(
					getClass(),
					UiUtilities.flipperNavIcon
				)
			)
			.priority(4)
			.panel(tabManager).build();
		clientToolbar.addNavigation(navButton);
	}

	private void changeToLoggedInView()
	{
		SwingUtilities.invokeLater(() -> {
			this.tabManager.renderLoggedInView(
				buysController.getPage(),
				sellsController.getPage(),
				flipsController.getPage(),
				inProgressPage
			);
		});
	}

	private void saveAll()
	{
		try
		{
			buysController.saveTransactions();
			sellsController.saveTransactions();
			flipsController.saveTransactions();
		}
		catch (Exception error)
		{
			Log.info("Failed to save Flipper2 files");
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientToolbar.removeNavigation(navButton);
		this.saveAll();
	}

	@Subscribe
	public void onClientShutdown(ClientShutdown clientShutdownEvent) throws IOException
	{
		this.saveAll();
	}

	@Subscribe
	public void onGrandExchangeOfferChanged(GrandExchangeOfferChanged newOfferEvent)
	{
		int slot = newOfferEvent.getSlot();
		GrandExchangeOffer offer = newOfferEvent.getOffer();
		GrandExchangeOfferState offerState = offer.getState();

		if (offerState == GrandExchangeOfferState.EMPTY && client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		if (GrandExchange.checkIsBuy(offerState))
		{
			if (offerState == GrandExchangeOfferState.BOUGHT ||
				(offerState == GrandExchangeOfferState.CANCELLED_BUY && offer.getQuantitySold() > 0))
			{
				Transaction buy = buysController.upsertTransaction(offer, slot);
				if (buy != null)
				{
					buysController.saveTransactions();
					List<Transaction> sells = sellsController.getTransactions();
					flipsController.upsertFlip(buy, sells);
				}
			}
		}
		else
		{
			if (offerState == GrandExchangeOfferState.SOLD ||
				(offerState == GrandExchangeOfferState.CANCELLED_SELL && offer.getQuantitySold() > 0))
			{
				Transaction sell = sellsController.upsertTransaction(offer, slot);
				if (sell != null)
				{
					sellsController.saveTransactions();
					List<Transaction> buys = buysController.getTransactions();
					flipsController.upsertFlip(sell, buys);
				}
			}
		}

		updatePanel(slot, offer);
	}

	private void updatePanel(int slot, GrandExchangeOffer offer)
	{
		cThread.invoke(() -> {
			ItemComposition offerItem = itemManager.getItemComposition(offer.getItemId());
			BufferedImage itemImage = itemManager.getImage(offer.getItemId(), 1, false);
			SwingUtilities.invokeLater(() -> inProgressPage.updateOffer(offerItem, itemImage, offer, slot));
		});
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGIN_SCREEN)
		{
			inProgressPage.resetOffers();
		}
	}

	@Provides
	FlipperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FlipperConfig.class);
	}
}
//package com.flipper2;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import com.flipper2.helpers.Persistor;
//
//public class TestUtilities {
//    public static void cleanTestResultFiles() {
//        Path currentRelativePath = Paths.get("");
//        String testFilePath = currentRelativePath.toAbsolutePath().toString()
//                + "\\src\\test\\java\\com\\flipper2\\test-result-files";
//        // Delete any generated test-result-files
//        File deleteBuys = new File(testFilePath + "\\" + Persistor.BUYS_JSON_FILE);
//        File deleteSells = new File(testFilePath + "\\" + Persistor.SELLS_JSON_FILE);
//        deleteBuys.delete();
//        deleteSells.delete();
//
//    }
//
//}