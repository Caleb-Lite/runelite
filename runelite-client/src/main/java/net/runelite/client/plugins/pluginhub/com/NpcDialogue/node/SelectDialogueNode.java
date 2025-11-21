package net.runelite.client.plugins.pluginhub.com.NpcDialogue.node;

public class SelectDialogueNode extends DialogueNode
{
	public SelectDialogueNode(String content)
	{
		super("{{tselect|" + content + "}}");
	}
}
