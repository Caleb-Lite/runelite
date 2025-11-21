package net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf;

import net.runelite.client.plugins.pluginhub.com.lootfilters.LootFiltersPlugin;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.LeafCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.model.PluginTileItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class ConstCondition extends LeafCondition {
    private final boolean target;

    public ConstCondition(boolean target) {
        this.target = target;
    }

    @Override
    public boolean test(LootFiltersPlugin plugin, PluginTileItem item) {
        return target;
    }
}
