package net.runelite.client.plugins.pluginhub.com.rseye.util;

import net.runelite.client.plugins.pluginhub.com.rseye.io.RequestHandler;

public interface Postable {
    RequestHandler.Endpoint endpoint();
}
