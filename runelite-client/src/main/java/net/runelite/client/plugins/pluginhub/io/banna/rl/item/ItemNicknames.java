package net.runelite.client.plugins.pluginhub.io.banna.rl.item;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public enum ItemNicknames
{
    BOWFA("bowfa", 25865),
    BOFA("bofa", 25865),
    TBOW("tbow", 20997),
    ZCB("zcb", 26374),
    DHCB("dhcb", 21012),
    ACB("acb", 11785),
    RCB("rcb", 9185),
    MSB("msb", 861),
    CHIN("chin", 10033);

    private final String nickname;
    private final int itemId;

    ItemNicknames(String nickname, int itemId) {
        this.nickname = nickname;
        this.itemId = itemId;
    }

    private static final Map<String, Integer> MAP = new HashMap<>();

    static
    {
        for (ItemNicknames i : values())
        {
            MAP.put(i.nickname.toLowerCase(), i.itemId);
        }
    }

    public static int checkNickname(String nickname)
    {
        return MAP.getOrDefault(nickname.toLowerCase(), -1);
    }

}
