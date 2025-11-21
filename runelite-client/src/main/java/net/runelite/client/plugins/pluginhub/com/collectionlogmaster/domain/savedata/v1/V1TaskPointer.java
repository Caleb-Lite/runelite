package net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.savedata.v1;

import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.Task;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.TaskTier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class V1TaskPointer {
    private TaskTier taskTier;
    private Task task;
}
