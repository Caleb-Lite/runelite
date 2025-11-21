package net.runelite.client.plugins.pluginhub.com.infernostats;

import java.util.Timer;
import java.util.TimerTask;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeathChargeTimer
{
	private Timer timer;

	@Getter
	private DeathChargeTimerState state;

	DeathChargeTimer()
	{
		timer = null;
		state = DeathChargeTimerState.IDLE;
	}

	public void start() {
		timer = new Timer();

		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				state = DeathChargeTimerState.EXPIRED;
			}
		}, 60 * 1000);

		state = DeathChargeTimerState.ACTIVE;
	}

	public void stop() {
		timer = null;
		state = DeathChargeTimerState.IDLE;
	}
}
