package net.runelite.client.plugins.pluginhub.com.cornertileindicators;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;
import net.runelite.client.RuneLite;
import net.runelite.client.RuneLiteProperties;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		setWindowTitle("corner-tile-indicators (" + getCurrentGitBranch() + ") RL-" + RuneLiteProperties.getVersion());

//		System.setProperty("runelite.pluginhub.version", "1.8.24.1");
		ExternalPluginManager.loadBuiltin(CornerTileIndicatorsPlugin.class);
		RuneLite.main(args);
	}

	private static void setWindowTitle(String title) throws NoSuchFieldException, IllegalAccessException
	{
		Field propertiesField = RuneLiteProperties.class.getDeclaredField("properties");
		propertiesField.setAccessible(true);
		Properties properties = (Properties) propertiesField.get(null);
		properties.setProperty("runelite.title", title);
	}

	public static String getCurrentGitBranch() {
		try
		{
			Process process = Runtime.getRuntime().exec("git rev-parse --abbrev-ref HEAD");
			process.waitFor();

			BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));

			return reader.readLine();
		}catch (Exception e) {
			return "threw exception";
		}
	}
}
/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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