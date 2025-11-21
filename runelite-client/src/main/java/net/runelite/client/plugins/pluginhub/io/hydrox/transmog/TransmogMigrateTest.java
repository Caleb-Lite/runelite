package net.runelite.client.plugins.pluginhub.io.hydrox.transmog;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import net.runelite.client.plugins.pluginhub.io.hydrox.transmog.config.TransmogrificationConfigManager;
import net.runelite.api.Client;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.game.ItemManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransmogMigrateTest
{
	private static final String V1_CONFIG = "2643,19697,null,24802,null,24804,-1,10083,24680,-1";

	@Mock
	@Bind
	private Client client;

	@Mock
	@Bind
	private ClientThread clientThread;

	@Mock
	@Bind
	private Notifier notifier;

	@Mock
	@Bind
	private ItemManager itemManager;

	@Mock
	@Bind
	private ChatMessageManager chatMessageManager;

	@Mock
	@Bind
	private TransmogrificationConfigManager config;

	@Inject
	private TransmogrificationManager manager;

	@Before
	public void before()
	{
		Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
	}

	@Test
	public void testMigrateV1()
	{
		when(config.lastIndex()).thenReturn(0);
		// config.getPresetData(0) == null
		when(config.getPresetData(2)).thenReturn(V1_CONFIG);
		when(config.getPresetData(4)).thenReturn(V1_CONFIG);

		manager.migrateV1();

		verify(config).lastIndex(5);
		verify(config, times(2)).savePreset(anyInt(), any());
	}

	@Test
	public void testDontMigrate()
	{
		when(config.lastIndex()).thenReturn(3);

		manager.migrateV1();

		verify(config, never()).lastIndex(5);
		verify(config, never()).savePreset(anyInt(), any());
	}
}
