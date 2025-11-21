package net.runelite.client.plugins.pluginhub.com.logmaster.domain.savedata.v1;

import net.runelite.client.plugins.pluginhub.com.logmaster.domain.Task;
import net.runelite.client.plugins.pluginhub.com.logmaster.domain.TaskTier;
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
