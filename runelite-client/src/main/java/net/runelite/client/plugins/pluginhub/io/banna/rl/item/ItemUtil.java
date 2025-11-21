package net.runelite.client.plugins.pluginhub.io.banna.rl.item;

import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.ItemManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.function.Consumer;

@Singleton
public class ItemUtil {

    @Inject
    private Client client;

    @Inject
    private ClientThread clientThread;

    @Inject
    private ItemManager itemManager;

    public void getId(String searchString, Consumer<Integer> callback)
    {
        if (searchString == null || "".equals(searchString)) {
            return;
        }
        int nicknameId = ItemNicknames.checkNickname(searchString);
        if (nicknameId != -1) {
            callback.accept(nicknameId);
        }
        if (isItemId(searchString)) {
            callback.accept(Integer.parseInt(searchString));
        } else {
            // Now we do horrific filth to find the best match we can
            clientThread.invokeLater(() -> {
                for (int i = 0; i < client.getItemCount(); i++) {
                    ItemComposition itemComposition = itemManager.getItemComposition(itemManager.canonicalize(i));
                    if (itemComposition.getMembersName().equalsIgnoreCase(searchString)) {
                        callback.accept(i);
                        return;
                    }
                }
                callback.accept(-1);
            });
        }
    }

    private boolean isItemId(String input)
    {
        return input.matches("\\d+");
    }
}

