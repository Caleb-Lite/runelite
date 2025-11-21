package net.runelite.client.plugins.pluginhub.com.mogcounter;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstantCountTuple
{
	private Instant instant;
	private int count;
}

