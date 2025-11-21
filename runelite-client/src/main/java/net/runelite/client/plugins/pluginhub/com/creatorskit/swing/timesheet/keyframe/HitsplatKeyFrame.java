package net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe;

import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.settings.HitsplatSprite;
import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.settings.HitsplatVariant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitsplatKeyFrame extends KeyFrame
{
    public static final double DEFAULT_DURATION = (double) 5 / 3;

    private int duration;
    private HitsplatSprite sprite;
    private HitsplatVariant variant;
    private int damage;

    public HitsplatKeyFrame(double tick, KeyFrameType hitsplatType, int duration, HitsplatSprite sprite, HitsplatVariant variant, int damage)
    {
        super(hitsplatType, tick);
        this.duration = duration;
        this.sprite = sprite;
        this.variant = variant;
        this.damage = damage;
    }
}
