package net.runelite.client.plugins.pluginhub.com.bankmemory;

public interface BanksListInteractionListener {
    void selectedToOpen(BanksListEntry save);

    void selectedToDelete(BanksListEntry save);

    void saveBankAs(BanksListEntry save, String saveName);

    void copyBankSaveItemDataToClipboard(BanksListEntry save);

    void openBanksDiffPanel();
}
