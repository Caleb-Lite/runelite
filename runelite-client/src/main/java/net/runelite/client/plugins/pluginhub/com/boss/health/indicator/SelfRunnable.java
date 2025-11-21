package net.runelite.client.plugins.pluginhub.com.boss.health.indicator;

@FunctionalInterface
public interface SelfRunnable<T> {
    void run(T self);
}
