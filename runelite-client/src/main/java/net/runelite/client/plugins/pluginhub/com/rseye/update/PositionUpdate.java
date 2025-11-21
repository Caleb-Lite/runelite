package net.runelite.client.plugins.pluginhub.com.rseye.update;

import net.runelite.client.plugins.pluginhub.com.rseye.io.RequestHandler;
import net.runelite.client.plugins.pluginhub.com.rseye.util.Postable;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.coords.WorldPoint;

public class PositionUpdate implements Postable {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private WorldPoint position;

    public PositionUpdate(String username, WorldPoint position) {
        this.username = username;
        this.position = position;
    }

    @Override
    public RequestHandler.Endpoint endpoint() {
        return RequestHandler.Endpoint.POSITION_UPDATE;
    }
}
