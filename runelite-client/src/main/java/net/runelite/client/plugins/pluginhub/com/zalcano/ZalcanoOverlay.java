package net.runelite.client.plugins.pluginhub.com.zalcano;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.*;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;


public class ZalcanoOverlay extends OverlayPanel {
    private final Client client;
    private final ZalcanoPlugin plugin;
    private final ZalcanoConfig config;


    @Inject
    private ZalcanoOverlay(Client client, ZalcanoPlugin plugin, ZalcanoConfig config)
    {
        super(plugin);
        setPosition(OverlayPosition.TOP_LEFT);
        setPriority(OverlayPriority.LOW);

        this.client = client;
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public String getName()
    {
        return "ZalcanoUtilityOverlay";
    }

    @Override
    public Dimension render(Graphics2D graphics2D)
    {
        if (!shouldShowOverlay()) return null;
        showTitle();
        if (config.showPlayerCount()) showPlayerCount();
        if (config.showHealth()) showHealth();
        if (config.showDamageDealt()) showDamageDealt();
        if (config.showToolSeedCalculations()) showToolSeedCalculations();
        return super.render(graphics2D);
    }

    private boolean shouldShowOverlay()
    {
        return plugin.playerInZalcanoArea();
    }

    private void showTitle()
    {
        panelComponent.getChildren().add(TitleComponent.builder().text("Zalcano").build());
    }

    private void showPlayerCount()
    {
        int playercount = plugin.getPlayersParticipating().size();
        panelComponent.getChildren().add(LineComponent.builder().left("Players: " + playercount).build());
    }

    private void showHealth()
    {
        int firstPhaseHealth = 640;
        int secondPhaseHealth = 360;
        int miningHp = plugin.getMiningHp();
        int phase = miningHp > firstPhaseHealth ? 1 : miningHp > secondPhaseHealth ? 2 : 3;

        Color color = decideColorBasedOnThreshold(miningHp, firstPhaseHealth, secondPhaseHealth);
        panelComponent.getChildren().add(LineComponent.builder().left("Mining HP:  " + miningHp + " / 1000 (Phase: " + phase + ")").leftColor(color).build());
        if (plugin.getZalcanoState() == ZalcanoStates.THROWING) panelComponent.getChildren().add(LineComponent.builder().left("Throwing HP:  " + plugin.getThrowingHp() + " / 300").build());
    }

    private void showDamageDealt()
    {
        Color color = decideColorBasedOnThreshold(plugin.getShieldDamageDealt(), plugin.getMinimumDamageUniquesShield(), plugin.getMinimumDamageLootShield());
        panelComponent.getChildren().add(LineComponent.builder().left("Shield Damage dealt: " + plugin.getShieldDamageDealt() + " / " + plugin.getMinimumDamageUniquesShield()).leftColor(color).build());

        color = decideColorBasedOnThreshold(plugin.getMiningDamageDealt(), plugin.getMinimumDamageUniquesMining(), plugin.getMinimumDamageLootMining());
        panelComponent.getChildren().add(LineComponent.builder().left("Mining Damage dealt: " + plugin.getMiningDamageDealt() + " / " + plugin.getMinimumDamageUniquesMining()).leftColor(color).build());
    }

    private Color decideColorBasedOnThreshold(int damage, int greenThreshold, int yellowThreshold)
    {
        if (damage >= greenThreshold) {
            return Color.GREEN;
        } else if (damage > yellowThreshold) {
            return Color.YELLOW;
        } else {
            return Color.RED;
        }
    }

    private void showToolSeedCalculations()
    {
        panelComponent.getChildren().add(LineComponent.builder().left("Chance of tool seed: " + String.format("%.3g", plugin.getChanceOfToolSeedTable() * 100) + "%").build());
    }
}

