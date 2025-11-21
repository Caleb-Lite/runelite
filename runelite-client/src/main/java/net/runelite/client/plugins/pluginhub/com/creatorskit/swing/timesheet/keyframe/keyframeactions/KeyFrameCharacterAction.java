package net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.keyframeactions;

import net.runelite.client.plugins.pluginhub.com.creatorskit.Character;
import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.KeyFrame;
import lombok.Getter;

@Getter
public class KeyFrameCharacterAction extends KeyFrameAction
{
    private final Character character;
    private final KeyFrameCharacterActionType characterActionType;

    public KeyFrameCharacterAction(KeyFrame keyFrame, Character character, KeyFrameCharacterActionType characterActionType)
    {
        super(KeyFrameActionType.CHARACTER, keyFrame);
        this.character = character;
        this.characterActionType = characterActionType;
    }
}
