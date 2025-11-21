package net.runelite.client.plugins.pluginhub.gg.embargo.manifest;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Data
public class Manifest
{
    // misc
    final float version = -1;

    //Varp/varb trackable items
    final int[] varbits = new int[0];
    final int[] varps = new int[0];

    //Untrackable items
    public final List<Integer> untrackableItems = new ArrayList<>();

    //Raid/Minigame completion messages
    public Map<String, String> raidCompletionMessages = new HashMap<>();
    public Map<String, String> minigameCompletionMessages = new HashMap<>();

    //Collection log
    public final ArrayList<Integer> collections = new ArrayList<>();

    //Easter egg
    public Map<String, String> itemRenames = new HashMap<>();
    public Map<String, String> npcRenames = new HashMap<>();

}