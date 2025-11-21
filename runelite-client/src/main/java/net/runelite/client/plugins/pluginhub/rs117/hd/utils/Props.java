package net.runelite.client.plugins.pluginhub.rs117.hd.utils;

import java.util.Properties;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static rs117.hd.utils.ResourcePath.path;

public class Props
{
	public static boolean DEVELOPMENT;

	private static final Properties env = new Properties();

	static {
		env.putAll(System.getProperties());
	}

	public static boolean has(String key)
	{
		return env.containsKey(key);
	}

	public static String get(String key)
	{
		return env.getProperty(key);
	}

	public static String getOrDefault(String key, String defaultValue)
	{
		String value = get(key);
		return value == null ? defaultValue : value;
	}

	public static String getOrDefault(String key, @Nonnull Supplier<String> defaultValueSupplier)
	{
		String value = get(key);
		return value == null ? defaultValueSupplier.get() : value;
	}

	public static boolean getBoolean(String key)
	{
		String value = get(key);
		if (value == null)
			return false;
		if (value.isEmpty())
			return true;
		value = value.toLowerCase();
		return value.equals("true") || value.equals("1") || value.equals("on") || value.equals("yes");
	}

	public static ResourcePath getFile(String key, @Nonnull Supplier<ResourcePath> fallback) {
		var path = get(key);
		return path != null ? path(path) : fallback.get();
	}

	@Nonnull(when = When.UNKNOWN) // Disable downstream null warnings, since they're not smart enough
	public static ResourcePath getFolder(String key, @Nonnull Supplier<ResourcePath> fallback) {
		var path = getFile(key, fallback);
		return path != null ? path.chroot() : null;
	}

	public static void set(String key, boolean value)
	{
		set(key, value ? "true" : "false");
	}

	public static void set(String key, String value)
	{
		if (value == null)
		{
			unset(key);
		}
		else
		{
			env.put(key, value);
		}
	}

	public static void unset(String key)
	{
		env.remove(key);
	}
}

/*
 * Copyright (c) 2025, Hooder <ahooder@protonmail.com>
 * Copyright (c) 2022, Mark <https://github.com/Mark7625>
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