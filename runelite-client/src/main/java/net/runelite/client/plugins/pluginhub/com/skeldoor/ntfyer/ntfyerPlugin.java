package net.runelite.client.plugins.pluginhub.com.skeldoor.ntfyer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.NotificationFired;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@PluginDescriptor(
	name = "ntfyer"
)
public class ntfyerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ntfyerConfig config;

	@Inject
	private EventBus eventBus;

	@Inject
	private OkHttpClient okHttpClient;

	@Override
	protected void startUp() throws Exception
	{
		log.info("ntfyer started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("ntfyer stopped!");
	}

	@Subscribe
	public void onNotificationFired(NotificationFired notificationFired)
	{
		if (Objects.equals(config.ntfyTopic(), "") || config.ntfyTopic() == null || Objects.equals(config.ntfyUrl(), "") || config.ntfyUrl() == null){
			return;
		}
		try {
			String ntfyUrl = config.ntfyUrl();
			if (!ntfyUrl.endsWith("/")) {
				ntfyUrl = ntfyUrl + "/";
			}
			ntfyUrl = ntfyUrl + config.ntfyTopic();

			MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
			Request r = new Request.Builder()
					.addHeader("Title", "RuneLite ntfyer")
					.url(ntfyUrl)
					.post(RequestBody.create(mediaType, notificationFired.getMessage()))
					.build();

			okHttpClient.newCall(r).enqueue(new Callback()
			{
				@Override
				public void onFailure(Call call, IOException e)
				{
					log.debug("Error sending post data", e);
				}

				@Override
				public void onResponse(Call call, Response response)
				{
					if (response.isSuccessful())
					{
						log.debug("Successfully sent ntfy notification");
						response.close();
					}
					else
					{
						log.debug("Post request unsuccessful");
						response.close();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Provides
	ntfyerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ntfyerConfig.class);
	}
}
