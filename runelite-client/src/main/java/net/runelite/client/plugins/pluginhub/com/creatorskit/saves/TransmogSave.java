package net.runelite.client.plugins.pluginhub.com.creatorskit.saves;

import net.runelite.client.plugins.pluginhub.com.creatorskit.models.CustomModelComp;
import net.runelite.client.plugins.pluginhub.com.creatorskit.swing.TransmogAnimationMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransmogSave
{
    private CustomModelComp customModelComp;
    private TransmogAnimationMode transmogAnimationMode;
    private int[][] animationSwaps;
    private int poseAnimation;
    private int walkAnimation;
    private int runAnimation;
    private int actionAnimation;
    private int backwardsAnimation;
    private int rightAnimation;
    private int leftAnimation;
    private int rotateAnimation;
    private int radius;
}
