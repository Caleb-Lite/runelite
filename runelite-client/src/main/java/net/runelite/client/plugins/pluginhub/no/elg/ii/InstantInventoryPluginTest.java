package net.runelite.client.plugins.pluginhub.no.elg.ii;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import net.runelite.api.events.GameStateChanged;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.pluginhub.no.elg.ii.test.IntegrationTestHelper;
import org.junit.Test;

public class InstantInventoryPluginTest extends IntegrationTestHelper {

  @Test
  public void startUp_calls_updateAllFeatures() {
    plugin.startUp();
    verify(featureManager).updateAllFeatureStatus();
  }

  @Test
  public void shutDown_disables_all_features() {
    plugin.shutDown();
    verify(featureManager).disableAllFeatures();
  }

  @Test
  public void onGameStateChanged_calls_nothing_on_incorrect_group() {
    plugin.startUp();
    plugin.onGameStateChanged(new GameStateChanged());

    verify(dropFeature, times(2)).reset();
    verify(cleanHerbFeature, times(2)).reset();
  }

  @Test
  public void onConfigChanged_calls_updateAllFeatureStatus_on_correct_group() {
    ConfigChanged configChanged = new ConfigChanged();
    configChanged.setGroup(InstantInventoryConfig.GROUP);
    plugin.onConfigChanged(configChanged);
    verify(featureManager).updateAllFeatureStatus();
  }

  @Test
  public void onConfigChanged_calls_nothing_on_incorrect_group() {
    ConfigChanged configChanged = new ConfigChanged();
    configChanged.setGroup("");
    plugin.onConfigChanged(configChanged);
    verify(featureManager, never()).updateAllFeatureStatus();
  }
}

