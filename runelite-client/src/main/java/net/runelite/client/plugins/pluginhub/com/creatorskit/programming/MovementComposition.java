package net.runelite.client.plugins.pluginhub.com.creatorskit.programming;

import net.runelite.client.plugins.pluginhub.com.creatorskit.programming.orientation.OrientationAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.coords.LocalPoint;

@AllArgsConstructor
@Getter
@Setter
public class MovementComposition
{
    private boolean moving;
    private LocalPoint localPoint;
    private OrientationAction orientationAction;
    private int orientationGoal;
    private int orientationToSet;
}
