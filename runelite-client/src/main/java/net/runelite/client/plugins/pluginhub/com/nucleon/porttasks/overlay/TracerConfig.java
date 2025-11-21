package net.runelite.client.plugins.pluginhub.com.nucleon.porttasks.overlay;

import net.runelite.client.plugins.pluginhub.com.nucleon.porttasks.PortTasksConfig;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.events.BeforeRender;
import net.runelite.client.eventbus.Subscribe;
import javax.inject.Inject;
import javax.inject.Singleton;

public class TracerConfig
{
	@Getter
	private int frameTick = 0;
	@Getter
	@Setter
	private int tracerSpeed = 30;
	@Getter
	@Setter
	private float tracerIntensity = 0.6f;
	@Getter
	@Setter
	private boolean tracerEnabled;
	private long lastUpdateNanos = 0;
	private final PortTasksConfig config;

	@Inject
	@Singleton
	public TracerConfig(PortTasksConfig config)
	{
		this.config = config;
	}

	@SuppressWarnings("unused")
	@Subscribe
	public void onBeforeRender(BeforeRender event)
	{
		if (!tracerEnabled)
		{
			return;
		}

		long FRAME_INTERVAL_NANOS = tracerSpeed == 0 ? Long.MAX_VALUE : 1_000_000_000L / tracerSpeed;
		long now = System.nanoTime();
		if (now - lastUpdateNanos >= FRAME_INTERVAL_NANOS)
		{
			frameTick = (frameTick + 1) % 1000;
			lastUpdateNanos = now;
		}
	}
	public void loadConfigs(PortTasksConfig config)
	{
		this.tracerEnabled = config.enableTracer();
		this.tracerIntensity = 1f - (config.tracerIntensity() / 100f);
		this.tracerSpeed = config.tracerSpeed();
	}
}

/*
 * Copyright (c) 2025, nucleon <https://github.com/nucleon>
 * Copyright (c) 2025, Cooper Morris <https://github.com/coopermor>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */