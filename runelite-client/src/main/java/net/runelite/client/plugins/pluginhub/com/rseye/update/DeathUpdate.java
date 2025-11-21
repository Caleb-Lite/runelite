package net.runelite.client.plugins.pluginhub.com.rseye.update;

import net.runelite.client.plugins.pluginhub.com.rseye.io.RequestHandler;
import net.runelite.client.plugins.pluginhub.com.rseye.util.Postable;
import lombok.Getter;
import lombok.Setter;

public class DeathUpdate implements Postable {
    @Getter
    @Setter
    private String username;

    public DeathUpdate(String username) {
        this.username = username;
    }

    @Override
    public RequestHandler.Endpoint endpoint() {
        return RequestHandler.Endpoint.DEATH_UPDATE;
    }
}
