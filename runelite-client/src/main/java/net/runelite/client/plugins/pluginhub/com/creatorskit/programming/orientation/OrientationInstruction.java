package net.runelite.client.plugins.pluginhub.com.creatorskit.programming.orientation;

import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.KeyFrameType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrientationInstruction
{
    private KeyFrameType type;
    private boolean setOrientation;
}
