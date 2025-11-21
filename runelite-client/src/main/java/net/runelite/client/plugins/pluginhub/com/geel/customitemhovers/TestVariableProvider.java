package net.runelite.client.plugins.pluginhub.com.geel.customitemhovers;

import net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.providers.IVariableProvider;

import java.util.HashMap;

public class TestVariableProvider extends HashMap<String, String> implements IVariableProvider {
    @Override
    public String resolveVariable(String name) {
        return getOrDefault(name, null);
    }
}
