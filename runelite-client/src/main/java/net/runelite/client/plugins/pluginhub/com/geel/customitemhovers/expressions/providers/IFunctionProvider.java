package net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.providers;

import net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.functions.Function;
import net.runelite.client.plugins.pluginhub.com.geel.customitemhovers.expressions.models.Token;

public interface IFunctionProvider {
    Function findFunction(String name, Token[] arguments);
}
