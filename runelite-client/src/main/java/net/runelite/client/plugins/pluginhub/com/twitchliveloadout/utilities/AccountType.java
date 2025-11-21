package net.runelite.client.plugins.pluginhub.com.twitchliveloadout.utilities;

import lombok.Getter;

public enum AccountType
{
	/**
	 * Normal account type.
	 */
	NORMAL(0, "NORMAL"),
	/**
	 * Ironman account type.
	 */
	IRONMAN(1, "IRONMAN"),
	/**
	 * Ultimate ironman account type.
	 */
	ULTIMATE_IRONMAN(2, "ULTIMATE_IRONMAN"),
	/**
	 * Hardcore ironman account type.
	 */
	HARDCORE_IRONMAN(3, "HARDCORE_IRONMAN"),
	/**
	 * Group ironman account type
	 */
	GROUP_IRONMAN(4, "GROUP_IRONMAN"),
	/**
	 * Hardcore group ironman account type
	 */
	HARDCORE_GROUP_IRONMAN(5, "HARDCORE_GROUP_IRONMAN"),

	/**
	 * Unranked group ironman account type
	 */
	UNRANKED_GROUP_IRONMAN(6, "UNRANKED_GROUP_IRONMAN");

	@Getter
	private final int id;

	@Getter
	private final String key;

	AccountType(int id, String key)
	{
		this.id = id;
		this.key = key;
	}
}

/*
 * Copyright (c) 2017, honeyhoney <https://github.com/honeyhoney>
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