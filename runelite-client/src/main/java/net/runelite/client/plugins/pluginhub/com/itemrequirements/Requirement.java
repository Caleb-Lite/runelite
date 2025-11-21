package net.runelite.client.plugins.pluginhub.com.itemrequirements;

import net.runelite.api.Client;

public interface Requirement
{
    boolean isMet(Client client);

    String getMessage();
}
