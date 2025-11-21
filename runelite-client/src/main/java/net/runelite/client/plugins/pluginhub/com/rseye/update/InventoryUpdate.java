package net.runelite.client.plugins.pluginhub.com.rseye.update;

import net.runelite.client.plugins.pluginhub.com.rseye.io.RequestHandler;
import net.runelite.client.plugins.pluginhub.com.rseye.util.Postable;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Item;

import java.util.List;

public class InventoryUpdate implements Postable {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private List<Item> items;

    public InventoryUpdate(String username, List<Item> items) {
        this.username = username;
        this.items = items;
    }

    @Override
    public RequestHandler.Endpoint endpoint() {
        return RequestHandler.Endpoint.INVENTORY_UPDATE;
    }
}
