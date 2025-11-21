package net.runelite.client.plugins.pluginhub.com.creatorskit.saves;


import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.KeyFrame;
import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.KeyFrameType;
import lombok.Getter;

@Getter
public class ModelKeyFrameSave extends KeyFrame
{
    private boolean useCustomModel;
    private int modelId;
    private int customModel;
    private int radius;

    public ModelKeyFrameSave(double tick, boolean useCustomModel, int modelId, int customModel, int radius)
    {
        super(KeyFrameType.MODEL, tick);
        this.useCustomModel = useCustomModel;
        this.modelId = modelId;
        this.customModel = customModel;
        this.radius = radius;
    }
}
