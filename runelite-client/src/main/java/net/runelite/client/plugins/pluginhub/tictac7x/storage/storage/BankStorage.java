package net.runelite.client.plugins.pluginhub.tictac7x.storage.storage;

import net.runelite.client.plugins.pluginhub.tictac7x.storage.TicTac7xStorageConfig;
import net.runelite.client.plugins.pluginhub.tictac7x.storage.utils.ItemContainerId;
import net.runelite.client.plugins.pluginhub.tictac7x.storage.utils.Provider;

import java.util.List;

public class BankStorage extends ConfigStorage {
    public BankStorage(final Provider provider) {
        super(TicTac7xStorageConfig.bank, ItemContainerId.BANK, provider);
    }

    public void depositItems(final List<StorageItem> items) {
        for (final StorageItem item : items) {
            addItem(item);
        }

        notifyListeners();
        updateConfig();
    }
}
