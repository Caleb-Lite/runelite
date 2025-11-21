package net.runelite.client.plugins.pluginhub.com.magnaboy.scripting;

import java.util.Queue;

public class ScriptFile {
	public Queue<ScriptAction> actions;
	public String name;

	public ScriptAction nextAction() {
		ScriptAction action = actions.poll();
		if (action != null) {
			actions.add(action);
			return action;
		}
		return null;
	}

}
