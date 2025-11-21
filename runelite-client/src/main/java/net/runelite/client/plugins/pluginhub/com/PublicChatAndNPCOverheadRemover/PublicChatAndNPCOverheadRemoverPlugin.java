package net.runelite.client.plugins.pluginhub.com.PublicChatAndNPCOverheadRemover;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.NPC;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
		name = "Public Chat And NPC Overhead Chat Remover",
		description = "Removes Selected Automatic Public Chat Entries and NPC Overhead Chat Messages"
)
public class PublicChatAndNPCOverheadRemoverPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private PublicChatAndNPCOverheadRemoverPluginConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private  PublicChatAndNPCOverheadRemoverPluginOverlay overlay;

	static final String CONFIG_GROUP = "PublicChatAndNPCOverheadRemoverConfigGroup";

	// Special Attack Messages
	private static final String AXE_SPECIAL_TEXT = "Chop chop!";
	private static final String PICKAXE_SPECIAL_TEXT = "Smashing!";
	private static final String HARPOON_SPECIAL_TEXT = "Here fishy fishies!";

	// Other Messages
	private static final String CUPPA_TEA_TEXT = "Aaah, nothing like a nice cuppa tea!";
	private static final String TOADY_TEXT = "Come here toady!";

	// NPC IDs
	private static final Set<Integer> CERBERUS = Set.of(5862, 5863, 5866);
	private static final Set<Integer> SUMMONED_SOUL = Set.of(5867, 5868, 5869);
	private static final Set<Integer> NEX = Set.of(11278, 11279, 11280, 11281, 11282);
	private static final Set<Integer> BARBARIAN = Set.of(3055, 3056, 3057, 3058, 3059, 3060, 3061, 3062, 3064, 3065, 3066, 3067, 3068, 3069, 3070, 3071, 3072, 3256, 3262, 10676, 10677, 10678, 10679, 10984, 10985, 10986, 10987, 10988);
	private static final Set<Integer> GRAARDOR = Set.of(2215, 6494, 12444);
	private static final Set<Integer> KRIL = Set.of(3129, 6495, 12446);
	private static final Set<Integer> ZILYANA = Set.of(2205, 6493, 12445);
	private static final Set<Integer> KREE = Set.of(3162, 6492, 12443);
	private static final Set<Integer> DERANGEDARCH = Set.of(7806);
	private static final Set<Integer> CRAZYARCH = Set.of(6618);
	private static final Set<Integer> CHAOSFANATIC = Set.of(6619);
	private static final Set<Integer> ROGUE = Set.of(6603);
	private static final Set<Integer> TOWN_CRIER = Set.of(276, 277, 278, 279, 280, 6823, 10887);
	private static final Set<Integer> CHAOS_FANATIC = Set.of(6619);
	private static final Set<Integer> RAMOCEAN = Set.of(6937);
	private static final Set<Integer> PROSPECTOR_PERCY = Set.of(6562);
	private static final Set<Integer> POSTIE_PETE = Set.of(3291);
	private static final Set<Integer> HUEY_SEER = Set.of(13997, 14008);
	private static final Set<Integer> HUEY_WORKER = Set.of(14003, 14004);
	private static final Set<Integer> RT_BRANDA = Set.of(12596, 14148);
	private static final Set<Integer> RT_ELDRIC = Set.of(14147, 14149);
	private static final Set<Integer> AMOXLIATL = Set.of(13685);
	private static final Set<Integer> BENNY = Set.of(5216);
	private static final Set<Integer> MASTER_FARMER = Set.of(5730, 5731, 11940, 11941, 13236, 13237, 13238, 13239, 13240, 13241, 13242, 13243, 14755, 14756, 14757, 14758);
	private static final Set<Integer> ARDY_KNIGHT = Set.of(3297, 3300, 8854, 11902, 11936);
	private static final Set<Integer> FARMING_GUILD_CAT = Set.of(8594);
	private static final Set<Integer> CALVARION = Set.of(11993, 11994, 11995);
	private static final Set<Integer> VETION = Set.of(6611, 6612, 12002);
	private static final Set<Integer> GE_GUIDES = Set.of(5449, 2152, 3115, 5451, 5450, 5452, 8193);
	private static final Set<Integer> GE_RECRUITERS = Set.of(10732, 8644);

	boolean debugNPC;
	private Set<Integer> MANUAL_LIST = new HashSet<>();

	// Special Attacks
	private Set<String> activeMutesOH;
	private Set<String> activeMutesPC;

	// NPCs
	private Set<Integer> mutedNPCsOH;


	@Override
	protected void startUp() throws Exception
	{
		debugNPC = config.debugNPC();
		overlayManager.add(overlay);

		// Special Attacks
		activeMutesOH = new HashSet<>();
		activeMutesPC = new HashSet<>();

		// NPCs
		mutedNPCsOH = new HashSet<>();


		readConfig();
	}

	private Set<Integer> splitList(String configStr)
	{
		Set<Integer> result = new HashSet<>();

		if (configStr != null && !configStr.isEmpty())
		{
			for (String str : configStr.split(","))
			{
				str = str.trim();
				if (!str.isEmpty())
				{
					try
					{
						result.add(Integer.parseInt(str));
					}
					catch (NumberFormatException e)
					{
						log.warn("Invalid NPC ID in config: {}", str);
					}
				}
			}
		}
		return result;
	}

	private void readConfig()
	{
		// Special Attacks
		activeMutesOH.clear();
		activeMutesPC.clear();

		// NPCs
		mutedNPCsOH.clear();
//		mutedNPCsPC.clear();

		// Special Attacks - Overheads
		if (config.muteAxesOH())
		{
			activeMutesOH.add(AXE_SPECIAL_TEXT);
		}
		if (config.mutePickaxesOH())
		{
			activeMutesOH.add(PICKAXE_SPECIAL_TEXT);
		}
		if (config.muteHarpoonsOH())
		{
			activeMutesOH.add(HARPOON_SPECIAL_TEXT);
		}

		// Special Attacks - Public Chat
		if (config.muteAxesPC())
		{
			activeMutesPC.add(AXE_SPECIAL_TEXT);
		}
		if (config.mutePickaxesPC())
		{
			activeMutesPC.add(PICKAXE_SPECIAL_TEXT);
		}
		if (config.muteHarpoonsPC())
		{
			activeMutesPC.add(HARPOON_SPECIAL_TEXT);
		}

		// NPCs - Overheads
		if (config.muteCerberusOH())
		{
			mutedNPCsOH.addAll(CERBERUS);
		}
		if (config.muteSummonedSoulOH())
		{
			mutedNPCsOH.addAll(SUMMONED_SOUL);
		}
		if (config.muteNexOH())
		{
			mutedNPCsOH.addAll(NEX);
		}
		if (config.muteBarbarianOH())
		{
			mutedNPCsOH.addAll(BARBARIAN);
		}
		if (config.muteKrilOH())
		{
			mutedNPCsOH.addAll(KRIL);
		}
		if (config.muteGeneralGraardorOH())
		{
			mutedNPCsOH.addAll(GRAARDOR);
		}
		if (config.muteKreeOH())
		{
			mutedNPCsOH.addAll(KREE);
		}
		if (config.muteCommanderZilyanaOH())
		{
			mutedNPCsOH.addAll(ZILYANA);
		}
		if (config.muteDerangedArchOH())
		{
			mutedNPCsOH.addAll(DERANGEDARCH);
		}
		if (config.muteCrazyArchOH())
		{
			mutedNPCsOH.addAll(CRAZYARCH);
		}
		if (config.muteChaosFanaticOH())
		{
			mutedNPCsOH.addAll(CHAOSFANATIC);
		}
		if (config.muteRoguesOH())
		{
			mutedNPCsOH.addAll(ROGUE);
		}
		if (config.muteTownCrierOH())
		{
			mutedNPCsOH.addAll(TOWN_CRIER);
		}
		if (config.muteChaosFanaticOH())
		{
			mutedNPCsOH.addAll(CHAOS_FANATIC);
		}
		if (config.muteRamoceanOH())
		{
			mutedNPCsOH.addAll(RAMOCEAN);
		}
		if (config.muteProspectorPercyOH())
		{
			mutedNPCsOH.addAll(PROSPECTOR_PERCY);
		}
		if (config.muteRTBrandaOH())
		{
			mutedNPCsOH.addAll(RT_BRANDA);
		}
		if (config.muteRTEldricOH())
		{
			mutedNPCsOH.addAll(RT_ELDRIC);
		}
		if (config.muteAmoxliatlOH())
		{
			mutedNPCsOH.addAll(AMOXLIATL);
		}
		if (config.muteAmoxliatlOH())
		{
			mutedNPCsOH.addAll(AMOXLIATL);
		}
		if (config.muteBennyOH())
		{
			mutedNPCsOH.addAll(BENNY);
		}
		if (config.muteHueySeerOH())
		{
			mutedNPCsOH.addAll(HUEY_SEER);
		}
		if (config.muteHueyWorkerOH())
		{
			mutedNPCsOH.addAll(HUEY_WORKER);
		}
		if (config.mutePostiePeteOH())
		{
			mutedNPCsOH.addAll(POSTIE_PETE);
		}
		if (config.muteGEGuidesOH())
		{
			mutedNPCsOH.addAll(GE_GUIDES);
		}
		if (config.muteGERecruitersOH())
		{
			mutedNPCsOH.addAll(GE_RECRUITERS);
		}
		if (config.muteCalvOH())
		{
			mutedNPCsOH.addAll(CALVARION);
		}
		if (config.muteVetionOH())
		{
			mutedNPCsOH.addAll(VETION);
		}
		if (config.muteFarmingGuildCatOH())
		{
			mutedNPCsOH.addAll(FARMING_GUILD_CAT);
		}
		if (config.muteArdyKnightOH())
		{
			mutedNPCsOH.addAll(ARDY_KNIGHT);
		}
		if (config.muteMasterFarmerOH())
		{
			mutedNPCsOH.addAll(MASTER_FARMER);
		}

		// Other - Overheads
		if (config.muteTeaOH())
		{
			activeMutesOH.add(CUPPA_TEA_TEXT);
		}
		if (config.muteToadyOH())
		{
			activeMutesOH.add(TOADY_TEXT);
		}

		// Other - Overheads
		if (config.muteTeaPC())
		{
			activeMutesPC.add(CUPPA_TEA_TEXT);
		}
		if (config.muteToadyPC())
		{
			activeMutesPC.add(TOADY_TEXT);
		}

		// Manual - Overheads
		mutedNPCsOH.addAll(MANUAL_LIST);


	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);

		activeMutesOH.clear();
		activeMutesPC.clear();

		mutedNPCsOH.clear();

	}

	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged event)
	{
		Actor actor = event.getActor();
		if ((actor instanceof Player) && (activeMutesOH.contains(event.getOverheadText())))
		{
			event.getActor().setOverheadText(null);
		}
		if (actor instanceof NPC)
		{
			NPC npc = (NPC) actor;
			if (mutedNPCsOH.contains(npc.getId()))
			{
				npc.setOverheadText(null);
			}
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		Player localPlayer = client.getLocalPlayer();
		if (chatMessage.getType() != ChatMessageType.PUBLICCHAT)
		{
			return;
		}
		final ChatLineBuffer lineBuffer = client.getChatLineMap().get(ChatMessageType.PUBLICCHAT.getType());
		if (lineBuffer == null)
		{
			return;
		}
		if (activeMutesPC.contains(chatMessage.getMessage()))
		{
			lineBuffer.removeMessageNode(chatMessage.getMessageNode());
			clientThread.invoke(() -> client.runScript(ScriptID.BUILD_CHATBOX));
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals(CONFIG_GROUP))
		{
			debugNPC = config.debugNPC();
			MANUAL_LIST = splitList(config.NPCIDsOH());
			readConfig();
		}
	}

	@Provides
	PublicChatAndNPCOverheadRemoverPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PublicChatAndNPCOverheadRemoverPluginConfig.class);
	}
}