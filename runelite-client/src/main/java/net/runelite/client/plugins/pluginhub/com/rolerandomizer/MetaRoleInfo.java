package net.runelite.client.plugins.pluginhub.com.rolerandomizer;

import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Not to be confused with the regular BA role, this describes a more specific team role in the context of
 * the BA metagame. Currently, it distinguishes between main and second attacker roles in funs.
 *
 * @see com.rolerandomizer.BaRole
 */
@RequiredArgsConstructor
public enum MetaRoleInfo
{
	MAIN_ATTACKER("Main attacker", "m", BaRole.ATTACKER, ImmutableSet.of("m", "(?<!2)a")),
	SECOND_ATTACKER("2nd attacker", "2", BaRole.ATTACKER, ImmutableSet.of("2", "2a", "a")),
	HEALER("Healer", "h", BaRole.HEALER, ImmutableSet.of("h")),
	COLLECTOR("Collector", "c", BaRole.COLLECTOR, ImmutableSet.of("c")),
	DEFENDER("Defender", "d", BaRole.DEFENDER, ImmutableSet.of("d"));

	// one or more of any of the possible matches, separated by slashes
	public static final String SLASH_SEPARATED_MATCHER;

	static
	{
		Set<String> all = new HashSet<>();
		all.addAll(MAIN_ATTACKER.matches);
		all.addAll(SECOND_ATTACKER.matches);
		all.addAll(HEALER.matches);
		all.addAll(COLLECTOR.matches);
		all.addAll(DEFENDER.matches);
		String ors = "[" + String.join("|", all) + "]";
		SLASH_SEPARATED_MATCHER = ors + "(?:/" + ors + ")*";
	}

	@Getter
	private final String fullName;
	@Getter
	private final String shortName;
	@Getter
	private final BaRole role;
	@Getter
	private final Set<String> matches;

}

/*
 * Copyright (c) 2021, Keano Porcaro <keano@ransty.com>
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