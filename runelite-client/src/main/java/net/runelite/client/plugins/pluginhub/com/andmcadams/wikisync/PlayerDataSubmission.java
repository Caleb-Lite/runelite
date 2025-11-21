package net.runelite.client.plugins.pluginhub.com.andmcadams.wikisync;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDataSubmission
{
    private String username;
    private String profile;
    private PlayerData data;
}