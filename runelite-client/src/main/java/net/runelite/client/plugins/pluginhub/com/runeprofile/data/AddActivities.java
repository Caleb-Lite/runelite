package net.runelite.client.plugins.pluginhub.com.runeprofile.data;

import net.runelite.client.plugins.pluginhub.com.runeprofile.data.activities.ActivityData;
import net.runelite.client.plugins.pluginhub.com.runeprofile.data.activities.Activity;
import lombok.Data;

import java.util.List;

@Data
public class AddActivities {
    private final String id;
    private final List<? extends Activity<? extends ActivityData>> activities;
}
