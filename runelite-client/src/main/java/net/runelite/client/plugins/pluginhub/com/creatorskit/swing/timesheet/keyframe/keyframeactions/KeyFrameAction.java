package net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.keyframeactions;

import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.KeyFrame;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyFrameAction
{
    private KeyFrameActionType actionType;
    private KeyFrame keyFrame;
}
