package net.runelite.client.plugins.pluginhub.com.bankmemory.data;

public interface DataStoreUpdateListener {
    void currentBanksListChanged();

    void currentBanksListOrderChanged();

    void snapshotBanksListChanged();

    void displayNameMapUpdated();
}
