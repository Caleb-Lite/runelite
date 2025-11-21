package net.runelite.client.plugins.pluginhub.com.runeprofile.data;

import lombok.Data;

import java.util.List;

@Data
public class CollectionLogPage {
    private final String name;
    private final List<CollectionLogItem> items;
}
