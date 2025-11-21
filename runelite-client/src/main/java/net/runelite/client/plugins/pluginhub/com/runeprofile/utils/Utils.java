package net.runelite.client.plugins.pluginhub.com.runeprofile.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.runelite.client.util.LinkBrowser;
import net.runelite.client.util.Text;
import okhttp3.*;

import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@UtilityClass
public class Utils {
    // Code from: DinkPlugin
    // Repository: https://github.com/pajlads/DinkPlugin
    // License: BSD 2-Clause License
    public <T> CompletableFuture<T> readJson(@NonNull OkHttpClient httpClient, @NonNull Gson gson, @NonNull String url, @NonNull TypeToken<T> type) {
        return readUrl(httpClient, url, reader -> gson.fromJson(reader, type.getType()));
    }

    // Code from: DinkPlugin
    // Repository: https://github.com/pajlads/DinkPlugin
    // License: BSD 2-Clause License
    public <T> CompletableFuture<T> readUrl(@NonNull OkHttpClient httpClient, @NonNull String url, @NonNull Function<Reader, T> transformer) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                assert response.body() != null;
                try (Reader reader = response.body().charStream()) {
                    future.complete(transformer.apply(reader));
                } catch (Exception e) {
                    future.completeExceptionally(e);
                } finally {
                    response.close();
                }
            }
        });
        return future;
    }

    // Code from: DinkPlugin
    // Repository: https://github.com/pajlads/DinkPlugin
    // License: BSD 2-Clause License
    public String sanitize(String str) {
        if (str == null || str.isEmpty()) return "";
        return Text.removeTags(str.replace("<br>", "\n")).replace('\u00A0', ' ').trim();
    }

    public void openProfileInBrowser(String username) {
        String url = "https://www.runeprofile.com/" + username.replace(" ", "%20");
        LinkBrowser.browse(url);
    }

    public String getApiErrorMessage(Throwable ex, String defaultMessage) {
        String result = defaultMessage;

        if (ex instanceof RuneProfileApiException) {
            result = ex.getMessage();
        } else if (ex.getCause() instanceof RuneProfileApiException) {
            result = ex.getCause().getMessage();
        }

        return result;
    }
}

/*
 *
 *  * Copyright (c) 2021, Senmori
 *  * All rights reserved.
 *  *
 *  * Redistribution and use in source and binary forms, with or without
 *  * modification, are permitted provided that the following conditions are met:
 *  *
 *  * 1. Redistributions of source code must retain the above copyright notice, this
 *  *    list of conditions and the following disclaimer.
 *  * 2. Redistributions in binary form must reproduce the above copyright notice,
 *  *    this list of conditions and the following disclaimer in the documentation
 *  *    and/or other materials provided with the distribution.
 *  *
 *  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
