package net.runelite.client.plugins.pluginhub.com.bankvaluechanges;

import lombok.Getter;

public class PriceData {
    @Getter
    private final int id;
    @Getter
    private final int price;

    public PriceData(int id, int price) {
        this.id = id;
        this.price = price;
    }
}
