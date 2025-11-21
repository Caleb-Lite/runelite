package net.runelite.client.plugins.pluginhub.com.rseye.update;

import net.runelite.client.plugins.pluginhub.com.rseye.io.RequestHandler;
import net.runelite.client.plugins.pluginhub.com.rseye.util.Postable;
import lombok.Getter;
import lombok.Setter;

public class SkullUpdate implements Postable {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private int skull;

    public SkullUpdate(String username, int skull) {
        this.username = username;
        this.skull = skull;
    }

    @Override
    public RequestHandler.Endpoint endpoint() {
        return RequestHandler.Endpoint.SKULL_UPDATE;
    }
}
