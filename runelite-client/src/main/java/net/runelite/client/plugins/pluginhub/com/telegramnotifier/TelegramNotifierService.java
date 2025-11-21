package net.runelite.client.plugins.pluginhub.com.telegramnotifier;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.inject.Inject;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Slf4j

public class TelegramNotifierService
{
    private final TelegramNotifierConfig config;

    @Inject
    private OkHttpClient okHttpClient;

    @Inject
    public TelegramNotifierService(TelegramNotifierConfig config)
    {
        this.config = config;
    }

    public void sendMessage(String message)
    {
        String TELEGRAM_BOT_TOKEN = config.telegramBotToken();
        String CHAT_ID = config.telegramChatId();

        String urlStr = String.format(
                "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                TELEGRAM_BOT_TOKEN,
                CHAT_ID,
                URLEncoder.encode(message, StandardCharsets.UTF_8)
        );

        Request request = new Request.Builder()
                .url(urlStr)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                log.debug("Failed to send Telegram message: ", e);
            }

            @Override
            public void onResponse(Call call, Response response)
            {
                try (response) {
                    if (!response.isSuccessful()) {
                        log.debug("Failed to send Telegram message: {}", response.code());
                    }
                }
            }
        });
    }
}
