package net.runelite.client.plugins.pluginhub.com.chunktasks.tasks;

import net.runelite.client.plugins.pluginhub.com.chunktasks.types.PatchType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FarmingPatchConfig {
    private PatchType patchType;
    private List<ValueRange> varbitRanges;
}
