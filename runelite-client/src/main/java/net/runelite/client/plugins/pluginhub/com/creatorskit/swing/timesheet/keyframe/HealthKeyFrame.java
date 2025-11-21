package net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe;

import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.settings.HealthbarSprite;
import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.timesheet.keyframe.settings.HitsplatSprite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthKeyFrame extends KeyFrame
{
    private double duration;
    private HealthbarSprite healthbarSprite;
    private int maxHealth;
    private int currentHealth;

    public HealthKeyFrame(double tick, double duration,
                          HealthbarSprite healthbarSprite,
                          int maxHealth, int currentHealth)
    {
        super(KeyFrameType.HEALTH, tick);
        this.duration = duration;
        this.healthbarSprite = healthbarSprite;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
    }
}
