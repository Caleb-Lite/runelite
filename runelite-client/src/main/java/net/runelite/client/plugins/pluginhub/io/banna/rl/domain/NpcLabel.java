package net.runelite.client.plugins.pluginhub.io.banna.rl.domain;

import lombok.Data;

import java.awt.*;

@Data
public class NpcLabel
{
    private Integer npcId;
    private String npcName;
    private String label;
    private Integer itemIconId;
    private String itemIconName;
    private Color color;
}
