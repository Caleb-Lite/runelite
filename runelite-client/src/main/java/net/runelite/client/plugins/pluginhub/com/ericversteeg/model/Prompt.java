package net.runelite.client.plugins.pluginhub.com.ericversteeg.model;

import com.google.gson.annotations.SerializedName;

import java.awt.*;

public class Prompt {

    public static int TIME_UNIT_SECONDS = 0;
    public static int TIME_UNIT_MINUTES = 1;

    public int id;

    public long posted = 0L;

    @SerializedName("enable")
    public boolean enable = false;

    @SerializedName("force")
    public boolean force = false;

    @SerializedName("duration")
    public int duration = 0;

    @SerializedName("cd")
    public int cooldown = 0;

    @SerializedName("unit")
    public int timeUnit = 0;

    @SerializedName("notify")
    public boolean notify = false;

    public long lastNotified;

    public boolean active = true;

    @SerializedName("text")
    public String text = "";

    @SerializedName("color")
    public String colorStr = "";

    public Color color = Color.WHITE;

    @SerializedName("location")
    public int location = 0;

    @SerializedName("anchor")
    public int anchorType = 0;

    @SerializedName("x")
    public int anchorX = 0;

    @SerializedName("y")
    public int anchorY = 0;

    @SerializedName("width")
    public int width = 140;

    @SerializedName("border")
    public boolean hasBorder = true;

    @SerializedName("bg")
    public String bgColorStr = "";

    public Color bgColor = Color.GRAY;

    @SerializedName("tsize")
    public int textSize = 0;

    @SerializedName("sound")
    public int sound = 0;

    @SerializedName("image")
    public int imageId = 0;

    @SerializedName("offset")
    public int imageOffset = 0;

    @SerializedName("noffset")
    public boolean isOffsetNegative = false;

    @SerializedName("times")
    public String times = "";

    @SerializedName("days")
    public String days = "";

    @SerializedName("dates")
    public String dates = "";

    @SerializedName("coords")
    public String coordinates = "";

    @SerializedName("radius")
    public int radius = 0;

    @SerializedName("geofences")
    public String geoFences = "";

    @SerializedName("regions")
    public String regionIds = "";

    @SerializedName("npcs")
    public String npcIds = "";

    @SerializedName("items")
    public String itemIds = "";

    @SerializedName("patterns")
    public String chatPatterns = "";

    public int savedCount = 0;

    public long getDurationMillis() {
        if (timeUnit == TIME_UNIT_SECONDS)
        {
            return duration * 1000L;
        }
        else if (timeUnit == TIME_UNIT_MINUTES)
        {
            return duration * 60 * 1000L;
        }
        else
        {
            return 0L;
        }
    }

    public long getCooldownMillis() {
        if (timeUnit == TIME_UNIT_SECONDS)
        {
            return cooldown * 1000L;
        }
        else if (timeUnit == TIME_UNIT_MINUTES)
        {
            return cooldown * 60 * 1000L;
        }
        else
        {
            return 0L;
        }
    }
}
