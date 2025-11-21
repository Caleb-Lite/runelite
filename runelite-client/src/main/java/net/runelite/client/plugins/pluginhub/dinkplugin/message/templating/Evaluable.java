package net.runelite.client.plugins.pluginhub.dinkplugin.message.templating;

@FunctionalInterface
public interface Evaluable {
    String evaluate(boolean rich);
}
