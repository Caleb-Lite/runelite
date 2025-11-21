package net.runelite.client.plugins.pluginhub.com.NpcDialogue.node;

public class OptionDialogueNode extends DialogueNode
{
	public OptionDialogueNode(String content)
	{
		super("{{topt|" + content + "}}");
	}
}
