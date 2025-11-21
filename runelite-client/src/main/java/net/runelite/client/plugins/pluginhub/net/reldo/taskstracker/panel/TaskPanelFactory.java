package net.runelite.client.plugins.pluginhub.net.reldo.taskstracker.panel;

import net.runelite.client.plugins.pluginhub.net.reldo.taskstracker.data.task.TaskFromStruct;

public interface TaskPanelFactory
{
	TaskPanel create(TaskFromStruct task);
}
