package net.runelite.client.plugins.pluginhub.abex.os.debug;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class ProfilerTest
{
	@Before
	public void before()
	{
		Profiler.init();
	}

	private long iteration(long value)
	{
		if ((value & 1) != 0)
		{
			value = value >>> 20 | value << 44;
		}
		else
		{
			value = iteration_inner(value);
		}
		return value;
	}

	private long iteration_inner(long value)
	{
		return value * 31;
	}

	@Test
	public void profile() throws InterruptedException
	{
		Semaphore done = new Semaphore(0);
		AtomicReference<Throwable> failure = new AtomicReference<>();
		Thread test = new Thread(() ->
		{
			long start = System.nanoTime();
			long v = start;
			try
			{
				for (; (System.nanoTime() - start) < 1_000_000_000L; )
				{
					for (long i = 0; i < 0xFFFFL; i++)
					{
						v = iteration(v);
					}
				}
				System.gc();
				for (; (System.nanoTime() - start) < 3_000_000_000L; )
				{
					for (long i = 0; i < 0xFFFFL; i++)
					{
						v = iteration(v);
					}
				}
				Profiler.stop(new byte[0]);
			}
			catch (Throwable t)
			{
				failure.set(t);
				throw t;
			}
			finally
			{
				log.info("got {} in {} ms", v, (System.nanoTime() - start) / 1_000_000);
				done.release();
			}
		}, "profile test thread");
		test.start();
		Profiler.start(new Thread[]{test}, 1024 * 1024, 1000);
		done.acquire();
		if (failure.get() != null)
		{
			Assert.fail();
		}
	}
}

