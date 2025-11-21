package net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf;

import net.runelite.client.plugins.pluginhub.com.lootfilters.LootFiltersPlugin;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.LeafCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.model.PluginTileItem;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
public class ItemTradeableCondition extends LeafCondition {
    private final boolean target;

    public ItemTradeableCondition(boolean target) {
        this.target = target;
    }

    @Override
    public boolean test(LootFiltersPlugin plugin, PluginTileItem item) {
        return item.isMoney() ? target : item.isTradeable() == target;
    }
}
