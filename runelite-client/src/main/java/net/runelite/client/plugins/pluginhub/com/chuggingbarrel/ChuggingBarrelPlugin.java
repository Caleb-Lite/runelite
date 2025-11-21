package net.runelite.client.plugins.pluginhub.com.chuggingbarrel;

import net.runelite.client.plugins.pluginhub.com.chuggingbarrel.features.loadoutnames.LoadoutNames;
import net.runelite.client.plugins.pluginhub.com.chuggingbarrel.features.lowdoseindicator.LowDoseIndicator;
import net.runelite.client.plugins.pluginhub.com.chuggingbarrel.features.notbankedwarning.NotBankedWarning;
import net.runelite.client.plugins.pluginhub.com.chuggingbarrel.module.LifecycleComponentManager;
import net.runelite.client.plugins.pluginhub.com.chuggingbarrel.module.PluginLifecycleComponent;
import com.google.inject.Provides;

import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.List;

@PluginDescriptor(
    name = "Chugging Barrel"
)
public class ChuggingBarrelPlugin extends Plugin {
    private LifecycleComponentManager lifecycleComponentManager = null;

    @Provides
    ChuggingBarrelConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ChuggingBarrelConfig.class);
    }

    @Provides
    public List<PluginLifecycleComponent> provideComponents(
        LoadoutNames loadoutNames,
        LowDoseIndicator lowDoseIndicator,
        NotBankedWarning notBankedWarning
    ) {
        return List.of(loadoutNames, lowDoseIndicator, notBankedWarning);
    }

    @Override
    protected void startUp() throws Exception {
        if (lifecycleComponentManager == null) {
            lifecycleComponentManager = injector.getInstance(LifecycleComponentManager.class);
        }
        lifecycleComponentManager.startUp();
    }

    @Override
    protected void shutDown() throws Exception {
        lifecycleComponentManager.shutDown();
    }
}
