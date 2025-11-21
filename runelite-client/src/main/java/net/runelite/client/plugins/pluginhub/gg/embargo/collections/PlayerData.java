package net.runelite.client.plugins.pluginhub.gg.embargo.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerData
{
    public List<Map<String, Map<String, Object>>> rawClogItems = new ArrayList<>();
}