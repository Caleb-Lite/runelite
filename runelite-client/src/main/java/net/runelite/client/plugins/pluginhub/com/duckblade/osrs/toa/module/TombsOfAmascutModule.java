package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.module;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.TombsOfAmascutConfig;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.AdrenalineCooldown;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.CameraShakeDisabler;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.DepositBoxFilter;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.DepositBoxFilterOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.FadeDisabler;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.InvocationScreenshot;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.LeftClickBankAll;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.PathLevelTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.QuickProceedSwaps;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.SmellingSaltsCooldown;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.apmeken.ApmekenBaboonIndicator;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.apmeken.ApmekenBaboonIndicatorOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.apmeken.ApmekenWaveInstaller;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.boss.akkha.AkkhaShadowHealth;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.boss.akkha.AkkhaShadowHealthOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.boss.baba.BabaSarcophagusWarning;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.boss.kephri.swarmer.SwarmerDataManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.boss.kephri.swarmer.SwarmerOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.boss.kephri.swarmer.SwarmerPanelManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.het.beamtimer.BeamTimerOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.het.beamtimer.BeamTimerTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.het.pickaxe.DepositPickaxeOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.het.pickaxe.DepositPickaxePreventEntry;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.het.pickaxe.DepositPickaxeSwap;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.het.solver.HetSolver;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.het.solver.HetSolverOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.hporbs.HpOrbManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.invocationpresets.InvocationPresetsManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.pointstracker.PartyPointsTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.pointstracker.PointsOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.pointstracker.PointsTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.pointstracker.PurpleWeightingManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.pointstracker.PurpleWeightingPartyBoardManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.SkipObeliskOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.overlay.AdditionPuzzleSolver;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.overlay.LightPuzzleSolver;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.overlay.MatchingPuzzleSolver;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.overlay.ObeliskPuzzleSolver;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.overlay.ScabarasOverlayManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.overlay.SequencePuzzleSolver;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.scabaras.panel.ScabarasPanelManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.timetracking.SplitsInfoBox;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.timetracking.SplitsOverlay;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.timetracking.SplitsTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.timetracking.TargetTimeManager;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.tomb.CursedPhalanxDetector;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.tomb.DryStreakTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.tomb.SarcophagusOpeningSoundPlayer;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.tomb.SarcophagusRecolorer;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.features.updatenotifier.UpdateNotifier;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidCompletionTracker;
import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.toa.util.RaidStateTracker;
import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;

@Slf4j
public class TombsOfAmascutModule extends AbstractModule
{

	@Override
	protected void configure()
	{
		bind(ComponentManager.class);
	}


	@Provides
	Set<PluginLifecycleComponent> lifecycleComponents(
		AdditionPuzzleSolver additionPuzzleSolver,
		AdrenalineCooldown adrenalineCooldown,
		AkkhaShadowHealth akkhaShadowHealth,
		AkkhaShadowHealthOverlay akkhaShadowHealthOverlay,
		ApmekenBaboonIndicator apmekenBaboonIndicator,
		ApmekenBaboonIndicatorOverlay apmekenBaboonIndicatorOverlay,
		ApmekenWaveInstaller apmekenWaveInstaller,
		BabaSarcophagusWarning babaSarcophagusWarning,
		BeamTimerOverlay beamTimerOverlay,
		BeamTimerTracker beamTimerTracker,
		CameraShakeDisabler cameraShakeDisabler,
		CursedPhalanxDetector cursedPhalanxDetector,
		DepositBoxFilter depositBoxFilter,
		DepositBoxFilterOverlay depositBoxFilterOverlay,
		DepositPickaxeOverlay depositPickaxeOverlay,
		DepositPickaxePreventEntry depositPickaxePreventEntry,
		DepositPickaxeSwap depositPickaxeSwap,
		DryStreakTracker dryStreakTracker,
		FadeDisabler fadeDisabler,
		HetSolver hetSolver,
		HetSolverOverlay hetSolverOverlay,
		HpOrbManager hpOrbManager,
		InvocationPresetsManager invocationPresetsManager,
		InvocationScreenshot invocationScreenshot,
		LeftClickBankAll leftClickBankAll,
		LightPuzzleSolver lightPuzzleSolver,
		MatchingPuzzleSolver matchingPuzzleSolver,
		ObeliskPuzzleSolver obeliskPuzzleSolver,
		PartyPointsTracker partyPointsTracker,
		PathLevelTracker pathLevelTracker,
		PointsOverlay pointsOverlay,
		PointsTracker pointsTracker,
		PurpleWeightingManager purpleWeightingManager,
		PurpleWeightingPartyBoardManager purpleWeightingPartyBoardManager,
		QuickProceedSwaps quickProceedSwaps,
		RaidCompletionTracker raidCompletionTracker,
		RaidStateTracker raidStateTracker,
		SarcophagusOpeningSoundPlayer sarcophagusOpeningSoundPlayer,
		SarcophagusRecolorer sarcophagusRecolorer,
		ScabarasOverlayManager scabarasOverlayManager,
		ScabarasPanelManager scabarasPanelManager,
		SequencePuzzleSolver sequencePuzzleSolver,
		SkipObeliskOverlay skipObeliskOverlay,
		SmellingSaltsCooldown smellingSaltsCooldown,
		SplitsInfoBox splitsInfoBox,
		SplitsOverlay splitsOverlay,
		SplitsTracker splitsTracker,
		SwarmerOverlay swarmerOverlay,
		SwarmerPanelManager swarmerPanelManager,
		SwarmerDataManager swarmerDataManager,
		TargetTimeManager targetTimeManager,
		UpdateNotifier updateNotifier
	)
	{
		return ImmutableSet.of(
			additionPuzzleSolver,
			adrenalineCooldown,
			akkhaShadowHealth,
			akkhaShadowHealthOverlay,
			apmekenBaboonIndicator,
			apmekenBaboonIndicatorOverlay,
			apmekenWaveInstaller,
			babaSarcophagusWarning,
			beamTimerOverlay,
			beamTimerTracker,
			cameraShakeDisabler,
			cursedPhalanxDetector,
			depositBoxFilter,
			depositBoxFilterOverlay,
			depositPickaxeOverlay,
			depositPickaxePreventEntry,
			depositPickaxeSwap,
			dryStreakTracker,
			fadeDisabler,
			hetSolver,
			hetSolverOverlay,
			hpOrbManager,
			invocationPresetsManager,
			invocationScreenshot,
			leftClickBankAll,
			lightPuzzleSolver,
			matchingPuzzleSolver,
			obeliskPuzzleSolver,
			partyPointsTracker,
			pathLevelTracker,
			pointsOverlay,
			pointsTracker,
			purpleWeightingManager,
			purpleWeightingPartyBoardManager,
			quickProceedSwaps,
			raidCompletionTracker,
			raidStateTracker,
			sarcophagusOpeningSoundPlayer,
			sarcophagusRecolorer,
			scabarasOverlayManager,
			scabarasPanelManager,
			sequencePuzzleSolver,
			skipObeliskOverlay,
			smellingSaltsCooldown,
			splitsInfoBox,
			splitsOverlay,
			splitsTracker,
			swarmerOverlay,
			swarmerPanelManager,
			swarmerDataManager,
			targetTimeManager,
			updateNotifier
		);
	}

	@Provides
	@Singleton
	TombsOfAmascutConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TombsOfAmascutConfig.class);
	}

}
