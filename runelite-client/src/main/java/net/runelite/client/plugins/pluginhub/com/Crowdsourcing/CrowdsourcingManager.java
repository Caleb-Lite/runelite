package net.runelite.client.plugins.pluginhub.com.Crowdsourcing;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Singleton
public class CrowdsourcingManager
{

	private static final String CROWDSOURCING_BASE = "https://crowdsource.runescape.wiki/runelite";
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final int MAX_LENGTH = 10000;

	@Inject
	private OkHttpClient okHttpClient;

	@Inject
	private Gson gson;

	@Inject
	private ChatMessageManager chatMessageManager;

	private List<Object> data = new ArrayList<>();

	public int size()
	{
		return data.size();
	}

	public void storeEvent(Object event)
	{
		if (this.size() > MAX_LENGTH)
		{
			return;
		}

		synchronized (this)
		{
			data.add(event);
		}
	}

	protected void submitToAPI()
	{
		List<Object> temp;
		synchronized (this)
		{
			if (data.isEmpty())
			{
				return;
			}
			temp = data;
			data = new ArrayList<>();
		}

		Request r = new Request.Builder()
			.url(CROWDSOURCING_BASE)
			.post(RequestBody.create(JSON, gson.toJson(temp)))
			.build();

		okHttpClient.newCall(r).enqueue(new Callback()
		{
			@Override
			public void onFailure(Call call, IOException e)
			{
				log.debug("Error sending crowdsourcing data", e);
			}

			@Override
			public void onResponse(Call call, Response response)
			{
				log.debug("Successfully sent crowdsourcing data");
				response.close();
			}
		});
	}

	public void sendMessage(String message)
	{
		final ChatMessageBuilder chatMessage = new ChatMessageBuilder()
				.append(ChatColorType.HIGHLIGHT)
				.append(message)
				.append(ChatColorType.NORMAL);

		chatMessageManager.queue(QueuedMessage.builder()
				.type(ChatMessageType.ITEM_EXAMINE)
				.runeLiteFormattedMessage(chatMessage.build())
				.build());
	}
}
