package net.runelite.client.plugins.pluginhub.com.commitorquit;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DiscordRecruitmentNotifierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DiscordRecruitmentPlugin.class);
		RuneLite.main(args);
	}
}
//package com.commitorquit;
//
//import com.commitorquit.models.Npc;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Objects;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class JsonUtils
//{
//	private static JsonUtils _instance;
//	private final List<Npc> npcList;
//
//	public static JsonUtils getInstance()
//	{
//		if (_instance == null)
//		{
//			_instance = new JsonUtils();
//		}
//
//		return _instance;
//	}
//
//	public JsonUtils()
//	{
//		try(InputStreamReader reader = new InputStreamReader(
//			Objects.requireNonNull(DiscordRecruitmentPlugin.class.getResourceAsStream("/monster-drops.json")),
//			StandardCharsets.UTF_8)) {
//			npcList = new Gson().fromJson(reader, new TypeToken<List<Npc>>() {}.getType());
//		}
//		catch (Exception e)
//		{
//			log.error("Error getting json", e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	public Npc getNpc(String npcName)
//	{
//		return npcList.stream().filter((i) -> npcName.equals(i.getNpcName())).findFirst().orElse(null);
//	}
//}

/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2020, MasterKenth
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
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