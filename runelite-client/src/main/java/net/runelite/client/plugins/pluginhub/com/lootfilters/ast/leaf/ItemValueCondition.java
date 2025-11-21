package net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf;

import net.runelite.client.plugins.pluginhub.com.lootfilters.LootFiltersPlugin;
import net.runelite.client.plugins.pluginhub.com.lootfilters.model.Comparator;
import net.runelite.client.plugins.pluginhub.com.lootfilters.model.ValueType;
import net.runelite.client.plugins.pluginhub.com.lootfilters.model.PluginTileItem;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ItemValueCondition extends ComparatorCondition {
    private final ValueType valueType;

    public ItemValueCondition(int value, Comparator cmp, ValueType valueType) {
       super(value, cmp);
       this.valueType = valueType;
    }

    @Override
    public int getLhs(LootFiltersPlugin plugin, PluginTileItem item) {
        return getValue(item) * item.getQuantity();
    }

    private int getValue(PluginTileItem item) {
        switch (valueType) {
            case HIGHEST: return Math.max(item.getGePrice(), item.getHaPrice());
            case GE: return item.getGePrice();
            default: return item.getHaPrice();
        }
    }
}
