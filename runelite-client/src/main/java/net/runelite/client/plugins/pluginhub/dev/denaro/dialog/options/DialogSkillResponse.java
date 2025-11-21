package net.runelite.client.plugins.pluginhub.dev.denaro.dialog.options;

import net.runelite.client.plugins.pluginhub.dev.denaro.dialog.options.requirements.DialogRequirement;
import org.tomlj.TomlArray;

import java.util.List;

public class DialogSkillResponse extends DialogResponse
{
    public String skillGroup;
    public DialogSkillResponse(TomlArray messages, List<DialogRequirement> requirements, String skillGroup) {
        super(messages, requirements);
        this.skillGroup = skillGroup;
    }

    public boolean isSkillGroup(String group)
    {
        return group.equalsIgnoreCase(this.skillGroup);
    }
}
