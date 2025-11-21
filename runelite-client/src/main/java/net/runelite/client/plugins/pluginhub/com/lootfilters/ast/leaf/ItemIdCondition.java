package net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf;

import net.runelite.client.plugins.pluginhub.com.lootfilters.LootFiltersPlugin;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.LeafCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.model.PluginTileItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class ItemIdCondition extends LeafCondition {
    private final List<Integer> ids;

    public ItemIdCondition(List<Integer> ids) {
        this.ids = ids;
    }

    public ItemIdCondition(int id) {
        this.ids = List.of(id);
    }

    @Override
    public boolean test(LootFiltersPlugin plugin, PluginTileItem item) {
        return ids.stream().anyMatch(it -> item.getId() == it);
    }
}
