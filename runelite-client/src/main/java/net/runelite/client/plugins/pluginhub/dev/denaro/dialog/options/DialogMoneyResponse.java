package net.runelite.client.plugins.pluginhub.dev.denaro.dialog.options;

import net.runelite.client.plugins.pluginhub.dev.denaro.dialog.options.requirements.DialogRequirement;
import org.tomlj.TomlArray;

import java.util.List;

public class DialogMoneyResponse extends DialogResponse
{
    public DialogMoneyResponse(TomlArray messages, List<DialogRequirement> requirements) {
        super(messages, requirements);
    }
}
