package net.runelite.client.plugins.pluginhub.com.flippingutilities.utilities;

import lombok.Data;

import java.util.Map;

@Data
public class WikiRequest {
    Map<Integer, WikiItemMargins> data;
}
