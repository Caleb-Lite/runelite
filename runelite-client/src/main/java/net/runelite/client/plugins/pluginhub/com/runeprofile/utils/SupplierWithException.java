package net.runelite.client.plugins.pluginhub.com.runeprofile.utils;

@FunctionalInterface
public interface SupplierWithException<T, E extends Exception> {
    T get() throws E;
}
