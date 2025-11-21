package net.runelite.client.plugins.pluginhub.com.TimersCA.Bosses;

import net.runelite.client.plugins.pluginhub.com.TimersCA.Boss;
import net.runelite.client.plugins.pluginhub.com.TimersCA.TimersCAConfig;
import net.runelite.client.plugins.pluginhub.com.TimersCA.TimersCAPlugin;
import net.runelite.api.Client;

import javax.inject.Inject;

public class Nex extends Boss {
    @Inject
    public Nex(Client client, TimersCAPlugin plugin, TimersCAConfig config) {
        super(client, plugin, config);
    }
}
