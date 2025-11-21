package net.runelite.client.plugins.pluginhub.com.cosmetics;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class CosmeticsCache {
    private static final String DATABASE_URL = "https://billiardssketch.fun/cosmetics/";
    private static final int TIME_TO_LIVE = 5 * 60 * 1000;
    private final HashMap<String, CosmeticsData> cache = new HashMap<>();
    private final OkHttpClient httpClient = new OkHttpClient();

    public void clear() {
        cache.clear();
    }

    public CosmeticsPlayer getCosmetics(String username) {
        if (isValid(username)) {
            return cache.get(username).getPlayer();
        } else {
            return null;
        }
    }

    public void save(CosmeticsPlayer p, String apiKey) {
        synchronized (cache) {
            cache.put(p.name, new CosmeticsData(System.currentTimeMillis(), p));
        }
        httpClient.newCall(buildRequestSave(p, apiKey)).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response)
            {

            }
        });
    }

    public void fillCache(String[] names) {
        String[] filtered = Arrays.stream(names).filter((x) -> !isValid(x)).toArray(String[]::new);
        if (filtered.length == 0) {
            return;
        }
        synchronized (cache) {
            long time = System.currentTimeMillis();
            for (String s: filtered) {
                CosmeticsData cd = cache.get(s);
                if (cd != null) {
                    cd.setFetchedTime(time);
                } else {
                    cache.put(s, new CosmeticsData(time, null));
                }
            }
        }
        httpClient.newCall(buildRequestFetch(filtered)).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                synchronized (cache) {
                    Gson gson = new Gson();
                    long time = System.currentTimeMillis();
                    List<CosmeticsPlayer> data = gson.fromJson(response.body().string(), new TypeToken<List<CosmeticsPlayer>>() {}.getType());
                    for (CosmeticsPlayer p: data) {
                        cache.put(p.name, new CosmeticsData(time, p));
                    }
                }
            }
        });
    }

    public boolean isValid(String username) {
        return cache.containsKey(username) && cache.get(username).getFetchedTime() + TIME_TO_LIVE > System.currentTimeMillis();
    }

    public Request buildRequestFetch(String[] names) {
        HttpUrl url = null;
        try {
            url = HttpUrl.get(new URL(DATABASE_URL)).newBuilder()
                    .addPathSegment(String.join(",", names))
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new Request.Builder()
                .url(url)
                .build();
    }

    public Request buildRequestSave(CosmeticsPlayer p, String apiKey) {
        HttpUrl url = null;
        try {
            url = HttpUrl.get(new URL(DATABASE_URL)).newBuilder()
                    .addPathSegment(apiKey)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        String s = gson.toJson(p);
        RequestBody body = RequestBody.create(JSON, s);
        log.debug("{}", s);
        return new Request.Builder()
                .url(url)
                .put(body)
                .build();
    }
}

/*
 * Copyright (c) 2021, JohnathonNow <johnjwesthoff@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */