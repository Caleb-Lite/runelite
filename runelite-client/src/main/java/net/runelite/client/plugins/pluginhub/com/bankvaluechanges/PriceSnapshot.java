package net.runelite.client.plugins.pluginhub.com.bankvaluechanges;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PriceSnapshot implements Comparable<PriceSnapshot> {
    private final long timestamp;
    private final ArrayList<PriceData> priceData;

    public PriceSnapshot(long timestamp) {
        this.timestamp = timestamp;
        this.priceData = new ArrayList<>();
    }

    public void addPriceData(PriceData priceData) {
        this.priceData.add(priceData);
    }

    @Override
    public int compareTo(PriceSnapshot o) {
        long difference = this.timestamp - o.timestamp;
        return difference == 0 ? 0 : (int)(difference / Math.abs(difference));
    }
}
