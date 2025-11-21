package net.runelite.client.plugins.pluginhub.com.brastasauce.purchaseprogress.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.runelite.client.util.AsyncBufferedImage;

@AllArgsConstructor
public class PurchaseProgressItem implements Comparable<PurchaseProgressItem>
{
    @Getter
    private AsyncBufferedImage image;

    @Getter
    private String name;

    @Getter
    private int itemId;

    @Getter
    @Setter
    private int gePrice;

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof PurchaseProgressItem))
        {
            return false;
        }

        final PurchaseProgressItem item = (PurchaseProgressItem) obj;
        return item.getItemId() == this.itemId;
    }

    @Override
    public int compareTo(PurchaseProgressItem other)
    {
        return Integer.compare(gePrice, other.getGePrice());
    }
}
