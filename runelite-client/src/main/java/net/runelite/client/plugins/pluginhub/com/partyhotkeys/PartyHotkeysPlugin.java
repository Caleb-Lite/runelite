package net.runelite.client.plugins.pluginhub.com.partyhotkeys;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ClientTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.PartyChanged;
import net.runelite.client.input.KeyManager;
import net.runelite.client.party.PartyService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.party.PartyPlugin;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.HotkeyListener;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@PluginDependency(PartyPlugin.class)
@Slf4j
@PluginDescriptor(
	name = "Party Hotkeys",
		description = "Custom Hotkeys for rejoining previous party & joining preset party names",
		tags= "party,hotkeys"
)
public class PartyHotkeysPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientUI clientUI;

	@Inject
	private ClientThread clientThread;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private PartyHotkeysConfig config;

	@Inject
	private ConfigManager configManager;

	@Inject
	private PartyService partyService;

	@Inject
	private KeyManager keyManager;

	final String PARTY_CONFIG_GROUP = "party";
	final String PREVIOUS_PARTY_ID = "previousPartyId";

	boolean isInParty = false;

	Instant lastLeave = null;

	boolean lastFocusStatus = false;

	private final CustomHotkeyListener previousKeyListener = new CustomHotkeyListener(() -> config.rejoinPreviousKey())
	{
		@Override
		public void hotkeyPressed()
		{
			RejoinPreviousParty();
		}
	};

	private final CustomHotkeyListener leavePartyKeyListener = new CustomHotkeyListener(() -> config.leavePartyKey())
	{
		@Override
		public void hotkeyPressed()
		{
			LeaveParty();
		}
	};

	private final CustomHotkeyListener joinParty1KeyListener = new CustomHotkeyListener(() -> config.joinParty1Key())
	{
		@Override
		public void hotkeyPressed()
		{
			changeParty(config.presetParty1());
		}
	};

	private final CustomHotkeyListener joinParty2KeyListener = new CustomHotkeyListener(() -> config.joinParty2Key())
	{
		@Override
		public void hotkeyPressed()
		{
			changeParty(config.presetParty2());
		}
	};

	private final CustomHotkeyListener joinParty3KeyListener = new CustomHotkeyListener(() -> config.joinParty3Key())
	{
		@Override
		public void hotkeyPressed()
		{
			changeParty(config.presetParty3());
		}
	};

	@Provides
	PartyHotkeysConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PartyHotkeysConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		CheckPartyStatus();
		keyManager.registerKeyListener(previousKeyListener);
		keyManager.registerKeyListener(leavePartyKeyListener);
		keyManager.registerKeyListener(joinParty1KeyListener);
		keyManager.registerKeyListener(joinParty2KeyListener);
		keyManager.registerKeyListener(joinParty3KeyListener);
	}

	@Override
	protected void shutDown() throws Exception
	{
		RemoveInfobox();
		keyManager.unregisterKeyListener(previousKeyListener);
		keyManager.unregisterKeyListener(leavePartyKeyListener);
		keyManager.unregisterKeyListener(joinParty1KeyListener);
		keyManager.unregisterKeyListener(joinParty2KeyListener);
		keyManager.unregisterKeyListener(joinParty3KeyListener);
	}

	@Subscribe
	public void onClientTick(ClientTick event)
	{
		//Fix chat being locked & hotkeys from being unusable if user loses focus
		if(lastFocusStatus != clientUI.isFocused()){
			if(!clientUI.isFocused()){
				if(previousKeyListener.isPressed()){
					previousKeyListener.ReleaseHotkey();
				}
				if(leavePartyKeyListener.isPressed()){
					leavePartyKeyListener.ReleaseHotkey();
				}
				if(joinParty1KeyListener.isPressed()){
					joinParty1KeyListener.ReleaseHotkey();
				}
				if(joinParty2KeyListener.isPressed()){
					joinParty2KeyListener.ReleaseHotkey();
				}
				if(joinParty3KeyListener.isPressed()){
					joinParty3KeyListener.ReleaseHotkey();
				}
			}
		}
		lastFocusStatus = clientUI.isFocused();
	}

	private void CreateInfobox()
	{
		RemoveInfobox();
		final BufferedImage image = ImageUtil.loadImageResource(getClass(), "/icon_Disconnected.png");
		final ConnectionIndicator infobox = new ConnectionIndicator(image,this,"You aren't in a party!");
		infoBoxManager.addInfoBox(infobox);
	}

	private void RemoveInfobox()
	{
		infoBoxManager.removeIf(t -> t instanceof ConnectionIndicator);
	}

	public void CheckPartyStatus()
	{
		isInParty = (partyService != null && partyService.isInParty());

		if(config.disconnectedWarning()){
			if (!isInParty){
				CreateInfobox();
			} else {
				RemoveInfobox();
			}
		}

	}

	public void changeParty(String passphrase)
	{

		//prevent potential spamming misuse by delaying user from joining a new party for a few moments
		if(lastLeave != null && Duration.between(lastLeave,Instant.now()).getSeconds() <= 5){
			//join attempt sfx
			clientThread.invokeLater(()->{client.playSoundEffect(2266);});
			return;
		}

		//only allow joins while logged in
		if(client.getGameState() != GameState.LOGGED_IN && client.getGameState() != GameState.LOADING){
			return;
		}

		//prevent joining while already in a party
		if (isInParty){
			return;
		}

		passphrase = passphrase.replace(" ", "-").trim();
		if (passphrase.length() == 0) {
			return;
		}

		for (int i = 0; i < passphrase.length(); ++i)
		{
			char ch = passphrase.charAt(i);
			if (!Character.isLetter(ch) && !Character.isDigit(ch) && ch != '-')
			{
				return;
			}
		}

		partyService.changeParty(passphrase);

	}

	public void LeaveParty(){
		if(isInParty){
			//leave party sfx
			clientThread.invokeLater(()->{client.playSoundEffect(3930);});
			partyService.changeParty(null);
			lastLeave = Instant.now();
		}
	}

	public void RejoinPreviousParty(){
		changeParty(getPreviousPartyId());
	}

	public String getPreviousPartyId(){
		return Optional.ofNullable(configManager.getConfiguration(PARTY_CONFIG_GROUP, PREVIOUS_PARTY_ID)).orElse("");
	}


	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if(event.getGroup().equals(PartyHotkeysConfig.GROUP)){
			if(event.getKey().equals("disconnectedWarning")){
				if(event.getNewValue().equals("false")){
					RemoveInfobox();
				}else{
					CheckPartyStatus();
				}
			}
		}
	}


	@Subscribe
	public void onPartyChanged(final PartyChanged event)
	{
		CheckPartyStatus();
	}

}

