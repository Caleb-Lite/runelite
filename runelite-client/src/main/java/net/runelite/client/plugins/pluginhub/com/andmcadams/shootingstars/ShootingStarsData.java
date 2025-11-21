package net.runelite.client.plugins.pluginhub.com.andmcadams.shootingstars;

import java.time.Duration;
import java.time.Instant;
import lombok.Data;
import lombok.Getter;

@Data
public class ShootingStarsData
{
	@Getter
	private final int location;

	@Getter
	private final int world;

	@Getter
	private final long minTime;

	@Getter
	private final long maxTime;

	private static final String NOW_STRING = "Now";

	public ShootingStarsData(ShootingStarsLocation loc, int world, long minTime, long maxTime)
	{
		this.location = loc.getId();
		this.world = world;
		this.minTime = minTime;
		this.maxTime = maxTime;
	}

	public ShootingStarsLocation getShootingStarsLocation()
	{
		return ShootingStarsLocation.getLocation(this.location);
	}

	public boolean hasLanded()
	{
		Duration timeUntil = Duration.between(Instant.now(), Instant.ofEpochMilli(this.minTime * 1000));
		return NOW_STRING.equals(prettyPrintTime(timeUntil));
	}

	private String prettyPrintTime(Duration d)
	{
		long hours = d.toHours();
		StringBuilder timeStringBuilder = new StringBuilder();
		if (hours != 0)
			timeStringBuilder.append(hours).append(" hr ");

		long minutes = d.toMinutes() % 60;
		if (hours == 0 && minutes <= 0)
		{
			long seconds = d.getSeconds() % 60;
			if (seconds > 0)
				timeStringBuilder.append(String.format("%d sec", seconds));
			else
				timeStringBuilder.append(NOW_STRING);
		}
		else
			timeStringBuilder.append(String.format("%d min", minutes));

		return timeStringBuilder.toString();
	}

	public String getLandingTime()
	{
		String minTimeString = prettyPrintTime(Duration.between(Instant.now(), Instant.ofEpochMilli(this.minTime * 1000)));
		String maxTimeString = prettyPrintTime(Duration.between(Instant.now(), Instant.ofEpochMilli(this.maxTime * 1000)));

		// If the star has definitely landed, just return "Now"
		if (NOW_STRING.equals(maxTimeString))
			return NOW_STRING;

		return minTimeString + " - " + maxTimeString;
	}
}
