package net.runelite.client.plugins.pluginhub.net.clanhalls.plugin.beans;

import lombok.Data;

@Data
public class ClanInfo {
    private String uuid;
    private String name;
    private String nameInGame;
    private String lastSyncedAt;
    private boolean isAdmin;
}
