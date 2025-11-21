package net.runelite.client.plugins.pluginhub.com.chunktasks.tasks;

import lombok.Setter;

@Setter
public class ValueRange {
    private int min;
    private int max;

    public boolean contains(int value) {
        return value >= this.min && value <= this.max;
    }
}
