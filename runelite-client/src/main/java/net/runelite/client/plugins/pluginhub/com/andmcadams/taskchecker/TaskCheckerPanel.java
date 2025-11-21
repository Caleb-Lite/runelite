package net.runelite.client.plugins.pluginhub.com.andmcadams.taskchecker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.DynamicGridLayout;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.plugins.pluginhub.com.andmcadams.taskchecker.tasklist.TaskList;

@Slf4j
public class TaskCheckerPanel extends PluginPanel
{

	TaskCheckerPlugin taskCheckerPlugin;
	ArrayList<TaskPanel> taskPanelList = new ArrayList<>();

	private JScrollPane scrollPane;

	public TaskCheckerPanel(TaskCheckerPlugin taskCheckerPlugin, ArrayList<TaskList> taskLists)
	{
		super(false);

		this.taskCheckerPlugin = taskCheckerPlugin;

		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setLayout(new BorderLayout());

		// Create the container for the title and refresh task button
		JPanel topContainer = new JPanel();
		topContainer.setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		titlePanel.setLayout(new BorderLayout());

		JLabel title = new JLabel();
		title.setText("Task Checker");
		title.setForeground(Color.WHITE);
		titlePanel.add(title, BorderLayout.WEST);
		topContainer.add(titlePanel, BorderLayout.NORTH);

		JButton calculateTasksButton = new JButton("Check tasks");
		calculateTasksButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		calculateTasksButton.setLayout(new BorderLayout());
		calculateTasksButton.addActionListener((event) -> this.checkTasks());
		topContainer.add(calculateTasksButton, BorderLayout.SOUTH);

		add(topContainer, BorderLayout.NORTH);

		// Create the task list panel
		FixedWidthPanel taskListPanel = new FixedWidthPanel();
		taskListPanel.setLayout(new DynamicGridLayout(0, 1, 0, 2));

		for (TaskList taskList : taskLists)
		{
			addTaskListHeader(taskListPanel, taskList.getName());
			for (Task task : taskList.getTasks())
			{
				addTask(taskListPanel, task);
			}
		}

		scrollPane = new JScrollPane(taskListPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		scrollPane.setBackground(ColorScheme.DARK_GRAY_COLOR);

		add(scrollPane, BorderLayout.CENTER);

	}

	public void addTaskListHeader(JPanel taskListPanel, String name)
	{
		TaskListHeaderPanel taskListHeaderPanel = new TaskListHeaderPanel(name);
		taskListPanel.add(taskListHeaderPanel);
	}

	public void addTask(JPanel taskListPanel, Task task)
	{
		TaskPanel taskPanel = new TaskPanel(task);
		taskPanelList.add(taskPanel);
		taskListPanel.add(taskPanel);
	}

	public void checkTasks()
	{
		this.taskCheckerPlugin.checkTasks();
	}

	public void refresh()
	{

		for (TaskPanel taskPanel : taskPanelList)
		{
			taskPanel.updateCompletion();
		}

		repaint();
		revalidate();
	}
}

