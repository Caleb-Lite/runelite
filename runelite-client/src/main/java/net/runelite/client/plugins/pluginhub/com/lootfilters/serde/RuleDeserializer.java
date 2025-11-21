package net.runelite.client.plugins.pluginhub.com.lootfilters.serde;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.AndCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf.ItemIdCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf.ItemNameCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf.ItemQuantityCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf.ItemValueCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.OrCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.leaf.ItemOwnershipCondition;
import net.runelite.client.plugins.pluginhub.com.lootfilters.ast.Condition;
import lombok.AllArgsConstructor;

import java.lang.reflect.Type;

@AllArgsConstructor
public class RuleDeserializer implements JsonDeserializer<Condition> {
    private final Gson gson;

    @Override
    public Condition deserialize(JsonElement elem, Type type, JsonDeserializationContext ctx) throws JsonParseException {
        var object = elem.getAsJsonObject();
        var discriminator = object.get("discriminator").getAsString();
        switch (discriminator) {
            case "item_id":
                return gson.fromJson(object, ItemIdCondition.class);
            case "item_ownership":
                return gson.fromJson(object, ItemOwnershipCondition.class);
            case "item_name":
                return gson.fromJson(object, ItemNameCondition.class);
            case "item_value":
                return gson.fromJson(object, ItemValueCondition.class);
            case "item_quantity":
                return gson.fromJson(object, ItemQuantityCondition.class);
            case "and":
                return deserializeInner(object, AndCondition.class);
            case "or":
                return deserializeInner(object, OrCondition.class);
            default:
                throw new JsonParseException("unknown rule type " + discriminator);
        }
    }

    private Condition deserializeInner(JsonElement elem, Type type) throws JsonParseException {
        return gson.newBuilder()
                .registerTypeAdapter(Condition.class, this)
                .create()
                .fromJson(elem, type);
    }
}
